package com.example.hsj.beastactiontest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.hsj.beastactiontest.utils.LogUtil;

/**非绑定启动
 * @author heshengjin
 * @date 2017-10-31
 *
 * 比较另外一种定时器hander方式：
 * /MainActivity.java
 *
 */
public class LongRunningService extends Service {
    private static final String TAG = "LongRunningService";
    public LongRunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }

    /**
     * 每次启动服务时候创建,非绑定服务
     * startService时候调用onStartCommand
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG, "onSt artCommand executed every 每 30 秒！");
        /**
         * 耗时的线程操作
         * 比较另外一种定时器hander方式：
         * /MainActivity.java
         */
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long trrigerAlarTime = SystemClock.elapsedRealtime() + 1000*30;
        PendingIntent pi =  PendingIntent.getService(this,0,new Intent(this,LongRunningService.class),0);
        //setExact,setAndAllowWhileIdle,setExactAndAllowWhileIdle代替set保证准时执行，因为setExact会因为电池原因不会准时但是setExact从API 19开始才有
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,trrigerAlarTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy executed");
    }
}
