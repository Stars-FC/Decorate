package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.DemandAndProgressLazyFragmentPagerAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.event.ChangeContractStateMsg;
import com.ygc.estatedecoration.event.ContractStateMsg;
import com.ygc.estatedecoration.fragment.home.ServiceProgressFragment;
import com.ygc.estatedecoration.fragment.home.TransactionManageNeedFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class DemandAndProgressActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @BindView(R.id.start_contract_btn)
    Button mBtn_startContract;

    private String[] titleArray = {"需求详情", "进  度"};
    private ArrayList<BaseFragment> fragmentList = new ArrayList<>();

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
        EventBus.getDefault().register(this);
        getIntentData();

        fragmentList.add(TransactionManageNeedFragment.getInstance(mDataBean));
        fragmentList.add(ServiceProgressFragment.newInstance(mDataBean));

        mViewpager.setAdapter(new DemandAndProgressLazyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(titleArray)));

        mTabLayout.setupWithViewPager(mViewpager);
    }

    private void getIntentData() {
        mDataBean = (NeedBean.DataBean) getIntent().getBundleExtra("bundle").getSerializable("demand");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand_and_progress;
    }

    @OnClick({R.id.back, R.id.telephone_ll, R.id.message_ll, R.id.start_contract_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.telephone_ll:
                break;
            case R.id.message_ll:
                break;
            case R.id.start_contract_btn:
                startContractEvent();
                break;
        }
    }

    private void startContractEvent() {
        if (mBtn_startContract.getText().toString().trim().equals("发起合同")) {
            startActivityForResult(new Intent(this, InitiatingContractActivity.class), 11);
        } else {
            startActivityForResult(new Intent(this, SupplementaryContractActivity.class), 12);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyContractState(ContractStateMsg contractStateMsg) {
        if (contractStateMsg.getContractState() == 1) {
            mBtn_startContract.setText("发起合同");
        } else {
            mBtn_startContract.setText("补充合同");
        }
    }
}
