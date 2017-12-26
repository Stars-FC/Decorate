package com.ygc.estatedecoration.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
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
        int layoutPosition = helper.getLayoutPosition();
        ((TextView) helper.getView(R.id.time_tv)).setText(item.getTime());
        ((TextView)helper.getView(R.id.content_tv)).setText(item.getDetail());
        TextView operateContractTv = helper.getView(R.id.operate_contract_tv);
        final String con_id;
        if (layoutPosition == 0) {
            con_id = item.getCon_id();
            int con_state = item.getCon_state();
            if (con_state == 0) {
                operateContractTv.setText("编辑合同");
            } else {
                operateContractTv.setText("查看合同");
            }
        } else {
            con_id = item.getRc_id();
            int rc_state = item.getRc_state();
            if (rc_state == 0) {
                operateContractTv.setText("编辑合同");
            } else {
                operateContractTv.setText("查看合同");
            }
        }
        operateContractTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/12/21 处理con_id
//                Intent intent = new Intent(mContext, )
            }
        });
    }
}
