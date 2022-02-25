package com.cyjh.ddy.base.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p110z1.Gson;
import p110z1.GsonBuilder;
import p110z1.TypeToken;

/* compiled from: GsonUtils.java */
/* renamed from: com.cyjh.ddy.base.utils.f */
/* loaded from: classes.dex */
public final class C1123f {

    /* renamed from: a */
    private static final String f7122a = "defaultGson";

    /* renamed from: b */
    private static final String f7123b = "delegateGson";

    /* renamed from: c */
    private static final String f7124c = "logUtilsGson";

    /* renamed from: d */
    private static final Map<String, Gson> f7125d = new HashMap();

    private C1123f() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m21836a(Gson oxVar) {
        if (oxVar != null) {
            f7125d.put(f7123b, oxVar);
        }
    }

    /* renamed from: a */
    public static void m21840a(String str, Gson oxVar) {
        if (!TextUtils.isEmpty(str) && oxVar != null) {
            f7125d.put(str, oxVar);
        }
    }

    /* renamed from: a */
    public static Gson m21843a(String str) {
        return f7125d.get(str);
    }

    /* renamed from: a */
    public static Gson m21848a() {
        Gson oxVar = f7125d.get(f7123b);
        if (oxVar != null) {
            return oxVar;
        }
        Gson oxVar2 = f7125d.get(f7122a);
        if (oxVar2 != null) {
            return oxVar2;
        }
        Gson j = new GsonBuilder().m1535h().m1533j();
        f7125d.put(f7122a, j);
        return j;
    }

    /* renamed from: a */
    public static String m21845a(@NonNull Object obj) {
        return m21833a(m21848a(), obj);
    }

    /* renamed from: a */
    public static String m21844a(@NonNull Object obj, @NonNull Type type) {
        return m21832a(m21848a(), obj, type);
    }

    /* renamed from: a */
    public static String m21833a(@NonNull Gson oxVar, @NonNull Object obj) {
        return oxVar.m1575b(obj);
    }

    /* renamed from: a */
    public static String m21832a(@NonNull Gson oxVar, @NonNull Object obj, @NonNull Type type) {
        return oxVar.m1574b(obj, type);
    }

    /* renamed from: a */
    public static <T> T m21842a(@NonNull String str, @NonNull Class<T> cls) {
        return (T) m21831a(m21848a(), str, (Class<Object>) cls);
    }

    /* renamed from: a */
    public static <T> T m21841a(@NonNull String str, @NonNull Type type) {
        return (T) m21830a(m21848a(), str, type);
    }

    /* renamed from: a */
    public static <T> T m21847a(@NonNull Reader reader, @NonNull Class<T> cls) {
        return (T) m21835a(m21848a(), reader, (Class<Object>) cls);
    }

    /* renamed from: a */
    public static <T> T m21846a(@NonNull Reader reader, @NonNull Type type) {
        return (T) m21834a(m21848a(), reader, type);
    }

    /* renamed from: a */
    public static <T> T m21831a(@NonNull Gson oxVar, @NonNull String str, @NonNull Class<T> cls) {
        return (T) oxVar.m1589a(str, (Class<Object>) cls);
    }

    /* renamed from: a */
    public static <T> T m21830a(@NonNull Gson oxVar, @NonNull String str, @NonNull Type type) {
        return (T) oxVar.m1588a(str, type);
    }

    /* renamed from: a */
    public static <T> T m21835a(@NonNull Gson oxVar, @NonNull Reader reader, @NonNull Class<T> cls) {
        return (T) oxVar.m1599a(reader, (Class<Object>) cls);
    }

    /* renamed from: a */
    public static <T> T m21834a(@NonNull Gson oxVar, @NonNull Reader reader, @NonNull Type type) {
        return (T) oxVar.m1598a(reader, type);
    }

    /* renamed from: a */
    public static Type m21839a(@NonNull Type type) {
        return TypeToken.getParameterized(List.class, type).getType();
    }

    /* renamed from: b */
    public static Type m21828b(@NonNull Type type) {
        return TypeToken.getParameterized(Set.class, type).getType();
    }

    /* renamed from: a */
    public static Type m21838a(@NonNull Type type, @NonNull Type type2) {
        return TypeToken.getParameterized(Map.class, type, type2).getType();
    }

    /* renamed from: c */
    public static Type m21826c(@NonNull Type type) {
        return TypeToken.getArray(type).getType();
    }

    /* renamed from: a */
    public static Type m21837a(@NonNull Type type, @NonNull Type... typeArr) {
        return TypeToken.getParameterized(type, typeArr).getType();
    }

    /* renamed from: b */
    static Gson m21829b() {
        Gson oxVar = f7125d.get(f7124c);
        if (oxVar != null) {
            return oxVar;
        }
        Gson j = new GsonBuilder().m1537f().m1540c().m1533j();
        f7125d.put(f7124c, j);
        return j;
    }

    /* renamed from: c */
    private static Gson m21827c() {
        return new GsonBuilder().m1540c().m1535h().m1533j();
    }
}
