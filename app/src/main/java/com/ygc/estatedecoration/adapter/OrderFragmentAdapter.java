package com.ygc.estatedecoration.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class OrderFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    private String[] mTitles = new String[]{"全部", "待付款" , "待收货" , "待评价" , "质保"};

    public OrderFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
