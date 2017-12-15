package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.MyBrightBean;
import com.ygc.estatedecoration.entity.base.Constant;

import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 我的-我的亮点
 */

public class MyBrightAdapter extends BaseQuickAdapter<MyBrightBean.DataBean, BaseViewHolder> {

    public MyBrightAdapter() {
        super(R.layout.item_my_bright_activity);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBrightBean.DataBean item) {
        helper.setText(R.id.my_bright_tv_introduce, item.getUs_title());
        ImageView imageView = (ImageView) helper.getView(R.id.my_bright_iv_show);
        String us_picture = Constant.BASE_IMG + item.getUs_picture();

        Glide.with(mContext)
                .load(us_picture)
                .placeholder(R.mipmap.ic_launcher) //设置占位图
                .error(R.mipmap.ic_launcher) //设置错误图片
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                //.dontAnimate() //不显示动画效果
                .into(imageView);
        ;
    }
}
