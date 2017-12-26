package com.ygc.estatedecoration.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.MyStoreActivity;
import com.ygc.estatedecoration.activity.home.MyVisitorActivity;
import com.ygc.estatedecoration.activity.home.NeedHallActivity;
import com.ygc.estatedecoration.activity.home.ServerMsgActivity;
import com.ygc.estatedecoration.activity.home.TransactionManageActivity;
import com.ygc.estatedecoration.activity.home.TransactionManageDetailActivity;
import com.ygc.estatedecoration.adapter.HomeAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.event.DeleteRecommendDemandMsg;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/13.
 * <p>
 * 首页
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, LazyFragmentPagerAdapter.Laziable {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeAdapter mHomeAdapter;

    private static final String ARG_C = "content";
    private View mView;
    private SweetAlertDialog mPDialog;

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("地标装饰");
        bar.setRightImageResource(R.drawable.xiaoxi_sel);
        return true;
    }

    @Override
    protected void initData(Bundle arguments) {
        EventBus.getDefault().register(this);
        mPDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
        mPDialog.show();
        getRecommendNeedData(0, Constant.NORMAL_REQUEST);
    }

    private void getRecommendNeedData(int pageNum, final String requestMark) {
        APPApi.getInstance().service
                .queryRecommendNeed("9", "0", "-1", pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeedBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull NeedBean needBean) {
                        if (needBean.responseState.equals("1")) {
                            int size = needBean.getData() == null ? 0 : needBean.getData().size();
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                mHomeAdapter.setNewData(needBean.getData());
                                refreshFinishEvent(true);
                            } else {
                                mHomeAdapter.addData(needBean.getData());
                                if (size > 0) {
                                    mHomeAdapter.loadMoreComplete();
                                } else {
                                    mHomeAdapter.loadMoreEnd();
                                }
                            }
                        } else {
                            if (requestMark.equals(Constant.NORMAL_REQUEST)) {
                                loadMoreErrorEvent();
                            } else {
                                refreshFinishEvent(false);
                            }
                            showToast(needBean.msg);
                        }
                        cancelDialog();
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

    private void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
    }

    private int curPageNum = 0;

    private void loadMoreErrorEvent() {
        mHomeAdapter.loadMoreFail();
        curPageNum -= 1;
        if (curPageNum < 0) {
            curPageNum = 0;
        }
    }

    private void refreshFinishEvent(boolean isSuccess) {
        mHomeAdapter.setEnableLoadMore(true);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            if (isSuccess) {
                curPageNum = 0;
            }
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mHomeAdapter = new HomeAdapter();

        mView = View.inflate(mActivity, R.layout.top_home, null);

        mHomeAdapter.addHeaderView(mView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, TransactionManageDetailActivity.class);
                NeedBean.DataBean dataBean = (NeedBean.DataBean) adapter.getItem(position);
                if (dataBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("demand", dataBean);
                    bundle.putInt("position", position);
                    bundle.putString("mark", "推荐需求");
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } else {
                    showToast("数据异常！");
                }
            }
        });
    }

    @Override
    protected void addListener() {
        mView.findViewById(R.id.my_store).setOnClickListener(this);
        mView.findViewById(R.id.need_Analysis).setOnClickListener(this);
        mView.findViewById(R.id.trade_manage).setOnClickListener(this);
        mView.findViewById(R.id.my_visitor).setOnClickListener(this);

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //上拉加载更多
        mHomeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                curPageNum += 1;
                getRecommendNeedData(curPageNum, Constant.NORMAL_REQUEST);
            }
        }, mRecyclerView);

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.my_store: //我的商铺
                intent.setClass(mActivity, MyStoreActivity.class);
                startActivity(intent);
                break;
            case R.id.need_Analysis://需求大厅
                intent.setClass(mActivity, NeedHallActivity.class);
                startActivity(intent);
                break;
            case R.id.trade_manage://交易管理
                intent.setClass(mActivity, TransactionManageActivity.class);
                startActivity(intent);
                break;
            case R.id.my_visitor://我的访客
                intent.setClass(mActivity, MyVisitorActivity.class);
                startActivity(intent);
                break;
        }
    }

    @OnClick({R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameRight:
                    Intent intent = new Intent(mActivity, ServerMsgActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        mHomeAdapter.setEnableLoadMore(false);
        getRecommendNeedData(0, Constant.REFRESH_REQUEST);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void deleteListData(DeleteRecommendDemandMsg deleteRecommendDemandMsg) {
        int position = deleteRecommendDemandMsg.getPosition();
        mHomeAdapter.remove(position);
    }
}
