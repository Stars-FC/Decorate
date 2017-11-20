package com.ygc.estatedecoration.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.cas.EffectFragment;
import com.ygc.estatedecoration.fragment.cas.PanoramaFragment;
import com.ygc.estatedecoration.utils.tablayoutUnderLine;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by FC on 2017/11/13.
 * 案例界面
 */

public class CaseFragment extends BaseFragment {

    private static final String ARG_C = "content";

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private List<String> mFragmentTitleList = new ArrayList<>();
    private ArrayList<BaseFragment> mFragmentList = new ArrayList<>();
    private FragmentManager mChildFragmentManager;


    public static CaseFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        CaseFragment fragment = new CaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentTitle();
        initFragment();
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    private void initFragmentTitle() {
        mFragmentTitleList.add("全景图");
        mFragmentTitleList.add("效果图");
    }

    private void initFragment() {
        mChildFragmentManager = getChildFragmentManager();
        PanoramaFragment panoramaFragment = PanoramaFragment.newInstance("", "");
        EffectFragment effectFragment = EffectFragment.newInstance("", "");
        mFragmentList.add(panoramaFragment);
        mFragmentList.add(effectFragment);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTabLayoutAndViewPager();
    }

    private void initTabLayoutAndViewPager() {
        HomeMyStoreAdapter homeMyStoreAdapter = new HomeMyStoreAdapter(mChildFragmentManager, mFragmentList, mFragmentTitleList);
        mViewPager.setAdapter(homeMyStoreAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                new tablayoutUnderLine().setIndicator(mTabLayout, 40, 40);
            }
        });
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_case;
    }
}
