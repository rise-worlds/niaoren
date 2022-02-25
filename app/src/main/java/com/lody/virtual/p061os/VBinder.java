package com.lody.virtual.p061os;

import android.os.Binder;
import com.lody.virtual.client.ipc.VActivityManager;

/* renamed from: com.lody.virtual.os.VBinder */
/* loaded from: classes.dex */
public class VBinder {
    public static int getCallingUid() {
        return VActivityManager.get().getUidByPid(Binder.getCallingPid());
    }

    public static int getBaseCallingUid() {
        return VUserHandle.getAppId(getCallingUid());
    }

    public static int getCallingPid() {
        return Binder.getCallingPid();
    }

    public static VUserHandle getCallingUserHandle() {
        return VUserHandle.getCallingUserHandle();
    }
}
