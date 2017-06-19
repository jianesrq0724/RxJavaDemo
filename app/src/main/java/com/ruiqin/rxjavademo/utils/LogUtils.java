package com.ruiqin.rxjavademo.utils;

import android.util.Log;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class LogUtils {
    private static boolean isShow = true;

    public static void e(String TAG, String message) {
        if (isShow) {
            Log.e(TAG, message);
        }
    }

    public static void e(String message) {
        if (isShow) {
            Log.e("TAG", message);
        }
    }

    public static void i(String TAG, String message) {
        if (isShow) {
            Log.i(TAG, message);
        }
    }

    public static void i(String message) {
        if (isShow) {
            Log.i("TAG", message);
        }
    }

}
