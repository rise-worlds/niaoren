package com.lody.virtual.helper.utils;

import android.os.IInterface;

/* loaded from: classes.dex */
public class IInterfaceUtils {
    public static boolean isAlive(IInterface iInterface) {
        if (iInterface == null) {
            return false;
        }
        return iInterface.asBinder().isBinderAlive();
    }
}
