package com.agrithings.farmmange.audio.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agrithings.farmmange.R;
import com.agrithings.farmmange.activity.BaseActivity;
import com.agrithings.farmmange.activity.MainActivity;
import com.agrithings.farmmange.activity.PestAddActivity;
import com.agrithings.farmmange.audio.MediaPlayerUtils;
import com.agrithings.farmmange.audio.MediaRecorderUtils;
import com.agrithings.farmmange.audio.StateAudioObjUtils;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import butterknife.OnTouch;

/**
 * 主页面
 */
public class VoiceMainActivity extends BaseActivity {
    private static final String TAG = "VoiceMainActivity";
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
    }

   


    /**
     * 弹出评论框
     */
    private void showCommentDailog() {
        //R.style.***一定要写，不然不能充满整个屏宽，引用R.style.AppTheme就可以
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.AppTheme).create();
        View view = View.inflate(this, R.layout.activity_voice_dialog, null);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.DialogAnimation);
        window.getDecorView().setPadding(0, 0, 0, 0);

        //设置dialog弹出后会点击屏幕或物理返回键，dialog不消失
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        window.setContentView(view);

        //获得window窗口的属性
        WindowManager.LayoutParams params = window.getAttributes();
        //设置窗口宽度为充满全屏
        params.width = WindowManager.LayoutParams.MATCH_PARENT;//如果不设置,可能部分机型出现左右有空隙,也就是产生margin的感觉
        //设置窗口高度为包裹内容
        params.height = 800;//WindowManager.LayoutParams.WRAP_CONTENT;
//        params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;//显示dialog的时候,就显示软键盘
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;//就是这个属性导致window后所有的东西都成暗淡
        params.dimAmount = 0.5f;//设置对话框的透明程度背景(非布局的透明度)
        //将设置好的属性set回去
        window.setAttributes(params);

        Button voice = (Button) view.findViewById(R.id.voice);
        Button delete = (Button) view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button sure = (Button) view.findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
