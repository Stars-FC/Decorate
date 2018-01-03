package com.ygc.estatedecoration.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.ProjectMainStageAdapter2;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.ContractInfoBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.event.EditMainContractMsg;
import com.ygc.estatedecoration.event.UpdateServiceProgressMsg;
import com.ygc.estatedecoration.widget.MaxRecyclerView;
import com.ygc.estatedecoration.widget.TitleBar;
import com.zhy.autolayout.AutoLinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EditMainContractActivity extends BaseActivity {

    @BindView(R.id.contract_content_tv)
    TextView mTv_contractContent;

    @BindView(R.id.gongqi_iv)
    ImageView mGongqiIv;
    @BindView(R.id.project_all_time_et)
    TextView mProjectAllTimeEt;
    @BindView(R.id.gongqi_ll)
    LinearLayout mGongqiLl;
    @BindView(R.id.gongqi_v)
    View mV_gongQi;

    @BindView(R.id.zhibaojin_iv)
    ImageView mZhibaojinIv;
    @BindView(R.id.zhibaojin_et)
    EditText mZhibaojinEt;
    @BindView(R.id.zhibaojin_ll)
    LinearLayout mZhibaojinLl;
    @BindView(R.id.zhibaojin_v)
    View mZhibaojinV;

    @BindView(R.id.project_stage_iv)
    ImageView mProjectStageIv;
    @BindView(R.id.project_jine_et)
    TextView mProjectJineEt;
    @BindView(R.id.project_stage_ll)
    AutoLinearLayout mProjectStageLl;
    @BindView(R.id.project_stage_v)
    View mV_projectStage;
    @BindView(R.id.project_stage_recyclerview)
    MaxRecyclerView mMaxRecyclerView;
    @BindView(R.id.jia_time_tv)
    TextView mTv_jiaTime;
    @BindView(R.id.yi_time_tv)
    TextView mTv_yiTime;
    private ProjectMainStageAdapter2 mProjectStageAdapter;
    private String mConId;

    @BindView(R.id.jia_agree_iv)
    ImageView mIv_jiaAgree;
    @BindView(R.id.activity_initiating_contract_submit)
    Button mBtn_submit;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("编辑合同");
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mProjectStageAdapter = new ProjectMainStageAdapter2();
        mMaxRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mMaxRecyclerView.setNestedScrollingEnabled(false);
        mMaxRecyclerView.setAdapter(mProjectStageAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        getIntentData();
        getContractInfoRequest();
    }

    private void getContractInfoRequest() {
        showDialog();
        APPApi.getInstance().service
                .getContractInfo(mConId)
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
                        if (contractInfoBean.responseState.equals("1")) {
                            List<ContractInfoBean.DataBean> data = contractInfoBean.getData();
                            if (data != null && data.size() > 0) {
                                ContractInfoBean.DataBean dataBean = data.get(0);
                                handleEntityData(dataBean);
                            }
                        } else {
                            showToast(contractInfoBean.msg);
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

    private void handleEntityData(ContractInfoBean.DataBean dataBean) {
        mTv_contractContent.setText(dataBean.getContractDetail());
        mZhibaojinEt.setText(dataBean.getQualityGuaranteeDeposit() + "");

        List<ContractInfoBean.DataBean.ContractStageListBean> contractStageList = dataBean.getContractStageList();
        mProjectStageAdapter.setNewData(contractStageList);

        double allMoney = 0.00;
        int allDay = 0;
        for (ContractInfoBean.DataBean.ContractStageListBean contractStageListBean : contractStageList) {
            allMoney += Double.valueOf(contractStageListBean.getPrice());
            allDay += contractStageListBean.getNeedDays();
        }
        mProjectAllTimeEt.setText(String.valueOf(allDay) + "天");
        mProjectJineEt.setText(allMoney + "");

        String createTime = dataBean.getCreateTime();
        mTv_jiaTime.setText(createTime.substring(0, 10));
        mTv_yiTime.setText(createTime.substring(0, 10));
    }

    private void getIntentData() {
        mConId = getIntent().getStringExtra("conId");
    }

    @OnClick({R.id.naviFrameLeft, R.id.gongqi_rl, R.id.zhibaojin_rl, R.id.project_stage_rl, R.id.activity_initiating_contract_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.gongqi_rl:
                clickGongQiEvent();
                break;
            case R.id.zhibaojin_rl:
                clickZhiBaoJinEvent();
                break;
            case R.id.project_stage_rl:
                clickProjectStageEvent();
                break;
            case R.id.activity_initiating_contract_submit:
                sureModify();
                break;
        }
    }

    private void sureModify() {
        String zhiBaoJinEStr = mZhibaojinEt.getText().toString().trim();
        if (TextUtils.isEmpty(zhiBaoJinEStr)) {
            showToast("请输入质保金额");
            return;
        }
        java.text.DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String zhiBaoJinD = decimalFormat.format(Double.valueOf(zhiBaoJinEStr));
        showDialog();
        APPApi.getInstance().service
                .modifyMainContractInfo(mConId, zhiBaoJinD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Base base) {
                        if (base.responseState.equals("1")) {
                            EventBus.getDefault().post(new UpdateServiceProgressMsg());
                            finish();
                        }
                        showToast(base.msg);
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

    private boolean gongQi = false;

    private void clickGongQiEvent() {
        gongQi = !gongQi;
        if (gongQi) {
            mGongqiLl.setVisibility(View.GONE);
            mGongqiIv.setImageResource(R.drawable.gengduo);
            mV_gongQi.setVisibility(View.VISIBLE);
        } else {
            mGongqiLl.setVisibility(View.VISIBLE);
            mGongqiIv.setImageResource(R.drawable.gengduoxiangxia);
            mV_gongQi.setVisibility(View.GONE);
        }
    }

    private boolean zhibaojin = false;

    private void clickZhiBaoJinEvent() {
        zhibaojin = !zhibaojin;
        if (zhibaojin) {
            mZhibaojinLl.setVisibility(View.VISIBLE);
            mZhibaojinIv.setImageResource(R.drawable.gengduoxiangxia);
            mZhibaojinV.setVisibility(View.GONE);
        } else {
            mZhibaojinLl.setVisibility(View.GONE);
            mZhibaojinIv.setImageResource(R.drawable.gengduo);
            mZhibaojinV.setVisibility(View.VISIBLE);
        }
    }

    private boolean projectStage = false;

    private void clickProjectStageEvent() {
        projectStage = !projectStage;
        if (projectStage) {
            mProjectStageLl.setVisibility(View.VISIBLE);
            mProjectStageIv.setImageResource(R.drawable.gengduoxiangxia);
            mV_projectStage.setVisibility(View.GONE);
        } else {
            mProjectStageLl.setVisibility(View.GONE);
            mProjectStageIv.setImageResource(R.drawable.gengduo);
            mV_projectStage.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void editContractMsg(EditMainContractMsg editMainContractMsg) {
        getContractInfoRequest();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_main_contract;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
