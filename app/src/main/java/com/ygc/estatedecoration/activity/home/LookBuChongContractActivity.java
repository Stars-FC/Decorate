package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.DisplayBuChongContractFuJianPicAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.ContractInfoBean;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LookBuChongContractActivity extends BaseActivity {

    @BindView(R.id.supplementary_contract_name)
    TextView mSupplementaryContractName;
    @BindView(R.id.supplementary_contract_content)
    TextView mSupplementaryContractContent;
    @BindView(R.id.supplementary_contract_pay_money)
    TextView mSupplementaryContractPayMoney;
    @BindView(R.id.start_time_et)
    TextView mStartTimeEt;
    @BindView(R.id.end_time_et)
    TextView mEndTimeEt;
    @BindView(R.id.supplementary_contract_day)
    TextView mSupplementaryContractDay;
    @BindView(R.id.supplementary_contract_supplementary_content)
    TextView mSupplementaryContractSupplementaryContent;
    @BindView(R.id.upload_pic_recyclerview)
    RecyclerView mUploadPicRecyclerview;
    private String mConId;
    private String mRcId;

    @BindView(R.id.supplementary_contract_submit)
    Button mBtn_submit;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("补充合同");
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
        getIntentData();
        getContractInfoEvent();
    }

    private void getContractInfoEvent() {
        showDialog();
        APPApi.getInstance().service
                .getContractInfo(mConId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContractInfoBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull ContractInfoBean contractInfoBean) {
                        cancelDialog();
                        List<ContractInfoBean.DataBean> data = contractInfoBean.getData();
                        if (data != null && data.size() > 0) {
                            List<ContractInfoBean.DataBean.ContractStageListBean> contractStageList = data.get(0).getContractStageList();
                            List<ContractInfoBean.DataBean.ReplenishContractBean> replenishContract = data.get(0).getReplenishContract();
                            if (replenishContract != null && replenishContract.size() > 0) {
                                for (int i = 0; i < replenishContract.size(); i++) {
                                    ContractInfoBean.DataBean.ReplenishContractBean replenishContractBean = replenishContract.get(i);
                                    if (replenishContractBean.getRcId().equals(mRcId)) {
                                        String title = replenishContractBean.getTitle();
                                        String detail = replenishContractBean.getDetail();
                                        String price = replenishContractBean.getPrice();
                                        String startTime = replenishContractBean.getStartTime();
                                        String endTime = replenishContractBean.getEndTime();
                                        int needDays = replenishContractBean.getNeedDays();
                                        String replenishDetail = replenishContractBean.getReplenishDetail();
                                        String accessory = replenishContractBean.getAccessory();
                                        mSupplementaryContractName.setText(title);
                                        mSupplementaryContractContent.setText(detail);
                                        mSupplementaryContractPayMoney.setText(price);
                                        mStartTimeEt.setText(startTime);
                                        mEndTimeEt.setText(endTime);
                                        mSupplementaryContractDay.setText(String.valueOf(needDays));
                                        mSupplementaryContractSupplementaryContent.setText(replenishDetail);
                                        initRecyclerView(accessory);
                                        String rcState = replenishContractBean.getRcState();

                                        break;
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        cancelDialog();
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initRecyclerView(String accessory) {
        if (TextUtils.isEmpty(accessory)) {
            return;
        }
        List<String> imgUrlList = new ArrayList<>();
        if (accessory.contains(",")) {
            String[] split = accessory.split(",");
            imgUrlList.addAll(Arrays.asList(split));
        } else {
            imgUrlList.add(accessory);
        }
        DisplayBuChongContractFuJianPicAdapter adapter = new DisplayBuChongContractFuJianPicAdapter(R.layout.item_publish_need, imgUrlList);
        mUploadPicRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mUploadPicRecyclerview.setAdapter(adapter);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mConId = intent.getStringExtra("conId");
        mRcId = intent.getStringExtra("rcId");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_look_bu_chong_contract;
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
