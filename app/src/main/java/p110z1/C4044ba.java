package p110z1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/* renamed from: z1.ba */
/* loaded from: classes3.dex */
public class C4044ba {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m9795a(byte[] r6) throws java.io.IOException {
        /*
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: all -> 0x003e
            r1.<init>(r6)     // Catch: all -> 0x003e
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch: all -> 0x003b
            r6.<init>()     // Catch: all -> 0x003b
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: all -> 0x0036
            r2.<init>(r6)     // Catch: all -> 0x0036
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch: all -> 0x0034
        L_0x0014:
            int r3 = r1.read(r0)     // Catch: all -> 0x0034
            r4 = -1
            if (r3 == r4) goto L_0x0020
            r4 = 0
            r2.write(r0, r4, r3)     // Catch: all -> 0x0034
            goto L_0x0014
        L_0x0020:
            r2.flush()     // Catch: all -> 0x0034
            r2.finish()     // Catch: all -> 0x0034
            byte[] r0 = r6.toByteArray()     // Catch: all -> 0x0034
            r1.close()     // Catch: Exception -> 0x002d
        L_0x002d:
            r6.close()     // Catch: Exception -> 0x0030
        L_0x0030:
            r2.close()     // Catch: Exception -> 0x0033
        L_0x0033:
            return r0
        L_0x0034:
            r0 = move-exception
            goto L_0x0043
        L_0x0036:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L_0x0043
        L_0x003b:
            r6 = move-exception
            r2 = r0
            goto L_0x0041
        L_0x003e:
            r6 = move-exception
            r1 = r0
            r2 = r1
        L_0x0041:
            r0 = r6
            r6 = r2
        L_0x0043:
            if (r1 == 0) goto L_0x004a
            r1.close()     // Catch: Exception -> 0x0049
            goto L_0x004a
        L_0x0049:
        L_0x004a:
            if (r6 == 0) goto L_0x0051
            r6.close()     // Catch: Exception -> 0x0050
            goto L_0x0051
        L_0x0050:
        L_0x0051:
            if (r2 == 0) goto L_0x0056
            r2.close()     // Catch: Exception -> 0x0056
        L_0x0056:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4044ba.m9795a(byte[]):byte[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public static byte[] m9794b(byte[] bArr) throws IOException {
        GZIPInputStream gZIPInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    byte[] bArr2 = new byte[4096];
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = gZIPInputStream.read(bArr2, 0, bArr2.length);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, read);
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception unused) {
                            }
                            try {
                                gZIPInputStream.close();
                            } catch (Exception unused2) {
                            }
                            try {
                                byteArrayInputStream.close();
                            } catch (Exception unused3) {
                            }
                            throw th;
                        }
                    }
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception unused4) {
                    }
                    try {
                        gZIPInputStream.close();
                    } catch (Exception unused5) {
                    }
                    try {
                        byteArrayInputStream.close();
                    } catch (Exception unused6) {
                    }
                    return byteArray;
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream = 0;
                }
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream = 0;
                gZIPInputStream = byteArrayOutputStream;
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            byteArrayInputStream = null;
            byteArrayOutputStream = 0;
        }
    }
}
