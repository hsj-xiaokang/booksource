package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/**
 * @author  heshengjin
 * @date 2017-10-18
 * 第二个activity
 */
public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    /**
     * 提供给其他的activity调用
     * @param context
     * @param data1
     * @param data2
     */
    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }

    /**
     * 创建activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Task id is " + getTaskId());
        setContentView(R.layout.second_layout);

        Intent i =  getIntent();
        String str = i.getStringExtra("param1");
        //toast测试intent参数获取
        Toast.makeText(SecondActivity.this,str,Toast.LENGTH_SHORT).show();

        //添加事件按钮
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "onDestroy");
    }

    /**
     * 带参数的返回第一个activity
     * 相当于back按钮键
     * 不覆盖的时会自己调用 finish();
     */
    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed method");
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hello FirstActivity i am back from SecondActivity");
        setResult(RESULT_OK, intent);
        finish();
    }

}
