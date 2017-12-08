package com.ruiqin.rxjavademo.module.rxjava;

import android.view.View;

import com.ruiqin.rxjavademo.R;
import com.ruiqin.rxjavademo.base.BaseActivity;
import com.ruiqin.rxjavademo.util.LogUtils;
import com.ruiqin.rxjavademo.util.ToastUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BackPressureDemo extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_back_pressure_demo;
    }

    @OnClick({R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                onClickButton();
                break;
            case R.id.button2:
                onClickRequest();
            default:
                break;
        }
    }

    public void onClickButton() {
        ToastUtils.showShort("start");
//        testObservableBackPressure();
        testFlowableBackpressure();
    }


    @OnClick(R.id.button2)
    public void onClickRequest() {
        mSubscription.request(128);
    }

    private Subscription mSubscription;

    /**
     * Flowable
     */
    private void testFlowableBackpressure() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                }
            }
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.e("onSubscribe");
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.e("onNext: " + integer);
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
     * 测试背压
     */
    private void testObservableBackPressure() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(integer.toString());
                    }
                });
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }
}
