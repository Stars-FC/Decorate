package com.ygc.estatedecoration.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.AddProjectStageActivity;
import com.ygc.estatedecoration.bean.ProjectStageBean;

public class ProjectStageAdapter extends BaseQuickAdapter<ProjectStageBean.DataBean, BaseViewHolder> {

    public ProjectStageAdapter() {
        super(R.layout.item_project_stage);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ProjectStageBean.DataBean item) {
        final int layoutPosition = helper.getLayoutPosition();
        helper.setText(R.id.stage_name_tv, item.getProjectStageName());
        helper.setText(R.id.stage_content_tv, item.getProjectContent());
        helper.setText(R.id.payMoney_tv, item.getPayMoneyJinE());
        helper.setText(R.id.start_time_et, item.getStartTime());
        helper.setText(R.id.end_time_et, item.getEndTime());
        helper.setText(R.id.needDays_tv, item.getNeedDays());
        helper.getView(R.id.edit_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddProjectStageActivity.class);
                intent.putExtra("dataBean", item);
                intent.putExtra("mark", "2");
                intent.putExtra("position", layoutPosition);
                mContext.startActivity(intent);
            }
        });
    }
}
