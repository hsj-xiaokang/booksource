package com.example.fragmentbestpractice.footerView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fragmentbestpractice.R;


/**
 * @author deadline
 * @time 2016/10/22
 */
public class SimpleFooterView extends BaseFooterView{

    private TextView mText;

    private ProgressBar progressBar;

    public SimpleFooterView(Context context) {
        this(context, null);
    }

    public SimpleFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //参数设置
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //进度条和文本框
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_footer_view, this);
        //根据id获取
        progressBar = (ProgressBar) view.findViewById(R.id.footer_view_progressbar);
        mText = (TextView) view.findViewById(R.id.footer_view_tv);
    }


    /**
     * 展示loading的progressBar
     */
    @Override
    public void onLoadingMore() {
        progressBar.setVisibility(VISIBLE);
        mText.setVisibility(GONE);
    }

    /**
     * 展示text
     */
    public void showText(){
        progressBar.setVisibility(GONE);
        mText.setVisibility(VISIBLE);
    }

    /**************文字自行修改或根据传入的参数动态修改****************/

    @Override
    public void onNoMore(CharSequence message) {
        showText();
        mText.setText(R.string.app_nhmd);
    }

    @Override
    public void onError(CharSequence message) {
        showText();
        mText.setText(R.string.app_thae);
    }

    @Override
    public void onNetChange(boolean isAvailable) {
        showText();
        mText.setText(R.string.app_ne);
    }
}
