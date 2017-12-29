package com.ygc.estatedecoration.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

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
        ImageView imageView = (ImageView) helper.getView(R.id.iv_design_image);
    }
}
