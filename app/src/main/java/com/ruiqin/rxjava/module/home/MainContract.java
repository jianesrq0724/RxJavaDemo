package com.ruiqin.rxjava.module.home;

import com.ruiqin.rxjava.base.BaseModel;
import com.ruiqin.rxjava.base.BasePresenter;
import com.ruiqin.rxjava.base.BaseView;
import com.ruiqin.rxjava.module.home.adapter.MainRecyclerAdapter;
import com.ruiqin.rxjava.module.home.bean.MainRecyclerData;

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
