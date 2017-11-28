package com.ygc.estatedecoration.user_activity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.home.TransactionManageNeedFragment;
import com.ygc.estatedecoration.fragment.home.TransactionManageOfferFragment;
import com.ygc.estatedecoration.user_fragment.UserMyCollectionFragment;
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

    private HomeMyStoreAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的收藏");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.mipmap.ic_launcher);
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
        mList=new ArrayList<>();
        mList.add("设计师");
        mList.add("施 工");
        mList.add("商 品");
        mList.add("监 理");

        mFragments=new ArrayList<>();
        mFragments.add(new UserMyCollectionFragment());
        mFragments.add(new UserMyCollectionFragment());
        mFragments.add(new UserMyCollectionFragment());
        mFragments.add(new UserMyCollectionFragment());

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new HomeMyStoreAdapter(fragmentManager, mFragments, mList);


        mViewpager.setAdapter(mAdapter);

        mTablayout.setupWithViewPager(mViewpager);
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
}
