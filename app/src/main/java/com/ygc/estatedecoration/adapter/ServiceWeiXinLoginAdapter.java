package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信登陆材料商item
 */

public class ServiceWeiXinLoginAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public ServiceWeiXinLoginAdapter(List<String> list) {
        super(R.layout.item_case_style, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.style_title_tv, item);
    }
}
