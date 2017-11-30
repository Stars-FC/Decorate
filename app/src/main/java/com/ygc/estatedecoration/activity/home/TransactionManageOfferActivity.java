package com.ygc.estatedecoration.activity.home;

import android.graphics.Color;
import android.os.Bundle;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

/**
 * Created by FC on 2017/11/20.
 * 首页-交易管理-商品条目的报价
 */

public class TransactionManageOfferActivity extends BaseActivity{
    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("报价");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
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
        return R.layout.offer_price_layout;
    }
}
