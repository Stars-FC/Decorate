package com.ygc.estatedecoration.user_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserFindSupervisorDetailAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.ContractContentBean;
import com.ygc.estatedecoration.bean.FindAllTypeBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.widget.TitleBar;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserFindSupervisorActivity extends BaseActivity {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private UserFindSupervisorDetailAdapter mAdapter;
    private int mMark;
    private int mPager = 1;
    private BGABanner mBGABanner;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("找监理");
        mTitleBar.setRightImageResource(R.drawable.shouyesou);
        mTitleBar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {
        //每个条目的点击事件
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("mRecyclerview第" + position + "数据");
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色

        //下拉加载
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mPager = 1;
                findAllType(mPager, Constant.REFRESH_REQUEST);
                //顶部轮播图
                getBannerData();
            }
        });

        //上拉加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ++mPager;
                findAllType(mPager, Constant.NORMAL_REQUEST);
            }
        }, mRecyclerview);
    }

    @Override
    protected void initView() {

        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new UserFindSupervisorDetailAdapter(R.layout.item_user_home_fragment_find_supervisor_more, mMark);
        View headerView = View.inflate(this, R.layout.header_find_supervisor, null);
        mBGABanner = headerView.findViewById(R.id.banner);
        mAdapter.addHeaderView(headerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mMark = getIntent().getIntExtra("mark", 0);
        if (mMark == 1) {
            mTitleBar.setTitleText("找施工");
        } else {
            mTitleBar.setTitleText("找监理");
        }
        mSwipeRefreshLayout.setRefreshing(true);
        getBannerData();
        findAllType(mPager, Constant.REFRESH_REQUEST);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_find_supervisor;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.naviFrameRight:
                    Intent intent = new Intent(this, UserSearchActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    /**
     * 获取2-施工/3-监理信息
     *
     * @param pager 当前页数
     * @param value 区分下拉，上拉
     */
    private void findAllType(int pager, final String value) {

        if (!NetWorkUtil.isNetWorkConnect(UserFindSupervisorActivity.this)) {
            showToast("请检查网络设置");
            return;
        }

        RequestBody requestBody = null;

        if (mMark == 1) {//找施工
            requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("type", "2")
                    .addFormDataPart("pn", String.valueOf(pager))
                    .addFormDataPart("pageSize", "10")
                    .build();
        } else if (mMark == 2) {//找监理
            requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("type", "3")
                    .addFormDataPart("pn", String.valueOf(pager))
                    .addFormDataPart("pageSize", "10")
                    .build();
        }


        APPApi.getInstance().service
                .findAllType(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindAllTypeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(FindAllTypeBean bean) {
                        int size = bean.getData() == null ? 0 : bean.getData().size();
                        String msg = bean.getMessage();
                        if ("1".equals(bean.getResponseState())) {
                            if (Constant.REFRESH_REQUEST.equals(value)) {
                                mAdapter.setNewData(bean.getData());
                            } else if (Constant.NORMAL_REQUEST.equals(value)) {
                                if (size > 0) {
                                    mAdapter.addData(bean.getData());
                                }
                            }
                            if (size < 10) {
                                //第一页如果不够一页就不显示没有更多数据布局
                                mAdapter.loadMoreEnd(false);
                            } else {
                                mAdapter.loadMoreComplete();
                            }
                        } else {
                            --mPager;
                            if (mPager < 1) {
                                mPager = 1;
                            }
                            showToast(msg);
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        mAdapter.setEnableLoadMore(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        --mPager;
                        if (mPager < 1) {
                            mPager = 1;
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        mAdapter.loadMoreFail();
                        mAdapter.setEnableLoadMore(true);
                        LogUtil.e("Fc_用户端1-设计师，请求网路失败" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    
    /**
     * 顶部轮播图
     */
    private void getBannerData() {
        APPApi.getInstance().service
                .getContractContentData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContractContentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull ContractContentBean contractContentBean) {
                        if (contractContentBean.responseState.equals("1")) {
                            String sys_case_banner;
                            if (mMark == 1) {//施工
                                sys_case_banner = contractContentBean.getData().getRoad_work_banner();
                            } else {//监理
                                sys_case_banner = contractContentBean.getData().getSupervisor_banner();
                            }
                            List<String> imgUrlList = new ArrayList<>();
                            String[] split = sys_case_banner.split(",");
                            for (String urlStr : split) {
                                imgUrlList.add(Constant.BASE_IMG + urlStr);
                            }
                            setAdvData(imgUrlList);
                        } else {
                            showToast(contractContentBean.msg);
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setAdvData(List<String> imgUrlList) {
        List<String> imgTitleList = new ArrayList<>();
        for (int i = 0; i < imgTitleList.size(); i++) {
            imgTitleList.add("");
        }
        BGABanner.Adapter<AutoLinearLayout, String> bgaBannerAdapter = new BGABanner.Adapter<AutoLinearLayout, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, AutoLinearLayout itemView, String model, int position) {
                ImageView iconIv = (ImageView) itemView.findViewById(R.id.banner_iv);
                Glide.with(itemView.getContext())
                        .load(model)
                        .error(R.drawable.banner_default_icon)
                        .dontAnimate()
                        .into(iconIv);
            }
        };
        mBGABanner.setAdapter(bgaBannerAdapter);
        mBGABanner.setData(R.layout.item_banner, imgUrlList, imgTitleList);
    }
}
