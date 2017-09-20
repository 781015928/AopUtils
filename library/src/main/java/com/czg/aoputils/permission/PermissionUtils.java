package com.czg.aoputils.permission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by czg on 2017/8/21.
 */

class PermissionUtils {
    static PermissionUtils mPermissionUtils = new PermissionUtils();
    CallBack callBack;

    /**
     * @param permissions
     * @param callBack
     */
    public void checkPermission(String[] permissions, CallBack callBack) {
        this.callBack = callBack;
        permissions = filterPermissions(permissions);
        if (permissions.length == 0) {//权限已经获取 直接调用
            callBack.onSuccess();
            return;
        }
        if (AopUtils.getActiveActivity() != null) {
            Intent intent = new Intent(AopUtils.getActiveActivity(), PermissionsShadowActivity.class);//权限未获取
            intent.putExtra(PermissionsShadowActivity.PERMISSIONS, permissions);

            AopUtils.getActiveActivity().startActivity(intent);
        }
    }

    /**
     * 过滤掉已经获得的权限
     *
     * @param permissions
     * @return
     */
    public String[] filterPermissions(String[] permissions) {
        ArrayList<String> permissionArray = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(AopUtils.getActiveActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                permissionArray.add(permission);
            }
        }
        permissions = new String[permissionArray.size()];
        for (int index = 0; index < permissionArray.size(); index++) {
            permissions[index] = permissionArray.get(index);
        }
        return permissions;
    }
}
