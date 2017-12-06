package com.ygc.estatedecoration.app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.widget.TitleBar;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AutoLayoutActivity {

    private Unbinder mUnBinder;

    //root
    protected ViewGroup mRootView;
    // main-ui
    protected RelativeLayout mContentRootView;
    // main-navigation bar
    protected TitleBar mTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));
        basicInitialize();
        mUnBinder = ButterKnife.bind(this);
        initData(savedInstanceState);
        initView();
        addListener();
    }

    private void basicInitialize() {
        basicFindViews();
        basicSetting();
    }

    private void basicSetting() {
        buildTitle();
        addContent();
    }

    private void addContent() {
        final int layoutId = getLayoutId();
        if (layoutId <= 0) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(layoutId, mContentRootView, true);
    }

    private void buildTitle() {
        final TitleBar bar = mTitleBar;
        if (bar == null)
            return;
        bar.setActivity(this);
        if (!buildTitle(mTitleBar))
            mTitleBar.setVisibility(View.GONE);
    }

    protected abstract boolean buildTitle(TitleBar bar);

    private void basicFindViews() {
        mRootView = (ViewGroup) findViewById(R.id.rootview_baseactivity);// 最外层
        mContentRootView = (RelativeLayout) findViewById(R.id.content);// 内容页
        mTitleBar = (TitleBar) findViewById(R.id.titleBar);// TitleBar
    }

    protected abstract void addListener();

    protected abstract void initView();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    public void showToast(String showInfo) {
        Toast.makeText(this, showInfo, Toast.LENGTH_SHORT).show();
    }

    public boolean judgeNetworkIsConnect() {
        return NetWorkUtil.isNetWorkConnect(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }
}
