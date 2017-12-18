package com.ygc.estatedecoration.user_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserShopCarAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.UserBalanceOrderBean;
import com.ygc.estatedecoration.bean.UserShopCarBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserShopCarActivity extends BaseActivity {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private UserShopCarAdapter mAdapter;

    @BindView(R.id.calculate_layout)
    LinearLayout mLl_calculateLayout;
    @BindView(R.id.delete_layout)
    LinearLayout mLl_deleteLayout;

    private int pager = 0;
    private List<UserShopCarBean.DataBean> mData;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("购物车");
        bar.setRightText("编辑");
        return true;
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        //下拉加载
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                getUserBalanceOrder(0, Constant.REFRESH_REQUEST);
            }
        });
        //加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ++pager;
                getUserBalanceOrder(pager, Constant.NORMAL_REQUEST);
            }
        }, mRecyclerView);
        mAdapter.disableLoadMoreIfNotFullPage(mRecyclerView);//默认第一次加载会进入回调，如果不需要可以配置：
        getUserBalanceOrder(0, Constant.REFRESH_REQUEST);
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new UserShopCarAdapter(R.layout.item_shop_car);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_shop_car;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.naviFrameRight:
                    editEvent();
                    break;
            }
        }
    }

    private boolean curState = true;// true 代表当前是结算状态

    private void editEvent() {
        curState = !curState;
        if (curState) {
            mLl_calculateLayout.setVisibility(View.VISIBLE);
            mLl_deleteLayout.setVisibility(View.GONE);
            mTitleBar.setRightText("编辑");
        } else {
            mLl_calculateLayout.setVisibility(View.GONE);
            mLl_deleteLayout.setVisibility(View.VISIBLE);
            mTitleBar.setRightText("完成");
        }
    }

    public void getUserBalanceOrder(final int pageNum, final String requestMark) {
        APPApi.getInstance().service
//                .queryBalanceOrder(UserUtils.getUserId().toString(), pageNum)
                .queryCommodityCarInfo("1", pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserShopCarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UserShopCarBean bean) {
                        int size = bean.getData() == null ? 0 : bean.getData().size();

                        if ("1".equals(bean.getResponseState())) {
                            mData = bean.getData();
                            if (Constant.REFRESH_REQUEST.equals(requestMark)) {
                                mAdapter.setNewData(mData);
                                pager = 0;
                                mAdapter.setEnableLoadMore(true);
                            } else if (Constant.NORMAL_REQUEST.equals(requestMark)) {
                                if (size > 0) {
                                    mAdapter.addData(mData);
                                }
                            }
                            if (size < 10) {
                                //第一页如果不够一页就不显示没有更多数据布局
                                mAdapter.loadMoreEnd(false);
                            } else {
                                mAdapter.loadMoreComplete();
                            }
                        } else {
                            showToast(bean.getMsg());
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        --pager;
                        if (pager < 0) {
                            pager = 0;
                        }
                        mAdapter.loadMoreFail();
                        mAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);

                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
