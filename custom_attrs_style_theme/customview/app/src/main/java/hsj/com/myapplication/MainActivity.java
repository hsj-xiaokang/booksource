package hsj.com.myapplication;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import hsj.com.myapplication.view.CustomTitleBar;
import hsj.com.myapplication.view.SelfCustomUIAttrsEvents;

/**
 * @author heshngjin
 * @date 2018-01-04
 * blog:https://www.cnblogs.com/whoislcj/p/5714760.html
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏actionbar---在setContentView之前
        ActionBar ab = getSupportActionBar();
        if(ab!= null){
            ab.hide();
        }
        setContentView(R.layout.activity_main);

        SelfCustomUIAttrsEvents scuae = new SelfCustomUIAttrsEvents(){
            @Override
            public void titleBarLeftBtnEvent() {
                Toast.makeText(MainActivity.this,"you clicked Left!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void titleBarTitleEvent() {
                Toast.makeText(MainActivity.this,"you clicked Title!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void titleBarRightBtnEvent() {
                Toast.makeText(MainActivity.this,"you clicked Right!",Toast.LENGTH_SHORT).show();
            }
        };

        ((CustomTitleBar) findViewById(R.id.customview_1)).setSelfCustomUIAttrsEvents(scuae);
        ((CustomTitleBar) findViewById(R.id.customview_2)).setSelfCustomUIAttrsEvents(scuae);
        ((CustomTitleBar) findViewById(R.id.customview_3)).setSelfCustomUIAttrsEvents(scuae);
        ((CustomTitleBar) findViewById(R.id.customview_4)).setSelfCustomUIAttrsEvents(scuae);
        ((CustomTitleBar) findViewById(R.id.customview_5)).setSelfCustomUIAttrsEvents(scuae);
        ((CustomTitleBar) findViewById(R.id.customview_6)).setSelfCustomUIAttrsEvents(scuae);
    }
}
