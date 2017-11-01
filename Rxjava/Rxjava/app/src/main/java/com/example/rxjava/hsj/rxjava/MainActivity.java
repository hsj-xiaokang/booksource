package com.example.rxjava.hsj.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * rxjava 2.0+
 * continued support for Java 6+ & Android 2.3+
 * https://zhuanlan.zhihu.com/p/24482660?refer=dreawer
 *
 * 号称东半球最佳实践博客：http://blog.csdn.net/theone10211024/article/details/50435325

 首先声明，RxJava以观察者模式为骨架，在2.0中依然如此。
 不过此次更新中，出现了两种观察者模式：
 Observable(被观察者)/Observer（观察者）
 Flowable(被观察者)/Subscriber(观察者)


 >1000考虑背压方式
 背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Observable正常用法-不常规
        ObservableTest.ObservableTestNotNormal();
        //Observable正常用法-常规
        ObservableTest.ObservableTestNormal();
        //Flowable一般用法-常规
        FlowableTest.FlowableTestNormal();
        //Flowable一般用法-不常规
        FlowableTest.FlowableTestNotNormal();
    }
    /**
     * 操作符相关

     这一块其实可以说没什么改动，大部分之前你用过的操作符都没变，即使有所变动，也只是包名或类名的改动。大家可能经常用到的就是Action和Function。

     Action相关
     之前我在文章里介绍过关于Action这类接口，在1.0中，这类接口是从Action0，Action1...往后排（数字代表可接受的参数），现在做出了改动

     Rx1.0-----------Rx2.0

     Action0--------Action
     Action1--------Consumer
     Action2--------BiConsumer
     后面的Action都去掉了，只保留了ActionN
     */

}
