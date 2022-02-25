package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.cl */
/* loaded from: classes2.dex */
public class C2774cl extends Exception {

    /* renamed from: a */
    private static final long f13685a = 3731842424390998726L;

    /* renamed from: b */
    private int f13686b;

    public C2774cl(int i) {
        this.f13686b = i;
    }

    public C2774cl(int i, String str) {
        super(str);
        this.f13686b = i;
    }

    public C2774cl(int i, Throwable th) {
        super(th);
        this.f13686b = i;
    }

    public C2774cl(int i, String str, Throwable th) {
        super(str, th);
        this.f13686b = i;
    }

    /* renamed from: a */
    public int m16144a() {
        return this.f13686b;
    }
}
