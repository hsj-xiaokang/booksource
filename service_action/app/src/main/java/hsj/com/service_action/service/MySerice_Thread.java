package hsj.com.service_action.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import hsj.com.service_action.ThreadPool.ThreadPoolUtils;

/**
 * Created by hsj on 2018/1/5.
 * 3.Service和Thread的关系---因为Service和Thread之间没有任何关系！--Service其实是运行在主线程里的
 *
 * 它们的线程id完全是一样的，由此证实了Service确实是运行在主线程里的，
 * 也就是说如果你在Service里编写了非常耗时的代码，程序必定会出现ANR的
 * 我们可以在Service中再创建一个子线程，然后在这里去处理耗时逻辑就没问题了
 *
 * 既然在Service里也要创建一个子线程，那为什么不直接在Activity里创建呢？
 * 这是因为Activity很难对Thread进行控制，当Activity被销毁之后，就没有任何其它的办法可以再重新获取到之前创建的子线程的实例。
 * 而且在一个Activity中创建的子线程，另一个Activity无法对其进行操作。
 * 但是Service就不同了，所有的Activity都可以与Service进行关联，然后可以很方便地操作其中的方法，即使Activity被销毁了，
 * 之后只要重新与Service建立关联，就又能够获取到原有的Service中Binder的实例。
 * 因此，使用Service来处理后台任务，Activity就可以放心地finish，完全不需要担心无法对后台任务进行控制的情况
 */

public class MySerice_Thread extends Service {
    private static final String TAG = "MySerice_Thread";
    private MyBinder mbd = new MyBinder();

    /**
     * 只会创建一次
     */
    @Override
    public void onCreate() {
        super.onCreate();
        ThreadPoolUtils.exeRunnable(new Runnable() {
            @Override
            public void run() {
                // 开始执行后台任务
            }
        });
        Log.i(TAG, "thread id is=" + Thread.currentThread().getId() + " thread name is=" + Thread.currentThread().getName());
        Log.d(TAG, "onCreate() executed");
    }

    /**
     * 绑定方式启动binder时候不会执行
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mbd;
    }

    class MyBinder extends Binder {

        public void startDownload() {
            ThreadPoolUtils.exeRunnable(new Runnable() {
                @Override
                public void run() {
                    // 执行具体的下载任务
                }
            });
        }
    }
}
