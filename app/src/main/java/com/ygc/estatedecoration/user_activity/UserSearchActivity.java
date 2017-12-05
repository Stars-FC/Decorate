package com.ygc.estatedecoration.user_activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UserSearchAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserSearchActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.keyword_recyclerview)
    RecyclerView mRv_keyword;
    private List<String> keywordList = new ArrayList<>();
    @BindView(R.id.search_history_recyclerview)
    RecyclerView mRv_searchHistory;
    private List<String> searchHistoryList = new ArrayList<>();

    private UserSearchAdapter mUserSearchAdapter1;
    private UserSearchAdapter mUserSearchAdapter2;

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
        initRecyclerView();
    }

    private void initRecyclerView() {

        mUserSearchAdapter1 = new UserSearchAdapter(R.layout.item_user_search, keywordList);
        mRv_keyword.setLayoutManager(new GridLayoutManager(this, 4));
        mRv_keyword.addItemDecoration(new RecyclerSpace(18, Color.parseColor("#ffffff")));
        mRv_keyword.setAdapter(mUserSearchAdapter1);

        mUserSearchAdapter2 = new UserSearchAdapter(R.layout.item_user_search, searchHistoryList);
        mRv_searchHistory.setLayoutManager(new GridLayoutManager(this, 4));
        mRv_searchHistory.addItemDecoration(new RecyclerSpace(18, Color.parseColor("#ffffff")));
        mRv_searchHistory.setAdapter(mUserSearchAdapter2);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        getNetworkData(Constant.NORMAL_REQUEST);
    }

    private void getNetworkData(String requestMark) {

        for (int i = 0; i < 10; i++) {
            keywordList.add("关键词" + (i + 1));
        }
        for (int i = 0; i < 4; i++) {
            searchHistoryList.add("历史" + (i + 1));
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

    @OnClick({R.id.finish_rl})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.finish_rl:
//                    closeKeyboard();
                    finish();
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
}
