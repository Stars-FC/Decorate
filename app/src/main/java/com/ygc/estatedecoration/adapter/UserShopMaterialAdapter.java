package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.ShopRecommendMaterialsBean;
import com.ygc.estatedecoration.entity.base.Constant;

public class UserShopMaterialAdapter extends BaseQuickAdapter<ShopRecommendMaterialsBean.DataBean, BaseViewHolder> {

    public UserShopMaterialAdapter() {
        super(R.layout.item_user_shop_materials);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopRecommendMaterialsBean.DataBean item) {
        helper.setText(R.id.materials_name_tv, item.getCm_name());
        helper.setText(R.id.price_tv, "￥ " + item.getCm_price());
        helper.setText(R.id.sale_num_tv, "销量 " + item.getSale_num());

        ImageView coverIv = helper.getView(R.id.cover_iv);
        Glide.with(mContext).load(Constant.BASE_IMG + item.getCover_picture()).into(coverIv);
    }
}
