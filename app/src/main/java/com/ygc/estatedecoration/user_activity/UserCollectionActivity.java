package com.ygc.estatedecoration.user_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.TransactionManageActivity;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.user_fragment.UserMyCollectionMaterialFragment;
import com.ygc.estatedecoration.user_fragment.UserMyCollectionPanoramaFragment;
import com.ygc.estatedecoration.user_fragment.UserMyCollectionResultChartFragment;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserCollectionActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mTablayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private ArrayList<BaseFragment> mFragments;

    private List<String> mList;

    private HomeLazyFragmentAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的收藏");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setRightText("编辑");
        bar.setRightTextColor(Color.BLACK);
        return true;
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
        mList.add("效果图");
        mList.add("全景图");
        mList.add("商 品");
        mFragments = new ArrayList<>();
        mFragments.add(new UserMyCollectionResultChartFragment());
        mFragments.add(new UserMyCollectionPanoramaFragment());
        mFragments.add(new UserMyCollectionMaterialFragment());

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new HomeLazyFragmentAdapter(fragmentManager);

        mViewpager.setAdapter(mAdapter);

//        mViewpager.setCurrentItem(2);
        mTablayout.setupWithViewPager(mViewpager);
//        mTablayout.addOnTabSelectedListener(new TabListener());
        mTablayout.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_my_collection;
    }

    @OnClick({R.id.naviButtonLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviButtonLeft://后退按钮
                finish();
                break;
        }
    }

    private class HomeLazyFragmentAdapter extends LazyFragmentPagerAdapter {

        public HomeLazyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment getItem(ViewGroup container, int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position);
        }
    }
}
