package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/20.
 * 主页-交易管理
 */

public class HomeTransactionManageDetailAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HomeTransactionManageDetailAdapter(List<String> data) {
        super(R.layout.item_home_transactionmanage, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}