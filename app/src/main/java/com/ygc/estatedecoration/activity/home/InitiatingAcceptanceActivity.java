package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.ScheduleBean;
import com.ygc.estatedecoration.widget.TitleBar;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.OnClick;

/**
 * 发起验收界面
 * Created by lsq on 2017/11/29.
 */

public class InitiatingAcceptanceActivity extends BaseActivity {

    private Button btn;
    private ScheduleBean bean;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("发起验收");
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
        bean=new ScheduleBean();
        bean= (ScheduleBean) getIntent().getSerializableExtra("bean");
        btn= (Button) findViewById(R.id.activity_initiating_acceptance_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setType("6");
                Intent intent=new Intent();
                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss ");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String time = formatter.format(curDate);
                intent.putExtra("time",time);
                intent.putExtra("bean",bean);
                InitiatingAcceptanceActivity.this.setResult(0,intent);
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_initiating_acceptance;
    }

    @OnClick({R.id.naviFrameLeft})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
            }
        }
    }
}
