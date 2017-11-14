package com.ygc.estatedecoration.activity.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.home.MyStoreEvaluateFragment;
import com.ygc.estatedecoration.fragment.home.MyStoreInformationFragment;
import com.ygc.estatedecoration.utils.tablayoutUnderLine;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的店铺页面
 */

public class MyStoreAvtivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

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

        mFragments = new ArrayList<>();
        mFragments.add(new MyStoreInformationFragment());
        mFragments.add(new MyStoreEvaluateFragment());

        mList = new ArrayList<>();
        mList.add("店铺信息");
        mList.add("评价信用");

        //设置viewpager的适配器
        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new HomeMyStoreAdapter(fragmentManager, mFragments, mList);
        mViewPager.setAdapter(mAdapter);
        //关联viewpager
        mTabLayout.setupWithViewPager(mViewPager);
        //为TabLayout设置ViewPager
//        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        //设置lablayout的固定或可滑动属性
//        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //设置tablayout下换线宽度
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                new tablayoutUnderLine().setIndicator(mTabLayout, 40, 40);
            }
        });
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
