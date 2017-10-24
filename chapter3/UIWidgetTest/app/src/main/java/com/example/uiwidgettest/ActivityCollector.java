package com.example.uiwidgettest;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heshengjin
 * @date 2017-10-18
 * 管理activitys
 */
public class ActivityCollector {
    private static final String TAG = "ActivityCollector";

    public static List<Activity> activities = new ArrayList<>();

    /**
     * 添加一个activity
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
        Log.i(TAG, "addActivity: "+activity.getClass().getSimpleName());
    }

    /**
     * 移除一个activity
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
        Log.i(TAG, "removeActivity: "+activity.getClass().getSimpleName());
    }

    /**
     * 销毁所有的activity
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
                Log.i(TAG, "finishAll: "+activity.getClass().getSimpleName());
            }
        }
    }

}
