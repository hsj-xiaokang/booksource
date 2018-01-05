package hsj.com.service_action.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hsj on 2018/1/5.
 * 2.Service和Activity通信
 */

public class MyService_binder extends Service{
    private static final String TAG = "MyService_1";
    private MyBinder_binder mbd = new MyBinder_binder();

    @Override
    public void onCreate() {
        Log.i(TAG,"oncreat!");
        super.onCreate();
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

    public class MyBinder_binder extends Binder{
        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }
    }
}
