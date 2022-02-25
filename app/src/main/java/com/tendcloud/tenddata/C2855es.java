package com.tendcloud.tenddata;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.Inflater;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.tools.tar.TarConstants;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.es */
/* loaded from: classes2.dex */
public class C2855es {

    /* renamed from: a */
    static final boolean f13911a = false;

    /* renamed from: e */
    private static final String f13915e = "UTF-8";

    /* renamed from: f */
    private static final String f13916f = "ge";

    /* renamed from: g */
    private static final String f13917g = "tp";

    /* renamed from: h */
    private static final String f13918h = "rop";

    /* renamed from: j */
    private static final byte f13920j = 61;

    /* renamed from: k */
    private static final String f13921k = "US-ASCII";

    /* renamed from: d */
    static final /* synthetic */ boolean f13914d = !C2855es.class.desiredAssertionStatus();

    /* renamed from: b */
    public static String f13912b = C2664ab.f13525s;

    /* renamed from: c */
    public static boolean f13913c = false;

    /* renamed from: i */
    private static final ExecutorService f13919i = Executors.newSingleThreadExecutor();

    /* renamed from: l */
    private static final byte[] f13922l = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 43, 47};

    /* renamed from: m */
    private static byte[] f13923m = {1, 2, 3, 4, 5, 6, 7, 8};

    /* renamed from: n */
    private static final SecureRandom f13924n = new SecureRandom();

    /* renamed from: a */
    public static boolean m15806a(Context context) {
        return false;
    }

    public static void execute(Runnable runnable) {
        ExecutorService executorService = f13919i;
        if (executorService != null) {
            executorService.execute(runnable);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:12:0x0031
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    public static java.lang.String m15804a(android.content.Context r2, java.lang.String r3) {
        /*
            r0 = 0
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch: Throwable -> 0x003d
            java.io.InputStream r2 = r2.open(r3)     // Catch: Throwable -> 0x003d
            int r3 = r2.available()     // Catch: Throwable -> 0x0033
            byte[] r3 = new byte[r3]     // Catch: Throwable -> 0x0033
            int r1 = r2.read(r3)     // Catch: Throwable -> 0x0033
            if (r1 <= 0) goto L_0x002b
            java.lang.String r1 = new java.lang.String     // Catch: Throwable -> 0x0033
            r1.<init>(r3)     // Catch: Throwable -> 0x0033
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: Throwable -> 0x0033
            r3.<init>(r1)     // Catch: Throwable -> 0x0033
            java.lang.String r1 = "td_channel_id"
            java.lang.String r3 = r3.getString(r1)     // Catch: Throwable -> 0x0033
            if (r2 == 0) goto L_0x002a
            r2.close()     // Catch: Throwable -> 0x002a
        L_0x002a:
            return r3
        L_0x002b:
            if (r2 == 0) goto L_0x0041
        L_0x002d:
            r2.close()     // Catch: Throwable -> 0x0041
            goto L_0x0041
        L_0x0031:
            r3 = move-exception
            goto L_0x0037
        L_0x0033:
            goto L_0x003e
        L_0x0035:
            r3 = move-exception
            r2 = r0
        L_0x0037:
            if (r2 == 0) goto L_0x003c
            r2.close()     // Catch: Throwable -> 0x003c
        L_0x003c:
            throw r3
        L_0x003d:
            r2 = r0
        L_0x003e:
            if (r2 == 0) goto L_0x0041
            goto L_0x002d
        L_0x0041:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2855es.m15804a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static void m15801a(Object obj, AbstractC2847el elVar, String str, String str2) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            Class<?> cls = Class.forName(str2);
            declaredField.set(obj, Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[]{cls}, new C2856et(elVar, obj2)));
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static void m15802a(Class cls, AbstractC2847el elVar, String str, String str2) {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        Object obj = declaredField.get(null);
        Class<?> cls2 = Class.forName(str2);
        declaredField.set(null, Proxy.newProxyInstance(cls.getClass().getClassLoader(), new Class[]{cls2}, new C2857eu(elVar, obj)));
    }

    /* renamed from: a */
    public static final String m15800a(String str) {
        if (str == null) {
            return null;
        }
        return str.length() > 256 ? str.substring(0, 256) : str;
    }

    /* renamed from: b */
    public static final boolean m15791b(String str) {
        return str == null || str.trim().length() == 0;
    }

    /* renamed from: c */
    public static String m15786c(String str) {
        try {
            return m15798a(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static boolean m15792b(Context context, String str) {
        try {
            return m15807a(23) ? context.checkSelfPermission(str) == 0 : context.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m15807a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    /* renamed from: b */
    public static boolean m15793b(int i) {
        return Build.VERSION.SDK_INT < i;
    }

    /* renamed from: a */
    public static String m15798a(byte[] bArr) {
        try {
            StringBuilder sb = new StringBuilder();
            for (byte b : bArr) {
                int i = b & 255;
                if (i < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m15796a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        try {
            byte[] bArr3 = f13922l;
            int i4 = 0;
            int i5 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0);
            if (i2 > 2) {
                i4 = (bArr[i + 2] << 24) >>> 24;
            }
            int i6 = i5 | i4;
            switch (i2) {
                case 1:
                    bArr2[i3] = bArr3[i6 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i6 >>> 12) & 63];
                    bArr2[i3 + 2] = f13920j;
                    bArr2[i3 + 3] = f13920j;
                    return bArr2;
                case 2:
                    bArr2[i3] = bArr3[i6 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i6 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i6 >>> 6) & 63];
                    bArr2[i3 + 3] = f13920j;
                    return bArr2;
                case 3:
                    bArr2[i3] = bArr3[i6 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i6 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i6 >>> 6) & 63];
                    bArr2[i3 + 3] = bArr3[i6 & 63];
                    return bArr2;
                default:
                    return bArr2;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: b */
    public static String m15790b(byte[] bArr) {
        String str;
        try {
            str = m15797a(bArr, 0, bArr.length);
        } catch (Throwable th) {
            if (f13914d) {
                str = null;
            } else {
                throw new AssertionError(th.getMessage());
            }
        }
        if (f13914d || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public static String m15797a(byte[] bArr, int i, int i2) {
        byte[] b = m15789b(bArr, i, i2);
        try {
            return new String(b, "US-ASCII");
        } catch (Throwable unused) {
            return new String(b);
        }
    }

    /* renamed from: b */
    public static byte[] m15789b(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        } else if (i < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i);
        } else if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i2);
        } else if (i + i2 <= bArr.length) {
            int i3 = 4;
            int i4 = (i2 / 3) * 4;
            if (i2 % 3 <= 0) {
                i3 = 0;
            }
            byte[] bArr2 = new byte[i4 + i3];
            int i5 = i2 - 2;
            int i6 = 0;
            int i7 = 0;
            while (i6 < i5) {
                m15796a(bArr, i6 + i, 3, bArr2, i7);
                i6 += 3;
                i7 += 4;
            }
            if (i6 < i2) {
                m15796a(bArr, i + i6, i2 - i6, bArr2, i7);
                i7 += 4;
            }
            if (i7 > bArr2.length - 1) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i7];
            System.arraycopy(bArr2, 0, bArr3, 0, i7);
            return bArr3;
        } else {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)));
        }
    }

    /* renamed from: c */
    public static String m15787c(Context context, String str) {
        try {
            return m15803a(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData, str);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m15803a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            for (String str2 : bundle.keySet()) {
                if (str2.equalsIgnoreCase(str)) {
                    return String.valueOf(bundle.get(str));
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* renamed from: a */
    public static byte[] m15795a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(1, generateSecret, new IvParameterSpec(f13923m));
            return instance.doFinal(bArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m15788b(byte[] bArr, byte[] bArr2) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(2, generateSecret, new IvParameterSpec(f13923m));
            return instance.doFinal(bArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:15:0x0047
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    public static java.lang.String m15808a() {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: Throwable -> 0x0055
            java.lang.String r2 = "getprop"
            java.lang.Process r1 = r1.exec(r2)     // Catch: Throwable -> 0x0055
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: Throwable -> 0x0055
            java.io.InputStream r1 = r1.getInputStream()     // Catch: Throwable -> 0x0055
            java.lang.String r3 = "UTF-8"
            r2.<init>(r1, r3)     // Catch: Throwable -> 0x0055
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: Throwable -> 0x0055
            r1.<init>(r2)     // Catch: Throwable -> 0x0055
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0049
            r2.<init>()     // Catch: Throwable -> 0x0049
        L_0x0020:
            java.lang.String r3 = r1.readLine()     // Catch: Throwable -> 0x003f
            if (r3 == 0) goto L_0x003f
            r2.append(r3)     // Catch: Throwable -> 0x003f
            java.lang.String r3 = "\n"
            r2.append(r3)     // Catch: Throwable -> 0x003f
            int r3 = r2.length()     // Catch: Throwable -> 0x003f
            r4 = 104857600(0x6400000, float:3.6111186E-35)
            if (r3 > r4) goto L_0x0037
            goto L_0x0020
        L_0x0037:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch: Throwable -> 0x003f
            java.lang.String r4 = "Input stream more than 100 MB size limit"
            r3.<init>(r4)     // Catch: Throwable -> 0x003f
            throw r3     // Catch: Throwable -> 0x003f
        L_0x003f:
            java.lang.String r0 = r2.toString()     // Catch: Throwable -> 0x0049
            r1.close()     // Catch: Throwable -> 0x0046
        L_0x0046:
            return r0
        L_0x0047:
            r0 = move-exception
            goto L_0x004f
        L_0x0049:
            goto L_0x0056
        L_0x004b:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x004f:
            if (r1 == 0) goto L_0x0054
            r1.close()     // Catch: Throwable -> 0x0054
        L_0x0054:
            throw r0
        L_0x0055:
            r1 = r0
        L_0x0056:
            if (r1 == 0) goto L_0x005b
            r1.close()     // Catch: Throwable -> 0x005b
        L_0x005b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2855es.m15808a():java.lang.String");
    }

    /* renamed from: d */
    public static String m15783d(String str) {
        if (str == null) {
            return null;
        }
        try {
            return m15798a(MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8")));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: d */
    public static FileChannel m15784d(Context context, String str) {
        RandomAccessFile randomAccessFile;
        try {
            File file = new File(context.getFilesDir(), str + "td.lock");
            if (!file.exists()) {
                file.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                return randomAccessFile.getChannel();
            } catch (Throwable unused) {
                if (randomAccessFile == null) {
                    return null;
                }
                try {
                    randomAccessFile.close();
                    return null;
                } catch (Throwable unused2) {
                    return null;
                }
            }
        } catch (Throwable unused3) {
            randomAccessFile = null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:6:0x0020
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: e */
    public static byte[] m15782e(java.lang.String r4) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.util.zip.Deflater r1 = new java.util.zip.Deflater
            r2 = 9
            r3 = 1
            r1.<init>(r2, r3)
            r2 = 0
            java.util.zip.DeflaterOutputStream r3 = new java.util.zip.DeflaterOutputStream     // Catch: Throwable -> 0x002c
            r3.<init>(r0, r1)     // Catch: Throwable -> 0x002c
            java.lang.String r2 = "UTF-8"
            byte[] r4 = r4.getBytes(r2)     // Catch: Throwable -> 0x0022
            r3.write(r4)     // Catch: Throwable -> 0x0022
        L_0x001c:
            r3.close()     // Catch: Throwable -> 0x0030
            goto L_0x0030
        L_0x0020:
            r4 = move-exception
            goto L_0x0026
        L_0x0022:
            goto L_0x002d
        L_0x0024:
            r4 = move-exception
            r3 = r2
        L_0x0026:
            if (r3 == 0) goto L_0x002b
            r3.close()     // Catch: Throwable -> 0x002b
        L_0x002b:
            throw r4
        L_0x002c:
            r3 = r2
        L_0x002d:
            if (r3 == 0) goto L_0x0030
            goto L_0x001c
        L_0x0030:
            r1.end()
            byte[] r4 = r0.toByteArray()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2855es.m15782e(java.lang.String):byte[]");
    }

    /* renamed from: c */
    public static byte[] m15785c(byte[] bArr) {
        byte[] bArr2 = new byte[0];
        Inflater inflater = new Inflater();
        inflater.reset();
        inflater.setInput(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        try {
            byte[] bArr3 = new byte[1024];
            while (!inflater.finished()) {
                byteArrayOutputStream.write(bArr3, 0, inflater.inflate(bArr3));
            }
            bArr = byteArrayOutputStream.toByteArray();
        } catch (Throwable unused) {
        }
        try {
            byteArrayOutputStream.close();
        } catch (Throwable unused2) {
            inflater.end();
            return bArr;
        }
    }

    /* renamed from: b */
    public static SecureRandom m15794b() {
        return f13924n;
    }

    /* renamed from: f */
    public static Long m15781f(String str) {
        long j = -1L;
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return j;
        }
    }

    /* renamed from: g */
    public static Integer m15780g(String str) {
        int i = -1;
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return i;
        }
    }

    /* renamed from: a */
    public static Map m15799a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next));
            }
            return hashMap;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: h */
    public static boolean m15779h(String str) {
        return !m15791b(str) && C2842eg.f13863h.matcher(str).matches();
    }

    /* renamed from: a */
    public static int m15805a(Context context, int i) {
        try {
            return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5f);
        } catch (Throwable unused) {
            return 0;
        }
    }
}
