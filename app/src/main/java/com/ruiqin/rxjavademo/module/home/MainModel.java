package com.ruiqin.rxjavademo.module.home;

import com.ruiqin.rxjavademo.module.home.bean.MainRecyclerData;
import com.ruiqin.rxjavademo.module.rxjava.BackPressureDemo;
import com.ruiqin.rxjavademo.module.rxjava.RxJavaBasicActivity;
import com.ruiqin.rxjavademo.module.rxjava.RxJavaIdentifierActivity;
import com.ruiqin.rxjavademo.module.rxjava.RxJavaSchedulerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MainModel implements MainContract.Model {
    @Override
    public List<MainRecyclerData> initData() {
        List<MainRecyclerData> recyclerDataList = new ArrayList<>();
        recyclerDataList.add(new MainRecyclerData("RxJava基础", RxJavaBasicActivity.class));
        recyclerDataList.add(new MainRecyclerData("RxJava线程调用", RxJavaSchedulerActivity.class));
        recyclerDataList.add(new MainRecyclerData("RxJava标识符", RxJavaIdentifierActivity.class));
        recyclerDataList.add(new MainRecyclerData("BackPressure", BackPressureDemo.class));
        return recyclerDataList;
    }
}
