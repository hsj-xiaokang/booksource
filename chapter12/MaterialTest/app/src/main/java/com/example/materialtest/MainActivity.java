package com.example.materialtest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.footerView.SimpleFooterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    //上拉下拉加载组件
    private SwipeRecyclerView recyclerView;
    //每一页的大小
    private int pagerSize = 10;
    //当前页
    private int currentPage = 1;

    private DrawerLayout mDrawerLayout;

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //侧滑的按钮图标mDrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //侧滑出来的菜单item
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //导航按钮显示
            actionBar.setDisplayHomeAsUpEnabled(true);
            //导航图标显示
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //浮动ActionButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data selete please!", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });


        recyclerView = (SwipeRecyclerView) findViewById(R.id.swipeRecyclerView);

        //set color
        recyclerView.getSwipeRefreshLayout()
                .setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        //set layoutManager
//        recyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getRecyclerView().setLayoutManager(new GridLayoutManager(this, 2));
        //recyclerView.getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //禁止下拉刷新
        // recyclerView.setRefreshEnable(false);

        //禁止加载更多
        //recyclerView.setLoadMoreEnable(false);

        //设置emptyView
        TextView textView = new TextView(this);
        textView.setText("no data!");
        recyclerView.setEmptyView(textView);

        //设置footerView
        recyclerView.setFooterView(new SimpleFooterView(this));

        //由于SwipeRecyclerView中对GridLayoutManager的SpanSizeLookup做了处理，因此对于使用了
        //GridLayoutManager又要使用SpanSizeLookup的情况，可以这样使用！
        /*recyclerView.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 3;
            }
        });*/

        //设置去除footerView 的分割线
       /* recyclerView.getRecyclerView().addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);

                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setColor(0xEEEECCCC);

                Rect rect = new Rect();
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                final int childCount = parent.getChildCount() - 1;
                for (int i = 0; i < childCount; i++) {
                    final View child = parent.getChildAt(i);

                    //获得child的布局信息
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    final int top = child.getBottom() + params.bottomMargin;
                    final int itemDividerHeight = 1;//px
                    rect.set(left + 50, top, right - 50, top + itemDividerHeight);
                    c.drawRect(rect, paint);
                }
            }
        });*/

        //设置noMore
        recyclerView.onNoMore(this.getString(R.string.app_nhmd));

        //设置网络处理
        recyclerView.onNetChange(true);

        //设置错误信息
        recyclerView.onError(this.getString(R.string.app_thae));

        //初始化数据
        initFruits();
        //adapter初始化
        adapter = new FruitAdapter(fruitList);
        //设置adapter
        recyclerView.setAdapter(adapter);

        recyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            /**
             * 下拉刷新
             */
            @Override
            public void onRefresh() {
                Log.d(TAG, "下拉刷新 "+fruitList.size());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        //触发完成的函数
                        recyclerView.complete();
                        //数据list通知data数据改变
                        adapter.notifyDataSetChanged();

                    }
                }, 1000);//延迟1秒执行，模拟http请求数据回来

            }

            /**
             * 上拉加载更多
             */
            @Override
            public void onLoadMore() {
                Log.d(TAG, "上拉加载更多"+fruitList.size());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //实际中就是http请求回来的数据,请求时候根据currentPage,pagerSize等追加数据
                        initFruits();
                        //请求回来currentPage加1
                        currentPage++;
                        //模拟没有更多数据情况
                        if(fruitList.size() > 50){
                            recyclerView.onNoMore(MainActivity.this.getString(R.string.app_nhmd));
                        }else {
                            recyclerView.stopLoadingMore();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, 1000);//延迟1秒执行，模拟http请求数据回来
            }
        });

        //设置自动下拉刷新，切记要在recyclerView.setOnLoadListener()之后调用
        //因为在没有设置监听接口的情况下，setRefreshing(true),调用不到OnLoadListener
        recyclerView.setRefreshing(true);
    }

    /**
     * 初始化水果
     */
    private void initFruits() {
        fruitList.add(new Fruit("Apple", R.drawable.apple));
        fruitList.add(new Fruit("Banana", R.drawable.banana));
        fruitList.add(new Fruit("Orange", R.drawable.orange));
        fruitList.add(new Fruit("Watermelon", R.drawable.watermelon));
        fruitList.add(new Fruit("Pear", R.drawable.pear));
        fruitList.add(new Fruit("Grape", R.drawable.grape));
        fruitList.add(new Fruit("Pineapple", R.drawable.pineapple));
        fruitList.add(new Fruit("Strawberry", R.drawable.strawberry));
        fruitList.add(new Fruit("Cherry", R.drawable.cherry));
        fruitList.add(new Fruit("Mango", R.drawable.mango));
    }

    /**
     * toolbar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * toolbar事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }


}
