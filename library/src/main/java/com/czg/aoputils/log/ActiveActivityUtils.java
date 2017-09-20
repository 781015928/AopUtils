package com.czg.aoputils.log;

import android.app.Activity;

import com.czg.aoputils.permission.AopUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by czg on 2017/8/17.
 */
@Aspect
public class ActiveActivityUtils {

    @Before("execution(* android.app.Activity.onResume(..))")
    public void onActivityResume(JoinPoint joinPoint) {
        AopUtils.updateActiveActivity((Activity) joinPoint.getThis());
    }


}
