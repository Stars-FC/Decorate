package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.home.MyStoreBrightFragment;
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

    @BindView(R.id.rl_background)
    RelativeLayout mRelativeLayout;

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
        fragments.add(new MyStoreInformationFragment());
        fragments.add(new MyStoreEvaluateFragment());
        fragments.add(MyStorePersonalCaseFragment.newInstance());
        fragments.add(new MyStoreBrightFragment());

        List<String> list = new ArrayList<>();
        list.add("店铺信息");
        list.add("评价信用");
        list.add("案例");
        list.add("亮点");

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new HomeMyStoreAdapter(fragmentManager, fragments, list);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_mystore;
    }

    @OnClick({R.id.iv_back, R.id.et_edit,R.id.rl_background})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish(); //后退
                break;
            case R.id.et_edit://编辑
                Intent intent = new Intent(MyStoreActivity.this, ChangeStoreActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_background://更换背景图片
                showToast("更换背景图片");
                break;
        }
    }
}
