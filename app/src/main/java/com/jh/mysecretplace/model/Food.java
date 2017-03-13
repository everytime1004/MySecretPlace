package com.jh.mysecretplace.model;

/**
 * Created by love on 2017-03-11.
 */

public class Food {
    String mId;
    String mName;
    String mPrice;
    String mDescription;
    String mCategory;

    public Food(String id, String price, String name, String desc, String category){
        mId = id;
        mPrice = price;
        mName = name;
        mDescription = desc;
        mCategory = category;
    }
}
