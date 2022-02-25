package com.blankj.utilcode.util;

import android.support.annotation.NonNull;
import android.support.p003v4.util.LruCache;
import java.util.HashMap;
import java.util.Map;
import p110z1.CacheConstants;

/* renamed from: com.blankj.utilcode.util.k */
/* loaded from: classes.dex */
public final class CacheMemoryUtils implements CacheConstants {

    /* renamed from: e */
    private static final int f6881e = 256;

    /* renamed from: f */
    private static final Map<String, CacheMemoryUtils> f6882f = new HashMap();

    /* renamed from: g */
    private final String f6883g;

    /* renamed from: h */
    private final LruCache<String, C1020a> f6884h;

    /* renamed from: a */
    public static CacheMemoryUtils m22528a() {
        return m22527a(256);
    }

    /* renamed from: a */
    public static CacheMemoryUtils m22527a(int i) {
        return m22525a(String.valueOf(i), i);
    }

    /* renamed from: a */
    public static CacheMemoryUtils m22525a(String str, int i) {
        CacheMemoryUtils kVar = f6882f.get(str);
        if (kVar == null) {
            synchronized (CacheMemoryUtils.class) {
                kVar = f6882f.get(str);
                if (kVar == null) {
                    kVar = new CacheMemoryUtils(str, new LruCache(i));
                    f6882f.put(str, kVar);
                }
            }
        }
        return kVar;
    }

    private CacheMemoryUtils(String str, LruCache<String, C1020a> lruCache) {
        this.f6883g = str;
        this.f6884h = lruCache;
    }

    public String toString() {
        return this.f6883g + "@" + Integer.toHexString(hashCode());
    }

    /* renamed from: a */
    public void m22524a(@NonNull String str, Object obj) {
        if (str != null) {
            m22523a(str, obj, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22523a(@NonNull String str, Object obj, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (obj != null) {
            this.f6884h.put(str, new C1020a(i < 0 ? -1L : System.currentTimeMillis() + (i * 1000), obj));
        }
    }

    /* renamed from: a */
    public <T> T m22526a(@NonNull String str) {
        if (str != null) {
            return (T) m22520b(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public <T> T m22520b(@NonNull String str, T t) {
        if (str != null) {
            C1020a aVar = this.f6884h.get(str);
            if (aVar == null) {
                return t;
            }
            if (aVar.f6885a == -1 || aVar.f6885a >= System.currentTimeMillis()) {
                return (T) aVar.f6886b;
            }
            this.f6884h.remove(str);
            return t;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public int m22522b() {
        return this.f6884h.size();
    }

    /* renamed from: b */
    public Object m22521b(@NonNull String str) {
        if (str != null) {
            C1020a remove = this.f6884h.remove(str);
            if (remove == null) {
                return null;
            }
            return remove.f6886b;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public void m22519c() {
        this.f6884h.evictAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CacheMemoryUtils.java */
    /* renamed from: com.blankj.utilcode.util.k$a */
    /* loaded from: classes.dex */
    public static final class C1020a {

        /* renamed from: a */
        long f6885a;

        /* renamed from: b */
        Object f6886b;

        C1020a(long j, Object obj) {
            this.f6885a = j;
            this.f6886b = obj;
        }
    }
}
