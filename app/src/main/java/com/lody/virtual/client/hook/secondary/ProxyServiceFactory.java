package com.lody.virtual.client.hook.secondary;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ProxyServiceFactory {
    private static final String TAG = "ProxyServiceFactory";

    /* renamed from: e */
    public static final String f10495e = "androidPackageName";

    /* renamed from: f */
    public static final String f10496f = "clientPackageName";
    private static Map<String, ServiceFetcher> sHookSecondaryServiceMap = new HashMap();

    /* loaded from: classes.dex */
    private interface ServiceFetcher {
        IBinder getService(Context context, ClassLoader classLoader, IBinder iBinder);
    }

    public static IBinder getProxyService(Context context, ComponentName componentName, IBinder iBinder) {
        if (context == null || iBinder == null) {
            return null;
        }
        try {
            ServiceFetcher serviceFetcher = sHookSecondaryServiceMap.get(iBinder.getInterfaceDescriptor());
            if (serviceFetcher != null) {
                IBinder service = serviceFetcher.getService(context, context.getClassLoader(), iBinder);
                if (service != null) {
                    return service;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }
}
