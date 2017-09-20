package com.czg.aoputils.log;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by czg on 2017/8/17.
 */
@Aspect
public class DebugAspect {
    private static final String TAG = DebugLog.class.getSimpleName();//┏
    private static final String LINE = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    private static final String TOP_CORNER = "┏";
    private static final String BOTTOM_CORNER = "┗";
    private static final String LEFTLINE = "┃";
    private static final String TAB = "     ";
    private static final String POINTCUTVALUE="execution(@com.czg.aoputils.log.DebugLog  * *(..))";
    @Pointcut(POINTCUTVALUE)
    public void debugPoint() {

    }

    @Around("debugPoint()")//tangent
    public Object debugTangent(ProceedingJoinPoint point) throws Throwable {
        //方法执行前
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        DebugLog behaviorTrace = methodSignature.getMethod().getAnnotation(DebugLog.class);
        int level = behaviorTrace.value();


        //方法执行时
        Object object = null;

        long beagin = System.currentTimeMillis();
        object = point.proceed();

        //方法执行完成
        print(level, TOP_CORNER + LINE);
        print(level, LEFTLINE + TAB + " This  :" + (point.getThis()==null?"static":point.getThis().toString()));
        print(level, LEFTLINE + TAB + "Thread :" + Thread.currentThread().getName());
        StringBuilder methodStr = new StringBuilder(LEFTLINE);
        methodStr.append(TAB);
        methodStr.append("Method :");
        methodStr.append(methodSignature.getName());
        methodStr.append("( ");

        for (int index = 0; index < methodSignature.getParameterTypes().length; index++) {
            Class parmsType = methodSignature.getParameterTypes()[index];
            String parmsName = methodSignature.getParameterNames()[index];
            methodStr.append(parmsType.getSimpleName());
            methodStr.append(" ");
            methodStr.append(parmsName);
            methodStr.append(" ");
            if (index != methodSignature.getParameterTypes().length - 1) {
                methodStr.append(",");
            }
        }


        methodStr.append(")");
        print(level, methodStr.toString());
        for (int index = 0; index < methodSignature.getParameterTypes().length; index++) {
            String parmsName = methodSignature.getParameterNames()[index];
            Object parmsValue = point.getArgs()[index];
            print(level, LEFTLINE + TAB +TAB+"   "+ parmsName+ " = "+(parmsValue!=null?parmsValue.toString():"null"));
        }
        long time = System.currentTimeMillis() - beagin;
        print(level, LEFTLINE + TAB + "return :" + (methodSignature.getReturnType() == void.class ? void.class.getSimpleName() : methodSignature.getReturnType().getSimpleName() + ":" + (object != null ? object.toString() : "null")));
        print(level, LEFTLINE + TAB + " Time  :" + time);
        print(level, BOTTOM_CORNER + LINE);//耗时
        return object;
    }

    private void print(int level, String msg) {
        switch (level) {
            case DebugLog.INFO:
                Log.i(TAG, msg);
                break;
            case DebugLog.WARNING:
                Log.w(TAG, msg);
                break;
            case DebugLog.DEBUG:
                Log.d(TAG, msg);
                break;
            case DebugLog.ERROR:
                Log.e(TAG, msg);
                break;

        }
    }


}
