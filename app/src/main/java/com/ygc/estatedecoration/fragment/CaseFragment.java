package com.ygc.estatedecoration.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CaseStyleAdapter;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.cas.EffectFragment;
import com.ygc.estatedecoration.fragment.cas.PanoramaFragment;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/13.
 * 案例界面
 */

public class CaseFragment extends BaseFragment {

    private static final String ARG_C = "content";

    @BindView(R.id.style_container_ll)
    LinearLayout mLl_styleContainer;

    @BindView(R.id.tablayout)
    XTabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private List<String> mFragmentTitleList = new ArrayList<>();
    private ArrayList<BaseFragment> mFragmentList = new ArrayList<>();
    private FragmentManager mChildFragmentManager;

    private BasePopupWindow mCasePopupWindow;

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
        initStylePopupWindow();
    }

    private String[] styleList = {"欧式", "中式", "美式", "田园", "地中海", "工业风",
            "东南亚", "混搭", "日式", "美式乡村", "简欧", "新古典"};

    private void initStylePopupWindow() {
        mCasePopupWindow = new BasePopupWindow(mActivity);
        View popupView = LayoutInflater.from(mActivity).inflate(R.layout.popup_window_case_more, null);
        RecyclerView styleRecyclerView = (RecyclerView) popupView.findViewById(R.id.style_recyclerview);
        CaseStyleAdapter caseStyleAdapter = new CaseStyleAdapter(R.layout.item_case_style, Arrays.asList(styleList));
        styleRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
        styleRecyclerView.addItemDecoration(new RecyclerSpace(30, Color.parseColor("#f6f6f6")));
        styleRecyclerView.setAdapter(caseStyleAdapter);
        caseStyleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i("521", "onItemClick: style:" + styleList[position]);
                mCasePopupWindow.dismiss();
            }
        });
        mCasePopupWindow.setContentView(popupView);
        mCasePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
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

    @OnClick({R.id.more_ll})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.more_ll:
                    showStylePopupWindow();
                    break;
            }
        }
    }

    private void showStylePopupWindow() {
        if (mCasePopupWindow == null) {
            initStylePopupWindow();
        }
        mCasePopupWindow.showAsDropDown(mLl_styleContainer);
    }
}
