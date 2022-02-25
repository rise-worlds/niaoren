package com.tendcloud.tenddata;

import java.util.Locale;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ek */
/* loaded from: classes2.dex */
public class C2846ek {

    /* renamed from: a */
    public static final int f13888a = -1;

    /* renamed from: b */
    public static final int f13889b = -2;

    /* renamed from: c */
    private final int f13890c;

    /* renamed from: d */
    private final String f13891d;

    public C2846ek(int i, String str) {
        this.f13890c = i;
        this.f13891d = str;
    }

    /* renamed from: a */
    public int m15822a() {
        return this.f13890c;
    }

    /* renamed from: b */
    public String m15821b() {
        return this.f13891d;
    }

    public String toString() {
        return String.format(Locale.getDefault(), "[Error %d] %s", Integer.valueOf(m15822a()), m15821b());
    }
}
