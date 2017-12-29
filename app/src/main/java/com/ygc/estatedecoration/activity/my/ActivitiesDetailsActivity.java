package com.ygc.estatedecoration.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.MyActivitesBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitiesDetailsActivity extends BaseActivity {

    @BindView(R.id.my_activites_details_title)
    TextView my_activites_details_title;//标题
    @BindView(R.id.my_activites_details_image)
    ImageView mMyActivitesDetailsImage;//活动图片
    @BindView(R.id.my_activites_details_startime)
    TextView mMyActivitesDetailsStartime;//开始时间
    @BindView(R.id.my_activites_details_endtime)
    TextView mMyActivitesDetailsEndtime;//结束时间
    @BindView(R.id.my_activites_details_place)
    TextView mMyActivitesDetailsPlace;//活动地点
    @BindView(R.id.my_activites_details_introduce)
    TextView mMyActivitesDetailsIntroduce;//介绍
    private MyActivitesBean.DataBean mBean;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("活动详情");
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mBean = (MyActivitesBean.DataBean) getIntent().getSerializableExtra(ActivitiesActivity.ACTIVITES_BEAN);

        mMyActivitesDetailsStartime.setText(mBean.getStart_time());
        mMyActivitesDetailsEndtime.setText(mBean.getEnd_time());
        mMyActivitesDetailsPlace.setText(mBean.getPlace());
        mMyActivitesDetailsIntroduce.setText(mBean.getIntroduce());
        my_activites_details_title.setText(mBean.getTitle());
        String picture_url = Constant.BASE_IMG + mBean.getPicture_url();

        Glide.with(ActivitiesDetailsActivity.this)
                .load(picture_url)
                .placeholder(R.drawable.iv_error) //设置占位图
                .error(R.drawable.iv_error) //设置错误图片
                .dontAnimate() //不显示动画效果
                .into(mMyActivitesDetailsImage);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_activities_details;
    }

    @OnClick({R.id.naviFrameLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
        }
    }
}
