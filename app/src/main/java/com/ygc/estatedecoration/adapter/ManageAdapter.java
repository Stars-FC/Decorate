package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/20.
 * 管理-适配器
 */

public class ManageAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ManageAdapter(List<String> data) {
        super(R.layout.item_home_transactionmanage, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}