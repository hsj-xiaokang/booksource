package hsj.com.service_action;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import hsj.com.service_action.service.MySerice_Thread;
import hsj.com.service_action.service.MyService;
import hsj.com.service_action.service.MyService_Front;
import hsj.com.service_action.service.MyService_Remote;
import hsj.com.service_action.service.MyService_binder;

/**
 * blog:http://blog.csdn.net/guolin_blog/article/details/11952435
 * @author hsj
 * @date 2018-01-05
 * service实践
 *
 * 注意：
 * 那么如果我们既点击了Start Service按钮，
 * 又点击了Bind Service按钮会怎么样呢？
 * 这个时候你会发现，不管你是单独点击Stop Service按钮还是Unbind Service按钮，Service都不会被销毁，
 * 必要将两个按钮都点击一下，Service才会被销毁。
 * 也就是说，点击Stop Service按钮只会让Service停止，点击Unbind Service按钮只会让Service和Activity解除关联，
 * 一个Service必须要在既没有和任何Activity关联又处理停止状态的时候才会被销毁。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private Button startBtn;
    private Button endBtn;

    private Button startBindlerBtn;
    private Button endBindlerBtn;

    private Button startBtn_Thread;
    private Button endBtn_Thread;

    private Button startBindlerBtn_front;
    private Button endBindlerBtn_front;

    private Button startBindlerBtn_remote;
    private Button endBindlerBtn_remote;

    private MyService_binder.MyBinder_binder myBinder;
    private MyService_Front.MyBinder_binder_front myBinderFront;
    private MyService_Remote.MyBinder_binder_remote myBinderRemotet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"thread id is="+Thread.currentThread().getId()+" thread name is="+Thread.currentThread().getName());
        setContentView(R.layout.activity_main);

        /**
         * 通知取消
         * 启动到前台-不会取消-一直显示
         *
         */
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(MyService_Front.NT_1);

        //alt+enter 再输入分号之前
        startBtn = (Button) findViewById(R.id.start_service);
        endBtn = (Button) findViewById(R.id.stop_service);

        startBindlerBtn = (Button) findViewById(R.id.bind_service);
        endBindlerBtn = (Button) findViewById(R.id.unbind_service);

        startBtn_Thread = (Button) findViewById(R.id.start_service_thread);
        endBtn_Thread = (Button) findViewById(R.id.stop_service_thread);

        startBindlerBtn_front = (Button) findViewById(R.id.bind_service_front);
        endBindlerBtn_front = (Button) findViewById(R.id.unbind_service_front);

        startBindlerBtn_remote = (Button) findViewById(R.id.bind_service_remote);
        endBindlerBtn_remote = (Button) findViewById(R.id.unbind_service_remote);

        startBtn.setOnClickListener(this);
        endBtn.setOnClickListener(this);

        startBindlerBtn.setOnClickListener(this);
        endBindlerBtn.setOnClickListener(this);

        startBtn_Thread.setOnClickListener(this);
        endBtn_Thread.setOnClickListener(this);

        startBindlerBtn_front.setOnClickListener(this);
        endBindlerBtn_front.setOnClickListener(this);

        startBindlerBtn_remote.setOnClickListener(this);
        endBindlerBtn_remote.setOnClickListener(this);

    }

    /**
     * 回调函数-执行binder里面的类
     */
    private ServiceConnection serviceConnection_binder = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService_binder.MyBinder_binder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection serviceConnection_front = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinderFront = (MyService_Front.MyBinder_binder_front) service;
            myBinderFront.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection serviceConnection_remote = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinderRemotet = (MyService_Remote.MyBinder_binder_remote) service;
            myBinderRemotet.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.stop_service:
                stopService(new Intent(this,MyService.class));
                break;
            case R.id.bind_service:
                Log.i(TAG,"bind_service");
                //这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service，
                // 这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                bindService(new Intent(this,MyService_binder.class),serviceConnection_binder,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                Log.i(TAG,"unbind_service");
                unbindService(serviceConnection_binder);
                break;
            case R.id.bind_service_front:
                Log.i(TAG,"bind_service_front");
                //这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service，
                // 这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                bindService(new Intent(this,MyService_Front.class),serviceConnection_front,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service_front:
                Log.i(TAG,"unbind_service_front");
                unbindService(serviceConnection_front);
                break;
            case R.id.bind_service_remote:
                Log.i(TAG,"bind_service_remote");
                //这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service，
                // 这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                bindService(new Intent(this,MyService_Remote.class),serviceConnection_remote,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service_remote:
                Log.i(TAG,"unbind_service_remote");
                unbindService(serviceConnection_remote);
                break;
            case R.id.start_service_thread:
                startService(new Intent(this, MySerice_Thread.class));
                break;
            case R.id.stop_service_thread:
                stopService(new Intent(this,MySerice_Thread.class));
                break;
            default:break;
        }
    }
}
