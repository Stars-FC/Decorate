package com.ygc.estatedecoration.activity.my;

import android.graphics.Color;
import android.os.Bundle;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

/**
 * Created by FC on 2017/11/15.
 * 我的-我的钱包页面
 */

public class MoneyBagActivity extends BaseActivity {
    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("钱包");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.mipmap.ic_launcher);
        bar.setRightText("明细");
        bar.setRightTextColor(Color.BLACK);
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
        return R.layout.my_moneybag;
    }
}
