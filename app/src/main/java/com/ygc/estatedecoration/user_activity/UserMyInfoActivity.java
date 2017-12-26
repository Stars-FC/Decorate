package com.ygc.estatedecoration.user_activity;

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
import com.ygc.estatedecoration.user_fragment.UserEvaluateFragment;
import com.ygc.estatedecoration.user_fragment.UserInformationFragment;
import com.ygc.estatedecoration.user_fragment.UserPersonalCaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/14.
 * 设计师，材料商……等的个人信息主页
 */

public class UserMyInfoActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

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

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new UserInformationFragment());
        fragments.add(new UserEvaluateFragment());
        fragments.add(UserPersonalCaseFragment.newInstance());

        List<String> list = new ArrayList<>();
        list.add("店铺信息");
        list.add("评价信用");
        list.add("个人案例");

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new HomeMyStoreAdapter(fragmentManager, fragments, list);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_my_info;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish(); //后退
    }
}
