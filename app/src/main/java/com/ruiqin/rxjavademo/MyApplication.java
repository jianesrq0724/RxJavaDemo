package com.ruiqin.rxjavademo;

import android.app.Application;
import android.content.Context;

import com.ruiqin.rxjavademo.utils.SPUtils;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        SPUtils.init(mContext);
    }

    public static Context getContext() {
        return mContext;
    }
}
