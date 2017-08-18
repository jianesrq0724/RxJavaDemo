package com.ruiqin.rxjava.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ruiqin.rxjava.R;
import com.ruiqin.rxjava.base.BaseActivity;
import com.ruiqin.rxjava.module.home.adapter.MainRecyclerAdapter;
import com.ruiqin.rxjava.util.ToastUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.setAdapter();
    }

    @Override
    public boolean canBack() {
        mToolbarTitle.setText("RxJavaDemo");
        return false;
    }

    private long lastClickTime;

    @Override
    public void onBackPressed() {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - lastClickTime > 2000) {
            ToastUtils.showShort("再按一次退出");
            lastClickTime = currentClickTime;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setRecyclerAdapterSuccess(MainRecyclerAdapter mainRecyclerAdapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        mRecyclerView.setAdapter(mainRecyclerAdapter);
    }
}
