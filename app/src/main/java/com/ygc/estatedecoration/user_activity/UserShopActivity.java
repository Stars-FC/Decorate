package com.ygc.estatedecoration.user_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserFindMaterialAdapter;
import com.ygc.estatedecoration.adapter.UserFindMaterialHeaderAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserShopActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private UserFindMaterialAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("找材料");
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setRightImageResource(R.drawable.shouyesou);
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
        adapter = new UserFindMaterialAdapter(R.layout.item_user_shop_find_materials, dataList);
        View headerView = getLayoutInflater().inflate(R.layout.header_find_materials, null);
        RecyclerView materialsHeaderRecyclerView = (RecyclerView) headerView.findViewById(R.id.recyclerview);
        UserFindMaterialHeaderAdapter userFindMaterialHeaderAdapter = new UserFindMaterialHeaderAdapter(R.layout.item_user_shop_find_materials_header, dataList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        materialsHeaderRecyclerView.setLayoutManager(gridLayoutManager);
        materialsHeaderRecyclerView.setAdapter(userFindMaterialHeaderAdapter);
        userFindMaterialHeaderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(UserShopActivity.this, UserGoodListActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        adapter.addHeaderView(headerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(UserShopActivity.this, UserGoodsDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        for (int i = 0; i < 11; i++) {
            dataList.add("heh");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_shop;
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
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
}
