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
import com.ygc.estatedecoration.utils.DateUtil;
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
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;
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
    @BindView(R.id.project_start_time_et)
    TextView mProjectStartTimeEt;
    @BindView(R.id.project_end_time_et)
    TextView mProjectEndTimeEt;
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
    EditText mProjectJineEt;
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

    @OnClick({R.id.naviFrameLeft, R.id.gongqi_rl, R.id.project_start_time_rl, R.id.project_end_time_rl,
            R.id.zhibaojin_rl, R.id.project_stage_rl, R.id.add_project_stage_tv, R.id.activity_initiating_contract_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.gongqi_rl:
                clickGongQiEvent();
                break;
            case R.id.project_start_time_rl:
                onYearMonthDayPicker(0);
                break;
            case R.id.project_end_time_rl:
                onYearMonthDayPicker(1);
                break;
            case R.id.zhibaojin_rl:
                clickZhiBaoJinEvent();
                break;
            case R.id.project_stage_rl:
                clickProjectStageEvent();
                break;
            case R.id.add_project_stage_tv:
                Intent intent = new Intent(this, AddProjectStageActivity.class);
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

    public void onYearMonthDayPicker(final int mark) {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setTopLineColor(0x994EBE65);
        picker.setTextColor(0xFF4EBE65);
        picker.setCancelTextColor(0xFF4EBE65);
        picker.setSubmitTextColor(0xFF4EBE65);
        picker.setTitleTextColor(0xFF4EBE65);
        picker.setDividerColor(0xFF4EBE65);
        picker.setRangeEnd(2111, 1, 11);
        picker.setRangeStart(2016, 8, 29);
        String curYearMonthDayStr = DateUtil.getNowString();
        String substring = curYearMonthDayStr.substring(0, 10);
        String[] split = substring.split("-");
        picker.setSelectedItem(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (mark == 0) {
                    mProjectStartTimeEt.setText(year + "-" + month + "-" + day);
                } else if (mark == 1) {
                    mProjectEndTimeEt.setText(year + "-" + month + "-" + day);
                }
                String projectStartTimeStr = mProjectStartTimeEt.getText().toString().trim();
                String projectEndTimeStr = mProjectEndTimeEt.getText().toString().trim();

                if (!TextUtils.isEmpty(projectStartTimeStr) && !TextUtils.isEmpty(projectEndTimeStr)) {
                    String startTimeStr = projectStartTimeStr + " 00:00:00";
                    long startTimeLog = DateUtil.string2Millis(startTimeStr);
                    String endTimeStr = projectEndTimeStr + " 00:00:00";
                    long endTimeLog = DateUtil.string2Millis(endTimeStr);
                    if (startTimeLog > endTimeLog) {
                        showToast("项目结束时间不能小于开始时间");
                        return;
                    }
                    int allTimeInt = (int) ((endTimeLog - startTimeLog) / 1000 / 60 / 60 / 24);
                    mProjectAllTimeEt.setText(allTimeInt + "天");
                }
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
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
        String projectStartTimeStr = mProjectStartTimeEt.getText().toString().trim();
        if (TextUtils.isEmpty(projectStartTimeStr)) {
            showToast("请选择项目开始时间");
            return;
        }
        String projectEndTimeStr = mProjectEndTimeEt.getText().toString().trim();
        if (TextUtils.isEmpty(projectEndTimeStr)) {
            showToast("请选择项目结束时间");
            return;
        }
        String startTimeStr = projectStartTimeStr + " 00:00:00";
        long startTimeLog = DateUtil.string2Millis(startTimeStr);
        String endTimeStr = projectEndTimeStr + " 00:00:00";
        long endTimeLog = DateUtil.string2Millis(endTimeStr);
        if (startTimeLog > endTimeLog) {
            showToast("项目结束时间不能小于开始时间");
            return;
        }
        int allTimeInt = (int) ((endTimeLog - startTimeLog) / 1000 / 60 / 60 / 24);
        mProjectAllTimeEt.setText(allTimeInt + "天");

        String zhiBaoJinStr = mZhibaojinEt.getText().toString().trim();
        if (TextUtils.isEmpty(zhiBaoJinStr)) {
            showToast("请输入质保金额");
            return;
        }

        String projectJinEStr = mProjectJineEt.getText().toString().trim();
        if (TextUtils.isEmpty(projectJinEStr)) {
            showToast("请输入项目金额");
            return;
        }

        if (mDataBeanList.size() == 0) {
            showToast("请添加项目阶段");
            return;
        }

        double projectAllJinE = 0.00;
        int needTime = 0;//总周期
        String title = "";
        String detail = "";
        String price = "";
        String needDays = "";
        for (int i = 0; i < mDataBeanList.size(); i++) {
            ProjectStageBean.DataBean dataBean = mDataBeanList.get(i);
            projectAllJinE += Double.valueOf(dataBean.getPayMoneyJinE());
            needTime += Integer.valueOf(dataBean.getNeedDays());
            if (i == 0) {
                title += dataBean.getProjectStageName() + ",";
                detail += dataBean.getProjectContent() + ",";
                price += dataBean.getPayMoneyJinE() + ",";
                needDays += dataBean.getNeedDays() + ",";
            } else {
                title += dataBean.getProjectStageName();
                detail += dataBean.getProjectContent();
                price += dataBean.getPayMoneyJinE();
                needDays += dataBean.getNeedDays();
            }
        }

        String allTimeStr = mProjectAllTimeEt.getText().toString();
        if (!allTimeStr.equals(String.valueOf(needTime))) {
            showToast("项目工期选择错误");
            return;
        }
        java.text.DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String projectAllJinEStr = decimalFormat.format(projectAllJinE);//总金额
        String projectJinED = decimalFormat.format(Double.valueOf(projectJinEStr));
        if (!projectJinED.equals(projectAllJinEStr)) {
            showToast("项目金额输入错误");
            return;
        }

        String contractContentStr = mTv_contractContent.getText().toString().trim();
        String userId = UserUtils.getUserId();
        int au_id = mDataBean.getCreator().getAu_id();
        int dId = mDataBean.getDId();
        String startTime = String.valueOf(startTimeLog / 1000);
        String endTime = String.valueOf(endTimeLog / 1000);
        String zhiBaoJinD = decimalFormat.format(Double.valueOf(zhiBaoJinStr));

        sureSponsorContractEvent(contractContentStr, userId, String.valueOf(au_id), String.valueOf(dId), projectAllJinEStr, projectStartTimeStr, projectEndTimeStr, allTimeStr, zhiBaoJinD, title, detail, price, needDays, String.valueOf(mType));
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
                            finish();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
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
        mDataBeanList.add(dataBean);
        mProjectStageAdapter.setNewData(mDataBeanList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
