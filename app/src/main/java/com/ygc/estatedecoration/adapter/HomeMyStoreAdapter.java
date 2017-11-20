package com.ygc.estatedecoration.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ygc.estatedecoration.app.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的商铺-公司信息、评价/交易管理-每个条目详情页面ViewPager的适配器
 */

public class HomeMyStoreAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseFragment> mFragments;
    private List<String> mList;

    public HomeMyStoreAdapter(FragmentManager fm, ArrayList<BaseFragment> mFragments, List<String> mList) {
        super(fm);
        this.mFragments = mFragments;
        this.mList = mList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
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
