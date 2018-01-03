package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.FindMaterialBean;
import com.ygc.estatedecoration.entity.base.Constant;

import java.util.List;

/**
 * 找材料
 */
public class UserFindMaterialAdapter extends BaseQuickAdapter<FindMaterialBean.DataBean, BaseViewHolder> {

    public UserFindMaterialAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindMaterialBean.DataBean bean) {
        helper.setText(R.id.materials_tv_cm_name, bean.getCm_name());
        helper.setText(R.id.materials_tv_cm_price, "￥ " + bean.getCm_price());
        helper.setText(R.id.materials_tv_sale_num, bean.getSale_num() + "");

        String cover_picture = Constant.BASE_IMG + bean.getCover_picture();

        if (cover_picture.contains(",")) {//判断字符串中是否含有‘，’
            cover_picture = cover_picture.substring(0, cover_picture.indexOf(","));//截取‘，’前面的字符串
        }

        Glide.with(mContext)
                .load(cover_picture)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .into((ImageView) helper.getView(R.id.materials_iv_cover_picture));
    }
}
