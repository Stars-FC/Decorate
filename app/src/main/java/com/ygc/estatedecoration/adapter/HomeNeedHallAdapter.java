package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.NeedBean;

import java.util.List;

/**
 * Created by FC on 2017/11/14.
 * 主页-需求大厅的适配器
 */

public class HomeNeedHallAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HomeNeedHallAdapter(List<String> data) {//NeedBean.DataBean
        super(R.layout.item_home_needhall, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }

}
