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
        super(R.layout.item_bind_photo_material_dealer, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_material_dealer_show, item);
    }
}
