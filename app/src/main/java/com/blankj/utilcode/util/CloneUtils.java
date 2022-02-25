package com.blankj.utilcode.util;

import java.lang.reflect.Type;
import p110z1.Gson;

/* renamed from: com.blankj.utilcode.util.n */
/* loaded from: classes.dex */
public final class CloneUtils {
    private CloneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static <T> T m22489a(T t, Type type) {
        try {
            Gson oxVar = new Gson();
            return (T) oxVar.m1588a(oxVar.m1575b(t), type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
