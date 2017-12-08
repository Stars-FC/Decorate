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

    //    @BindView(R.id.rg_main)
//    RadioGroup mRadioGroup;
    @BindView(R.id.tab)
    PageNavigationView tab;

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
                .addItem(newItem(R.drawable.xiaoxi, R.drawable.xiaoxi_sel, "消息"))
                .addItem(newItem(R.drawable.wode, R.drawable.wode_sel,"我的"))
                .build();

        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), navigationController.getItemCount()));

        navigationController.setupWithViewPager(mViewPager);
        //设置单选按钮的点击事件
//        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                switch (i) {
//                    case R.id.rb_home:
//                        mViewPager.setCurrentItem(0, false);
//                        break;
//                    case R.id.rb_case:
//                        mViewPager.setCurrentItem(1, false);
//                        break;
//                    case R.id.rb_manage:
//                        mViewPager.setCurrentItem(2, false);
//                        break;
//                    case R.id.rb_news:
//                        mViewPager.setCurrentItem(3, false);
//                        break;
//                    case R.id.rb_my:
//                        mViewPager.setCurrentItem(4, false);
//                        break;
//                }
//            }
//        });
//        //设置监听viewpager滑动的事件
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            /**
//             * 当某个页面呗被中的时候调用这个方法
//             * @param position 被选中页面的位置
//             */
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        //设置默认选中页面
//        mRadioGroup.check(R.id.rb_home);
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

    }
}
