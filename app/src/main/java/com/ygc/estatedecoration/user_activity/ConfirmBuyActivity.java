package com.ygc.estatedecoration.user_activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserConfirmBuyAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认购买页面
 */

public class ConfirmBuyActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private UserConfirmBuyAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("确认订单");
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
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("" + i);
        }
        mAdapter = new UserConfirmBuyAdapter(list);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(ConfirmBuyActivity.this));
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_confirm_buy;
    }

    @OnClick({R.id.naviFrameLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
        }
    }
}
