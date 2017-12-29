package com.ygc.estatedecoration.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CaseStyleAdapter;
import com.ygc.estatedecoration.adapter.DemandAndProgressLazyFragmentPagerAdapter;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.CaseStyleBean;
import com.ygc.estatedecoration.fragment.cas.EffectFragment;
import com.ygc.estatedecoration.fragment.cas.PanoramaFragment;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/13.
 * 案例界面
 */

public class CaseFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_C = "content";

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.anli_state_ll)
    LinearLayout mLl_stateContainer;
    @BindView(R.id.anli_style_ll)
    LinearLayout mLl_styleContainer;
    @BindView(R.id.tablayout)
    XTabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private List<String> mFragmentTitleList = new ArrayList<>();
    private ArrayList<BaseFragment> mFragmentList = new ArrayList<>();
    private FragmentManager mChildFragmentManager;

    private BasePopupWindow mCasePopupWindow;

    private int curTab = 0;
    private int curCaseState = 0;//当前案例的状态
    private List<CaseStyleBean.DataBean> mDataBeanList;//案例风格数据

    public static CaseFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        CaseFragment fragment = new CaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("案例");
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private List<String> caseStyleList = new ArrayList<>();
    private CaseStyleAdapter mCaseStyleAdapter;
    private void initStylePopupWindow() {
        mCasePopupWindow = new BasePopupWindow(mActivity);
        View popupView = LayoutInflater.from(mActivity).inflate(R.layout.popup_window_case_style, null);
        RecyclerView styleRecyclerView = (RecyclerView) popupView.findViewById(R.id.style_recyclerview);
        mCaseStyleAdapter = new CaseStyleAdapter(R.layout.item_case_style, caseStyleList);
        styleRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
        styleRecyclerView.addItemDecoration(new RecyclerSpace(30, Color.parseColor("#f6f6f6")));
        styleRecyclerView.setAdapter(mCaseStyleAdapter);
        mCaseStyleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i("521", "onItemClick: style:" + caseStyleList.get(position));
                mCasePopupWindow.dismiss();
            }
        });
        mCasePopupWindow.setContentView(popupView);
        mCasePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
    }

    @Override
    protected void initData(Bundle arguments) {
        initFragmentTitle();
        initFragment();
        initStylePopupWindow();
        getCaseStyleData();
    }

    private void getCaseStyleData() {
        showDialog();
        APPApi.getInstance().service
                .getCaseStyleData("5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CaseStyleBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull CaseStyleBean caseStyleBean) {
                        cancelDialog();
                        if (caseStyleBean.responseState.equals("1")) {
                            mDataBeanList = caseStyleBean.getData();
                            if (caseStyleList.size() > 0) {
                                caseStyleList.clear();
                            }
                            caseStyleList.add("全部");
                            for (CaseStyleBean.DataBean dataBean : mDataBeanList) {
                                String r_name = dataBean.getR_name();
                                caseStyleList.add(r_name);
                                mCaseStyleAdapter.notifyDataSetChanged();
                            }
                        } else {
                            showToast(caseStyleBean.msg);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initFragmentTitle() {
        mFragmentTitleList.add("全景图");
        mFragmentTitleList.add("效果图");
    }

    private void initFragment() {
        mChildFragmentManager = getChildFragmentManager();
        PanoramaFragment panoramaFragment = PanoramaFragment.newInstance();
        EffectFragment effectFragment = EffectFragment.newInstance("", "");
        mFragmentList.add(panoramaFragment);
        mFragmentList.add(effectFragment);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTabLayoutAndViewPager();
    }

    private void initTabLayoutAndViewPager() {
        DemandAndProgressLazyFragmentPagerAdapter adapter = new DemandAndProgressLazyFragmentPagerAdapter(mChildFragmentManager, mFragmentList, mFragmentTitleList);
//        HomeMyStoreAdapter homeMyStoreAdapter = new HomeMyStoreAdapter(mChildFragmentManager, mFragmentList, mFragmentTitleList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curTab = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_case;
    }

    @OnClick({R.id.anli_state_ll, R.id.anli_style_ll})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.anli_state_ll:
                    showStatePopupWindow();
                    break;
                case R.id.anli_style_ll:
                    showStylePopupWindow();
                    break;
            }
        }
    }

    private void showStatePopupWindow() {
        if (mStatePopupWindow == null) {
            initStatePopupWindow();
        }
        mStatePopupWindow.showAsDropDown(mLl_stateContainer);
    }

    private BasePopupWindow mStatePopupWindow;
    private void initStatePopupWindow() {
        mStatePopupWindow = new BasePopupWindow(mActivity);
        View popupView = LayoutInflater.from(mActivity).inflate(R.layout.popup_window_case_state, null);
        final TextView stateAllTv = (TextView) popupView.findViewById(R.id.state_all_tv);
        final TextView stateFinishTv = (TextView) popupView.findViewById(R.id.state_finish_tv);
        final TextView stateNoFinishTv = (TextView) popupView.findViewById(R.id.state_no_finish_tv);
        stateAllTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStatePopupWindow.dismiss();
                stateAllTv.setBackgroundResource(R.drawable.bg_anli);
                stateAllTv.setTextColor(Color.parseColor("#ffffff"));
                stateFinishTv.setBackgroundResource(R.drawable.white_bg);
                stateFinishTv.setTextColor(Color.parseColor("#000000"));
                stateNoFinishTv.setBackgroundResource(R.drawable.white_bg);
                stateNoFinishTv.setTextColor(Color.parseColor("#000000"));
                curCaseState = 0;
            }
        });
        stateFinishTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStatePopupWindow.dismiss();
                stateFinishTv.setBackgroundResource(R.drawable.bg_anli);
                stateFinishTv.setTextColor(Color.parseColor("#ffffff"));
                stateAllTv.setBackgroundResource(R.drawable.white_bg);
                stateAllTv.setTextColor(Color.parseColor("#000000"));
                stateNoFinishTv.setBackgroundResource(R.drawable.white_bg);
                stateNoFinishTv.setTextColor(Color.parseColor("#000000"));
                curCaseState = 1;
            }
        });
        stateNoFinishTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStatePopupWindow.dismiss();
                stateNoFinishTv.setBackgroundResource(R.drawable.bg_anli);
                stateNoFinishTv.setTextColor(Color.parseColor("#ffffff"));
                stateFinishTv.setBackgroundResource(R.drawable.white_bg);
                stateFinishTv.setTextColor(Color.parseColor("#000000"));
                stateAllTv.setBackgroundResource(R.drawable.white_bg);
                stateAllTv.setTextColor(Color.parseColor("#000000"));
                curCaseState = 2;
            }
        });
        mStatePopupWindow.setContentView(popupView);
        mStatePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
    }

    private void showStylePopupWindow() {
        if (mCasePopupWindow == null) {
            initStylePopupWindow();
        }
        mCasePopupWindow.showAsDropDown(mLl_styleContainer);
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
