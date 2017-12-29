package com.ygc.estatedecoration.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 用户端-找设计师适配器
 */

public class UserFindDesignerDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<String> mList;
    private RecyclerView mBetterRecyclerView;

    public UserFindDesignerDetailAdapter(List<String> list) {
        super(R.layout.item_user_find_design, list);
        mList = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        mBetterRecyclerView = (RecyclerView) helper.getView(R.id.scrollrecyclerview);

        setAdapter();
    }

    private void setAdapter() {
        ImageAdapter imageAdapter = new ImageAdapter(mList);
        mBetterRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mBetterRecyclerView.setAdapter(imageAdapter);
    }
}
