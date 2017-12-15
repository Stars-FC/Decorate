package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.GirdDropDownAdapter;
import com.ygc.estatedecoration.adapter.HomeNeedHallAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.AddressPickTask;
import com.ygc.estatedecoration.utils.DateUtil;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.widget.TitleBar;
import com.yyydjk.library.DropDownMenu;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/14.
 * 首页-需求大厅界面
 */

public class NeedHallActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.missionType_tagFlowLayout)
    TagFlowLayout missionTypeTagFlowLayout;
    @BindView(R.id.constructionStatusQuo_tagFlowLayout)
    TagFlowLayout constructionStatusQuoTagFlowLayout;
    @BindView(R.id.start_time_et)
    EditText mEt_startTime;
    @BindView(R.id.end_time_et)
    EditText mEt_endTime;
    @BindView(R.id.minBuildingArea_et)
    EditText mEt_minBuildingArea;
    @BindView(R.id.maxBuildingArea_et)
    EditText mEt_maxBuildingArea;
    @BindView(R.id.address_tv)
    TextView mTv_address;

    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;

    private View mTopRightView; //右头布局
    private View mTopLeftView; //左侧头布局
    private View mRcView;//recycleyview的布局

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private List<View> popupViews = new ArrayList<>();
    private String headers[] = {"装修类型", "房屋现状"};
    private String types[] = {"不限", "家装", "工装"};
    private String nows[] = {"不限", "局部改造", "毛胚房", "旧房翻新"};
    private GirdDropDownAdapter typesAdapter;
    private GirdDropDownAdapter nowsAdapter;

    private List<NeedBean.DataBean> mDataBeanList = new ArrayList<>();
    private HomeNeedHallAdapter mAdapter;
    private String mStartTimeStr = null;
    private String mEndTimeStr = null;
    private String mMinAreaStr = null;
    private String mMaxAreaStr = null;
    private String mAddressStr = null;
    private ListView typesList;
    private ListView nowsList;

    private String[] missionTypeArray = {"家装", "工装"};
    private String[] constructionStatusQuoArray = {"局部改造", "毛胚房", "旧房翻新"};
    private String curSelectedMissionTypePosition = null;
    private String curSelectedConstructionStatusQuoPosition = null;
    private SweetAlertDialog mPDialog;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {
        //筛选按钮的点击事件
        mTopRightView.findViewById(R.id.tv_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDropDownMenu.closeMenu();//关闭选择列表
                mDrawerLayout.openDrawer(GravityCompat.END); //显示左侧筛选界面
            }
        });

        mTopLeftView.findViewById(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDropDownMenu.closeMenu();//关闭选择列表
                typesAdapter.setCheckItem(0);
                nowsAdapter.setCheckItem(0);
                mStartTimeStr = null;
                mEndTimeStr = null;
                mMinAreaStr = null;
                mMaxAreaStr = null;
                mAddressStr = null;
                missionType = null;
                constructionStatusQuo = null;
                curSelectedConstructionStatusQuoPosition = null;
                curSelectedMissionTypePosition = null;
                curPageNumAllData = 0;
                mDataBeanList.clear();
                mAdapter.notifyDataSetChanged();
                mPDialog.show();
                queryNeedDataEvent(0, Constant.NORMAL_REQUEST, missionType, constructionStatusQuo, mStartTimeStr, mEndTimeStr, mMinAreaStr, mMaxAreaStr, null);


                nowsAdapter.setCheckItem(0);
                mDropDownMenu.setListener1(headers[0]);

               typesAdapter.setCheckItem(0);
                mDropDownMenu.setListener2(headers[1]);

            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色

        //下拉加载
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //上拉加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()

        {
            @Override
            public void onLoadMoreRequested() {
                curPageNumAllData += 1;
                queryNeedDataEvent(curPageNumAllData, Constant.NORMAL_REQUEST, missionType, constructionStatusQuo, mStartTimeStr, mEndTimeStr, mMinAreaStr, mMaxAreaStr, mAddressStr);
            }
        }, mRecyclerView);
    }

    @Override
    protected void initView() {

        //头布局
        mTopRightView = View.inflate(getApplicationContext(), R.layout.top_home_needhall_right, null);

        mTopLeftView = View.inflate(getApplicationContext(), R.layout.top_home_needhall_left, null);

        mRcView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.recyclerview, null);

        mRecyclerView = (RecyclerView) mRcView.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRcView.findViewById(R.id.swipeLayout);

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止mDrawerLayout滑动显示

        //设置mDropDownMenu筛选
        initDropDownMenu();

        mAdapter = new HomeNeedHallAdapter(mDataBeanList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(NeedHallActivity.this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(NeedHallActivity.this, TransactionManageDetailActivity.class);
                NeedBean.DataBean dataBean = (NeedBean.DataBean) adapter.getItem(position);
                if (dataBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("demand", dataBean);
                    intent.putExtra("position", position);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } else {
                    showToast("数据异常，请稍后再试！");
                }
            }
        });


        missionTypeTagFlowLayout.setMaxSelectCount(1);
        missionTypeTagFlowLayout.setAdapter(new TagAdapter<String>(missionTypeArray) {
            @Override
            public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(NeedHallActivity.this).inflate(R.layout.item_user_search,
                        missionTypeTagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        missionTypeTagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.size() > 0) {
                    Iterator<Integer> iterator = selectPosSet.iterator();
                    if (iterator.hasNext()) {
                        curSelectedMissionTypePosition = String.valueOf(iterator.next());
                    }
                } else {
                    curSelectedMissionTypePosition = null;
                }
            }
        });
        constructionStatusQuoTagFlowLayout.setMaxSelectCount(1);
        constructionStatusQuoTagFlowLayout.setAdapter(new TagAdapter<String>(constructionStatusQuoArray) {
            @Override
            public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(NeedHallActivity.this).inflate(R.layout.item_user_search,
                        constructionStatusQuoTagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        constructionStatusQuoTagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.size() > 0) {
                    Iterator<Integer> iterator = selectPosSet.iterator();
                    if (iterator.hasNext()) {
                        curSelectedConstructionStatusQuoPosition = String.valueOf(iterator.next());
                    }
                } else {
                    curSelectedConstructionStatusQuoPosition = null;
                }
            }
        });
    }

    /**
     * 设置mDropDownMenu筛选
     */
    private void initDropDownMenu() {

        typesList = new ListView(this);
        typesList.setDividerHeight(0);
        typesAdapter = new GirdDropDownAdapter(this, Arrays.asList(types));
        typesList.setAdapter(typesAdapter);

        nowsList = new ListView(this);
        nowsList.setDividerHeight(0);
        nowsAdapter = new GirdDropDownAdapter(this, Arrays.asList(nows));
        nowsList.setAdapter(nowsAdapter);

        popupViews.add(typesList);
        popupViews.add(nowsList);

        typesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typesAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : types[position]);
                LogUtil.e("pos" + position);
                mDropDownMenu.closeMenu();
                mStartTimeStr = null;
                mEndTimeStr = null;
                mMinAreaStr = null;
                mMaxAreaStr = null;
                mAddressStr = null;
                if (position - 1 >= 0) {
                    missionType = String.valueOf(position - 1);
                } else {
                    missionType = null;
                }
                mDataBeanList.clear();
                mAdapter.notifyDataSetChanged();
                curPageNumAllData = 0;
                queryNeedDataEvent(0, Constant.NORMAL_REQUEST, missionType, constructionStatusQuo, mStartTimeStr, mEndTimeStr, mMinAreaStr, mMaxAreaStr, mAddressStr);
            }
        });

        nowsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nowsAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : nows[position]);
                mDropDownMenu.closeMenu();
                mStartTimeStr = null;
                mEndTimeStr = null;
                mMinAreaStr = null;
                mMaxAreaStr = null;
                mAddressStr = null;
                if (position - 1 >= 0) {
                    constructionStatusQuo = String.valueOf(position - 1);
                } else {
                    constructionStatusQuo = null;
                }
                mDataBeanList.clear();
                mAdapter.notifyDataSetChanged();
                curPageNumAllData = 0;
                queryNeedDataEvent(0, Constant.NORMAL_REQUEST, missionType, constructionStatusQuo, mStartTimeStr, mEndTimeStr, mMinAreaStr, mMaxAreaStr, mAddressStr);
            }
        });

        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, mRcView, mTopLeftView, mTopRightView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //查询全部的需求
        mPDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        mPDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mPDialog.setCancelable(false);
        mPDialog.show();
        queryNeedDataEvent(0, Constant.NORMAL_REQUEST, missionType, constructionStatusQuo, mStartTimeStr, mEndTimeStr, mMinAreaStr, mMaxAreaStr, mAddressStr);
    }

    private int curPageNumAllData = 0;
    private String missionType = null;//装修类型
    private String constructionStatusQuo = null;//建筑现状

    private void queryNeedDataEvent(int pageNum, final String requestMark, String tempMissionType, String tempConstructionStatusQuo,
                                    String startTimeStr, String endTimeStr, String minAreaStr, String maxAreaStr, String addressStr) {
        Log.i("521", "queryNeedDataEvent: " + pageNum + "===" + requestMark + "===" + tempMissionType + "===" + tempConstructionStatusQuo + "===" + startTimeStr + "===" + endTimeStr + "===" + minAreaStr + "===" + maxAreaStr + "===" + addressStr);

        APPApi.getInstance().service
                .queryAllNeed(pageNum, tempMissionType, tempConstructionStatusQuo, startTimeStr, endTimeStr, minAreaStr, maxAreaStr, addressStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeedBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull NeedBean needBean) {
                        if (needBean.responseState.equals("1")) {
                            int size = needBean.getData() == null ? 0 : needBean.getData().size();
                            if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                                mDataBeanList.clear();
                                if (size > 0) {
                                    mDataBeanList.addAll(needBean.getData());
                                }
                                mAdapter.notifyDataSetChanged();
                                refreshFinishEvent(true);
                            } else {
                                if (size > 0) {
                                    mDataBeanList.addAll(needBean.getData());
                                    mAdapter.notifyDataSetChanged();
                                    mAdapter.loadMoreComplete();
                                } else {
                                    mAdapter.loadMoreEnd();
                                }
                            }
                        } else {
                            if (requestMark.equals(Constant.NORMAL_REQUEST)) {
                                loadMoreFinishEvent();
                            } else {
                                refreshFinishEvent(false);
                            }
                            showToast(needBean.msg);
                        }
                        if (requestMark.equals(Constant.REFRESH_REQUEST)) {
                            mAdapter.setEnableLoadMore(true);
                        }
                        cancelDialog();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loadMoreFinishEvent();
                        refreshFinishEvent(false);
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void cancelDialog() {
        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
    }

    private void loadMoreFinishEvent() {
        mAdapter.loadMoreFail();
        curPageNumAllData -= 1;
        if (curPageNumAllData < 0) {
            curPageNumAllData = 0;
        }
    }

    private void refreshFinishEvent(boolean isSuccess) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            if (isSuccess) {
                curPageNumAllData = 0;
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_need_hall;
    }

    @OnClick({R.id.back, R.id.start_time_et, R.id.end_time_et, R.id.sure_btn, R.id.address_ll})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.start_time_et:
                onYearMonthDayPicker(0);
                break;
            case R.id.end_time_et:
                onYearMonthDayPicker(1);
                break;
            case R.id.address_ll:
                selectAddressEvent();
                break;
            case R.id.sure_btn:
                sureSelectCondition();
                break;
        }
    }

    private void selectAddressEvent() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideCounty(true);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToast("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                mTv_address.setText(province.getAreaName() + " " + city.getAreaName());
            }
        });
        task.execute("辽宁", "沈阳");
    }

    //确认筛选条件
    private void sureSelectCondition() {
        missionType = null;
        constructionStatusQuo = null;
        mDataBeanList.clear();
        mAdapter.notifyDataSetChanged();

        if (TextUtils.isEmpty(curSelectedMissionTypePosition)) {
            typesAdapter.setCheckItem(0);
        } else {
            typesAdapter.setCheckItem(Integer.valueOf(curSelectedMissionTypePosition));
        }
        if (TextUtils.isEmpty(curSelectedConstructionStatusQuoPosition)) {
            nowsAdapter.setCheckItem(0);
        } else {
            nowsAdapter.setCheckItem(Integer.valueOf(curSelectedConstructionStatusQuoPosition));
        }

        nowsAdapter.setCheckItem(0);
        mDropDownMenu.setListener1(headers[0]);
        typesAdapter.setCheckItem(0);
        mDropDownMenu.setListener2(headers[1]);
        mDrawerLayout.closeDrawer(Gravity.END);

        mStartTimeStr = mEt_startTime.getText().toString().trim();
        if (TextUtils.isEmpty(mStartTimeStr)) {
            mStartTimeStr = null;
        } else {
            mStartTimeStr = String.valueOf(DateUtil.string2Millis(mStartTimeStr + " 00:00:00") / 1000);
        }
        mEndTimeStr = mEt_endTime.getText().toString().trim();
        if (!TextUtils.isEmpty(mEndTimeStr)) {
            mEndTimeStr = String.valueOf(DateUtil.string2Millis(mEndTimeStr + " 00:00:00") / 1000);
        } else {
            mEndTimeStr = null;
        }
        mMinAreaStr = mEt_minBuildingArea.getText().toString().trim();
        if (TextUtils.isEmpty(mMinAreaStr)) {
            mMinAreaStr = null;
        }
        mMaxAreaStr = mEt_maxBuildingArea.getText().toString().trim();
        if (TextUtils.isEmpty(mMaxAreaStr)) {
            mMaxAreaStr = null;
        }
        mAddressStr = mTv_address.getText().toString().trim();
        if (TextUtils.isEmpty(mAddressStr)) {
            mAddressStr = null;
        } else {
            if (mAddressStr.equals("请选择")) {
                mAddressStr = null;
            }
        }
        mPDialog.show();
        Log.i("521", "sureSelectCondition: curSelectedMissionTypePosition:" + curSelectedMissionTypePosition);
        Log.i("521", "sureSelectCondition: curSelectedConstructionStatusQuoPosition:" + curSelectedConstructionStatusQuoPosition);

        queryNeedDataEvent(0, Constant.NORMAL_REQUEST, curSelectedMissionTypePosition, curSelectedConstructionStatusQuoPosition, mStartTimeStr, mEndTimeStr, mMinAreaStr, mMaxAreaStr, mAddressStr);
    }

    public void onYearMonthDayPicker(final int mark) {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setTopLineColor(0x994EBE65);
        picker.setTextColor(0xFF4EBE65);
        picker.setCancelTextColor(0xFF4EBE65);
        picker.setSubmitTextColor(0xFF4EBE65);
        picker.setTitleTextColor(0xFF4EBE65);
        picker.setDividerColor(0xFF4EBE65);
        picker.setRangeEnd(2111, 1, 11);
        picker.setRangeStart(2016, 8, 29);
        String curYearMonthDayStr = DateUtil.getNowString();
        String substring = curYearMonthDayStr.substring(0, 10);
        String[] split = substring.split("-");
        picker.setSelectedItem(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (mark == 0) {
                    mEt_startTime.setText(year + "-" + month + "-" + day);
                } else if (mark == 1) {
                    mEt_endTime.setText(year + "-" + month + "-" + day);
                }
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        queryNeedDataEvent(0, Constant.REFRESH_REQUEST, missionType, constructionStatusQuo, mStartTimeStr, mEndTimeStr, mMinAreaStr, mMaxAreaStr, mAddressStr);
    }
}
