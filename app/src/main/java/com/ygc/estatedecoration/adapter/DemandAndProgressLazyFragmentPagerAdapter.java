package com.ygc.estatedecoration.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;

import java.util.List;

public class DemandAndProgressLazyFragmentPagerAdapter extends LazyFragmentPagerAdapter {

    private List<BaseFragment> fragmentList;
    private List<String> titleList;
    public DemandAndProgressLazyFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    protected Fragment getItem(ViewGroup container, int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
