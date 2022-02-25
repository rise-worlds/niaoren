package com.lody.virtual.helper.compat;

import p110z1.StrictMode;

/* loaded from: classes.dex */
public class StrictModeCompat {
    public static int DETECT_VM_FILE_URI_EXPOSURE;
    public static int PENALTY_DEATH_ON_FILE_URI_EXPOSURE;

    static {
        DETECT_VM_FILE_URI_EXPOSURE = StrictMode.DETECT_VM_FILE_URI_EXPOSURE == null ? 8192 : StrictMode.DETECT_VM_FILE_URI_EXPOSURE.get();
        PENALTY_DEATH_ON_FILE_URI_EXPOSURE = StrictMode.PENALTY_DEATH_ON_FILE_URI_EXPOSURE == null ? 67108864 : StrictMode.PENALTY_DEATH_ON_FILE_URI_EXPOSURE.get();
    }

    public static boolean disableDeathOnFileUriExposure() {
        try {
            try {
                StrictMode.disableDeathOnFileUriExposure.call(new Object[0]);
                return true;
            } catch (Throwable unused) {
                StrictMode.sVmPolicyMask.set(StrictMode.sVmPolicyMask.get() & (~(DETECT_VM_FILE_URI_EXPOSURE | PENALTY_DEATH_ON_FILE_URI_EXPOSURE)));
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
