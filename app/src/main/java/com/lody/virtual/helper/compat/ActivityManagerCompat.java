package com.lody.virtual.helper.compat;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import p110z1.ActivityManager;
import p110z1.ActivityManagerNative;
import p110z1.IActivityManager;
import p110z1.IActivityManagerICS;
import p110z1.IActivityManagerL;
import p110z1.IActivityManagerN;

/* loaded from: classes.dex */
public class ActivityManagerCompat {
    public static final int INTENT_SENDER_ACTIVITY = 2;
    public static final int INTENT_SENDER_ACTIVITY_RESULT = 3;
    public static final int INTENT_SENDER_BROADCAST = 1;
    public static final int INTENT_SENDER_SERVICE = 4;
    public static final int SERVICE_DONE_EXECUTING_ANON = 0;
    public static final int SERVICE_DONE_EXECUTING_START = 1;
    public static final int SERVICE_DONE_EXECUTING_STOP = 2;
    public static final int START_INTENT_NOT_RESOLVED;
    public static final int START_NOT_CURRENT_USER_ACTIVITY;
    public static final int START_TASK_TO_FRONT;
    public static final int USER_OP_SUCCESS = 0;

    static {
        START_INTENT_NOT_RESOLVED = ActivityManager.START_INTENT_NOT_RESOLVED == null ? -1 : ActivityManager.START_INTENT_NOT_RESOLVED.get();
        START_NOT_CURRENT_USER_ACTIVITY = ActivityManager.START_NOT_CURRENT_USER_ACTIVITY == null ? -8 : ActivityManager.START_NOT_CURRENT_USER_ACTIVITY.get();
        START_TASK_TO_FRONT = ActivityManager.START_TASK_TO_FRONT == null ? 2 : ActivityManager.START_TASK_TO_FRONT.get();
    }

    public static boolean finishActivity(IBinder iBinder, int i, Intent intent) {
        if (Build.VERSION.SDK_INT >= 24) {
            return IActivityManagerN.finishActivity.call(ActivityManagerNative.getDefault.call(new Object[0]), iBinder, Integer.valueOf(i), intent, 0).booleanValue();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return IActivityManagerL.finishActivity.call(ActivityManagerNative.getDefault.call(new Object[0]), iBinder, Integer.valueOf(i), intent, false).booleanValue();
        }
        IActivityManagerICS.finishActivity.call(ActivityManagerNative.getDefault.call(new Object[0]), iBinder, Integer.valueOf(i), intent);
        return false;
    }

    public static void setActivityOrientation(Activity activity, int i) {
        try {
            activity.setRequestedOrientation(i);
        } catch (Throwable th) {
            th.printStackTrace();
            Activity activity2 = p110z1.Activity.mParent.get(activity);
            while (activity2 != null) {
                activity2 = p110z1.Activity.mParent.get(activity2);
            }
            try {
                IActivityManager.setRequestedOrientation.call(ActivityManagerNative.getDefault.call(new Object[0]), p110z1.Activity.mToken.get(activity2), Integer.valueOf(i));
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }
}
