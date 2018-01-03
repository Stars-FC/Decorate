package com.ygc.estatedecoration.adapter;

import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.EffectBean;
import com.ygc.estatedecoration.entity.base.Constant;

public class CaseEffectAdapter extends BaseQuickAdapter<EffectBean.DataBean, BaseViewHolder> {

    public CaseEffectAdapter() {
        super(R.layout.item_case_effect);
    }

    @Override
    protected void convert(BaseViewHolder helper, EffectBean.DataBean item) {
        RoundedImageView roundedImageView = helper.getView(R.id.pic_iv);
        String effect_picture = item.getEffect_picture();
        String picUrlStr = null;
        if (!TextUtils.isEmpty(effect_picture)) {
            if (effect_picture.contains(",")) {
                String[] split = effect_picture.split(",");
                picUrlStr = split[0];
            } else {
                picUrlStr = effect_picture;
            }
        }

        if (TextUtils.isEmpty(picUrlStr)) {
//            roundedImageView.setImageResource();
        } else {
            Glide.with(mContext).load(Constant.BASE_IMG + picUrlStr).into(roundedImageView);
        }
        helper.setText(R.id.title_tv, item.getTitle());
    }
}
