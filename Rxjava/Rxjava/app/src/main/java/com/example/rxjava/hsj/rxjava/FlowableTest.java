package com.example.rxjava.hsj.rxjava;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * @author  hsj
 * @date 2017/11/1.
 */

public final class FlowableTest {
    private static final String TAG = "FlowableTest";

    /**
     * Flowable一般用法-常规
     * Flowable支持背压
     * //每次请求一个,支持背压
     Subscription sub.request(1);
     */
    public static void FlowableTestNormal(){
        //range(0,30)左闭右开
        Flowable.range(0,30)
                .subscribe(new Subscriber<Integer>() {
                    Subscription sub;
                    //当订阅后，会首先调用这个方法，其实就相当于onStart()，
                    //传入的Subscription s参数可以用于请求数据或者取消订阅
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.w("TAG","onsubscribe start");
                        sub=s;
                        //每次请求一个,支持背压
                        sub.request(1);
                        Log.w("TAG","onsubscribe end");
                    }

                    @Override
                    public void onNext(Integer o) {
                        Log.w("TAG","onNext--->"+o);
                        //每次请求一个,支持背压
                        sub.request(1);
                    }
                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }
                    @Override
                    public void onComplete() {
                        Log.w("TAG","onComplete");
                    }
                });
    }


    /**
     * Flowable一般用法-不常规
     * Flowable支持背压
     */
    public static void FlowableTestNotNormal(){
        Flowable mFlowable =  Flowable.create(new FlowableOnSubscribe<Integer>() {
                                                  @Override
                                                  public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                                                      e.onNext(1);
                                                      e.onNext(2);
                                                      e.onNext(3);
                                                      e.onNext(4);
                                                      e.onComplete();
                                                  }
                                              }
                //需要指定背压策略
                , BackpressureStrategy.BUFFER);

        Subscriber mSubscriber = new Subscriber<Integer>() {
            Subscription sub;
            //当订阅后，会首先调用这个方法，其实就相当于onStart()，
            //传入的Subscription s参数可以用于请求数据或者取消订阅
            @Override
            public void onSubscribe(Subscription s) {
                Log.w("TAG","onsubscribe start");
                sub=s;
                //每次请求一个
                sub.request(1);
                Log.w("TAG","onsubscribe end");
            }

            @Override
            public void onNext(Integer o) {
                Log.w("TAG","onNext--->"+o);
                //每次请求一个
                sub.request(1);
            }
            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
            @Override
            public void onComplete() {
                Log.w("TAG","onComplete");
            }
        };

        mFlowable.subscribe(mSubscriber);
    }
}
