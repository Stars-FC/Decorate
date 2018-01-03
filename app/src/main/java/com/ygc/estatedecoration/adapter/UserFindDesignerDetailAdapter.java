package com.ygc.estatedecoration.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.FindAllTypeBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.user_activity.UserLookStoreActivity;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 用户端-找设计师适配器
 */

public class UserFindDesignerDetailAdapter extends BaseQuickAdapter<FindAllTypeBean.DataBean, BaseViewHolder> {

    private List<String> mList;
    private RecyclerView mBetterRecyclerView;

    public UserFindDesignerDetailAdapter() {
        super(R.layout.item_user_find_design);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindAllTypeBean.DataBean bean) {

        mBetterRecyclerView = (RecyclerView) helper.getView(R.id.scrollrecyclerview);

        CircleImageView headIv = (CircleImageView) helper.getView(R.id.iv_server_icom);

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

        Glide.with(mContext)
                .load(s_logo)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .dontAnimate()
                .into(headIv);

        mList = MyPublic.convertStrToArray(bean.getStatic_picture());//图片url地址集合

        setAdapter();
    }

    private void setAdapter() {
        ImageAdapter imageAdapter = new ImageAdapter(mList);
        mBetterRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mBetterRecyclerView.setAdapter(imageAdapter);
    }
}
