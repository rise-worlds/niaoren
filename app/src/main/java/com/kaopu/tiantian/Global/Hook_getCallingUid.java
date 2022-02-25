package com.kaopu.tiantian.Global;

import android.os.Binder;
import android.os.Process;
import android.util.Log;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.client.ipc.VActivityManager;

/* loaded from: classes.dex */
public class Hook_getCallingUid {
    private static String TAG = "tiantian";
    public static String className = "android.os.Binder";
    public static String methodName = "getCallingUid";
    public static String methodSig = "()I";
    public static int minSDK = 29;

    public static int hook() {
        int callingPid = Binder.getCallingPid();
        if (callingPid == 0) {
            return Constants.UNKNOWN_APP_UID;
        }
        if (callingPid == Process.myPid()) {
            return VClient.get().getBaseVUid();
        }
        return VActivityManager.get().getUidByPid(callingPid);
    }

    public static int backup() {
        try {
            Log.w(TAG, "should not be here");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
