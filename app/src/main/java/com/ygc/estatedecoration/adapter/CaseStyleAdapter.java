package com.ygc.estatedecoration.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.CaseStyleBean;

import java.util.List;

public class CaseStyleAdapter extends BaseQuickAdapter<CaseStyleBean.DataBean, BaseViewHolder> {

    public CaseStyleAdapter(@LayoutRes int layoutResId, @Nullable List<CaseStyleBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CaseStyleBean.DataBean item) {
        TextView title = helper.getView(R.id.style_title_tv);
        title.setText(item.getR_name());
        if (item.isSelected()) {
            title.setBackgroundResource(R.drawable.bg_anli);
            title.setTextColor(Color.parseColor("#ffffff"));
        } else {
            title.setBackgroundResource(R.drawable.white_bg);
            title.setTextColor(Color.parseColor("#000000"));
        }
    }
}
