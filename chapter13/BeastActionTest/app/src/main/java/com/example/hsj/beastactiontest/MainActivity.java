package com.example.hsj.beastactiontest;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.hsj.beastactiontest.entity.Person;
import com.example.hsj.beastactiontest.utils.KillProcessUtil;
import com.example.hsj.beastactiontest.utils.LogUtil;
import com.example.hsj.beastactiontest.utils.MyApplication;

/**
 * @author  heshengjin
 * @date 2017-10-31
 * Intent传递值：
 *       1.Seriable 方式不好，不要使用！
 *       2.使用parceble方式！
 * 全局Context获取，见/utils/MyApplication.java说明
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    private int TIME = 1000 * 15;//1s*30
    private int TIME_count = 0;

    /**
     * 方式启动定时器比较AlarmManager
     * /LongRunningService.java
     */
    private  Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.intent_serializable).setOnClickListener(this);
        findViewById(R.id.intent_parceble).setOnClickListener(this);
        findViewById(R.id.alarm_test).setOnClickListener(this);

        //测试随时随地获取Context
        testMyContext();

        /**
        * 方式启动定时器比较AlarmManager
        * /LongRunningService.java
        */
        startHanderPostDelay();


    }

    /**
     * 事件
     * @param v
     */
    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.intent_serializable:
                 LogUtil.d(TAG,"onClick->Seriable 方式不好，不要使用！");
                 Toast.makeText(MyApplication.getContext(),"Seriable 方式不好，不要使用！",Toast.LENGTH_LONG).show();
                 break;
             case R.id.intent_parceble:
                 PersonActivityIntentBySerializable.startPersonActivityIntentBySerializable(new Person(){
                     {   setAge(18);
                         setName("hsj");
                         setAddr("kunming");
                     }
                 });
                 break;
             case R.id.alarm_test:
                 startService(new Intent(MyApplication.getContext(),LongRunningService.class));
                 break;
             default:break;
         }
    }

    /**
     * 方式启动定时器比较AlarmManager
     * /LongRunningService.java
     * 2.5分钟关闭
     */
   private void startHanderPostDelay(){
       handler = new Handler();
       Runnable runnable = new Runnable() {

           @Override
           public void run() {
               // handler自带方法实现定时器
               try {
                   TIME_count++;
                   if(TIME_count == 9){
                       new AlertDialog.Builder(MainActivity.this)
                                      .setTitle("please notice")
                                      .setMessage("30second later will close")
                                      .setPositiveButton("confirm", null)
                                      .show();
                   }
                   if(TIME_count == 10){
                       //移除定时
                       handler.removeCallbacks(this);
                       //销毁当前的activity
                       MainActivity.this.finish();
                       KillProcessUtil.killCurrentProcess();
                   }
                   handler.postDelayed(this, TIME);
               } catch (Exception e) {
                   //移除定时
                   handler.removeCallbacks(this);
                   //销毁当前的activity
                   MainActivity.this.finish();
                   KillProcessUtil.killCurrentProcess();
                   e.printStackTrace();
               }
           }
       };
       handler.postDelayed(runnable, TIME); //每隔30s执行
   }

    /**
     * 测试随时随地获取Context
     */
    private void testMyContext(){
        Toast.makeText(
                MyApplication.getContext(),
                MyApplication.getContext().getString(R.string.hello_world),
                Toast.LENGTH_LONG
        ).show();
    }


}
