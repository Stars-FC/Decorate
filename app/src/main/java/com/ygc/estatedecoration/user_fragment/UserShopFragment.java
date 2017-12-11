package com.ygc.estatedecoration.user_fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserShopMaterialAdapter;
import com.ygc.estatedecoration.adapter.UserShopMaterialHeaderAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.user_activity.UserGoodListActivity;
import com.ygc.estatedecoration.user_activity.UserGoodsDetailActivity;
import com.ygc.estatedecoration.user_activity.UserSearchActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserShopFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private UserShopMaterialAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    public static UserShopFragment newInstance() {
        return new UserShopFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("找材料");
        bar.setRightImageResource(R.drawable.shouyesou);
        return true;
    }

    @Override
    protected void initData(Bundle arguments) {
        for (int i = 0; i < 11; i++) {
            dataList.add("heh");
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new UserShopMaterialAdapter(R.layout.item_user_shop_materials, dataList);
        View headerView = LayoutInflater.from(mActivity).inflate(R.layout.header_find_materials, null);
        RecyclerView materialsHeaderRecyclerView = (RecyclerView) headerView.findViewById(R.id.recyclerview);
        UserShopMaterialHeaderAdapter userShopMaterialHeaderAdapter = new UserShopMaterialHeaderAdapter(R.layout.item_user_shop_find_materials_header, dataList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2, GridLayoutManager.HORIZONTAL, false);
        materialsHeaderRecyclerView.setLayoutManager(gridLayoutManager);
        materialsHeaderRecyclerView.setAdapter(userShopMaterialHeaderAdapter);
        userShopMaterialHeaderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, UserGoodListActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        adapter.addHeaderView(headerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, UserGoodsDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_user_shop;
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @OnClick({R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameRight:
                    Intent intent = new Intent(mActivity, UserSearchActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
