package com.ygc.estatedecoration.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.LogUtil;

import java.util.List;

/**
 * Created by FC on 2017/12/28.
 * 每个设计师……的图片
 */

public class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    protected ImageAdapter(List<String> list) {
        super(R.layout.item_user_find_design_image, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        String imageUrl = Constant.BASE_IMG + item;
        Glide.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_design_image));
    }
}
