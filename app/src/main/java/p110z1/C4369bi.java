package p110z1;

import java.util.Locale;

/* renamed from: z1.bi */
/* loaded from: classes3.dex */
public final class C4369bi {

    /* renamed from: a */
    private boolean f18786a;

    /* renamed from: b */
    private String f18787b = C5019co.m4503a(24);

    public C4369bi(boolean z) {
        this.f18786a = z;
    }

    /* renamed from: a */
    public C4409bj m9679a(C4330bh bhVar, boolean z, String str) {
        if (bhVar == null) {
            return null;
        }
        byte[] bytes = bhVar.m9691a().getBytes();
        byte[] bytes2 = bhVar.m9690b().getBytes();
        if (z) {
            try {
                bytes2 = C4044ba.m9795a(bytes2);
            } catch (Exception unused) {
                z = false;
            }
        }
        return new C4409bj(z, this.f18786a ? m9677a(bytes, m9681a(this.f18787b, C3876ar.f17428e), m9680a(this.f18787b, bytes2, str)) : m9677a(bytes, bytes2));
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public p110z1.C4330bh m9678a(p110z1.C4409bj r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: all -> 0x005c, Exception -> 0x005f
            byte[] r2 = r6.m9639b()     // Catch: all -> 0x005c, Exception -> 0x005f
            r1.<init>(r2)     // Catch: all -> 0x005c, Exception -> 0x005f
            r2 = 5
            byte[] r3 = new byte[r2]     // Catch: Exception -> 0x0059, all -> 0x0076
            r1.read(r3)     // Catch: Exception -> 0x0059, all -> 0x0076
            java.lang.String r4 = new java.lang.String     // Catch: Exception -> 0x0059, all -> 0x0076
            r4.<init>(r3)     // Catch: Exception -> 0x0059, all -> 0x0076
            int r3 = m9682a(r4)     // Catch: Exception -> 0x0059, all -> 0x0076
            byte[] r3 = new byte[r3]     // Catch: Exception -> 0x0059, all -> 0x0076
            r1.read(r3)     // Catch: Exception -> 0x0059, all -> 0x0076
            java.lang.String r4 = new java.lang.String     // Catch: Exception -> 0x0059, all -> 0x0076
            r4.<init>(r3)     // Catch: Exception -> 0x0059, all -> 0x0076
            byte[] r2 = new byte[r2]     // Catch: Exception -> 0x0057, all -> 0x0076
            r1.read(r2)     // Catch: Exception -> 0x0057, all -> 0x0076
            java.lang.String r3 = new java.lang.String     // Catch: Exception -> 0x0057, all -> 0x0076
            r3.<init>(r2)     // Catch: Exception -> 0x0057, all -> 0x0076
            int r2 = m9682a(r3)     // Catch: Exception -> 0x0057, all -> 0x0076
            if (r2 <= 0) goto L_0x0052
            byte[] r2 = new byte[r2]     // Catch: Exception -> 0x0057, all -> 0x0076
            r1.read(r2)     // Catch: Exception -> 0x0057, all -> 0x0076
            boolean r3 = r5.f18786a     // Catch: Exception -> 0x0057, all -> 0x0076
            if (r3 == 0) goto L_0x0042
            java.lang.String r3 = r5.f18787b     // Catch: Exception -> 0x0057, all -> 0x0076
            byte[] r2 = m9676b(r3, r2, r7)     // Catch: Exception -> 0x0057, all -> 0x0076
        L_0x0042:
            boolean r6 = r6.m9640a()     // Catch: Exception -> 0x0057, all -> 0x0076
            if (r6 == 0) goto L_0x004c
            byte[] r2 = p110z1.C4044ba.m9794b(r2)     // Catch: Exception -> 0x0057, all -> 0x0076
        L_0x004c:
            java.lang.String r6 = new java.lang.String     // Catch: Exception -> 0x0057, all -> 0x0076
            r6.<init>(r2)     // Catch: Exception -> 0x0057, all -> 0x0076
            goto L_0x0053
        L_0x0052:
            r6 = r0
        L_0x0053:
            r1.close()     // Catch: Exception -> 0x006b
            goto L_0x006b
        L_0x0057:
            r6 = move-exception
            goto L_0x0062
        L_0x0059:
            r6 = move-exception
            r4 = r0
            goto L_0x0062
        L_0x005c:
            r6 = move-exception
            r1 = r0
            goto L_0x0077
        L_0x005f:
            r6 = move-exception
            r1 = r0
            r4 = r1
        L_0x0062:
            p110z1.C4921cd.m5618a(r6)     // Catch: all -> 0x0076
            if (r1 == 0) goto L_0x006a
            r1.close()     // Catch: Exception -> 0x006a
        L_0x006a:
            r6 = r0
        L_0x006b:
            if (r4 != 0) goto L_0x0070
            if (r6 != 0) goto L_0x0070
            return r0
        L_0x0070:
            z1.bh r7 = new z1.bh
            r7.<init>(r4, r6)
            return r7
        L_0x0076:
            r6 = move-exception
        L_0x0077:
            if (r1 == 0) goto L_0x007c
            r1.close()     // Catch: Exception -> 0x007c
        L_0x007c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4369bi.m9678a(z1.bj, java.lang.String):z1.bh");
    }

    /* renamed from: a */
    private static byte[] m9681a(String str, String str2) {
        return C4130bc.m9759a(str, str2);
    }

    /* renamed from: a */
    private static byte[] m9680a(String str, byte[] bArr, String str2) {
        return C4187bd.m9733a(str, bArr, str2);
    }

    /* renamed from: b */
    private static byte[] m9676b(String str, byte[] bArr, String str2) {
        return C4187bd.m9731b(str, bArr, str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0051, code lost:
        if (r2 == null) goto L_0x0054;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] m9677a(byte[]... r7) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x0063
            int r1 = r7.length
            if (r1 != 0) goto L_0x0008
            goto L_0x0063
        L_0x0008:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: all -> 0x0040, Exception -> 0x0044
            r1.<init>()     // Catch: all -> 0x0040, Exception -> 0x0044
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch: all -> 0x003a, Exception -> 0x003d
            r2.<init>(r1)     // Catch: all -> 0x003a, Exception -> 0x003d
            int r3 = r7.length     // Catch: Exception -> 0x0038, all -> 0x0055
            r4 = 0
        L_0x0014:
            if (r4 >= r3) goto L_0x002a
            r5 = r7[r4]     // Catch: Exception -> 0x0038, all -> 0x0055
            int r6 = r5.length     // Catch: Exception -> 0x0038, all -> 0x0055
            java.lang.String r6 = m9683a(r6)     // Catch: Exception -> 0x0038, all -> 0x0055
            byte[] r6 = r6.getBytes()     // Catch: Exception -> 0x0038, all -> 0x0055
            r2.write(r6)     // Catch: Exception -> 0x0038, all -> 0x0055
            r2.write(r5)     // Catch: Exception -> 0x0038, all -> 0x0055
            int r4 = r4 + 1
            goto L_0x0014
        L_0x002a:
            r2.flush()     // Catch: Exception -> 0x0038, all -> 0x0055
            byte[] r0 = r1.toByteArray()     // Catch: Exception -> 0x0038, all -> 0x0055
            r1.close()     // Catch: Exception -> 0x0034
        L_0x0034:
            r2.close()     // Catch: Exception -> 0x0054
            goto L_0x0054
        L_0x0038:
            r7 = move-exception
            goto L_0x0047
        L_0x003a:
            r7 = move-exception
            r2 = r0
            goto L_0x0056
        L_0x003d:
            r7 = move-exception
            r2 = r0
            goto L_0x0047
        L_0x0040:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x0056
        L_0x0044:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L_0x0047:
            p110z1.C4921cd.m5618a(r7)     // Catch: all -> 0x0055
            if (r1 == 0) goto L_0x0051
            r1.close()     // Catch: Exception -> 0x0050
            goto L_0x0051
        L_0x0050:
        L_0x0051:
            if (r2 == 0) goto L_0x0054
            goto L_0x0034
        L_0x0054:
            return r0
        L_0x0055:
            r7 = move-exception
        L_0x0056:
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch: Exception -> 0x005c
            goto L_0x005d
        L_0x005c:
        L_0x005d:
            if (r2 == 0) goto L_0x0062
            r2.close()     // Catch: Exception -> 0x0062
        L_0x0062:
            throw r7
        L_0x0063:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4369bi.m9677a(byte[][]):byte[]");
    }

    /* renamed from: a */
    private static String m9683a(int i) {
        return String.format(Locale.getDefault(), "%05d", Integer.valueOf(i));
    }

    /* renamed from: a */
    private static int m9682a(String str) {
        return Integer.parseInt(str);
    }
}
