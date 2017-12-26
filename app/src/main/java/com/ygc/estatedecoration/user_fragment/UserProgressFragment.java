package com.ygc.estatedecoration.user_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserScheduleAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.bean.UserProjectProgressBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserProgressFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable, SwipeRefreshLayout.OnRefreshListener{

    private NeedBean.DataBean mDataBean;

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private SweetAlertDialog mPDialog;
    private UserScheduleAdapter mUserScheduleAdapter;

    public static UserProgressFragment newInstance(NeedBean.DataBean dataBean) {
        UserProgressFragment fragment = new UserProgressFragment();
        Bundle args = new Bundle();
        args.putSerializable("dataBean", dataBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDataBean = (NeedBean.DataBean) getArguments().getSerializable("dataBean");
        }
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        initDialog();
        getUserProjectProgress(Constant.NORMAL_REQUEST);
    }

    private void initDialog() {
        mPDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
    }

    private void getUserProjectProgress(final String requestMark) {
        APPApi.getInstance().service
                .getDemandPlan("cId", String.valueOf(mDataBean.getDId()), String.valueOf(mDataBean.getCreator().getType()))
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
    protected void initView(Bundle savedInstanceState) {
        iniRecyclerView();
    }

    private void iniRecyclerView() {
        mUserScheduleAdapter = new UserScheduleAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mUserScheduleAdapter);
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_user_progress;
    }

    @Override
    public void onRefresh() {
        getUserProjectProgress(Constant.REFRESH_REQUEST);
    }
}
