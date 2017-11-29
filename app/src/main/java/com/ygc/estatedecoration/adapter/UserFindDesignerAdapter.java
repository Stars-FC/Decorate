package com.ygc.estatedecoration.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.widget.BetterRecyclerView;
import com.ygc.estatedecoration.widget.ScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 用户端-找设计师适配器
 */

public class UserFindDesignerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;

    private ScrollRecyclerView mBetterRecyclerView;

    public UserFindDesignerAdapter(List<String> list,Context context) {
        super(R.layout.item_user_find_design, list);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        mBetterRecyclerView = (ScrollRecyclerView) helper.getView(R.id.scrollrecyclerview);

        setAdapter();
    }

    private void setAdapter() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add("" + i);
        }

        mBetterRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mBetterRecyclerView.setAdapter(new ImageAdapter(list));
    }

    private class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        protected ImageAdapter(List<String> list) {
            super(R.layout.item_user_find_design_image, list);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView imageView = (ImageView) helper.getView(R.id.iv_design_image);
        }
    }
}
