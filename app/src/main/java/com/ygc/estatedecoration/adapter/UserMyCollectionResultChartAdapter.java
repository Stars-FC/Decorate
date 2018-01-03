package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.UserCollectionResultChartBean;
import com.ygc.estatedecoration.entity.base.Constant;

import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 我的收藏-效果图
 */

public class UserMyCollectionResultChartAdapter extends BaseQuickAdapter<UserCollectionResultChartBean.DataBean, BaseViewHolder> {

    public UserMyCollectionResultChartAdapter() {
        super(R.layout.item_case_effect);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCollectionResultChartBean.DataBean bean) {
        helper.setText(R.id.title_tv, bean.getArticle().getTitle());
        String effect_picture = Constant.BASE_IMG + bean.getArticle().getEffect_picture();
        Glide.with(mContext)
                .load(effect_picture)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.pic_iv));
    }
}
