package com.ruiqin.rxjavademo.activity.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.ruiqin.rxjavademo.adapter.MainRecyclerViewAdapter;
import com.ruiqin.rxjavademo.base.BaseModel;
import com.ruiqin.rxjavademo.base.BasePresenter;
import com.ruiqin.rxjavademo.base.BaseView;

import java.util.List;


/**
 * Created by ruiqin.shen
 * 类说明：
 */

public interface MainContact {

    interface Model extends BaseModel {
        List<String> initData();
    }

    interface View extends BaseView {
        void setAdapterSuccess(MainRecyclerViewAdapter recyclerViewAdapter);
    }


    abstract class Presenter extends BasePresenter<View, Model> {
        abstract void setAdapter(Context context, RecyclerView recyclerView);

        abstract void setItemClickLister(int position);
    }

}
