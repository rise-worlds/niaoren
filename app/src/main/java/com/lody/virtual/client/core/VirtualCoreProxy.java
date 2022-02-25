package com.lody.virtual.client.core;

import com.lody.virtual.server.bit64.V64BitHelper;

/* loaded from: classes.dex */
public final class VirtualCoreProxy {
    public static boolean isEngineInstalled() {
        return VirtualCore.get().is64BitEngineInstalled();
    }

    public static boolean hasEngineStartPermission() {
        return V64BitHelper.has64BitEngineStartPermission();
    }

    public static boolean isRunProcess(String str) {
        return VirtualCore.get().isRun64BitProcess(str);
    }
}
