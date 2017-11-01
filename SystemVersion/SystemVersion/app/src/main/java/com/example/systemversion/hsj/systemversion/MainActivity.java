package com.example.systemversion.hsj.systemversion;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.systemversion.hsj.systemversion.utils.ObjectUtils;

/**
 * @author heshengjin
 * @date 2017-11-01
 */
public class MainActivity extends AppCompatActivity {

    private TextView versionName;
    private TextView versioncode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        versionName = (TextView) this.findViewById(R.id.versionName);
        versioncode = (TextView) findViewById(R.id.versioncode);
        //获取versionInfo
        String[] versionInfo = getVersionInfo();
        if(!ObjectUtils.isEmpty(versionInfo)){
            versionName.setText(versionInfo[0]);
            versioncode.setText(versionInfo[1]);
        }
    }

    /**
     * 获取系统的信息相关
     * 正式项目考虑封装成一个util工具
     * 使用随时随地获取Context
     * @return
     */
    private String[] getVersionInfo() {
        //系统版本
        String[] versionInfo = null;

        PackageManager pm = MainActivity.this.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(MainActivity.this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new NullPointerException(getFailureMessage());
        }
        if (!ObjectUtils.isEmpty(pi)) {
            versionInfo = new String[2];
            versionInfo[0] = pi.versionName;
            versionInfo[1] = pi.versionCode+"";
        } else {
            throw new NullPointerException(getFailureMessage());
        }
        return versionInfo;
    }

    /**
     * 获取失败的信息
     * 以后实际的项目就配在string.xml文件里面
     * @return
     */
    private String getFailureMessage(){
        return getString(R.string.PackageInfo_get_fail);
    }
}
