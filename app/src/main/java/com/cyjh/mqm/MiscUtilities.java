package com.cyjh.mqm;

/* loaded from: classes.dex */
public class MiscUtilities {
    public native String LoadUIFile(String str, boolean z);

    static {
        System.loadLibrary("mqm");
    }
}
