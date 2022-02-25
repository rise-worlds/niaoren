package com.p018b.p019a.p020a;

/* renamed from: com.b.a.a.b */
/* loaded from: classes.dex */
public abstract class NamedRunnable implements Runnable {

    /* renamed from: b */
    protected final String f5733b;

    /* renamed from: b */
    public abstract void mo24472b();

    public NamedRunnable(String str, Object... objArr) {
        this.f5733b = Util.m24755a(str, objArr);
    }

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f5733b);
        try {
            mo24472b();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
