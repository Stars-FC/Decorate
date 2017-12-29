package com.ygc.estatedecoration.user_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserScheduleAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.DemandOfferBean;
import com.ygc.estatedecoration.bean.UserProjectProgressBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserProjectProgressActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private SweetAlertDialog mPDialog;
    private UserScheduleAdapter mUserScheduleAdapter;
    private DemandOfferBean.DataBean mDataBean;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("进度");
        return true;
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initView() {
        iniRecyclerView();
    }

    private void iniRecyclerView() {
        mUserScheduleAdapter = new UserScheduleAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mUserScheduleAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initDialog();
        getIntentData();
        getUserProjectProgress(Constant.NORMAL_REQUEST);
    }

    private void getIntentData() {
        mDataBean = (DemandOfferBean.DataBean) getIntent().getSerializableExtra("dataBean");
    }

    private void initDialog() {
        mPDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
    }

    private void getUserProjectProgress(final String requestMark) {
        APPApi.getInstance().service
                .getDemandPlan(mDataBean.getCId(), mDataBean.getDId(), String.valueOf(mDataBean.getCreator().getType()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserProjectProgressBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull UserProjectProgressBean userProjectProgressBean) {
                        if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                            refreshFinishEvent();
                        }
                        if (userProjectProgressBean.responseState.equals("1")) {
                            mUserScheduleAdapter.setNewData(userProjectProgressBean.getData());
                        } else {
                            showToast(userProjectProgressBean.msg);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        refreshFinishEvent();
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void refreshFinishEvent() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_project_progress;
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

    @Override
    public void onRefresh() {
        getUserProjectProgress(Constant.REFRESH_REQUEST);
    }
}
