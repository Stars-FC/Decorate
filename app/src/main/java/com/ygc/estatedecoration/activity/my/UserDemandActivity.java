package com.ygc.estatedecoration.activity.my;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.DemandAndProgressLazyFragmentPagerAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.user_fragment.UserDemandFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserDemandActivity extends BaseActivity{

    private int mMark;

    @BindView(R.id.tablayout)
    XTabLayout mXTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的需求");
        bar.setLeftImageResource(R.drawable.fanhui);
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
        getIntentData();

        String[] titleArray = {"全部", "已关闭", "进行中", "已完成"};
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(UserDemandFragment.newInstance(""));
        fragmentList.add(UserDemandFragment.newInstance("-1"));
        fragmentList.add(UserDemandFragment.newInstance("1"));
        fragmentList.add(UserDemandFragment.newInstance("2"));

        mViewpager.setAdapter(new DemandAndProgressLazyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(titleArray)));
        mXTabLayout.setupWithViewPager(mViewpager);
        if (mMark == 1) {
            mViewpager.setCurrentItem(2);
        }
    }

    private void getIntentData() {
        mMark = getIntent().getIntExtra("mark", -1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_demand;

    }
    @OnClick({R.id.naviFrameLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft://后退按钮
                finish();
                break;
        }
    }
}
