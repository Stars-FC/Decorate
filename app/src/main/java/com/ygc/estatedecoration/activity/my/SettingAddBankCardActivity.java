package com.ygc.estatedecoration.activity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.LoginActivity;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.BankCardBean;
import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.utils.ValidationUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SettingAddBankCardActivity extends BaseActivity {


    @BindView(R.id.add_bank_ev_bank_num)
    EditText mAddBankEvBankNum;//一银行卡号
    @BindView(R.id.add_bank_ev_bank_that)
    EditText mAddBankEvBankThat;//开户行
    @BindView(R.id.add_bank_ev_name)
    EditText mAddBankEvName;//真实姓名
    @BindView(R.id.add_bank_ev_phonenum)
    EditText mAddBankEvPhonenum;//预留手机号

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("添加银行卡");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setRightText("绑定");
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

    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_setting_add_bank_card;
    }


    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.naviFrameLeft://后退按钮
                finish();
                break;
            case R.id.naviFrameRight://绑定
                addBankCard();
                break;
        }
    }

    /**
     * 绑定银行卡
     */
    private void addBankCard() {
        if (!NetWorkUtil.isNetWorkConnect(this)) {
            showToast("请检查网络设置");
            return;
        }
        String bankNum = MyPublic.getText(mAddBankEvBankNum);
        String bankThat = MyPublic.getText(mAddBankEvBankThat);
        String name = MyPublic.getText(mAddBankEvName);
        String phonenum = MyPublic.getText(mAddBankEvPhonenum);

        List<String> list = new ArrayList<>(Arrays.asList(bankNum, bankThat, name, phonenum));
        if (MyPublic.isEmpty(list)) {
            showToast("请填写完整");
            return;
        }

        if (!MyPublic.checkBankCard(bankNum)) {
            showToast("银行卡格式错误");
            return;
        }

        if (!ValidationUtil.isPhone(phonenum)) {
            showToast("手机号格式错误");
            return;
        }

        APPApi.getInstance().service
                .addBankCard(bankNum, bankThat, name, phonenum, UserUtils.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean bean) {
                        String msg = bean.getMsg();
                        showToast(msg);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("Fc_添加银行卡" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
