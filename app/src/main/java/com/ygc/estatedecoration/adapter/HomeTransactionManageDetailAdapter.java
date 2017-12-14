package com.ygc.estatedecoration.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.NeedBean;

public class HomeTransactionManageDetailAdapter extends BaseQuickAdapter<NeedBean.DataBean, BaseViewHolder> {

    public HomeTransactionManageDetailAdapter() {
        super(R.layout.item_home_transactionmanage);
    }

    @Override
    protected void convert(BaseViewHolder helper, NeedBean.DataBean item) {

        //需求标题
        TextView demandTitleTv = helper.getView(R.id.demandTitle_tv);

        //装修类型
        TextView missionTypeTv = helper.getView(R.id.missionType_tv);
        String missionType = item.getMissionType();
        missionTypeTv.setText(missionType.equals("0") ? "家装" : "工装");

        //需求分析
        TextView demandDetailsTv = helper.getView(R.id.demandDetails_tv);
        String demandDetails = item.getDemandDetails();
        demandDetailsTv.setText(demandDetails);

        //报价
        TextView offerTv = helper.getView(R.id.offer_tv);
        int offer = item.getOffer();
        offerTv.setText(String.valueOf(offer));

        //建筑现状
        TextView constructionStatusQuoTv = helper.getView(R.id.constructionStatusQuo_tv);
        int constructionStatusQuo = item.getConstructionStatusQuo();
        switch (constructionStatusQuo) {
            case 0:
                constructionStatusQuoTv.setText("局部改造");
                break;
            case 1:
                constructionStatusQuoTv.setText("毛坯房");
                break;
            case 2:
                constructionStatusQuoTv.setText("旧房翻新");
                break;
        }

        //建筑面积
        TextView buildingAreaTv = helper.getView(R.id.buildingArea_tv);
        int buildingArea = item.getBuildingArea();
        buildingAreaTv.setText(buildingArea + "㎡");

        //地址
        TextView addressTv = helper.getView(R.id.address_tv);
        String address = item.getAddress();
        addressTv.setText(address);

        //订单号
        TextView orderIdTv = helper.getView(R.id.did_tv);
        int dId = item.getDId();
        orderIdTv.setText(String.valueOf(dId));

    }
}