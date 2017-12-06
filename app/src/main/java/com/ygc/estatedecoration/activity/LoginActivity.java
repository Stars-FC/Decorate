package com.ygc.estatedecoration.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.login.ForgetPwdActivity;
import com.ygc.estatedecoration.activity.login.ServiceRegisterActivity;
import com.ygc.estatedecoration.activity.login.UserRegisterActivity;
import com.ygc.estatedecoration.activity.login.ServiceWeiXinLoginActivity;
import com.ygc.estatedecoration.activity.login.UserWeiXinLoginActivity;
import com.ygc.estatedecoration.user_activity.UserHomeActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 登陆界面
 */
public class LoginActivity extends AutoLayoutActivity {


    private Unbinder mUnBinder;

    @BindView(R.id.et_number)
    EditText mEtNumber;

    @BindView(R.id.et_pwd)
    EditText mEtPwd;

    @BindView(R.id.rgbutton_nemu)
    RadioGroup mRadioGroup;

    @BindView(R.id.rb_ordinaryuser)
    RadioButton mordinaryuser;
    @BindView(R.id.rb_serviceuser)
    RadioButton mServiceuser;

    @BindView(R.id.tv_register)
    TextView mTvRegister;   //注册textview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        setContentView(R.layout.activity_login);

        mUnBinder = ButterKnife.bind(this);

        initListener();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    private void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mordinaryuser.getId()) {
                    mTvRegister.setText("装修用户注册");
                } else if (i == mServiceuser.getId()) {
                    mTvRegister.setText("服务商注册");
                }
            }
        });
    }


    @OnClick({R.id.tv_pwd_forget, R.id.tv_register, R.id.login_btn, R.id.login_weixin})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_pwd_forget://忘记密码
                intent.setClass(LoginActivity.this, ForgetPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_register://注册
                registerUser();
                break;
            case R.id.login_btn://登陆
                loginEvent();
                break;
            case R.id.login_weixin://微信登陆
                bandWeiXin();
                break;
        }
    }

    /**
     * 用户注册判断
     */
    public void registerUser() {
        Intent intent = new Intent();
        if (mordinaryuser.isChecked()) {
            intent.setClass(LoginActivity.this, UserRegisterActivity.class);
            startActivity(intent);
        } else if (mServiceuser.isChecked()) {
            intent.setClass(LoginActivity.this, ServiceRegisterActivity.class);
            startActivity(intent);
        }
    }

    public void bandWeiXin(){
        Intent intent = new Intent();
        if (mordinaryuser.isChecked()) {
            intent.setClass(LoginActivity.this, UserWeiXinLoginActivity.class);
            startActivity(intent);
        } else if (mServiceuser.isChecked()) {
            intent.setClass(LoginActivity.this, ServiceWeiXinLoginActivity.class);
            startActivity(intent);
        }

    }

    public void loginEvent(){
        Intent intent = new Intent();
        if (mordinaryuser.isChecked()) {
            intent.setClass(LoginActivity.this, UserHomeActivity.class);
            startActivity(intent);
        } else if (mServiceuser.isChecked()) {
            intent.setClass(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        finish();
    }
}