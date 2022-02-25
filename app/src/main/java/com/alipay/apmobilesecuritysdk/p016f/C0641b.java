package com.alipay.apmobilesecuritysdk.p016f;

import java.util.LinkedList;

/* renamed from: com.alipay.apmobilesecuritysdk.f.b */
/* loaded from: classes.dex */
public final class C0641b {

    /* renamed from: a */
    private static C0641b f242a = new C0641b();

    /* renamed from: b */
    private Thread f243b = null;

    /* renamed from: c */
    private LinkedList<Runnable> f244c = new LinkedList<>();

    /* renamed from: a */
    public static C0641b m25346a() {
        return f242a;
    }

    /* renamed from: a */
    public final synchronized void m25344a(Runnable runnable) {
        this.f244c.add(runnable);
        if (this.f243b == null) {
            this.f243b = new Thread(new RunnableC0642c(this));
            this.f243b.start();
        }
    }
}
