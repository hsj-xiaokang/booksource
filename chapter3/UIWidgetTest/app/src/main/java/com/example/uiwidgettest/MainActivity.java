package com.example.uiwidgettest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uiwidgettest.selfCustom_UI_Attrs.self_custom_ui_attrs_activity;
import com.example.uiwidgettest.utils.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * 字体的大小使用sp单位
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private EditText editText;

    private ImageView imageView;

    private ProgressBar progressBar;

    private Button b_ui_attrs;
    /**
     * 1.Handler用作定时器  2.更新主UI线程
     * 当创建一个Handler的时候，该Handler就绑定了当前创建Hanlder的线程ui线程
     */
    private  Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(MainActivity.this,msg.arg1 + "  " + msg.arg2,Toast.LENGTH_SHORT).show();
                    break;
                default:break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);

        editText = (EditText) findViewById(R.id.edit_text);
        editText.setOnClickListener(this);
        imageView  = (ImageView) findViewById(R.id.image_view);
        imageView.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setOnClickListener(this);
        b_ui_attrs = (Button) findViewById(R.id.ui_attrs);
        b_ui_attrs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            case R.id.edit_text:
                String et = editText.getText().toString().trim();
                if(!ObjectUtils.isEmpty(et)){
                    Toast.makeText(this,et,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.image_view:
                Toast.makeText(this,"you click image!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.progress_bar:
                hProgress();
                Toast.makeText(this,"you click progress!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ui_attrs:
                MainActivity.this.startActivity(new Intent(){
                    {setClass(MainActivity.this,self_custom_ui_attrs_activity.class);}
                });
                break;
            default:
                break;
        }
    }

    /**
     * 进度条
     */
    private void hProgress(){

        Runnable runnable = new Runnable() {
            int i=0;
            @Override
            public void run() {
                if(i<=100){
                    i+=1;

//                    2.更新主UI线程
                    //虽然Message的构造函数式public的，我们也可以通过以下两种方式通过循环对象获取Message
                    Message msg = Message.obtain(MainActivity.this.handler);
                    //msg = uiHandler.obtainMessage();
                    //what是我们自定义的一个Message的识别码，以便于在Handler的handleMessage方法中根据what识别
                    //出不同的Message，以便我们做出不同的处理操作
                    msg.what = 1;
                    //我们可以通过arg1和arg2给Message传入简单的数据
                    msg.arg1 = 123;
                    msg.arg2 = 321;
                    MainActivity.this.handler.sendMessage(msg);

//                    1.Handler用作定时器
                    MainActivity.this.progressBar.setProgress(i);
                    MainActivity.this.handler.postDelayed(this,1000);
                }
            }
        };

        handler.post(runnable);
    }

}
