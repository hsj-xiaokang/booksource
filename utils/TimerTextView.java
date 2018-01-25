package com.agrithings.farmmange.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 自定义的60秒倒计时
 * @author heshnegjin
 * @date 2018-01-17
 */
public class TimerTextView extends AppCompatTextView implements Runnable{
    private boolean isRun = false;
    //默认60秒
    private long secondCount = 60;

    public TimerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setFocusable(true);
        this.setClickable(true);
        this.setText("获取验证码");

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRun){
                    //TODO 触发后台服务发送短信
                    beginRun();
                }
            }
        });
    }

    @Override
    public void run() {
        if(isRun){
            this.setText(ComputeTime());

            postDelayed(this, 1000);
        }else {
            removeCallbacks(this);
        }
    }

    /**
     * 倒计时计算
     */
    private String ComputeTime() {
        String backTime = "";
        secondCount--;
        if (secondCount <= 0) {
            backTime = "重新获取";

            secondCount = 60;
            this.isRun = false;
            this.setFocusable(true);
            this.setClickable(true);
        }else{
            backTime = secondCount + "秒";
        }
        return backTime;
    }

    public void setSecondCount(long secondCount){
        this.secondCount = secondCount;
    }

    public boolean isRun() {
        return isRun;
    }

    public void beginRun() {
        this.isRun = true;
        this.setFocusable(false);
        this.setClickable(false);
        run();
    }

    public void stopRun(){
        this.isRun = false;
    }
}

