package com.jh.mysecretplace.model;

/**
 * Created by love on 2017-01-11.
 */

public class User {

    String mId;
    String mName;
    String mEmail;
    // 0 : 남, 1 : 여자
    int mGender;
    int mAge;

    public User(String id, String name, String email, int gender, int age){
        mId = id;
        mName = name;
        mEmail = email;
        mGender = gender;
        mAge = age;
    }

}
