package com.ygc.estatedecoration.user_fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserFindDesignerAdapter;
import com.ygc.estatedecoration.adapter.UserFindMaterialAdapter;
import com.ygc.estatedecoration.adapter.UserRecommendActivityAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.event.SkipUserShopMsg;
import com.ygc.estatedecoration.user_activity.UserFindDesigerActivity;
import com.ygc.estatedecoration.user_activity.UserFindSupervisorActivity;
import com.ygc.estatedecoration.user_activity.UserMsgActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

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

    /*推荐活动*/
    private void initRecommendActivityRecyclerView() {
        UserRecommendActivityAdapter userRecommendActivityAdapter = new UserRecommendActivityAdapter(R.layout.item_user_home_fragment_recommend_activity, findDesignDataList);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_recommendActivity.setLayoutManager(linearLayoutManager);
        mRv_recommendActivity.setAdapter(userRecommendActivityAdapter);
    }

    /*找材料*/
    private void initFindMaterialsRecyclerView() {
        UserFindMaterialAdapter userFindMaterialAdapter = new UserFindMaterialAdapter(R.layout.item_user_home_fragment_find_materials, findDesignDataList);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findMaterials.setLayoutManager(linearLayoutManager);
        mRv_findMaterials.setAdapter(userFindMaterialAdapter);
    }

    /*找监理*/
    private void initFindSupervisorRecyclerView() {
        UserFindDesignerAdapter mUserFindDesignerAdapter = new UserFindDesignerAdapter(R.layout.item_user_home_fragment_find_design, findDesignDataList, mActivity);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findSupervisor.setLayoutManager(linearLayoutManager);
        mRv_findSupervisor.setAdapter(mUserFindDesignerAdapter);
    }

    /*找施工*/
    private void initFindImplementRecyclerView() {
        UserFindDesignerAdapter mUserFindDesignerAdapter = new UserFindDesignerAdapter(R.layout.item_user_home_fragment_find_design, findDesignDataList, mActivity);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findImpl.setLayoutManager(linearLayoutManager);
        mRv_findImpl.setAdapter(mUserFindDesignerAdapter);
    }

    /*找设计*/
    private void initFindDesignRecyclerView() {
        for (int i = 0; i < 5; i++) {
            findDesignDataList.add("heh");
        }
        UserFindDesignerAdapter mUserFindDesignerAdapter = new UserFindDesignerAdapter(R.layout.item_user_home_fragment_find_design, findDesignDataList, mActivity);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findDesign.setLayoutManager(linearLayoutManager);
        mRv_findDesign.setAdapter(mUserFindDesignerAdapter);
    }


    /*private void setBannerData(BannerAndAdvBean bannerAndAdvBean) {
        if (bannerAndAdvBean == null) {
            return;
        }
        List<BannerAndAdvBean.BannerListBean> bannerList = bannerAndAdvBean.getBannerList();
        if (bannerList != null && bannerList.size() > 0) {
            List<String> imgUrlList = new ArrayList<>();
            List<String> imgTitleList = new ArrayList<>();
            for (int i = 0; i < bannerList.size(); i++) {
                BannerAndAdvBean.BannerListBean bannerListBean = bannerList.get(i);
                imgUrlList.add(UrlUtil.IP_PORT + "/" + bannerListBean.getImgUrl());
                imgTitleList.add("");
            }
            BGABanner.Adapter<ImageView, String> bgaBannerAdapter = new BGABanner.Adapter<ImageView, String>() {
                @Override
                public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                    Glide.with(itemView.getContext())
                            .load(model)
                            .dontAnimate()
                            .centerCrop()
                            .into(itemView);
                }
            };
            mBGABanner.setAdapter(bgaBannerAdapter);
            mBGABanner.setData(imgUrlList, imgTitleList);
        }
    }*/

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

    @OnClick({R.id.location_ll, R.id.msg_ll, R.id.find_design_more_ll, R.id.find_implement_more_ll,
            R.id.find_supervisor_more_ll, R.id.find_materials_more_ll, R.id.tips_more_ll, R.id.recommend_activity_ll})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.location_ll://定位
                    break;
                case R.id.msg_ll://消息
                    Intent intent = new Intent(mActivity, UserMsgActivity.class);
                    startActivity(intent);
                    break;
                case R.id.find_design_more_ll://找设计
                    Intent desigeIntent = new Intent(mActivity, UserFindDesigerActivity.class);
                    startActivity(desigeIntent);
                    break;
                case R.id.find_implement_more_ll://找施工
                    Intent intent3 = new Intent(mActivity, UserFindSupervisorActivity.class);
                    intent3.putExtra("mark", 1);
                    startActivity(intent3);
                    break;
                case R.id.find_supervisor_more_ll://找监理
                    Intent intent1 = new Intent(mActivity, UserFindSupervisorActivity.class);
                    intent1.putExtra("mark", 2);
                    startActivity(intent1);
                    break;
                case R.id.find_materials_more_ll://找材料
                    EventBus.getDefault().post(new SkipUserShopMsg());
                    break;
                case R.id.tips_more_ll://小技巧
                    showToast("暂无内容");
                    break;
                case R.id.recommend_activity_ll://推荐活动
                    showToast("暂无内容");
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
