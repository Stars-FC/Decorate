package com.ygc.estatedecoration.user_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.AllOrderAdapter1;
import com.ygc.estatedecoration.bean.DisplayAllUserOrdersBean;
import com.ygc.estatedecoration.user_activity.UserAllOrderActivity;
import com.ygc.estatedecoration.utils.CltitudeUtil;

public class ForTheGoodsFragment extends Fragment {
    private ListView listView;
    private UserAllOrderActivity act;
    private DisplayAllUserOrdersBean bean;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        act = (UserAllOrderActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_order_fragment,null);
        listView = (ListView) view.findViewById(R.id.all_order_fragment_listview);
//        setList();
        return view;
    }

    private void setList() {
        bean = act.getBean();
        listView.setAdapter(new AllOrderAdapter1(bean,act,"2"));
        CltitudeUtil.setListViewHeightBasedOnChildren(listView);
    }
}
