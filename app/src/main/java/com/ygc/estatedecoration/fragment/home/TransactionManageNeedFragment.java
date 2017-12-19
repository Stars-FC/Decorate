package com.ygc.estatedecoration.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.TransactionManageNeedAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.CircleImageView;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by FC on 2017/11/20.
 * 首页-交易管理-ViewPager第一界面需求详情页面
 */

public class TransactionManageNeedFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable{

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.iv_customer_icon)
    CircleImageView mIvCustomerIcon;
    @BindView(R.id.iv_customer_name)
    TextView mIvCustomerName;
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.demandTitle_tv)
    TextView mDemandTitleTv;
    @BindView(R.id.time_tv)
    TextView mTimeTv;
    @BindView(R.id.address_tv)
    TextView mAddressTv;
    @BindView(R.id.residue_time_tv)
    TextView mResidueTimeTv;
    @BindView(R.id.missionType_tv)
    TextView mMissionTypeTv;
    @BindView(R.id.constructionStatusQuo_tv)
    TextView mConstructionStatusQuoTv;
    @BindView(R.id.buildingArea_tv)
    TextView mBuildingAreaTv;
    @BindView(R.id.finish_time_tv)
    TextView mFinishTimeTv;
    @BindView(R.id.demandDetails_tv)
    TextView mDemandDetailsTv;
    @BindView(R.id.explain_tv)
    TextView mExplainTv;

    private TransactionManageNeedAdapter mTransactionManageNeedAdapter;
    private List<String> dataList = new ArrayList<>();
    private NeedBean.DataBean mDataBean;

    public static TransactionManageNeedFragment getInstance(NeedBean.DataBean creatorBean) {
        TransactionManageNeedFragment transactionManageNeedFragment = new TransactionManageNeedFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("creator", creatorBean);
        transactionManageNeedFragment.setArguments(bundle);
        return transactionManageNeedFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mDataBean = (NeedBean.DataBean) arguments.getSerializable("creator");
        }
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        if (!TextUtils.isEmpty(mDataBean.getCreator().getHead_portrait())) {
            Glide.with(mActivity).load(Constant.BASE_IMG + mDataBean.getCreator().getHead_portrait()).into(mIvCustomerIcon);
        } else {
            mIvCustomerIcon.setImageResource(R.drawable.touxiangxuqiubaojia);
        }
        mIvCustomerName.setText(mDataBean.getCreator().getNickname());
        mTvOrderNumber.setText(String.valueOf(mDataBean.getDId()));

        if (TextUtils.isEmpty(mDataBean.getTitle())) {
            mDemandTitleTv.setText("无标题");
        } else {
            mDemandTitleTv.setText(mDataBean.getTitle());
        }
        mTvMoney.setText(mDataBean.getOffer()+"");
        mTimeTv.setText(mDataBean.getCreateTime().substring(0, 10));
        mAddressTv.setText(mDataBean.getAddress());
        if ((Long.valueOf(mDataBean.getMissionStartTime()) - System.currentTimeMillis()) > 0) {
            long time = (Long.valueOf(mDataBean.getMissionStartTime()) - System.currentTimeMillis() / 1000);
            int day = (int) (time / (24 * 60 * 60));
            int hour = (int) ((time % (24 * 60 * 60)) / (60 * 60));
            mResidueTimeTv.setText("剩余" + day + "天" + hour + "小时");
        } else {
            mResidueTimeTv.setText("剩余0天0小时");
        }
        String missionType = mDataBean.getMissionType();
        if (missionType.equals("0")) {
            mMissionTypeTv.setText("家装");
        } else if (missionType.equals("1")) {
            mMissionTypeTv.setText("工装");
        }
        int constructionStatusQuo = mDataBean.getConstructionStatusQuo();
        if (constructionStatusQuo == 0) {
            mConstructionStatusQuoTv.setText("局部改造");
        } else if (constructionStatusQuo == 1) {
            mConstructionStatusQuoTv.setText("毛坯房");
        } else if (constructionStatusQuo == 2) {
            mConstructionStatusQuoTv.setText("旧房翻新");
        }
        mBuildingAreaTv.setText(mDataBean.getBuildingArea() + "㎡");
//        DateUtil.millis2String(Long.valueOf(mDataBean.getMissionStartTime()) * 1000).substring(0, 10) + "开工"
        mFinishTimeTv.setText("未定义");
        mDemandDetailsTv.setText(mDataBean.getDemandDetails());
        mExplainTv.setText(mDataBean.getDemandNote());
//        String photos = mDataBean.getPhotos();
//        String[] split = photos.split(",");
//        dataList.addAll(Arrays.asList(split));
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mTransactionManageNeedAdapter = new TransactionManageNeedAdapter(R.layout.item_need_detail, dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mTransactionManageNeedAdapter);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.home_need_details;
    }

}
