package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.BankCardBean;

import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 我的-设置-银行卡管理适配器
 */

public class MySettingBankCardAdapter extends BaseQuickAdapter<BankCardBean.DataBean, BaseViewHolder> {

    public MySettingBankCardAdapter() {
        super(R.layout.item_my_setting_bank_card);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankCardBean.DataBean bean) {
        helper.setText(R.id.see_bank_number, bean.getBankNumber());
        helper.setText(R.id.see_bank_name, bean.getBankName());
    }
}
