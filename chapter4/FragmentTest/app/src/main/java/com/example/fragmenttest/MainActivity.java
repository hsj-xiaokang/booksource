package com.example.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //去掉标题栏无效果 必须在setContentView前
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);

        //动态的添加碎片
//        replaceFragment(new RightFragment());

        //左边碎片的按钮
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    /**
     * 单击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                //动态添加碎片
                replaceFragment(new AnotherRightFragment());

                /**
                 * activity里面获取碎片实例
                 */
                LeftFragment lf = (LeftFragment) getSupportFragmentManager().findFragmentById(R.id.left_fragment);
                Toast.makeText(this,lf.toString(),Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 动态的添加碎片函数
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        /**
         * FragmentManager获取
         */
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //动态替换碎片
        transaction.replace(R.id.right_fragment, fragment);
        //添加到返回栈
        transaction.addToBackStack(null);
        transaction.commit();
    }




}
