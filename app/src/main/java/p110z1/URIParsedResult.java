package p110z1;

import java.util.regex.Pattern;

/* renamed from: z1.gl */
/* loaded from: classes3.dex */
public final class URIParsedResult extends ParsedResult {

    /* renamed from: a */
    private static final Pattern f21783a = Pattern.compile(":/*([^/@]+)@[^/]+");

    /* renamed from: b */
    private final String f21784b;

    /* renamed from: c */
    private final String f21785c;

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (p110z1.ResultParser.m2588a(r3, r0, (r1 < 0 ? r3.length() : r1) - r0) != false) goto L_0x0026;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public URIParsedResult(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            int r0 = p110z1.ParsedResultType.f21881d
            r2.<init>(r0)
            java.lang.String r3 = r3.trim()
            r0 = 58
            int r0 = r3.indexOf(r0)
            if (r0 < 0) goto L_0x0026
            int r0 = r0 + 1
            r1 = 47
            int r1 = r3.indexOf(r1, r0)
            if (r1 >= 0) goto L_0x001f
            int r1 = r3.length()
        L_0x001f:
            int r1 = r1 - r0
            boolean r0 = p110z1.ResultParser.m2588a(r3, r0, r1)
            if (r0 == 0) goto L_0x0030
        L_0x0026:
            java.lang.String r0 = "http://"
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r3 = r0.concat(r3)
        L_0x0030:
            r2.f21784b = r3
            r2.f21785c = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.URIParsedResult.<init>(java.lang.String, java.lang.String):void");
    }

    /* renamed from: b */
    private String m2722b() {
        return this.f21784b;
    }

    /* renamed from: c */
    private String m2721c() {
        return this.f21785c;
    }

    /* renamed from: d */
    private boolean m2720d() {
        return f21783a.matcher(this.f21784b).find();
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        StringBuilder sb = new StringBuilder(30);
        m2597a(this.f21785c, sb);
        m2597a(this.f21784b, sb);
        return sb.toString();
    }

    /* renamed from: a */
    private static String m2724a(String str) {
        String trim = str.trim();
        int indexOf = trim.indexOf(58);
        if (indexOf >= 0) {
            int i = indexOf + 1;
            int indexOf2 = trim.indexOf(47, i);
            if (indexOf2 < 0) {
                indexOf2 = trim.length();
            }
            if (!ResultParser.m2588a(trim, i, indexOf2 - i)) {
                return trim;
            }
        }
        return "http://".concat(String.valueOf(trim));
    }

    /* renamed from: a */
    private static boolean m2723a(String str, int i) {
        int i2 = i + 1;
        int indexOf = str.indexOf(47, i2);
        if (indexOf < 0) {
            indexOf = str.length();
        }
        return ResultParser.m2588a(str, i2, indexOf - i2);
    }
}
