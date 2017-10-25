package com.example.runtimepermissiontest;

import java.util.List;

/**
 * Created by hsj on 2017/10/25.
 */

public interface PermissionListener {
    //授权，同意
    void onGranted();
    //拒绝
    void onDenied(List<String> deniedPermission);
}
