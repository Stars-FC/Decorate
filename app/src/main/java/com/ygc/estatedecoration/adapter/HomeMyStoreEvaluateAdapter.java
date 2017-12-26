package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.UserCommentBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.widget.StarView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的商铺-评价子页面的RecyclerView的适配器
 */

public class HomeMyStoreEvaluateAdapter extends BaseQuickAdapter<UserCommentBean.DataBean, BaseViewHolder> {

    public HomeMyStoreEvaluateAdapter() {
        super(R.layout.item_home_mystore_evaluate);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCommentBean.DataBean bean) {
        StarView starView = (StarView) helper.getView(R.id.starview);
        ImageView imageView = (ImageView) helper.getView(R.id.iv_icon_show);
        starView.setClickable(false);//设置不可点击
        starView.setStar(Integer.parseInt(bean.getStarLeval()));//设置显示的星星个数

        helper.setText(R.id.tv_nick_show, bean.getCreator().getNickname());
        helper.setText(R.id.tv_time_show, bean.getCreateTime());
        helper.setText(R.id.tv_evaluate_show, bean.getCotent());

        String picture_url = Constant.BASE_IMG + bean.getCreator().getPicture_url();

        Glide.with(mContext)
                .load(picture_url)
                .placeholder(R.drawable.iv_error) //设置占位图
                .error(R.drawable.iv_error) //设置错误图片
                .dontAnimate() //不显示动画效果
                .into(imageView);
    }
}
