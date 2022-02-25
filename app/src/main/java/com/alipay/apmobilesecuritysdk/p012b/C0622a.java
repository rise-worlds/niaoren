package com.alipay.apmobilesecuritysdk.p012b;

import p110z1.C5097cq;
import p110z1.C5268dn;

/* renamed from: com.alipay.apmobilesecuritysdk.b.a */
/* loaded from: classes.dex */
public final class C0622a {

    /* renamed from: b */
    private static C0622a f218b = new C0622a();

    /* renamed from: a */
    private int f219a = 0;

    /* renamed from: a */
    public static C0622a m25434a() {
        return f218b;
    }

    /* renamed from: a */
    public final void m25433a(int i) {
        this.f219a = i;
    }

    /* renamed from: b */
    public final int m25432b() {
        return this.f219a;
    }

    /* renamed from: c */
    public final String m25431c() {
        String a = C5268dn.m3211a();
        if (C5097cq.m3695b(a)) {
            return a;
        }
        switch (this.f219a) {
            case 1:
                return "http://mobilegw.stable.alipay.net/mgw.htm";
            case 2:
                return "https://mobilegw.alipay.com/mgw.htm";
            case 3:
                return "http://mobilegw-1-64.test.alipay.net/mgw.htm";
            case 4:
                return "http://mobilegw.aaa.alipay.net/mgw.htm";
            default:
                return "https://mobilegw.alipay.com/mgw.htm";
        }
    }
}
