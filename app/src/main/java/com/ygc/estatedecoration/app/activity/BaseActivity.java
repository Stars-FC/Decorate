package com.ygc.estatedecoration.app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.MyApplication;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.widget.TitleBar;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity extends AutoLayoutActivity {

    public CompositeDisposable compositeDisposable;

    private Unbinder mUnBinder;

    //root
    protected ViewGroup mRootView;
    // main-ui
    protected RelativeLayout mContentRootView;
    // main-navigation bar
    protected TitleBar mTitleBar;

    private MyApplication application;
    private BaseActivity mContext;

    private SweetAlertDialog mPDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        compositeDisposable = new CompositeDisposable();
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).statusBarColor(R.color.white).init();
        basicInitialize();
        mUnBinder = ButterKnife.bind(this);
        initDialog();
        initData(savedInstanceState);
        initView();
        addListener();

        if (application == null) {
            // 得到Application对象
            application = (MyApplication) getApplication();
        }
        mContext = this;// 把当前的上下文对象赋值给BaseActivity
        addActivity();// 调用添加方法
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

    private void initDialog() {
        mPDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("请求中...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
    }

    public void showDialog() {
        if (mPDialog != null && !mPDialog.isShowing()) {
            mPDialog.show();
        }
    }

    public void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
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
        ImmersionBar.with(this).destroy();
        compositeDisposable.dispose();
    }

    // 添加Activity方法
    public void addActivity() {
        application.addActivity(mContext);// 调用myApplication的添加Activity方法
    }

    //销毁当个Activity方法
    public void removeActivity() {
        application.removeActivity(mContext);// 调用myApplication的销毁单个Activity方法
    }

    //销毁所有Activity方法
    public void removeALLActivity() {
        application.removeALLActivity();// 调用myApplication的销毁所有Activity方法
    }
}
