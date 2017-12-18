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
import com.ygc.estatedecoration.bean.UserShopCarBean;

import java.util.List;

public class UserShopCarAdapter extends BaseQuickAdapter<UserShopCarBean.DataBean, BaseViewHolder> {

    public UserShopCarAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserShopCarBean.DataBean item) {
        int layoutPosition = helper.getLayoutPosition();
        RecyclerView recyclerView = helper.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new UserShopCarCommodityDetailAdapter(R.layout.item_shop_car_commodity_detail, item.getCommodityCarItem(), mContext, layoutPosition));

        // TODO: 2017/12/18 为控件赋值，还有子RecyclerView的值
        //为控件赋值
//        helper.setText(R.id.)

        View view = helper.getView(R.id.view);
        if (getData().size() - 1 == layoutPosition) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }
}
