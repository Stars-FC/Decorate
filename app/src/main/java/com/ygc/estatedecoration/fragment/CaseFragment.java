package com.ygc.estatedecoration.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CaseStyleAdapter;
import com.ygc.estatedecoration.adapter.DemandAndProgressLazyFragmentPagerAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.CaseStyleBean;
import com.ygc.estatedecoration.bean.ContractContentBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.event.CaseEvent;
import com.ygc.estatedecoration.fragment.cas.EffectFragment;
import com.ygc.estatedecoration.fragment.cas.PanoramaFragment;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;
import com.zhy.autolayout.AutoLinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/13.
 * 案例界面
 */

public class CaseFragment extends BaseFragment {

    private static final String ARG_C = "content";

    @BindView(R.id.banner)
    BGABanner mBGABanner;
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

    private String curCaseStyle = null;//当前案例风格
    private String curCaseState = null;//当前案例的状态

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

    private List<CaseStyleBean.DataBean> mDataBeanList = new ArrayList<>();//案例风格数据
    private CaseStyleAdapter mCaseStyleAdapter;
    private void initStylePopupWindow() {
        mCasePopupWindow = new BasePopupWindow(mActivity);
        View popupView = LayoutInflater.from(mActivity).inflate(R.layout.popup_window_case_style, null);
        RecyclerView styleRecyclerView = (RecyclerView) popupView.findViewById(R.id.style_recyclerview);
        mCaseStyleAdapter = new CaseStyleAdapter(R.layout.item_case_style, mDataBeanList);
        styleRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
        styleRecyclerView.addItemDecoration(new RecyclerSpace(30, Color.parseColor("#f6f6f6")));
        styleRecyclerView.setAdapter(mCaseStyleAdapter);
        mCaseStyleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CaseStyleBean.DataBean> dataList = mCaseStyleAdapter.getData();
                if (position == 0) {
                    curCaseStyle = null;
                } else {
                    curCaseStyle = String.valueOf(dataList.get(position).getR_id());
                }
                for (int i = 0; i < dataList.size(); i++) {
                    CaseStyleBean.DataBean dataBean = dataList.get(i);
                    if (position == i) {
                        dataBean.setSelected(true);
                    } else {
                        dataBean.setSelected(false);
                    }
                }
                mCaseStyleAdapter.notifyDataSetChanged();
                mCasePopupWindow.dismiss();
                updateDataEvent();
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
        getBannerData();
        getCaseStyleData();
    }

    private void getBannerData() {
        APPApi.getInstance().service
                .getContractContentData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContractContentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull ContractContentBean contractContentBean) {
                        if (contractContentBean.responseState.equals("1")) {
                            String sys_case_banner = contractContentBean.getData().getSys_case_banner();
                            List<String> imgUrlList = new ArrayList<>();
                            String[] split = sys_case_banner.split(",");
                            for (String urlStr : split) {
                                imgUrlList.add(Constant.BASE_IMG + urlStr);
                            }
                            setAdvData(imgUrlList);
                        } else {
                            showToast(contractContentBean.msg);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setAdvData(List<String> imgUrlList) {
        List<String> imgTitleList = new ArrayList<>();
        for (int i = 0; i < imgTitleList.size(); i++) {
            imgTitleList.add("");
        }
        BGABanner.Adapter<AutoLinearLayout, String> bgaBannerAdapter = new BGABanner.Adapter<AutoLinearLayout, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, AutoLinearLayout itemView, String model, int position) {
                ImageView iconIv = (ImageView) itemView.findViewById(R.id.banner_iv);
                Glide.with(itemView.getContext())
                        .load(model)
                        .error(R.drawable.banner_default_icon)
                        .dontAnimate()
                        .into(iconIv);
            }
        };
        mBGABanner.setAdapter(bgaBannerAdapter);
        mBGABanner.setData(R.layout.item_banner, imgUrlList, imgTitleList);
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
                            List<CaseStyleBean.DataBean> data = caseStyleBean.getData();
                            mDataBeanList.clear();
                            if (data != null && data.size() > 0) {
                                CaseStyleBean.DataBean dataBean = new CaseStyleBean.DataBean();
                                dataBean.setR_name("全部");
                                dataBean.setSelected(true);
                                mDataBeanList.add(dataBean);
                                mDataBeanList.addAll(data);
                            }
                            mCaseStyleAdapter.notifyDataSetChanged();
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
        EffectFragment effectFragment = EffectFragment.newInstance();
        mFragmentList.add(panoramaFragment);
        mFragmentList.add(effectFragment);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTabLayoutAndViewPager();
    }

    private void initTabLayoutAndViewPager() {
        DemandAndProgressLazyFragmentPagerAdapter adapter = new DemandAndProgressLazyFragmentPagerAdapter(mChildFragmentManager, mFragmentList, mFragmentTitleList);
        mViewPager.setAdapter(adapter);
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
                curCaseState = null;
                updateDataEvent();
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
                curCaseState = "2";
                updateDataEvent();
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
                curCaseState = "1";
                updateDataEvent();
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

    private void updateDataEvent() {
        EventBus.getDefault().postSticky(new CaseEvent(curCaseStyle, curCaseState));
    }
}
