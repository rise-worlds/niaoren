package com.cyjh.ddy.net.p037b;

/* renamed from: com.cyjh.ddy.net.b.b */
/* loaded from: classes.dex */
public class ApiExection extends RuntimeException implements IExection {

    /* renamed from: a */
    public static final int f7469a = -1001;

    /* renamed from: b */
    public static final int f7470b = -1002;

    /* renamed from: c */
    public int f7471c;

    /* renamed from: d */
    public Object f7472d;

    /* renamed from: e */
    public Throwable f7473e;

    /* renamed from: f */
    public Exception f7474f;

    public ApiExection() {
    }

    public ApiExection(int i) {
        this.f7471c = i;
    }

    public ApiExection(int i, Object obj) {
        this.f7471c = i;
        this.f7472d = obj;
    }

    public ApiExection(int i, Object obj, Exception exc) {
        this.f7471c = i;
        this.f7474f = exc;
        this.f7472d = obj;
    }

    public ApiExection(int i, Object obj, Throwable th) {
        this.f7471c = i;
        this.f7472d = obj;
        this.f7473e = th;
    }
}
