package com.example.runtimepermissiontest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsj on 2017/10/25.
 */

public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    //获取当前栈顶activity
    public static Activity getTopActivity(){
        if (activityList.isEmpty()){
            return null;
        }else {
            return activityList.get(activityList.size()-1);
        }
    }
}
