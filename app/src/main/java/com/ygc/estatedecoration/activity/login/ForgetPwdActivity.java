package com.ygc.estatedecoration.activity.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.LoginActivity;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.LoginBean;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.ValidationUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 登陆-重置密码界面
 */
public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.forgetpwd_et_photo)
    EditText mForgetpwdEtPhoto;//手机号
    @BindView(R.id.forgetpwd_et_newpwd)
    EditText mForgetpwdEtNewpwd;//新密码
    @BindView(R.id.forgetpwd_et_again_newpwd)
    EditText mForgetpwdEtAgainNewpwd;//确认新密码
    @BindView(R.id.et_verification)
    EditText mEtVerification;//输入验证码
    @BindView(R.id.tv_get_verification)
    Button mTvGetVerification;//获取验证码按钮

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("重置密码");
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
        return R.layout.login_forgetpwd;
    }

    @OnClick({R.id.naviFrameLeft, R.id.tv_get_verification, R.id.Reset_pwd_btn,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft://后退按钮
                finish();
                break;
            case R.id.tv_get_verification://获取验证码
                getNetVerification();
                break;
            case R.id.Reset_pwd_btn://重置密码
                getNetUpdatePwd();
                break;
        }
    }

    /**
     * 获取验证码
     */
    public void getNetVerification() {

        if (!NetWorkUtil.isNetWorkConnect(this)) {
            showToast("请检查网络设置");
            return;//判断网络状态，没有网络直接返回不想下执行
        }

        String photoNum = MyPublic.getText(mForgetpwdEtPhoto);
        if (TextUtils.isEmpty(photoNum)) {
            showToast("请填写手机号");
            return;
        }

        if (!ValidationUtil.isPhone(photoNum)) {
            showToast("手机号格式错误");
            return;
        }

        MyPublic.getVerification(ForgetPwdActivity.this, photoNum, mTvGetVerification, "1");
    }

    /**
     * 重置密码
     */
    public void getNetUpdatePwd() {
        if (!NetWorkUtil.isNetWorkConnect(this)) {
            showToast("请检查网络设置");
            return;
        }

        String UserPhoto = MyPublic.getText(mForgetpwdEtPhoto);
        if (TextUtils.isEmpty(UserPhoto)) {
            showToast("请填写手机号");
            return;
        }

        String psw = MyPublic.getText(mForgetpwdEtNewpwd);
        if (TextUtils.isEmpty(psw)) {
            showToast("请输入密码");
            return;
        }
        String againPsw = MyPublic.getText(mForgetpwdEtAgainNewpwd);
        if (TextUtils.isEmpty(againPsw) || !(againPsw.equals(psw))) {
            showToast("请确认两次密码相同");
            return;
        }

        String doSendCode = MyPublic.getText(mEtVerification);
        if (TextUtils.isEmpty(doSendCode)) {
            showToast("请输入验证码");
            return;
        }

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading");
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setCancelable(false);
        pDialog.show();

        APPApi.getInstance().service
                .updatePwd(UserPhoto, psw, doSendCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        String msg = loginBean.getMsg();
                        if ("修改成功".equals(msg)) {
                            pDialog.cancel();
                            showToast(msg);
                            removeALLActivity();//设置界面跳转后当前页面，防止在登录界面按后退键到设置界面
                            Intent intent = new Intent(ForgetPwdActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            pDialog.cancel();
                            showToast(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialog.cancel();
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        showToast("网络繁忙，请稍后再试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
