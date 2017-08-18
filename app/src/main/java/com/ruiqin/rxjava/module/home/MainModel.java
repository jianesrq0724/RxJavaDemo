package com.ruiqin.rxjava.module.home;

import com.ruiqin.rxjava.module.home.bean.MainRecyclerData;
import com.ruiqin.rxjava.module.rxjava.RxJavaDemo0Activity;
import com.ruiqin.rxjava.module.rxjava.RxJavaDemo1Activity;
import com.ruiqin.rxjava.module.rxjava.RxJavaDemo2Activity;

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
        recyclerDataList.add(new MainRecyclerData("RxJava基础", RxJavaDemo0Activity.class));
        recyclerDataList.add(new MainRecyclerData("RxJava异步调用", RxJavaDemo1Activity.class));
        recyclerDataList.add(new MainRecyclerData("RxJava标识符", RxJavaDemo2Activity.class));
        return recyclerDataList;
    }
}
