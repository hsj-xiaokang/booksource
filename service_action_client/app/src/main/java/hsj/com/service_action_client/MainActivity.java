package hsj.com.service_action_client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import hsj.com.service_action.MyAIDLService;

public class MainActivity extends AppCompatActivity {


    private MyAIDLService myAIDLService;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int result = myAIDLService.plus(50, 50);
                String upperStr = myAIDLService.toUpperCase("comes from ClientTest");
                Log.d("TAG", "result is " + result);
                Log.d("TAG", "upperStr is " + upperStr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bindService = (Button) findViewById(R.id.bind_service);
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 回复blx1031241781：
                 * Intent intent = new Intent("com.example.servicetest.MyAIDLService").setPakage("com.example.servicetest");
                 后面指定包名 亲测 运行成功
                 */
                Intent intent = new Intent("hsj.com.service_action.MyAIDLService").setPackage("hsj.com.service_action");
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
    }
}
