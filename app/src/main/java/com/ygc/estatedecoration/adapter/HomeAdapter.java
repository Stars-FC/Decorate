package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 主页适配器
 */

public class HomeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HomeAdapter(List<String> list) {
        super(R.layout.item_home, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
