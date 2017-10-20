package com.example.listviewtest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hsj
 * @date 2017-10-20
 * ListView和PullToRefreshListView
 *
 *
 *
 * PullToRefreshListView插件安装见：
 *          Android Studio的使用——引入库如：引入PullToRefresh的library

            PullToRefresh使用FloatMath注意：
            FloatMath的所有方法都已经从版本23的公共API中删除，官方提示我们可以使用java.lang.Math类来代替使用

           安装博客 http://blog.csdn.net/u011809714/article/details/50955130
 *
 *
 */
public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = "MainActivity";

/**************************************************************************************************ListView自定制**********************************************************
    //郭霖大神的原文内容被注释
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initFruits(); // 初始化水果数据

        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        //事件点击
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }*/






    /***************************************************************************************PullToRefreshListView自定制和默认[默认的就是下面被注释的部分]**********************************************************************
     * http://blog.csdn.net/lmj623565791/article/details/38238749，本文出自：【张鸿洋的博客】
     *
     */

//    private LinkedList<String> mListItems;
    private List<Fruit> mListItems = new ArrayList<Fruit>();
    /**
     * 上拉刷新的控件
     */
    private PullToRefreshListView mPullRefreshListView;

    private FruitAdapter mAdapter;

//    private int mItemCount = 9;

    /**
     * 创建初始化
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 得到控件
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

        //初始化数据
//        initDatas();
          initFruits();

        //设置适配器
//        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mListItems);
        mAdapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, mListItems);

        mPullRefreshListView.setAdapter(mAdapter);

        //汉化
        initZn();

        // 设置监听事件
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            /**
             * 下拉
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh( PullToRefreshBase<ListView> refreshView){
                Log.d(TAG, "onPullDownToRefresh");
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(com.example.listviewtest.utils.DateUtils.getCurrentTimeFormate());
                //这里写下拉刷新的任务
                new GetDataTask().execute();
            }

            /**
             * 上拉
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView){
                Log.d(TAG, "onPullUpToRefresh");
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(com.example.listviewtest.utils.DateUtils.getCurrentTimeFormate());
                //这里写上拉加载更多的任务
                new GetDataTask().execute();
            }
        });
    }

    /**
     * 初始化数据值
     */
    /*private void initDatas() {
        // 初始化数据和数据源
        mListItems = new LinkedList<String>();

        for (int i = 0; i < mItemCount; i++){
            mListItems.add("" + i);
        }
    }*/
    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            mListItems.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            mListItems.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            mListItems.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            mListItems.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            mListItems.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            mListItems.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            mListItems.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            mListItems.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            mListItems.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            mListItems.add(mango);
        }
    }

    /**
     * 上下拉的时间
     * @return
     */
    private String labelTime(){
        return  DateUtils.formatDateTime(
                getApplicationContext(),
                System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_ABBREV_ALL);
    }

    /**
     * 汉化
     */
    private void initZn()
    {
        ILoadingLayout startLabels = mPullRefreshListView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("你可以拉...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在刷新...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = mPullRefreshListView.getLoadingLayoutProxy( false, true);
        endLabels.setPullLabel("你可以拉...");// 刚上拉时，显示的提示
        endLabels.setRefreshingLabel("正在刷新...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 上来达到一定距离时，显示的提示
    }

    /**
     * 上拉刷新的类
     * Params, Progress, Result
     */
    /*private class GetDataTask extends AsyncTask<Void, Void, String>{

        *//**
         * 模拟数据请求
         * @param
         * @return
         *//*
        @Override
        protected String doInBackground(Void... params) {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return ""+(mItemCount++);
        }

        *//**
         * 数据回来更新视图
         * @param
         *//*
        @Override
        protected void onPostExecute(String result) {
            mListItems.add(result);
            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }
    }*/
    private class GetDataTask extends AsyncTask<Void, Void, Fruit>{

        /**
         * 模拟数据请求
         * @param params
         * @return
         */
        @Override
        protected Fruit doInBackground(Void... params) {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return new Fruit("hsjjsh", R.drawable.mango_pic);
        }

        /**
         * 数据回来更新视图
         * @param result
         */
        @Override
        protected void onPostExecute(Fruit result) {
            mListItems.add(result);
            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }
    }

}
