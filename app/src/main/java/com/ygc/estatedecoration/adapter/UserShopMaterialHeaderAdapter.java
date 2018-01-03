package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.CaseStyleBean;
import com.ygc.estatedecoration.entity.base.Constant;

public class UserShopMaterialHeaderAdapter extends BaseQuickAdapter<CaseStyleBean.DataBean, BaseViewHolder> {

    public UserShopMaterialHeaderAdapter() {
        super(R.layout.item_user_shop_find_materials_header);
    }

    @Override
    protected void convert(BaseViewHolder helper, CaseStyleBean.DataBean item) {
        ImageView iconIv = helper.getView(R.id.icon_iv);
        TextView titleTv = helper.getView(R.id.title_tv);
        titleTv.setText(item.getR_name());
        Glide.with(mContext).load(Constant.BASE_IMG + item.getR_picture()).into(iconIv);
    }
}
