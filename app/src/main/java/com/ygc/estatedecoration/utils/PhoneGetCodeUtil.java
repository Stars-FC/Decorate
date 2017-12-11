package com.ygc.estatedecoration.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by FC on 2017/12/11.
 * 为获取验证码设置显示对应文字的工具类
 */

public class PhoneGetCodeUtil {

    private Button mTextView;
    private Context mContext;
    private TimeCount mTimeCount;

    public PhoneGetCodeUtil(Button textView, Context context) {
        mTextView = textView;
        mContext = context;
        mTimeCount = new TimeCount(60000, 1000);
    }

    public void onStart() {
        this.mTimeCount.start();
    }

    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            mTextView.setClickable(false);
            mTextView.setText(l / 1000 + "s以后重试");
        }

        @Override
        public void onFinish() {
            mTextView.setClickable(true);
            mTextView.setText("获取验证码");
        }
    }
}
