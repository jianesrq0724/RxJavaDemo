package com.ruiqin.rxjavademo.module.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ruiqin.rxjavademo.R;
import com.ruiqin.rxjavademo.base.BaseActivity;
import com.ruiqin.rxjavademo.module.BlankFragment;
import com.ruiqin.rxjavademo.module.home.adapter.MainRecyclerAdapter;
import com.ruiqin.rxjavademo.util.ToastUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment;
    }

    @Override
    protected void initData() {
        mPresenter.setAdapter();
    }

    @Override
    protected void initView() {
        addFragment(new BlankFragment());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean canBack() {
        mToolbarTitle.setText("BaseProject");
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
