package com.czg.aoputils.permission;

import android.os.Build;
import android.os.Looper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by czg on 2017/8/21.
 */
@Aspect
public class PermissionAspect {
    private static final String POINTCUTVALUE = "execution(@com.czg.aoputils.permission.Permission  * *(..))";

    @Pointcut(POINTCUTVALUE)
    public void permissionPoint() {

    }

    @Around("permissionPoint()")//tangent
    public Object permissionTangent(final ProceedingJoinPoint point) throws Throwable {
        //方法执行前
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("Must use @Permission in Main Thread");
        }

        MethodSignature methodSignature = (MethodSignature) point.getSignature();

        if (methodSignature.getReturnType() != void.class) {
            throw new RuntimeException("@Permission    ReturnType  must is void ");
        }

        Permission behaviorTrace = methodSignature.getMethod().getAnnotation(Permission.class);//拿到注解

        String[] permissions = behaviorTrace.value();
        if (permissions.length > 0) {//注解是否含有权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断系统版本
                try {
                    PermissionUtils.mPermissionUtils.checkPermission(permissions, new CallBack(point));
                } catch (Exception e) {

                }


            } else {
                point.proceed();
            }
        } else {
            point.proceed();
        }
        //方法执行时


        return null;
    }


}
