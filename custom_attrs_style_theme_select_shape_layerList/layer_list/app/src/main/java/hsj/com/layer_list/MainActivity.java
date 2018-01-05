package hsj.com.layer_list;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private ProgressBar progressBar;
    //有没有开始进度条
    private boolean isDongingPercent = Boolean.FALSE;
    //进度条增长的下标index
    private int progressIndexValue = 0;
    //没有个间隔里面的增长数值
    private final static int GAP_VALUE = 1;
    //百分百的时候
    private final static int PER_100 = 100;
    //每次进度条的时间隔增长数值-1000ms = 1s
    private final static int EVERY_GAP_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = ((ProgressBar) findViewById(R.id.progress_bar));
        progressBar.setOnClickListener(this);
    }

    /**
     * 1.Handler用作定时器  2.更新主UI线程
     * 当创建一个Handler的时候，该Handler就绑定了当前创建Hanlder的线程ui线程
     */
    private Handler handler= new Handler(){
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.progress_bar:
                if(isDongingPercent){return;}
                hProgress();
                Toast.makeText(this,"you click progress!",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 进度条
     */
    private void hProgress(){
        isDongingPercent = Boolean.TRUE;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(progressIndexValue<=PER_100){
                    progressIndexValue+=GAP_VALUE;
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
                    MainActivity.this.progressBar.setProgress(progressIndexValue);
                    MainActivity.this.handler.postDelayed(this,EVERY_GAP_TIME);
                }else{
                    progressIndexValue = 0;
                    MainActivity.this.progressBar.setProgress(progressIndexValue);
                    MainActivity.this.handler.removeCallbacks(this);
                    isDongingPercent = Boolean.FALSE;
                }
            }
        };

        handler.post(runnable);
    }
}
