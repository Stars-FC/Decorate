package com.ygc.estatedecoration.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.FindActivitesBean;
import com.ygc.estatedecoration.bean.MyActivitesBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.CircleImageView;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitiesDetailsActivity extends BaseActivity {

    public static final String ACTIVITES_HOME = "activites_home";//主页-意图key
    public static final String ACTIVITES_BEAN = "activites_bean";//我的-意图key

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
    @BindView(R.id.iv_icon)
    CircleImageView mIvIcon;//发起活动人的头像
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;//发起活动人的昵称

    private MyActivitesBean.DataBean m2Bean;
    private FindActivitesBean.DataBean m1Bean;
    private int mMark;

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
        mMark = getIntent().getIntExtra("type", 0);
        if (mMark == 1) {
            m1Bean = (FindActivitesBean.DataBean) getIntent().getSerializableExtra(ACTIVITES_HOME);
            mMyActivitesDetailsStartime.setText(m1Bean.getStart_time());
            mMyActivitesDetailsEndtime.setText(m1Bean.getEnd_time());
            mMyActivitesDetailsPlace.setText(m1Bean.getPlace());
            mMyActivitesDetailsIntroduce.setText(m1Bean.getIntroduce());
            my_activites_details_title.setText(m1Bean.getTitle());
            mTvNickname.setText(m1Bean.getUserInfo().getNickname());

            String picture_url = Constant.BASE_IMG + m1Bean.getPicture_url();
            String my_url = Constant.BASE_IMG + m1Bean.getUserInfo().getHead_portrait();

            Glide.with(ActivitiesDetailsActivity.this)
                    .load(picture_url)
                    .placeholder(R.drawable.iv_error) //设置占位图
                    .error(R.drawable.iv_error) //设置错误图片
                    .dontAnimate() //不显示动画效果
                    .into(mMyActivitesDetailsImage);

            Glide.with(ActivitiesDetailsActivity.this)
                    .load(my_url)
                    .placeholder(R.drawable.iv_error) //设置占位图
                    .error(R.drawable.iv_error) //设置错误图片
                    .dontAnimate() //不显示动画效果
                    .into(mIvIcon);

        } else if (mMark == 2) {
            m2Bean = (MyActivitesBean.DataBean) getIntent().getSerializableExtra(ACTIVITES_BEAN);
            mMyActivitesDetailsStartime.setText(m2Bean.getStart_time());
            mMyActivitesDetailsEndtime.setText(m2Bean.getEnd_time());
            mMyActivitesDetailsPlace.setText(m2Bean.getPlace());
            mMyActivitesDetailsIntroduce.setText(m2Bean.getIntroduce());
            my_activites_details_title.setText(m2Bean.getTitle());

            String picture_url = Constant.BASE_IMG + m2Bean.getPicture_url();


            Glide.with(ActivitiesDetailsActivity.this)
                    .load(picture_url)
                    .placeholder(R.drawable.iv_error) //设置占位图
                    .error(R.drawable.iv_error) //设置错误图片
                    .dontAnimate() //不显示动画效果
                    .into(mMyActivitesDetailsImage);

            mTvNickname.setText(UserUtils.sDataBean.getNickname());
            String my_url = Constant.BASE_IMG + UserUtils.sDataBean.getHead_portrait();
            Glide.with(ActivitiesDetailsActivity.this)
                    .load(my_url)
                    .placeholder(R.drawable.iv_error) //设置占位图
                    .error(R.drawable.iv_error) //设置错误图片
                    .dontAnimate() //不显示动画效果
                    .into(mIvIcon);

        }

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
