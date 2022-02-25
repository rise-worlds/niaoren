package com.cyjh.mobileanjian.ipc.utils;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.m */
/* loaded from: classes.dex */
public class RpcError {

    /* renamed from: a */
    public static final String f8691a = "m";

    /* renamed from: b */
    public static final int f8692b = 0;

    /* renamed from: c */
    public static final int f8693c = 1;

    /* renamed from: d */
    public static final int f8694d = 2;

    /* renamed from: e */
    public static final int f8695e = 3;

    /* renamed from: f */
    public static final int f8696f = 4;

    /* renamed from: g */
    public static final int f8697g = 5;

    /* renamed from: h */
    public static final int f8698h = 6;

    /* renamed from: i */
    public static final int f8699i = 7;

    /* renamed from: a */
    private static String m20623a(int i) {
        return RpcError.class.getFields()[i].getName();
    }

    /* renamed from: b */
    private static void m20622b(int i) {
        StringBuilder sb = new StringBuilder("errno=");
        sb.append(i);
        sb.append("; ");
        sb.append(RpcError.class.getFields()[i].getName());
    }
}
