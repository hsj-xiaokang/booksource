package com.example.activitytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @author heshengjin
 * @date 2017-10-18
 * 第三个activity
 */
public class ThirdActivity extends BaseActivity {

    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Task id is " + getTaskId());
        setContentView(R.layout.third_layout);
        Button button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

    }
}
