package com.ygc.estatedecoration.user_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.MyCollectionAdapter;
import com.ygc.estatedecoration.adapter.UserFindDesignerAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.BetterRecyclerView;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/29.
 */

public class UserFindDesigerActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @BindView(R.id.appbarlayout)
    AppBarLayout mBarLayout;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private UserFindDesignerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.mipmap.ic_launcher);
        bar.setTitleText("找设计");
        bar.setRightImageResource(R.mipmap.ic_launcher);
        return true;
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
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        //上拉加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mAdapter.loadMoreComplete();//完成
//                mAdapter.loadMoreFail();//失败
//                mAdapter.loadMoreEnd();//结束
            }
        }, mRecyclerview);
    }

    @Override
    protected void initView() {
        mLayoutManager = new LinearLayoutManager(UserFindDesigerActivity.this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        mAdapter = new UserFindDesignerAdapter(list, UserFindDesigerActivity.this);
        /**
         * 自定义RecyclerView实现对AppBarLayout的滚动效果
         */
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int firstVisiblePosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                    if (firstVisiblePosition == 0) {
                        mBarLayout.setExpanded(true, true);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_find_designer;
    }

    @OnClick({R.id.naviButtonLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviButtonLeft://后退按钮
                finish();
                break;
        }
    }
}
