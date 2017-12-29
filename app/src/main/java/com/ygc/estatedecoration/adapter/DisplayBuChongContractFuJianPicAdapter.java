package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.entity.base.Constant;

import java.util.List;

public class DisplayBuChongContractFuJianPicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<String> urlList;
    public DisplayBuChongContractFuJianPicAdapter(@LayoutRes int layoutResId, List<String> urlList) {
        super(layoutResId);
        this.urlList = urlList;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int layoutPosition = helper.getLayoutPosition();
        ImageView picIv = helper.getView(R.id.upload_effect_pic_iv);
        Glide.with(mContext).load(Constant.BASE_IMG + urlList.get(layoutPosition)).into(picIv);
    }
}
