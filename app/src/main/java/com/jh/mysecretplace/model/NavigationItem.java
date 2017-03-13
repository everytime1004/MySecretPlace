package com.jh.mysecretplace.model;

import android.graphics.drawable.Drawable;

/**
 * Created by love on 2017-03-11.
 */

public class NavigationItem {

    private Drawable mIcon;
    private String mTitle;

    public NavigationItem(Drawable icon, String title) {
        mIcon = icon;
        mTitle = title;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
