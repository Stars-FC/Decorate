package com.ygc.estatedecoration.activity.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.home.ServiceNeedFragment;
import com.ygc.estatedecoration.fragment.home.ServiceProgressFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DemandAndProgressActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private List<String> titleList = new ArrayList<>();

    private ArrayList<BaseFragment> fragmentList = new ArrayList<>();

    private ServiceNeedFragment mServiceNeedFragment;

    private ServiceProgressFragment mServiceProgressFragment;


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

        titleList.add("需求详情");
        titleList.add("进  度");

        fragmentList.add(ServiceNeedFragment.newInstance("", ""));
        fragmentList.add(ServiceProgressFragment.newInstance("", ""));

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeMyStoreAdapter adapter = new HomeMyStoreAdapter(fragmentManager, fragmentList, titleList);

        mViewpager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewpager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand_and_progress;
    }

    @OnClick({R.id.back, R.id.iv_follow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_follow://右上角关注按钮
                break;
        }
    }
}
