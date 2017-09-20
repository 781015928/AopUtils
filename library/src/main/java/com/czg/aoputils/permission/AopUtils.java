package com.czg.aoputils.permission;

import android.app.Activity;

/**
 * Created by czg on 2017/8/21.
 */

 public class AopUtils {
    private static Activity activeActivity;

    public static Activity getActiveActivity() {
        return activeActivity;
    }

    public static void updateActiveActivity(Activity activeActivity) {
        AopUtils.activeActivity = activeActivity;
    }
}
