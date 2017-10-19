package com.example.activitylifecycletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.utils.ObjectUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{//alt+insert导入method

    public static final String TAG = "MainActivity";

    private  Button startNormalActivity;
    private  Button startDialogActivity;

    private final static String INSTANCESTATE_KEY = "hsj";
    private final static String INSTANCESTATE_VALUE = "hsj is smart!";

    /**
     * onCreate只会创建一次，除非内存不够被回收了才会重新创建
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        //这里获取相关的数据 见：onSaveInstanceState
        if(!ObjectUtils.isEmpty(savedInstanceState)){
            String instanceStr = savedInstanceState.getString(INSTANCESTATE_KEY);
            //只能在主线程里面更新UI，不然会崩溃
            Toast.makeText(this,instanceStr,Toast.LENGTH_SHORT).show();
        }
         //添加事件推荐
         startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
         startNormalActivity.setOnClickListener(this);
         startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);
         startDialogActivity.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    /**
     * 该方法用于活动之前有一个A activity的input，里面有数据，但是有一个B activity创建导致系统不够了销毁了A，再返回就重新创建一个A的activity实例导致数据丢失
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        保存数据
        outState.putString(INSTANCESTATE_KEY,INSTANCESTATE_VALUE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    /**
     * 事件
     * @param v
     */
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.start_normal_activity:
               Intent intent_normal = new Intent(MainActivity.this, NormalActivity.class);
               startActivity(intent_normal);
               break;
           case R.id.start_dialog_activity:
               Intent intent_dialog = new Intent(MainActivity.this, DialogActivity.class);
               startActivity(intent_dialog);
               break;
           default:break;
       }
    }
}
