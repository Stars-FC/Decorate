package com.ygc.estatedecoration.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.my.SettingActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by FC on 2017/11/13.
 * 我的界面
 */

public class MyFragment extends BaseFragment {

    private static final String ARG_C = "content";
    Unbinder unbinder;

    public static MyFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setRightImageResource(R.mipmap.ic_launcher);
        return true;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_my;
    }

    @OnClick({R.id.my_attestation, R.id.my_trade, R.id.my_moneybag, R.id.my_collection, R.id.my_bright,R.id.imageView_right_titlebar})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.my_attestation://实名认证
                showToast("实名认证");
                break;
            case R.id.my_trade://交易管理
                showToast("交易管理");
                break;
            case R.id.my_moneybag://我的钱包
                showToast("我的钱包");
                break;
            case R.id.my_collection://我的收藏
                showToast("我的收藏");
                break;
            case R.id.my_bright://我的亮点
                showToast("我的亮点");
                break;
            case R.id.imageView_right_titlebar://设置
                intent.setClass(mActivity,SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
