package com.jh.mysecretplace.utils;

import android.text.TextUtils;

/**
 * Created by love on 2017-01-12.
 */

public class util {

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
