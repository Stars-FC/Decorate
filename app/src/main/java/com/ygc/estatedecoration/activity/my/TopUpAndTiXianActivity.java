package com.ygc.estatedecoration.activity.my;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class TopUpAndTiXianActivity extends BaseActivity {

    public static final String TOP_UP = "TOP_UP";
    public static final String TI_XIAN = "TI_XIAN";

    @BindView(R.id.content_layout)
    LinearLayout mLl_contentLayout;
    @BindView(R.id.top_ti_tv)
    TextView mTv_topTv;
    @BindView(R.id.top_ti_btn)
    TextView mBtn_topTi;
    private String mMark;

    @Override
    protected boolean buildTitle(TitleBar bar) {
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

        getIntentData();
    }

    private void getIntentData() {
        mMark = getIntent().getStringExtra("mark");
        switch (mMark) {
            case TI_XIAN:
                mTitleBar.setTitleText("提现");
                mTv_topTv.setText("提现金额");
                mBtn_topTi.setText("确认体现");
                break;
            case TOP_UP:
                mTitleBar.setTitleText("充值");
                mBtn_topTi.setText("确认充值");
                mTv_topTv.setText("充值金额");
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ti_xian_top_up;
    }

    @OnClick({R.id.naviFrameLeft, R.id.top_ti_btn})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.top_ti_btn:
                    topAndTiEvent();
                    break;
            }
        }
    }

    private void topAndTiEvent() {
        switch (mMark) {
            case TI_XIAN:
                break;
            case TOP_UP:
                showTopUpWindow();
                break;
        }
    }

    private BasePopupWindow mTopUpPopupWindow;
    private void showTopUpWindow() {
        if (mTopUpPopupWindow == null) {
            mTopUpPopupWindow = new BasePopupWindow(this);
            final View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_top_up, null);
            mTopUpPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            mTopUpPopupWindow.setContentView(popupView);
            mTopUpPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
            popupView.findViewById(R.id.wechat_pay_iv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTopUpPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.alipay_iv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTopUpPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.my_package_pay_iv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTopUpPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTopUpPopupWindow.dismiss();
                }
            });
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int top = popupView.findViewById(R.id.content_rl).getTop();
                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (top > y) {
                            mTopUpPopupWindow.dismiss();
                        }
                    }
                    return true;
                }
            });
        }
        mTopUpPopupWindow.showAtLocation(mLl_contentLayout, Gravity.CENTER, 0, 0);
    }
}
