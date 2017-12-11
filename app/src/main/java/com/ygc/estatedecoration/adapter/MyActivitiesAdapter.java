package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/13.
 * 我的-我的亮点
 */

public class MyActivitiesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyActivitiesAdapter(List<String> list) {
        super(R.layout.item_my_activity_activities, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
