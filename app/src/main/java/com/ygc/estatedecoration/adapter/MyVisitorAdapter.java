package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.entity.HomeMyVisitor;
import com.ygc.estatedecoration.entity.HomeMyVisitorSection;

import java.util.List;

/**
 * Created by FC on 2017/11/16.
 * 主页-我的方访客适配器（类似两个RecyclerView的嵌套的实现）
 */

public class MyVisitorAdapter extends BaseSectionQuickAdapter<HomeMyVisitorSection, BaseViewHolder> {

    public MyVisitorAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final HomeMyVisitorSection item) {
        helper.setText(R.id.tv_time, item.header);//为父标题赋值
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMyVisitorSection item) {
        HomeMyVisitor myVisitor = (HomeMyVisitor) item.t;
        //为子标题内容赋值
        helper.setText(R.id.tv_name, myVisitor.getName());
//        helper.setText(R.id.iv_image, myVisitor.getImage());//图片
        helper.setText(R.id.tv_nowtime, myVisitor.getTime());
    }
}
