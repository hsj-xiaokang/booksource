package hsj.com.service_action.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hsj on 2018/1/5.
 * blog:http://blog.csdn.net/guolin_blog/article/details/9797169
 * 5.remote
 * 使用了远程Service后，MyService已经在另外一个进程当中运行了，所以只会阻塞该进程中的主线程，并不会影响到当前的应用程序。
 *
 * AIDL（Android Interface Definition Language）是Android接口定义语言的意思，
 * 它可以用于让某个Service与多个应用程序组件之间进行跨进程通信，从而可以实现多个应用程序共享同一个Service的功能。
 */

public class MyService_Remote extends Service{
    private static final String TAG = "MyService_Remote";
    private MyService_Remote.MyBinder_binder_remote mbd = new MyService_Remote.MyBinder_binder_remote();

    @Override
    public void onCreate() {
        Log.i(TAG,"oncreat!");
        super.onCreate();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mbd;
    }

    /**
     * 观察MyService中的代码，你会发现一直有一个onBind()方法我们都没有使用到，这个方法其实就是用于和Activity建立关联的
     * 绑定方式启动binder时候不会执行
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder_binder_remote extends Binder {
        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }
    }
}
