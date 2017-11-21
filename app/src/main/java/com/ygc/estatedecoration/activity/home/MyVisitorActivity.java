package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.my.SettingActivity;
import com.ygc.estatedecoration.activity.my.SettingSaleActivity;
import com.ygc.estatedecoration.adapter.MyVisitorAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.entity.HomeMyVisitor;
import com.ygc.estatedecoration.entity.HomeMyVisitorSection;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/16.
 * 主页-我的访客界面
 */

public class MyVisitorActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private MyVisitorAdapter mMyVisitorAdapter;
    private List<HomeMyVisitorSection> mList;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的访客");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.mipmap.ic_launcher);
        return true;
    }

    @Override
    protected void addListener() {

        //每个条目的点击事件
        mMyVisitorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeMyVisitorSection mySection = mList.get(position);
                if (mySection.isHeader) {
                    showToast("时间" + position + "数据");
                } else {
                    showToast("访客" + position + "数据");
                }
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色

        //下拉加载
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMyVisitorAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        //上拉加载更多
        mMyVisitorAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mMyVisitorAdapter.loadMoreComplete();//完成
//                mAdapter.loadMoreFail();//失败
//                mAdapter.loadMoreEnd();//结束
            }
        }, mRecyclerView);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //为嵌套的两个recyclerview赋值
        mList = new ArrayList<>();
        mList.add(new HomeMyVisitorSection(true, "11月11日"));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "张三", "12:31")));
        mList.add(new HomeMyVisitorSection(true, "11月14日"));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "学习", "23:00")));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "使我", "5:00")));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "快乐", "")));
        mList.add(new HomeMyVisitorSection(true, "11月16日"));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "还好", "")));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "没", "12：00")));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "放弃", "13：00")));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "终于", "17:30")));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "等到", "")));
        mList.add(new HomeMyVisitorSection(new HomeMyVisitor("", "你", "")));

        mMyVisitorAdapter = new MyVisitorAdapter(R.layout.item_home_myvisitor_below, R.layout.item_home_myvisitor_top, mList);

        mRecyclerView.setAdapter(mMyVisitorAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_myvisitor;
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
