package com.credit.exg.exg.init;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dizner on 2017/8/18.
 */

public class PermissionsActivity extends AppCompatActivity {


    private int REQUEST_PERMISSION_CODE = 10000;//申请权限的请求码
    private PermissionListener permissionListener;//权限申请后的回调接口

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0) {
                List<String> deniedList = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    int grantResult = grantResults[i];
                    String permission = permissions[i];
                    Log.d("PermissionsActivity", "grantResult:" + grantResult);
                    if (grantResult == PackageManager.PERMISSION_DENIED) {
                        Log.d("PermissionsActivity", "拒绝的权限" + permission);
                        deniedList.add(permission);
                    }
                }
                if (deniedList.isEmpty()) {
                    //全部都授权了
                    permissionListener.permissionGranted();
                    Log.d("PermissionsActivity", "全部都授权了");
                } else {
                    //有没授权的
                    permissionListener.permissionDenied(deniedList);
                    Log.d("PermissionsActivity", "全部都拒绝了");
                }

                //申请权限结束时 要开启悬浮窗
//                EventBus.getDefault().post(AppEvent.END_REQUEST_PERMISSIONS);
            }
        }
    }


    /**
     * 权限申请
     *
     * @param permission 需要申请的权限
     * @param listener   权限申请后的回调接口
     */
    public void requestRuntimePermission(@NonNull String[] permission, PermissionListener listener) {
        permissionListener = listener;
        ArrayList<String> permissionList = new ArrayList<>();
        if (this == null) {
            return;
        }
        for (String s : permission) {
            if (ContextCompat.checkSelfPermission(this, s) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(s);
            }
        }

        if (permissionList.isEmpty()) {//要申请的权限都被授权过了
            permissionListener.permissionGranted();
        } else {//有需要申请的权限
            //申请权限开始时 要关闭悬浮窗，以免提示检测到堆叠窗口，影响用户体验
//            EventBus.getDefault().post(AppEvent.BIGEN_REQUEST_PERMISSIONS);
            ActivityCompat.requestPermissions(this,
                    permissionList.toArray(new String[permissionList.size()]), REQUEST_PERMISSION_CODE);
        }
    }

}
