package com.lody.virtual.andr11;

import com.lody.virtual.client.core.VirtualCoreProxy;
import com.lody.virtual.helper.utils.VLog;

/* loaded from: classes.dex */
public class and11CofigManager {
    private static and11CofigManager manager;

    public void InstallStaute() {
    }

    public void MyAppRequest(String str) {
    }

    public static and11CofigManager getInstance() {
        and11CofigManager and11cofigmanager = manager;
        if (and11cofigmanager == null) {
            synchronized (and11CofigManager.class) {
                and11cofigmanager = manager;
                if (and11cofigmanager == null) {
                    and11cofigmanager = new and11CofigManager();
                    manager = and11cofigmanager;
                }
            }
        }
        return and11cofigmanager;
    }

    public boolean isExtPackagerInsalled() {
        return !VirtualCoreProxy.isEngineInstalled();
    }

    private static void info(String str) {
        VLog.m18992e("AppInstaller", str);
    }
}
