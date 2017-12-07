package com.ruiqin.rxjava.module.rxjava;

import android.os.Bundle;
import android.view.View;

import com.ruiqin.rxjava.R;
import com.ruiqin.rxjava.base.BaseActivity;
import com.ruiqin.rxjava.util.ToastUtils;

import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class RxJavaDemo0Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo0);

        Flowable.just("1234").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });

        Observable.just("122").just("123").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }


    @OnClick({R.id.btn_hello, R.id.btn_hello_lambda})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_hello:
                helloRxJava();
                break;
            case R.id.btn_hello_lambda:
                helloRxJavaLambda();
                break;
            default:
                break;
        }
    }

    /**
     * 输出HellowRxJava，未使用Lambda表达式
     */
    public void helloRxJava() {
        /**
         * 未使用Lambda表达式
         */
        Flowable.just("Hello RxJava").subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                ToastUtils.showShort(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

        Flowable.create((FlowableOnSubscribe<Integer>) e -> {

        }, BackpressureStrategy.BUFFER);

        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

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
