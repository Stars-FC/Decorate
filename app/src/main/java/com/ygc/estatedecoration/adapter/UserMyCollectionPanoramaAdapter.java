package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.UserCollectionPanoramaBean;
import com.ygc.estatedecoration.entity.base.Constant;

/**
 * Created by FC on 2017/11/13.
 * 我的收藏- 全景图
 */

public class UserMyCollectionPanoramaAdapter extends BaseQuickAdapter<UserCollectionPanoramaBean.DataBean, BaseViewHolder> {

    public UserMyCollectionPanoramaAdapter() {
        super(R.layout.item_case_panorama);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCollectionPanoramaBean.DataBean bean) {
        helper.setText(R.id.tv_panorama_title, bean.getArticle().getTitle());
        helper.setText(R.id.tv_panorama_city, bean.getArticle().getCity());
        helper.setText(R.id.tv_panorama_address, bean.getArticle().getAddress());
        helper.setText(R.id.tv_panorama_house_type, bean.getArticle().getHouse_type());

        String dynamic_picture = Constant.BASE_IMG+ bean.getArticle().getDynamic_picture();
        Glide.with(mContext)
                .load(dynamic_picture)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .into((ImageView) helper.getView(R.id.iv_static_picture));
    }
}
