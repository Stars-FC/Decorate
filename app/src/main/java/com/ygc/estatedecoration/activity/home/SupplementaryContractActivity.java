package com.ygc.estatedecoration.activity.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UploadBuChongContractPicAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.api.APPService;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.ContractInfoBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.event.UpdateServiceProgressMsg;
import com.ygc.estatedecoration.utils.DateUtil;
import com.ygc.estatedecoration.utils.PictureCompressUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SupplementaryContractActivity extends BaseActivity {

    @BindView(R.id.supplementary_contract_name)
    EditText mSupplementaryContractName;
    @BindView(R.id.supplementary_contract_content)
    EditText mSupplementaryContractContent;
    @BindView(R.id.supplementary_contract_pay_money)
    EditText mSupplementaryContractPayMoney;
    @BindView(R.id.start_time_et)
    TextView mTv_startTime;
    @BindView(R.id.end_time_et)
    TextView mTv_endTime;
    @BindView(R.id.supplementary_contract_day)
    TextView mSupplementaryContractDay;
    @BindView(R.id.supplementary_contract_supplementary_content)
    EditText mSupplementaryContractSupplementaryContent;
    @BindView(R.id.upload_pic_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.supplementary_contract_submit)
    Button mBtn_submit;
    private SweetAlertDialog mPDialog;
    private String mConIdStr;//发起补充合同是主合同的id，编辑合同是补充合同的id
    private int mSign;
    private String mMark;
    private String mRcId;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private List<String> realPicUrlList = new ArrayList<>();
    private List<File> fileList = new ArrayList<>();
    private UploadBuChongContractPicAdapter mAdapter;

    private void initRecyclerView() {
        realPicUrlList.add("伪视图");
        mAdapter = new UploadBuChongContractPicAdapter(R.layout.item_publish_need, realPicUrlList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        getIntentData();
        initDialog();
        getContractInfoEvent();
    }

    private void getContractInfoEvent() {
        mPDialog.show();
        APPApi.getInstance().service
                .getContractInfo(mConIdStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContractInfoBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull ContractInfoBean contractInfoBean) {
                        cancelDialog();
                        List<ContractInfoBean.DataBean> data = contractInfoBean.getData();
                        if (data != null && data.size() > 0) {
                            List<ContractInfoBean.DataBean.ReplenishContractBean> replenishContract = data.get(0).getReplenishContract();
                            if (replenishContract != null && replenishContract.size() > 0) {
                                for (int i = 0; i < replenishContract.size(); i++) {
                                    ContractInfoBean.DataBean.ReplenishContractBean replenishContractBean = replenishContract.get(i);
                                    if (replenishContractBean.getRcId().equals(mConIdStr)) {
                                        String title = replenishContractBean.getTitle();
                                        String detail = replenishContractBean.getDetail();
                                        String price = replenishContractBean.getPrice();
                                        String startTime = replenishContractBean.getStartTime();
                                        String endTime = replenishContractBean.getEndTime();
                                        int needDays = replenishContractBean.getNeedDays();
                                        String replenishDetail = replenishContractBean.getReplenishDetail();
                                        mSupplementaryContractName.setText(title);
                                        mSupplementaryContractContent.setText(detail);
                                        mSupplementaryContractPayMoney.setText(price);
                                        mTv_startTime.setText(startTime);
                                        mTv_endTime.setText(endTime);
                                        mSupplementaryContractDay.setText(String.valueOf(needDays));
                                        mSupplementaryContractSupplementaryContent.setText(replenishDetail);
                                        break;
                                    }
                                }
                            }
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

    private void getIntentData() {
        Intent intent = getIntent();
        mMark = intent.getStringExtra("mark");
        mConIdStr = intent.getStringExtra("conId");
        if (mMark.equals("1")) {
            mSign = intent.getIntExtra("sign", 0);
            mTitleBar.setTitleText("补充合同");
            mBtn_submit.setText("发起补充合同");
        } else {
            mTitleBar.setTitleText("编辑合同");
            mBtn_submit.setText("确定修改");
            mRcId = intent.getStringExtra("rcId");
        }
    }

    private void initDialog() {
        mPDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("请求中...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_supplementary_contract;
    }

    @OnClick({R.id.naviFrameLeft, R.id.supplementary_contract_submit, R.id.start_time_rl, R.id.end_time_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.start_time_rl:
                onYearMonthDayPicker(0);
                break;
            case R.id.end_time_rl:
                onYearMonthDayPicker(1);
                break;
            case R.id.supplementary_contract_submit:
                faQiBuChongContract();
                break;
        }
    }

    public void onYearMonthDayPicker(final int mark) {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setTopLineColor(0x994EBE65);
        picker.setTextColor(0xFF4EBE65);
        picker.setCancelTextColor(0xFF4EBE65);
        picker.setSubmitTextColor(0xFF4EBE65);
        picker.setTitleTextColor(0xFF4EBE65);
        picker.setDividerColor(0xFF4EBE65);
        picker.setRangeEnd(2111, 1, 11);
        picker.setRangeStart(2016, 8, 29);
        String curYearMonthDayStr = DateUtil.getNowString();
        String substring = curYearMonthDayStr.substring(0, 10);
        String[] split = substring.split("-");
        picker.setSelectedItem(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (mark == 0) {
                    mTv_startTime.setText(year + "-" + month + "-" + day);
                } else if (mark == 1) {
                    mTv_endTime.setText(year + "-" + month + "-" + day);
                }
                String projectStartTimeStr = mTv_startTime.getText().toString().trim();
                String projectEndTimeStr = mTv_endTime.getText().toString().trim();

                if (!TextUtils.isEmpty(projectStartTimeStr) && !TextUtils.isEmpty(projectEndTimeStr)) {
                    String startTimeStr = projectStartTimeStr + " 00:00:00";
                    long startTimeLog = DateUtil.string2Millis(startTimeStr);
                    String endTimeStr = projectEndTimeStr + " 00:00:00";
                    long endTimeLog = DateUtil.string2Millis(endTimeStr);
                    if (startTimeLog > endTimeLog) {
                        showToast("结束日期不能小于开始日期");
                        return;
                    }
                    int allTimeInt = (int) ((endTimeLog - startTimeLog) / 1000 / 60 / 60 / 24) + 1;
                    mSupplementaryContractDay.setText(String.valueOf(allTimeInt));
                }
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    private void faQiBuChongContract() {
        String nameStr = mSupplementaryContractName.getText().toString().trim();
        if (TextUtils.isEmpty(nameStr)) {
            showToast("请输入阶段名称");
            return;
        }
        String contentStr = mSupplementaryContractContent.getText().toString().trim();
        if (TextUtils.isEmpty(contentStr)) {
            showToast("请输入项目内容");
            return;
        }
        String moneyStr = mSupplementaryContractPayMoney.getText().toString().trim();
        if (TextUtils.isEmpty(moneyStr)) {
            showToast("请输入付款金额");
            return;
        }
        java.text.DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String moneyD = decimalFormat.format(Double.valueOf(moneyStr));

        String startTimeStr = mTv_startTime.getText().toString().trim();
        if (TextUtils.isEmpty(startTimeStr)) {
            showToast("请选择开始日期");
            return;
        }
        String endTimeStr = mTv_endTime.getText().toString().trim();
        if (TextUtils.isEmpty(endTimeStr)) {
            showToast("请选择结束日期");
            return;
        }
        String dayStr = mSupplementaryContractDay.getText().toString().trim();

        String explainStr = mSupplementaryContractSupplementaryContent.getText().toString().trim();
        if (TextUtils.isEmpty(explainStr)) {
            showToast("请输入补充内容");
            return;
        }

        /*if (mMark.equals("1")) {
            if (fileList.size() == 0) {
                showToast("请上传附件");
                return;
            }
        }*/

        mPDialog.show();
        Map<String, RequestBody> partMap = new HashMap<>();
        if (fileList.size() > 0) {
            for (File file : fileList) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
                partMap.put("accessory" + "\";filename=\"" + file.getName(), fileBody);
            }
        }

        RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), nameStr);
        RequestBody detailBody = RequestBody.create(MediaType.parse("text/plain"), contentStr);
        RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), moneyD);
        RequestBody needDaysBody = RequestBody.create(MediaType.parse("text/plain"), dayStr);
        RequestBody replenishDetailBody = RequestBody.create(MediaType.parse("text/plain"), explainStr);
        RequestBody startTimeBody = RequestBody.create(MediaType.parse("text/plain"), startTimeStr);
        RequestBody endTimeBody = RequestBody.create(MediaType.parse("text/plain"), endTimeStr);
        if (mMark.equals("1")) {
            RequestBody signBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mSign));
            partMap.put("sign", signBody);
            RequestBody conIdBody = RequestBody.create(MediaType.parse("text/plain"), mConIdStr);
            partMap.put("conId", conIdBody);
        } else {
            RequestBody conIdBody = RequestBody.create(MediaType.parse("text/plain"), mRcId);
            partMap.put("rcId", conIdBody);
        }
        partMap.put("title", nameBody);
        partMap.put("detail", detailBody);
        partMap.put("price", priceBody);
        partMap.put("needDays", needDaysBody);
        partMap.put("replenishDetail", replenishDetailBody);
        partMap.put("startTime", startTimeBody);
        partMap.put("endTime", endTimeBody);

        APPService service = APPApi.getInstance().service;
        Observable<Base> baseObservable;
        if (mMark.equals("1")) {
            baseObservable = service.addReplenishContract(partMap);
        } else {
            baseObservable = service.editReplenishContract(partMap);
        }
        baseObservable
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
                            EventBus.getDefault().post(new UpdateServiceProgressMsg());
                            finish();
                        }
                        showToast(base.msg);
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

    private void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
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
                .start(SupplementaryContractActivity.this, PhotoPicker.REQUEST_CODE);
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
}
