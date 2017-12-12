package com.ygc.estatedecoration.activity.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登陆-重置密码界面
 */
public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.forgetpwd_et_photo)
    EditText mForgetpwdEtPhoto;//手机号
    @BindView(R.id.forgetpwd_et_newpwd)
    EditText mForgetpwdEtNewpwd;//新密码
    @BindView(R.id.forgetpwd_et_again_newpwd)
    EditText mForgetpwdEtAgainNewpwd;//确认新密码
    @BindView(R.id.et_verification)
    EditText mEtVerification;//输入验证码
    @BindView(R.id.tv_get_verification)
    Button mTvGetVerification;//获取验证码按钮

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("重置密码");
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
        return R.layout.login_forgetpwd;
    }

    @OnClick({R.id.naviFrameLeft, R.id.tv_get_verification, R.id.Reset_pwd_btn,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft://后退按钮
                finish();
                break;
            case R.id.tv_get_verification://获取验证码
                break;
            case R.id.Reset_pwd_btn://重置密码
                break;
        }
    }
}
