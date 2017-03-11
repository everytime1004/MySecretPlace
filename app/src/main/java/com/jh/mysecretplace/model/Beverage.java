package com.jh.mysecretplace.model;

/**
 * Created by love on 2017-03-11.
 */

public class Beverage {
    String mId;
    String mName;
    String mContent;
    String mDescription;
    String mCategory;

    public Beverage(String id, String name, String content, String desc, String category){
        mId = id;
        mName = name;
        mContent = content;
        mDescription = desc;
        mCategory = category;
    }
}
