package com.ygc.estatedecoration.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.ContractInfoBean;

public class ProjectMainStageAdapter extends BaseQuickAdapter<ContractInfoBean.DataBean.ContractStageListBean, BaseViewHolder> {

    public ProjectMainStageAdapter() {
        super(R.layout.item_project_stage);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContractInfoBean.DataBean.ContractStageListBean item) {
        helper.setText(R.id.stage_name_tv, item.getTitle());
        helper.setText(R.id.stage_content_tv, item.getDetail());
        helper.setText(R.id.payMoney_tv, item.getPrice()+"");
        helper.setText(R.id.start_time_et, item.getStageStartTime());
        helper.setText(R.id.end_time_et, item.getStageEndTime());
        helper.setText(R.id.needDays_tv, item.getNeedDays()+"");
        helper.getView(R.id.edit_iv).setVisibility(View.GONE);
    }
}
