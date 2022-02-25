package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.ReflectionUtils;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

/* loaded from: classes2.dex */
public final class CacheManager {
    @Deprecated
    public static File getCacheFileBaseDir() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return (File) ReflectionUtils.m16403a("android.webkit.CacheManager", "getCacheFileBaseDir");
        }
        return (File) a.m16616c().m16579g();
    }

    @Deprecated
    public static boolean cacheDisabled() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            return ((Boolean) a.m16616c().m16592c()).booleanValue();
        }
        Object a2 = ReflectionUtils.m16403a("android.webkit.CacheManager", "cacheDisabled");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public static Object getCacheFile(String str, Map<String, String> map) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            return a.m16616c().m16579g();
        }
        try {
            return ReflectionUtils.m16407a(Class.forName("android.webkit.CacheManager"), "getCacheFile", (Class<?>[]) new Class[]{String.class, Map.class}, str, map);
        } catch (Exception unused) {
            return null;
        }
    }

    public static InputStream getCacheFile(String str, boolean z) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return null;
        }
        return a.m16616c().m16602a(str, z);
    }
}
