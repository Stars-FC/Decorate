package com.ygc.estatedecoration.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.FindAllTypeBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.user_activity.UserLookStoreActivity;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.widget.CircleImageView;

import java.util.List;

public class UserFindSupervisorDetailAdapter extends BaseQuickAdapter<FindAllTypeBean.DataBean, BaseViewHolder> {
    private int mark;
    private List<String> dataList;
    private RecyclerView mRecyclerView;

    public UserFindSupervisorDetailAdapter(@LayoutRes int layoutResId, int mark) {
        super(layoutResId);
        this.mark = mark;
    }

    @Override
    protected void convert(BaseViewHolder helper, FindAllTypeBean.DataBean bean) {
       /* TextView nameTv = helper.getView(R.id.name_tv);
        TextView experienceTv = helper.getView(R.id.experience_tv);
        if (mark == 1) {
            nameTv.setText("施工团队名称");
            experienceTv.setText("10年施工经验");
        }*/
       /* RecyclerView recyclerView = helper.getView(R.id.recyclerview);
        UserFindSupervisorDetailChildAdapter adapter = new UserFindSupervisorDetailChildAdapter(R.layout.item_user_home_fragment_find_supervisor_more_child, dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);*/

        //-------------------------------------------------------------------------------------------------------

        CircleImageView headIv = (CircleImageView) helper.getView(R.id.iv_server_icom);

        helper.setText(R.id.tv_server_name, bean.getS_name());
        helper.setText(R.id.tv_server_work_year, bean.getWork_year() + "年工作经验");
//        helper.setText(R.id.tv_server_type, "");（个体、团体）
//        helper.setText(R.id.tv_server_house_type, "");(设计师：擅长普通户型)
        helper.setText(R.id.tv_server_place, bean.getS_province() + " " + bean.getS_city());//服务商信息
        helper.setText(R.id.tv_server_bid_num, bean.getBid_num() + "");

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

        mRecyclerView = helper.getView(R.id.recyclerview);
        dataList = MyPublic.convertStrToArray(bean.getStatic_picture());//图片url地址集合

        setAdapter();
    }

    private void setAdapter() {
        ImageAdapter imageAdapter = new ImageAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(imageAdapter);
    }
}
