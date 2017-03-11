package com.jh.mysecretplace.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by love on 2017-01-12.
 */

public class DLog {
    /**
     * Log Level Error
     **/
    public static final void e(Context context, String message) {
        if (BuildConfig.DEBUG) Log.e(context.getClass().getSimpleName(), message);
    }

    /**
     * Log Level Warning
     **/
    public static final void w(Context context, String message) {
        if (BuildConfig.DEBUG) Log.w(context.getClass().getSimpleName(), message);
    }

    /**
     * Log Level Information
     **/
    public static final void i(Context context, String message) {
        if (BuildConfig.DEBUG) Log.i(context.getClass().getSimpleName(), message);
    }

    /**
     * Log Level Debug
     **/
    public static final void d(Context context, String message) {
        if (BuildConfig.DEBUG) Log.d("MysecretTest", message);
    }

    /**
     * Log Level Verbose
     **/
    public static final void v(Context context, String message) {
        if (BuildConfig.DEBUG) Log.v(context.getClass().getSimpleName(), message);
    }

    /**
     * Log Level Error
     **/
    public static final void e(String TAG, String message) {
        if (BuildConfig.DEBUG) Log.e(TAG, message);
    }

    /**
     * Log Level Warning
     **/
    public static final void w(String TAG, String message) {
        if (BuildConfig.DEBUG) Log.w(TAG, message);
    }

    /**
     * Log Level Information
     **/
    public static final void i(String TAG, String message) {
        if (BuildConfig.DEBUG) Log.i(TAG, message);
    }

    /**
     * Log Level Debug
     **/
    public static final void d(String TAG, String message) {
        if (BuildConfig.DEBUG) Log.d(TAG, message);
    }

    /**
     * Log Level Verbose
     **/
    public static final void v(String TAG, String message) {
        if (BuildConfig.DEBUG) Log.v(TAG, message);
    }


    public final class BuildConfig {
        public final static boolean DEBUG = true;


        /**
         * 현재 디버그모드여부를 리턴
         *
         * @param context
         * @return
         */
        private boolean isDebuggable(Context context) {
            boolean debuggable = false;

            PackageManager pm = context.getPackageManager();
            try {
                ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
                debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
            } catch (PackageManager.NameNotFoundException e) {
        /* debuggable variable will remain false */
            }

            return debuggable;
        }
    }
}
