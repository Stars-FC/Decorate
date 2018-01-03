package com.ygc.estatedecoration.user_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.AddressAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.UserAddressDataListBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.event.RefreshAddressInfoMsg;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserAddressActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.content_layout)
    LinearLayout content_layout;
    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private AddressAdapter mAddressAdapter;
    private int curPageNum = 0;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("地址管理");
        return true;
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAddressAdapter = new AddressAdapter(content_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAddressAdapter);
        mAddressAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        showDialog();
        getMyAddressDataList(0, Constant.NORMAL_REQUEST);
    }

    private void getMyAddressDataList(int curPage, final String requestMark) {
        APPApi.getInstance().service
                .myAddressDataList(UserUtils.getUserId(), String.valueOf(curPage))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserAddressDataListBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull UserAddressDataListBean userAddressDataListBean) {
                        cancelDialog();
                        showToast(userAddressDataListBean.msg);
                        if (userAddressDataListBean.responseState.equals("1")) {
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                mAddressAdapter.setNewData(userAddressDataListBean.getData());
                                refreshFinishEvent(true);
                            } else {
                                mAddressAdapter.addData(userAddressDataListBean.getData());
                                if (userAddressDataListBean.getData().size() > 0) {
                                    mAddressAdapter.loadMoreComplete();
                                } else {
                                    mAddressAdapter.loadMoreEnd();
                                }
                            }
                        } else {
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                refreshFinishEvent(false);
                            } else {
                                loadMoreErrorEvent();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
                        showToast(getResources().getString(R.string.network_error));
                        refreshFinishEvent(false);
                        loadMoreErrorEvent();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_address;
    }

    @OnClick({R.id.naviFrameLeft, R.id.add_address_tv})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.add_address_tv:
                    addAddressEvent();
                    break;
            }
        }
    }

    private void addAddressEvent() {
        Intent intent = new Intent(this, EditAddressActivity.class);
        intent.putExtra(EditAddressActivity.OPERATE_ADDRESS, 1);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mAddressAdapter.setEnableLoadMore(false);
        getMyAddressDataList(0, Constant.REFRESH_REQUEST);
    }

    @Override
    public void onLoadMoreRequested() {
        curPageNum += 1;
        getMyAddressDataList(curPageNum, Constant.NORMAL_REQUEST);
    }

    private void loadMoreErrorEvent() {
        mAddressAdapter.loadMoreFail();
        curPageNum -= 1;
        if (curPageNum < 0) {
            curPageNum = 0;
        }
    }

    private void refreshFinishEvent(boolean isSuccess) {
        mAddressAdapter.setEnableLoadMore(true);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            if (isSuccess) {
                curPageNum = 0;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshAddressInfoEvent(RefreshAddressInfoMsg refreshAddressInfoMsg) {
        mAddressAdapter.setEnableLoadMore(false);
        getMyAddressDataList(0, Constant.REFRESH_REQUEST);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
