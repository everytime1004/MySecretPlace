package com.jh.mysecretplace.model;

/**
 * Created by love on 2017-01-11.
 */

public class Store {

    int mId;
    String mStoreName;
    String mStoreAddress;
    // 0 : 없음, 1 : 거의 없음, 2 : 좌석마다 대부분
    int mElectricCharge;
    // wifi 있나 없나
    boolean mWifi;
    // 술이 있나, 없나
    boolean mAlcohol;

    public Store(int id, String name, String address, int charge, boolean wifi, boolean alcohol){

    }

}
