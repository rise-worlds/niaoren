package com.lody.virtual.helper.compat;

import android.os.IBinder;
import android.os.IInterface;
import p110z1.ApplicationThreadNative;
import p110z1.IApplicationThreadOreo;

/* loaded from: classes.dex */
public class ApplicationThreadCompat {
    public static IInterface asInterface(IBinder iBinder) {
        return BuildCompat.isOreo() ? IApplicationThreadOreo.C5117a.asInterface.call(iBinder) : ApplicationThreadNative.asInterface.call(iBinder);
    }
}
