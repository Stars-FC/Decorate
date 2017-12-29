package com.ygc.estatedecoration.activity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.MySettingBankCardAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectBankCardActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MySettingBankCardAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的银行卡");
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                finish();
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mAdapter = new MySettingBankCardAdapter();

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_bank_card;
    }

    @OnClick({R.id.naviFrameLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
        }
    }
}
