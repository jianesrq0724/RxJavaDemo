package com.ruiqin.rxjavademo.base;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public abstract class BasePresenter<V, M> {
    public V mView;
    public M mModel;

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onAttach();
    }

    public abstract void onAttach();

}
