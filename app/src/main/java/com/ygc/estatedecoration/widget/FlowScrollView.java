package com.ygc.estatedecoration.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;


public class FlowScrollView extends ScrollView {

    public void setTopView(View topView) {
        mTopView = topView;
    }

    public void setFlowView(View flowView) {
        mFlowView = flowView;
    }

    private View mTopView;

    private View mFlowView;

    public FlowScrollView(Context context) {
        super(context);
    }

    public FlowScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mTopView != null) {
            if(t >= mTopView.getHeight()) {
                mFlowView.setVisibility(View.VISIBLE);
            } else {
                mFlowView.setVisibility(View.GONE);
            }
        }
    }
}
