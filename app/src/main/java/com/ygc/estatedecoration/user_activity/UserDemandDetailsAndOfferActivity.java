package com.ygc.estatedecoration.user_activity;

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
import com.ygc.estatedecoration.user_fragment.UserDemandFragment;
import com.ygc.estatedecoration.user_fragment.UserOfferFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserDemandDetailsAndOfferActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mXTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private NeedBean.DataBean mDataBean;

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
        fragmentList.add(UserOfferFragment.newInstance(mDataBean));

        mViewpager.setAdapter(new DemandAndProgressLazyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(titleArray)));
        mXTabLayout.setupWithViewPager(mViewpager);
    }

    private void getIntentData() {
        mDataBean = (NeedBean.DataBean) getIntent().getSerializableExtra("dataBean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_demand_details_and_offer;
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
