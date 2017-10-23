package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsContentFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container, false);
        return view;
    }

    /**
     * 别人调用API展示碎片时候的布局样式
     * @param newsTitle
     * @param newsContent
     */
    public void refresh(String newsTitle, String newsContent,boolean leftLineShow) {
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        //单页的时候左边的竖线不需要显示
        View leftLine =  view.findViewById(R.id.left_line);
        if(leftLineShow){
            leftLine.setVisibility(View.VISIBLE);
        }else{
            leftLine.setVisibility(View.GONE);
        }
        TextView newsTitleText = (TextView) view.findViewById (R.id.news_title);
        TextView newsContentText = (TextView) view.findViewById(R.id.news_content);
        newsTitleText.setText(newsTitle); // 刷新新闻的标题
        newsContentText.setText(newsContent); // 刷新新闻的内容
    }

}
