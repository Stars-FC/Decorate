package com.ygc.estatedecoration.activity.my;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.OnClick;

/**
 * Created by FC on 2017/12/12.
 * 发起活动
 */

public class LaunchActivitesActivity extends BaseActivity {
    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("发起活动");
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
        //设置弹出软键盘时上移布局，防止EditText被挡住
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.my_launch_activities;
    }

    @OnClick({R.id.naviFrameLeft, R.id.my_launch_activites_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.my_launch_activites_ok:
                showToast("确认发起");
                break;
        }
    }
}
