package com.ygc.estatedecoration.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeTransactionManageAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.base.BasePager;
import com.ygc.estatedecoration.utils.tablayoutUnderLine;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FC on 2017/11/20.
 */

public class TransactionManageActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout mTablayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private List<String> mList;  //创建标题集合

    private ArrayList<TransactionDetailPager> mBasePagers;  //viewpager中几个页面

    private HomeTransactionManageAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("交易管理");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.mipmap.ic_launcher);
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
        mList.add("全部");
        mList.add("中标进行中");
        mList.add("已完成");
        mList.add("招标中");
        mList.add("已淘汰");
        mList.add("已关闭");

        mBasePagers = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            mBasePagers.add(new TransactionDetailPager(TransactionManageActivity.this));//有网络数据时传入获取打的数据集合、修改构造方法
        }

        mTablayout.setupWithViewPager(mViewpager);

        mAdapter = new HomeTransactionManageAdapter(mBasePagers, mList);

        mViewpager.setAdapter(mAdapter);

//        //设置tablayout下换线宽度
//        mTablayout.post(new Runnable() {
//            @Override
//            public void run() {
//                new tablayoutUnderLine().setIndicator(mTablayout, 5, 5);
//            }
//        });

        mTablayout.addOnTabSelectedListener(new TabListener());

        //滑动或禁止属性
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_transactionmanage;
    }

    /**
     * tablayout点击的监听事件
     */
    public class TabListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            // 默认切换的时候，会有一个过渡动画，设为false后，取消动画，直接显示
            mViewpager.setCurrentItem(tab.getPosition(), false);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}
