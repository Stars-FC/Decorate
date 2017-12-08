package com.ygc.estatedecoration.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.DisplayAllUserOrdersBean;
import com.ygc.estatedecoration.utils.MyPublic;

import java.util.List;


public class AllOrderAdapter2 extends BaseAdapter {
    private List<DisplayAllUserOrdersBean.Obj1Bean> list;
    private Context context;

    public AllOrderAdapter2(List<DisplayAllUserOrdersBean.Obj1Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order2, null);
            viewHolder = new ViewHolder();
            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.flower_icon);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.flower_name);
            viewHolder.describeTv = (TextView) convertView.findViewById(R.id.flower_describe);
            viewHolder.moneyTv = (TextView) convertView.findViewById(R.id.flower_money);
            viewHolder.numberTv = (TextView) convertView.findViewById(R.id.flower_number);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DisplayAllUserOrdersBean.Obj1Bean bean = list.get(position);
        MyPublic.loadImg(bean.getC_cover(),viewHolder.iconImg);
        viewHolder.nameTv.setText(String.valueOf(bean.getC_name()));
        viewHolder.describeTv.setText(String.valueOf(bean.getC_describe()));
        viewHolder.moneyTv.setText(String.valueOf("￥"+bean.getUnit_price()));
        viewHolder.numberTv.setText(String.valueOf("x"+bean.getMog_count()));

        return convertView;
    }

    private class ViewHolder {
        ImageView iconImg;
        TextView nameTv, describeTv, moneyTv, numberTv;
    }
}
