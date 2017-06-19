package com.ruiqin.rxjavademo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ruiqin.rxjavademo.utils.InstanceUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {
    public P mPresenter;
    private Unbinder mBind;
    public Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        mContext = this;
        if (this instanceof BaseView) {
            mPresenter = InstanceUtil.getInstance(this, 0);
            mPresenter.setVM(this, InstanceUtil.getInstance(this, 1));
        }
        initView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();
}
