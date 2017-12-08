package com.ruiqin.rxjavademo.module.rxjava;

import android.view.View;

import com.ruiqin.rxjavademo.R;
import com.ruiqin.rxjavademo.base.BaseActivity;
import com.ruiqin.rxjavademo.util.LogUtils;
import com.ruiqin.rxjavademo.util.ToastUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Callable;

import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * RxJava基础
 */

public class RxJavaBasicActivity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mToolbarTitle.setText("RxJava基础");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rxjava_basic;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.button0, R.id.button1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button0:
//                testCreateObservable();
//                testCreateFlwoable();
//                testJustFlowable();
                testFromCallable();
                break;
            case R.id.button1:
                testConsumer();
                break;
            default:
                break;
        }
    }

    /**
     * 简略写法
     */
    private void testConsumer() {
//        Observable.just("123").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                LogUtils.e(s);
//            }
//        });

        Flowable.just("123").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.e(s);
            }
        }, Throwable::printStackTrace);
    }


    /**
     *
     */
    private void testFromCallable() {
        Flowable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "test fromCallable";
            }
        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onSubscribe(Subscription s) {
                LogUtils.e("onSubscribe ");
            }

            @Override
            public void onNext(Object o) {
                LogUtils.e("onNext " + o.toString());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void testJustFlowable() {
        Flowable.just("F 123").subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                LogUtils.e("F subscription: " + s);

            }

            @Override
            public void onNext(String s) {
                LogUtils.e("F onNext: " + s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 创建Observable
     */
    private void testCreateObservable() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onNext("O 123");
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("O subscription: ");
            }

            @Override
            public void onNext(Object o) {
                LogUtils.e("O onNext: " + o.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 创建Flwoable
     */
    private void testCreateFlwoable() {

        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> e) throws Exception {
                e.onNext("F 123");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.e("subscription: ");
                    }

                    @Override
                    public void onNext(Object o) {
                        LogUtils.e("onNext: " + o.toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.e("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 输入Hellow RxJava，使用Lambda表达式
     */
    public void helloRxJavaLambda() {
        Flowable.just("Hello RxJava").subscribe(s -> {
            ToastUtils.showShort(s);
        }, Throwable::printStackTrace);
    }


}
