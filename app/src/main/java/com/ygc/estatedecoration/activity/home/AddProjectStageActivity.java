package com.ygc.estatedecoration.activity.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.ProjectStageBean;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProjectStageActivity extends BaseActivity {

    @BindView(R.id.supplementary_contract_name)
    EditText mSupplementaryContractName;
    @BindView(R.id.supplementary_contract_content)
    EditText mSupplementaryContractContent;
    @BindView(R.id.supplementary_contract_pay_money)
    EditText mSupplementaryContractPayMoney;
    @BindView(R.id.supplementary_contract_day)
    EditText mSupplementaryContractDay;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("添加阶段");
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

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_project_stage;
    }

    @OnClick({R.id.naviFrameLeft, R.id.sure_submit})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
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
        String dayStr = mSupplementaryContractDay.getText().toString().trim();
        if (TextUtils.isEmpty(dayStr)) {
            showToast("请输入所需工时");
            return;
        }

        ProjectStageBean.DataBean dataBean = new ProjectStageBean.DataBean();
        dataBean.setProjectStageName(nameStr);
        dataBean.setProjectContent(contentStr);
        dataBean.setPayMoneyJinE(moneyStr);
        dataBean.setNeedDays(dayStr);
        EventBus.getDefault().post(dataBean);
        finish();
    }
}
