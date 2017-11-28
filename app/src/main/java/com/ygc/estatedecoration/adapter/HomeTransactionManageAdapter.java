package com.ygc.estatedecoration.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.ygc.estatedecoration.activity.home.TransactionDetailPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/11/20.
 */

public class HomeTransactionManageAdapter extends PagerAdapter {

    private ArrayList<TransactionDetailPager> mBasePagers;
    private List<String> mList;

    public HomeTransactionManageAdapter(ArrayList<TransactionDetailPager> mBasePagers, List<String> mList) {
        this.mBasePagers = mBasePagers;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mBasePagers.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TransactionDetailPager detailPager  =mBasePagers.get(position);
        View rootView = detailPager.rootView;
        detailPager.initData();//调用父类方法初始化数据初始化数据
        container.addView(rootView);  //添加布局
        return rootView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 得到页面的标题
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}
