package com.ygc.estatedecoration.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.MyViewPagerAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

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

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
//        PageNavigationView tab = (PageNavigationView) findViewById(R.id.tab);
        NavigationController navigationController = tab.custom()
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "首页"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "案例"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "管理"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "消息"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "我的"))
                .build();

        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), navigationController.getItemCount()));

        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
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
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(0xFF009688);
        return normalItemView;
    }
}
