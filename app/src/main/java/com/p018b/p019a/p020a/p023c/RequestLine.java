package com.p018b.p019a.p020a.p023c;

import com.p018b.p019a.HttpUrl;

/* renamed from: com.b.a.a.c.j */
/* loaded from: classes.dex */
public final class RequestLine {
    /* renamed from: a */
    public static String m24730a(HttpUrl acVar) {
        String h = acVar.m24527h();
        String j = acVar.m24525j();
        if (j == null) {
            return h;
        }
        return h + '?' + j;
    }
}
