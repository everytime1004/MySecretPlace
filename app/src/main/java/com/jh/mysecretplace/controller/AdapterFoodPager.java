package com.jh.mysecretplace.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jh.mysecretplace.R;
import com.jh.mysecretplace.view.MainActivity;
import com.jh.mysecretplace.view.ViewCustomRelativeLayout;

/**
 * Created by love on 2017-03-13.
 */

public class AdapterFoodPager extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private MainActivity context;
    private FragmentManager fragmentManager;
    private float scale;

    public AdapterFoodPager(MainActivity context, FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
//                MyViewPagerLinearLayout cur = getRootView(position);
//                MyViewPagerLinearLayout next = getRootView(position + 1);
//
//                cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
//                next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @SuppressWarnings("ConstantConditions")
    private ViewCustomRelativeLayout getRootView(int position) {
        return (ViewCustomRelativeLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.view_my_food_detail_containter);
    }

    private String getFragmentTag(int position) {
//        return "android:switcher:" + context.pager.getId() + ":" + position;
        return null;
    }
}