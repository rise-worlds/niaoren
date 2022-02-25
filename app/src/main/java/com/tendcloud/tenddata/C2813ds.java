package com.tendcloud.tenddata;

import android.content.Context;
import android.os.SystemClock;
import com.github.kevinsawicki.http.HttpRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.TreeMap;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.protocol.HTTP;
import p110z1.Consts;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ds */
/* loaded from: classes2.dex */
public class C2813ds {

    /* renamed from: a */
    public static int f13806a = 60000;

    /* renamed from: b */
    public static int f13807b = 60000;

    /* renamed from: d */
    private static final int f13809d = 600;

    /* renamed from: e */
    private static final int f13810e = 60000;

    /* renamed from: f */
    private static final int f13811f = 60000;

    /* renamed from: c */
    public static volatile HashMap f13808c = new HashMap();

    /* renamed from: g */
    private static Context f13812g = null;

    /* renamed from: h */
    private static volatile HashMap f13813h = new HashMap();

    /* renamed from: a */
    private static void m15977a(HttpsURLConnection httpsURLConnection) {
    }

    /* renamed from: a */
    public static C2818e m15994a(Context context, String str, String str2, String str3, String str4, byte[] bArr, String str5) {
        return m15993a(context, str, str2, str3, str4, bArr, "default", str5);
    }

    /* renamed from: a */
    public static C2818e m15995a(Context context, AbstractC2790d dVar, String str, byte[] bArr, String str2) {
        f13812g = context;
        return m15985a(dVar.getHost(), "", str, dVar.getCert(), bArr, "default", dVar, str2, (String) null);
    }

    /* renamed from: a */
    public static C2818e m15993a(Context context, String str, String str2, String str3, String str4, byte[] bArr, String str5, String str6) {
        return m15992a(context, str, str2, str3, str4, bArr, str5, str6, (String) null);
    }

    /* renamed from: a */
    public static C2818e m15992a(Context context, String str, String str2, String str3, String str4, byte[] bArr, String str5, String str6, String str7) {
        f13812g = context;
        return m15985a(str, str2, str3, str4, bArr, str5, (AbstractC2790d) null, str6, str7);
    }

    /* renamed from: a */
    public static String m15982a(String str, boolean z) {
        return m15972b(str, (String) null, z);
    }

    /* renamed from: a */
    public static String m15984a(String str, String str2, boolean z) {
        return m15972b(str, str2, z);
    }

    /* renamed from: a */
    public static SSLSocketFactory m15976a(boolean z, X509Certificate x509Certificate) {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            if (!z || x509Certificate == null) {
                instance.init(null, null, null);
            } else {
                instance.init(null, new TrustManager[]{new C2817d(x509Certificate)}, null);
            }
            return instance.getSocketFactory();
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:29:0x009c
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: b */
    private static java.lang.String m15972b(java.lang.String r6, java.lang.String r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2813ds.m15972b(java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:12:0x002a
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private static byte[] m15991a(java.io.InputStream r7) {
        /*
            r0 = 0
            java.util.zip.GZIPInputStream r1 = new java.util.zip.GZIPInputStream     // Catch: Throwable -> 0x0038
            r1.<init>(r7)     // Catch: Throwable -> 0x0038
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch: Throwable -> 0x0032
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: Throwable -> 0x0032
            r2.<init>()     // Catch: Throwable -> 0x0032
        L_0x000f:
            int r3 = r7.length     // Catch: Throwable -> 0x002d
            r4 = 0
            int r3 = r1.read(r7, r4, r3)     // Catch: Throwable -> 0x002d
            r5 = -1
            if (r3 == r5) goto L_0x001c
            r2.write(r7, r4, r3)     // Catch: Throwable -> 0x002d
            goto L_0x000f
        L_0x001c:
            byte[] r0 = r2.toByteArray()     // Catch: Throwable -> 0x002d
            r2.flush()     // Catch: Throwable -> 0x002d
            r2.close()     // Catch: Throwable -> 0x0026
        L_0x0026:
            r1.close()     // Catch: Throwable -> 0x004b
            goto L_0x004b
        L_0x002a:
            r7 = move-exception
            r0 = r2
            goto L_0x004d
        L_0x002d:
            r7 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L_0x003b
        L_0x0032:
            r7 = move-exception
            r2 = r0
            goto L_0x003b
        L_0x0035:
            r7 = move-exception
            r1 = r0
            goto L_0x004d
        L_0x0038:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L_0x003b:
            com.tendcloud.tenddata.C2933hb.postSDKError(r7)     // Catch: all -> 0x004c
            if (r0 == 0) goto L_0x0045
            r0.close()     // Catch: Throwable -> 0x0044
            goto L_0x0045
        L_0x0044:
        L_0x0045:
            if (r1 == 0) goto L_0x004a
            r1.close()     // Catch: Throwable -> 0x004a
        L_0x004a:
            r0 = r2
        L_0x004b:
            return r0
        L_0x004c:
            r7 = move-exception
        L_0x004d:
            if (r0 == 0) goto L_0x0054
            r0.close()     // Catch: Throwable -> 0x0053
            goto L_0x0054
        L_0x0053:
        L_0x0054:
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch: Throwable -> 0x0059
        L_0x0059:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2813ds.m15991a(java.io.InputStream):byte[]");
    }

    /* renamed from: a */
    public static String m15989a(String str, File file) {
        return m15974b(str, (String) null, file);
    }

    /* renamed from: a */
    public static String m15987a(String str, String str2, File file) {
        return m15974b(str, str2, file);
    }

    /* renamed from: a */
    public static C2818e m15986a(String str, String str2, File file, String[] strArr) {
        return m15973b(str, str2, file, strArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0095, code lost:
        if (r1 != null) goto L_0x0097;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0097, code lost:
        r1.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a7, code lost:
        if (r1 == null) goto L_0x00aa;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00aa, code lost:
        return r0;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.tendcloud.tenddata.C2813ds.C2818e m15973b(java.lang.String r7, java.lang.String r8, java.io.File r9, java.lang.String[] r10) {
        /*
            com.tendcloud.tenddata.ds$e r0 = new com.tendcloud.tenddata.ds$e
            r1 = 600(0x258, float:8.41E-43)
            r0.<init>(r1)
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: Throwable -> 0x00a3
            r2.<init>(r7)     // Catch: Throwable -> 0x00a3
            r3 = 0
            java.net.URLConnection r4 = m15979a(r2, r1, r3, r1)     // Catch: Throwable -> 0x00a3
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: Throwable -> 0x00a3
            java.lang.String r7 = r7.toLowerCase()     // Catch: Throwable -> 0x009e
            java.lang.String r1 = "https"
            boolean r7 = r7.startsWith(r1)     // Catch: Throwable -> 0x009e
            if (r7 == 0) goto L_0x0041
            boolean r7 = com.tendcloud.tenddata.C2855es.m15791b(r8)     // Catch: Throwable -> 0x009e
            if (r7 != 0) goto L_0x0041
            java.util.HashMap r7 = com.tendcloud.tenddata.C2813ds.f13808c     // Catch: Throwable -> 0x009e
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: Throwable -> 0x009e
            long r5 = r1.getId()     // Catch: Throwable -> 0x009e
            java.lang.Long r1 = java.lang.Long.valueOf(r5)     // Catch: Throwable -> 0x009e
            java.lang.String r2 = r2.getHost()     // Catch: Throwable -> 0x009e
            r7.put(r1, r2)     // Catch: Throwable -> 0x009e
            javax.net.ssl.HttpsURLConnection r7 = m15978a(r4, r8)     // Catch: Throwable -> 0x009e
            r1 = r7
            goto L_0x0042
        L_0x0041:
            r1 = r4
        L_0x0042:
            int r7 = r1.getResponseCode()     // Catch: Throwable -> 0x00a3
            r0.statusCode = r7     // Catch: Throwable -> 0x00a3
            int r7 = r0.statusCode     // Catch: Throwable -> 0x00a3
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 != r8) goto L_0x0095
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: Throwable -> 0x00a3
            r7.<init>()     // Catch: Throwable -> 0x00a3
            int r8 = r10.length     // Catch: Throwable -> 0x00a3
            r2 = 0
        L_0x0055:
            if (r2 >= r8) goto L_0x0063
            r4 = r10[r2]     // Catch: Throwable -> 0x00a3
            java.lang.String r5 = r1.getHeaderField(r4)     // Catch: Throwable -> 0x00a3
            r7.put(r4, r5)     // Catch: Throwable -> 0x00a3
            int r2 = r2 + 1
            goto L_0x0055
        L_0x0063:
            java.lang.String r7 = r7.toString()     // Catch: Throwable -> 0x00a3
            r0.responseMsg = r7     // Catch: Throwable -> 0x00a3
            java.io.InputStream r7 = r1.getInputStream()     // Catch: Throwable -> 0x00a3
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch: Throwable -> 0x00a3
            r8.<init>(r9)     // Catch: Throwable -> 0x00a3
            r9 = 4096(0x1000, float:5.74E-42)
            byte[] r9 = new byte[r9]     // Catch: all -> 0x008d
        L_0x0076:
            int r10 = r7.read(r9)     // Catch: all -> 0x008d
            r2 = -1
            if (r10 == r2) goto L_0x0081
            r8.write(r9, r3, r10)     // Catch: all -> 0x008d
            goto L_0x0076
        L_0x0081:
            r8.close()     // Catch: Throwable -> 0x00a3
            r7.close()     // Catch: Throwable -> 0x00a3
            if (r1 == 0) goto L_0x008c
            r1.disconnect()     // Catch: Throwable -> 0x008c
        L_0x008c:
            return r0
        L_0x008d:
            r9 = move-exception
            r8.close()     // Catch: Throwable -> 0x00a3
            r7.close()     // Catch: Throwable -> 0x00a3
            throw r9     // Catch: Throwable -> 0x00a3
        L_0x0095:
            if (r1 == 0) goto L_0x00aa
        L_0x0097:
            r1.disconnect()     // Catch: Throwable -> 0x00aa
            goto L_0x00aa
        L_0x009b:
            r7 = move-exception
            r1 = r4
            goto L_0x00ab
        L_0x009e:
            r7 = move-exception
            r1 = r4
            goto L_0x00a4
        L_0x00a1:
            r7 = move-exception
            goto L_0x00ab
        L_0x00a3:
            r7 = move-exception
        L_0x00a4:
            com.tendcloud.tenddata.C2933hb.postSDKError(r7)     // Catch: all -> 0x00a1
            if (r1 == 0) goto L_0x00aa
            goto L_0x0097
        L_0x00aa:
            return r0
        L_0x00ab:
            if (r1 == 0) goto L_0x00b0
            r1.disconnect()     // Catch: Throwable -> 0x00b0
        L_0x00b0:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2813ds.m15973b(java.lang.String, java.lang.String, java.io.File, java.lang.String[]):com.tendcloud.tenddata.ds$e");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:40:0x0094
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: b */
    private static java.lang.String m15974b(java.lang.String r6, java.lang.String r7, java.io.File r8) {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: Throwable -> 0x00a1
            r1.<init>(r6)     // Catch: Throwable -> 0x00a1
            r2 = 0
            java.net.URLConnection r3 = m15979a(r1, r0, r2, r0)     // Catch: Throwable -> 0x00a1
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch: Throwable -> 0x00a1
            java.lang.String r6 = r6.toLowerCase()     // Catch: Throwable -> 0x009c
            java.lang.String r4 = "https"
            boolean r6 = r6.startsWith(r4)     // Catch: Throwable -> 0x009c
            if (r6 == 0) goto L_0x0039
            boolean r6 = com.tendcloud.tenddata.C2855es.m15791b(r7)     // Catch: Throwable -> 0x009c
            if (r6 != 0) goto L_0x0039
            java.util.HashMap r6 = com.tendcloud.tenddata.C2813ds.f13808c     // Catch: Throwable -> 0x009c
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch: Throwable -> 0x009c
            long r4 = r4.getId()     // Catch: Throwable -> 0x009c
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch: Throwable -> 0x009c
            java.lang.String r1 = r1.getHost()     // Catch: Throwable -> 0x009c
            r6.put(r4, r1)     // Catch: Throwable -> 0x009c
            javax.net.ssl.HttpsURLConnection r6 = m15978a(r3, r7)     // Catch: Throwable -> 0x009c
            goto L_0x003a
        L_0x0039:
            r6 = r3
        L_0x003a:
            int r7 = r6.getResponseCode()     // Catch: Throwable -> 0x0098
            r1 = 200(0xc8, float:2.8E-43)
            if (r7 != r1) goto L_0x008e
            java.lang.String r7 = "MD5"
            java.security.MessageDigest r7 = java.security.MessageDigest.getInstance(r7)     // Catch: Throwable -> 0x0098
            java.io.InputStream r1 = r6.getInputStream()     // Catch: all -> 0x007e
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: all -> 0x007b
            r3.<init>(r8)     // Catch: all -> 0x007b
            r8 = 4096(0x1000, float:5.74E-42)
            byte[] r8 = new byte[r8]     // Catch: all -> 0x0079
        L_0x0055:
            int r4 = r1.read(r8)     // Catch: all -> 0x0079
            r5 = -1
            if (r4 == r5) goto L_0x0063
            r3.write(r8, r2, r4)     // Catch: all -> 0x0079
            r7.update(r8, r2, r4)     // Catch: all -> 0x0079
            goto L_0x0055
        L_0x0063:
            r3.close()     // Catch: Throwable -> 0x0066
        L_0x0066:
            if (r1 == 0) goto L_0x006b
            r1.close()     // Catch: Throwable -> 0x006b
        L_0x006b:
            byte[] r7 = r7.digest()     // Catch: Throwable -> 0x0098
            java.lang.String r7 = com.tendcloud.tenddata.C2855es.m15798a(r7)     // Catch: Throwable -> 0x0098
            if (r6 == 0) goto L_0x0078
            r6.disconnect()     // Catch: Throwable -> 0x0078
        L_0x0078:
            return r7
        L_0x0079:
            r7 = move-exception
            goto L_0x0081
        L_0x007b:
            r7 = move-exception
            r3 = r0
            goto L_0x0081
        L_0x007e:
            r7 = move-exception
            r1 = r0
            r3 = r1
        L_0x0081:
            if (r3 == 0) goto L_0x0088
            r3.close()     // Catch: Throwable -> 0x0087
            goto L_0x0088
        L_0x0087:
        L_0x0088:
            if (r1 == 0) goto L_0x008d
            r1.close()     // Catch: Throwable -> 0x008d
        L_0x008d:
            throw r7     // Catch: Throwable -> 0x0098
        L_0x008e:
            if (r6 == 0) goto L_0x00ab
            r6.disconnect()     // Catch: Throwable -> 0x00ab
            goto L_0x00ab
        L_0x0094:
            r7 = move-exception
            r3 = r6
            r6 = r7
            goto L_0x00ad
        L_0x0098:
            r7 = move-exception
            r3 = r6
            r6 = r7
            goto L_0x00a3
        L_0x009c:
            r6 = move-exception
            goto L_0x00a3
        L_0x009e:
            r6 = move-exception
            r3 = r0
            goto L_0x00ad
        L_0x00a1:
            r6 = move-exception
            r3 = r0
        L_0x00a3:
            com.tendcloud.tenddata.C2933hb.postSDKError(r6)     // Catch: all -> 0x00ac
            if (r3 == 0) goto L_0x00ab
            r3.disconnect()     // Catch: Throwable -> 0x00ab
        L_0x00ab:
            return r0
        L_0x00ac:
            r6 = move-exception
        L_0x00ad:
            if (r3 == 0) goto L_0x00b2
            r3.disconnect()     // Catch: Throwable -> 0x00b2
        L_0x00b2:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2813ds.m15974b(java.lang.String, java.lang.String, java.io.File):java.lang.String");
    }

    /* renamed from: a */
    private static C2818e m15985a(String str, String str2, String str3, String str4, byte[] bArr, String str5, AbstractC2790d dVar, String str6, String str7) {
        C2818e eVar = new C2818e(f13809d);
        try {
            return m15983a(str3, str4, bArr, str, str5, dVar, str6, str7);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return eVar;
        }
    }

    /* renamed from: a */
    private static C2818e m15983a(String str, String str2, byte[] bArr, String str3, String str4, AbstractC2790d dVar, String str5, String str6) {
        try {
            if (str.startsWith("https://")) {
                f13808c.put(Long.valueOf(Thread.currentThread().getId()), str3);
            }
            URLConnection a = m15980a(new URL(str), str3, true, dVar, str4, str5, str6);
            if (a == null) {
                return new C2818e(f13809d, "");
            }
            if (str.toLowerCase().startsWith("https") && !str2.trim().isEmpty()) {
                a = m15978a(a, str2);
            }
            return m15975a(bArr, a, str4);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return new C2818e(f13809d, "");
        }
    }

    /* renamed from: a */
    private static URLConnection m15979a(URL url, String str, boolean z, String str2) {
        return m15971b(url, str, z, null, null, "", str2);
    }

    /* renamed from: a */
    private static URLConnection m15980a(URL url, String str, boolean z, AbstractC2790d dVar, String str2, String str3, String str4) {
        return m15971b(url, str, z, dVar, str2, str3, str4);
    }

    /* renamed from: b */
    private static URLConnection m15971b(URL url, String str, boolean z, AbstractC2790d dVar, String str2, String str3, String str4) {
        try {
            URLConnection openConnection = url.openConnection();
            openConnection.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, "");
            openConnection.setRequestProperty("User-Agent", "");
            if (str != null) {
                if (!C2855es.m15791b(url.getHost())) {
                    str = url.getHost();
                }
                openConnection.setRequestProperty(HTTP.TARGET_HOST, str);
                openConnection.setRequestProperty("Content-Type", "");
                openConnection.setRequestProperty("Content-Type", str3);
            }
            if (C2855es.m15807a(14) && C2855es.m15793b(19)) {
                openConnection.setRequestProperty(HTTP.CONN_DIRECTIVE, "close");
            }
            openConnection.setDoInput(true);
            if (z) {
                openConnection.setDoOutput(true);
            }
            openConnection.setConnectTimeout(f13806a);
            openConnection.setReadTimeout(f13807b);
            return openConnection;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: a */
    private static HttpsURLConnection m15978a(URLConnection uRLConnection, String str) {
        SSLContext sSLContext;
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
            if (C2855es.m15807a(16)) {
                sSLContext = SSLContext.getInstance("TLSv1.2");
            } else {
                sSLContext = SSLContext.getInstance("TLSv1");
            }
            sSLContext.init(null, new TrustManager[]{new C2817d(m15990a(str))}, null);
            httpsURLConnection.getHostnameVerifier();
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            return httpsURLConnection;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:24:0x0062
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private static com.tendcloud.tenddata.C2813ds.C2818e m15975a(byte[] r9, java.net.URLConnection r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2813ds.m15975a(byte[], java.net.URLConnection, java.lang.String):com.tendcloud.tenddata.ds$e");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0022, code lost:
        r5 = new java.io.BufferedReader(new java.io.InputStreamReader(r6));
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        r6 = r5.readLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        if (r6 == null) goto L_0x005e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
        r0.append(r6);
        r0.append('\n');
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r0.length() > 104857600) goto L_0x0043;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004a, code lost:
        throw new java.lang.RuntimeException("Input stream more than 100 MB size limit");
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004e, code lost:
        r6 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0064, code lost:
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0067, code lost:
        com.tendcloud.tenddata.C2933hb.postSDKError(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006a, code lost:
        if (r1 != null) goto L_0x006c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006c, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0074, code lost:
        if (r1 != null) goto L_0x0076;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0076, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0079, code lost:
        throw r6;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m15988a(java.lang.String r5, java.io.InputStream r6) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
            boolean r2 = com.tendcloud.tenddata.C2855es.m15791b(r5)     // Catch: Throwable -> 0x0066
            if (r2 != 0) goto L_0x005d
            r2 = -1
            int r3 = r5.hashCode()     // Catch: Throwable -> 0x0066
            r4 = 3189082(0x30a95a, float:4.468856E-39)
            if (r3 == r4) goto L_0x0017
            goto L_0x0020
        L_0x0017:
            java.lang.String r3 = "gzip"
            boolean r5 = r5.equals(r3)     // Catch: Throwable -> 0x0066
            if (r5 == 0) goto L_0x0020
            r2 = 0
        L_0x0020:
            if (r2 == 0) goto L_0x0051
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: Throwable -> 0x0066
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: Throwable -> 0x0066
            r2.<init>(r6)     // Catch: Throwable -> 0x0066
            r5.<init>(r2)     // Catch: Throwable -> 0x0066
        L_0x002c:
            java.lang.String r6 = r5.readLine()     // Catch: Throwable -> 0x004e
            if (r6 == 0) goto L_0x005e
            r0.append(r6)     // Catch: Throwable -> 0x004e
            r6 = 10
            r0.append(r6)     // Catch: Throwable -> 0x004e
            int r6 = r0.length()     // Catch: Throwable -> 0x004e
            r1 = 104857600(0x6400000, float:3.6111186E-35)
            if (r6 > r1) goto L_0x0043
            goto L_0x002c
        L_0x0043:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch: Throwable -> 0x004e
            java.lang.String r1 = "Input stream more than 100 MB size limit"
            r6.<init>(r1)     // Catch: Throwable -> 0x004e
            throw r6     // Catch: Throwable -> 0x004e
        L_0x004b:
            r6 = move-exception
            r1 = r5
            goto L_0x0074
        L_0x004e:
            r6 = move-exception
            r1 = r5
            goto L_0x0067
        L_0x0051:
            java.lang.String r5 = new java.lang.String     // Catch: Throwable -> 0x0066
            byte[] r6 = m15991a(r6)     // Catch: Throwable -> 0x0066
            r5.<init>(r6)     // Catch: Throwable -> 0x0066
            r0.append(r5)     // Catch: Throwable -> 0x0066
        L_0x005d:
            r5 = r1
        L_0x005e:
            if (r5 == 0) goto L_0x006f
            r5.close()     // Catch: Throwable -> 0x006f
            goto L_0x006f
        L_0x0064:
            r6 = move-exception
            goto L_0x0074
        L_0x0066:
            r6 = move-exception
        L_0x0067:
            com.tendcloud.tenddata.C2933hb.postSDKError(r6)     // Catch: all -> 0x0064
            if (r1 == 0) goto L_0x006f
            r1.close()     // Catch: Throwable -> 0x006f
        L_0x006f:
            java.lang.String r5 = r0.toString()
            return r5
        L_0x0074:
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch: Throwable -> 0x0079
        L_0x0079:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2813ds.m15988a(java.lang.String, java.io.InputStream):java.lang.String");
    }

    /* renamed from: a */
    private static void m15981a(HttpURLConnection httpURLConnection, long j, long j2, StringBuffer stringBuffer, int i) {
        boolean z;
        if (httpURLConnection != null) {
            try {
                TreeMap treeMap = new TreeMap();
                URL url = httpURLConnection.getURL();
                treeMap.put("targetUrl", url.toString());
                treeMap.put("targetIP", InetAddress.getByName(url.getHost()).getHostAddress());
                if (i == 200) {
                    treeMap.put("reqSize", Long.valueOf(j));
                    treeMap.put("respTime", Long.valueOf(SystemClock.elapsedRealtime() - j2));
                    z = true;
                } else {
                    treeMap.put("errorMsg", stringBuffer.toString());
                    treeMap.put("statusCode", Integer.valueOf(i));
                    z = false;
                }
                C2933hb.m15522a(z, treeMap);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    private static X509Certificate m15990a(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        try {
            try {
                X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream);
                try {
                    byteArrayInputStream.close();
                    return x509Certificate;
                } catch (Throwable unused) {
                    return x509Certificate;
                }
            } catch (Throwable unused2) {
                return null;
            }
        } catch (Exception unused3) {
            byteArrayInputStream.close();
            return null;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable unused4) {
            }
            throw th;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ds$e */
    /* loaded from: classes2.dex */
    public static class C2818e {
        public String responseMsg;
        public int statusCode;

        C2818e(int i, String str) {
            this.statusCode = i;
            this.responseMsg = str;
        }

        C2818e(int i) {
            this(i, "");
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public String getResponseMsg() {
            return this.responseMsg;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ds$d */
    /* loaded from: classes2.dex */
    public static class C2817d implements X509TrustManager {
        X509Certificate cert;

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        C2817d(X509Certificate x509Certificate) {
            this.cert = x509Certificate;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            String[] split;
            int indexOf;
            int length = x509CertificateArr.length;
            x509CertificateArr[0].getIssuerDN().equals(this.cert.getSubjectDN());
            try {
                String name = x509CertificateArr[0].getSubjectDN().getName();
                int indexOf2 = name.indexOf("CN=");
                if (indexOf2 >= 0 && (indexOf = (name = name.substring(indexOf2 + 3)).indexOf(",")) >= 0) {
                    name = name.substring(0, indexOf);
                }
                if (name.split("\\.").length >= 2) {
                    name = split[split.length - 2] + Consts.f23430h + split[split.length - 1];
                }
                if (!C2813ds.f13808c.containsKey(Long.valueOf(Thread.currentThread().getId()))) {
                    throw new CertificateException("No valid host provided!");
                } else if (((String) C2813ds.f13808c.get(Long.valueOf(Thread.currentThread().getId()))).endsWith(name)) {
                    x509CertificateArr[0].verify(this.cert.getPublicKey());
                    x509CertificateArr[0].checkValidity();
                } else {
                    throw new CertificateException("Server certificate has incorrect host name!");
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
                boolean z = th instanceof CertificateException;
            }
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ds$a */
    /* loaded from: classes2.dex */
    static class C2814a {
        String staticIp = null;
        String resolveIp = null;
        String savedIp = null;
        String successIp = null;
        String host = null;

        C2814a() {
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ds$b */
    /* loaded from: classes2.dex */
    static class C2815b {
        static final int resolvedIp = 1;
        static final int savedIp = 3;
        static final int staticIp = 4;
        static final int successIp = 2;

        C2815b() {
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ds$c */
    /* loaded from: classes2.dex */
    public class C2816c {
        public static final String EMPTY = "";
        public static final String FORM = "application/x-www-form-urlencoded";
        public static final String JSON = "application/json";
        public static final String UNIVERSAL_STREAM = "application/octet-stream";

        public C2816c() {
        }
    }
}
