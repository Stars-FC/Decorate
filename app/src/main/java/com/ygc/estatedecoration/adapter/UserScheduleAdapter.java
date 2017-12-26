package com.ygc.estatedecoration.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.UserProjectProgressBean;

import java.util.List;

public class UserScheduleAdapter extends BaseQuickAdapter<UserProjectProgressBean.DataBeanX, BaseViewHolder> {

    public UserScheduleAdapter() {
        super(R.layout.item_schedule);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserProjectProgressBean.DataBeanX item) {
        TextView titleTv = helper.getView(R.id.title_tv);
        LinearLayout timeAndContentLl = helper.getView(R.id.time_and_content_ll);
        TextView timeTv = helper.getView(R.id.time_tv);
        TextView contentTv = helper.getView(R.id.content_tv);
        RecyclerView recyclerViewMainStage = helper.getView(R.id.stage_main_recyclerview);
        RecyclerView recyclerViewMinorStage = helper.getView(R.id.stage_minor_recyclerview);

        titleTv.setText(item.getTitle());
        if (helper.getLayoutPosition() == 0) {
            timeAndContentLl.setVisibility(View.VISIBLE);
            recyclerViewMainStage.setVisibility(View.GONE);
            recyclerViewMinorStage.setVisibility(View.GONE);
            timeTv.setText(item.getTime());
            contentTv.setText(item.getDetail());
        } else if (helper.getLayoutPosition() == 1) {
            timeAndContentLl.setVisibility(View.GONE);
            recyclerViewMinorStage.setVisibility(View.GONE);
            List<UserProjectProgressBean.DataBeanX.DataBean> dataBeanList = item.getData();
            if (dataBeanList != null && dataBeanList.size() > 0) {
                recyclerViewMainStage.setVisibility(View.VISIBLE);
                recyclerViewMainStage.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                recyclerViewMainStage.setAdapter(new UserContractAdapter1(dataBeanList));
            } else {
                recyclerViewMainStage.setVisibility(View.GONE);
            }
        } else if (helper.getLayoutPosition() == 2) {
            timeAndContentLl.setVisibility(View.GONE);
            recyclerViewMinorStage.setVisibility(View.VISIBLE);
            List<UserProjectProgressBean.DataBeanX.ContractStageListBean> contractStageList = item.getContractStageList();
            if (contractStageList != null && contractStageList.size() > 0) {
                recyclerViewMainStage.setVisibility(View.VISIBLE);
                recyclerViewMainStage.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                recyclerViewMainStage.setAdapter(new UserContractAdapter2(contractStageList));
            } else {
                recyclerViewMainStage.setVisibility(View.GONE);
            }
            List<UserProjectProgressBean.DataBeanX.ReplenishContractListBean> replenishContractList = item.getReplenishContractList();
            if (replenishContractList != null && replenishContractList.size() > 0) {
                recyclerViewMinorStage.setVisibility(View.VISIBLE);
                recyclerViewMinorStage.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                recyclerViewMinorStage.setAdapter(new UserContractAdapter3(replenishContractList));
            } else {
                recyclerViewMinorStage.setVisibility(View.GONE);
            }
        }
    }
}
