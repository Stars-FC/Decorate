package com.ygc.estatedecoration.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.login.ForgetPwdActivity;
import com.ygc.estatedecoration.activity.login.ServiceRegisterActivity;
import com.ygc.estatedecoration.activity.login.ServiceWeiXinLoginActivity;
import com.ygc.estatedecoration.activity.login.UserRegisterActivity;
import com.ygc.estatedecoration.activity.login.UserWeiXinLoginActivity;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.bean.LoginBean;
import com.ygc.estatedecoration.user_activity.UserHomeActivity;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.utils.ValidationUtil;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 登陆界面
 */
public class LoginActivity extends AutoLayoutActivity {


    private Unbinder mUnBinder;

    @BindView(R.id.et_number)
    EditText mEtNumber;//账号

    @BindView(R.id.et_pwd)
    EditText mEtPwd;//密码

    @BindView(R.id.rgbutton_nemu)
    RadioGroup mRadioGroup;

    @BindView(R.id.rb_ordinaryuser)
    RadioButton mordinaryuser;
    @BindView(R.id.rb_serviceuser)
    RadioButton mServiceuser;

    @BindView(R.id.tv_register)
    TextView mTvRegister;   //注册textview

    private int identity = 0; //表示身份（0：用户，1：服务商）

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    public CompositeDisposable compositeDisposable;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

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
        compositeDisposable = new CompositeDisposable();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        compositeDisposable.dispose();
    }

    private void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                /*if (i == mordinaryuser.getId()) {
                    mTvRegister.setText("装修用户注册");
                } else if (i == mServiceuser.getId()) {
                    mTvRegister.setText("服务商注册");
                }*/
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

    // TODO: 2017/12/14 微信登录未实现

    /**
     * 微信登录绑定
     */
    public void bandWeiXin() {
        Intent intent = new Intent();
        if (mordinaryuser.isChecked()) {
            intent.setClass(LoginActivity.this, UserWeiXinLoginActivity.class);
            startActivity(intent);
        } else if (mServiceuser.isChecked()) {
            intent.setClass(LoginActivity.this, ServiceWeiXinLoginActivity.class);
            startActivity(intent);
        }

    }

    /**
     * 登陆
     */
    public void loginEvent() {
        /*Intent intent = new Intent();
        if (mordinaryuser.isChecked()) {
            intent.setClass(LoginActivity.this, UserHomeActivity.class);
            startActivity(intent);
            finish();
        } else if (mServiceuser.isChecked()) {
            intent.setClass(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }*/
        if (!NetWorkUtil.isNetWorkConnect(this)) {
            Toast.makeText(LoginActivity.this, "请检查网络设置", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mordinaryuser.isChecked()) {
            identity = 0;
        } else if (mServiceuser.isChecked()) {
            identity = 1;
        }

        String num = MyPublic.getText(mEtNumber);
        String pwd = MyPublic.getText(mEtPwd);
        if (TextUtils.isEmpty(num) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(LoginActivity.this, "请填写用户名和密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!ValidationUtil.isPhone(num)) {
            Toast.makeText(LoginActivity.this, "手机号格式错误", Toast.LENGTH_SHORT).show();
            return;
        }

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading");
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setCancelable(false);
        pDialog.show();
//        pDialog.getProgressHelper().setBarWidth(10);//转圈圆环宽度
//        pDialog.getProgressHelper().setRimWidth(2);//--中间半圆环空隙
//        pDialog.getProgressHelper().setInstantProgress(10f);
//        pDialog.getProgressHelper().setSpinSpeed(10);//转的速度
//        pDialog.getProgressHelper().setProgress(2);//转了一圈
//        pDialog.getProgressHelper().setCircleRadius(10);

        APPApi.getInstance().service
                .login(num, pwd, identity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginBean roleFindAllBean) {
                        String msg = roleFindAllBean.getMsg();
                        if ("登录成功".equals(msg)) {
                            String password = roleFindAllBean.getData().getPassword();//密码
                            String userName = roleFindAllBean.getData().getUsername();//手机号
                            String userId = String.valueOf(roleFindAllBean.getData().getAu_id());//用户id
                            int type = roleFindAllBean.getData().getType();//用户、服务端
                            int r_id = roleFindAllBean.getData().getR_id();//材料商
                            pDialog.cancel();
                            //保存用户名、密码
                            UserUtils.setParam(UserUtils.USER, UserUtils.userId, userId);
                            UserUtils.setParam(UserUtils.USER, UserUtils.userPws, password);
                            UserUtils.setParam(UserUtils.USER, UserUtils.userName, userName);
                            UserUtils.putOnLineBoolean(LoginActivity.this, "", true);//标记用户退出登录

                            Intent intent = new Intent();
                            if (type == 0) {
                                //用户端登陆
                                UserUtils.sDataBean = roleFindAllBean.getData();
                                intent.setClass(LoginActivity.this, UserHomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {      //1、2、3、4
                                //服务商端登陆
                                UserUtils.sDataBean = roleFindAllBean.getData();
                                intent.setClass(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            pDialog.cancel();
                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialog.cancel();
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*@Override
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    *//**
     * 对退出时间的监听
     *//*
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }*/
}
