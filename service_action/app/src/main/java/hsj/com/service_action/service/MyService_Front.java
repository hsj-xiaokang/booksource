package hsj.com.service_action.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.File;

import hsj.com.service_action.MainActivity;
import hsj.com.service_action.R;

/**
 * Created by hsj on 2018/1/5.
 * 4.创建前台Service--区别就是在onCreate里面的时候
 *
 *
 * Service几乎都是在后台运行的，一直以来它都是默默地做着辛苦的工作。
 * 但是Service的系统优先级还是比较低的，当系统出现内存不足情况时，就有可能会回收掉正在后台运行的Service。
 * 如果你希望Service可以一直保持运行状态，而不会由于系统内存不足的原因导致被回收，就可以考虑使用前台Service。
 * 前台Service和普通Service最大的区别就在于，它会一直有一个正在运行的图标在系统的状态栏显示，
 * 下拉状态栏后可以看到更加详细的信息，非常类似于通知的效果。当然有时候你也可能不仅仅是为了防止Service被回收才使用前台Service，
 * 有些项目由于特殊的需求会要求必须使用前台Service，比如说墨迹天气，它的Service在后台更新天气数据的同时，
 * 还会在系统状态栏一直显示当前天气的信息
 *
 */

public class MyService_Front extends Service {
    private static final String TAG = "MyService_Front";
    public final static int NT_1 = 1;
    private MyService_Front.MyBinder_binder_front mbd = new MyService_Front.MyBinder_binder_front();

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         *创建Notification
         */
        Intent intent = new Intent(this, MainActivity.class);
        //设置可以点击intent【延迟的intent】
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        //获取NotificationManager
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //构建Notification
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                //设置之后会取消
                .setAutoCancel(true)
                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .setLights(Color.GREEN, 1000, 1000)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        //启动到前台-不会取消-一直显示
        startForeground(NT_1, notification);
        Log.d(TAG, "onCreate() executed");
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


    public class MyBinder_binder_front extends Binder {
        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }
    }
}
