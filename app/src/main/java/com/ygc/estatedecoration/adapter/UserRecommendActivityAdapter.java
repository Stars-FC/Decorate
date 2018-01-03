package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.FindActivitesBean;
import com.ygc.estatedecoration.entity.base.Constant;

import java.util.List;

/**
 * 推荐活动
 */

public class UserRecommendActivityAdapter extends BaseQuickAdapter<FindActivitesBean.DataBean, BaseViewHolder> {

    public UserRecommendActivityAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindActivitesBean.DataBean bean) {
        helper.setText(R.id.tv_recommend_title, bean.getTitle());
        helper.setText(R.id.tv_recommend_nick, bean.getUserInfo().getNickname());

        String head_portrait = Constant.BASE_IMG + bean.getPicture_url();
        Glide.with(mContext)
                .load(head_portrait)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.tv_recommend_cover_picture));
    }
}
