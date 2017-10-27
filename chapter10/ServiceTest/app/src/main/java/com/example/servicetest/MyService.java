package com.example.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * @author heshengjin
 * @date 2017-10-27
 *
 * stopSelf();自己停止自己
 * service只会有一个实例，不停止的时候不论启动多少次
 *
 *
 *
 * bindService和startService同时启动必须stopSelf()和unbindService(context)同时调用才会onDestroy
 *
 */
public class MyService extends Service {

    public MyService() {
    }

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }

    }

    /**
     * bindService时候调用onBind
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * bindService时候调用onCreate
     * startService时候调用onCreate
     * 创建一次
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate executed");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                //前台服务取消无效
                .setAutoCancel(true)
                .build();

         // 不同于通知的地方
         //启动NotificationManager
         // manager.notify(1, notification);
        startForeground(1, notification);

    }

    /**
     * 每次启动服务时候创建
     * startService时候调用onStartCommand
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }

}
