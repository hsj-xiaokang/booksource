package com.example.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {

    /**
     * 暴露给调用者使用的API
     * @param context
     * @param newsTitle
     * @param newsContent
     */
    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        String newsTitle = getIntent().getStringExtra("news_title"); // 获取传入的新闻标题
        String newsContent = getIntent().getStringExtra("news_content"); // 获取传入的新闻内容

        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        // 刷新NewsContentFragment界面,这是单页，左边的竖线不要显示false
        newsContentFragment.refresh(newsTitle, newsContent,false);
    }

}
