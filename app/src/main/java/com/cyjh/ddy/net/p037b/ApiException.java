package com.cyjh.ddy.net.p037b;

/* renamed from: com.cyjh.ddy.net.b.a */
/* loaded from: classes.dex */
public class ApiException extends Exception {

    /* renamed from: a */
    private int f7467a;

    /* renamed from: b */
    private String f7468b;

    public ApiException(Throwable th, int i) {
        super(th);
        this.f7467a = i;
    }

    public ApiException(int i, String str) {
        this.f7467a = i;
        this.f7468b = str;
    }

    /* renamed from: a */
    public int m21453a() {
        return this.f7467a;
    }

    /* renamed from: a */
    public void m21452a(int i) {
        this.f7467a = i;
    }

    /* renamed from: b */
    public String m21450b() {
        return this.f7468b;
    }

    /* renamed from: a */
    public void m21451a(String str) {
        this.f7468b = str;
    }
}
