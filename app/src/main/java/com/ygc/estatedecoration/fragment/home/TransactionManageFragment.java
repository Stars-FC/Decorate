package com.ygc.estatedecoration.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.DemandAndProgressActivity;
import com.ygc.estatedecoration.adapter.HomeTransactionManageDetailAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TransactionManageFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable{

    private SweetAlertDialog mSweetAlertDialog;
    private String orderState;
    private int curPageNum = 0;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private HomeTransactionManageDetailAdapter mAdapter;

    public static TransactionManageFragment newInstance(String missionType) {
        TransactionManageFragment fragment = new TransactionManageFragment();
        Bundle args = new Bundle();
        args.putString("missionType", missionType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderState = getArguments().getString("missionType");
        }
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        Log.i("521", "initData: orderState:" + orderState);
        initDialog();
        getTransactionDemandData(0, Constant.NORMAL_REQUEST);
    }

    private void initDialog() {
        mSweetAlertDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mSweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.show();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new HomeTransactionManageDetailAdapter();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, DemandAndProgressActivity.class);
                NeedBean.DataBean dataBean = (NeedBean.DataBean) adapter.getItem(position);
                if (dataBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("demand", dataBean);
                    intent.putExtra("bundle", bundle);
                    mActivity.startActivity(intent);
                } else {
                    showToast("数据异常！");
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.setEnableLoadMore(false);
                getTransactionDemandData(0, Constant.REFRESH_REQUEST);
            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                curPageNum += 1;
                getTransactionDemandData(curPageNum, Constant.NORMAL_REQUEST);
            }
        }, mRecyclerView);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_transaction_manage;
    }

    private void getTransactionDemandData(int pageNum, final String requestMark) {
        Log.i("521", "getTransactionDemandData: orderState:" + orderState + "===pageNum:" + pageNum);
        APPApi.getInstance().service
                .queryRecommendNeed("9", "1", orderState, pageNum)
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
                            showToast(needBean.msg);
                        }
                        if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                            mAdapter.setEnableLoadMore(true);
                        }
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
        if (mSweetAlertDialog != null && mSweetAlertDialog.isShowing()) {
            mSweetAlertDialog.dismiss();
        }
    }

    private void loadMoreFinishEvent() {
        mAdapter.loadMoreFail();
        curPageNum -= 1;
        if (curPageNum < 0) {
            curPageNum = 0;
        }
    }

    private void refreshFinishEvent(boolean isSuccess) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            if (isSuccess) {
                curPageNum = 0;
            }
        }
    }
}
