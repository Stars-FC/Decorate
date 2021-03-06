package com.ygc.estatedecoration.activity.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeTransactionManageDetailAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.base.BasePager;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.entity.base.Constant;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/20.
 * 交易管理的子页面
 */

public class TransactionDetailPager extends BasePager {
    private CompositeDisposable mCompositeDisposable;
    private String orderState = "-1";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HomeTransactionManageDetailAdapter mAdapter;
    private int curPageNum = 0;
    private SweetAlertDialog mSweetAlertDialog;

    public TransactionDetailPager(Context context, String orderState, CompositeDisposable compositeDisposable, SweetAlertDialog dialog) {
        super(context);
        this.orderState = orderState;
        this.mCompositeDisposable = compositeDisposable;
        this.mSweetAlertDialog = dialog;
    }

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.recyclerview, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mSweetAlertDialog.show();
        getTransactionDemandData(0, Constant.NORMAL_REQUEST);

        mAdapter = new HomeTransactionManageDetailAdapter();

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, DemandAndProgressActivity.class);
                mContext.startActivity(intent);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

        initListener();
    }

    private void getTransactionDemandData(int pageNum, final String requestMark) {
        Log.i("521", "getTransactionDemandData: orderState:" + orderState);
        APPApi.getInstance().service
                .queryRecommendNeed("9", "1", orderState, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeedBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
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
                            Toast.makeText(mContext, needBean.msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
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

    /**
     * 监听事件
     */
    private void initListener() {
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
}
