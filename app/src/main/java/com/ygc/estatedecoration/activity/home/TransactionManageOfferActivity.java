package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.event.DeleteRecommendDemandMsg;
import com.ygc.estatedecoration.event.OfferFinishMsg;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/20.
 * 首页-交易管理-商品条目的报价
 */

public class TransactionManageOfferActivity extends BaseActivity {

    @BindView(R.id.content_layout)
    LinearLayout content_layout;
    @BindView(R.id.price_et)
    EditText mEt_price;
    @BindView(R.id.time_et)
    EditText mEt_time;
    @BindView(R.id.message_et)
    EditText mEt_message;
    @BindView(R.id.toopen_cb)
    CheckBox mCb_toOpen;

    private String isToOpen = "0";
    private String mDIdStr;
    private int mPosition;
    private String mMark;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("报价");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {
        mCb_toOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isToOpen = "1";
                }else
                    isToOpen = "0";
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mDIdStr = intent.getStringExtra("dId");
        mPosition = intent.getIntExtra("position", -1);
        mMark = intent.getStringExtra("mark");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.offer_price_layout;
    }

    @OnClick({R.id.naviFrameLeft, R.id.submit_tv})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.submit_tv:
                    if (paramsIsRight()) {
                        showSubmitPopupWindow();
                    }
                    break;
            }
        }
    }

    private boolean paramsIsRight() {
        String messageStr = mEt_message.getText().toString().trim();
        if (TextUtils.isEmpty(messageStr)) {
            showToast("请您输入留言内容！");
            return false;
        }
        return true;
    }

    private void submitOffer() {
        final SweetAlertDialog mPDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("提交中，请稍后...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
        mPDialog.show();
        String priceStr = mEt_price.getText().toString().trim();
        if (TextUtils.isEmpty(priceStr)) {
            priceStr = "-1";
        }
        String timeStr = mEt_time.getText().toString().trim();
        if (TextUtils.isEmpty(timeStr)) {
            timeStr = "-1";
        }
        String messageStr = mEt_message.getText().toString();
        Log.i("521", "submitOffer: userId>" + UserUtils.getUserId());
        APPApi.getInstance().service
                .demandOffer(UserUtils.getUserId(), mDIdStr, priceStr, timeStr, messageStr, isToOpen)
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
                            if (!mMark.equals("需求大厅")) {
                                EventBus.getDefault().post(new DeleteRecommendDemandMsg(mPosition));
                                EventBus.getDefault().post(new OfferFinishMsg());
                            }
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
    }

    private BasePopupWindow submitPopupWindow;
    private void showSubmitPopupWindow() {
        if (submitPopupWindow == null) {
            initDelAddressPopupWindow();
        }
        submitPopupWindow.showAtLocation(content_layout, Gravity.CENTER, 0, 0);
    }

    private void initDelAddressPopupWindow() {
        submitPopupWindow = new BasePopupWindow(this);
        submitPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_del_address, null);
        TextView alertInfoTv = (TextView) popupView.findViewById(R.id.alert_info_tv);
        alertInfoTv.setText("提示：提交后不可修改！");
        final LinearLayout content_layout = (LinearLayout) popupView.findViewById(R.id.content_layout);
        popupView.findViewById(R.id.sure_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPopupWindow.dismiss();
                submitOffer();
            }
        });
        popupView.findViewById(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPopupWindow.dismiss();
            }
        });
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int top = content_layout.getTop();
                int bottom = content_layout.getBottom();
                int left = content_layout.getLeft();
                int right = content_layout.getRight();
                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < top || y > bottom || x < left || x > right) {
                        submitPopupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        submitPopupWindow.setContentView(popupView);
        submitPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
    }
}
