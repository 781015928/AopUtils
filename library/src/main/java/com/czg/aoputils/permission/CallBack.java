package com.czg.aoputils.permission;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by czg on 2017/8/21.
 */

class CallBack {
    public CallBack(ProceedingJoinPoint point) {
        this.point = point;
    }

    ProceedingJoinPoint point;

    void onSuccess() {
        try {
            point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    ;

    void onFail() {
    }

    ;
}
