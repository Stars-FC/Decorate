package com.ygc.estatedecoration.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.OnClick;

public class SettingAddBankCardActivity extends BaseActivity {


    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("添加银行卡");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.mipmap.ic_launcher);
        bar.setRightText("绑定");
        bar.setRightTextColor(Color.parseColor("#4EBE65"));
//        getResources().getColor(R.color.colorAccent);
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
        return R.layout.my_setting_add_bank_card;
    }


    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.naviFrameLeft://后退按钮
                finish();
                break;
            case R.id.naviFrameRight://绑定
                showToast("绑定银行卡成功");
                break;
        }
    }
}
