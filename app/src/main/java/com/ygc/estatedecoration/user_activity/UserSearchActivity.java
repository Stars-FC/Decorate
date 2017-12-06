package com.ygc.estatedecoration.user_activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.MainActivity;
import com.ygc.estatedecoration.adapter.UserSearchAdapter;
import com.ygc.estatedecoration.app.MyApplication;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.FlowLayout;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.majiajie.pagerbottomtabstrip.internal.Utils;

public class UserSearchActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    //搜索的内容
    @BindView(R.id.et_search_data)
    EditText mEtcontent;

    @BindView(R.id.flow_layout)
    FlowLayout mFlowLayout;  //流式布局自定义控件

    @BindView(R.id.flow_layout_history)
    FlowLayout mHistoryFlowLayout;

    private UserUtils mUtils;


    /**
     * 搜索历史
     */
    private List<String> searchHistoryList = new ArrayList<>();

    /**
     * 显示的文字
     */
    private List<String> keywordList = new ArrayList<>();

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initView() {
//        initRecyclerView();
    }

    private void initRecyclerView() {

       /* mUserSearchAdapter1 = new UserSearchAdapter(R.layout.item_user_search, keywordList);
        mRv_keyword.setLayoutManager(new GridLayoutManager(this, 4));
        mRv_keyword.addItemDecoration(new RecyclerSpace(18, Color.parseColor("#ffffff")));
        mRv_keyword.setAdapter(mUserSearchAdapter1);

        mUserSearchAdapter2 = new UserSearchAdapter(R.layout.item_user_search, searchHistoryList);
        mRv_searchHistory.setLayoutManager(new GridLayoutManager(this, 4));
        mRv_searchHistory.addItemDecoration(new RecyclerSpace(18, Color.parseColor("#ffffff")));
        mRv_searchHistory.setAdapter(mUserSearchAdapter2);*/
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        keywordList.add("装修类型一");
        keywordList.add("装修类型二");
        keywordList.add("家装");
        keywordList.add("彩妆");
        keywordList.add("关键字一");
        keywordList.add("关键字二");
        keywordList.add("装修类型二");
        keywordList.add("装修装修装修装修");
        keywordList.add("关键字一");
        keywordList.add("test");
        keywordList.add("彩妆");

        mUtils = new UserUtils();

        getHoisteryData(); //获取历史搜索记录

        //显示热门搜索
        getData(keywordList, mFlowLayout);

        //显示历史搜索记录
        if (searchHistoryList != null) {
            getData(searchHistoryList, mHistoryFlowLayout);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_search;
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @OnClick({R.id.finish_rl, R.id.tv_search, R.id.tv_empty})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.finish_rl://后退
                    closeKeyboard();
                    finish();
                    break;
                case R.id.tv_search://搜索
                    setHisteryData();
                    break;
                case R.id.tv_empty://清空
                    mUtils.clear(mUtils.SEARCH);
                    showToast("清除记录成功");
                    break;
            }
        }
    }

    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 显示流式标签
     *
     * @param list
     * @param flowLayout
     */
    public void getData(List<String> list, FlowLayout flowLayout) {
        // 循环添加TextView到容器
        for (int i = 0; i < list.size(); i++) {
            TextView tv = (TextView) LayoutInflater.from(UserSearchActivity.this).inflate(
                    R.layout.item_user_search, flowLayout, false);
            tv.setText(list.get(i));
            final String str = tv.getText().toString();
            //点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //加入搜索历史纪录记录
//                    Toast.makeText(UserSearchActivity.this, str, Toast.LENGTH_LONG).show();
                    mEtcontent.setText(str);
                }
            });
            flowLayout.addView(tv);
        }
    }

    /**
     * 保存搜索的内容记录
     */
    public void setHisteryData() {

        String content = mEtcontent.getText().toString().trim();
//        String strs =content+"," ;
        if (!TextUtils.isEmpty(content)) {
            mUtils.setParam(mUtils.SEARCH, "search_history", content);
        }
    }

    /**
     * 读取搜索历史纪录
     */
    public void getHoisteryData() {
        String history = (String) mUtils.getParam(mUtils.SEARCH, "search_history", "");
        if (!TextUtils.isEmpty(history)) {
            List<String> list = new ArrayList<String>();
            for (Object o : history.split(",")) {
                list.add((String) o);
            }
            searchHistoryList = list;
        }
    }
}
