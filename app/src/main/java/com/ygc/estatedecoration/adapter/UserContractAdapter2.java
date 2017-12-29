package com.ygc.estatedecoration.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.UserProjectProgressBean;

import java.util.List;

public class UserContractAdapter2 extends BaseQuickAdapter<UserProjectProgressBean.DataBeanX.ContractStageListBean, BaseViewHolder> {

    public UserContractAdapter2(@Nullable List<UserProjectProgressBean.DataBeanX.ContractStageListBean> data) {
        super(R.layout.item_contract_stage, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserProjectProgressBean.DataBeanX.ContractStageListBean item) {
        ((TextView) helper.getView(R.id.time_tv)).setText(item.getCreateTime());
        ((TextView)helper.getView(R.id.content_tv)).setText(item.getDetail());
        TextView operateContractTv = helper.getView(R.id.operate_contract_tv);
        String conId = item.getConId();
        int csState = item.getCsState();
        if (csState == 0) {
            operateContractTv.setVisibility(View.GONE);
        } else if (csState == 1) {
            operateContractTv.setVisibility(View.VISIBLE);
            operateContractTv.setText("确认验收");
            operateContractTv.setBackgroundResource(R.drawable.shape_hollow_rounded_rectangle);
        } else if (csState == 2) {
            operateContractTv.setVisibility(View.VISIBLE);
            operateContractTv.setText("已验收");
            operateContractTv.setBackgroundResource(R.drawable.shape_hollow_rounded_rectangle2);
        }
        operateContractTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
