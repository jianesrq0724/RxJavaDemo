package com.ruiqin.rxjavademo.util;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 类描述:
 *
 * @author : ruiqin.shen
 * @date : 2017/12/11
 */
public class RxManage {
    private CompositeDisposable subscriptions = new CompositeDisposable();

    public RxManage() {
    }

    public void add(Disposable m) {
        if(this.subscriptions == null) {
            this.subscriptions = new CompositeDisposable();
        }

        if(m != null && !this.subscriptions.isDisposed()) {
            this.subscriptions.add(m);
        }

    }

    public void clear() {
        if(this.subscriptions != null) {
            this.subscriptions.dispose();
            this.subscriptions = null;
        }

    }

    public void addSubscription(Flowable observable, ResourceSubscriber subscriber) {
        if(observable != null && subscriber != null) {
            this.subscriptions.add((Disposable)observable.compose(RxUtils.rxScheduler()).subscribeWith(subscriber));
        }

    }
}
