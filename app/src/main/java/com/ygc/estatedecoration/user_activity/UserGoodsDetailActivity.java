package com.ygc.estatedecoration.user_activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.OnClick;

/**
 * Created by FC on 2017/11/28.
 * 商品详情页面
 */

public class UserGoodsDetailActivity extends BaseActivity {
    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        //设置状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_goods_detail;
    }

    @OnClick({R.id.iv_back, R.id.tv_offer})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
                case R.id.tv_offer://立即购买
                    break;
            }
        }
    }
}
