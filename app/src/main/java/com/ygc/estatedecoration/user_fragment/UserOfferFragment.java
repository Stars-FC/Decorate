package com.ygc.estatedecoration.user_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserOfferListAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.DemandOfferBean;
import com.ygc.estatedecoration.bean.NeedBean;
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

public class UserOfferFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, LazyFragmentPagerAdapter.Laziable {

    private NeedBean.DataBean mDataBean;

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private UserOfferListAdapter mUserOfferListAdapter;
    private SweetAlertDialog mPDialog;

    public static UserOfferFragment newInstance(NeedBean.DataBean dataBean) {
        UserOfferFragment fragment = new UserOfferFragment();
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
//        initDialog();
        showDialog();
        getOfferDataList(0, Constant.NORMAL_REQUEST);
    }

    private void getOfferDataList(int pageNum, final String requestMark) {
        APPApi.getInstance().service
                .getDemandOfferList(String.valueOf(mDataBean.getDId()), String.valueOf(pageNum))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DemandOfferBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull DemandOfferBean demandOfferBean) {
                        showToast(demandOfferBean.msg);
                        cancelDialog();
                        if (demandOfferBean.responseState.equals("1")) {
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                mUserOfferListAdapter.setNewData(demandOfferBean.getData());
                                refreshFinishEvent(true);
                            } else {
                                int size = demandOfferBean.getData().size();
                                if (size > 0) {
                                    mUserOfferListAdapter.addData(demandOfferBean.getData());
                                    mUserOfferListAdapter.loadMoreComplete();
                                } else {
                                    mUserOfferListAdapter.loadMoreEnd();
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
                        loadMoreErrorEvent();
                        refreshFinishEvent(false);
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*private void initDialog() {
        mPDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
        mPDialog.show();
    }
*/
    /*private void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
    }*/

    private int curPageNum = 0;

    private void loadMoreErrorEvent() {
        mUserOfferListAdapter.loadMoreFail();
        curPageNum -= 1;
        if (curPageNum < 0) {
            curPageNum = 0;
        }
    }

    private void refreshFinishEvent(boolean isSuccess) {
        mUserOfferListAdapter.setEnableLoadMore(true);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            if (isSuccess) {
                curPageNum = 0;
            }
        }
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mUserOfferListAdapter = new UserOfferListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mUserOfferListAdapter);
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
        return R.layout.fragment_user_offer;
    }

    @Override
    public void onRefresh() {
        mUserOfferListAdapter.setEnableLoadMore(false);
        getOfferDataList(0, Constant.REFRESH_REQUEST);
    }
}
