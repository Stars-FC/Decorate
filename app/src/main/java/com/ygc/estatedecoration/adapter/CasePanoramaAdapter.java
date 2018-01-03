package com.ygc.estatedecoration.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.PanoramaBean;
import com.ygc.estatedecoration.entity.base.Constant;

public class CasePanoramaAdapter extends BaseQuickAdapter<PanoramaBean.DataBean, BaseViewHolder> {


    public CasePanoramaAdapter() {
        super(R.layout.item_case_panorama);
    }

    @Override
    protected void convert(BaseViewHolder helper, PanoramaBean.DataBean item) {
        helper.setText(R.id.title_tv, item.getTitle());
        helper.setText(R.id.city_tv, item.getCity());
        helper.setText(R.id.housing_tv, item.getAddress());
        helper.setText(R.id.huxing_tv, item.getHouse_type());

        ImageView mIv = helper.getView(R.id.pic_iv);
        String static_picture = item.getStatic_picture();
        if (TextUtils.isEmpty(static_picture)) {
//            mIv.setImageResource();
        } else {
            if (static_picture.contains(",")) {
                String[] split = static_picture.split(",");
                Glide.with(mContext).load(Constant.BASE_IMG + split[0]).into(mIv);
            } else {
                Glide.with(mContext).load(Constant.BASE_IMG + static_picture).into(mIv);
            }
        }
    }
}
