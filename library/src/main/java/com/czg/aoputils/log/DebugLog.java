package com.czg.aoputils.log;


import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by czg on 2017/8/17.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DebugLog {
    int ERROR = 4;
    int DEBUG = 3;
    int WARNING = 2;
    int INFO = 1;

    @Level
    int value() default DebugLog.DEBUG;


    @IntDef({DebugLog.ERROR, DebugLog.DEBUG, DebugLog.WARNING, DebugLog.INFO})
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    @interface Level {

    }

}
