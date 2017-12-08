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

public class RxJavaDemo0Activity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_demo0;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.button0, R.id.button1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button0:
                testCreateFlwoable();
                break;
            case R.id.button1:
                break;
            default:
                break;
        }
    }


    /**
     * 创建Flwoable
     */
    private void testCreateFlwoable() {

        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> e) throws Exception {
                e.onNext("123");
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
