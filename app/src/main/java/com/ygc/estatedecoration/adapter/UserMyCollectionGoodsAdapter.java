package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.UserCollectionMaterialBean;
import com.ygc.estatedecoration.entity.base.Constant;

/**
 * Created by FC on 2017/11/13.
 * 我的收藏-商品
 */

public class UserMyCollectionGoodsAdapter extends BaseQuickAdapter<UserCollectionMaterialBean.DataBean, BaseViewHolder> {

    public UserMyCollectionGoodsAdapter() {
        super(R.layout.item_user_my_collection_materials);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCollectionMaterialBean.DataBean bean) {
        helper.setText(R.id.tv_detail_cm_name, bean.getArticle().getCm_name());
        helper.setText(R.id.tv_detail_cm_price, "¥ " + bean.getArticle().getCm_price());
        helper.setText(R.id.tv_detail_sale_num, bean.getArticle().getSale_num() + "人付款");//付款人数

        String detail_picture = Constant.BASE_IMG + bean.getArticle().getDetail_picture();

        if (detail_picture.contains(",")) {//判断字符串中是否含有‘，’
            detail_picture = detail_picture.substring(0, detail_picture.indexOf(","));//截取‘，’前面的字符串
        }

        Glide.with(mContext)
                .load(detail_picture)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_detail_picture));
    }
}
