package com.ygc.estatedecoration.fragment.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeTransactionManageOfferAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.DemandOfferBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.event.OfferFinishMsg;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/20.
 * 首页-交易管理-ViewPager第二界面报价页面
 */

public class TransactionManageOfferFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, LazyFragmentPagerAdapter.Laziable {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeTransactionManageOfferAdapter mAdapter;
    private String mDId;
    private int curPageNum = 0;
//    private SweetAlertDialog mPDialog;


    public static TransactionManageOfferFragment getInstance(String dId) {
        TransactionManageOfferFragment transactionManageOfferFragment = new TransactionManageOfferFragment();
        Bundle bundle = new Bundle();
        bundle.putString("dId", dId);
        transactionManageOfferFragment.setArguments(bundle);
        return transactionManageOfferFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mDId = arguments.getString("dId");
        }
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        EventBus.getDefault().register(this);
        mAdapter = new HomeTransactionManageOfferAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        /*mPDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
        mPDialog.show();*/
        showDialog();
        getDemandOfferList(0, Constant.NORMAL_REQUEST);
    }

    private void getDemandOfferList(int page, final String requestMark) {
        APPApi.getInstance().service
                .getDemandOfferList(mDId, String.valueOf(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DemandOfferBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull DemandOfferBean base) {
                        cancelDialog();
                        if (base.responseState.equals("1")) {
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                mAdapter.setNewData(base.getData());
                                refreshFinishEvent(true);
                            } else {
                                mAdapter.addData(base.getData());
                                if (base.getData().size() > 0) {
                                    mAdapter.loadMoreComplete();
                                } else {
                                    mAdapter.loadMoreEnd();
                                }
                            }
                        } else {
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                refreshFinishEvent(false);
                            } else {
                                loadMoreDefeatEvent();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
                        loadMoreDefeatEvent();
                        refreshFinishEvent(false);
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*private void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
    }*/

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                curPageNum += 1;
                getDemandOfferList(curPageNum, Constant.NORMAL_REQUEST);
            }
        }, mRecyclerView);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.home_need_already_offer;
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        getDemandOfferList(0, Constant.REFRESH_REQUEST);
    }

    private void loadMoreDefeatEvent() {
        mAdapter.loadMoreFail();
        curPageNum -= 1;
        if (curPageNum < 0) {
            curPageNum = 0;
        }
    }

    private void refreshFinishEvent(boolean isSuccess) {
        mAdapter.setEnableLoadMore(true);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            if (isSuccess) {
                curPageNum = 0;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void offerFinishEvent(OfferFinishMsg offerFinishMsg) {
        getDemandOfferList(0, Constant.REFRESH_REQUEST);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
