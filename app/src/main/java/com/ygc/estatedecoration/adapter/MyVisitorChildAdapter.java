package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.VisiterBean;
import com.ygc.estatedecoration.entity.base.Constant;

import java.util.List;

/**
 * 我的访客子条目
 */

public class MyVisitorChildAdapter extends BaseQuickAdapter<VisiterBean.DataBean.ListBean.UserListBean, BaseViewHolder> {

    public MyVisitorChildAdapter() {
        super(R.layout.item_home_myvisitor_below);
    }

    @Override
    protected void convert(BaseViewHolder helper, VisiterBean.DataBean.ListBean.UserListBean bean) {
        //为子标题内容赋值
        helper.setText(R.id.tv_name, bean.getUserInfo().getNickname());
        helper.setText(R.id.tv_nowtime, bean.getUserInfo().getCreate_time());
        String image = Constant.BASE_IMG + bean.getUserInfo().getPicture_url();
        ImageView imageView = (ImageView) helper.getView(R.id.iv_image);//图片
        Glide.with(mContext)
                .load(image)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .dontAnimate()
                .into(imageView);
    }
}