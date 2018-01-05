package com.ygc.estatedecoration.user_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserMyCollectionGoodsAdapter;
import com.ygc.estatedecoration.adapter.UserMyCollectionResultChartAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.UserCollectionResultChartBean;
import com.ygc.estatedecoration.user_activity.UserCollectionActivity;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/28.
 * 用户端-我的收藏，效果图界面
 */

public class UserMyCollectionResultChartFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private UserMyCollectionResultChartAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        mAdapter = new UserMyCollectionResultChartAdapter();

        mRecyclerview.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRecyclerview.setAdapter(mAdapter);
        getDataFromNet();
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
                getDataFromNet();
            }
        });

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.recyclerview;
    }


    /**
     * 获取我的收藏数据
     */
    public void getDataFromNet() {
        if (!NetWorkUtil.isNetWorkConnect(mActivity)) {
            showToast("请检查网络设置");
            return;
        }
        APPApi.getInstance().service
                .myCollectionResultChart(UserUtils.getUserId(), "1")
//                .myCollectionResultChart("1", "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserCollectionResultChartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UserCollectionResultChartBean bean) {
                        String msg = bean.getMsg();
                        if ("1".equals(bean.getResponseState())) {
                            mAdapter.setNewData(bean.getData());
                        } else {
                            showToast(msg);
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        LogUtil.e("Fc_我的收藏-xiaoguotu" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
