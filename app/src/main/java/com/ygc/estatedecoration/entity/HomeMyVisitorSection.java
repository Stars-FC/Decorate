package com.ygc.estatedecoration.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by FC on 2017/11/16.
 * 1.用于访客页面嵌套的recyclerview的adapter泛型,
 * 2.在MyVisitorActivity中条用次此方法，为嵌套的recyclerview设置数据
 */

public class HomeMyVisitorSection extends SectionEntity<HomeMyVisitor> {
    public HomeMyVisitorSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public HomeMyVisitorSection(HomeMyVisitor visitor) {
        super(visitor);
    }
}
