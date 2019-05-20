package com.jni.org.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.jni.org.R;


public class RxJavaActivity_X extends AppCompatActivity {
    public static final String TAG = RxJavaActivity_X.class.getSimpleName();

    @BindView(R.id.btn_1)
    Button btn_1;

    @OnClick(R.id.btn_1)
    void btn1_clicked() {
        // test1();
        // test2();
        test3();
    }

    // 切断的操作，让Observer观察者不再接收上游事件
    private void test3() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello");
                emitter.onNext("java");
                emitter.onNext("C++");
                emitter.onComplete();// 会触发接受者的onComplete，后续的发射事件还是会被发射
                emitter.onNext("PHP");
            }
        }).subscribe(new Observer<String>() {
            private Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                this.d = d;
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "s: " + s);
                if ("java".equalsIgnoreCase(s)) {
                    //d.dispose();// 不在接受后续的回调
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    private void test2() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello test2");
                emitter.onComplete();// 触发收受者的onComplete
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });

    }

    /**
     * ObservableOnSubscribe.subscribe
     * 默认是在执行subscribe方法的线程里面执行的
     */
    private void test1() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello Rxjava");
                Log.i(TAG, "Thread name: " + Thread.currentThread().getName());
            }
        }).observeOn(/*回调的执行线程*/AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(RxJavaActivity_X.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java__x);
        ButterKnife.bind(this);
    }
}
