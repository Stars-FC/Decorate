package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.ContractInfoBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.event.EditMainContractMsg;
import com.ygc.estatedecoration.utils.DateUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;

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

public class EditMainContractStageActivity extends BaseActivity {

    @BindView(R.id.supplementary_contract_name)
    EditText mSupplementaryContractName;
    @BindView(R.id.supplementary_contract_content)
    EditText mSupplementaryContractContent;
    @BindView(R.id.supplementary_contract_pay_money)
    EditText mSupplementaryContractPayMoney;
    @BindView(R.id.start_time_et)
    TextView mTv_startTime;
    @BindView(R.id.end_time_et)
    TextView mTv_endTime;
    @BindView(R.id.supplementary_contract_day)
    TextView mSupplementaryContractDay;
    private ContractInfoBean.DataBean.ContractStageListBean mContractStageListBean;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("编辑阶段");
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
        mSupplementaryContractName.setText(mContractStageListBean.getTitle());
        mSupplementaryContractContent.setText(mContractStageListBean.getDetail());
        mSupplementaryContractPayMoney.setText(mContractStageListBean.getPrice());
        mTv_startTime.setText(mContractStageListBean.getStageStartTime());
        mTv_endTime.setText(mContractStageListBean.getStageEndTime());
        mSupplementaryContractDay.setText(mContractStageListBean.getNeedDays());
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mContractStageListBean = (ContractInfoBean.DataBean.ContractStageListBean) intent.getSerializableExtra("dataBean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_main_contract_stage;
    }

    @OnClick({R.id.naviFrameLeft, R.id.start_time_rl, R.id.end_time_rl, R.id.sure_submit})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.start_time_rl:
                    onYearMonthDayPicker(0);
                    break;
                case R.id.end_time_rl:
                    onYearMonthDayPicker(1);
                    break;
                case R.id.sure_submit:
                    sureSubmitEvent();
                    break;
            }
        }
    }

    private void sureSubmitEvent() {
        String nameStr = mSupplementaryContractName.getText().toString().trim();
        if (TextUtils.isEmpty(nameStr)) {
            showToast("请输入阶段名称");
            return;
        }
        String contentStr = mSupplementaryContractContent.getText().toString().trim();
        if (TextUtils.isEmpty(contentStr)) {
            showToast("请输入项目内容");
            return;
        }
        String moneyStr = mSupplementaryContractPayMoney.getText().toString().trim();
        if (TextUtils.isEmpty(moneyStr)) {
            showToast("请输入付款金额");
            return;
        }

        java.text.DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String moneyD = decimalFormat.format(Double.valueOf(moneyStr));

        String startTimeStr = mTv_startTime.getText().toString().trim();
        if (TextUtils.isEmpty(startTimeStr)) {
            showToast("请选择开始日期");
            return;
        }
        String endTimeStr = mTv_endTime.getText().toString().trim();
        if (TextUtils.isEmpty(endTimeStr)) {
            showToast("请选择结束日期");
            return;
        }
        String dayStr = mSupplementaryContractDay.getText().toString().trim();

        requestNetwork(mContractStageListBean.getConsId(), nameStr, contentStr, moneyD, dayStr);
    }

    private void requestNetwork(String conId, String nameStr, String contentStr, String moneyD, String dayStr) {
        showDialog();
        APPApi.getInstance().service
                .modifyMainContractStage(conId, nameStr, contentStr, moneyD, dayStr)
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
                            EventBus.getDefault().post(new EditMainContractMsg());
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
                    mTv_startTime.setText(year + "-" + month + "-" + day);
                } else if (mark == 1) {
                    mTv_endTime.setText(year + "-" + month + "-" + day);
                }
                String projectStartTimeStr = mTv_startTime.getText().toString().trim();
                String projectEndTimeStr = mTv_endTime.getText().toString().trim();

                if (!TextUtils.isEmpty(projectStartTimeStr) && !TextUtils.isEmpty(projectEndTimeStr)) {
                    String startTimeStr = projectStartTimeStr + " 00:00:00";
                    long startTimeLog = DateUtil.string2Millis(startTimeStr);
                    String endTimeStr = projectEndTimeStr + " 00:00:00";
                    long endTimeLog = DateUtil.string2Millis(endTimeStr);
                    if (startTimeLog > endTimeLog) {
                        showToast("结束日期不能小于开始日期");
                        return;
                    }
                    int allTimeInt = (int) ((endTimeLog - startTimeLog) / 1000 / 60 / 60 / 24) + 1;
                    mSupplementaryContractDay.setText(String.valueOf(allTimeInt));
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
}
