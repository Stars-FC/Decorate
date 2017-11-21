package com.ygc.estatedecoration.activity.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeTransactionManageDetailAdapter;
import com.ygc.estatedecoration.base.BasePager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/11/20.
 * 交易管理的子页面
 */

public class TransactionDetailPager extends BasePager {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HomeTransactionManageDetailAdapter mAdapter;

    public TransactionDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.recyclerview, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);

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

//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(mContext,"mAdapter第" + position + "数据",Toast.LENGTH_SHORT).show();
//                //跳转到详细界面
//                Intent intent = new Intent(mContext, TransactionManageDetailActivity.class);
//                mContext.startActivity(intent);
//            }
//        });

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        initListener();
    }

    /**
     * 监听事件
     */
    private void initListener() {
        //每个条目的点击事件
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mContext,"mRecyclerview第" + position + "数据",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, TransactionManageDetailActivity.class);
                mContext.startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色

        //下拉加载
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        //上拉加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mAdapter.loadMoreComplete();//完成
//                mAdapter.loadMoreFail();//失败
//                mAdapter.loadMoreEnd();//结束
            }
        }, mRecyclerView);
    }
}
