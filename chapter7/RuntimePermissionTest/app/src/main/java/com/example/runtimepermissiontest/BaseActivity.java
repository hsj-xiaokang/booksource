package com.example.runtimepermissiontest;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsj on 2017/10/25.
 * 郭神视频录播地址：http://edu.csdn.net/course/detail/3539
 * 整理的博客地址：http://blog.csdn.net/qq_31715429/article/details/52885787
 */

public class BaseActivity extends AppCompatActivity {
    //6.0以上系统判断权限  --> start
    private static final int RUNTIME_PERMISSION_REQUEST_CODE = 1;
    //回调接口
    private RuntimePermissionListener mRuntimePermissionListener;


    /**
     * 运行时权限监听器
     */
    public interface RuntimePermissionListener {
        /**
         * 允许所请求的全部权限
         */
        void onRuntimePermissionGranted();

        /**
         * 拒绝所请求的部分或全部权限
         */
        void onRuntimePermissionDenied();
    }

    /**
     * 检查运行时权限
     *
     * @param permissions               所检查的权限数组
     * @param runtimePermissionListener 运行时权限监听器
     */
    public void checkRuntimePermission(String[] permissions, RuntimePermissionListener runtimePermissionListener) {

        mRuntimePermissionListener = runtimePermissionListener;

        //不允许的权限list
        List<String> deniedPermissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {//判断权限是否允许
                //不允许的权限
                deniedPermissionList.add(permission);
            }
        }
        if (deniedPermissionList.isEmpty()) {
            //权限全部允许
            mRuntimePermissionListener.onRuntimePermissionGranted();
        } else {
            String[] deniedPermissionArray = deniedPermissionList.toArray(new String[deniedPermissionList.size()]);
            //请求未允许的权限
            ActivityCompat.requestPermissions(this, deniedPermissionArray, RUNTIME_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * 用户操作权限之后的结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case RUNTIME_PERMISSION_REQUEST_CODE:

                if (grantResults.length > 0) {
                    //没有允许的权限
                    List<String> deniedPermissionList = new ArrayList<>();

                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

                            deniedPermissionList.add(permissions[i]);
                        }
                    }
                    if (deniedPermissionList.isEmpty()) { //权限全部允许
                        mRuntimePermissionListener.onRuntimePermissionGranted();
                    } else {//有拒绝的权限
                        mRuntimePermissionListener.onRuntimePermissionDenied();
                    }

                }

            default:break;
        }
    }

//6.0以上系统判断权限  --> end
}
