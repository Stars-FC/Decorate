package com.ygc.estatedecoration.user_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserShopMaterialAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserGoodListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private int mPosition;

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private UserShopMaterialAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected boolean buildTitle(TitleBar bar) {
        mTitleBar.setTitleText("瓷砖");
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
        adapter = new UserShopMaterialAdapter(R.layout.item_user_shop_find_materials_good_list, dataList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(UserGoodListActivity.this, UserGoodsDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPosition = getIntent().getIntExtra("position", -1);
        switch (mPosition) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;

        }
        for (int i = 0; i < 13; i++) {
            dataList.add("heh");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_good_list;
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
