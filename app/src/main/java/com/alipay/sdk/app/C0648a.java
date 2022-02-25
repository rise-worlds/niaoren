package com.alipay.sdk.app;

/* renamed from: com.alipay.sdk.app.a */
/* loaded from: classes.dex */
public class C0648a {

    /* renamed from: a */
    private static EnumC0649a f297a = EnumC0649a.ONLINE;

    /* renamed from: com.alipay.sdk.app.a$a */
    /* loaded from: classes.dex */
    public enum EnumC0649a {
        ONLINE,
        SANDBOX
    }

    /* renamed from: a */
    public static void m25302a(EnumC0649a aVar) {
        f297a = aVar;
    }

    /* renamed from: a */
    public static EnumC0649a m25303a() {
        return f297a;
    }

    /* renamed from: b */
    public static boolean m25301b() {
        return f297a == EnumC0649a.SANDBOX;
    }
}
