package com.p018b.p019a.p020a.p023c;

import com.p018b.p019a.Cookie;
import com.p018b.p019a.CookieJar;
import com.p018b.p019a.Headers;
import com.p018b.p019a.HttpUrl;
import com.p018b.p019a.Response;
import java.util.regex.Pattern;
import org.apache.http.protocol.HTTP;

/* renamed from: com.b.a.a.c.f */
/* loaded from: classes.dex */
public final class HttpHeaders {

    /* renamed from: a */
    private static final Pattern f5798a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    /* renamed from: a */
    public static long m24742a(Response asVar) {
        return m24743a(asVar.m24450d());
    }

    /* renamed from: a */
    public static long m24743a(Headers aaVar) {
        return m24740a(aaVar.m24557a("Content-Length"));
    }

    /* renamed from: a */
    private static long m24740a(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    /* renamed from: a */
    public static void m24741a(CookieJar sVar, HttpUrl acVar, Headers aaVar) {
        if (sVar != CookieJar.f6389a && !Cookie.m24382a(acVar, aaVar).isEmpty()) {
            sVar.mo24378a();
        }
    }

    /* renamed from: b */
    public static boolean m24737b(Response asVar) {
        if (asVar.m24454a().m24469b().equals("HEAD")) {
            return false;
        }
        int b = asVar.m24452b();
        return (((b >= 100 && b < 200) || b == 204 || b == 304) && m24743a(asVar.m24450d()) == -1 && !HTTP.CHUNK_CODING.equalsIgnoreCase(asVar.m24453a(HTTP.TRANSFER_ENCODING))) ? false : true;
    }

    /* renamed from: a */
    public static int m24738a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    /* renamed from: a */
    public static int m24739a(String str, int i) {
        char charAt;
        while (i < str.length() && ((charAt = str.charAt(i)) == ' ' || charAt == '\t')) {
            i++;
        }
        return i;
    }

    /* renamed from: b */
    public static int m24736b(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException unused) {
            return i;
        }
    }
}
