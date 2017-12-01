package com.ygc.estatedecoration.user_activity;

import android.os.Bundle;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.OnClick;

/**
 * Created by FC on 2017/11/28.
 * 商品详情页面
 */

public class UserGoodsDetailActivity extends BaseActivity{
    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
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
        return R.layout.user_goods_detail;
    }

    @OnClick({R.id.iv_back})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
            }
        }
    }
}
