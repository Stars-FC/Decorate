package com.ygc.estatedecoration.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.EditMainContractActivity;
import com.ygc.estatedecoration.activity.home.LookBuChongContractActivity;
import com.ygc.estatedecoration.activity.home.LookMainContractActivity;
import com.ygc.estatedecoration.activity.home.SupplementaryContractActivity;
import com.ygc.estatedecoration.bean.ScheduleBean;

import java.util.List;

public class ContractAdapter1 extends BaseQuickAdapter<ScheduleBean.DataBeanX.DataBean, BaseViewHolder> {

    List<ScheduleBean.DataBeanX.DataBean> data;
    public ContractAdapter1(@Nullable List<ScheduleBean.DataBeanX.DataBean> data) {
        super(R.layout.item_contract_stage, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ScheduleBean.DataBeanX.DataBean item) {
        final int layoutPosition = helper.getLayoutPosition();
        ((TextView) helper.getView(R.id.time_tv)).setText(item.getTime());
        ((TextView)helper.getView(R.id.content_tv)).setText(item.getDetail());
        TextView operateContractTv = helper.getView(R.id.operate_contract_tv);
        final String id = item.getConId();
        final String rcId = item.getRcId();
        final int con_state = item.getConState();
        final int rc_state = item.getRcState();
        if (layoutPosition == 0) {
            if (con_state == 0) {
                operateContractTv.setText("编辑合同");
            } else {
                operateContractTv.setText("查看合同");
            }
        } else {
            if (rc_state == 0) {
                operateContractTv.setText("编辑合同");
            } else {
                operateContractTv.setText("查看合同");
            }
        }
        operateContractTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (layoutPosition == 0) {
                    if (con_state == 0) {
                        intent.setClass(mContext, EditMainContractActivity.class);
                    } else {
                        intent.setClass(mContext, LookMainContractActivity.class);
                    }
                    intent.putExtra("conId", id);
                } else {
                    if (rc_state == 0) {
                        intent.setClass(mContext, SupplementaryContractActivity.class);
                        intent.putExtra("mark", "2");
                    } else {
                        intent.setClass(mContext, LookBuChongContractActivity.class);
                    }
                    intent.putExtra("conId", id);
                    intent.putExtra("rcId", rcId);
                }
                mContext.startActivity(intent);
            }
        });
    }
}
