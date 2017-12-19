package com.ygc.estatedecoration.user_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.UserAddressDataListBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.event.RefreshAddressInfoMsg;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EditAddressActivity extends BaseActivity {

    public static final String OPERATE_ADDRESS = "operate_address";
    @BindView(R.id.name_et)
    EditText mNameEt;
    @BindView(R.id.line_phone_number_et)
    EditText mLinePhoneNumberEt;
    @BindView(R.id.province_city_et)
    EditText mProvinceCityEt;
    @BindView(R.id.details_address_et)
    EditText mDetailsAddressEt;
    private SweetAlertDialog mPDialog;
    private int mMark;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setRightText("保存");
        bar.setRightTextColor(Color.parseColor("#4EBE65"));
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
        initDialog();
        getIntentData();
    }

    private void initDialog() {
        mPDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("请求中...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mMark = intent.getIntExtra(OPERATE_ADDRESS, 0);
        if (mMark == 1) {
            mTitleBar.setTitleText("添加新地址");
        } else if (mMark == 2) {
            mTitleBar.setTitleText("编辑地址");
            UserAddressDataListBean.DataBean dataBean = (UserAddressDataListBean.DataBean) intent.getSerializableExtra("dataBean");
            mNameEt.setText(dataBean.getUserName());
            mLinePhoneNumberEt.setText(dataBean.getUserMobile());
            mProvinceCityEt.setText(dataBean.getProvince());
            mDetailsAddressEt.setText(dataBean.getDetail());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_address;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    hideSoftInput();
                    finish();
                    break;
                case R.id.naviFrameRight:
                    hideSoftInput();
                    saveAddressInfo();
                    break;
            }
        }
    }

    private void saveAddressInfo() {
        String nameStr = mNameEt.getText().toString().trim();
        if (TextUtils.isEmpty(nameStr)) {
            showToast("请输入收货人姓名");
            return;
        }
        String linePhoneNumberStr = mLinePhoneNumberEt.getText().toString().trim();
        if (TextUtils.isEmpty(linePhoneNumberStr)) {
            showToast("请输入收货人电话");
            return;
        }
        String provinceCityStr = mProvinceCityEt.getText().toString().trim();
        if (TextUtils.isEmpty(provinceCityStr)) {
            showToast("请输入省市");
            return;
        }
        String detailsAddressStr = mDetailsAddressEt.getText().toString().trim();
        if (TextUtils.isEmpty(detailsAddressStr)) {
            showToast("请输入详细地址");
            return;
        }
        if (mMark == 1) {
            mPDialog.show();
            APPApi.getInstance().service
                    .addUserAddress("auId", nameStr, linePhoneNumberStr, provinceCityStr, detailsAddressStr)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Base>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(@NonNull Base base) {
                            mPDialog.dismiss();
                            showToast(base.msg);
                            if (base.responseState.equals("1")) {
                                EventBus.getDefault().post(new RefreshAddressInfoMsg());
                                finish();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mPDialog.dismiss();
                            showToast(getResources().getString(R.string.network_error));
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {

        }
    }

    private void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
