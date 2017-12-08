package com.ygc.estatedecoration.user_activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.OrderFragmentAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.DisplayAllUserOrdersBean;
import com.ygc.estatedecoration.user_fragment.AllOrderFragment;
import com.ygc.estatedecoration.user_fragment.ForTheGoodsFragment;
import com.ygc.estatedecoration.user_fragment.PaymentPendingOrderFragment;
import com.ygc.estatedecoration.user_fragment.QualitySureFragment;
import com.ygc.estatedecoration.user_fragment.ToEvaluateFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserAllOrderActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private DisplayAllUserOrdersBean bean;
    private int mOrderType = 0;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的订单");
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        initTabAndViewPager();
    }

    private void initTabAndViewPager() {

        AllOrderFragment allOrderFragment = new AllOrderFragment();
        PaymentPendingOrderFragment paymentPendingOrderFragment = new PaymentPendingOrderFragment();
        ForTheGoodsFragment forTheGoodsFragment = new ForTheGoodsFragment();
        ToEvaluateFragment toEvaluateFragment = new ToEvaluateFragment();
        QualitySureFragment qualitySureFragment = new QualitySureFragment();

        List<Fragment> fragments = new ArrayList<>(Arrays.asList(allOrderFragment, paymentPendingOrderFragment,
                forTheGoodsFragment, toEvaluateFragment, qualitySureFragment));
        OrderFragmentAdapter orderFragmentAdapter = new OrderFragmentAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(orderFragmentAdapter);
        mViewPager.setCurrentItem(mOrderType);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getIntentData();
    }

    private void getIntentData() {
        mOrderType = getIntent().getIntExtra("orderType", -1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_all_order;
    }

    public DisplayAllUserOrdersBean getBean() {
        return bean;
    }

    @OnClick({R.id.naviFrameLeft})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
            }
        }
    }
}
