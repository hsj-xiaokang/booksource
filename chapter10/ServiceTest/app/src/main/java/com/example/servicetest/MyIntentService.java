package com.example.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * @author hsj
 * @date 2017-10-27
 * 这种服务自己会停止不需要调用stopSelf();自己停止自己
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService"); // 调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // 打印当前线程的id
        Log.d("MyIntentService", "Thread id is " + Thread.currentThread(). getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy executed");
    }

}
