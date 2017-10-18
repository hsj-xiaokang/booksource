package com.example.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author  heshengjin
 * @date 2017-10-18
 * 第一个activity
 */
public class FirstActivity extends BaseActivity {
    private static final String TAG = "FirstActivity";
    private final static int REQUESTCODE = 1; // 返回的结果码

    /**
     * activity启动时候
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Task id is " + getTaskId());

        //去掉顶部标题项  导入类-------> ctrl+alt+space
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        //去掉标题栏无效果
//        if (getSupportActionBar() != null){
//            getSupportActionBar().hide();
//        }

        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toast测试
                 Toast.makeText(FirstActivity.this,"you click button 1",Toast.LENGTH_SHORT).show();
                //没有传递参数的intent
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);
                //传递参数
//                 SecondActivity.actionStart(FirstActivity.this, "data1", "data2");

                //有返回结果
                // 意图实现activity的跳转
                 Intent intent = new Intent(FirstActivity.this,
                         SecondActivity.class);
                 intent.putExtra("param1", "data1");
                 intent.putExtra("param2", "data2");
                 startActivityForResult(intent, REQUESTCODE); //REQUESTCODE--->1

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    /**
     * 创建菜单 返回的Boolean值展现显示和不显示
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 菜单的单击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    /**
     * activity传递参数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { Log.d(TAG, "onActivityResultonActivityResultonActivityResultonActivityResultonActivityResult");
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Toast.makeText(this, returnedData, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, returnedData);
                }
                break;
            default:
        }
    }

}
