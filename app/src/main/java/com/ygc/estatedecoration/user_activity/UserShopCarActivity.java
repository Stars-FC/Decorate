package com.ygc.estatedecoration.user_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserShopCarAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserShopCarActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private UserShopCarAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    @BindView(R.id.calculate_layout)
    LinearLayout mLl_calculateLayout;
    @BindView(R.id.delete_layout)
    LinearLayout mLl_deleteLayout;

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
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new UserShopCarAdapter(R.layout.item_shop_car, dataList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        for (int i = 0; i < 10; i++) {
            dataList.add("heh");
        }
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

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
