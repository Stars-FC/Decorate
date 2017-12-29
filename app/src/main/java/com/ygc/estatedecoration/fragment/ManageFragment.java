package com.ygc.estatedecoration.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.DemandAndProgressActivity;
import com.ygc.estatedecoration.adapter.ManageAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/13.
 * 管理界面
 */

public class ManageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, LazyFragmentPagerAdapter.Laziable{

    private static final String ARG_C = "content";

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ManageAdapter mAdapter;
//    private SweetAlertDialog mPDialog;

    public static ManageFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        ManageFragment fragment = new ManageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("进行中任务");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        return true;
    }

    @Override
    protected void initData(Bundle arguments) {
//        initDialog();
        showDialog();
        getGoingDemandDataList(0, Constant.NORMAL_REQUEST);
    }

    /*private void initDialog() {
        mPDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
        mPDialog.show();
    }*/

    private void getGoingDemandDataList(int pageNum, final String requestMark) {
        APPApi.getInstance().service
                .queryRecommendNeed(UserUtils.getUserId(), "1", "0", pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeedBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull NeedBean needBean) {
                        cancelDialog();
                        if (needBean.responseState.equals("1")) {
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                mAdapter.setNewData(needBean.getData());
                                refreshFinishEvent(true);
                            } else {
                                if (needBean.getData().size() > 0) {
                                    mAdapter.loadMoreComplete();
                                    mAdapter.addData(needBean.getData());
                                } else {
                                    mAdapter.loadMoreEnd();
                                }
                            }
                        } else {
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                refreshFinishEvent(false);
                            } else {
                                loadMoreErrorEvent();
                            }
                        }
                        showToast(needBean.msg);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
                        refreshFinishEvent(false);
                        loadMoreErrorEvent();
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*private void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
    }*/

    private int curPageNum = 0;

    private void loadMoreErrorEvent() {
        mAdapter.loadMoreFail();
        curPageNum -= 1;
        if (curPageNum < 0) {
            curPageNum = 0;
        }
    }

    private void refreshFinishEvent(boolean isSuccess) {
        mAdapter.setEnableLoadMore(true);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            if (isSuccess) {
                curPageNum = 0;
            }
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new ManageAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, DemandAndProgressActivity.class);
                NeedBean.DataBean dataBean = (NeedBean.DataBean) adapter.getItem(position);
                if (dataBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("demand", dataBean);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } else {
                    showToast("数据异常！");
                }
            }
        });
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));

        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                curPageNum += 1;
                getGoingDemandDataList(curPageNum, Constant.NORMAL_REQUEST);
            }
        }, mRecyclerView);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_manage;
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        getGoingDemandDataList(0, Constant.REFRESH_REQUEST);
    }
}
