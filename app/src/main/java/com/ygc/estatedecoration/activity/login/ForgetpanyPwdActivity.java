package com.ygc.estatedecoration.activity.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.OnClick;

/**
 * 登陆-重置支付密码界面
 */
public class ForgetpanyPwdActivity extends BaseActivity {


    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("重置支付密码");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.mipmap.ic_launcher);
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
        return R.layout.login_forget_pany_pwd;
    }

    @OnClick({R.id.naviFrameLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft://后退按钮
                finish();
                break;
        }
    }
}
