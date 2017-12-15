package com.ygc.estatedecoration.user_fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CaseStyleAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.user_activity.FastPublishActivity;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class UserPublishFragment extends BaseFragment {

    @BindView(R.id.banner)
    BGABanner mBanner;
    @BindView(R.id.seven_day_rb)
    RadioButton mSevenDayRb;
    @BindView(R.id.fifteen_day_rb)
    RadioButton mFifteenDayRb;
    @BindView(R.id.thirty_day_rb)
    RadioButton mThirtyDayRb;
    @BindView(R.id.start_decorate_time_rg)
    RadioGroup mStartDecorateTimeRg;
    @BindView(R.id.jia_rb)
    RadioButton mJiaRb;
    @BindView(R.id.gong_rb)
    RadioButton mGongRb;
    @BindView(R.id.decorate_type_rg)
    RadioGroup mDecorateTypeRg;
    @BindView(R.id.jubu_rb)
    RadioButton mJubuRb;
    @BindView(R.id.maopi_rb)
    RadioButton mMaopiRb;
    @BindView(R.id.jiufang_rb)
    RadioButton mJiufangRb;
    @BindView(R.id.building_status_rg)
    RadioGroup mBuildingStatusRg;
    @BindView(R.id.buildingArea_et)
    EditText mBuildingAreaEt;
    @BindView(R.id.demand_title_tv)
    EditText mDemandTitleTv;
    @BindView(R.id.all_service_cb)
    CheckBox mAllServiceCb;
    @BindView(R.id.design_service_cb)
    CheckBox mDesignServiceCb;
    @BindView(R.id.shigong_service_cb)
    CheckBox mShigongServiceCb;
    @BindView(R.id.jianli_service_cb)
    CheckBox mJianliServiceCb;
    @BindView(R.id.materials_servcie_cb)
    CheckBox mMaterialsServcieCb;
    @BindView(R.id.gongqi_et)
    EditText mGongqiEt;
    @BindView(R.id.demand_details_et)
    EditText mDemandDetailsEt;
    @BindView(R.id.explain_et)
    EditText mExplainEt;
    @BindView(R.id.consume_money_et)
    EditText mConsumeMoneyEt;
    @BindView(R.id.bargain_cb)
    CheckBox mBargainCb;
    @BindView(R.id.upload_pic_recyclerview)
    RecyclerView mUploadPicRecyclerview;

    @BindView(R.id.materials_type_recyclerview)
    RecyclerView mRv_materialsType;

    private String missionStartTime = null;//开始装修时间
    private int missionType = -1;//装修类型
    private int constructionStatusQuo = -1;//建筑现状
    private String buildingArea = null;//建筑面积
    private String title = null;//需求标题
//    private

    public static UserPublishFragment newInstance() {
        return new UserPublishFragment();
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("发布需求");
        bar.setRightText("快速发布");
        bar.setRightTextColor(Color.parseColor("#4EBE65"));
        return true;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        initMaterialsTypeRv();
    }

    private String[] materialsTypeArray = {"木工", "泥工", "水电工", "漆工", "安装工", "杂工", "玻璃", "门窗", "空调", "地暖", "灯具", "洁具", "软装"};

    private void initMaterialsTypeRv() {
        CaseStyleAdapter caseStyleAdapter = new CaseStyleAdapter(R.layout.item_case_style, Arrays.asList(materialsTypeArray));
        mRv_materialsType.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mRv_materialsType.addItemDecoration(new RecyclerSpace(30, Color.parseColor("#f6f6f6")));
        mRv_materialsType.setNestedScrollingEnabled(false);
        mRv_materialsType.setAdapter(caseStyleAdapter);

        caseStyleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected void addListener() {
        mStartDecorateTimeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.seven_day_rb:
                        break;
                    case R.id.fifteen_day_rb:
                        break;
                    case R.id.thirty_day_rb:
                        break;
                }
            }
        });
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_user_publish;
    }

    @OnClick({R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameRight:
                    Intent intent = new Intent(mActivity, FastPublishActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
