package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.entity.base.Constant;

public class UserCaseEffectDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public UserCaseEffectDetailAdapter() {
        super(R.layout.item_user_case_effect_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iconIv = helper.getView(R.id.icon_iv);
        Glide.with(mContext).load(Constant.BASE_IMG + item).into(iconIv);
    }
}
