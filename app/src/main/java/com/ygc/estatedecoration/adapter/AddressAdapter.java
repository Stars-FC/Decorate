package com.ygc.estatedecoration.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.user_activity.EditAddressActivity;
import com.ygc.estatedecoration.widget.BasePopupWindow;

import java.util.List;
public class AddressAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private LinearLayout content_layout;
    private BasePopupWindow delAddressPopupWindow;

    public AddressAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, LinearLayout content_layout) {
        super(layoutResId, data);
        this.content_layout = content_layout;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        LinearLayout editAddressLl = helper.getView(R.id.edit_address_ll);
        LinearLayout delAddressLl = helper.getView(R.id.del_address_ll);

        editAddressLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditAddressActivity.class);
                intent.putExtra(EditAddressActivity.OPERATE_ADDRESS, 2);
                mContext.startActivity(intent);
            }
        });

        delAddressLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDelAddressPopupWindow();
            }
        });
    }

    private void showDelAddressPopupWindow() {
        if (delAddressPopupWindow == null) {
            initDelAddressPopupWindow();
        }
        delAddressPopupWindow.showAtLocation(content_layout, Gravity.CENTER, 0, 0);
    }

    private void initDelAddressPopupWindow() {
        delAddressPopupWindow = new BasePopupWindow(mContext);
        delAddressPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.popup_window_del_address, null);
        final LinearLayout content_layout = (LinearLayout) popupView.findViewById(R.id.content_layout);
        popupView.findViewById(R.id.sure_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delAddressPopupWindow.dismiss();

            }
        });
        popupView.findViewById(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delAddressPopupWindow.dismiss();
            }
        });
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int top = content_layout.getTop();
                int bottom = content_layout.getBottom();
                int left = content_layout.getLeft();
                int right = content_layout.getRight();
                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < top || y > bottom || x < left || x > right) {
                        delAddressPopupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        delAddressPopupWindow.setContentView(popupView);
        delAddressPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
    }
}
