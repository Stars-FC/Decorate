package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.entity.base.Constant;

import java.util.List;

public class TransactionManageNeedAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private List<String> data;
    public TransactionManageNeedAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView roundedImageView = helper.getView(R.id.icon_riv);
        Glide.with(mContext).load(Constant.BASE_IMG + data.get(helper.getLayoutPosition())).into(roundedImageView);
    }
}
