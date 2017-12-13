package com.ygc.estatedecoration.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.LoginActivity;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/14.
 * 我的-设置页面
 */

public class SettingActivity extends BaseActivity {

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("设置");
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
        return R.layout.my_setting;
    }

    @OnClick({R.id.naviButtonLeft, R.id.tv_accountsafe, R.id.tv_cleancache, R.id.tv_aboutwe, R.id.tv_opinionfeedback, R.id.bt_logout})
    public void onViewClicked(View view) {

        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.naviButtonLeft://后退按钮
                finish();
                break;
            case R.id.tv_accountsafe://账号安全
                intent.setClass(SettingActivity.this, SettingSaleActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_cleancache://清除缓存
                showToast("清除缓存成功");
                break;
            case R.id.tv_aboutwe://关于我们
                intent.setClass(SettingActivity.this, AboutOurActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_opinionfeedback://意见反馈
                intent.setClass(SettingActivity.this, BackOpinionActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_logout://退出当前账号
                UserUtils.putOnLineBoolean(SettingActivity.this, "", false);//标记用户退出登录
                intent.setClass(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
                removeALLActivity();//销毁所有activity
                break;
        }
    }
}
