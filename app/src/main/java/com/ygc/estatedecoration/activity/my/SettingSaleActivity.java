package com.ygc.estatedecoration.activity.my;

import android.graphics.Color;
import android.os.Bundle;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

/**
 * Created by FC on 2017/11/15.
 * 我的-设置-账户安全页面
 */

public class SettingSaleActivity extends BaseActivity {
    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("账户安全");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.mipmap.ic_launcher);
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
        return R.layout.my_setting_safe;
    }
}
