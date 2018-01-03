package hsj.com.gosontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import hsj.com.gosontest.test0.Test0Activity;
import hsj.com.gosontest.test1.Test1Activity;

/**
 * @author heshengjin
 * @date 2017-01-03
 * gson插件gsonFormat:https://www.cnblogs.com/tianmanyi/p/6028624.html
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test0).setOnClickListener(this);
        findViewById(R.id.test1).setOnClickListener(this);
    }

    /**
     * 事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test0:
                Log.i(TAG,"you click test0!");
                Toast.makeText(MyApplication.getContext(),"you click test0！",Toast.LENGTH_LONG).show();
                Test0Activity.startTest0Activity();
                break;
            case R.id.test1:
                Log.i(TAG,"you click test1!");
                Toast.makeText(MyApplication.getContext(),"you click test1！",Toast.LENGTH_LONG).show();
                Test1Activity.startTest1Activity();
                break;
            default:break;
        }
    }
}
