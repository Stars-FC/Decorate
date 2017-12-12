package com.ygc.estatedecoration.activity.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.LoginActivity;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.PhoneGetCodeUtil;
import com.ygc.estatedecoration.utils.ValidationUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 用户注册页面
 */

public class UserRegisterActivity extends BaseActivity {

    @BindView(R.id.et_user_photo)
    EditText mEtUserPhoto;//手机号
    @BindView(R.id.et_user_random_num)
    EditText mEtUserRandomNum;//昵称
    @BindView(R.id.et_new_psw)
    EditText mEtNewPsw;//密码
    @BindView(R.id.et_again_new_psw)
    EditText mEtAgainNewPsw;//确认密码
    @BindView(R.id.et_verification)
    EditText mEtVerification;//验证码
    @BindView(R.id.tv_get_verification)
    Button mTvGetVerification;//获取验证码

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("用户注册");
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
        //设置弹出软键盘时上移布局，防止EditText被挡住
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.login_user_register;
    }

    @OnClick({R.id.naviButtonLeft, R.id.tv_get_verification, R.id.bt_register_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviButtonLeft://后退
                finish();
                break;
            case R.id.tv_get_verification://获取验证码
                getNetVerification();
                break;
            case R.id.bt_register_login://注册完成
                getNetRegister();
                break;
        }
    }


    /**
     * 用户注册
     */
    public void getNetRegister() {
        if (!NetWorkUtil.isNetWorkConnect(this)) {
            showToast("请检查网络设置");
            return;
        }

        String UserPhoto = MyPublic.getText(mEtUserPhoto);
        if (TextUtils.isEmpty(UserPhoto)) {
            showToast("请填写手机号");
            return;
        }

        String userNum = MyPublic.getText(mEtUserRandomNum);
        if (TextUtils.isEmpty(userNum)) {
            showToast("请填昵称");
            return;
        }

        String psw = MyPublic.getText(mEtNewPsw);
        if (TextUtils.isEmpty(psw)) {
            showToast("请输入密码");
            return;
        }
        String againPsw = MyPublic.getText(mEtAgainNewPsw);
        if (TextUtils.isEmpty(againPsw) || !(againPsw.equals(psw))) {
            showToast("请确认两次密码相同");
            return;
        }

        String doSendCode = MyPublic.getText(mEtVerification);
        if (TextUtils.isEmpty(doSendCode)) {
            showToast("请输入验证码");
            return;
        }

        APPApi.getInstance().service
                .register(UserPhoto, 0, 0, userNum, psw, doSendCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        LogUtil.e("请求网路成功");

                        if (null == baseBean) return;

                        String msg = baseBean.getMsg();
                        String responseState = baseBean.getResponseState();
                        if ("添加成功".equals(msg)) {
                            showToast("注册成功");
                            Intent intent = new Intent(UserRegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            showToast(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        Toast.makeText(UserRegisterActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 获取验证码
     */
    public void getNetVerification() {

        if (!NetWorkUtil.isNetWorkConnect(this)) {
            showToast("请检查网络设置");
            return;//判断网络状态，没有网络直接返回不想下执行
        }

        String photoNum = MyPublic.getText(mEtUserPhoto);
        if (TextUtils.isEmpty(photoNum)) {
            showToast("请填写手机号");
            return;
        }

        if (!ValidationUtil.isPhone(photoNum)) {
            showToast("手机号格式错误");
            return;
        }

        MyPublic.getVerification(UserRegisterActivity.this, photoNum, mTvGetVerification);
    }
}
