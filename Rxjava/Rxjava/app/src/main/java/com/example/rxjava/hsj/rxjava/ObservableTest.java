package com.example.rxjava.hsj.rxjava;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author  hsj 
 * @date 2017/11/1.
 */

public final class ObservableTest {
    private static final String TAG = "ObservableTest";

    /**
     * Observable正常用法-不常规
     * Observable不支持背压
     * //每次请求一个,支持背压
     Subscription sub.request(1);
     */
    public static void ObservableTestNotNormal(){
        Observable mObservable= Observable.create(new ObservableOnSubscribe<Integer>() {
            //Emitter发射器
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        });

        Observer mObserver=new Observer<Integer>() {
            //这是新加入的方法，在订阅后发送数据之前，
            //回首先调用这个方法，而Disposable可用于取消订阅
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext value="+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        //关联观察者和被观察者
        mObservable.subscribe(mObserver);
    }

    /**
     * Observable正常用法-常规
     * Observable不支持背压
     * //每次请求一个,支持背压
     *Subscription sub.request(1);
     */
    public static void ObservableTestNormal(){
        Observable.range(0,30)
                  .subscribe(new Observer<Integer>() {
                      //这是新加入的方法，在订阅后发送数据之前，
                      //回首先调用这个方法，而Disposable可用于取消订阅
                      @Override
                      public void onSubscribe(Disposable d) {
                          Log.d(TAG, "onSubscribe");
                      }

                      @Override
                      public void onNext(Integer value) {
                          Log.d(TAG, "onNext value="+value);
                      }

                      @Override
                      public void onError(Throwable e) {
                          Log.d(TAG, "onError");
                      }

                      @Override
                      public void onComplete() {
                          Log.d(TAG, "onComplete");
                      }
                  });
    }
}
