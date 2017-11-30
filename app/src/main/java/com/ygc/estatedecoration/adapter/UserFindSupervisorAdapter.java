package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

public class UserFindSupervisorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<String> dataList;
    public UserFindSupervisorAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.dataList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        RecyclerView recyclerView = helper.getView(R.id.recyclerview);
        UserFindSupervisorChildAdapter adapter = new UserFindSupervisorChildAdapter(R.layout.item_user_home_fragment_find_supervisor_more_child, dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
}
