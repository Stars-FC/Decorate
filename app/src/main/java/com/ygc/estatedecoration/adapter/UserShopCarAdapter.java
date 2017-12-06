package com.ygc.estatedecoration.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

public class UserShopCarAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;
    private List<String> dataList;
    public UserShopCarAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
        this.dataList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int layoutPosition = helper.getLayoutPosition();
        RecyclerView recyclerView = helper.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new UserShopCarCommodityDetailAdapter(R.layout.item_shop_car_commodity_detail, dataList, mContext, layoutPosition));

        View view = helper.getView(R.id.view);
        if (dataList.size() - 1 == layoutPosition) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }
}
