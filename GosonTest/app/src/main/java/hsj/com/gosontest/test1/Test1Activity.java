package hsj.com.gosontest.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import hsj.com.gosontest.MyApplication;
import hsj.com.gosontest.R;
import hsj.com.gosontest.test0.Test0Activity;

/**
 * Created by hsj on 2018/1/3.
 * json对象转成java对象
 */

public class Test1Activity extends AppCompatActivity{
    private static final String TAG = "Test1Activity";
    public final static String JSON_STRING =
            "{\"age\":10,\"books\":[{\"name\":\"格林童话0\",\"price\":\"价格：0$\"},{\"name\":\"格林童话1\",\"price\":\"价格：1$\"}," +
                    "{\"name\":\"格林童话2\",\"price\":\"价格：2$\"}],\"id\":1,\"name\":\"小孩A\",\"sex\":\"男\",\"toys\":[\"小车\",\"皮卡丘\",\"奥特曼\",\"火影忍者\"]," +
                    "\"toysMap\":{\"4\":\"火影忍者2\",\"1\":\"小车2\",\"2\":\"皮卡丘2\",\"3\":\"奥特曼2\"}}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        Gson gson = new Gson();
        Child child = gson.fromJson(JSON_STRING, Child.class);
        List<String> listToy =  child.getToys();
        int size = listToy.size();
        for (int i = 0; i < size; i++){
            Log.i(""+i+"for-child-list-toys:",listToy.get(i));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //    对外的接口，打开当前的activity
    public static void startTest1Activity(){
        Intent intent = new Intent(MyApplication.getContext(),Test1Activity.class);
        MyApplication.getContext().startActivity(intent);

    }
}
