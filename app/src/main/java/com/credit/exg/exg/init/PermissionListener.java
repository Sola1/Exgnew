package com.credit.exg.exg.init;

import java.util.List;

/**
 * Created by sunhao on 17/6/28.
 * 权限申请后的回调接口
 */

public interface PermissionListener {

    //成功授权
    void permissionGranted();

    /**
     * 有拒绝的权限是调用
     * @param deniedList 用户拒绝权限的集合
     */
    void permissionDenied(List<String> deniedList);
}
