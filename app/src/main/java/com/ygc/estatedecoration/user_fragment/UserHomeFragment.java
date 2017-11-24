package com.ygc.estatedecoration.user_fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserFindDesignAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class UserHomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.location_tv)
    TextView mTv_location;
    @BindView(R.id.banner)
    BGABanner mBGABanner;
    @BindView(R.id.find_design_recyclerview)
    RecyclerView mRv_findDesign;
    @BindView(R.id.find_implement_recyclerview)
    RecyclerView mRv_findImpl;
    @BindView(R.id.find_supervisor_recyclerview)
    RecyclerView mRv_findSupervisor;
    @BindView(R.id.find_materials_recyclerview)
    RecyclerView mRv_findMaterials;

    @BindView(R.id.tips_riv)
    RoundedImageView mRiv_tips;
    @BindView(R.id.tips_title_tv)
    TextView mTv_tips;

    @BindView(R.id.recommend_activity_recyclerview)
    RecyclerView mRv_recommendActivity;

    private List<String> findDesignDataList = new ArrayList<>();
    private UserFindDesignAdapter mUserFindDesignAdapter;


    public UserHomeFragment() {}

    public static UserHomeFragment newInstance() {
        return new UserHomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {

        getNetworkData(Constant.NORMAL_REQUEST);
    }

    private void getNetworkData(String requestMark) {
        /*APPApi.getInstance().service
                .queryUserHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {


                    }

                    @Override
                    public void onNext(@NonNull Base base) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        initFindDesignRecyclerView();

        initFindImplementRecyclerView();

        initFindSupervisorRecyclerView();

        initFindMaterialsRecyclerView();

        initRecommendActivityRecyclerView();
    }

    private void initRecommendActivityRecyclerView() {
        mUserFindDesignAdapter = new UserFindDesignAdapter(R.layout.item_user_home_fragment_find_design, findDesignDataList);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_recommendActivity.setLayoutManager(linearLayoutManager);
        mRv_recommendActivity.setAdapter(mUserFindDesignAdapter);
    }

    private void initFindMaterialsRecyclerView() {
        mUserFindDesignAdapter = new UserFindDesignAdapter(R.layout.item_user_home_fragment_find_materials, findDesignDataList);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findMaterials.setLayoutManager(linearLayoutManager);
        mRv_findMaterials.setAdapter(mUserFindDesignAdapter);
    }

    private void initFindSupervisorRecyclerView() {
        mUserFindDesignAdapter = new UserFindDesignAdapter(R.layout.item_user_home_fragment_find_design, findDesignDataList);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findSupervisor.setLayoutManager(linearLayoutManager);
        mRv_findSupervisor.setAdapter(mUserFindDesignAdapter);
    }

    private void initFindImplementRecyclerView() {
        mUserFindDesignAdapter = new UserFindDesignAdapter(R.layout.item_user_home_fragment_find_design, findDesignDataList);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findImpl.setLayoutManager(linearLayoutManager);
        mRv_findImpl.setAdapter(mUserFindDesignAdapter);
    }

    private void initFindDesignRecyclerView() {
        findDesignDataList.add("hah");
        findDesignDataList.add("hah");
        findDesignDataList.add("hah");
        findDesignDataList.add("hah");
        findDesignDataList.add("heh");
        findDesignDataList.add("ded");
        mUserFindDesignAdapter = new UserFindDesignAdapter(R.layout.item_user_home_fragment_find_design, findDesignDataList);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findDesign.setLayoutManager(linearLayoutManager);
        mRv_findDesign.setAdapter(mUserFindDesignAdapter);
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
        return R.layout.fragment_user_home;
    }

    @OnClick({R.id.location_ll, R.id.search_ll, R.id.find_design_more_ll, R.id.find_implement_more_ll,
            R.id.find_supervisor_more_ll, R.id.find_materials_more_ll, R.id.tips_more_ll, R.id.recommend_activity_ll})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.location_ll://定位
                    break;
                case R.id.search_ll://搜索
                    break;
                case R.id.find_design_more_ll:
                    break;
                case R.id.find_implement_more_ll:
                    break;
                case R.id.find_supervisor_more_ll:
                    break;
                case R.id.find_materials_more_ll:
                    break;
                case R.id.tips_more_ll://小技巧
                    break;
                case R.id.recommend_activity_ll://推荐活动
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        getNetworkData(Constant.REFRESH_REQUEST);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
