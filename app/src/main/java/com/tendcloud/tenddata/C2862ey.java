package com.tendcloud.tenddata;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ey */
/* loaded from: classes2.dex */
public final class C2862ey {

    /* renamed from: a */
    private static final Map f13930a = new ConcurrentHashMap();

    /* renamed from: a */
    private static void m15771a(Class cls) {
        Method[] declaredMethods;
        try {
            HashMap hashMap = new HashMap();
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().startsWith("onTDEBEvent") && method.getParameterTypes().length == 1) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        C2811dq.eForInternal("Method " + method + " must have one and only one argument.");
                    }
                    Class<?> cls2 = parameterTypes[0];
                    if (cls2.isInterface()) {
                        C2811dq.eForInternal("Method " + method + " must have a argument whose type is a class which can be instantialized.");
                    }
                    if ((method.getModifiers() & 1) == 0) {
                        C2811dq.eForInternal("Method " + method + " must be 'public'.");
                    }
                    Set set = (Set) hashMap.get(cls2);
                    if (set == null) {
                        set = new HashSet();
                        hashMap.put(cls2, set);
                    }
                    set.add(method);
                }
            }
            f13930a.put(cls, hashMap);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Map m15770a(Object obj) {
        HashMap hashMap = new HashMap();
        try {
            Class<?> cls = obj.getClass();
            if (!f13930a.containsKey(cls)) {
                m15771a((Class) cls);
            }
            Map map = (Map) f13930a.get(cls);
            if (map != null && !map.isEmpty()) {
                for (Map.Entry entry : map.entrySet()) {
                    HashSet hashSet = new HashSet();
                    for (Method method : (Set) entry.getValue()) {
                        hashSet.add(new C2863ez(obj, method));
                    }
                    hashMap.put(entry.getKey(), hashSet);
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return hashMap;
    }

    private C2862ey() {
    }
}
