package com.example.fragmentbestpractice.footerView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @auther deadline
 * @time   2016/10/22
 */
public abstract class BaseFooterView extends FrameLayout implements FooterViewListener{
    /**
     * 下面三个构造函数
     *
     */

    public BaseFooterView(Context context) {
        super(context);
    }

    public BaseFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
