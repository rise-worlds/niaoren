package com.p018b.p019a;

import java.util.ArrayList;
import java.util.List;

/* compiled from: FormBody.java */
/* renamed from: com.b.a.y */
/* loaded from: classes.dex */
public final class C0915y {

    /* renamed from: a */
    private final List<String> f6401a = new ArrayList();

    /* renamed from: b */
    private final List<String> f6402b = new ArrayList();

    /* renamed from: a */
    public final C0915y m24361a(String str, String str2) {
        this.f6401a.add(HttpUrl.m24542a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        this.f6402b.add(HttpUrl.m24542a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        return this;
    }

    /* renamed from: a */
    public final FormBody m24362a() {
        return new FormBody(this.f6401a, this.f6402b);
    }
}
