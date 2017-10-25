package com.example.runtimepermissiontest;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 动态权限
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeCall = (Button) findViewById(R.id.make_call);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRuntimePermission();
            }
        });
    }

    /**
     * 执行之前检查权限请求权限，见checkRuntimePermission方法
     * 申请权限最好还是try catch
     */
    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查权限请求权限
     *
     *
      3、如果在fragment中怎么用呢，那就在BaseFragment中：


     * 检查运行时权限
     *
     * @param permissions               所检查的权限数组
     * @param runtimePermissionListener 运行时权限监听器

    public void checkRuntimePermission(String[] permissions, RuntimePermissionListener runtimePermissionListener) {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).checkRuntimePermission(permissions, runtimePermissionListener);
        }
    }
     */
    private void checkRuntimePermission() {
        String[] permissions = new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_COARSE_LOCATION};
        checkRuntimePermission(permissions, new RuntimePermissionListener() {
            @Override
            public void onRuntimePermissionGranted() {
                //do something
                Toast.makeText(MainActivity.this,"所需的权限全部成功申请，将所有功能",Toast.LENGTH_SHORT).show();
                //权限成功执行
                call();
            }

            @Override
            public void onRuntimePermissionDenied() {
                Toast.makeText(MainActivity.this,"拒绝该所需的权限将无法使用部分功能",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
