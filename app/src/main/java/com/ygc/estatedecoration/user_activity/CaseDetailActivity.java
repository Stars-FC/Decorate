package com.ygc.estatedecoration.user_activity;

import android.os.Bundle;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.RatingBar;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class CaseDetailActivity extends BaseActivity {

    @BindView(R.id.ratingbar)
    RatingBar mRatingBar;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("案例标题");
        bar.setRightImageResource(R.drawable.shoucang);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        mRatingBar.setStar(4.5f);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initTitleBar();
    }

    private void initTitleBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_detail;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.naviFrameRight:
                    break;
            }
        }
    }
}
