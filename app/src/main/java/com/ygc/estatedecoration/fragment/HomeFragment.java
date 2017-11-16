package com.ygc.estatedecoration.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.MyStoreAvtivity;
import com.ygc.estatedecoration.activity.home.MyVisitorActivity;
import com.ygc.estatedecoration.activity.home.NeedHallActivity;
import com.ygc.estatedecoration.adapter.HomeAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by FC on 2017/11/13.
 * <p>
 * 首页
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private HomeAdapter mHomeAdapter;

    private static final String ARG_C = "content";
    private View mView;

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
//        String content = getArguments().getString(ARG_C); //获取
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("地产装饰");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        return true;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        mHomeAdapter = new HomeAdapter(list);

        mView = View.inflate(mActivity, R.layout.top_home, null);

        mHomeAdapter.addHeaderView(mView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mHomeAdapter);
    }

    @Override
    protected void addListener() {
        mView.findViewById(R.id.my_store).setOnClickListener(this);
        mView.findViewById(R.id.need_Analysis).setOnClickListener(this);
        mView.findViewById(R.id.trade_manage).setOnClickListener(this);
        mView.findViewById(R.id.my_visitor).setOnClickListener(this);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.my_store: //我的商铺
                intent.setClass(mActivity, MyStoreAvtivity.class);
                startActivity(intent);
                break;
            case R.id.need_Analysis://需求大厅
                intent.setClass(mActivity,NeedHallActivity.class);
                startActivity(intent);
                break;
            case R.id.trade_manage://交易管理
                Toast.makeText(mActivity, "交易管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_visitor://我的访客
//                intent.setClass(mActivity,MyVisitorActivity.class);
//                startActivity(intent);
                break;
        }
    }
}
