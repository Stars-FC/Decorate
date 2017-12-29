package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.ProjectStageBean;
import com.ygc.estatedecoration.utils.DateUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

public class AddProjectStageActivity extends BaseActivity {

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
    private ProjectStageBean.DataBean mDataBean;
    private String mMark = "1";

    @BindView(R.id.sure_submit)
    Button mBtn_sureSubmit;
    private int mPosition;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
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
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mMark = intent.getStringExtra("mark");
        if (mMark.equals("2")) {
            mPosition = intent.getIntExtra("position", -1);
            mDataBean = (ProjectStageBean.DataBean) intent.getSerializableExtra("dataBean");
            mSupplementaryContractName.setText(mDataBean.getProjectStageName());
            mSupplementaryContractContent.setText(mDataBean.getProjectContent());
            mSupplementaryContractPayMoney.setText(mDataBean.getPayMoneyJinE());
            mTv_startTime.setText(mDataBean.getStartTime());
            mTv_endTime.setText(mDataBean.getEndTime());
            mSupplementaryContractDay.setText(mDataBean.getNeedDays());
            mBtn_sureSubmit.setText("确定修改");
            mTitleBar.setTitleText("修改阶段");
        } else {
            mBtn_sureSubmit.setText("确定添加");
            mTitleBar.setTitleText("添加阶段");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_project_stage;
    }

    @OnClick({R.id.naviFrameLeft, R.id.sure_submit, R.id.start_time_rl, R.id.end_time_rl})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.sure_submit:
                    sureSubmitEvent();
                    break;
                case R.id.start_time_rl:
                    onYearMonthDayPicker(0);
                    break;
                case R.id.end_time_rl:
                    onYearMonthDayPicker(1);
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

        ProjectStageBean.DataBean dataBean = new ProjectStageBean.DataBean();
        dataBean.setProjectStageName(nameStr);
        dataBean.setProjectContent(contentStr);
        dataBean.setPayMoneyJinE(moneyStr);
        dataBean.setStartTime(startTimeStr);
        dataBean.setEndTime(endTimeStr);
        dataBean.setNeedDays(dayStr);
        dataBean.setMark(mMark);
        dataBean.setPosition(mPosition);
        EventBus.getDefault().post(dataBean);
        finish();
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
