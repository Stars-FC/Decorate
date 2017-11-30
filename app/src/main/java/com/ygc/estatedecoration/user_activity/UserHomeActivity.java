package com.ygc.estatedecoration.user_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.fragment.CaseFragment;
import com.ygc.estatedecoration.user_fragment.UserHomeFragment;
import com.ygc.estatedecoration.user_fragment.UserMsgFragment;
import com.ygc.estatedecoration.user_fragment.UserMyFragment;
import com.ygc.estatedecoration.user_fragment.UserPublishFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class UserHomeActivity extends BaseActivity implements OnTabItemSelectedListener {

    @BindView(R.id.tab)
    PageNavigationView tab;

    private NavigationController mNavigationController;
    private UserHomeFragment mUserHomeFragment;
    private CaseFragment mUserPlanFragment;
    private UserPublishFragment mUserPublishFragment;
    private UserMsgFragment mUserMsgFragment;
    private UserMyFragment mUserMyFragment;
    private FragmentManager mSupportFragmentManager;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {
        mNavigationController.addTabItemSelectedListener(this);
    }

    @Override
    protected void initView() {
        initTab();
    }

    private void initTab() {
        mNavigationController = tab.custom()
                .addItem(newItem(R.drawable.shouye, R.drawable.shouye_sel, "首页"))
                .addItem(newItem(R.drawable.dianpu, R.drawable.dianpu_sel, "方案"))
                .addItem(newItem(R.drawable.guanli, R.drawable.guanli_sel, "发布"))
                .addItem(newItem(R.drawable.xiaoxi, R.drawable.xiaoxi_sel, "消息"))
                .addItem(newItem(R.drawable.wode, R.drawable.wode_sel, "我的"))
                .build();
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


    @Override
    protected void initData(Bundle savedInstanceState) {
        mSupportFragmentManager = getSupportFragmentManager();
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            List<Fragment> fragmentList = mSupportFragmentManager.getFragments();
            for (Fragment fragment : fragmentList) {
                if (fragment instanceof UserHomeFragment) {
                    mUserHomeFragment = (UserHomeFragment) fragment;
                } else if (fragment instanceof CaseFragment) {
                    mUserPlanFragment = (CaseFragment) fragment;
                } else if (fragment instanceof UserPublishFragment) {
                    mUserPublishFragment = (UserPublishFragment) fragment;
                } else if (fragment instanceof UserMsgFragment) {
                    mUserMsgFragment = (UserMsgFragment) fragment;
                } else if (fragment instanceof UserMyFragment) {
                    mUserMyFragment = (UserMyFragment) fragment;
                }
            }
        } else {
            FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
            createUserHomeFragment(fragmentTransaction);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_home;
    }

    @Override
    public void onSelected(int index, int old) {
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (index) {
            case 0:
                createUserHomeFragment(fragmentTransaction);
                break;
            case 1:
                createUserPlanFragment(fragmentTransaction);
                break;
            case 2:
                createUserPublishFragment(fragmentTransaction);
                break;
            case 3:
                createUserMsgFragment(fragmentTransaction);
                break;
            case 4:
                createUserMyFragment(fragmentTransaction);
                break;
        }
    }

    @Override
    public void onRepeat(int index) {

    }

    /**
     * 隐藏所有的fragment
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (mUserHomeFragment != null) {
            fragmentTransaction.hide(mUserHomeFragment);
        }
        if (mUserPlanFragment != null) {
            fragmentTransaction.hide(mUserPlanFragment);
        }
        if (mUserPublishFragment != null) {
            fragmentTransaction.hide(mUserPublishFragment);
        }
        if (mUserMsgFragment != null) {
            fragmentTransaction.hide(mUserMsgFragment);
        }
        if (mUserMyFragment != null) {
            fragmentTransaction.hide(mUserMyFragment);
        }
    }

    private void createUserMyFragment(FragmentTransaction fragmentTransaction) {
        if (mUserMyFragment == null) {
            mUserMyFragment = UserMyFragment.newInstance("", "");
            fragmentTransaction.add(R.id.fragment_container, mUserMyFragment).commit();
        } else {
            fragmentTransaction.show(mUserMyFragment).commit();
        }
    }

    private void createUserMsgFragment(FragmentTransaction fragmentTransaction) {
        if (mUserMsgFragment == null) {
            mUserMsgFragment = UserMsgFragment.newInstance("", "");
            fragmentTransaction.add(R.id.fragment_container, mUserMsgFragment).commit();
        } else {
            fragmentTransaction.show(mUserMsgFragment).commit();
        }
    }

    private void createUserPublishFragment(FragmentTransaction fragmentTransaction) {
        if (mUserPublishFragment == null) {
            mUserPublishFragment = UserPublishFragment.newInstance("", "");
            fragmentTransaction.add(R.id.fragment_container, mUserPublishFragment).commit();
        } else {
            fragmentTransaction.show(mUserPublishFragment).commit();
        }
    }

    private void createUserPlanFragment(FragmentTransaction fragmentTransaction) {
        if (mUserPlanFragment == null) {
            mUserPlanFragment = CaseFragment.newInstance("");
            fragmentTransaction.add(R.id.fragment_container, mUserPlanFragment).commit();
        } else {
            fragmentTransaction.show(mUserPlanFragment).commit();
        }
    }

    private void createUserHomeFragment(FragmentTransaction fragmentTransaction) {
        if (mUserHomeFragment == null) {
            mUserHomeFragment = UserHomeFragment.newInstance();
            fragmentTransaction.add(R.id.fragment_container, mUserHomeFragment).commit();
        } else {
            fragmentTransaction.show(mUserHomeFragment).commit();
        }
    }
}
