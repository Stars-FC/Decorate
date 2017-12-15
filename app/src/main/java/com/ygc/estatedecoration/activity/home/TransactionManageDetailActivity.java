package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
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

    private ArrayList<BaseFragment> mFragments;

    private List<String> mList;

    private HomeMyStoreAdapter mAdapter;
    private String mDIdStr;
    private NeedBean.DataBean mDataBean;
    private int mPosition;

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
        mList = new ArrayList<>();
        mList.add("需求详情");
        mList.add("报  价");

        mFragments = new ArrayList<>();
        mFragments.add(TransactionManageNeedFragment.getInstance(mDIdStr, mDataBean));
        mFragments.add(TransactionManageOfferFragment.getInstance(mDIdStr));

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new HomeMyStoreAdapter(fragmentManager, mFragments, mList);

        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(1);
        mXTabLayout.setupWithViewPager(mViewpager);

    }

    private void getIntentData() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        mDataBean = (NeedBean.DataBean) bundle.getSerializable("demand");
        mDIdStr = String.valueOf(mDataBean.getDId());
        mPosition = bundle.getInt("position");

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
                intent.putExtra("dId", mDIdStr);
                intent.putExtra("position", mPosition);
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
