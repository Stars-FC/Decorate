package com.ygc.estatedecoration.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ygc.estatedecoration.fragment.HomeFragment;
import com.ygc.estatedecoration.fragment.ManageFragment;
import com.ygc.estatedecoration.fragment.MyFragment;

/**
 *主页面内ViewPager的适配器，
 * 作用：实现显示界面的切换
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private int size;

    public MyViewPagerAdapter(FragmentManager fm, int size) {
        super(fm);
        this.size = size;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return HomeFragment.newInstance(position + "");
        } else if (position == 1) {
            return ManageFragment.newInstance(position + "");
        }else if (position == 2) {
            return MyFragment.newInstance(position + "");
        } else {
            return HomeFragment.newInstance(position + "");
        }
    }

    @Override
    public int getCount() {
        return size;
    }
}
