package com.ruiqin.rxjavademo.activity.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ruiqin.rxjavademo.adapter.MainRecyclerViewAdapter;
import com.ruiqin.rxjavademo.utils.ToastUtils;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MainPresenter extends MainContact.Presenter {
    @Override
    public void onAttach() {

    }


    @Override
    void setAdapter(Context context, RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        MainRecyclerViewAdapter recyclerViewAdapter = new MainRecyclerViewAdapter(mModel.initData());
        recyclerView.setAdapter(recyclerViewAdapter);

        mView.setAdapterSuccess(recyclerViewAdapter);

    }

    @Override
    void setItemClickLister(int position) {
        ToastUtils.show(position + "");
    }
}
