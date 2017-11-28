package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 主页适配器
 */

public class UserMyCollectionGoodsAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public UserMyCollectionGoodsAdapter(List<String> list) {
        super(R.layout.item_user_my_collection_materials, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
