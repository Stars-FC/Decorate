package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

public class UploadEffectPicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<String> data;

    public UploadEffectPicAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView picIv = helper.getView(R.id.upload_effect_pic_iv);
        Glide.with(mContext).load(data.get(helper.getLayoutPosition())).into(picIv);
    }
}
