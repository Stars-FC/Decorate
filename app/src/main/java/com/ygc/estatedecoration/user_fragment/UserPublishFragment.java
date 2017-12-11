package com.ygc.estatedecoration.user_fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CaseStyleAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.Arrays;

import butterknife.BindView;

public class UserPublishFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public UserPublishFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.materials_type_recyclerview)
    RecyclerView mRv_materialsType;

    public static UserPublishFragment newInstance(String param1, String param2) {
        UserPublishFragment fragment = new UserPublishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("发布需求");
        return true;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        initMaterialsTypeRv();
    }

    private String[] materialsTypeArray = {"木工", "泥工", "水电工", "漆工", "安装工", "杂工", "玻璃", "门窗", "空调", "地暖", "灯具", "洁具", "软装"};

    private void initMaterialsTypeRv() {
        CaseStyleAdapter caseStyleAdapter = new CaseStyleAdapter(R.layout.item_case_style, Arrays.asList(materialsTypeArray));
        mRv_materialsType.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mRv_materialsType.addItemDecoration(new RecyclerSpace(30, Color.parseColor("#f6f6f6")));
        mRv_materialsType.setNestedScrollingEnabled(false);
        mRv_materialsType.setAdapter(caseStyleAdapter);
        caseStyleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

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
        return R.layout.fragment_user_publish;
    }

}
