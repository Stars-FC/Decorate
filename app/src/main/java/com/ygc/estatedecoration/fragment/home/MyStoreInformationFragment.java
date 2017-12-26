package com.ygc.estatedecoration.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的店铺-公司信息界面
 */

public class MyStoreInformationFragment extends BaseFragment {
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
                    showToast("修改成功");
                    /**
                     * 请求网络提交修改后的内容
                     */
                }
                break;
            case R.id.et_background://编辑背景资料
                break;
            case R.id.et_work_experience://编辑工作经验
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
}
