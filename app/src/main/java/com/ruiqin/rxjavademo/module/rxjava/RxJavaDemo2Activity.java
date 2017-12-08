package com.ruiqin.rxjavademo.module.rxjava;

import android.view.View;

import com.ruiqin.rxjavademo.R;
import com.ruiqin.rxjavademo.base.BaseActivity;
import com.ruiqin.rxjavademo.commonality.view.LoadingDialog;
import com.ruiqin.rxjavademo.util.ToastUtils;

import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class RxJavaDemo2Activity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_demo2;
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
                break;
            default:
                break;
        }
    }

    /**
     * 点击事件0
     * time默认实在分线程中运行
     * 接受者需要主动切换到主线程中
     */
    private void onClickBtn0() {
        Flowable.timer(5000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        ToastUtils.showShort("延迟操作");
                    }
                }, Throwable::printStackTrace);
    }

    /**
     * 加载进度条
     */
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
