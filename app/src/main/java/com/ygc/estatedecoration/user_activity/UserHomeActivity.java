package com.ygc.estatedecoration.user_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.fragment.CaseFragment;
import com.ygc.estatedecoration.user_fragment.UserHomeFragment;
import com.ygc.estatedecoration.user_fragment.UserMsgFragment;
import com.ygc.estatedecoration.user_fragment.UserMyFragment;
import com.ygc.estatedecoration.user_fragment.UserPublishFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserHomeActivity extends BaseActivity{

    @BindView(R.id.home_iv)
    ImageView mIv_home;
    @BindView(R.id.home_tv)
    TextView mTv_home;
    @BindView(R.id.fangan_iv)
    ImageView mIv_fangAn;
    @BindView(R.id.fangan_tv)
    TextView mTv_fangAn;
    @BindView(R.id.publish_iv)
    ImageView mIv_publish;
    @BindView(R.id.publish_tv)
    TextView mTv_publish;
    @BindView(R.id.msg_iv)
    ImageView mIv_msg;
    @BindView(R.id.msg_tv)
    TextView mTv_msg;
    @BindView(R.id.my_iv)
    ImageView mIv_my;
    @BindView(R.id.my_tv)
    TextView mTv_my;
    private List<ImageView> iv_iconList = new ArrayList<>();
    private List<TextView> txt_titleList = new ArrayList<>();
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

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        iv_iconList.add(mIv_home);
        iv_iconList.add(mIv_fangAn);
        iv_iconList.add(mIv_publish);
        iv_iconList.add(mIv_msg);
        iv_iconList.add(mIv_my);
        txt_titleList.add(mTv_home);
        txt_titleList.add(mTv_fangAn);
        txt_titleList.add(mTv_publish);
        txt_titleList.add(mTv_msg);
        txt_titleList.add(mTv_my);
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

    @OnClick({R.id.home_ll, R.id.fangan_ll, R.id.publish_rl, R.id.msg_ll, R.id.my_ll})
    public void onClick(View view) {
        if (view != null) {
            FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
            hideAllFragment(fragmentTransaction);
            switch (view.getId()) {
                case R.id.home_ll:
                    changeTabStyle(0);
                    mIv_home.setImageResource(R.drawable.shouye_sel);
                    mIv_fangAn.setImageResource(R.drawable.anli);
                    mIv_publish.setImageResource(R.drawable.fabu);
                    mIv_msg.setImageResource(R.drawable.xiaoxi);
                    mIv_my.setImageResource(R.drawable.wode);
                    createUserHomeFragment(fragmentTransaction);
                    break;
                case R.id.fangan_ll:
                    changeTabStyle(1);
                    mIv_home.setImageResource(R.drawable.shouye);
                    mIv_fangAn.setImageResource(R.drawable.anli_sel);
                    mIv_publish.setImageResource(R.drawable.fabu);
                    mIv_msg.setImageResource(R.drawable.xiaoxi);
                    mIv_my.setImageResource(R.drawable.wode);
                    createUserPlanFragment(fragmentTransaction);
                    break;
                case R.id.publish_rl:
                    changeTabStyle(2);
                    mIv_home.setImageResource(R.drawable.shouye);
                    mIv_fangAn.setImageResource(R.drawable.anli);
                    mIv_publish.setImageResource(R.drawable.fabu_sel);
                    mIv_msg.setImageResource(R.drawable.xiaoxi);
                    mIv_my.setImageResource(R.drawable.wode);
                    createUserPublishFragment(fragmentTransaction);
                    break;
                case R.id.msg_ll:
                    changeTabStyle(3);
                    mIv_home.setImageResource(R.drawable.shouye);
                    mIv_fangAn.setImageResource(R.drawable.anli);
                    mIv_publish.setImageResource(R.drawable.fabu);
                    mIv_msg.setImageResource(R.drawable.xiaoxi_sel);
                    mIv_my.setImageResource(R.drawable.wode);
                    createUserMsgFragment(fragmentTransaction);
                    break;
                case R.id.my_ll:
                    changeTabStyle(4);
                    mIv_home.setImageResource(R.drawable.shouye);
                    mIv_fangAn.setImageResource(R.drawable.anli);
                    mIv_publish.setImageResource(R.drawable.fabu);
                    mIv_msg.setImageResource(R.drawable.xiaoxi);
                    mIv_my.setImageResource(R.drawable.wode_sel);
                    createUserMyFragment(fragmentTransaction);
                    break;
            }
        }
    }

    private void changeTabStyle(int position) {
        for (int i = 0; i < txt_titleList.size(); i++) {
            //改变tab标题的颜色
            TextView textView = txt_titleList.get(i);
            if (i == position) {
                textView.setTextColor(Color.parseColor("#4fbc65"));
            } else {
                textView.setTextColor(Color.parseColor("#707686"));
            }
        }
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
