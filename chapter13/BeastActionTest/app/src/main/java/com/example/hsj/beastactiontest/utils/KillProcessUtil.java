package com.example.hsj.beastactiontest.utils;

/**
 * @author  hsj on 2017/10/31.
 */

public final class KillProcessUtil {

    public static void killCurrentProcess(){
        /**
         * System.exit(0)是停止程序的虚拟机；
         Process.killProcess(Process.myPid()) 是通过PID去杀死进程。
         */
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
