package com.ygc.estatedecoration.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CaseStyleAdapter;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.fragment.cas.EffectFragment;
import com.ygc.estatedecoration.fragment.cas.PanoramaFragment;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ServerMyAnLiActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.content_layout)
    CoordinatorLayout mCoordinatorLayout;
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
    private FragmentManager mFragmentManager;

    private int curTab = 0;
    private int curCaseState = 0;//当前案例的状态

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("我的案例");
        return true;
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
    protected void initView() {
        initTabLayoutAndViewPager();
    }

    private void initTabLayoutAndViewPager() {
        HomeMyStoreAdapter homeMyStoreAdapter = new HomeMyStoreAdapter(mFragmentManager, mFragmentList, mFragmentTitleList);
        mViewPager.setAdapter(homeMyStoreAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initFragmentTitle();
        initFragment();
    }

    private void initFragmentTitle() {
        mFragmentTitleList.add("全景图");
        mFragmentTitleList.add("效果图");
    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();
        PanoramaFragment panoramaFragment = PanoramaFragment.newInstance("", "");
        EffectFragment effectFragment = EffectFragment.newInstance("", "");
        mFragmentList.add(panoramaFragment);
        mFragmentList.add(effectFragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_servier_my_anli;
    }

    @OnClick({R.id.naviFrameLeft, R.id.anli_state_ll, R.id.anli_style_ll, R.id.upload_ll})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.anli_state_ll:
                    showStatePopupWindow();
                    break;
                case R.id.anli_style_ll:
                    showStylePopupWindow();
                    break;
                case R.id.upload_ll:
                    upLoadEvent();
                    break;
            }
        }
    }

    //上传
    private void upLoadEvent() {
        if (mUploadPopupWindow == null) {
            initUploadPopupWindow();
        }
        mUploadPopupWindow.showAtLocation(mCoordinatorLayout, Gravity.BOTTOM, 0, 0);
    }

    private BasePopupWindow mUploadPopupWindow;
    private void initUploadPopupWindow() {
        mUploadPopupWindow = new BasePopupWindow(this);
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_case_upload, null);
        final LinearLayout contentLayout = (LinearLayout) popupView.findViewById(R.id.content_ll);
        popupView.findViewById(R.id.quanjing_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUploadPopupWindow.dismiss();
                initSdPopupWindow();
            }
        });
        popupView.findViewById(R.id.xiaoguo_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUploadPopupWindow.dismiss();
                Intent intent = new Intent(ServerMyAnLiActivity.this, CaseEffectPicActivity.class);
                startActivity(intent);
            }
        });
        popupView.findViewById(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUploadPopupWindow.dismiss();
            }
        });
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int top = contentLayout.getTop();
                int vTop = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (vTop < top) {
                        mUploadPopupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        mUploadPopupWindow.setContentView(popupView);
        mUploadPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
    }

    private BasePopupWindow mSdPopupWindow;
    private void initSdPopupWindow() {
        if (mSdPopupWindow == null) {
            mSdPopupWindow = new BasePopupWindow(this);
            mSdPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_case_upload_sd, null);
            final LinearLayout contentLayout = (LinearLayout) popupView.findViewById(R.id.content_ll);
            popupView.findViewById(R.id.sure_tv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSdPopupWindow.dismiss();
                }
            });
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int left = contentLayout.getLeft();
                    int top = contentLayout.getTop();
                    int right = contentLayout.getRight();
                    int bottom = contentLayout.getBottom();
                    int vY = (int) event.getY();
                    int vX = (int) event.getX();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (vY < top || vY > bottom || vX < left || vX > right) {
                            mSdPopupWindow.dismiss();
                        }
                    }
                    return true;
                }
            });
            mSdPopupWindow.setContentView(popupView);
            mSdPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
        }
        mSdPopupWindow.showAtLocation(mCoordinatorLayout, Gravity.CENTER, 0, 0);
    }

    private void showStatePopupWindow() {
        if (mStatePopupWindow == null) {
            initStatePopupWindow();
        }
        mStatePopupWindow.showAsDropDown(mLl_stateContainer);
    }

    private BasePopupWindow mStatePopupWindow;
    private void initStatePopupWindow() {
        mStatePopupWindow = new BasePopupWindow(this);
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_case_state, null);
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
        if (mStylePopupWindow == null) {
            initStylePopupWindow();
        }
        mStylePopupWindow.showAsDropDown(mLl_styleContainer);
    }

    private String[] styleList = {"欧式", "中式", "美式", "田园", "地中海", "工业风",
            "东南亚", "混搭", "日式", "美式乡村", "简欧", "新古典"};

    private BasePopupWindow mStylePopupWindow;

    private void initStylePopupWindow() {
        mStylePopupWindow = new BasePopupWindow(this);
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_case_style, null);
        RecyclerView styleRecyclerView = (RecyclerView) popupView.findViewById(R.id.style_recyclerview);
        CaseStyleAdapter caseStyleAdapter = new CaseStyleAdapter(R.layout.item_case_style, Arrays.asList(styleList));
        styleRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        styleRecyclerView.addItemDecoration(new RecyclerSpace(30, Color.parseColor("#f6f6f6")));
        styleRecyclerView.setAdapter(caseStyleAdapter);
        caseStyleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i("521", "onItemClick: style:" + styleList[position]);
                mStylePopupWindow.dismiss();
            }
        });
        mStylePopupWindow.setContentView(popupView);
        mStylePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
    }


    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
