package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.SupplementaryContractActivity;

import java.util.List;

import me.iwf.photopicker.PhotoPicker;

public class UploadBuChongContractPicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<String> data;
    private SupplementaryContractActivity context;
    public UploadBuChongContractPicAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, SupplementaryContractActivity context) {
        super(layoutResId, data);
        this.data = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView picIv = helper.getView(R.id.upload_effect_pic_iv);
        final int layoutPosition = helper.getLayoutPosition();
        if (layoutPosition == data.size() - 1) {
            picIv.setImageResource(R.drawable.fankui);
        } else {
            Glide.with(mContext).load(data.get(helper.getLayoutPosition())).into(picIv);
        }
        picIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutPosition == data.size() - 1) {
                    PhotoPicker.builder()
                            .setPhotoCount(9)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(context, PhotoPicker.REQUEST_CODE);
                }
            }
        });
    }
}
