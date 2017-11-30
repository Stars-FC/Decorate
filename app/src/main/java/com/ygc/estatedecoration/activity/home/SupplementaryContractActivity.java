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

/**
 * 补充合同界面
 * Created by lsq on 2017/11/29.
 */

public class SupplementaryContractActivity extends Activity implements View.OnClickListener {
    private ImageView back;
    private TextView title;
    private Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplementary_contract);
        title= (TextView) findViewById(R.id.title);
        title.setText("发起补充合同");
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        submit= (Button) findViewById(R.id.supplementary_contract_submit);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==back){
            finish();
        }else if(v==submit){
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss ");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String time = formatter.format(curDate);
            Intent intent=new Intent();
            intent.putExtra("time",time);
            setResult(0,intent);
            finish();
        }
    }
}
