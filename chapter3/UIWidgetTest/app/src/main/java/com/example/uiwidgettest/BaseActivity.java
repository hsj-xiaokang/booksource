package com.example.uiwidgettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author hsj
 * @date 2017-10-18
 * 基本的activity 添加一些公共的基本的方法
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    /**
     * 创建的时候添加一个activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "add a activity: "+this.getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    /**
     * 销毁一个activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "destroy a activity: "+this.getClass().getSimpleName());
        ActivityCollector.removeActivity(this);
    }


}
