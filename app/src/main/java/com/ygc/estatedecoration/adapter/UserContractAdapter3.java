package com.ygc.estatedecoration.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.UserProjectProgressBean;

import java.util.List;

public class UserContractAdapter3 extends BaseQuickAdapter<UserProjectProgressBean.DataBeanX.ReplenishContractListBean, BaseViewHolder> {

    private List<UserProjectProgressBean.DataBeanX.ReplenishContractListBean> data;
    public UserContractAdapter3(@Nullable List<UserProjectProgressBean.DataBeanX.ReplenishContractListBean> data) {
        super(R.layout.item_contract_stage, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserProjectProgressBean.DataBeanX.ReplenishContractListBean item) {
        ((TextView) helper.getView(R.id.time_tv)).setText(item.getCreateTime());
        ((TextView)helper.getView(R.id.content_tv)).setText(item.getDetail());
        TextView operateContractTv = helper.getView(R.id.operate_contract_tv);
        operateContractTv.setText("发起验收");
        operateContractTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
