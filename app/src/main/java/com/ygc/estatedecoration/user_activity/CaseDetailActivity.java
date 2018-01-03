package com.ygc.estatedecoration.user_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserCaseEffectDetailAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.UserCaseEffectDetailBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.CircleImageView;
import com.ygc.estatedecoration.widget.MaxRecyclerView;
import com.ygc.estatedecoration.widget.RatingBar;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CaseDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.layout_container)
    LinearLayout mLayout_container;
    @BindView(R.id.designer_comprehensive_score_ratingbar)
    RatingBar mRatingBar_designer;
    @BindView(R.id.shigong_comprehensive_score_ratingbar)
    RatingBar mRatingBar_shigong;
    @BindView(R.id.jianli_comprehensive_score_ratingbar)
    RatingBar mRatingBar_jianli;
    @BindView(R.id.materials_comprehensive_score_ratingbar)
    RatingBar mRatingBar_materials;
    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.designer_recyclerview)
    MaxRecyclerView mDesignerRecyclerview;
    @BindView(R.id.designer_head_iv)
    CircleImageView mDesignerHeadIv;
    @BindView(R.id.designer_store_name_tv)
    TextView mDesignerStoreNameTv;
    @BindView(R.id.designer_type_tv)
    TextView mDesignerTypeTv;
    @BindView(R.id.designer_province_city_tv)
    TextView mDesignerProvinceCityTv;
    @BindView(R.id.designer_work_experience_tv)
    TextView mDesignerWorkExperienceTv;
    @BindView(R.id.designer_turnover_tv)
    TextView mDesignerTurnoverTv;
    @BindView(R.id.designer_r_name_tv)
    TextView mDesignerRNameTv;
    @BindView(R.id.designer_introduce_tv)
    TextView mDesignerIntroduceTv;

    @BindView(R.id.shigong_recyclerview)
    MaxRecyclerView mShigongRecyclerview;
    @BindView(R.id.shigong_head_iv)
    CircleImageView mShigongHeadIv;
    @BindView(R.id.shigong_store_name_tv)
    TextView mShigongStoreNameTv;
    @BindView(R.id.shigong_type_tv)
    TextView mShigongTypeTv;
    @BindView(R.id.shigong_province_city_tv)
    TextView mShigongProvinceCityTv;
    @BindView(R.id.shigong_turnover_tv)
    TextView mShigongTurnoverTv;
    @BindView(R.id.shigong_work_experience_tv)
    TextView mShigongWorkExperienceTv;
    @BindView(R.id.shigong_introduce_tv)

    TextView mShigongIntroduceTv;
    @BindView(R.id.jianli_recyclerview)
    MaxRecyclerView mJianliRecyclerview;
    @BindView(R.id.jianli_head_iv)
    CircleImageView mJianliHeadIv;
    @BindView(R.id.jianli_store_name_tv)
    TextView mJianliStoreNameTv;
    @BindView(R.id.jianli_type_tv)
    TextView mJianliTypeTv;
    @BindView(R.id.jianli_province_city_tv)
    TextView mJianliProvinceCityTv;
    @BindView(R.id.jianli_turnover_tv)
    TextView mJianliTurnoverTv;
    @BindView(R.id.jianli_work_experience_tv)
    TextView mJianliWorkExperienceTv;
    @BindView(R.id.jianli_introduce_tv)
    TextView mJianliIntroduceTv;

    @BindView(R.id.materials_recyclerview)
    MaxRecyclerView mMaterialsRecyclerview;
    @BindView(R.id.materials_head_iv)
    CircleImageView mMaterialsHeadIv;
    @BindView(R.id.materials_store_name_tv)
    TextView mMaterialsStoreNameTv;
    @BindView(R.id.materials_province_city_tv)
    TextView mMaterialsProvinceCityTv;
    @BindView(R.id.materials_turnover_tv)
    TextView mMaterialsTurnoverTv;
    @BindView(R.id.materials_introduce_tv)
    TextView mMaterialsIntroduceTv;
    private String mDid;
    private UserCaseEffectDetailAdapter mDesignerAdapter;
    private UserCaseEffectDetailAdapter mShiGongAdapter;
    private UserCaseEffectDetailAdapter mJianLiAdapter;
    private UserCaseEffectDetailAdapter mMaterialsAdapter;
    private UserCaseEffectDetailBean.DataBean mData;


    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("案例详情");
        return true;
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initView() {
        initDesignerRecyclerView();
        initShigongRecyclerView();
        initJinaLiRecyclerView();
        initMaterialsRecyclerView();
    }

    private void initMaterialsRecyclerView() {
        mMaterialsAdapter = new UserCaseEffectDetailAdapter();
        mMaterialsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mMaterialsRecyclerview.setNestedScrollingEnabled(false);
        mMaterialsRecyclerview.setAdapter(mMaterialsAdapter);
    }

    private void initJinaLiRecyclerView() {
        mJianLiAdapter = new UserCaseEffectDetailAdapter();
        mJianliRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mJianliRecyclerview.setNestedScrollingEnabled(false);
        mJianliRecyclerview.setAdapter(mJianLiAdapter);
    }

    private void initShigongRecyclerView() {
        mShiGongAdapter = new UserCaseEffectDetailAdapter();
        mShigongRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mShigongRecyclerview.setNestedScrollingEnabled(false);
        mShigongRecyclerview.setAdapter(mShiGongAdapter);
    }

    private void initDesignerRecyclerView() {
        mDesignerAdapter = new UserCaseEffectDetailAdapter();
        mDesignerRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mDesignerRecyclerview.setNestedScrollingEnabled(false);
        mDesignerRecyclerview.setAdapter(mDesignerAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getIntentData();
        showDialog();
        getCaseEffectDetailData();
    }

    private void getCaseEffectDetailData() {
        APPApi.getInstance().service
                .getCaseEffectDetailData(mDid, UserUtils.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserCaseEffectDetailBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserCaseEffectDetailBean userCaseEffectDetailBean) {
                        cancelDialog();
                        refreshFinish();
                        if (userCaseEffectDetailBean.responseState.equals("1")) {
                            mLayout_container.setVisibility(View.VISIBLE);
                            mData = userCaseEffectDetailBean.getData();
                            UserCaseEffectDetailBean.DataBean.DesignerInfoBean designerInfo = mData.getDesignerInfo();
                            UserCaseEffectDetailBean.DataBean.ConstructionInfoBean constructionInfo = mData.getConstructionInfo();
                            UserCaseEffectDetailBean.DataBean.SupervisorInfoBean supervisorInfo = mData.getSupervisorInfo();
                            UserCaseEffectDetailBean.DataBean.MaterialInfoBean materialInfo = mData.getMaterialInfo();
                            handleDesignerData(designerInfo);
                            handleConstructionInfo(constructionInfo);
                            handleSupervisorInfo(supervisorInfo);
                            handleMaterialInfo(materialInfo);
                            boolean collect = mData.isCollect();
                            if (collect) {
                                mTitleBar.setRightImageResource(R.drawable.shoucang_sel);
                            } else {
                                mTitleBar.setRightImageResource(R.drawable.shoucang);
                            }
                        } else {
                            showToast(userCaseEffectDetailBean.msg);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
                        refreshFinish();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void handleMaterialInfo(UserCaseEffectDetailBean.DataBean.MaterialInfoBean materialInfo) {
        String effect_picture = materialInfo.getEffect_picture();
        if (!TextUtils.isEmpty(effect_picture)) {
            List<String> urlList = new ArrayList<>();
            if (effect_picture.contains(",")) {
                String[] split = effect_picture.split(",");
                urlList.addAll(Arrays.asList(split));
            } else {
                urlList.add(effect_picture);
            }
            mMaterialsAdapter.setNewData(urlList);
        }

        UserCaseEffectDetailBean.DataBean.MaterialInfoBean.StoreInfoBeanXXX storeInfo = materialInfo.getStoreInfo();
        String s_logo = storeInfo.getS_logo();
        if (!TextUtils.isEmpty(s_logo)) {
            Glide.with(this).load(Constant.BASE_IMG + s_logo).into(mMaterialsHeadIv);
        }

        String s_name = storeInfo.getS_name();
        if (!TextUtils.isEmpty(s_name)) {
            mMaterialsStoreNameTv.setText(s_name);
        }

        String comprehensive_score = storeInfo.getComprehensive_score();
        if (!TextUtils.isEmpty(comprehensive_score)) {
            mRatingBar_materials.setStar(Integer.valueOf(comprehensive_score));
        }

        String s_province = storeInfo.getS_province();
        String s_city = storeInfo.getS_city();
        mMaterialsProvinceCityTv.setText(s_province + " " + s_city);

        int bid_num = storeInfo.getBid_num();
        mMaterialsTurnoverTv.setText(String.valueOf(bid_num));

        String introduce = storeInfo.getIntroduce();
        if (!TextUtils.isEmpty(introduce)) {
            mMaterialsIntroduceTv.setText(introduce);
        }
    }

    private void handleSupervisorInfo(UserCaseEffectDetailBean.DataBean.SupervisorInfoBean supervisorInfo) {
        String effect_picture = supervisorInfo.getEffect_picture();
        if (!TextUtils.isEmpty(effect_picture)) {
            List<String> urlList = new ArrayList<>();
            if (effect_picture.contains(",")) {
                String[] split = effect_picture.split(",");
                urlList.addAll(Arrays.asList(split));
            } else {
                urlList.add(effect_picture);
            }
            mJianLiAdapter.setNewData(urlList);
        }

        UserCaseEffectDetailBean.DataBean.SupervisorInfoBean.StoreInfoBeanXX storeInfo = supervisorInfo.getStoreInfo();
        String s_logo = storeInfo.getS_logo();
        if (!TextUtils.isEmpty(s_logo)) {
            Glide.with(this).load(Constant.BASE_IMG + s_logo).into(mJianliHeadIv);
        }

        String s_name = storeInfo.getS_name();
        if (!TextUtils.isEmpty(s_name)) {
            mJianliStoreNameTv.setText(s_name);
        }

        String s_type = storeInfo.getS_type();
        if (s_type.equals("团队")) {
            mJianliTypeTv.setVisibility(View.VISIBLE);
        } else {
            mJianliTypeTv.setVisibility(View.GONE);
        }

        String comprehensive_score = storeInfo.getComprehensive_score();
        if (!TextUtils.isEmpty(comprehensive_score)) {
            mRatingBar_jianli.setStar(Integer.valueOf(comprehensive_score));
        }

        String s_province = storeInfo.getS_province();
        String s_city = storeInfo.getS_city();
        mJianliProvinceCityTv.setText(s_province + " " + s_city);

        int bid_num = storeInfo.getBid_num();
        mJianliTurnoverTv.setText(String.valueOf(bid_num));

        String work_experience = storeInfo.getWork_experience();
        if (!TextUtils.isEmpty(work_experience)) {
            mJianliWorkExperienceTv.setText(work_experience + "年监理经验");
        }

        String introduce = storeInfo.getIntroduce();
        if (!TextUtils.isEmpty(introduce)) {
            mJianliIntroduceTv.setText(introduce);
        }
    }

    private void handleConstructionInfo(UserCaseEffectDetailBean.DataBean.ConstructionInfoBean constructionInfo) {
        String effect_picture = constructionInfo.getEffect_picture();
        if (!TextUtils.isEmpty(effect_picture)) {
            List<String> urlList = new ArrayList<>();
            if (effect_picture.contains(",")) {
                String[] split = effect_picture.split(",");
                urlList.addAll(Arrays.asList(split));
            } else {
                urlList.add(effect_picture);
            }
            mShiGongAdapter.setNewData(urlList);
        }

        UserCaseEffectDetailBean.DataBean.ConstructionInfoBean.StoreInfoBeanX storeInfo = constructionInfo.getStoreInfo();
        String s_logo = storeInfo.getS_logo();
        if (!TextUtils.isEmpty(s_logo)) {
            Glide.with(this).load(Constant.BASE_IMG + s_logo).into(mShigongHeadIv);
        }

        String s_name = storeInfo.getS_name();
        if (!TextUtils.isEmpty(s_name)) {
            mShigongStoreNameTv.setText(s_name);
        }

        String s_type = storeInfo.getS_type();
        if (s_type.equals("团队")) {
            mShigongTypeTv.setVisibility(View.VISIBLE);
        } else {
            mShigongTypeTv.setVisibility(View.GONE);
        }

        String comprehensive_score = storeInfo.getComprehensive_score();
        if (!TextUtils.isEmpty(comprehensive_score)) {
            mRatingBar_shigong.setStar(Integer.valueOf(comprehensive_score));
        }

        String s_province = storeInfo.getS_province();
        String s_city = storeInfo.getS_city();
        mShigongProvinceCityTv.setText(s_province + " " + s_city);

        int bid_num = storeInfo.getBid_num();
        mShigongTurnoverTv.setText(String.valueOf(bid_num));

        String work_experience = storeInfo.getWork_experience();
        if (!TextUtils.isEmpty(work_experience)) {
            mShigongWorkExperienceTv.setText(work_experience + "年施工经验");
        }

        String introduce = storeInfo.getIntroduce();
        if (!TextUtils.isEmpty(introduce)) {
            mShigongIntroduceTv.setText(introduce);
        }
    }

    private void handleDesignerData(UserCaseEffectDetailBean.DataBean.DesignerInfoBean designerInfo) {
        String effect_picture = designerInfo.getEffect_picture();
        if (!TextUtils.isEmpty(effect_picture)) {
            List<String> urlList = new ArrayList<>();
            if (effect_picture.contains(",")) {
                String[] split = effect_picture.split(",");
                urlList.addAll(Arrays.asList(split));
            } else {
                urlList.add(effect_picture);
            }
            mDesignerAdapter.setNewData(urlList);
        }

        UserCaseEffectDetailBean.DataBean.DesignerInfoBean.StoreInfoBean storeInfo = designerInfo.getStoreInfo();
        String s_logo = storeInfo.getS_logo();
        if (!TextUtils.isEmpty(s_logo)) {
            Glide.with(this).load(Constant.BASE_IMG + s_logo).into(mDesignerHeadIv);
        }

        String s_name = storeInfo.getS_name();
        if (!TextUtils.isEmpty(s_name)) {
            mDesignerStoreNameTv.setText(s_name);
        }

        String s_type = storeInfo.getS_type();
        if (s_type.equals("团队")) {
            mDesignerTypeTv.setVisibility(View.VISIBLE);
        } else {
            mDesignerTypeTv.setVisibility(View.GONE);
        }

        String comprehensive_score = storeInfo.getComprehensive_score();
        if (!TextUtils.isEmpty(comprehensive_score)) {
            mRatingBar_designer.setStar(Integer.valueOf(comprehensive_score));
        }

        String s_province = storeInfo.getS_province();
        String s_city = storeInfo.getS_city();
        mDesignerProvinceCityTv.setText(s_province + " " + s_city);

        int bid_num = storeInfo.getBid_num();
        mDesignerTurnoverTv.setText(String.valueOf(bid_num));

        String work_experience = storeInfo.getWork_experience();
        if (!TextUtils.isEmpty(work_experience)) {
            mDesignerWorkExperienceTv.setText(work_experience + "年设计经验");
        }

        UserCaseEffectDetailBean.DataBean.DesignerInfoBean.StoreInfoBean.RinfoBean rinfo = storeInfo.getRinfo();
        String r_name = rinfo.getR_name();
        if (!TextUtils.isEmpty(r_name)) {
            mDesignerRNameTv.setText(r_name);
        }

        String introduce = storeInfo.getIntroduce();
        if (!TextUtils.isEmpty(introduce)) {
            mDesignerIntroduceTv.setText(introduce);
        }
    }

    private void refreshFinish() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private void getIntentData() {
        mDid = getIntent().getStringExtra("did");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_detail;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.naviFrameRight:
                    collectEvent();
                    break;
            }
        }
    }

    private void collectEvent() {
        if (UserUtils.getOnLineBoolean(this, "")) {
            if (mData.isCollect()) {//已收藏
                showDialog();
                APPApi.getInstance().service
                        .cancelCollect(UserUtils.getUserId(), mDid, "1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Base>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull Base base) {
                                cancelDialog();
                                if (base.responseState.equals("1")) {
                                    mData.setCollect(false);
                                    mTitleBar.setRightImageResource(R.drawable.shoucang);
                                } else {
                                    showToast(base.msg);
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                cancelDialog();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            } else {//未收藏
                showDialog();
                APPApi.getInstance().service
                        .doCollect(UserUtils.getUserId(), mDid, "1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Base>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(@NonNull Base base) {
                                cancelDialog();
                                if (base.responseState.equals("1")) {
                                    mData.setCollect(true);
                                    mTitleBar.setRightImageResource(R.drawable.shoucang_sel);
                                } else {
                                    showToast(base.msg);
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                cancelDialog();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        } else {
            // TODO: 2018/1/3 当前用户未登陆
        }
    }

    @Override
    public void onRefresh() {
        getCaseEffectDetailData();
    }

}
