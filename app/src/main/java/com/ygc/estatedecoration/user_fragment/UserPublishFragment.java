package com.ygc.estatedecoration.user_fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.RoleFindAllBean;
import com.ygc.estatedecoration.user_activity.FastPublishActivity;
import com.ygc.estatedecoration.widget.TitleBar;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.flow_layout_type)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.type_ll)
    LinearLayout typeLl;
    private SweetAlertDialog mPDialog;

    private String missionStartTime = null;//开始装修时间
    private String missionType = null;//装修类型
    private String constructionStatusQuo = null;//建筑现状
    private TagAdapter<String> mTagAdapter;
    private String allService = null;//所有服务
    private String designService = null;//设计服务
    private String shiGongService = null;//施工服务
    private String jianLiService = null;//监理服务
    private String materialsService = null;//材料服务
    private String rId = null;//需求细类
    private String isBargain = null;//是否可议价

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
        initDialog();
        getServiceTypeData();
    }

    private void initDialog() {
        mPDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
        mPDialog.show();
    }

    private void getServiceTypeData() {
        APPApi.getInstance().service
                .roleFindAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RoleFindAllBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(RoleFindAllBean roleFindAllBean) {
                        cancelDialog();
                        if (roleFindAllBean.getResponseState().equals("1")) {
                            RoleFindAllBean.DataBean dataBean = roleFindAllBean.getData();
                            if (dataBean != null) {
                                List<RoleFindAllBean.DataBean._$2Bean> dataBean_$2 = dataBean.get_$2();
                                List<RoleFindAllBean.DataBean._$4Bean> dataBean_$4 = dataBean.get_$4();
                                for (RoleFindAllBean.DataBean._$2Bean _$2Bean : dataBean_$2) {
                                    shiGongTypeList.add(_$2Bean.getR_name());
                                    shiGongTypeRIdList.add(String.valueOf(_$2Bean.getR_id()));
                                }
                                for (RoleFindAllBean.DataBean._$4Bean _$4Bean : dataBean_$4) {
                                    caiLiaoShangTypeList.add(_$4Bean.getR_name());
                                    caiLiaoShangTypeRIdList.add(String.valueOf(_$4Bean.getR_id()));
                                }
                            }
                        } else {
                            showToast(roleFindAllBean.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        cancelDialog();
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

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTagFlowLayout();
    }

    private boolean shiGongIsChecked = false;
    private boolean materialsIsChecked = false;
    private List<String> shiGongType = new ArrayList<>();
    private List<String> materialsType = new ArrayList<>();
    private int selectWhichType = -1;
    private List<String> typeList = new ArrayList<>();
    private List<String> shiGongTypeList = new ArrayList<>();
    private List<String> shiGongTypeRIdList = new ArrayList<>();
    private List<String> caiLiaoShangTypeList = new ArrayList<>();
    private List<String> caiLiaoShangTypeRIdList = new ArrayList<>();
    private void initTagFlowLayout() {
        mTagAdapter = new TagAdapter<String>(typeList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.item_tagflowlayout_service_type,
                        mTagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
        mTagFlowLayout.setAdapter(mTagAdapter);
        mTagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                if (selectWhichType == 0) {
                    shiGongType.clear();
                } else if (selectWhichType == 1) {
                    materialsType.clear();
                }
                for (Integer position : selectPosSet) {
                    if (selectWhichType == 0) {
                        shiGongType.add(shiGongTypeList.get(position));
                    } else if (selectWhichType == 1) {
                        materialsType.add(caiLiaoShangTypeList.get(position));
                    }
                }
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
                        missionStartTime = "7";
                        break;
                    case R.id.fifteen_day_rb:
                        missionStartTime = "15";
                        break;
                    case R.id.thirty_day_rb:
                        missionStartTime = "30";
                        break;
                }
            }
        });
        mDecorateTypeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.jia_rb:
                        missionType = "0";
                        break;
                    case R.id.gong_rb:
                        missionType = "1";
                        break;
                }
            }
        });

        mBuildingStatusRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.jubu_rb:
                        constructionStatusQuo = "0";
                        break;
                    case R.id.maopi_rb:
                        constructionStatusQuo = "1";
                        break;
                    case R.id.jiufang_rb:
                        constructionStatusQuo = "2";
                        break;
                }
            }
        });

        mAllServiceCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hideTypeView();
                if (isChecked) {
                    allService = "0,1,2,3";
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < shiGongTypeRIdList.size(); i++) {
                        stringBuilder.append(shiGongTypeRIdList.get(i) + ",");
                    }
                    for (int i = 0; i < caiLiaoShangTypeRIdList.size(); i++) {
                        if (i == caiLiaoShangTypeRIdList.size() - 1) {
                            stringBuilder.append(caiLiaoShangTypeRIdList.get(i));
                        } else {
                            stringBuilder.append(caiLiaoShangTypeRIdList.get(i) + ",");
                        }
                    }
                    rId = stringBuilder.toString();
                } else {
                    allService = null;
                    rId = null;
                }
            }
        });
        mDesignServiceCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hideTypeView();
                if (isChecked) {
                    designService = "0";
                } else {
                    designService = null;
                }
            }
        });
        mJianliServiceCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hideTypeView();
                if (isChecked) {
                    jianLiService = "2";
                } else {
                    jianLiService = null;
                }
            }
        });

        mShigongServiceCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mMaterialsServcieCb.isChecked() && materialsType.size()==0) {
                    mMaterialsServcieCb.setChecked(false);
                    materialsIsChecked = false;
                }

                if (shiGongIsChecked) {
                    mShigongServiceCb.setChecked(false);
                    shiGongIsChecked = false;
                    typeLl.setVisibility(View.GONE);
                    shiGongType.clear();
                    shiGongService = null;
                } else {
                    shiGongService = "1";
                    mShigongServiceCb.setChecked(true);
                    shiGongIsChecked = true;
                    if (typeList.size()>0) {
                        typeList.clear();
                    }
                    typeList.addAll(shiGongTypeList);
                    mTagAdapter.notifyDataChanged();
                    typeLl.setVisibility(View.VISIBLE);
                    selectWhichType = 0;
                }
            }
        });

        mMaterialsServcieCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mShigongServiceCb.isChecked() && shiGongType.size() == 0) {
                    mShigongServiceCb.setChecked(false);
                    shiGongIsChecked = false;
                }

                if (materialsIsChecked) {
                    mMaterialsServcieCb.setChecked(false);
                    materialsIsChecked = false;
                    typeLl.setVisibility(View.GONE);
                    materialsType.clear();
                    materialsService = null;
                } else {
                    materialsService = "3";
                    mMaterialsServcieCb.setChecked(true);
                    materialsIsChecked = true;
                    if (typeList.size() > 0) {
                        typeList.clear();
                    }
                    typeList.addAll(caiLiaoShangTypeList);
                    mTagAdapter.notifyDataChanged();
                    typeLl.setVisibility(View.VISIBLE);
                    selectWhichType = 1;
                }
            }
        });

        mBargainCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isBargain = "0";
                } else {
                    isBargain = null;
                }
            }
        });
    }

    private void hideTypeView() {
        if (typeLl.isShown()) {
            typeLl.setVisibility(View.GONE);
        }

        if (mShigongServiceCb.isChecked() && shiGongType.size()==0) {
            mShigongServiceCb.setChecked(false);
            shiGongIsChecked = false;
        }

        if (mMaterialsServcieCb.isChecked() && materialsType.size()==0) {
            mMaterialsServcieCb.setChecked(false);
            materialsIsChecked = false;
        }
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_user_publish;
    }

    @OnClick({R.id.naviFrameRight, R.id.select_location_ll, R.id.sure_selected_tv})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameRight:
                    Intent intent = new Intent(mActivity, FastPublishActivity.class);
                    startActivity(intent);
                    break;
                case R.id.select_location_ll:

                    break;
                case R.id.sure_selected_tv:
                    typeLl.setVisibility(View.GONE);
                    if (shiGongType.size() == 0) {
                        if (mShigongServiceCb.isChecked()) {
                            mShigongServiceCb.setChecked(false);
                            shiGongIsChecked = false;
                        }
                    } else {
                        for (String string : shiGongType) {
                            Log.i("521", "onClickEvent: 施工===>" + string);
                        }
                    }

                    if (materialsType.size() == 0) {
                        if (mMaterialsServcieCb.isChecked()) {
                            mMaterialsServcieCb.setChecked(false);
                            materialsIsChecked = false;
                        }
                    } else {
                        for (String string : materialsType) {
                            Log.i("521", "onClickEvent: 材料商===>" + string);
                        }
                    }
                    break;
            }
        }
    }
}
