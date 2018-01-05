package com.ygc.estatedecoration.fragment.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.bean.MyStoreBean;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的店铺-公司信息界面
 */

public class MyStoreInformationFragment extends BaseFragment {
    @BindView(R.id.tv_mystore_turnover)
    TextView mTvMystoreTurnover;//交易额
    @BindView(R.id.tv_mystore_comment)
    TextView mTvMystoreComment;//好评率
    @BindView(R.id.tv_mystore_bid_num)
    TextView mTvMystoreBidNum;//中标次数
    @BindView(R.id.my_store_introduce)
    EditText mMyStoreIntroduce;//店铺内容
    @BindView(R.id.my_store_background)
    EditText mMyStoreBackground;//背景资料
    @BindView(R.id.my_store_work_experience)
    EditText mMyStoreWorkExperience;//工作经验
    @BindView(R.id.et_introduce)
    TextView mEtIntroduce;//编辑店铺内容
    @BindView(R.id.et_background)
    TextView mEtBackground;//编辑背景资料
    @BindView(R.id.et_work_experience)
    TextView mEtWorkExperience;//编辑工作经验

    @BindView(R.id.tv_mystore_score)
    TextView mTvMystoreScore;//综合评分

    private boolean isIntroduceState = true;//编辑的判断
    private boolean isBackgroundState = true;//编辑的判断
    private boolean isExperienceState = true;//编辑的判断


    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //设置不可编辑
        isEdit(false, mMyStoreIntroduce);
        isEdit(false, mMyStoreBackground);
        isEdit(false, mMyStoreWorkExperience);

    }


    @Override
    protected void addListener() {

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.home_mystore_information;
    }

    @OnClick({R.id.et_introduce, R.id.et_background, R.id.et_work_experience})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_introduce://编辑店铺介绍
                if (isIntroduceState) {
                    isIntroduceState = false;
                    mEtIntroduce.setText("完成");//改变文字
                    isEdit(true, mMyStoreIntroduce);//设置可以修改

                } else {
                    isIntroduceState = true;
                    mEtIntroduce.setText("编辑");
                    isEdit(false, mMyStoreIntroduce);
                    // 请求网络提交修改后的内容
                    changeContent("introduce", MyPublic.getText(mMyStoreIntroduce));
                }
                break;
            case R.id.et_background://编辑背景资料
                if (isBackgroundState) {
                    isBackgroundState = false;
                    mEtBackground.setText("完成");//改变文字
                    isEdit(true, mMyStoreBackground);//设置可以修改
                } else {
                    isBackgroundState = true;
                    mEtBackground.setText("编辑");
                    isEdit(false, mMyStoreBackground);
                    // 请求网络提交修改后的内容
                    changeContent("background_info", MyPublic.getText(mMyStoreBackground));
                }
                break;
            case R.id.et_work_experience://编辑工作经验
                if (isExperienceState) {
                    isExperienceState = false;
                    mEtWorkExperience.setText("完成");//改变文字
                    isEdit(true, mMyStoreWorkExperience);//设置可以修改
                } else {
                    isExperienceState = true;
                    mEtWorkExperience.setText("编辑");
                    isEdit(false, mMyStoreWorkExperience);
                    // 请求网络提交修改后的内容
                    changeContent("work_experience", MyPublic.getText(mMyStoreWorkExperience));
                }
                break;
        }
    }

    /**
     * EditText是否可以编辑、焦点、软件盘设置
     *
     * @param value
     * @param editText
     */
    private void isEdit(boolean value, EditText editText) {
        if (value) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            /*editText.setFilters(new InputFilter[]{new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    return null;
                }
            }
            });*/
            editText.requestFocus();//代码动态设置焦点
            //打开软键盘
            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        } else {

            //关闭软键盘
            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (mActivity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
            //设置不可获取焦点
            editText.setFocusable(false);
            //输入框无法输入新的内容
            editText.setFocusableInTouchMode(false);
            /*editText.setFilters(new InputFilter[]{new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    return source.length() < 1 ? dest.subSequence(dstart, dend) : "";
                }
            }});*/
        }
    }


    /**
     * 编辑店铺信息
     *
     * @param key     字段
     * @param content 内容
     */
    public void changeContent(String key, String content) {

        if (!NetWorkUtil.isNetWorkConnect(mActivity)) {
            showToast("请检查网络设置");
            return;
        }

        final SweetAlertDialog pDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading");
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setCancelable(false);
        pDialog.show();

        String storeId = (String) UserUtils.getParam(UserUtils.STOREID, "storeId", "");
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("s_id", storeId)
                .addFormDataPart(key, content)
                .build();

        APPApi.getInstance().service
                .editMyStore(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean bean) {
                        String msg = bean.getMsg();
                        showToast(msg);
                        pDialog.cancel();
                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialog.cancel();
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyStoreBean bean) {

        mTvMystoreTurnover.setText(bean.getData().getTurnover() + "万");
        mTvMystoreBidNum.setText(bean.getData().getBid_num() + "");
        mMyStoreIntroduce.setText(bean.getData().getIntroduce());
        mMyStoreBackground.setText(bean.getData().getBackground_info());
        mMyStoreWorkExperience.setText(bean.getData().getWork_experience());
        mTvMystoreScore.setText(bean.getData().getComprehensive_score());//综合评分
        mTvMystoreComment.setText(bean.getData().getApplause_rate());//好评率

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
