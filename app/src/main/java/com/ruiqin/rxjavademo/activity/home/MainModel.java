package com.ruiqin.rxjavademo.activity.home;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MainModel implements MainContact.Model {

    @Override
    public List<String> initData() {
        List<String> mListData = new ArrayList<String>();
        mListData.add("Test");
        mListData.add("Test");
        mListData.add("Test");
        return mListData;
    }
}
