package com.ygc.estatedecoration.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/15.
 * 我的-我的钱包页面
 */

public class MoneyBagActivity extends BaseActivity{

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("钱包");
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

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight, R.id.top_up_btn, R.id.ti_xian_btn})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.naviFrameRight://明细
                    Intent intent = new Intent(this, AccountDetailActivity.class);
                    startActivity(intent);
                    break;
                case R.id.top_up_btn:
                    topUpAndTiXianEvent(TopUpAndTiXianActivity.TOP_UP);
                    break;
                case R.id.ti_xian_btn:
                    topUpAndTiXianEvent(TopUpAndTiXianActivity.TI_XIAN);
                    break;
            }
        }
    }

    private void topUpAndTiXianEvent(String mark) {
        Intent intent = new Intent(this, TopUpAndTiXianActivity.class);
        intent.putExtra("mark", mark);
        startActivity(intent);
    }
}
