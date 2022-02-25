package com.blankj.utilcode.util;

import android.support.annotation.NonNull;

/* renamed from: com.blankj.utilcode.util.j */
/* loaded from: classes.dex */
public final class CacheMemoryStaticUtils {

    /* renamed from: a */
    private static CacheMemoryUtils f6880a;

    /* renamed from: a */
    public static void m22543a(CacheMemoryUtils kVar) {
        f6880a = kVar;
    }

    /* renamed from: a */
    public static void m22540a(@NonNull String str, Object obj) {
        if (str != null) {
            m22537a(str, obj, m22530c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22539a(@NonNull String str, Object obj, int i) {
        if (str != null) {
            m22538a(str, obj, i, m22530c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static <T> T m22542a(@NonNull String str) {
        if (str != null) {
            return (T) m22541a(str, m22530c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static <T> T m22532b(@NonNull String str, T t) {
        if (str != null) {
            return (T) m22531b(str, t, m22530c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static int m22544a() {
        return m22535b(m22530c());
    }

    /* renamed from: b */
    public static Object m22534b(@NonNull String str) {
        if (str != null) {
            return m22533b(str, m22530c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m22536b() {
        m22529c(m22530c());
    }

    /* renamed from: a */
    public static void m22537a(@NonNull String str, Object obj, @NonNull CacheMemoryUtils kVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (kVar != null) {
            kVar.m22524a(str, obj);
        } else {
            throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22538a(@NonNull String str, Object obj, int i, @NonNull CacheMemoryUtils kVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (kVar != null) {
            kVar.m22523a(str, obj, i);
        } else {
            throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static <T> T m22541a(@NonNull String str, @NonNull CacheMemoryUtils kVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (kVar != null) {
            return (T) kVar.m22526a(str);
        } else {
            throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static <T> T m22531b(@NonNull String str, T t, @NonNull CacheMemoryUtils kVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (kVar != null) {
            return (T) kVar.m22520b(str, t);
        } else {
            throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static int m22535b(@NonNull CacheMemoryUtils kVar) {
        if (kVar != null) {
            return kVar.m22522b();
        }
        throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static Object m22533b(@NonNull String str, @NonNull CacheMemoryUtils kVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (kVar != null) {
            return kVar.m22521b(str);
        } else {
            throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static void m22529c(@NonNull CacheMemoryUtils kVar) {
        if (kVar != null) {
            kVar.m22519c();
            return;
        }
        throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    private static CacheMemoryUtils m22530c() {
        CacheMemoryUtils kVar = f6880a;
        return kVar != null ? kVar : CacheMemoryUtils.m22528a();
    }
}
