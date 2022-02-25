package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* renamed from: com.b.a.p */
/* loaded from: classes.dex */
public final class ConnectionSpec {

    /* renamed from: a */
    public static final ConnectionSpec f6364a;

    /* renamed from: b */
    public static final ConnectionSpec f6365b;

    /* renamed from: c */
    public static final ConnectionSpec f6366c;

    /* renamed from: h */
    private static final CipherSuite[] f6367h = {CipherSuite.f6290aW, CipherSuite.f6321ba, CipherSuite.f6291aX, CipherSuite.f6322bb, CipherSuite.f6328bh, CipherSuite.f6327bg, CipherSuite.f6317ax, CipherSuite.f6275aH, CipherSuite.f6318ay, CipherSuite.f6276aI, CipherSuite.f6299af, CipherSuite.f6300ag, CipherSuite.f6244D, CipherSuite.f6248H, CipherSuite.f6335h};

    /* renamed from: d */
    final boolean f6368d;

    /* renamed from: e */
    final boolean f6369e;

    /* renamed from: f */
    final String[] f6370f;

    /* renamed from: g */
    final String[] f6371g;

    static {
        C0912q qVar = new C0912q(true);
        CipherSuite[] lVarArr = f6367h;
        if (qVar.f6372a) {
            String[] strArr = new String[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                strArr[i] = lVarArr[i].f6354bi;
            }
            f6364a = qVar.m24387a(strArr).m24388a(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).m24389a().m24386b();
            f6365b = new C0912q(f6364a).m24388a(TlsVersion.TLS_1_0).m24389a().m24386b();
            f6366c = new C0912q(false).m24386b();
            return;
        }
        throw new IllegalStateException("no cipher suites for cleartext connections");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectionSpec(C0912q qVar) {
        this.f6368d = qVar.f6372a;
        this.f6370f = qVar.f6373b;
        this.f6371g = qVar.f6374c;
        this.f6369e = qVar.f6375d;
    }

    /* renamed from: b */
    private List<TlsVersion> m24390b() {
        String[] strArr = this.f6371g;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : this.f6371g) {
            arrayList.add(TlsVersion.m24420a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* renamed from: a */
    public final boolean m24393a() {
        return this.f6369e;
    }

    /* renamed from: a */
    public final boolean m24392a(SSLSocket sSLSocket) {
        if (!this.f6368d) {
            return false;
        }
        String[] strArr = this.f6371g;
        if (strArr != null && !m24391a(strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.f6370f;
        return strArr2 == null || m24391a(strArr2, sSLSocket.getEnabledCipherSuites());
    }

    /* renamed from: a */
    private static boolean m24391a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length == 0 || strArr2.length == 0) {
            return false;
        }
        for (String str : strArr) {
            if (Util.m24751a(strArr2, str) != -1) {
                return true;
            }
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ConnectionSpec)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ConnectionSpec pVar = (ConnectionSpec) obj;
        boolean z = this.f6368d;
        if (z != pVar.f6368d) {
            return false;
        }
        return !z || (Arrays.equals(this.f6370f, pVar.f6370f) && Arrays.equals(this.f6371g, pVar.f6371g) && this.f6369e == pVar.f6369e);
    }

    public final int hashCode() {
        if (this.f6368d) {
            return ((((Arrays.hashCode(this.f6370f) + 527) * 31) + Arrays.hashCode(this.f6371g)) * 31) + (!this.f6369e ? 1 : 0);
        }
        return 17;
    }

    public final String toString() {
        String str;
        List list;
        if (!this.f6368d) {
            return "ConnectionSpec()";
        }
        String[] strArr = this.f6370f;
        if (strArr != null) {
            if (strArr == null) {
                list = null;
            } else {
                ArrayList arrayList = new ArrayList(strArr.length);
                for (String str2 : this.f6370f) {
                    arrayList.add(CipherSuite.m24400a(str2));
                }
                list = Collections.unmodifiableList(arrayList);
            }
            str = list.toString();
        } else {
            str = "[all enabled]";
        }
        return "ConnectionSpec(cipherSuites=" + str + ", tlsVersions=" + (this.f6371g != null ? m24390b().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.f6369e + ")";
    }
}
