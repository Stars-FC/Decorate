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
import com.ygc.estatedecoration.widget.StarView;

import java.util.List;

public class UserFindDesignerAdapter extends BaseQuickAdapter<FindAllTypeBean.DataBean, BaseViewHolder> {

    private int num;

    public UserFindDesignerAdapter(@LayoutRes int layoutResId, int num) {
        super(layoutResId);
        this.num = num;
    }

    @Override
    protected void convert(BaseViewHolder helper, final FindAllTypeBean.DataBean bean) {
        CircleImageView headIv = (CircleImageView) helper.getView(R.id.iv_server_icom);
        ImageView imageView = (ImageView) helper.getView(R.id.iv_service_picture);

        //跳转到商家店铺界面
        helper.getView(R.id.server_info_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String au_id = bean.getAu_id();//将所查看的服务行id传递给，查看店铺界面（作为请求查看店铺的参数au_id）
                Intent intent = new Intent(mContext, UserLookStoreActivity.class);
                intent.putExtra("service_auid", au_id);
                mContext.startActivity(intent);
            }
        });

        helper.setText(R.id.tv_server_name, bean.getS_name());

        helper.setText(R.id.tv_server_place, bean.getS_province() + " " + bean.getS_city());//服务商信息
        helper.setText(R.id.tv_server_bid_num, bean.getBid_num() + "");
        helper.setText(R.id.tv_server_work_year, bean.getWork_year() + "年工作经验");

        // TODO: 2018/1/5 星星个数 （等待后台更新）
        StarView starView = (StarView) helper.getView(R.id.starview);
//        starView.setStar();
        // TODO: 2018/1/5 设置类型
        helper.setText(R.id.tv_server_type, "");//（个体、团体）
        // TODO: 2018/1/5 找设计显示对应字段，找监理、找施工不显示
        if (num == 1) {
            helper.setText(R.id.tv_server_house_type, "");//(设计师：擅长普通户型)
        } else {
            helper.setText(R.id.tv_server_house_type, "");
        }

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
