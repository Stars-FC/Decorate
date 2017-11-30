package com.ygc.estatedecoration.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.login.ForgetPwdActivity;
import com.ygc.estatedecoration.activity.login.ForgetpanyPwdActivity;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/15.
 * 我的-设置-账户安全页面
 */

public class SettingSaleActivity extends BaseActivity {

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("账户安全");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
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
        return R.layout.my_setting_safe;
    }

    @OnClick({R.id.naviFrameLeft, R.id.tv_bank_card, R.id.tv_login_password, R.id.tv_payment_password})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.tv_bank_card://银行卡管理
                intent.setClass(SettingSaleActivity.this, SettingBankCardActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_password://登陆密码
                intent.setClass(SettingSaleActivity.this, ForgetPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_payment_password://支付密码
                intent.setClass(SettingSaleActivity.this, ForgetpanyPwdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
