package com.example.hsj.beastactiontest.utils;

import android.app.Application;
import android.content.Context;

/**
 * @author hsj
 * @date 2017/10/31
 * 随时随地获取Context
 * 在AndroidManifest.xml里面配置 android:name="com.example.hsj.beastactiontest.utils.MyApplication"
 * litepal也是一样的原理（android:name="org.litepal.LitePalApplication"），使用litepal时候，需要注意一件事，android:name只能有一个，咋办？
 * 见详解注释处.
 *
 */

public class MyApplication extends Application{
    /**
     * 全局的context
     */
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        /**
         * 详解注释处
         * 相当于我们全局的Context传递给LitePal，和android:name="org.litepal.LitePalApplication"一样
         */
        // LitePalApplication.initialize(context);

    }

    public static Context getContext(){
        return context;
    }

}
