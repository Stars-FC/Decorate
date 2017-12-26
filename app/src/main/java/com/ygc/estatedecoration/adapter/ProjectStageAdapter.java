package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.ProjectStageBean;

public class ProjectStageAdapter extends BaseQuickAdapter<ProjectStageBean.DataBean, BaseViewHolder> {

    public ProjectStageAdapter() {
        super(R.layout.item_project_stage);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectStageBean.DataBean item) {
        helper.setText(R.id.stage_name_tv, item.getProjectStageName());
        helper.setText(R.id.stage_content_tv, item.getProjectContent());
        helper.setText(R.id.payMoney_tv, item.getPayMoneyJinE());
        helper.setText(R.id.needDays_tv, item.getNeedDays());
    }
}
