# AopUtils
### DebugLog

##### classpath
```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.0.8'
       
    }
}
```
##### apply plugin
```
apply plugin: 'android-aspectjx'
```


### debug
debugCompile 'com.czg.utils:UtilsDebug:1.0.4' 

### release
 release版本部关闭所有功能

releaseCompile  'com.czg.utils:UtilsRelease:1.0.4' 


在方法上加上@DebugLog 
![Image text](https://github.com/781015928/Utils/blob/master/snapshot/SNAPSHOT_1.png)
   ``` 
  @DebugLog
    private static String  getStr2222(String str,String str2,String str3){
        return "33333"+str+str3;
    }

```
![Image text](https://github.com/781015928/Utils/blob/master/snapshot/SNAPSHOT_2.png)
 ```
   @DebugLog(DebugLog.ERROR)
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
      }
  
 ```
 
 

## @Permission 注解实现android23权限请求
```
    @Permission({Manifest.permission.READ_CONTACTS})
     private void needContacts() {
         Toast.makeText(MainActivity.this, "通讯录权限获取成功", Toast.LENGTH_SHORT).show();
     }
```
![Image text](https://github.com/781015928/Utils/blob/master/snapshot/SNAPSHOT_4.png)
![Image text](https://github.com/781015928/Utils/blob/master/snapshot/SNAPSHOT_5.png)

```
    @Permission({Manifest.permission.READ_CALL_LOG
            , Manifest.permission.READ_PHONE_STATE
            , Manifest.permission.CALL_PHONE
            , Manifest.permission.CAMERA
            , Manifest.permission.USE_SIP
            , Manifest.permission.SEND_SMS})
    private void needMore() {
        Toast.makeText(MainActivity.this, "多个权限获取成功", Toast.LENGTH_SHORT).show();
    }
```
![Image text](https://github.com/781015928/Utils/blob/master/snapshot/SNAPSHOT_6.png)
![Image text](https://github.com/781015928/Utils/blob/master/snapshot/SNAPSHOT_7.png)
