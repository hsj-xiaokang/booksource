package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * android的四大组件之一
 * 广播接收器
 * 分为两大类：
 *        有序和无序的广播
 * 两种注册方式：
 *         代码注册和AndroidManifest里面注册（动态广播接收器和静态广播接收器）
 *
 * 两种类型：
 *        本地广播和全局广播
 *
 *         广播接收器不允许开启线程或者耗时的逻辑
 */
public class MainActivity extends AppCompatActivity {

    /**--------------------------------2----------本地广播，只能动态，不能配置在Androidmanifest里面----------------
     * 本地广播
     * 只能动态
     */
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    /**
     * ----------------监听网络变化的广播---------1-----动态广播接收器---
     * @param savedInstanceState
     */
//    private IntentFilter intentFilter;
//    private NetworkChangeReceiver networkChangeReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**--------------------------------2----------本地广播，只能动态，不能配置在Androidmanifest里面----------------
         * 本地广播
         * 只能动态
         */
        localBroadcastManager = LocalBroadcastManager.getInstance(this); // 获取实例
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent); // 发送本地广播
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter); // 注册本地广播监听器


        /**
         * ----------------监听网络变化的广播注册-----1------动态广播接收器---
         */
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver, intentFilter);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();


        //--------------------------------2----------本地广播，只能动态，不能配置在Androidmanifest里面----------------
        localBroadcastManager.unregisterReceiver(localReceiver);

        /**
         * ---------------------监听网络变化的广播取消注册------------1-------动态广播接收器-----
         */
//        unregisterReceiver(networkChangeReceiver);

    }



    /**
     * --------------------------------2----------本地广播，只能动态，不能配置在Androidmanifest里面----------------
     */
    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * --------------------重写广播接收器---------1------动态广播接收器---
     */
    /*class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable",Toast.LENGTH_SHORT).show();
            }

        }

    }*/

}
