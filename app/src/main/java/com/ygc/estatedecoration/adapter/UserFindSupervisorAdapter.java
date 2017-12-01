package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

public class UserFindSupervisorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int mark;
    private List<String> dataList;
    public UserFindSupervisorAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, int mark) {
        super(layoutResId, data);
        this.dataList = data;
        this.mark = mark;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView nameTv = helper.getView(R.id.name_tv);
        TextView experienceTv = helper.getView(R.id.experience_tv);
        if (mark == 1) {
            nameTv.setText("施工团队名称");
            experienceTv.setText("10年施工经验");
        }
        RecyclerView recyclerView = helper.getView(R.id.recyclerview);
        UserFindSupervisorChildAdapter adapter = new UserFindSupervisorChildAdapter(R.layout.item_user_home_fragment_find_supervisor_more_child, dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
}
