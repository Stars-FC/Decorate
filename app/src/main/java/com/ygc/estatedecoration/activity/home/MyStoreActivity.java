package com.ygc.estatedecoration.activity.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.home.MyStoreEvaluateFragment;
import com.ygc.estatedecoration.fragment.home.MyStoreInformationFragment;
import com.ygc.estatedecoration.fragment.home.MyStorePersonalCaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的店铺页面
 */

public class MyStoreActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private ArrayList<BaseFragment> mFragments;//子界面集合
    private List<String> mList;//标题
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
        ImmersionBar.with(this).fitsSystemWindows(false).transparentStatusBar().init();

        mFragments = new ArrayList<>();
        mFragments.add(new MyStoreInformationFragment());
        mFragments.add(new MyStoreEvaluateFragment());
        mFragments.add(MyStorePersonalCaseFragment.newInstance());


        mList = new ArrayList<>();
        mList.add("店铺信息");
        mList.add("评价信用");
        mList.add("个人案例");

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new HomeMyStoreAdapter(fragmentManager, mFragments, mList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_mystore;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish(); //后退
    }
}
