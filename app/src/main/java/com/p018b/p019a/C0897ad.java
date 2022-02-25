package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.tendcloud.tenddata.C2663aa;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import p110z1.Consts;

/* compiled from: HttpUrl.java */
/* renamed from: com.b.a.ad */
/* loaded from: classes.dex */
public final class C0897ad {

    /* renamed from: a */
    String f6069a;

    /* renamed from: d */
    String f6072d;

    /* renamed from: g */
    List<String> f6075g;

    /* renamed from: h */
    String f6076h;

    /* renamed from: b */
    String f6070b = "";

    /* renamed from: c */
    String f6071c = "";

    /* renamed from: e */
    int f6073e = -1;

    /* renamed from: f */
    final List<String> f6074f = new ArrayList();

    public C0897ad() {
        this.f6074f.add("");
    }

    /* renamed from: a */
    public final C0897ad m24521a(String str) {
        if (str != null) {
            String c = m24516c(str, 0, str.length());
            if (c != null) {
                this.f6072d = c;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }
        throw new NullPointerException("host == null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final int m24523a() {
        int i = this.f6073e;
        return i != -1 ? i : HttpUrl.m24546a(this.f6069a);
    }

    /* renamed from: b */
    public final C0897ad m24518b(String str) {
        this.f6075g = str != null ? HttpUrl.m24537b(HttpUrl.m24542a(str, " \"'<>#", true, false, true, true)) : null;
        return this;
    }

    /* renamed from: b */
    public final HttpUrl m24519b() {
        if (this.f6069a == null) {
            throw new IllegalStateException("scheme == null");
        } else if (this.f6072d != null) {
            return new HttpUrl(this);
        } else {
            throw new IllegalStateException("host == null");
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f6069a);
        sb.append(C2663aa.f13457a);
        if (!this.f6070b.isEmpty() || !this.f6071c.isEmpty()) {
            sb.append(this.f6070b);
            if (!this.f6071c.isEmpty()) {
                sb.append(':');
                sb.append(this.f6071c);
            }
            sb.append('@');
        }
        if (this.f6072d.indexOf(58) != -1) {
            sb.append('[');
            sb.append(this.f6072d);
            sb.append(']');
        } else {
            sb.append(this.f6072d);
        }
        int a = m24523a();
        if (a != HttpUrl.m24546a(this.f6069a)) {
            sb.append(':');
            sb.append(a);
        }
        HttpUrl.m24540a(sb, this.f6074f);
        if (this.f6075g != null) {
            sb.append('?');
            HttpUrl.m24536b(sb, this.f6075g);
        }
        if (this.f6076h != null) {
            sb.append('#');
            sb.append(this.f6076h);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0112 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x019b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00f8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01c9  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int m24522a(com.p018b.p019a.HttpUrl r22, java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 548
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p019a.C0897ad.m24522a(com.b.a.ac, java.lang.String):int");
    }

    /* renamed from: a */
    private void m24520a(String str, int i, int i2) {
        int i3;
        if (i != i2) {
            char charAt = str.charAt(i);
            if (charAt == '/' || charAt == '\\') {
                this.f6074f.clear();
                this.f6074f.add("");
                i3 = i + 1;
            } else {
                List<String> list = this.f6074f;
                list.set(list.size() - 1, "");
                i3 = i;
            }
            while (i3 < i2) {
                i3 = Util.m24757a(str, i3, i2, "/\\");
                boolean z = false;
                boolean z2 = i3 < i2;
                String a = HttpUrl.m24544a(str, i3, i3, " \"<>^`{}|/\\?#", true, false, false, true);
                if (!(a.equals(Consts.f23430h) || a.equalsIgnoreCase("%2e"))) {
                    if (a.equals("..") || a.equalsIgnoreCase("%2e.") || a.equalsIgnoreCase(".%2e") || a.equalsIgnoreCase("%2e%2e")) {
                        z = true;
                    }
                    if (z) {
                        List<String> list2 = this.f6074f;
                        if (!list2.remove(list2.size() - 1).isEmpty() || this.f6074f.isEmpty()) {
                            this.f6074f.add("");
                        } else {
                            List<String> list3 = this.f6074f;
                            list3.set(list3.size() - 1, "");
                        }
                    } else {
                        List<String> list4 = this.f6074f;
                        if (list4.get(list4.size() - 1).isEmpty()) {
                            List<String> list5 = this.f6074f;
                            list5.set(list5.size() - 1, a);
                        } else {
                            this.f6074f.add(a);
                        }
                        if (z2) {
                            this.f6074f.add("");
                        }
                    }
                }
                if (z2) {
                    i3++;
                }
            }
        }
    }

    /* renamed from: b */
    private static int m24517b(String str, int i, int i2) {
        while (i < i2) {
            char charAt = str.charAt(i);
            if (charAt == ':') {
                return i;
            }
            if (charAt == '[') {
                do {
                    i++;
                    if (i < i2) {
                    }
                } while (str.charAt(i) != ']');
            }
            i++;
        }
        return i2;
    }

    /* renamed from: c */
    private static String m24516c(String str, int i, int i2) {
        InetAddress inetAddress;
        int i3 = 0;
        String a = HttpUrl.m24543a(str, i, i2, false);
        if (!a.contains(":")) {
            return Util.m24760a(a);
        }
        if (!a.startsWith("[") || !a.endsWith("]")) {
            inetAddress = m24515d(a, 0, a.length());
        } else {
            inetAddress = m24515d(a, 1, a.length() - 1);
        }
        if (inetAddress == null) {
            return null;
        }
        byte[] address = inetAddress.getAddress();
        if (address.length == 16) {
            int i4 = 0;
            int i5 = -1;
            int i6 = 0;
            while (i4 < address.length) {
                int i7 = i4;
                while (i7 < 16 && address[i7] == 0 && address[i7 + 1] == 0) {
                    i7 += 2;
                }
                int i8 = i7 - i4;
                if (i8 > i6) {
                    i5 = i4;
                    i6 = i8;
                }
                i4 = i7 + 2;
            }
            Buffer fVar = new Buffer();
            while (i3 < address.length) {
                if (i3 == i5) {
                    fVar.mo24293h(58);
                    i3 += i6;
                    if (i3 == 16) {
                        fVar.mo24293h(58);
                    }
                } else {
                    if (i3 > 0) {
                        fVar.mo24293h(58);
                    }
                    fVar.mo24292i(((address[i3] & 255) << 8) | (address[i3 + 1] & 255));
                    i3 += 2;
                }
            }
            return fVar.m24319l();
        }
        throw new AssertionError();
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x00db, code lost:
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ae  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.net.InetAddress m24515d(java.lang.String r16, int r17, int r18) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p019a.C0897ad.m24515d(java.lang.String, int, int):java.net.InetAddress");
    }

    /* renamed from: e */
    private static int m24514e(String str, int i, int i2) {
        try {
            int parseInt = Integer.parseInt(HttpUrl.m24544a(str, i, i2, "", false, false, false, true));
            if (parseInt <= 0 || parseInt > 65535) {
                return -1;
            }
            return parseInt;
        } catch (NumberFormatException unused) {
            return -1;
        }
    }
}
