package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 开机启动
 *  广播接收器不允许开启线程或者耗时的逻辑
 *
 *
 *
 *  -----------------------------静态广播接收器-------------------------
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot Complete（开机启动完成！）", Toast.LENGTH_LONG).show();
    }

}
