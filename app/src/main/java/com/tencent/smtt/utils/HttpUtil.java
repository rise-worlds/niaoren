package com.tencent.smtt.utils;

import android.os.Build;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import org.apache.http.protocol.HTTP;
import p110z1.C3894au;

/* renamed from: com.tencent.smtt.utils.g */
/* loaded from: classes2.dex */
public class HttpUtil {

    /* compiled from: HttpUtil.java */
    /* renamed from: com.tencent.smtt.utils.g$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2658a {
        /* renamed from: a */
        void mo16424a(int i);
    }

    /* renamed from: a */
    public static String m16428a(String str, byte[] bArr, AbstractC2658a aVar, boolean z) {
        String str2;
        try {
            if (z) {
                str2 = PostEncryption.m16415a().m16410c();
            } else {
                str2 = Post3DESEncryption.m16423a().m16420b();
            }
            String str3 = str + str2;
            try {
                if (z) {
                    bArr = PostEncryption.m16415a().m16413a(bArr);
                } else {
                    bArr = Post3DESEncryption.m16423a().m16422a(bArr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bArr == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/x-www-form-urlencoded");
            hashMap.put("Content-Length", String.valueOf(bArr.length));
            HttpURLConnection a = m16430a(str3, hashMap);
            if (a == null) {
                return null;
            }
            m16425b(a, bArr);
            return m16427a(a, aVar, z);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m16429a(String str, Map<String, String> map, byte[] bArr, AbstractC2658a aVar, boolean z) {
        HttpURLConnection a;
        if (bArr == null || (a = m16430a(str, map)) == null) {
            return null;
        }
        if (z) {
            m16426a(a, bArr);
        } else {
            m16425b(a, bArr);
        }
        return m16427a(a, aVar, false);
    }

    /* renamed from: a */
    private static HttpURLConnection m16430a(String str, Map<String, String> map) {
        Exception e;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        } catch (Exception e2) {
            e = e2;
        }
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(C3894au.f17525h);
            if (Build.VERSION.SDK_INT > 13) {
                httpURLConnection.setRequestProperty(HTTP.CONN_DIRECTIVE, "close");
            } else {
                httpURLConnection.setRequestProperty("http.keepAlive", "false");
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            return httpURLConnection;
        } catch (Exception e3) {
            e = e3;
            httpURLConnection2 = httpURLConnection;
            e.printStackTrace();
            return httpURLConnection2;
        }
    }

    /* renamed from: a */
    private static void m16426a(HttpURLConnection httpURLConnection, byte[] bArr) {
        Throwable th;
        GZIPOutputStream gZIPOutputStream;
        Exception e;
        try {
            gZIPOutputStream = new GZIPOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream(), 204800));
            try {
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.flush();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    m16431a(null);
                    m16431a(gZIPOutputStream);
                }
            } catch (Throwable th2) {
                th = th2;
                m16431a(null);
                m16431a(gZIPOutputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            gZIPOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream = null;
            m16431a(null);
            m16431a(gZIPOutputStream);
            throw th;
        }
        m16431a(null);
        m16431a(gZIPOutputStream);
    }

    /* renamed from: b */
    private static void m16425b(HttpURLConnection httpURLConnection, byte[] bArr) {
        OutputStream outputStream = null;
        try {
            try {
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            m16431a(outputStream);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:28:0x0074
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private static java.lang.String m16427a(java.net.HttpURLConnection r5, com.tencent.smtt.utils.HttpUtil.AbstractC2658a r6, boolean r7) {
        /*
            r0 = 0
            int r1 = r5.getResponseCode()     // Catch: Throwable -> 0x0096
            if (r6 == 0) goto L_0x000a
            r6.mo16424a(r1)     // Catch: Throwable -> 0x0096
        L_0x000a:
            r6 = 200(0xc8, float:2.8E-43)
            if (r1 != r6) goto L_0x008a
            java.io.InputStream r6 = r5.getInputStream()     // Catch: Throwable -> 0x0096
            java.lang.String r5 = r5.getContentEncoding()     // Catch: Throwable -> 0x0096
            if (r5 == 0) goto L_0x0026
            java.lang.String r1 = "gzip"
            boolean r1 = r5.equalsIgnoreCase(r1)     // Catch: Throwable -> 0x0096
            if (r1 == 0) goto L_0x0026
            java.util.zip.GZIPInputStream r5 = new java.util.zip.GZIPInputStream     // Catch: Throwable -> 0x0096
            r5.<init>(r6)     // Catch: Throwable -> 0x0096
            goto L_0x003d
        L_0x0026:
            if (r5 == 0) goto L_0x003c
            java.lang.String r1 = "deflate"
            boolean r5 = r5.equalsIgnoreCase(r1)     // Catch: Throwable -> 0x0096
            if (r5 == 0) goto L_0x003c
            java.util.zip.InflaterInputStream r5 = new java.util.zip.InflaterInputStream     // Catch: Throwable -> 0x0096
            java.util.zip.Inflater r1 = new java.util.zip.Inflater     // Catch: Throwable -> 0x0096
            r2 = 1
            r1.<init>(r2)     // Catch: Throwable -> 0x0096
            r5.<init>(r6, r1)     // Catch: Throwable -> 0x0096
            goto L_0x003d
        L_0x003c:
            r5 = r6
        L_0x003d:
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch: Throwable -> 0x0084
            r6.<init>()     // Catch: Throwable -> 0x0084
            r1 = 128(0x80, float:1.794E-43)
            byte[] r1 = new byte[r1]     // Catch: Throwable -> 0x0078
        L_0x0046:
            int r2 = r5.read(r1)     // Catch: Throwable -> 0x0078
            r3 = -1
            if (r2 == r3) goto L_0x0052
            r3 = 0
            r6.write(r1, r3, r2)     // Catch: Throwable -> 0x0078
            goto L_0x0046
        L_0x0052:
            if (r7 == 0) goto L_0x0061
            java.lang.String r7 = new java.lang.String     // Catch: Throwable -> 0x0078
            byte[] r1 = r6.toByteArray()     // Catch: Throwable -> 0x0078
            java.lang.String r2 = "utf-8"
            r7.<init>(r1, r2)     // Catch: Throwable -> 0x0078
            r0 = r7
            goto L_0x008c
        L_0x0061:
            java.lang.String r7 = new java.lang.String     // Catch: Throwable -> 0x0078
            com.tencent.smtt.utils.h r1 = com.tencent.smtt.utils.Post3DESEncryption.m16423a()     // Catch: Throwable -> 0x0078
            byte[] r2 = r6.toByteArray()     // Catch: Throwable -> 0x0078
            byte[] r1 = r1.m16416c(r2)     // Catch: Throwable -> 0x0078
            r7.<init>(r1)     // Catch: Throwable -> 0x0078
            r0 = r7
            goto L_0x008c
        L_0x0074:
            r7 = move-exception
            r0 = r5
            r5 = r7
            goto L_0x00a6
        L_0x0078:
            r7 = move-exception
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
            goto L_0x0099
        L_0x007e:
            r6 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
            goto L_0x00a6
        L_0x0084:
            r6 = move-exception
            r7 = r0
            r4 = r6
            r6 = r5
            r5 = r4
            goto L_0x0099
        L_0x008a:
            r5 = r0
            r6 = r5
        L_0x008c:
            m16431a(r5)
            m16431a(r6)
            goto L_0x00a2
        L_0x0093:
            r5 = move-exception
            r6 = r0
            goto L_0x00a6
        L_0x0096:
            r5 = move-exception
            r6 = r0
            r7 = r6
        L_0x0099:
            r5.printStackTrace()     // Catch: all -> 0x00a3
            m16431a(r6)
            m16431a(r7)
        L_0x00a2:
            return r0
        L_0x00a3:
            r5 = move-exception
            r0 = r6
            r6 = r7
        L_0x00a6:
            m16431a(r0)
            m16431a(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.HttpUtil.m16427a(java.net.HttpURLConnection, com.tencent.smtt.utils.g$a, boolean):java.lang.String");
    }

    /* renamed from: a */
    private static void m16431a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }
}
