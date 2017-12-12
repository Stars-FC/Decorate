package com.ygc.estatedecoration.activity.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

public class WarrantyMoneyActivity extends BaseActivity {

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("质保金");
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
        return R.layout.recyclerview_line;
    }
}
