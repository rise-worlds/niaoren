package com.p018b.p019a.p020a.p023c;

/* renamed from: com.b.a.a.c.g */
/* loaded from: classes.dex */
public final class HttpMethod {
    /* renamed from: a */
    public static boolean m24735a(String str) {
        return str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    /* renamed from: b */
    public static boolean m24734b(String str) {
        return m24735a(str) || str.equals("OPTIONS") || str.equals("DELETE") || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK");
    }
}
