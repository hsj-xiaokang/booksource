package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HelloWorldActivity extends AppCompatActivity {
     //logt   ---->    ctrl+alt+space
     private static final String TAG = "HelloWorldActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //查看方法的参数和返回值  ---->   ctrl+alt+space
        setContentView(R.layout.hello_world_layout);
        Log.d(TAG, "onCreate execute");
    }

}
