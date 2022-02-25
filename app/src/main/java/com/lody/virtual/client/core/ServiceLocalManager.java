package com.lody.virtual.client.core;

import com.lody.virtual.client.hook.base.BinderInvocationStub;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ServiceLocalManager {
    private static final Map<String, BinderInvocationStub> sCache = new HashMap();

    public static void addService(String str, BinderInvocationStub binderInvocationStub) {
        synchronized (sCache) {
            sCache.put(str, binderInvocationStub);
        }
    }

    public static BinderInvocationStub getService(String str) {
        BinderInvocationStub binderInvocationStub;
        synchronized (sCache) {
            binderInvocationStub = sCache.get(str);
        }
        return binderInvocationStub;
    }
}
