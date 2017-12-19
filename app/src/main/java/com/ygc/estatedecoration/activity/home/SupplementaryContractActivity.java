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
 * 补充合同界面
 * Created by lsq on 2017/11/29.
 */

public class SupplementaryContractActivity extends BaseActivity {


    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("发起补充合同");
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
        return R.layout.activity_supplementary_contract;
    }

    @OnClick({R.id.naviFrameLeft, R.id.supplementary_contract_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.supplementary_contract_submit://发起补充合同
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss ");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String time = formatter.format(curDate);
                Intent intent = new Intent();
                intent.putExtra("time", time);
                setResult(0, intent);
                finish();
                break;
        }
    }
}
