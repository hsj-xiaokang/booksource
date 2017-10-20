package com.example.uicustomviews;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.uicustomviews.counstomEvents.EditEventsProcess;

/**
 * 自定义组件
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 自定义的组件
     * 设置setEditEventsProcess(EditEventsProcess editEventsProcess)
     */
    private TitleLayout titleLayout;

    private static final String TAG = "MainActivity";
    private final static String EDIT = "edit";
    private final static String BACK = "back";
    //测试自定义的edit事件
    private final static String TEST_COUNSTOM = "TEST_COUNSTOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }

        //设置自定义的组件的标题
        titleLayout = (TitleLayout) findViewById(R.id.title_globle);
        titleLayout.setTextView(TAG);
        titleLayout.setTitleEdit(EDIT);
        titleLayout.setTitleBack(BACK);
        //初始化EditEventsProcess,重写EditEventsProcess.doEvents方法
        //设置setEditEventsProcess(EditEventsProcess editEventsProcess)
        titleLayout.setEditEventsProcess(new EditEventsProcess(){

            @Override
            public void doEvents() {
                Toast.makeText(MainActivity.this,TEST_COUNSTOM,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
