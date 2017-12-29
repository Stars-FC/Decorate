package com.ygc.estatedecoration.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.HomeFragment;
import com.ygc.estatedecoration.fragment.ManageFragment;
import com.ygc.estatedecoration.fragment.MyFragment;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;

/**
 * 软件主界面的整体Activity
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tab)
    PageNavigationView tab;

    private static List<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        NavigationController navigationController = tab.custom()
                .addItem(newItem(R.drawable.shouye, R.drawable.shouye_sel, "首页"))
                .addItem(newItem(R.drawable.guanli, R.drawable.guanli_sel, "管理"))
                .addItem(newItem(R.drawable.wode, R.drawable.wode_sel,"我的"))
                .build();

        mViewPager.setAdapter(new HomeLazyFragmentAdapter(getSupportFragmentManager()));
        navigationController.setupWithViewPager(mViewPager);
    }

    /**
     * 创建一个Item，底部按钮
     *
     * @param drawable
     * @param checkedDrawable
     * @param text
     * @return
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.parseColor("#707686"));
        normalItemView.setTextCheckedColor(Color.parseColor("#4fbc65"));
        return normalItemView;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initFragment();
    }

    private void initFragment() {
        fragmentList.add(HomeFragment.newInstance(""));
        fragmentList.add(ManageFragment.newInstance(""));
        fragmentList.add(MyFragment.newInstance());
    }

    private static class HomeLazyFragmentAdapter extends LazyFragmentPagerAdapter{

        public HomeLazyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment getItem(ViewGroup container, int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
