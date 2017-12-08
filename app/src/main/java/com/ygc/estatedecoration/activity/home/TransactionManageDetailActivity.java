package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.home.TransactionManageNeedFragment;
import com.ygc.estatedecoration.fragment.home.TransactionManageOfferFragment;
import com.ygc.estatedecoration.utils.tablayoutUnderLine;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/20.
 * 首页-交易管理界面
 */

public class TransactionManageDetailActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mTablayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private ArrayList<BaseFragment> mFragments;

    private List<String> mList;

    private HomeMyStoreAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mList = new ArrayList<>();
        mList.add("需求详情");
        mList.add("报  价");

        mFragments = new ArrayList<>();
        mFragments.add(new TransactionManageNeedFragment());
        mFragments.add(new TransactionManageOfferFragment());

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new HomeMyStoreAdapter(fragmentManager, mFragments, mList);

        mViewpager.setAdapter(mAdapter);

        mTablayout.setupWithViewPager(mViewpager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_transactionmanage_item;
    }

    @OnClick({R.id.back, R.id.iv_follow,R.id.tv_offer})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_follow://右上角关注按钮
                break;
            case R.id.tv_offer:
                intent.setClass(TransactionManageDetailActivity.this, TransactionManageOfferActivity.class);
                startActivity(intent);
                break;
        }
    }
}
