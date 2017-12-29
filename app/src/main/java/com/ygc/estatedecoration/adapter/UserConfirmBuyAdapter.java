package com.ygc.estatedecoration.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.user_activity.ConfirmBuyActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户端-确认购买页面的适配器
 */

public class UserConfirmBuyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private RecyclerView mChildRecyclerView;

    private UserConfirmBuyChildAdapter mAdapter;

    public UserConfirmBuyAdapter(List<String> data) {
        super(R.layout.item_user_confirm_buy, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        mChildRecyclerView = (RecyclerView) helper.getView(R.id.recyclerview);

        setChildAdapter();
    }

    private void setChildAdapter() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("" + i);
        }
        mAdapter = new UserConfirmBuyChildAdapter(list);

        mChildRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mChildRecyclerView.setAdapter(mAdapter);

        mChildRecyclerView.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mContext, "position__" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}