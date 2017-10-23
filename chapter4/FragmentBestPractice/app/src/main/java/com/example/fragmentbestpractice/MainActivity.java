package com.example.fragmentbestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * sw->shortest width 最短宽度(最小宽度限制)
 * 最小宽度限制  layout-sw600dp
 *              小于600dp的加载layout下面的布局
 *              大于600dp的加载layout_sw600dp下面的布局
 * http://blog.csdn.net/mafei852213034/article/details/51190309
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
