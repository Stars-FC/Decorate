package com.ygc.estatedecoration.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.DisplayAllUserOrdersBean;
import com.ygc.estatedecoration.utils.CltitudeUtil;
import com.ygc.estatedecoration.utils.MyPublic;

import java.util.List;

public class AllOrderAdapter1 extends BaseAdapter {

    private DisplayAllUserOrdersBean bean;
    private Context context;
    private String state;

    public AllOrderAdapter1(DisplayAllUserOrdersBean bean, Context context, String state) {
        this.bean = bean;
        this.context = context;
        this.state = state;
    }

    @Override
    public int getCount() {
        return bean.getObj().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order1, null);
            viewHolder = new ViewHolder();
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.order_all);
            viewHolder.numTv = (TextView) convertView.findViewById(R.id.order_number);
            viewHolder.typeTv = (TextView) convertView.findViewById(R.id.order_type);
            viewHolder.listView = (ListView) convertView.findViewById(R.id.order_listView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (!TextUtils.isEmpty(state)) {
            if (!bean.getObj().get(position).getMpo_state().equals(state)) {
                viewHolder.linearLayout.setVisibility(View.GONE);
                return convertView;
            }
        }
        viewHolder.listView.setClickable(false);
        viewHolder.listView.setEnabled(false);
        viewHolder.numTv.setText(String.valueOf(bean.getObj().get(position).getMpo_number()));
        viewHolder.typeTv.setText(MyPublic.getType(bean.getObj().get(position).getMpo_state()));
        List<List<DisplayAllUserOrdersBean.Obj1Bean>> list1 = bean.getObj1();
        for (int i = 0; i < list1.size(); i++) {
            List<DisplayAllUserOrdersBean.Obj1Bean> list2 = list1.get(i);
            if (list2 != null && list2.size() != 0) {
                if (bean.getObj().get(position).getMpo_id() == list2.get(0).getMpo_id()) {
                    viewHolder.listView.setAdapter(new AllOrderAdapter2(list2, context));
                    CltitudeUtil.setListViewHeightBasedOnChildren(viewHolder.listView);
                }
            }
        }

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Jump.toActivityCarryKey(context, OrderDetailsActivity.class, "mpoId", String.valueOf(bean.getObj().get(position).getMpo_id()));
            }
        });

        return convertView;
    }

    private class ViewHolder {
        LinearLayout linearLayout;
        TextView numTv, typeTv;
        ListView listView;
    }
}
