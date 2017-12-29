package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidkun.xtablayout.XTabLayout;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.DemandAndProgressLazyFragmentPagerAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.ContractInfoBean;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.event.FaQiContractFinishMsg;
import com.ygc.estatedecoration.fragment.home.ServiceProgressFragment;
import com.ygc.estatedecoration.fragment.home.TransactionManageNeedFragment;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    private SweetAlertDialog mPDialog;
    private int mContractState = -1;
    private String mConId;
    private int sign = 0;

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
        initDialog();
        fragmentList.add(TransactionManageNeedFragment.getInstance(mDataBean));
        fragmentList.add(ServiceProgressFragment.newInstance(mDataBean));

        mViewpager.setAdapter(new DemandAndProgressLazyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(titleArray)));

        mTabLayout.setupWithViewPager(mViewpager);

        getContractInfoEvent();
    }

    private void getContractInfoEvent() {
        mPDialog.show();
        APPApi.getInstance().service
                .getContractInfo(String.valueOf(mDataBean.getDId()), UserUtils.sDataBean.getType())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContractInfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull ContractInfoBean contractInfoBean) {
                        cancelDialog();
                        List<ContractInfoBean.DataBean> data = contractInfoBean.getData();
                        if (data != null && data.size() > 0) {
                            mContractState = data.get(0).getContractState();
                            mConId = data.get(0).getConId();
                            List<ContractInfoBean.DataBean.ReplenishContractBean> replenishContract = data.get(0).getReplenishContract();
                            if (replenishContract != null && replenishContract.size() > 0) {
                                sign = replenishContract.size();
                            }
                            Log.i("521", "onNext: contractState:" + mContractState);
                            switch (mContractState) {
                                case 0:
                                    mBtn_startContract.setText("发起合同");
                                    mBtn_startContract.setBackgroundColor(Color.parseColor("#eeeeee"));
                                    break;
                                case 1:
                                    mBtn_startContract.setText("发起补充合同");
                                    break;
                                case 2:
                                    mBtn_startContract.setText("合同已完结");
                                    mBtn_startContract.setBackgroundColor(Color.parseColor("#eeeeee"));
                                    break;
                            }
                        } else {
                            Log.i("521", "onNext: contractState:" + mContractState);
                            mBtn_startContract.setText("发起合同");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initDialog() {
        mPDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
    }

    private void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
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
                if (mContractState == 1 || mContractState == -1) {
                    startContractEvent();
                }
                break;
        }
    }

    /**
     *发起合同
     */
    private void startContractEvent() {
        Intent intent = new Intent();
        if (mBtn_startContract.getText().toString().trim().equals("发起合同")) {
            intent.setClass(this, InitiatingContractActivity.class);
            intent.putExtra("dataBean", mDataBean);
            startActivity(intent);
        } else {
            intent.setClass(this, SupplementaryContractActivity.class);
            intent.putExtra("mark", "1");
            intent.putExtra("conId", mConId);
            intent.putExtra("sign", sign);
            startActivity(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMsg(FaQiContractFinishMsg faQiContractFinishMsg) {
        getContractInfoEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
