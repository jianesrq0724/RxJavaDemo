package com.ruiqin.rxjavademo.module.rxjava;

import android.view.View;

import com.ruiqin.rxjavademo.R;
import com.ruiqin.rxjavademo.base.BaseActivity;
import com.ruiqin.rxjavademo.util.LogUtils;
import com.ruiqin.rxjavademo.util.RxManage;
import com.ruiqin.rxjavademo.util.ToastUtils;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava标识符
 */
public class RxJavaIdentifierActivity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mToolbarTitle.setText("RxJava标识符");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_demo2;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.btn_0, R.id.btn_1, R.id.button2, R.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                onClickBtn0();
                break;
            case R.id.btn_1:
                onClickBtn1();
                break;
            case R.id.button2:
                onClickBtn2();
                break;
            case R.id.button3:
                onClickBtn3();
                break;
            default:
                break;
        }
    }

    /**
     * 学生类
     */
    class Student {
        /**
         * 姓名
         */
        private String name;

        /**
         * 年龄
         */
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


    /**
     * flatMap
     */
    private void onClickBtn3() {
        List<Student> students = new ArrayList<Student>();
        for (int i = 0; i < 6; i++) {
            students.add(new Student("test" + i, 24 + i));
        }

        Flowable.timer(2, TimeUnit.MICROSECONDS)
                .flatMap(new Function<Long, Publisher<List<Student>>>() {
                    @Override
                    public Publisher<List<Student>> apply(Long aLong) throws Exception {
                        return Flowable.just(students);
                    }
                }).observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Student>>() {
                    @Override
                    public void accept(List<Student> students) throws Exception {
                        for (Student student : students) {
                            LogUtils.e(student.toString());
                        }
                    }
                }, Throwable::printStackTrace);

    }

    /**
     * map
     * 值转换、类型转换
     */
    private void onClickBtn2() {


        Flowable.just(new Student("ruiqin", 24))
                .map(new Function<Student, String>() {
                    @Override
                    public String apply(Student student) throws Exception {
                        String name = student.name;
                        return name;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String name) throws Exception {
                ToastUtils.showShort(name);
            }
        });

    }

    RxManage mRxManage = new RxManage();


    /**
     * 点击事件1
     */
    private void onClickBtn1() {
        Disposable disposable = Flowable.interval(1 * 1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> LogUtils.e("当前次数: " + aLong)
                        , Throwable::printStackTrace);
        mRxManage.add(disposable);
    }

    /**
     * 点击事件0
     * time默认实在分线程中运行
     * 接受者需要主动切换到主线程中
     */
    private void onClickBtn0() {
        Flowable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        ToastUtils.showShort("延迟1s操作");
                    }
                }, Throwable::printStackTrace);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManage.clear();
    }
}
