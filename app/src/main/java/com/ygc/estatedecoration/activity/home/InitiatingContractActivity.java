package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.ProjectStageAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.ContractContentBean;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.bean.ProjectStageBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.event.FaQiContractFinishMsg;
import com.ygc.estatedecoration.event.UpdateServiceProgressMsg;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.MaxRecyclerView;
import com.ygc.estatedecoration.widget.TitleBar;
import com.zhy.autolayout.AutoLinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InitiatingContractActivity extends BaseActivity {

    @BindView(R.id.contract_content_tv)
    TextView mTv_contractContent;
    @BindView(R.id.gongqi_iv)
    ImageView mGongqiIv;
    /*@BindView(R.id.project_start_time_et)
    TextView mProjectStartTimeEt;
    @BindView(R.id.project_end_time_et)
    TextView mProjectEndTimeEt;*/
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
    private ProjectStageAdapter mProjectStageAdapter;
    private List<ProjectStageBean.DataBean> mDataBeanList = new ArrayList<>();
    private SweetAlertDialog mPDialog;
    private NeedBean.DataBean mDataBean;
    private int mType;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("发起合同");
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        initProjectStageRecyclerView();
    }

    private void initProjectStageRecyclerView() {
        mProjectStageAdapter = new ProjectStageAdapter();
        mMaxRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mMaxRecyclerView.setNestedScrollingEnabled(false);
        mMaxRecyclerView.setAdapter(mProjectStageAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mType = UserUtils.sDataBean.getType();
        initDialog();
        getContractContentRequest();
        getIntentData();
        EventBus.getDefault().register(this);
    }

    private void initDialog() {
        mPDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
        mPDialog.show();
    }

    private void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
    }

    private void getContractContentRequest() {
        APPApi.getInstance().service
                .getContractContentData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContractContentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull ContractContentBean contractContentBean) {
                        cancelDialog();
                        switch (mType) {
                            case 1:
                                mTv_contractContent.setText(contractContentBean.getData().getDesign_contract_model());
                                break;
                            case 2:
                                mTv_contractContent.setText(contractContentBean.getData().getConstruction_contract_model());
                                break;
                            case 3:
                                mTv_contractContent.setText(contractContentBean.getData().getSupervision_contract_model());
                                break;
                            case 4:
                                mTv_contractContent.setText(contractContentBean.getData().getMaterial_contract_model());
                                break;
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

    private void getIntentData() {
        mDataBean = (NeedBean.DataBean) getIntent().getSerializableExtra("dataBean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_initiating_contract;
    }

    @OnClick({R.id.naviFrameLeft, R.id.gongqi_rl, R.id.zhibaojin_rl, R.id.project_stage_rl, R.id.add_project_stage_tv,
            R.id.activity_initiating_contract_submit})
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
            case R.id.add_project_stage_tv:
                Intent intent = new Intent(this, AddProjectStageActivity.class);
                intent.putExtra("mark", "1");
                startActivity(intent);
                break;
            case R.id.activity_initiating_contract_submit:
                faQiContractEvent();
                break;
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

    private void faQiContractEvent() {

        String zhiBaoJinStr = mZhibaojinEt.getText().toString().trim();
        if (TextUtils.isEmpty(zhiBaoJinStr)) {
            showToast("请输入质保金额");
            return;
        }

        if (mDataBeanList.size() == 0) {
            showToast("请添加项目阶段");
            return;
        }

        String title = "";
        String detail = "";
        String price = "";
        String startTime = "";
        String endTime = "";
        String needDays = "";
        for (int i = 0; i < mDataBeanList.size(); i++) {
            ProjectStageBean.DataBean dataBean = mDataBeanList.get(i);
            if (i == 0) {
                title += dataBean.getProjectStageName();
                detail += dataBean.getProjectContent();
                price += dataBean.getPayMoneyJinE();
                startTime += dataBean.getStartTime();
                endTime += dataBean.getEndTime();
                needDays += dataBean.getNeedDays();
            } else {
                title += "," + dataBean.getProjectStageName();
                detail += "," + dataBean.getProjectContent();
                price += "," + dataBean.getPayMoneyJinE();
                startTime += "," + dataBean.getStartTime();
                endTime += "," + dataBean.getEndTime();
                needDays += "," + dataBean.getNeedDays();
            }
        }

        java.text.DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String allTimeStr = mProjectAllTimeEt.getText().toString().trim();
        String projectAllJinEStr = mProjectJineEt.getText().toString().trim();

        String contractContentStr = mTv_contractContent.getText().toString().trim();
        String userId = UserUtils.getUserId();
        int au_id = mDataBean.getCreator().getAu_id();
        int dId = mDataBean.getDId();
        String zhiBaoJinD = decimalFormat.format(Double.valueOf(zhiBaoJinStr));

        sureSponsorContractEvent(contractContentStr, userId, String.valueOf(au_id), String.valueOf(dId), projectAllJinEStr, startTime, endTime, allTimeStr, zhiBaoJinD, title, detail, price, needDays, String.valueOf(mType));
    }

    private void sureSponsorContractEvent(String contractContentStr, String userId, String s, String s1, String projectAllJinEStr, String startTime, String endTime, String allTimeStr, String zhiBaoJinD, String title, String detail, String price, String needDays, String s2) {
        mPDialog.setTitleText("请求中...");
        mPDialog.show();
        APPApi.getInstance().service
                .faQiContract(contractContentStr, userId, s, s1, projectAllJinEStr, startTime, endTime, allTimeStr,
                        zhiBaoJinD, title, detail, price, needDays, s2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Base base) {
                        cancelDialog();
                        showToast(base.msg);
                        if (base.responseState.equals("1")) {
                            EventBus.getDefault().post(new FaQiContractFinishMsg());
                            EventBus.getDefault().post(new UpdateServiceProgressMsg());
                            finish();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveStageMsg(ProjectStageBean.DataBean dataBean) {
        if (dataBean.getMark().equals("2")) {
            mDataBeanList.remove(dataBean.getPosition());
            mDataBeanList.add(dataBean.getPosition(), dataBean);
        } else {
            mDataBeanList.add(dataBean);
        }
        java.text.DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double money = 0.00;
        int needTime = 0;
        for (ProjectStageBean.DataBean data : mDataBeanList) {
            String payMoneyJinE = data.getPayMoneyJinE();
            money += Double.valueOf(decimalFormat.format(Double.valueOf(payMoneyJinE)));
            needTime += Integer.valueOf(data.getNeedDays());
        }
        mProjectAllTimeEt.setText(String.valueOf(needTime));
        mProjectJineEt.setText(String.valueOf(money));
        mProjectStageAdapter.setNewData(mDataBeanList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
