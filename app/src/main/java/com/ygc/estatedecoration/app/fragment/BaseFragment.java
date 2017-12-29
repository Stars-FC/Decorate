package com.ygc.estatedecoration.app.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment extends Fragment {
    public CompositeDisposable compositeDisposable;

    //root
    protected ViewGroup mViewGroup;
    // main-ui
    protected LinearLayout mContentRootView;
    // main-navigation bar
    protected TitleBar mTitleBar;

    /**
     * 贴附的activity
     */
    protected Activity mActivity;

    /**
     * 根view
     */
    protected View mRootView;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;

    /**
     * 是否加载完成
     * 当执行完onCreateView,View的初始化方法后即为true
     */
    protected boolean mIsPrepare;

    private SweetAlertDialog mPDialog;

    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_base, container, false);
        basicInitialize();

        mUnBinder = ButterKnife.bind(this, mRootView);

        initData(getArguments());

        initDialog();

        initView(savedInstanceState);

        mIsPrepare = true;

        onLazyLoad();

        addListener();

        return mRootView;
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
        final int layoutId = setLayoutResourceId();
        if (layoutId <= 0) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        inflater.inflate(layoutId, mContentRootView, true);
    }

    private void buildTitle() {
        final TitleBar bar = mTitleBar;
        if (bar == null)
            return;
        bar.setActivity(mActivity);
        if (!buildTitle(mTitleBar))
            mTitleBar.setVisibility(View.GONE);
    }

    protected abstract boolean buildTitle(TitleBar bar);

    private void basicFindViews() {
        mViewGroup = (ViewGroup) mRootView.findViewById(R.id.rootview_basefragment);// 最外层
        mContentRootView = (LinearLayout) mRootView.findViewById(R.id.content_fragment);// 内容页
        mTitleBar = (TitleBar) mRootView.findViewById(R.id.titleBar);// TitleBar
    }

    /**
     * 初始化数据，接收到的从其他地方传递过来的参数
     */
    protected abstract void initData(Bundle arguments);

    /**
     * 初始化View
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 设置监听事件
     */
    protected abstract void addListener();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser) {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {
            onLazyLoad();
        }
    }

    /**
     * 懒加载，仅当用户可见且view初始化结束后才会执行
     */
    protected abstract void onLazyLoad();

    /**
     * 设置根布局资源id
     */
    protected abstract int setLayoutResourceId();

    public void showToast(String showInfo) {
        Toast.makeText(mActivity, showInfo, Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断是否联网
     *
     * @return
     */
    public boolean judgeNetworkIsConnect() {
        return NetWorkUtil.isNetWorkConnect(mActivity);
    }

    private void initDialog() {
        mPDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder!=null) {
            mUnBinder.unbind();
        }
        compositeDisposable.dispose();
    }
}
