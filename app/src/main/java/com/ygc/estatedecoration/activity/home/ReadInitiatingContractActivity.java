package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.OnClick;

/**
 * 查看合同
 */

public class ReadInitiatingContractActivity extends BaseActivity {

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("查看合同");
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
        return R.layout.activity_read_initiating_contract;
    }

    @OnClick({R.id.naviFrameLeft, R.id.add_contract})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.add_contract://补充合同
                Intent intent = new Intent(ReadInitiatingContractActivity.this, SupplementaryContractActivity.class);
                startActivity(intent);
                break;
        }
    }
}
