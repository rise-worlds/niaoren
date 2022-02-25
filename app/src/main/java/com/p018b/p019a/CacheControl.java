package com.p018b.p019a;

import com.github.kevinsawicki.http.HttpRequest;
import com.p018b.p019a.p020a.p023c.HttpHeaders;
import java.util.concurrent.TimeUnit;
import org.apache.http.cookie.ClientCookie;

/* renamed from: com.b.a.e */
/* loaded from: classes.dex */
public final class CacheControl {

    /* renamed from: a */
    public static final CacheControl f6212a;

    /* renamed from: b */
    public static final CacheControl f6213b;

    /* renamed from: c */
    String f6214c;

    /* renamed from: d */
    private final boolean f6215d;

    /* renamed from: e */
    private final boolean f6216e;

    /* renamed from: f */
    private final int f6217f;

    /* renamed from: g */
    private final int f6218g;

    /* renamed from: h */
    private final boolean f6219h;

    /* renamed from: i */
    private final boolean f6220i;

    /* renamed from: j */
    private final boolean f6221j;

    /* renamed from: k */
    private final int f6222k;

    /* renamed from: l */
    private final int f6223l;

    /* renamed from: m */
    private final boolean f6224m;

    /* renamed from: n */
    private final boolean f6225n;

    static {
        C0908f fVar = new C0908f();
        fVar.f6226a = true;
        f6212a = fVar.m24408a();
        C0908f fVar2 = new C0908f();
        fVar2.f6231f = true;
        long seconds = TimeUnit.SECONDS.toSeconds(2147483647L);
        fVar2.f6229d = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
        f6213b = fVar2.m24408a();
    }

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, String str) {
        this.f6215d = z;
        this.f6216e = z2;
        this.f6217f = i;
        this.f6218g = i2;
        this.f6219h = z3;
        this.f6220i = z4;
        this.f6221j = z5;
        this.f6222k = i3;
        this.f6223l = i4;
        this.f6224m = z6;
        this.f6225n = z7;
        this.f6214c = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CacheControl(C0908f fVar) {
        this.f6215d = fVar.f6226a;
        this.f6216e = fVar.f6227b;
        this.f6217f = fVar.f6228c;
        this.f6218g = -1;
        this.f6219h = false;
        this.f6220i = false;
        this.f6221j = false;
        this.f6222k = fVar.f6229d;
        this.f6223l = fVar.f6230e;
        this.f6224m = fVar.f6231f;
        this.f6225n = fVar.f6232g;
    }

    /* renamed from: a */
    public final boolean m24418a() {
        return this.f6215d;
    }

    /* renamed from: b */
    public final boolean m24416b() {
        return this.f6216e;
    }

    /* renamed from: c */
    public final int m24415c() {
        return this.f6217f;
    }

    /* renamed from: d */
    public final boolean m24414d() {
        return this.f6219h;
    }

    /* renamed from: e */
    public final boolean m24413e() {
        return this.f6220i;
    }

    /* renamed from: f */
    public final boolean m24412f() {
        return this.f6221j;
    }

    /* renamed from: g */
    public final int m24411g() {
        return this.f6222k;
    }

    /* renamed from: h */
    public final int m24410h() {
        return this.f6223l;
    }

    /* renamed from: i */
    public final boolean m24409i() {
        return this.f6224m;
    }

    /* renamed from: a */
    public static CacheControl m24417a(Headers aaVar) {
        int i;
        String str;
        Headers aaVar2 = aaVar;
        int a = aaVar.m24559a();
        int i2 = 0;
        boolean z = true;
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = -1;
        int i4 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i5 = -1;
        int i6 = -1;
        boolean z7 = false;
        boolean z8 = false;
        while (i2 < a) {
            String a2 = aaVar2.m24558a(i2);
            String b = aaVar2.m24555b(i2);
            if (a2.equalsIgnoreCase(HttpRequest.HEADER_CACHE_CONTROL)) {
                if (str2 != null) {
                    z = false;
                } else {
                    str2 = b;
                }
            } else if (a2.equalsIgnoreCase("Pragma")) {
                z = false;
            } else {
                i2++;
                aaVar2 = aaVar;
            }
            int i7 = 0;
            while (i7 < b.length()) {
                int a3 = HttpHeaders.m24738a(b, i7, "=,;");
                String trim = b.substring(i7, a3).trim();
                if (a3 == b.length() || b.charAt(a3) == ',' || b.charAt(a3) == ';') {
                    i = a3 + 1;
                    str = null;
                } else {
                    int a4 = HttpHeaders.m24739a(b, a3 + 1);
                    if (a4 >= b.length() || b.charAt(a4) != '\"') {
                        i = HttpHeaders.m24738a(b, a4, ",;");
                        str = b.substring(a4, i).trim();
                    } else {
                        int i8 = a4 + 1;
                        int a5 = HttpHeaders.m24738a(b, i8, "\"");
                        str = b.substring(i8, a5);
                        i = a5 + 1;
                    }
                }
                if ("no-cache".equalsIgnoreCase(trim)) {
                    i7 = i;
                    z2 = true;
                } else if ("no-store".equalsIgnoreCase(trim)) {
                    i7 = i;
                    z3 = true;
                } else if (ClientCookie.MAX_AGE_ATTR.equalsIgnoreCase(trim)) {
                    i3 = HttpHeaders.m24736b(str, -1);
                    i7 = i;
                } else if ("s-maxage".equalsIgnoreCase(trim)) {
                    i4 = HttpHeaders.m24736b(str, -1);
                    i7 = i;
                } else if ("private".equalsIgnoreCase(trim)) {
                    i7 = i;
                    z4 = true;
                } else if ("public".equalsIgnoreCase(trim)) {
                    i7 = i;
                    z5 = true;
                } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                    i7 = i;
                    z6 = true;
                } else if ("max-stale".equalsIgnoreCase(trim)) {
                    i5 = HttpHeaders.m24736b(str, Integer.MAX_VALUE);
                    i7 = i;
                } else if ("min-fresh".equalsIgnoreCase(trim)) {
                    i6 = HttpHeaders.m24736b(str, -1);
                    i7 = i;
                } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                    i7 = i;
                    z7 = true;
                } else {
                    if ("no-transform".equalsIgnoreCase(trim)) {
                        z8 = true;
                    }
                    i7 = i;
                }
            }
            i2++;
            aaVar2 = aaVar;
        }
        return new CacheControl(z2, z3, i3, i4, z4, z5, z6, i5, i6, z7, z8, !z ? null : str2);
    }

    public final String toString() {
        String str;
        String str2 = this.f6214c;
        if (str2 != null) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        if (this.f6215d) {
            sb.append("no-cache, ");
        }
        if (this.f6216e) {
            sb.append("no-store, ");
        }
        if (this.f6217f != -1) {
            sb.append("max-age=");
            sb.append(this.f6217f);
            sb.append(", ");
        }
        if (this.f6218g != -1) {
            sb.append("s-maxage=");
            sb.append(this.f6218g);
            sb.append(", ");
        }
        if (this.f6219h) {
            sb.append("private, ");
        }
        if (this.f6220i) {
            sb.append("public, ");
        }
        if (this.f6221j) {
            sb.append("must-revalidate, ");
        }
        if (this.f6222k != -1) {
            sb.append("max-stale=");
            sb.append(this.f6222k);
            sb.append(", ");
        }
        if (this.f6223l != -1) {
            sb.append("min-fresh=");
            sb.append(this.f6223l);
            sb.append(", ");
        }
        if (this.f6224m) {
            sb.append("only-if-cached, ");
        }
        if (this.f6225n) {
            sb.append("no-transform, ");
        }
        if (sb.length() == 0) {
            str = "";
        } else {
            sb.delete(sb.length() - 2, sb.length());
            str = sb.toString();
        }
        this.f6214c = str;
        return str;
    }
}
