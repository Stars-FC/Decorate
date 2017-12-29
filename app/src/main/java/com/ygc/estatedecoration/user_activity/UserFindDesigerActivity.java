package com.ygc.estatedecoration.user_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserFindDesignerDetailAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.MyScrollview;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by FC on 2017/11/29.
 * 找设计页面
 */

public class UserFindDesigerActivity extends BaseActivity {

    @BindView(R.id.scrollView)
    MyScrollview mScrollView;
    @BindView(R.id.banner)
    BGABanner mBGABanner;
    @BindView(R.id.flow_view)
    LinearLayout flow_view;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private UserFindDesignerDetailAdapter mAdapter;
    List<String> list = new ArrayList<>();

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("找设计");
        bar.setRightImageResource(R.drawable.shouyesou);
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
            }
        }, mRecyclerview);
    }

    @Override
    protected void initView() {
        mScrollView.setFlowView(flow_view);
        mScrollView.setTopView(mBGABanner);
        initRecyclerView();
    }

    private void initRecyclerView() {
//        View topView = View.inflate(UserFindDesigerActivity.this, R.layout.user_find_designer, null);
        mAdapter = new UserFindDesignerDetailAdapter(list);
//        mAdapter.addHeaderView(topView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setAdapter(mAdapter);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_find_design;
    }

    @OnClick({R.id.naviButtonLeft, R.id.naviFrameRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviButtonLeft://后退按钮
                finish();
                break;
            case R.id.naviFrameRight:
                Intent intent = new Intent(this, UserSearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
