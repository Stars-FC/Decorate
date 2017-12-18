package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.UserBalanceOrderBean;

import java.util.List;

public class AccountDetailAdapter extends BaseQuickAdapter<UserBalanceOrderBean.DataBean, BaseViewHolder> {

    public AccountDetailAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBalanceOrderBean.DataBean bean) {
        helper.setText(R.id.account_time, bean.getCreate_time() + "");//时间
        helper.setText(R.id.account_money, bean.getTotal_price() + "");//金额
    }
}
