package com.ygc.estatedecoration.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.ScheduleBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发起验收界面
 * Created by lsq on 2017/11/29.
 */

public class InitiatingAcceptanceActivity extends Activity {
    private ImageView back;
    private Button btn;
    private TextView title;
    private ScheduleBean bean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiating_acceptance);
        bean=new ScheduleBean();
        bean= (ScheduleBean) getIntent().getSerializableExtra("bean");
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title= (TextView) findViewById(R.id.title);
        title.setText("发起验收");
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
}
