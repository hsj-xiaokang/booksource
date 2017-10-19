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
 *
 *
 * startActivityForResult会引发singleInstance模式的异常情况
 */
public class FirstActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "FirstActivity";
    private final static int REQUESTCODE = 1; // 返回的结果码
    private  Button button1;
    private  Button button2;
    private final static String INTENTHIDEN_TO_SECONDACTIVITY_ACTION = "com.example.activitytest.ACTION_START";
    private final static String INTENTHIDEN_TO_DECONDACTVITY_CATEGORY = "com.example.activitytest.MY_CATEGORY";

    /**
     * activity启动时候
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Task id is --------------------------------------------------FirstActivity 》" + getTaskId());

        //去掉顶部标题项  导入类-------> ctrl+alt+space
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        //去掉标题栏无效果
//        if (getSupportActionBar() != null){
//            getSupportActionBar().hide();
//        }

        setContentView(R.layout.first_layout);
         button1 = (Button) findViewById(R.id.button_1);
         button1.setOnClickListener(this);
         button2 = (Button) findViewById(R.id.button_2);
         button2.setOnClickListener(this);

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

    /**
     * 事件的方式推荐
     * @param v
     */
    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.button_1:
                    //toast测试
                    Toast.makeText(FirstActivity.this,"you click button 1",Toast.LENGTH_SHORT).show();

                    //没有传递参数的intent
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);

                    //传递参数
                 SecondActivity.actionStart(FirstActivity.this, "data1", "data2");

                    //有返回结果
                    // 意图实现activity的跳转
                    //*************会引发singleInstance模式的异常情况*************
//                    Intent intent = new Intent(FirstActivity.this,
//                            SecondActivity.class);
//                    intent.putExtra("param1", "data1");
//                    intent.putExtra("param2", "data2");
//                    startActivityForResult(intent, REQUESTCODE); //REQUESTCODE--->1

        break;
        case R.id.button_2:
            //隐式intent
//            Intent intent_hiden = new Intent(INTENTHIDEN_TO_SECONDACTIVITY_ACTION);
//            intent_hiden.addCategory(INTENTHIDEN_TO_DECONDACTVITY_CATEGORY);
//            intent_hiden.putExtra("param1", "data1");
//            startActivity(intent_hiden);

            //隐式的方式实现多个程序之间共享 比如打开系统浏览器
//            Intent intent_browse = new Intent(Intent.ACTION_VIEW);
//            intent_browse.setData(Uri.parse("http://www.baidu.com"));
//            startActivity(intent_browse);

            //隐式的方式实现多个程序之间共享 比如打开系统电话
            Intent intent_phone = new Intent(Intent.ACTION_DIAL);
            intent_phone.setData(Uri.parse("tel:10086"));
            startActivity(intent_phone);
        default:break;

     }
    }
}
