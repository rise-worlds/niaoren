package com.tendcloud.tenddata;

import android.content.SharedPreferences;
import java.io.File;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fe */
/* loaded from: classes2.dex */
final class C2869fe {

    /* renamed from: a */
    private static final String f13951a = "mPBE";

    /* renamed from: b */
    private static final String f13952b = "_Ladder_Project";

    /* renamed from: c */
    private static final String f13953c = "Pythagoras_phase";

    private C2869fe() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m15749a(String str, byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append((int) b);
            sb.append(",");
        }
        m15750a(C2664ab.f13513g.getFilesDir() + File.separator + f13951a, str, sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m15751a(String str, String str2) {
        m15750a(C2664ab.f13513g.getFilesDir() + File.separator + f13952b, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m15747b(String str, String str2) {
        m15750a(C2664ab.f13513g.getFilesDir().getPath(), str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static void m15745c(String str, String str2) {
        System.getProperties().setProperty(str, str2);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:18:0x003b
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private static void m15750a(java.lang.String r2, java.lang.String r3, java.lang.String r4) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: Throwable -> 0x0047
            r1.<init>(r2)     // Catch: Throwable -> 0x0047
            boolean r2 = r1.exists()     // Catch: Throwable -> 0x0047
            if (r2 != 0) goto L_0x0019
            boolean r2 = r1.isDirectory()     // Catch: Throwable -> 0x0047
            if (r2 != 0) goto L_0x0019
            boolean r2 = r1.mkdirs()     // Catch: Throwable -> 0x0047
            if (r2 != 0) goto L_0x0019
            return
        L_0x0019:
            java.io.File r2 = new java.io.File     // Catch: Throwable -> 0x0047
            r2.<init>(r1, r3)     // Catch: Throwable -> 0x0047
            boolean r3 = r2.exists()     // Catch: Throwable -> 0x0047
            if (r3 != 0) goto L_0x002b
            boolean r3 = r2.createNewFile()     // Catch: Throwable -> 0x0047
            if (r3 != 0) goto L_0x002b
            return
        L_0x002b:
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: Throwable -> 0x0047
            r3.<init>(r2)     // Catch: Throwable -> 0x0047
            byte[] r2 = r4.getBytes()     // Catch: Throwable -> 0x003e
            r3.write(r2)     // Catch: Throwable -> 0x003e
            r3.close()     // Catch: Throwable -> 0x004d
            goto L_0x004d
        L_0x003b:
            r2 = move-exception
            r0 = r3
            goto L_0x0041
        L_0x003e:
            r0 = r3
            goto L_0x0048
        L_0x0040:
            r2 = move-exception
        L_0x0041:
            if (r0 == 0) goto L_0x0046
            r0.close()     // Catch: Throwable -> 0x0046
        L_0x0046:
            throw r2
        L_0x0047:
        L_0x0048:
            if (r0 == 0) goto L_0x004d
            r0.close()     // Catch: Throwable -> 0x004d
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2869fe.m15750a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m15752a(String str, int i) {
        SharedPreferences sharedPreferences = C2664ab.f13513g.getSharedPreferences(f13951a, 0);
        String string = sharedPreferences.getString(str, "");
        String str2 = C2664ab.f13513g.getFilesDir() + File.separator + f13951a;
        String d = m15744d(str2, str);
        if (!C2855es.m15791b(d)) {
            return m15754a(i, d);
        }
        if (C2855es.m15791b(string)) {
            return null;
        }
        m15750a(str2, str, string);
        sharedPreferences.edit().putString(str, "").apply();
        return m15754a(i, string);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m15753a(String str) {
        SharedPreferences sharedPreferences = C2664ab.f13513g.getSharedPreferences(f13953c, 0);
        String string = sharedPreferences.getString(str, "");
        String str2 = C2664ab.f13513g.getFilesDir() + File.separator + f13952b;
        String d = m15744d(str2, str);
        if (!C2855es.m15791b(d) || C2855es.m15791b(string)) {
            return d;
        }
        m15750a(str2, str, string);
        sharedPreferences.edit().putString(str, "").apply();
        return string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m15748b(String str) {
        return m15744d(C2664ab.f13513g.getFilesDir().getPath(), str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static String m15746c(String str) {
        return System.getProperties().getProperty(str);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:17:0x003f
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: d */
    private static java.lang.String m15744d(java.lang.String r3, java.lang.String r4) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            java.lang.String r3 = ""
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x001a
            boolean r1 = r0.isDirectory()
            if (r1 != 0) goto L_0x001a
            boolean r1 = r0.mkdirs()
            if (r1 != 0) goto L_0x001a
            return r3
        L_0x001a:
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: Throwable -> 0x0059
            r2.<init>(r0, r4)     // Catch: Throwable -> 0x0059
            boolean r4 = r2.exists()     // Catch: Throwable -> 0x0059
            if (r4 == 0) goto L_0x0044
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: Throwable -> 0x0059
            r4.<init>(r2)     // Catch: Throwable -> 0x0059
            long r0 = r2.length()     // Catch: Throwable -> 0x0042
            int r0 = (int) r0     // Catch: Throwable -> 0x0042
            byte[] r0 = new byte[r0]     // Catch: Throwable -> 0x0042
            int r1 = r4.read(r0)     // Catch: Throwable -> 0x0042
            if (r1 <= 0) goto L_0x004c
            java.lang.String r1 = new java.lang.String     // Catch: Throwable -> 0x0042
            r1.<init>(r0)     // Catch: Throwable -> 0x0042
            r3 = r1
            goto L_0x004c
        L_0x003f:
            r3 = move-exception
            r1 = r4
            goto L_0x0053
        L_0x0042:
            r1 = r4
            goto L_0x005a
        L_0x0044:
            boolean r4 = r2.createNewFile()     // Catch: Throwable -> 0x0059
            if (r4 != 0) goto L_0x004b
            return r3
        L_0x004b:
            r4 = r1
        L_0x004c:
            if (r4 == 0) goto L_0x005f
            r4.close()     // Catch: Throwable -> 0x005f
            goto L_0x005f
        L_0x0052:
            r3 = move-exception
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()     // Catch: Throwable -> 0x0058
        L_0x0058:
            throw r3
        L_0x0059:
        L_0x005a:
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch: Throwable -> 0x005f
        L_0x005f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2869fe.m15744d(java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private static byte[] m15754a(int i, String str) {
        try {
            if (C2855es.m15791b(str)) {
                return null;
            }
            byte[] bArr = new byte[i];
            String[] split = str.split(",");
            for (int i2 = 0; i2 < split.length; i2++) {
                bArr[i2] = Byte.parseByte(split[i2]);
            }
            return bArr;
        } catch (Throwable unused) {
            return null;
        }
    }
}
