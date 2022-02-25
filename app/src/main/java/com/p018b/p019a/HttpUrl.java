package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.tendcloud.tenddata.AbstractC2752bw;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.p105io.IOUtils;
import org.apache.http.HttpHost;
import org.slf4j.Marker;
import p110z1.Typography;

/* renamed from: com.b.a.ac */
/* loaded from: classes.dex */
public final class HttpUrl {

    /* renamed from: d */
    private static final char[] f6059d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    final String f6060a;

    /* renamed from: b */
    final String f6061b;

    /* renamed from: c */
    final int f6062c;

    /* renamed from: e */
    private final String f6063e;

    /* renamed from: f */
    private final String f6064f;

    /* renamed from: g */
    private final List<String> f6065g;

    /* renamed from: h */
    private final List<String> f6066h;

    /* renamed from: i */
    private final String f6067i;

    /* renamed from: j */
    private final String f6068j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m24547a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 'A') + 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpUrl(C0897ad adVar) {
        this.f6060a = adVar.f6069a;
        this.f6063e = m24541a(adVar.f6070b, false);
        this.f6064f = m24541a(adVar.f6071c, false);
        this.f6061b = adVar.f6072d;
        this.f6062c = adVar.m24523a();
        this.f6065g = m24539a(adVar.f6074f, false);
        String str = null;
        this.f6066h = adVar.f6075g != null ? m24539a(adVar.f6075g, true) : null;
        this.f6067i = adVar.f6076h != null ? m24541a(adVar.f6076h, false) : str;
        this.f6068j = adVar.toString();
    }

    /* renamed from: b */
    public final String m24538b() {
        return this.f6060a;
    }

    /* renamed from: c */
    public final boolean m24535c() {
        return this.f6060a.equals("https");
    }

    /* renamed from: d */
    public final String m24533d() {
        if (this.f6063e.isEmpty()) {
            return "";
        }
        int length = this.f6060a.length() + 3;
        String str = this.f6068j;
        return this.f6068j.substring(length, Util.m24757a(str, length, str.length(), ":@"));
    }

    /* renamed from: e */
    public final String m24531e() {
        if (this.f6064f.isEmpty()) {
            return "";
        }
        int indexOf = this.f6068j.indexOf(64);
        return this.f6068j.substring(this.f6068j.indexOf(58, this.f6060a.length() + 3) + 1, indexOf);
    }

    /* renamed from: f */
    public final String m24529f() {
        return this.f6061b;
    }

    /* renamed from: g */
    public final int m24528g() {
        return this.f6062c;
    }

    /* renamed from: a */
    public static int m24546a(String str) {
        if (str.equals(HttpHost.DEFAULT_SCHEME_NAME)) {
            return 80;
        }
        if (str.equals("https")) {
            return AbstractC2752bw.f13639b;
        }
        return -1;
    }

    /* renamed from: h */
    public final String m24527h() {
        int indexOf = this.f6068j.indexOf(47, this.f6060a.length() + 3);
        String str = this.f6068j;
        return this.f6068j.substring(indexOf, Util.m24757a(str, indexOf, str.length(), "?#"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m24540a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(list.get(i));
        }
    }

    /* renamed from: i */
    public final List<String> m24526i() {
        int indexOf = this.f6068j.indexOf(47, this.f6060a.length() + 3);
        String str = this.f6068j;
        int a = Util.m24757a(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < a) {
            int i = indexOf + 1;
            int a2 = Util.m24758a(this.f6068j, i, a, (char) IOUtils.DIR_SEPARATOR_UNIX);
            arrayList.add(this.f6068j.substring(i, a2));
            indexOf = a2;
        }
        return arrayList;
    }

    /* renamed from: j */
    public final String m24525j() {
        if (this.f6066h == null) {
            return null;
        }
        int indexOf = this.f6068j.indexOf(63) + 1;
        String str = this.f6068j;
        return this.f6068j.substring(indexOf, Util.m24758a(str, indexOf + 1, str.length(), '#'));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m24536b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = list.get(i);
            String str2 = list.get(i + 1);
            if (i > 0) {
                sb.append(Typography.f21051c);
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static List<String> m24537b(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i, indexOf));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i, indexOf2));
                arrayList.add(str.substring(indexOf2 + 1, indexOf));
            }
            i = indexOf + 1;
        }
        return arrayList;
    }

    /* renamed from: k */
    public final String m24524k() {
        if (this.f6066h == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        m24536b(sb, this.f6066h);
        return sb.toString();
    }

    /* renamed from: c */
    public final HttpUrl m24534c(String str) {
        C0897ad d = m24532d(str);
        if (d != null) {
            return d.m24519b();
        }
        return null;
    }

    /* renamed from: d */
    public final C0897ad m24532d(String str) {
        C0897ad adVar = new C0897ad();
        if (adVar.m24522a(this, str) == C0898ae.f6077a) {
            return adVar;
        }
        return null;
    }

    /* renamed from: e */
    public static HttpUrl m24530e(String str) {
        C0897ad adVar = new C0897ad();
        if (adVar.m24522a(null, str) == C0898ae.f6077a) {
            return adVar.m24519b();
        }
        return null;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof HttpUrl) && ((HttpUrl) obj).f6068j.equals(this.f6068j);
    }

    public final int hashCode() {
        return this.f6068j.hashCode();
    }

    public final String toString() {
        return this.f6068j;
    }

    /* renamed from: a */
    private static String m24541a(String str, boolean z) {
        return m24543a(str, 0, str.length(), z);
    }

    /* renamed from: a */
    private static List<String> m24539a(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            arrayList.add(str != null ? m24541a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m24543a(String str, int i, int i2, boolean z) {
        int i3;
        int i4 = i;
        while (i4 < i2) {
            char charAt = str.charAt(i4);
            if (charAt == '%' || (charAt == '+' && z)) {
                Buffer fVar = new Buffer();
                fVar.m24334a(str, i, i4);
                while (i4 < i2) {
                    int codePointAt = str.codePointAt(i4);
                    if (codePointAt != 37 || (i3 = i4 + 2) >= i2) {
                        if (codePointAt == 43 && z) {
                            fVar.mo24293h(32);
                            i4 += Character.charCount(codePointAt);
                        }
                        fVar.m24340a(codePointAt);
                        i4 += Character.charCount(codePointAt);
                    } else {
                        int a = m24547a(str.charAt(i4 + 1));
                        int a2 = m24547a(str.charAt(i3));
                        if (!(a == -1 || a2 == -1)) {
                            fVar.mo24293h((a << 4) + a2);
                            i4 = i3;
                            i4 += Character.charCount(codePointAt);
                        }
                        fVar.m24340a(codePointAt);
                        i4 += Character.charCount(codePointAt);
                    }
                }
                return fVar.m24319l();
            }
            i4++;
        }
        return str.substring(i, i2);
    }

    /* renamed from: a */
    private static boolean m24545a(String str, int i, int i2) {
        int i3 = i + 2;
        return i3 < i2 && str.charAt(i) == '%' && m24547a(str.charAt(i + 1)) != -1 && m24547a(str.charAt(i3)) != -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m24544a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || ((codePointAt == 37 && (!z || (z2 && !m24545a(str, i3, i2)))) || (codePointAt == 43 && z3)))) {
                Buffer fVar = new Buffer();
                fVar.m24334a(str, i, i3);
                Buffer fVar2 = null;
                while (i3 < i2) {
                    int codePointAt2 = str.codePointAt(i3);
                    if (!z || !(codePointAt2 == 9 || codePointAt2 == 10 || codePointAt2 == 12 || codePointAt2 == 13)) {
                        if (codePointAt2 == 43 && z3) {
                            fVar.mo24298b(z ? Marker.ANY_NON_NULL_MARKER : "%2B");
                        } else if (codePointAt2 < 32 || codePointAt2 == 127 || ((codePointAt2 >= 128 && z4) || str2.indexOf(codePointAt2) != -1 || (codePointAt2 == 37 && (!z || (z2 && !m24545a(str, i3, i2)))))) {
                            if (fVar2 == null) {
                                fVar2 = new Buffer();
                            }
                            fVar2.m24340a(codePointAt2);
                            while (!fVar2.mo24282d()) {
                                int e = fVar2.mo24281e() & 255;
                                fVar.mo24293h(37);
                                fVar.mo24293h((int) f6059d[(e >> 4) & 15]);
                                fVar.mo24293h((int) f6059d[e & 15]);
                            }
                        } else {
                            fVar.m24340a(codePointAt2);
                        }
                    }
                    i3 += Character.charCount(codePointAt2);
                }
                return fVar.m24319l();
            }
            i3 += Character.charCount(codePointAt);
        }
        return str.substring(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m24542a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return m24544a(str, 0, str.length(), str2, z, z2, z3, z4);
    }

    /* renamed from: a */
    public final URI m24548a() {
        C0897ad adVar = new C0897ad();
        adVar.f6069a = this.f6060a;
        adVar.f6070b = m24533d();
        adVar.f6071c = m24531e();
        adVar.f6072d = this.f6061b;
        adVar.f6073e = this.f6062c != m24546a(this.f6060a) ? this.f6062c : -1;
        adVar.f6074f.clear();
        adVar.f6074f.addAll(m24526i());
        adVar.m24518b(m24525j());
        adVar.f6076h = this.f6067i == null ? null : this.f6068j.substring(this.f6068j.indexOf(35) + 1);
        int size = adVar.f6074f.size();
        for (int i = 0; i < size; i++) {
            adVar.f6074f.set(i, m24542a(adVar.f6074f.get(i), "[]", true, true, false, true));
        }
        if (adVar.f6075g != null) {
            int size2 = adVar.f6075g.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String str = adVar.f6075g.get(i2);
                if (str != null) {
                    adVar.f6075g.set(i2, m24542a(str, "\\^`{|}", true, true, true, true));
                }
            }
        }
        if (adVar.f6076h != null) {
            adVar.f6076h = m24542a(adVar.f6076h, " \"#<>\\^`{|}", true, true, false, false);
        }
        String adVar2 = adVar.toString();
        try {
            return new URI(adVar2);
        } catch (URISyntaxException e) {
            try {
                return URI.create(adVar2.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e);
            }
        }
    }
}
