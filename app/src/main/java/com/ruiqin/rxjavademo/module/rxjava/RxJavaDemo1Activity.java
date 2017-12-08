package com.ruiqin.rxjavademo.module.rxjava;

import android.os.SystemClock;
import android.view.View;

import com.ruiqin.rxjavademo.R;
import com.ruiqin.rxjavademo.base.BaseActivity;
import com.ruiqin.rxjavademo.commonality.view.LoadingDialog;
import com.ruiqin.rxjavademo.util.ToastUtils;

import java.util.concurrent.Callable;

import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemo1Activity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_demo1;
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
        Flowable.just(2).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(@NonNull Integer integer) throws Exception {
                return integer * integer;
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer s) throws Exception {
                        ToastUtils.showShort(s + "");
                    }
                });
    }

    /**
     * fromCallable
     */
    private void onClickBtn0() {
        showLoading();
        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                SystemClock.sleep(2000);
                return "Done";
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    ToastUtils.showShort(s);
                    cancelLoading();
                }, Throwable::printStackTrace);
    }

    LoadingDialog mLoadingDialog;

    private void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(mContext);
        }
        mLoadingDialog.show();
    }

    private void cancelLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.cancel();
        }
    }
}
