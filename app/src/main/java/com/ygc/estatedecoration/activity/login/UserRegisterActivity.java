package com.ygc.estatedecoration.activity.login;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
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
    EditText mEtUserPhoto;
    @BindView(R.id.et_user_random_num)
    EditText mEtUserRandomNum;
    @BindView(R.id.et_new_psw)
    EditText mEtNewPsw;
    @BindView(R.id.et_again_new_psw)
    EditText mEtAgainNewPsw;
    @BindView(R.id.et_verification)
    EditText mEtVerification;
    @BindView(R.id.tv_get_verification)
    Button mTvGetVerification;

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
        return R.layout.login_user_register;
    }

    @OnClick({R.id.naviButtonLeft, R.id.tv_get_verification, R.id.bt_register_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviButtonLeft://后退
                finish();
                break;
            case R.id.tv_get_verification://获取验证码
                getVerification();
                break;
            case R.id.bt_register_login://注册完成
                getregister();
                break;
        }
    }


    /**
     * 用户注册
     */
    public void getregister() {
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
        // TODO: 2017/12/11  

    }


    public void getVerification() {

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
