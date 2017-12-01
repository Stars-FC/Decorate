package com.ygc.estatedecoration.user_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserFindSupervisorAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserFindSupervisorActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private UserFindSupervisorAdapter adapter;
    private List<String> dataList = new ArrayList<>();
    private int mMark;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("找监理");
        mTitleBar.setRightImageResource(R.drawable.shouyesou);
        mTitleBar.setLeftImageResource(R.drawable.fanhui);
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
        adapter = new UserFindSupervisorAdapter(R.layout.item_user_home_fragment_find_supervisor_more, dataList, mMark);
        View headerView = View.inflate(this, R.layout.header_find_supervisor, null);
        adapter.addHeaderView(headerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mMark = getIntent().getIntExtra("mark", 0);
        if (mMark == 1) {
            mTitleBar.setTitleText("找施工");
        } else {
            mTitleBar.setTitleText("找监理");
        }
        for (int i = 0; i < 10; i++) {
            dataList.add("hah");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_find_supervisor;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.naviFrameRight:
                    Intent intent = new Intent(this, UserSearchActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
