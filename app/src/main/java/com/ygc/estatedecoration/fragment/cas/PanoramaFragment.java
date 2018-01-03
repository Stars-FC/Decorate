package com.ygc.estatedecoration.fragment.cas;

import android.content.Intent;
import android.graphics.Color;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CasePanoramaAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.PanoramaBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.event.CaseEvent;
import com.ygc.estatedecoration.user_activity.UserPanoramaActivity;
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

public class PanoramaFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private CasePanoramaAdapter mCasePanoramaAdapter;

    private int allPagerNum = 0;//总页数
    private int curPagerNum = 1;//当前页数
    private String r_id = null;
    private String status = null;

    public static PanoramaFragment newInstance() {
        PanoramaFragment fragment = new PanoramaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        mCasePanoramaAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
        return R.layout.fragment_panorama;
    }

    private void initRecyclerView() {
        mCasePanoramaAdapter = new CasePanoramaAdapter();
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mCasePanoramaAdapter);
        mCasePanoramaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PanoramaBean.DataBean dataBean = mCasePanoramaAdapter.getData().get(position);
                int cp_id = dataBean.getCp_id();
                Intent intent = new Intent(mActivity, UserPanoramaActivity.class);
                intent.putExtra("cp_id", cp_id);
                startActivity(intent);
            }
        });
    }

    private void requestDataEvent(int pageNum, final String requestMark) {
        APPApi.getInstance().service
                .queryCasePanoramaData(pageNum, r_id, status, UserUtils.getUserId(), String.valueOf(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PanoramaBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull PanoramaBean panoramaBean) {
                        if (panoramaBean.responseState.equals("1")) {
                            List<PanoramaBean.DataBean> dataList = panoramaBean.getData();
                            if (dataList != null) {
                                if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                    mCasePanoramaAdapter.setNewData(dataList);
                                } else {
                                    mCasePanoramaAdapter.addData(dataList);
                                }
                                if (requestMark.equals(Constant.REFRESH_REQUEST) && dataList.size()==0) {
                                    showToast("暂无数据");
                                }
                            }
                            PanoramaBean.PageBean page = panoramaBean.getPage();
                            curPagerNum = page.getPageNow();
                            allPagerNum = page.getTotalPageCount();
                            if (curPagerNum == allPagerNum) {
                                mCasePanoramaAdapter.loadMoreEnd();
                            } else {
                                mCasePanoramaAdapter.loadMoreComplete();
                            }
                        } else {
                            showToast(panoramaBean.msg);
                            mCasePanoramaAdapter.loadMoreFail();
                        }
                        cancelDialog();
                        refreshFinish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showToast(getResources().getString(R.string.network_error));
                        cancelDialog();
                        refreshFinish();
                        mCasePanoramaAdapter.loadMoreFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onRefresh() {
        mCasePanoramaAdapter.setEnableLoadMore(false);
        requestDataEvent(1, Constant.REFRESH_REQUEST);
    }

    private void refreshFinish() {
        mCasePanoramaAdapter.setEnableLoadMore(true);
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void upDateCaseDataEvent(CaseEvent caseEvent) {
        Log.i("521", "upDateCaseDataEvent: 更新了全景图数据");
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
