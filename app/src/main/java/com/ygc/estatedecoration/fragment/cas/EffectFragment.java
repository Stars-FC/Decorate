package com.ygc.estatedecoration.fragment.cas;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CaseEffectAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.EffectBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.event.CaseEvent;
import com.ygc.estatedecoration.user_activity.CaseDetailActivity;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EffectFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable, SwipeRefreshLayout.OnRefreshListener{

    private int allPagerNum = 0;//总页数
    private int curPagerNum = 1;//当前页数
    private String r_id = null;
    private String status = null;

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private CaseEffectAdapter mCaseEffectAdapter;

    public static EffectFragment newInstance() {
        return new EffectFragment();
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        EventBus.getDefault().register(this);
        showDialog();
        requestDataEvent(1, Constant.NORMAL_REQUEST);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mCaseEffectAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (allPagerNum > curPagerNum) {
                    requestDataEvent(curPagerNum, Constant.NORMAL_REQUEST);
                }
            }
        }, mRecyclerView);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_effect;
    }

    private void initRecyclerView() {
        mCaseEffectAdapter = new CaseEffectAdapter();
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRecyclerView.setAdapter(mCaseEffectAdapter);
        mCaseEffectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EffectBean.DataBean dataBean = mCaseEffectAdapter.getData().get(position);
                Intent intent = new Intent(mActivity, CaseDetailActivity.class);
                intent.putExtra("did", dataBean.getD_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        mCaseEffectAdapter.setEnableLoadMore(false);
        requestDataEvent(1, Constant.REFRESH_REQUEST);
    }

    private void requestDataEvent(int pageNum, final String requestMark) {
        APPApi.getInstance().service
                .queryCaseEffectData(pageNum, r_id, status, UserUtils.getUserId(), String.valueOf(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EffectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull EffectBean effectBean) {
                        if (effectBean.responseState.equals("1")) {
                            List<EffectBean.DataBean> dataList = effectBean.getData();
                            if (dataList != null) {
                                if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                    mCaseEffectAdapter.setNewData(dataList);
                                } else {
                                    mCaseEffectAdapter.addData(dataList);
                                }
                                if (requestMark.equals(Constant.REFRESH_REQUEST) && dataList.size()==0) {
                                    showToast("暂无数据");
                                }
                            }

                            EffectBean.PageBean page = effectBean.getPage();
                            curPagerNum = page.getPageNow();
                            allPagerNum = page.getTotalPageCount();
                            if (curPagerNum == allPagerNum) {
                                mCaseEffectAdapter.loadMoreEnd();
                            } else {
                                mCaseEffectAdapter.loadMoreComplete();
                            }
                        } else {
                            showToast(effectBean.msg);
                            mCaseEffectAdapter.loadMoreFail();
                        }
                        cancelDialog();
                        refreshFinish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showToast(getResources().getString(R.string.network_error));
                        cancelDialog();
                        refreshFinish();
                        mCaseEffectAdapter.loadMoreFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void refreshFinish() {
        mCaseEffectAdapter.setEnableLoadMore(true);
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void upDateCaseDataEvent(CaseEvent caseEvent) {
        Log.i("521", "upDateCaseDataEvent: 更新了效果图数据");
        this.r_id = caseEvent.getCaseStyle();
        this.status = caseEvent.getCaseState();
        curPagerNum = 1;
        allPagerNum = 0;
        showDialog();
        requestDataEvent(1, Constant.REFRESH_REQUEST);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
