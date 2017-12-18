package com.ygc.estatedecoration.activity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.AccountDetailAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.UserBalanceOrderBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AccountDetailActivity extends BaseActivity {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private AccountDetailAdapter mAdapter;

    private int pager = 0;
    private List<UserBalanceOrderBean.DataBean> mData;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("账户明细");
        bar.setLeftImageResource(R.drawable.fanhui);
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
//        mAdapter.disableLoadMoreIfNotFullPage(mRecyclerView);//默认第一次加载会进入回调，如果不需要可以配置：
        getUserBalanceOrder(0, Constant.REFRESH_REQUEST);
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new AccountDetailAdapter(R.layout.item_account_detail);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_detail;
    }

    @OnClick({R.id.naviFrameLeft})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
            }
        }
    }

    /**
     * 获取网路数据
     *
     * @param pageNum     当前页数
     * @param requestMark 传入上啦刷新，或下拉加载状态
     */
    public void getUserBalanceOrder(final int pageNum, final String requestMark) {
        APPApi.getInstance().service
//                .queryBalanceOrder(UserUtils.getUserId().toString(), pageNum)
                .queryBalanceOrder("1", pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBalanceOrderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UserBalanceOrderBean bean) {
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
