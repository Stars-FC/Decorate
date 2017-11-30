package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 我的-设置-银行卡管理适配器
 */

public class MySettingBankCardAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public MySettingBankCardAdapter(List<String> list) {
        super(R.layout.item_my_setting_bank_card, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
