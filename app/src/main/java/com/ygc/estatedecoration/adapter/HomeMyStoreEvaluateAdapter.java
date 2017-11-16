package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的商铺-评价子页面的RecyclerView的适配器
 */

public class HomeMyStoreEvaluateAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HomeMyStoreEvaluateAdapter(List<String> data) {
        super(R.layout.item_home_mystore_evaluate, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
