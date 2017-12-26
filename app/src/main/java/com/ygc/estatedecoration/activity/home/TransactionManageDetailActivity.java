package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.DemandAndProgressLazyFragmentPagerAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.event.OfferFinishMsg;
import com.ygc.estatedecoration.fragment.home.TransactionManageNeedFragment;
import com.ygc.estatedecoration.fragment.home.TransactionManageOfferFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/20.
 * 首页-交易管理界面
 */

public class TransactionManageDetailActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mXTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private NeedBean.DataBean mDataBean;
    private int mPosition;
    private String mMark;

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
        EventBus.getDefault().register(this);
        getIntentData();

        String[] titleArray = {"需求详情", "报  价"};
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(TransactionManageNeedFragment.getInstance(mDataBean));
        fragmentList.add(TransactionManageOfferFragment.getInstance(String.valueOf(mDataBean.getDId())));

        mViewpager.setAdapter(new DemandAndProgressLazyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(titleArray)));
        mXTabLayout.setupWithViewPager(mViewpager);

    }

    private void getIntentData() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        mDataBean = (NeedBean.DataBean) bundle.getSerializable("demand");
        mPosition = bundle.getInt("position");
        mMark = bundle.getString("mark");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_transactionmanage_item;
    }

    @OnClick({R.id.back,R.id.tv_offer})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_offer:
                intent.setClass(TransactionManageDetailActivity.this, TransactionManageOfferActivity.class);
                intent.putExtra("dId", String.valueOf(mDataBean.getDId()));
                intent.putExtra("position", mPosition);
                intent.putExtra("mark", mMark);
                startActivity(intent);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void offerFinishEvent(OfferFinishMsg offerFinishMsg) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
