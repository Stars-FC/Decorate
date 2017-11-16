package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/16.
 */

public class MyVisitorAdapter extends BaseSectionQuickAdapter<SectionEntity, BaseViewHolder> {

    public MyVisitorAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionEntity item) {
        helper.setBackgroundRes(R.id.iviviv, R.mipmap.ic_launcher);

    }

    @Override
    protected void convert(BaseViewHolder helper, SectionEntity item) {
        for (int i = 0; i < 3; i++) {
            helper.setText(R.id.tvtvtv, "Test-"+i);
        }

    }
}
