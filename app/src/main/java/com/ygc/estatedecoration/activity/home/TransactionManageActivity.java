package com.ygc.estatedecoration.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeTransactionManageAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/20.
 *首页-交易管理页面
 */

public class TransactionManageActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private ArrayList<TransactionDetailPager> mBasePagers;

    private HomeTransactionManageAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("交易管理");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
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
        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("进行中");
        list.add("已完成");
        list.add("招标中");
        list.add("已淘汰");

        mBasePagers = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            mBasePagers.add(new TransactionDetailPager(TransactionManageActivity.this, i, compositeDisposable));//有网络数据时传入获取打的数据集合、修改构造方法
        }

        mTabLayout.setupWithViewPager(mViewpager);

        mAdapter = new HomeTransactionManageAdapter(mBasePagers, list);

        mViewpager.setAdapter(mAdapter);

        mTabLayout.addOnTabSelectedListener(new TabListener());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_transactionmanage;
    }

    @OnClick({R.id.naviButtonLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviButtonLeft:
                finish();
                break;
        }
    }

    private class TabListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewpager.setCurrentItem(tab.getPosition(), true);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}
