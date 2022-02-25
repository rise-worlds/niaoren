package p110z1;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/* renamed from: z1.aim */
/* loaded from: classes3.dex */
public final class PayloadReader {
    private PayloadReader() {
    }

    /* renamed from: a */
    public static String m13045a(File file, int i) {
        byte[] b = m13043b(file, i);
        if (b == null) {
            return null;
        }
        try {
            return new String(b, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m13043b(File file, int i) {
        ByteBuffer byteBuffer;
        Map<Integer, ByteBuffer> a = m13046a(file);
        if (a == null || (byteBuffer = a.get(Integer.valueOf(i))) == null) {
            return null;
        }
        return m13044a(byteBuffer);
    }

    /* renamed from: a */
    private static byte[] m13044a(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, byteBuffer.position() + arrayOffset, arrayOffset + byteBuffer.limit());
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x003f, code lost:
        if (r1 == null) goto L_0x0042;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.Map<java.lang.Integer, java.nio.ByteBuffer> m13046a(java.io.File r3) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: all -> 0x002a, IOException -> 0x0038
            java.lang.String r2 = "r"
            r1.<init>(r3, r2)     // Catch: all -> 0x002a, IOException -> 0x0038
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch: all -> 0x0025, IOException -> 0x0028
            z1.ail r2 = p110z1.aih.m13055c(r3)     // Catch: all -> 0x0023, IOException -> 0x003a
            java.lang.Object r2 = r2.m13049a()     // Catch: all -> 0x0023, IOException -> 0x003a
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2     // Catch: all -> 0x0023, IOException -> 0x003a
            java.util.Map r0 = p110z1.aih.m13063a(r2)     // Catch: all -> 0x0023, IOException -> 0x003a
            if (r3 == 0) goto L_0x001f
            r3.close()     // Catch: IOException -> 0x001f, aio | IOException -> 0x0042
        L_0x001f:
            r1.close()     // Catch: aio | IOException -> 0x0042
            goto L_0x0042
        L_0x0023:
            r2 = move-exception
            goto L_0x002d
        L_0x0025:
            r2 = move-exception
            r3 = r0
            goto L_0x002d
        L_0x0028:
            r3 = r0
            goto L_0x003a
        L_0x002a:
            r2 = move-exception
            r3 = r0
            r1 = r3
        L_0x002d:
            if (r3 == 0) goto L_0x0032
            r3.close()     // Catch: IOException -> 0x0032
        L_0x0032:
            if (r1 == 0) goto L_0x0037
            r1.close()     // Catch: IOException -> 0x0037
        L_0x0037:
            throw r2     // Catch: aio | IOException -> 0x0042
        L_0x0038:
            r3 = r0
            r1 = r3
        L_0x003a:
            if (r3 == 0) goto L_0x003f
            r3.close()     // Catch: IOException -> 0x003f
        L_0x003f:
            if (r1 == 0) goto L_0x0042
            goto L_0x001f
        L_0x0042:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.PayloadReader.m13046a(java.io.File):java.util.Map");
    }
}
