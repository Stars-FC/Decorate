package com.ygc.estatedecoration.user_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.MyStoreBean;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的店铺-公司信息界面
 */

public class UserInformationFragment extends BaseFragment {

    @BindView(R.id.tv_turnover)
    TextView mTvTurnover;//交易额
    @BindView(R.id.et_comment)
    TextView mEtComment;//好评率
    @BindView(R.id.tv_bid_num)
    TextView mTvBidNum;//中标次数
    @BindView(R.id.et_store_introduce)
    TextView mEtStoreIntroduce;//店铺介绍
    @BindView(R.id.et_store_background)
    TextView mEtStoreBackground;//背景资料
    @BindView(R.id.et_store_work_experience)
    TextView mEtStoreWorkExperience;//工作经验

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
//        Fragment.setArguments(Bundle bundle)
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.user_look_service_store_information;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyStoreBean bean) {

        mTvTurnover.setText(bean.getData().getTurnover() + "");
//        mEtComment.setText(bean.getData().getBackground_info());//好评率
        mTvBidNum.setText(bean.getData().getBid_num() + "");
        mEtStoreIntroduce.setText(bean.getData().getIntroduce());
        mEtStoreBackground.setText(bean.getData().getBackground_info());
        mEtStoreWorkExperience.setText(bean.getData().getWork_experience());
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

    ;
}
