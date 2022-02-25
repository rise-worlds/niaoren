package com.blankj.utilcode.util;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p110z1.Gson;
import p110z1.GsonBuilder;
import p110z1.TypeToken;

/* renamed from: com.blankj.utilcode.util.z */
/* loaded from: classes.dex */
public final class GsonUtils {

    /* renamed from: a */
    private static final Gson f6932a = m22047b(true);

    /* renamed from: b */
    private static final Gson f6933b = m22047b(false);

    private GsonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static Gson m22061a() {
        return m22049a(true);
    }

    /* renamed from: a */
    public static Gson m22049a(boolean z) {
        return z ? f6933b : f6932a;
    }

    /* renamed from: a */
    public static String m22058a(Object obj) {
        return m22055a(obj, true);
    }

    /* renamed from: a */
    public static String m22055a(Object obj, boolean z) {
        return (z ? f6932a : f6933b).m1575b(obj);
    }

    /* renamed from: a */
    public static String m22057a(Object obj, Type type) {
        return m22056a(obj, type, true);
    }

    /* renamed from: a */
    public static String m22056a(Object obj, Type type, boolean z) {
        return (z ? f6932a : f6933b).m1574b(obj, type);
    }

    /* renamed from: a */
    public static <T> T m22054a(String str, Class<T> cls) {
        return (T) f6932a.m1589a(str, (Class<Object>) cls);
    }

    /* renamed from: a */
    public static <T> T m22053a(String str, Type type) {
        return (T) f6932a.m1588a(str, type);
    }

    /* renamed from: a */
    public static <T> T m22060a(Reader reader, Class<T> cls) {
        return (T) f6932a.m1599a(reader, (Class<Object>) cls);
    }

    /* renamed from: a */
    public static <T> T m22059a(Reader reader, Type type) {
        return (T) f6932a.m1598a(reader, type);
    }

    /* renamed from: a */
    public static Type m22052a(Type type) {
        return TypeToken.getParameterized(List.class, type).getType();
    }

    /* renamed from: b */
    public static Type m22048b(Type type) {
        return TypeToken.getParameterized(Set.class, type).getType();
    }

    /* renamed from: a */
    public static Type m22051a(Type type, Type type2) {
        return TypeToken.getParameterized(Map.class, type, type2).getType();
    }

    /* renamed from: c */
    public static Type m22046c(Type type) {
        return TypeToken.getArray(type).getType();
    }

    /* renamed from: a */
    public static Type m22050a(Type type, Type... typeArr) {
        return TypeToken.getParameterized(type, typeArr).getType();
    }

    /* renamed from: b */
    private static Gson m22047b(boolean z) {
        GsonBuilder oyVar = new GsonBuilder();
        if (z) {
            oyVar.m1540c();
        }
        return oyVar.m1533j();
    }
}
