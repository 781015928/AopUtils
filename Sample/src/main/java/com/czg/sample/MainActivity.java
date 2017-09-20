package com.czg.sample;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.czg.aoputils.permission.Permission;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("----");
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                needContacts();
                break;
            case R.id.btn2:
                needWriteExternalStorage();
                break;
            case R.id.btn3:
                needMore();
                break;

        }


    }

    @Permission({Manifest.permission.READ_CONTACTS})
    private void needContacts() {
        Toast.makeText(MainActivity.this, "通讯录权限获取成功", Toast.LENGTH_SHORT).show();
    }

    @Permission({Manifest.permission.WRITE_EXTERNAL_STORAGE})
    private void needWriteExternalStorage() {
        Toast.makeText(MainActivity.this, "写入存储获取成功", Toast.LENGTH_SHORT).show();
    }

    @Permission({Manifest.permission.READ_CALL_LOG
            , Manifest.permission.READ_PHONE_STATE
            , Manifest.permission.CALL_PHONE
            , Manifest.permission.CAMERA
            , Manifest.permission.USE_SIP
            , Manifest.permission.SEND_SMS})
    private void needMore() {
        Toast.makeText(MainActivity.this, "多个权限获取成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
