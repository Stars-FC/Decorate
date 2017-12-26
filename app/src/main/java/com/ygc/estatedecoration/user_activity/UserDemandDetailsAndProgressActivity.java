package com.ygc.estatedecoration.user_activity;

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
import com.ygc.estatedecoration.fragment.home.TransactionManageNeedFragment;
import com.ygc.estatedecoration.fragment.home.TransactionManageOfferFragment;
import com.ygc.estatedecoration.user_fragment.UserProgressFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserDemandDetailsAndProgressActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mXTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private NeedBean.DataBean mDataBean;
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
        getIntentData();
        String[] titleArray = {"需求详情", "进  度"};
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(TransactionManageNeedFragment.getInstance(mDataBean));
        fragmentList.add(UserProgressFragment.newInstance(mDataBean));
        mViewpager.setAdapter(new DemandAndProgressLazyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(titleArray)));
        mXTabLayout.setupWithViewPager(mViewpager);
        if (mMark.equals("2")) {
            mViewpager.setCurrentItem(1);
        }
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mDataBean = (NeedBean.DataBean) intent.getSerializableExtra("dataBean");
        mMark = intent.getStringExtra("mark");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_demand_details_and_progress;
    }

    @OnClick({R.id.back})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.back:
                    finish();
                    break;
            }
        }
    }
}
