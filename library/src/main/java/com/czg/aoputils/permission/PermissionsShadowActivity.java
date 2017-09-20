package com.czg.aoputils.permission;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.czg.aoputils.R;

/**
 * Created by czg on 2017/8/18.
 */

public class PermissionsShadowActivity extends AppCompatActivity {
    private String[] permissions;
    private AlertDialog dialog;
    private static final int PERMISSION_REQUEST_CODE = 321;//弹出窗口开启
    private static final int PERMISSION_REQUEST_SETTING = 123;//手动去设置界面开启
    public static final String PERMISSIONS = "permissions";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissions = getIntent().getStringArrayExtra(PERMISSIONS);
        startRequestPermission();
    }

    // 开始提交请求权限
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.permissions = permissions = PermissionUtils.mPermissionUtils.filterPermissions(permissions);
                if (permissions.length == 0) {
                    success();

                } else {
                    boolean grantResultBool = false;
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            grantResultBool = true;
                        }
                    }
                    if (grantResultBool) {//拒絕了 點擊了不再提示
                        if (!shouldShowRequestPermissionsRationale(permissions)) {
                            // 用户还是想用我的 APP 的
                            // 提示用户去应用设置界面手动开启权限
                            showDialogTipUserGoToAppSettting();
                        } else {//拒絕了
                            onFail();

                        }

                    } else {
                        success();

                    }
                }


            }
        }
    }

    private void onFail() {
        if (PermissionUtils.mPermissionUtils.callBack != null) {
            PermissionUtils.mPermissionUtils.callBack.onFail();

        }
        finish();

    }

    private void success() {
        if (PermissionUtils.mPermissionUtils.callBack != null) {
            PermissionUtils.mPermissionUtils.callBack.onSuccess();//Access to success
            Toast.makeText(this, R.string.permission_to_success, Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    /**
     * // 判断用户是否 点击了不再提醒.
     *
     * @param permissions
     * @return false 点击了 true 未点击
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean shouldShowRequestPermissionsRationale(String[] permissions) {
        for (String permission : permissions) {
            if (!shouldShowRequestPermissionRationale(permission)) {
                return false;//点击了不再提醒.
            }
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_REQUEST_SETTING) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取
                String[] permissions = PermissionUtils.mPermissionUtils.filterPermissions(this.permissions);
                if (permissions.length > 0) {//还有未获取权限
                    showDialogTipUserGoToAppSettting();
                } else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    success();
                }
            }
        }
    }

    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, PERMISSION_REQUEST_SETTING);
    }

    private void showDialogTipUserGoToAppSettting() {
        dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.permission_not_available)
                //.setMessage("请在-应用设置-权限-中，允许使用存储权限来保存用户数据")
                .setMessage(R.string.permission_not_available_msg)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到应用设置界面
                        goToAppSetting();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }
}
