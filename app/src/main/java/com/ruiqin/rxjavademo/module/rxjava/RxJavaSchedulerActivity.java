package com.ruiqin.rxjavademo.module.rxjava;

import android.os.SystemClock;
import android.view.View;

import com.ruiqin.rxjavademo.R;
import com.ruiqin.rxjavademo.base.BaseActivity;
import com.ruiqin.rxjavademo.interfaces.ILoading;
import com.ruiqin.rxjavademo.util.LogUtils;
import com.ruiqin.rxjavademo.util.RxUtils;
import com.ruiqin.rxjavademo.util.ToastUtils;

import java.util.concurrent.Callable;

import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程调用
 */
public class RxJavaSchedulerActivity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mToolbarTitle.setText("RxJava线程调用");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rxjava_scheduler;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }


    @OnClick({R.id.btn_0, R.id.btn_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                onClickBtn0();
                break;
            case R.id.btn_1:
                onClickBtn1();
                break;
            default:
                break;
        }
    }

    /**
     * map
     */
    private void onClickBtn1() {
        LogUtils.e("1 Thread: " + Thread.currentThread());

        showLoading();

        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                SystemClock.sleep(2000);
                LogUtils.e("2 Thread: " + Thread.currentThread());
                return "Done";
            }
        })
                .compose(RxUtils.getScheduler(true, (ILoading) mContext))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        dismissLoading();
                        LogUtils.e("3 Thread: " + Thread.currentThread());
                        ToastUtils.showShort(s);
                    }
                }, Throwable::printStackTrace);
    }

    /**
     * fromCallable
     */
    private void onClickBtn0() {
        LogUtils.e("1 Thread: " + Thread.currentThread());

        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                SystemClock.sleep(2000);
                LogUtils.e("2 Thread: " + Thread.currentThread());
                return "Done";
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.e("3 Thread: " + Thread.currentThread());
                        ToastUtils.showShort(s);
                    }
                }, Throwable::printStackTrace);
    }


}
