package com.ygc.estatedecoration.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.ScheduleBean;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

public class ScheduleAdapter extends BaseQuickAdapter<ScheduleBean.DataBeanX, BaseViewHolder> {

    public ScheduleAdapter() {
        super(R.layout.item_schedule);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScheduleBean.DataBeanX item) {
        AutoLinearLayout bottomLl = helper.getView(R.id.bottom_ll);
        TextView circle = helper.getView(R.id.item_schedule_hollow_circle);
        TextView mTv_pingJia = helper.getView(R.id.operate_contract_tv);
        TextView titleTv = helper.getView(R.id.title_tv);
        LinearLayout timeAndContentLl = helper.getView(R.id.time_and_content_ll);
        TextView timeTv = helper.getView(R.id.time_tv);
        TextView contentTv = helper.getView(R.id.content_tv);
        RecyclerView recyclerViewMainStage = helper.getView(R.id.stage_main_recyclerview);
        RecyclerView recyclerViewMinorStage = helper.getView(R.id.stage_minor_recyclerview);

        titleTv.setText(item.getTitle());
        if (helper.getLayoutPosition() == 0) {
            circle.setBackgroundResource(R.drawable.shape_green_hollow_circle);
            bottomLl.setVisibility(View.VISIBLE);
            mTv_pingJia.setVisibility(View.INVISIBLE);
            timeAndContentLl.setVisibility(View.VISIBLE);
            recyclerViewMainStage.setVisibility(View.GONE);
            recyclerViewMinorStage.setVisibility(View.GONE);
            timeTv.setText(item.getTime());
            contentTv.setText(item.getDetail());
        } else if (helper.getLayoutPosition() == 1) {
            circle.setBackgroundResource(R.drawable.shape_green_hollow_circle);
            bottomLl.setVisibility(View.VISIBLE);
            mTv_pingJia.setVisibility(View.INVISIBLE);
            timeAndContentLl.setVisibility(View.GONE);
            recyclerViewMinorStage.setVisibility(View.GONE);
            List<ScheduleBean.DataBeanX.DataBean> dataBeanList = item.getData();
            if (dataBeanList != null && dataBeanList.size() > 0) {
                recyclerViewMainStage.setVisibility(View.VISIBLE);
                recyclerViewMainStage.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                recyclerViewMainStage.setAdapter(new ContractAdapter1(dataBeanList));
            } else {
                recyclerViewMainStage.setVisibility(View.GONE);
            }
        } else if (helper.getLayoutPosition() == 2) {
            circle.setBackgroundResource(R.drawable.shape_green_hollow_circle);
            bottomLl.setVisibility(View.VISIBLE);
            mTv_pingJia.setVisibility(View.INVISIBLE);
            timeAndContentLl.setVisibility(View.GONE);
            recyclerViewMinorStage.setVisibility(View.VISIBLE);
            List<ScheduleBean.DataBeanX.ContractStageListBean> contractStageList = item.getContractStageList();
            if (contractStageList != null && contractStageList.size() > 0) {
                recyclerViewMainStage.setVisibility(View.VISIBLE);
                recyclerViewMainStage.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                recyclerViewMainStage.setAdapter(new ContractAdapter2(contractStageList));
            } else {
                recyclerViewMainStage.setVisibility(View.GONE);
            }
            List<ScheduleBean.DataBeanX.ReplenishContractListBean> replenishContractList = item.getReplenishContractList();
            if (replenishContractList != null && replenishContractList.size() > 0) {
                recyclerViewMinorStage.setVisibility(View.VISIBLE);
                recyclerViewMinorStage.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                recyclerViewMinorStage.setAdapter(new ContractAdapter3(replenishContractList));
            } else {
                recyclerViewMinorStage.setVisibility(View.GONE);
            }
        } else if (helper.getLayoutPosition() == 3) {
            titleTv.setText("评价");
            bottomLl.setVisibility(View.GONE);
            mTv_pingJia.setVisibility(View.VISIBLE);
            circle.setBackgroundResource(R.drawable.shape_gray_hollow_circle);

            mTv_pingJia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
