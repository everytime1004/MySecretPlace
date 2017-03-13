package com.jh.mysecretplace.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.jh.mysecretplace.controller.AdapterFoodPager;

/**
 * Created by love on 2017-03-13.
 */

public class ViewCustomRelativeLayout extends RelativeLayout{
    private float scale = AdapterFoodPager.BIG_SCALE;
    /*
        1 : list
        2 : pager
     */
    private int mViewType;

    private final int VIEW_LIST_LAYOUT = 1;
    private final int VIEW_PAGER_LAYOUT = 2;

    public ViewCustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewCustomRelativeLayout(Context context) {
        super(context);
    }

    public void setScaleBoth(float scale, int type) {
        this.mViewType = type;
        this.scale = scale;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // The main mechanism to display scale animation, you can customize it as your needs
        int w = this.getWidth();
        int h = this.getHeight();
        if(mViewType == VIEW_LIST_LAYOUT){
            canvas.scale(scale, scale , w, h);
        }else{
            canvas.scale(scale, scale , w/2, h/2);
        }
    }
}
