package com.example.fragmentbestpractice;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentbestpractice.footerView.SimpleFooterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
     //标志单页还是双页
    private boolean isTwoPane;
    //数据
    private List<News> newsList;
    //适配器
    private NewsAdapter adapter;
    //上拉下拉加载组件
    private SwipeRecyclerView recyclerView;
    //每一页的大小
    private int pagerSize = 10;
    //当前页
    private int currentPage = 1;
    //模拟新闻服务器的所有总条数
    private final static int SUM_NEWS = 48;
    //模拟http请求的耗时间毫秒1000
    private final static int HTTP_TIME_SIMULATE = 1000;
    //模拟数据的条数
    private final static int NUM_0 = 0;
    private final static int NUM_1 = 1;
    private final static int NUM_2 = 2;
    private final static int NUM_3 = 3;
    private final static int NUM_4 = 4;
    private final static int NUM_5 = 5;
    private final static int NUM_6 = 6;
    private final static int NUM_7 = 7;
    private final static int NUM_8 = 8;
    private final static int NUM_9 = 9;
    private final static int NUM_10 = 10;
    private final static int NUM_11 = 11;
    private final static int NUM_12 = 12;
    private final static int NUM_13 = 13;
    private final static int NUM_14 = 14;
    private final static int NUM_15 = 15;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        //news_title_frag碎片
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        //RecyclerView
        recyclerView = (SwipeRecyclerView) view.findViewById(R.id.news_title_recycler_view);

        //set color
        recyclerView.getSwipeRefreshLayout()
                .setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        //set layoutManager
        recyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.getRecyclerView().setLayoutManager(new GridLayoutManager(this, 3));
        //recyclerView.getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //禁止下拉刷新
        // recyclerView.setRefreshEnable(false);

        //禁止加载更多
        //recyclerView.setLoadMoreEnable(false);

        //设置emptyView
        TextView textView = new TextView(getActivity());
        textView.setText(getActivity().getString(R.string.app_nhmd));
        recyclerView.setEmptyView(textView);

        //设置footerView
        recyclerView.setFooterView(new SimpleFooterView(getActivity()));

        //由于SwipeRecyclerView中对GridLayoutManager的SpanSizeLookup做了处理，因此对于使用了
        //GridLayoutManager又要使用SpanSizeLookup的情况，可以这样使用！
        /*recyclerView.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 3;
            }
        });*/

        //设置去除footerView 的分割线
        recyclerView.getRecyclerView().addItemDecoration(new RecyclerView.ItemDecoration() {
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
        });

        //设置noMore
        recyclerView.onNoMore(this.getString(R.string.app_nhmd));

        //设置网络处理
        recyclerView.onNetChange(true);

        //设置错误信息
        recyclerView.onError(this.getString(R.string.app_thae));

        //数据的list
        newsList = new ArrayList<>();
        //adapter初始化
        adapter = new NewsAdapter(newsList);
        //设置adapter
        recyclerView.setAdapter(adapter);

        recyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            /**
             * 下拉刷新
             */
            @Override
            public void onRefresh() {
                /**
                 * 模拟http请求
                 */
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //先清空newsList数据list
                        newsList.clear();
                        //放置pagerSize的数量个，实际中就是http请求回来的数据,请求时候根据currentPage,pagerSize等
                        {
                            //代码块先于构造函数
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_0);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_0));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_1);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_1));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_2);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_2));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_3);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_3));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_4);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_4));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_5);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_5));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_6);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_6));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_7);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_7));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_8);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_8));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_9);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_9));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_10);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_10));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_11);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_11));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_12);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_12));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_13);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_13));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_14);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_14));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_15);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_15));}});
                        }
                        //触发完成的函数
                        recyclerView.complete();
                        //数据list通知data数据改变
                        adapter.notifyDataSetChanged();

                    }
                }, HTTP_TIME_SIMULATE);//延迟1秒执行，模拟http请求数据回来

            }

            /**
             * 上拉加载更多
             */
            @Override
            public void onLoadMore() {
                /**
                 * 模拟http请求
                 */
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //实际中就是http请求回来的数据,请求时候根据currentPage,pagerSize等追加数据
                        {//代码块先于构造函数
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_0);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_0));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_1);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_1));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_2);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_2));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_3);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_3));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_4);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_4));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_5);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_5));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_6);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_6));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_7);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_7));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_8);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_8));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_9);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_9));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_10);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_10));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_11);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_11));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_12);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_12));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_13);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_13));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_14);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_14));}});
                            newsList.add(new News(){{
                                setTitle(getActivity().getString(R.string.app_title)+NUM_15);
                                setContent(getRandomLengthContent(getActivity().getString(R.string.app_content)+NUM_15));}});
                        }
                        //请求回来currentPage加1
                        currentPage++;
                        //模拟没有更多数据情况
                        if(newsList.size() > SUM_NEWS){
                            recyclerView.onNoMore(getActivity().getString(R.string.app_nhmd));
                        }else {
                            recyclerView.stopLoadingMore();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, HTTP_TIME_SIMULATE);//延迟1秒执行，模拟http请求数据回来
            }
        });

        //设置自动下拉刷新，切记要在recyclerView.setOnLoadListener()之后调用
        //因为在没有设置监听接口的情况下，setRefreshing(true),调用不到OnLoadListener
        recyclerView.setRefreshing(true);

        return view;
    }

    /**
     * 单页还是双页
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true; // 可以找到news_content_layout布局时，为双页模式
        } else {
            isTwoPane = false; // 找不到news_content_layout布局时，为单页模式
        }
    }


    /**
     * 随机内容长度
     * @param content
     * @return
     */
    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    /**
     * 自定义的新闻适配器
     */
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitleText;

            public ViewHolder(View view) {
                super(view);
                newsTitleText = (TextView) view.findViewById(R.id.news_title);
            }

        }

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);

            final ViewHolder holder = new ViewHolder(view);
            //点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        //碎片的布局
                        NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        //这里是双页，左边的竖线分割需要显示，true
                        newsContentFragment.refresh(news.getTitle(), news.getContent(),true);
                    } else {
                        //不是碎片，activity启动
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

    }

}
