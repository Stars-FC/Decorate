package com.ygc.estatedecoration.activity.login;

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

/**
 * 服务商注册界面
 */
public class ServiceRegisterActivity extends BaseActivity {


    @BindView(R.id.login_forgetpwd)
    LinearLayout mLoginForgetpwd;

    @BindView(R.id.tv_service_provider)
    TextView mServiceProvider; //显示服务商类型

    private BasePopupWindow mCheckPopupWindow;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("服务商注册");
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
        return R.layout.login_service_register;
    }

    @OnClick({R.id.naviButtonLeft, R.id.tv_service_provider, R.id.tv_get_verification, R.id.bt_register_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviButtonLeft://后退
                finish();
                break;
            case R.id.tv_service_provider://选择服务商类型
                showSelectPicPopupWindow();
                break;
            case R.id.tv_get_verification://获取验证码
                break;
            case R.id.bt_register_login://注册完成
                break;
        }
    }

    /**
     * 选择服务商类型popuwindows
     */
    private void showSelectPicPopupWindow() {
        if (mCheckPopupWindow == null) {
            mCheckPopupWindow = new BasePopupWindow(ServiceRegisterActivity.this);
            mCheckPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupView = LayoutInflater.from(ServiceRegisterActivity.this).inflate(R.layout.popuwindows_login_register, null);
            popupView.findViewById(R.id.tv_designer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mServiceProvider.setText("设计师");
                    mCheckPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.tv_construction).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mServiceProvider.setText("施工服务");
                    mCheckPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.tv_supervisor).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mServiceProvider.setText("监理");
                    mCheckPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.tv_material).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mServiceProvider.setText("材料商");
                    mCheckPopupWindow.dismiss();
                }
            });
            mCheckPopupWindow.setContentView(popupView);
            mCheckPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int top = popupView.findViewById(R.id.ll_popuwindows_register).getTop();
                    int bottm = popupView.findViewById(R.id.ll_popuwindows_register).getBottom();
                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < top || y > bottm) {
                            mCheckPopupWindow.dismiss();
                        }
                    }
                    return true;
                }
            });
        }
        mCheckPopupWindow.showAtLocation(mLoginForgetpwd, Gravity.CENTER, 0, 0);
    }
}
