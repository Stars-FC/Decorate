package com.ygc.estatedecoration.activity.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.OnClick;

public class AddMyBrightActivity extends BaseActivity {

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("添加亮点");
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.add_my_bright_activity;
    }

    @OnClick({R.id.naviFrameLeft, R.id.my_add_bright_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.my_add_bright_ok:
                showToast("确认添加");
                break;
        }
    }
}
