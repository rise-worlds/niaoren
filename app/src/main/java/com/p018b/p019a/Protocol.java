package com.p018b.p019a;

import java.io.IOException;

/* renamed from: com.b.a.al */
/* loaded from: classes.dex */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");
    

    /* renamed from: e */
    private final String f6148e;

    Protocol(String str) {
        this.f6148e = str;
    }

    /* renamed from: a */
    public static Protocol m24477a(String str) {
        if (str.equals(HTTP_1_0.f6148e)) {
            return HTTP_1_0;
        }
        if (str.equals(HTTP_1_1.f6148e)) {
            return HTTP_1_1;
        }
        if (str.equals(HTTP_2.f6148e)) {
            return HTTP_2;
        }
        if (str.equals(SPDY_3.f6148e)) {
            return SPDY_3;
        }
        throw new IOException("Unexpected protocol: " + str);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f6148e;
    }
}
