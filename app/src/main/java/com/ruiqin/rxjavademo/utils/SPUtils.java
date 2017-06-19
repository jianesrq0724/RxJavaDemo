package com.ruiqin.rxjavademo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ruiqin.shen
 * 类说明：SP保存
 */

public class SPUtils {
    static SharedPreferences sSharedPreferences;

    /**
     * 初始化，在MyApplication中
     * @param context
     */
    public static void init(Context context) {
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 保存String到SP中
     * @param name
     * @param content
     */
    public static void putString(String name, String content) {
        sSharedPreferences.edit().putString(name, content).apply();
    }

    /**
     * 保存Boolean到SP中
     * @param name
     * @param content
     */
    public static void putBoolean(String name, boolean content) {
        sSharedPreferences.edit().putBoolean(name, content).apply();
    }

    /**
     * 从SP中获取String
     * @param name
     * @return
     */
    public static String getString(String name) {
        return sSharedPreferences.getString(name, "");
    }

    /**
     * 从SP中获取Boolean
     * @param name
     * @return
     */
    public static boolean getBoolean(String name) {
        return sSharedPreferences.getBoolean(name, false);
    }

    /**
     * 清除保存到本地SP的数据
     */
    public static void clean() {
        sSharedPreferences.edit().clear();
    }
}
