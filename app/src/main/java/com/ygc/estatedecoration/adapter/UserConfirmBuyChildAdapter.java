package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * 用户端-确认购买页面子item的适配器
 */

public class UserConfirmBuyChildAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public UserConfirmBuyChildAdapter(List<String> data) {
        super(R.layout.item_user_confirm_buy_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}