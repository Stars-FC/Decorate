package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.VisiterBean;
import com.ygc.estatedecoration.entity.HomeMyVisitor;
import com.ygc.estatedecoration.entity.HomeMyVisitorSection;
import com.ygc.estatedecoration.entity.base.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/11/16.
 * 主页-我的方访客适配器（类似两个RecyclerView的嵌套的实现）
 */

public class MyVisitorAdapter extends BaseQuickAdapter<VisiterBean.DataBean.ListBean, BaseViewHolder> {

    private RecyclerView mChildRecyclerView;
    private MyVisitorChildAdapter mAdapter;

    public MyVisitorAdapter() {
        super(R.layout.item_home_myvisitor_top);
    }

    @Override
    protected void convert(BaseViewHolder helper, VisiterBean.DataBean.ListBean bean) {

        mChildRecyclerView = (RecyclerView) helper.getView(R.id.chile_recycler);
        helper.setText(R.id.tv_time, bean.getVisited_time());//为父标题赋值
        setChildAdapter(bean);

    }

    private void setChildAdapter(VisiterBean.DataBean.ListBean bean) {
        mAdapter = new MyVisitorChildAdapter();
        mChildRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mChildRecyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(bean.getUserList());

        mChildRecyclerView.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mContext, "position__" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
