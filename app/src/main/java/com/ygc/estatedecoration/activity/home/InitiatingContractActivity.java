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

import java.text.SimpleDateFormat;
import java.util.Date;

public class InitiatingContractActivity extends Activity {
    private ImageView back;
    private Button btn;
    private TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiating_contract);
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title= (TextView) findViewById(R.id.title);
        title.setText("发起合同");
        btn= (Button) findViewById(R.id.activity_initiating_contract_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss ");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String time = formatter.format(curDate);
                Intent intent=new Intent();
                intent.putExtra("time",time);
                setResult(0,intent);
                finish();
            }
        });
    }
}
