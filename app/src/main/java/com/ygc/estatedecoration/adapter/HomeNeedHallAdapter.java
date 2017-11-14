package com.ygc.estatedecoration.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/14.
 */

public class HomeNeedHallAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public HomeNeedHallAdapter(List<String> data) {
        super(R.layout.item_home_needhall,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
