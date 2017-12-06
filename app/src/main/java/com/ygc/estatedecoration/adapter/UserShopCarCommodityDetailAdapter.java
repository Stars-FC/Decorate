package com.ygc.estatedecoration.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

public class UserShopCarCommodityDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;
    private int parentLayoutPosition;
    public UserShopCarCommodityDetailAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, Context context, int parentLayoutPosition) {
        super(layoutResId, data);
        this.mContext = context;
        this.parentLayoutPosition = parentLayoutPosition;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int childLayoutPosition = helper.getLayoutPosition();

        LinearLayout bottomLayout = helper.getView(R.id.bottom_layout);
        if (parentLayoutPosition == childLayoutPosition) {
            bottomLayout.setVisibility(View.GONE);
        } else {
            bottomLayout.setVisibility(View.VISIBLE);
        }

    }
}
