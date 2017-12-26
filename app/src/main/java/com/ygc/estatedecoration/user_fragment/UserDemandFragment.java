package com.ygc.estatedecoration.user_fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.MyCollectionAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.api.APPService;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.user_activity.UserDemandDetailsAndOfferActivity;
import com.ygc.estatedecoration.user_activity.UserDemandDetailsAndProgressActivity;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserDemandFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MyCollectionAdapter mAdapter;
    private SweetAlertDialog mPDialog;
    private int curPageNumAllData = 0;
    private String mMark = "";

    public static UserDemandFragment newInstance(String mark) {
        UserDemandFragment fragment = new UserDemandFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mark", mark);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMark = getArguments().getString("mark");
        }
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        mPDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
        mPDialog.show();
        getMyDemandData(0, Constant.NORMAL_REQUEST);
    }

    private void getMyDemandData(int pageNum, final String requestMark) {
        Log.i("521", "getMyDemandData: userId>" + UserUtils.getUserId());
        APPService service = APPApi.getInstance().service;
        Observable<NeedBean> needBeanObservable;
        if (TextUtils.isEmpty(mMark)) {
            needBeanObservable = service
                    .queryAllNeed(UserUtils.getUserId(), pageNum, null, null, null, null, null, null, null);
            Log.i("521", "getMyDemandData: 请求的是全部数据>>" + mMark);
        } else {
            needBeanObservable = service
                    .queryAllNeed(UserUtils.getUserId(), Integer.valueOf(mMark), pageNum, null, null, null, null, null, null, null);
            Log.i("521", "getMyDemandData: 请求的是分类数据>>" + mMark);
        }
        needBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeedBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull NeedBean needBean) {
                        if (needBean.responseState.equals("1")) {
                            int size = needBean.getData() == null ? 0 : needBean.getData().size();
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                mAdapter.setNewData(needBean.getData());
                                refreshFinishEvent(true);
                            } else {
                                if (size > 0) {
                                    mAdapter.addData(needBean.getData());
                                    mAdapter.loadMoreComplete();
                                } else {
                                    mAdapter.loadMoreEnd();
                                }
                            }
                        } else {
                            if (requestMark.equals(Constant.NORMAL_REQUEST)) {
                                loadMoreFinishEvent();
                            } else {
                                refreshFinishEvent(false);
                            }
                        }
                        showToast(needBean.msg);
                        cancelDialog();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
                        loadMoreFinishEvent();
                        refreshFinishEvent(false);
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
    }

    private void loadMoreFinishEvent() {
        mAdapter.loadMoreFail();
        curPageNumAllData -= 1;
        if (curPageNumAllData < 0) {
            curPageNumAllData = 0;
        }
    }

    private void refreshFinishEvent(boolean isSuccess) {
        mAdapter.setEnableLoadMore(true);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            if (isSuccess) {
                curPageNumAllData = 0;
            }
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new MyCollectionAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
//                intent.setClass(mActivity, UserDemandDetailsAndProgressActivity.class);
                intent.setClass(mActivity, UserDemandDetailsAndOfferActivity.class);
                NeedBean.DataBean dataBean = (NeedBean.DataBean) adapter.getData().get(position);
                intent.putExtra("dataBean", dataBean);
                startActivity(intent);
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
                curPageNumAllData += 1;
                getMyDemandData(curPageNumAllData, Constant.NORMAL_REQUEST);
            }
        }, mRecyclerView);
        mAdapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_user_demand;
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        getMyDemandData(0, Constant.REFRESH_REQUEST);
    }
}
