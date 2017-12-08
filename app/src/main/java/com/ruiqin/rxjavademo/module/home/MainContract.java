package com.ruiqin.rxjavademo.module.home;

import com.ruiqin.rxjavademo.base.BaseModel;
import com.ruiqin.rxjavademo.base.BasePresenter;
import com.ruiqin.rxjavademo.base.BaseView;
import com.ruiqin.rxjavademo.module.home.adapter.MainRecyclerAdapter;
import com.ruiqin.rxjavademo.module.home.bean.MainRecyclerData;

import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public interface MainContract {
    interface Model extends BaseModel {
        List<MainRecyclerData> initData();
    }

    interface View extends BaseView {
        void setRecyclerAdapterSuccess(MainRecyclerAdapter mainRecyclerAdapter);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        abstract void setAdapter();
    }
}
