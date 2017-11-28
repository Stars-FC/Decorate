package com.ygc.estatedecoration.user_activity;

import android.os.Bundle;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.OnClick;

public class CaseDetailActivity extends BaseActivity {

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.mipmap.ic_launcher);
        bar.setTitleText("案例标题");
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
        initTitleBar();
    }

    private void initTitleBar() {
        mTitleBar.setRightImageResource(R.mipmap.ic_launcher);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_detail;
    }

    @OnClick({R.id.naviFrameLeft})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
            }
        }
    }
}
