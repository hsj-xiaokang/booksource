package com.example.networktest.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author hsj
 * @date 2017/10/26.
 * com.google.common.util.concurrent.ThreadFactoryBuilder需要Google gua的jar包
 * 见：compile 'com.google.guava:guava:22.0-android'
 */

public class ThreadUtils {

    public static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("com.example.networktest-pool-%d").build();

    public static ExecutorService singleThreadPool = new ThreadPoolExecutor(
            1,
            1,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy()
    );

   public static void addRunable2ThreadPool(Runnable runnable){
       singleThreadPool.execute(runnable);
   }

    public static void shutdownThreadPool(){
        singleThreadPool.shutdown();
    }
}
