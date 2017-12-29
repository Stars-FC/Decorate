package com.ygc.estatedecoration.user_activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.ygc.estatedecoration.adapter.UploadPublishNeedPicAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.RoleFindAllBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.utils.AddressPickTask;
import com.ygc.estatedecoration.utils.PictureCompressUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UserPublishActivity extends BaseActivity {

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
    EditText mDemandTitleEt;
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
    private String isBargain = "0";//是否可议价
    private UploadPublishNeedPicAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("发布需求");
        bar.setRightText("快速发布");
        bar.setRightTextColor(Color.parseColor("#4EBE65"));
        return true;
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
                    allService = "1,2,3,4";
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
                    designService = "1";
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
                    jianLiService = "3";
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
                    shiGongService = "2";
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
                    materialsService = "4";
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
                    isBargain = "1";
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
    protected void initView() {
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
                TextView tv = (TextView) LayoutInflater.from(UserPublishActivity.this).inflate(R.layout.item_tagflowlayout_service_type,
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
                        shiGongType.add(shiGongTypeRIdList.get(position));
                    } else if (selectWhichType == 1) {
                        materialsType.add(caiLiaoShangTypeRIdList.get(position));
                    }
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initDialog();
        getServiceTypeData();
        initPicRecyclerView();
    }

    private List<String> realPicUrlList = new ArrayList<>();
    private List<File> fileList = new ArrayList<>();
    private void initPicRecyclerView() {
        realPicUrlList.add("伪视图");
        mAdapter = new UploadPublishNeedPicAdapter(R.layout.item_publish_need, realPicUrlList, this);
        mUploadPicRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mUploadPicRecyclerview.setAdapter(mAdapter);
    }

    private void initDialog() {
        mPDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("请求中...");
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
    protected int getLayoutId() {
        return R.layout.activity_user_publish;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight, R.id.select_location_ll, R.id.sure_selected_tv, R.id.publish_btn})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.naviFrameRight:
                    Intent intent = new Intent(this, FastPublishActivity.class);
                    startActivity(intent);
                    break;
                case R.id.select_location_ll:
                    selectLocation();
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
                case R.id.publish_btn:
                    publishEvent();
                    break;
            }
        }
    }

    @BindView(R.id.address_tv)
    TextView mTv_address;
    private void selectLocation() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideCounty(true);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToast("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                mTv_address.setText(province.getAreaName() + " " + city.getAreaName());
            }
        });
        task.execute("辽宁", "沈阳");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                if (grantResults[0] == 0) {
                    selectPicEvent();
                }
                break;
        }
    }

    private void selectPicEvent() {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(UserPublishActivity.this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                handleUploadPicEvent(photos);
            }
        }
    }

    private ProgressDialog dialog;
    private void handleUploadPicEvent(ArrayList<String> photos) {
        if (dialog == null) {
            dialog = ProgressDialog.show(this, null, "数据处理中...", true, true);
        }
        PictureCompressUtil.getInstance().startCompress(this, photos, new PictureCompressUtil.CompressedPicResultCallBack() {
            @Override
            public void showResult(List<String> photos, List<File> list) {
                dialog.dismiss();
                fileList.addAll(list);
                realPicUrlList.remove(realPicUrlList.get(realPicUrlList.size() - 1));
                realPicUrlList.addAll(photos);
                realPicUrlList.add("伪视图");
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void publishEvent() {
        String dType = "";
        String rid = "";
        if (!TextUtils.isEmpty(allService)) {
            dType = allService;
            rid = rId;
        } else {
            if (!TextUtils.isEmpty(designService)) {
                dType = designService;
            }

            if (!TextUtils.isEmpty(shiGongService) && !TextUtils.isEmpty(dType)) {
                dType = dType + "," + shiGongService;
            } else if (!TextUtils.isEmpty(shiGongService) && TextUtils.isEmpty(dType)){
                dType = shiGongService;
            }

            if (!TextUtils.isEmpty(jianLiService) && !TextUtils.isEmpty(dType)) {
                dType = dType + "," + jianLiService;
            } else if (!TextUtils.isEmpty(jianLiService) && TextUtils.isEmpty(dType)) {
                dType = jianLiService;
            }

            if (!TextUtils.isEmpty(materialsService) && !TextUtils.isEmpty(dType)) {
                dType = dType + "," + materialsService;
            } else if (!TextUtils.isEmpty(materialsService) && TextUtils.isEmpty(dType)) {
                dType = materialsService;
            }

            StringBuilder stringBuilder = new StringBuilder();
            if (shiGongType.size() > 0) {
                for (int i = 0; i < shiGongType.size(); i++) {
                    String rIdStr = shiGongType.get(i);
                    if (i == 0) {
                        stringBuilder.append(rIdStr);
                    } else {
                        stringBuilder.append("," + rIdStr);
                    }
                }
                rid = stringBuilder.toString();
            }

            if (materialsType.size() > 0) {
                for (int i = 0; i < materialsType.size(); i++) {
                    String rIdStr = materialsType.get(i);
                    if (i == 0) {
                        if (TextUtils.isEmpty(rid)) {
                            stringBuilder.append(rIdStr);
                        } else {
                            stringBuilder.append(","+rIdStr);
                        }
                    } else {
                        stringBuilder.append(","+rIdStr);
                    }
                }
                rid = stringBuilder.toString();
            }
        }
        if (TextUtils.isEmpty(dType)) {
            showToast("请选择需求类型");
            return;
        }
        if (TextUtils.isEmpty(missionStartTime)) {
            showToast("请选择开始装修时间");
            return;
        }
        missionStartTime = String.valueOf(Integer.valueOf(missionStartTime) * 24 * 60 * 60 + System.currentTimeMillis() / 1000);

        if (TextUtils.isEmpty(missionType)) {
            showToast("请选择装修类型");
            return;
        }

        String addressStr = mTv_address.getText().toString();
        if (addressStr.equals("请选择")) {
            showToast("请选择所在区域");
            return;
        }

        String demandDetailsStr = mDemandDetailsEt.getText().toString().trim();
        if (TextUtils.isEmpty(demandDetailsStr)) {
            showToast("请输入需求详情");
            return;
        }

        String explainStr = mExplainEt.getText().toString().trim();
        if (TextUtils.isEmpty(explainStr)) {
            showToast("请输入其他备注信息");
            return;
        }

        if (TextUtils.isEmpty(constructionStatusQuo)) {
            showToast("请选择建筑现状");
            return;
        }

        String consumeMoneyStr = mConsumeMoneyEt.getText().toString().trim();
        if (TextUtils.isEmpty(consumeMoneyStr)) {
            showToast("请输入预算价格");
            return;
        }

        String buildingAreaStr = mBuildingAreaEt.getText().toString().trim();
        if (TextUtils.isEmpty(buildingAreaStr)) {
            showToast("请输入房屋面积");
            return;
        }

        if (fileList.size() == 0) {
            showToast("请上传图片");
            return;
        }
        String gongQiStr = mGongqiEt.getText().toString().trim();
        if (TextUtils.isEmpty(gongQiStr)) {
            showToast("请输入装修工期");
            return;
        }

        String demandTitleStr = mDemandTitleEt.getText().toString().trim();
        if (TextUtils.isEmpty(demandTitleStr)) {
            showToast("请输入需求标题");
            return;
        }
        String userId = String.valueOf(UserUtils.sDataBean.getAu_id());
        Log.i("521", "publishEvent: userId>" + userId);
        Log.i("521", "publishEvent: dType>" + dType);
        Log.i("521", "publishEvent: rId>" + rid);
        instancePublishEvent(dType, missionStartTime, missionType, addressStr, demandDetailsStr, explainStr, constructionStatusQuo, consumeMoneyStr, buildingAreaStr, userId, gongQiStr, demandTitleStr, rid, isBargain);
    }

    private void instancePublishEvent(String dType, String missionStartTime, String missionType, String addressStr,
                                      String demandDetailsStr, String explainStr, String constructionStatusQuo,
                                      String consumeMoneyStr, String buildingAreaStr, String auId, String gongQiStr,
                                      String demandTitleStr, String rId, String isBargain) {

        Map<String, RequestBody> partMap = new HashMap<>();
        for(File file : fileList){
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
            partMap.put("photos" + "\";filename=\"" + file.getName(), fileBody);
        }
        RequestBody dTypeBody = RequestBody.create(MediaType.parse("text/plain"), dType);
        RequestBody missionStartTimeBody = RequestBody.create(MediaType.parse("text/plain"), missionStartTime);
        RequestBody missionTypeBody = RequestBody.create(MediaType.parse("text/plain"), missionType);
        RequestBody addressBody = RequestBody.create(MediaType.parse("text/plain"), addressStr);
        RequestBody demandDetailsBody = RequestBody.create(MediaType.parse("text/plain"), demandDetailsStr);
        RequestBody demandNoteBody = RequestBody.create(MediaType.parse("text/plain"), explainStr);
        RequestBody constructionStatusQuoBody = RequestBody.create(MediaType.parse("text/plain"), constructionStatusQuo);
        RequestBody offerBody = RequestBody.create(MediaType.parse("text/plain"), consumeMoneyStr);
        RequestBody buildingAreaBody = RequestBody.create(MediaType.parse("text/plain"), buildingAreaStr);
        RequestBody cIdBody = RequestBody.create(MediaType.parse("text/plain"), auId);
        RequestBody needDaysBody = RequestBody.create(MediaType.parse("text/plain"), gongQiStr);
        RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), demandTitleStr);
        RequestBody rIdBody = RequestBody.create(MediaType.parse("text/plain"), rId);
        RequestBody offerFlagBody = RequestBody.create(MediaType.parse("text/plain"), isBargain);
        partMap.put("dType" , dTypeBody);
        partMap.put("missionStartTime" , missionStartTimeBody);
        partMap.put("missionType" , missionTypeBody);
        partMap.put("address" , addressBody);
        partMap.put("demandDetails" , demandDetailsBody);
        partMap.put("demandNote" , demandNoteBody);
        partMap.put("constructionStatusQuo" , constructionStatusQuoBody);
        partMap.put("offer" , offerBody);
        partMap.put("buildingArea" , buildingAreaBody);
        partMap.put("cId" , cIdBody);
        partMap.put("needDays" , needDaysBody);
        partMap.put("title" , titleBody);
        partMap.put("rId" , rIdBody);
        partMap.put("offerFlag" , offerFlagBody);
        mPDialog.show();
        APPApi.getInstance().service
                .publishDemand(partMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Base base) {
                        cancelDialog();
                        if (base.responseState.equals("1")) {
                            showToast("发布成功");
                            finish();
                        } else {
                            showToast(base.msg);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        cancelDialog();
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
