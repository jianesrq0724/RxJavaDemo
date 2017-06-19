package com.ruiqin.rxjavademo.activity.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.ruiqin.rxjavademo.R;
import com.ruiqin.rxjavademo.adapter.MainRecyclerViewAdapter;
import com.ruiqin.rxjavademo.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContact.View {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mPresenter.setAdapter(this, mRecyclerview);
    }

    /**
     * 设置适配器陈宫后，设置点击事件
     *
     * @param recyclerViewAdapter
     */
    @Override
    public void setAdapterSuccess(MainRecyclerViewAdapter recyclerViewAdapter) {

        /**
         * 设置点击事件
         */
        recyclerViewAdapter.setItemClickLister(new MainRecyclerViewAdapter.OnItemClickLister() {
            @Override
            public void onClick(View view, int position) {
                mPresenter.setItemClickLister(position);
            }
        });
    }
}
