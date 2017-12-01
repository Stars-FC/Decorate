package com.ygc.estatedecoration.activity.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.OnClick;

/**
 * 用户登陆-微信登陆的绑定手机界面
 */
public class UserWeiXinLoginActivity extends BaseActivity {


    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("用户绑定手机");
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
        return R.layout.login_user_weixin;
    }

    @OnClick({R.id.naviFrameLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft://后退
                finish();
                break;
        }
    }

}
