package com.czg.aoputils.permission;

import android.Manifest;
import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by czg on 2017/8/18.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Permission {

    @ManifestPermission
    String[] value() default {};


    @StringDef({

            Manifest.permission.WRITE_CONTACTS
            , Manifest.permission.GET_ACCOUNTS
            , Manifest.permission.READ_CONTACTS

            , Manifest.permission.READ_CALL_LOG
            , Manifest.permission.READ_PHONE_STATE
            , Manifest.permission.CALL_PHONE
            , Manifest.permission.WRITE_CALL_LOG
            , Manifest.permission.USE_SIP
            , Manifest.permission.PROCESS_OUTGOING_CALLS

            , Manifest.permission.READ_CALENDAR
            , Manifest.permission.WRITE_CALENDAR

            , Manifest.permission.CAMERA

            , Manifest.permission.BODY_SENSORS

            , Manifest.permission.ACCESS_FINE_LOCATION
            , Manifest.permission.ACCESS_COARSE_LOCATION

            , Manifest.permission.READ_EXTERNAL_STORAGE
            , Manifest.permission.WRITE_EXTERNAL_STORAGE

            , Manifest.permission.RECORD_AUDIO

            , Manifest.permission.READ_SMS
            , Manifest.permission.RECEIVE_WAP_PUSH
            , Manifest.permission.RECEIVE_MMS
            , Manifest.permission.RECEIVE_SMS
            , Manifest.permission.SEND_SMS
    })
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    @interface ManifestPermission {

    }

}
