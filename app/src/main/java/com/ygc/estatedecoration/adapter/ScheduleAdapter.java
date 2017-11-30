package com.ygc.estatedecoration.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.ScheduleBean;

import java.util.List;

public class ScheduleAdapter extends BaseAdapter {
    private Activity activity;
    private List<ScheduleBean> list;

    public ScheduleAdapter(Activity activity, List<ScheduleBean> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView title, time, content, hollowCircle, btn;
        final View top, bottom;
        convertView = LayoutInflater.from(activity).inflate(R.layout.item_schedule, null);
        title = (TextView) convertView.findViewById(R.id.item_schedule_title);
        time = (TextView) convertView.findViewById(R.id.item_schedule_time);
        content = (TextView) convertView.findViewById(R.id.item_schedule_content);
        hollowCircle = (TextView) convertView.findViewById(R.id.item_schedule_hollow_circle);
        btn = (TextView) convertView.findViewById(R.id.item_schedule_btn);
        top = convertView.findViewById(R.id.item_schedule_left_top);
        bottom = convertView.findViewById(R.id.item_schedule_left_bottom);


        if (list.size() == 1) {
            bottom.setVisibility(View.INVISIBLE);
            top.setVisibility(View.INVISIBLE);
            btn.setVisibility(View.GONE);
        }
        if (list.get(position).getType().equals("0")) {
//            bottom.setVisibility(View.VISIBLE);
            top.setVisibility(View.INVISIBLE);
            btn.setVisibility(View.GONE);
            hollowCircle.setBackground(activity.getResources().getDrawable(R.drawable.shape_green_hollow_circle));
        } else if (list.get(position).getType().equals("1")) {
            btn.setText("编辑合同");
        } else if (list.get(position).getType().equals("2")) {
            btn.setText("查看合同");
        } else if (list.get(position).getType().equals("3")) {
            btn.setText("发起验收");
        } else if (list.get(position).getType().equals("6")){
            btn.setText("提醒验收");
        }else if (list.get(position).getType().equals("4")) {
            bottom.setVisibility(View.INVISIBLE);
            content.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
            btn.setText("去评价");
            hollowCircle.setBackground(activity.getResources().getDrawable(R.drawable.shape_gray_hollow_circle));
        }
        title.setText(list.get(position).getTitle());
        time.setText(list.get(position).getTime());
        content.setText(list.get(position).getContent());
        return convertView;
    }


}
