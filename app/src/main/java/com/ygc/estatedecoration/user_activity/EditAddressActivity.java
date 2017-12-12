package com.ygc.estatedecoration.user_activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.event.RefreshAddressInfoMsg;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

public class EditAddressActivity extends BaseActivity {

    public static final String OPERATE_ADDRESS = "operate_address";

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setRightText("保存");
        bar.setRightTextColor(Color.parseColor("#4EBE65"));
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
        int mark = getIntent().getIntExtra(OPERATE_ADDRESS, 0);
        if (mark == 1) {
            mTitleBar.setTitleText("添加新地址");
        } else if (mark == 2) {
            mTitleBar.setTitleText("编辑地址");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_address;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    hideSoftInput();
                    finish();
                    break;
                case R.id.naviFrameRight:
                    EventBus.getDefault().post(new RefreshAddressInfoMsg());
                    hideSoftInput();
                    finish();
                    break;
            }
        }
    }

    private void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
