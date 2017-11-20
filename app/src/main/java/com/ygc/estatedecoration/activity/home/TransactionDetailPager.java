package com.ygc.estatedecoration.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeTransactionManageDetailAdapter;
import com.ygc.estatedecoration.base.BasePager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/11/20.
 */

public class TransactionDetailPager extends BasePager {

    private RecyclerView mRecyclerView;
    private HomeTransactionManageDetailAdapter mAdapter;

    public TransactionDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.recyclerview, null);

        mRecyclerView = view.findViewById(R.id.recyclerview);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        List<String> list = new ArrayList<>(); //（请求网络获取到的数据集合）
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        mAdapter = new HomeTransactionManageDetailAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));


        //跳转到详细界面
//        Intent intent = new Intent(mContext, TransactionManageDetailActivity.class);
//        mContext.startActivity(intent);

    }
}
