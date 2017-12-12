package com.ygc.estatedecoration.adapter;

import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

/**
 * Created by FC on 2017/11/14.
 * 主页-交易管理-报价页面的RecyclerView的适配器
 */

public class HomeTransactionManageOfferAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HomeTransactionManageOfferAdapter(List<String> data) {
        super(R.layout.item_progress, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RelativeLayout lookProgressRl = helper.getView(R.id.look_progress_rl);
        lookProgressRl.setVisibility(View.GONE);
    }
}
