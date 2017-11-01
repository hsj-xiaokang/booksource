package com.example.systemversion.hsj.systemversion;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.systemversion.hsj.systemversion.utils.ObjectUtils;

public class MainActivity extends AppCompatActivity {

    private TextView versionName;
    private TextView versioncode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取versionInfo
        String[] versionInfo = getVersionInfo();
        if(!ObjectUtils.isEmpty(versionInfo)){
            versionName.setText(versionInfo[0]);
            versioncode.setText(versionInfo[1]);
        }
    }

    /**
     * 获取系统的信息相关
     * @return
     */
    private String[] getVersionInfo() {
        //系统版本
        versionName = (TextView) this.findViewById(R.id.versionName);
        versioncode = (TextView) findViewById(R.id.versioncode);
        String[] versionInfo = null;

        PackageManager pm = MainActivity.this.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(MainActivity.this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (!ObjectUtils.isEmpty(pi)) {
            versionInfo = new String[2];
            versionInfo[0] = pi.versionName;
            versionInfo[1] = pi.versionCode+"";
        } else {
            throw new NullPointerException("PackageInfo get failure!");
        }
        return versionInfo;
    }
}
