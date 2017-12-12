package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.event.ChangeContractStateMsg;
import com.ygc.estatedecoration.event.ContractStateMsg;
import com.ygc.estatedecoration.fragment.home.ServiceNeedFragment;
import com.ygc.estatedecoration.fragment.home.ServiceProgressFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DemandAndProgressActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @BindView(R.id.start_contract_btn)
    Button mBtn_startContract;

    private List<String> titleList = new ArrayList<>();

    private ArrayList<BaseFragment> fragmentList = new ArrayList<>();

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
        titleList.add("需求详情");
        titleList.add("进  度");

        fragmentList.add(ServiceNeedFragment.newInstance());
        fragmentList.add(ServiceProgressFragment.newInstance());

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeMyStoreAdapter adapter = new HomeMyStoreAdapter(fragmentManager, fragmentList, titleList);

        mViewpager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewpager);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("521", "onActivityResult: DemandAndProgresActivity。。。。");
        EventBus.getDefault().post(new ChangeContractStateMsg(data, requestCode, resultCode));
    }
}
