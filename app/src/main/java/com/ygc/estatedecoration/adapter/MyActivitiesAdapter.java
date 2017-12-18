package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.MyActivitesBean;
import com.ygc.estatedecoration.entity.base.Constant;

import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 我的-我的亮点
 */

public class MyActivitiesAdapter extends BaseQuickAdapter<MyActivitesBean.DataBean, BaseViewHolder> {

    public MyActivitiesAdapter() {
        super(R.layout.item_my_activity_activities);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyActivitesBean.DataBean item) {
        helper.setText(R.id.activites_title, item.getTitle());
        ImageView imageView = (ImageView) helper.getView(R.id.activites_image);
        String picture_url = Constant.BASE_IMG + item.getPicture_url();
        Glide.with(mContext)
                .load(picture_url)
                .placeholder(R.mipmap.ic_launcher) //设置占位图
                .error(R.mipmap.ic_launcher) //设置错误图片
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                //.dontAnimate() //不显示动画效果
                .into(imageView);
    }
}
