package com.ygc.estatedecoration.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.RoleFindAllBean;

import java.util.List;

/**
 * 微信登陆材料商item
 */

public class ServiceWeiXinLoginAdapter extends BaseQuickAdapter<RoleFindAllBean.DataBean._$4Bean, BaseViewHolder> {


    public ServiceWeiXinLoginAdapter(List<RoleFindAllBean.DataBean._$4Bean> materialBeans) {
        super(R.layout.item_case_style, materialBeans);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoleFindAllBean.DataBean._$4Bean item) {
        helper.setText(R.id.style_title_tv, item.getR_name());
    }


}
