package com.ygc.estatedecoration.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.LookMainContractActivity;
import com.ygc.estatedecoration.bean.UserProjectProgressBean;

import java.util.List;

public class UserContractAdapter1 extends BaseQuickAdapter<UserProjectProgressBean.DataBeanX.DataBean, BaseViewHolder> {

    List<UserProjectProgressBean.DataBeanX.DataBean> data;
    public UserContractAdapter1(@Nullable List<UserProjectProgressBean.DataBeanX.DataBean> data) {
        super(R.layout.item_contract_stage, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserProjectProgressBean.DataBeanX.DataBean item) {
        final int layoutPosition = helper.getLayoutPosition();
        ((TextView) helper.getView(R.id.time_tv)).setText(item.getTime());
        ((TextView)helper.getView(R.id.content_tv)).setText(item.getDetail());
        TextView operateContractTv = helper.getView(R.id.operate_contract_tv);
        operateContractTv.setText("查看合同");
        final String conId = item.getCon_id();
        operateContractTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (layoutPosition == 0) {
                    intent.setClass(mContext, LookMainContractActivity.class);
                } else {
                    intent.setClass(mContext, LookMainContractActivity.class);
                }
                intent.putExtra("conId", conId);
                mContext.startActivity(intent);
            }
        });
    }
}
