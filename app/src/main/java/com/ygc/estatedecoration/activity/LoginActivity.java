package com.ygc.estatedecoration.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.login.ForgetPwdActivity;
import com.ygc.estatedecoration.activity.login.RegisterActivity;
import com.ygc.estatedecoration.activity.login.WeiXinLoginActivity;
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
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
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn://登陆
//                intent.setClass(LoginActivity.this, HomeActivity.class);
                intent.setClass(LoginActivity.this, UserHomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_weixin://微信登陆
                intent.setClass(LoginActivity.this, WeiXinLoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
