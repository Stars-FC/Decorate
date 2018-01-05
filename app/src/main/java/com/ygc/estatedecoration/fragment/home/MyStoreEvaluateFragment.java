package com.ygc.estatedecoration.fragment.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreEvaluateAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.UserCommentBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的店铺-评价界面
 */

public class MyStoreEvaluateFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeMyStoreEvaluateAdapter mAdapter;

    private List<UserCommentBean.DataBean> mData;

    private int mPager = 0;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {

        mAdapter = new HomeMyStoreEvaluateAdapter();

        mRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void addListener() {

        //每个条目的点击事件
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("mRecyclerview第" + position + "数据");
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色

        //下拉加载
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                queryUserEvaluate(0, Constant.REFRESH_REQUEST);
            }
        });
        //加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ++mPager;
                queryUserEvaluate(mPager, Constant.NORMAL_REQUEST);
            }
        }, mRecyclerview);
        mAdapter.disableLoadMoreIfNotFullPage(mRecyclerview);//默认第一次加载会进入回调，如果不需要可以配置：
        queryUserEvaluate(0, Constant.REFRESH_REQUEST);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.recyclerview;
    }

    /**
     * 获取服务商评价
     */
    public void queryUserEvaluate(int pager, final String vaule) {
        APPApi.getInstance().service
                .queryUserEvaluate(Integer.parseInt(UserUtils.getUserId()), pager)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UserCommentBean bean) {
                        int size = bean.getData() == null ? 0 : bean.getData().size();
                        String msg = bean.getMsg();
                        if ("1".equals(bean.getResponseState())) {
                            mData = bean.getData();
                            if (Constant.REFRESH_REQUEST.equals(vaule)) {
                                mAdapter.setNewData(mData);
                                mPager = 0;
                                mAdapter.setEnableLoadMore(true);
                            } else if (Constant.NORMAL_REQUEST.equals(vaule)) {
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
                            --mPager;
                            if (mPager < 0) {
                                mPager = 0;
                            }
                            showToast(bean.getMsg());
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        --mPager;
                        if (mPager < 0) {
                            mPager = 0;
                        }
                        mAdapter.loadMoreFail();
                        mAdapter.setEnableLoadMore(true);
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
