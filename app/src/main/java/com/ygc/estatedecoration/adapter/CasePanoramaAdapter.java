package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.PanoramaBean;

public class CasePanoramaAdapter extends BaseQuickAdapter<PanoramaBean.DataBean, BaseViewHolder> {


    public CasePanoramaAdapter() {
        super(R.layout.item_case_panorama);
    }

    @Override
    protected void convert(BaseViewHolder helper, PanoramaBean.DataBean item) {

    }
}
