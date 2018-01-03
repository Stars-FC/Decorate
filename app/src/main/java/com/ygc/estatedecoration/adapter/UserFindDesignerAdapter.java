package com.ygc.estatedecoration.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.FindAllTypeBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.user_activity.UserLookStoreActivity;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.widget.CircleImageView;

import java.util.List;

public class UserFindDesignerAdapter extends BaseQuickAdapter<FindAllTypeBean.DataBean, BaseViewHolder> {

    public UserFindDesignerAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindAllTypeBean.DataBean bean) {
        CircleImageView headIv = (CircleImageView) helper.getView(R.id.iv_server_icom);
        ImageView imageView = (ImageView) helper.getView(R.id.iv_service_picture);

        helper.getView(R.id.server_info_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, UserLookStoreActivity.class));
            }
        });

        helper.setText(R.id.tv_server_name, bean.getS_name());
//        helper.setText(R.id.tv_server_type, "");（个体、团体）
        helper.setText(R.id.tv_server_place, bean.getS_province() + " " + bean.getS_city());//服务商信息
        helper.setText(R.id.tv_server_bid_num, bean.getBid_num() + "");
        helper.setText(R.id.tv_server_work_year, bean.getWork_year() + "年工作经验");
//        helper.setText(R.id.tv_server_house_type, "");(设计师：擅长普通户型)

        helper.setText(R.id.tv_city, bean.getCity());
        helper.setText(R.id.tv_address, bean.getAddress());
        helper.setText(R.id.tv_house_type, bean.getHouse_type());

        String s_logo = Constant.BASE_IMG + bean.getS_logo();
        String static_picture = Constant.BASE_IMG + bean.getStatic_picture();

        Glide.with(mContext)
                .load(s_logo)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .dontAnimate()
                .into(headIv);

        if (static_picture.contains(",")) {//判断字符串中是否含有‘，’
            static_picture = static_picture.substring(0, static_picture.indexOf(","));//截取‘，’前面的字符串
        }
        Glide.with(mContext)
                .load(static_picture)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .dontAnimate()
                .into(imageView);
    }
}
