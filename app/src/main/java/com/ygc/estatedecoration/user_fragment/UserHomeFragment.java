package com.ygc.estatedecoration.user_fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.LoginActivity;
import com.ygc.estatedecoration.activity.my.ActivitiesActivity;
import com.ygc.estatedecoration.activity.my.ActivitiesDetailsActivity;
import com.ygc.estatedecoration.adapter.UserFindDesignerAdapter;
import com.ygc.estatedecoration.adapter.UserFindMaterialAdapter;
import com.ygc.estatedecoration.adapter.UserRecommendActivityAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.ContractContentBean;
import com.ygc.estatedecoration.bean.FindActivitesBean;
import com.ygc.estatedecoration.bean.FindAllTypeBean;
import com.ygc.estatedecoration.bean.FindMaterialBean;
import com.ygc.estatedecoration.bean.MyActivitesBean;
import com.ygc.estatedecoration.bean.UserHomeSkillBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.event.SkipUserShopMsg;
import com.ygc.estatedecoration.user_activity.UserFindDesigerActivity;
import com.ygc.estatedecoration.user_activity.UserFindSupervisorActivity;
import com.ygc.estatedecoration.user_activity.UserMsgActivity;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.widget.TitleBar;
import com.zhy.autolayout.AutoLinearLayout;

import org.greenrobot.eventbus.EventBus;

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
    RoundedImageView mRiv_tips;//小技巧图片
    @BindView(R.id.tips_title_tv)
    TextView mTv_tips;//小技巧标题

    @BindView(R.id.recommend_activity_recyclerview)
    RecyclerView mRv_recommendActivity;

    private UserFindDesignerAdapter mDesignAdapter;//找设计
    private UserFindDesignerAdapter mFindImplementAdapter;//找施工
    private UserFindDesignerAdapter mFindSupervisorAdapter;//找监理
    private UserFindMaterialAdapter mFindMaterialsAdapter;//找材料
    private UserRecommendActivityAdapter mRecommendActivityAdapter;//推荐活动
    private List<FindActivitesBean.DataBean> mData;

    public UserHomeFragment() {
    }

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
        mSwipeRefreshLayout.setRefreshing(true);//启动页面显示刷新状态
        //顶部轮播图
        getBannerData();
        //获取 找设计/找施工/找监理信息
        for (int i = 1; i <= 3; i++) {
            findAllType(i);
        }
        findMaterial();//找材料
        findSkill();   //小技巧
        findActivity();//推荐活动
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
        mRecommendActivityAdapter = new UserRecommendActivityAdapter(R.layout.item_user_home_fragment_recommend_activity);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_recommendActivity.setLayoutManager(linearLayoutManager);
        mRv_recommendActivity.setAdapter(mRecommendActivityAdapter);

       /* mRecommendActivityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, ActivitiesDetailsActivity.class);
                FindActivitesBean.DataBean dataBean = mData.get(position);
                intent.putExtra("type", 1);
                intent.putExtra("activity_home", dataBean);
                startActivity(intent);
            }
        });*/
        mRv_recommendActivity.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, ActivitiesDetailsActivity.class);
                FindActivitesBean.DataBean dataBean = mData.get(position);
                intent.putExtra("type", 1);
                intent.putExtra(ActivitiesDetailsActivity.ACTIVITES_HOME, dataBean);
                startActivity(intent);
            }
        });

    }

    /*找材料*/
    private void initFindMaterialsRecyclerView() {
        mFindMaterialsAdapter = new UserFindMaterialAdapter(R.layout.item_user_home_fragment_find_materials);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findMaterials.setLayoutManager(linearLayoutManager);
        mRv_findMaterials.setAdapter(mFindMaterialsAdapter);
    }

    /*找监理*/
    private void initFindSupervisorRecyclerView() {
        mFindSupervisorAdapter = new UserFindDesignerAdapter(R.layout.item_user_home_fragment_find_design, 3);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findSupervisor.setLayoutManager(linearLayoutManager);
        mRv_findSupervisor.setAdapter(mFindSupervisorAdapter);
    }

    /*找施工*/
    private void initFindImplementRecyclerView() {
        mFindImplementAdapter = new UserFindDesignerAdapter(R.layout.item_user_home_fragment_find_design, 2);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findImpl.setLayoutManager(linearLayoutManager);
        mRv_findImpl.setAdapter(mFindImplementAdapter);
    }

    /*找设计*/
    private void initFindDesignRecyclerView() {
        mDesignAdapter = new UserFindDesignerAdapter(R.layout.item_user_home_fragment_find_design, 1);
        mRv_findDesign.setHasFixedSize(true);
        mRv_findDesign.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRv_findDesign.setLayoutManager(linearLayoutManager);
        mRv_findDesign.setAdapter(mDesignAdapter);
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
        //顶部轮播图
        getBannerData();
        //获取 找设计/找施工/找监理信息
        for (int i = 1; i <= 3; i++) {
            findAllType(i);
        }
        findMaterial();
        findSkill();
        findActivity();
    }


    /**
     * 获取信息
     *
     * @param postion 1-设计师/2-施工/3-监理
     */
    private void findAllType(final int postion) {

        if (!NetWorkUtil.isNetWorkConnect(mActivity)) {
            showToast("请检查网络设置");
            return;
        }

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("type", String.valueOf(postion))
                .addFormDataPart("pageSize", "6")
                .build();

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
                        String msg = bean.getMessage();
                        if ("1".equals(bean.getResponseState())) {
                            switch (postion) {
                                case 1:
                                    mDesignAdapter.setNewData(bean.getData());
                                    break;
                                case 2:
                                    mFindImplementAdapter.setNewData(bean.getData());
                                    break;
                                case 3:
                                    mFindSupervisorAdapter.setNewData(bean.getData());
                                    break;
                            }
                        } else {
                            showToast(msg);
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        LogUtil.e("Fc_用户端主页1-设计师/2-施工/3-监理，请求网路失败" + e.getMessage());
//                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 推荐活动
     */
    private void findActivity() {
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("pageSize", "6")
                .build();

        APPApi.getInstance().service
                .findActivity(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindActivitesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(FindActivitesBean bean) {
                        String msg = bean.getMsg();
                        mData = bean.getData();
                        if ("1".equals(bean.getResponseState())) {
                            mRecommendActivityAdapter.setNewData(mData);
                        } else {
                            showToast(msg);
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        LogUtil.e("Fc_用户端主页推荐活动，请求网路失败" + e.getMessage());
//                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 找材料
     */
    private void findMaterial() {
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("pageSize", "6")
                .build();

        APPApi.getInstance().service
                .findMaterial(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindMaterialBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(FindMaterialBean bean) {
                        String msg = bean.getMsg();
                        if ("1".equals(bean.getResponseState())) {
                            mFindMaterialsAdapter.setNewData(bean.getData());
                        } else {
                            showToast(msg);
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        LogUtil.e("Fc_用户端主页找材料，请求网路失败" + e.getMessage());
//                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 小技巧
     */
    private void findSkill() {
        APPApi.getInstance().service
                .getSkillData("1", "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserHomeSkillBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UserHomeSkillBean bean) {
                        String msg = bean.getMsg();
                        if ("1".equals(bean.getResponseState())) {
                            mTv_tips.setText(bean.getData().get(0).getSkill_title());
                            String skill_picture = Constant.BASE_IMG + bean.getData().get(0).getSkill_picture();
                            Glide.with(mActivity)
                                    .load(skill_picture)
                                    .placeholder(R.drawable.iv_error)
                                    .error(R.drawable.iv_error)
                                    .into(mRiv_tips);
                        } else {
                            showToast(msg);
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        LogUtil.e("Fc_用户端主页小技巧，请求网路失败" + e.getMessage());
//                        showToast(getResources().getString(R.string.network_error));
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
                            String sys_case_banner = contractContentBean.getData().getSys_banner();
                            List<String> imgUrlList = new ArrayList<>();
                            String[] split = sys_case_banner.split(",");
                            for (String urlStr : split) {
                                imgUrlList.add(Constant.BASE_IMG + urlStr);
                            }
                            setAdvData(imgUrlList);
                        } else {
                            showToast(contractContentBean.msg);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
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
