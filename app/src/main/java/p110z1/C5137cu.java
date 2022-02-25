package p110z1;

/* renamed from: z1.cu */
/* loaded from: classes3.dex */
public final class C5137cu {
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003c, code lost:
        if (r4 == null) goto L_0x003f;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m3515a(java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: all -> 0x0034, IOException -> 0x003b
            r2.<init>(r4, r5)     // Catch: all -> 0x0034, IOException -> 0x003b
            boolean r4 = r2.exists()     // Catch: all -> 0x0034, IOException -> 0x003b
            if (r4 != 0) goto L_0x0012
            return r1
        L_0x0012:
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: all -> 0x0034, IOException -> 0x003b
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: all -> 0x0034, IOException -> 0x003b
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: all -> 0x0034, IOException -> 0x003b
            r3.<init>(r2)     // Catch: all -> 0x0034, IOException -> 0x003b
            java.lang.String r2 = "UTF-8"
            r5.<init>(r3, r2)     // Catch: all -> 0x0034, IOException -> 0x003b
            r4.<init>(r5)     // Catch: all -> 0x0034, IOException -> 0x003b
        L_0x0023:
            java.lang.String r5 = r4.readLine()     // Catch: all -> 0x0031, IOException -> 0x003c
            if (r5 == 0) goto L_0x002d
            r0.append(r5)     // Catch: all -> 0x0031, IOException -> 0x003c
            goto L_0x0023
        L_0x002d:
            r4.close()     // Catch: Throwable -> 0x003f
            goto L_0x003f
        L_0x0031:
            r5 = move-exception
            r1 = r4
            goto L_0x0035
        L_0x0034:
            r5 = move-exception
        L_0x0035:
            if (r1 == 0) goto L_0x003a
            r1.close()     // Catch: Throwable -> 0x003a
        L_0x003a:
            throw r5
        L_0x003b:
            r4 = r1
        L_0x003c:
            if (r4 == 0) goto L_0x003f
            goto L_0x002d
        L_0x003f:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5137cu.m3515a(java.lang.String, java.lang.String):java.lang.String");
    }
}
