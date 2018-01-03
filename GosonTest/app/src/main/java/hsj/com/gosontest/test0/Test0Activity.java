package hsj.com.gosontest.test0;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import hsj.com.gosontest.MyApplication;
import hsj.com.gosontest.R;

/**
 * Created by hsj on 2018/1/3.
 * java对象转成json
 */

public class Test0Activity extends AppCompatActivity{
    private static final String TAG = "Test0Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test0);

        Gson gson = new Gson();

        Child child = new Child();
        child.setId(1);
        child.setAge(10);
        child.setName("小孩A");
        child.setSex("男");

//         玩具
        ArrayList<String> toys = new ArrayList<String>();
        toys.add("小车");
        toys.add("皮卡丘");
        toys.add("奥特曼");
        toys.add("火影忍者");
        child.setToys(toys);
//        儿童玩具的map
        HashMap<String, String> toysMap = new HashMap<String, String>();
        toysMap.put("1", "小车2");
        toysMap.put("2", "皮卡丘2");
        toysMap.put("3", "奥特曼2");
        toysMap.put("4", "火影忍者2");
        child.setToysMap(toysMap);
//        小孩的书籍
        ArrayList<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 3; i++) {
            Book book = new Book();
            book.setName("格林童话" + i);
            book.setPrice("价格：" + i + "$");
            books.add(book);
        }
        child.setBooks(books);

        Log.i("child", gson.toJson(child));

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
    public static void startTest0Activity(){
        Intent intent = new Intent(MyApplication.getContext(),Test0Activity.class);
        MyApplication.getContext().startActivity(intent);

    }
}
