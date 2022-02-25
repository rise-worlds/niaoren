package com.p073ta.utdid2.p074a.p075a;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tools.tar.TarConstants;

/* renamed from: com.ta.utdid2.a.a.a */
/* loaded from: classes2.dex */
public class C2510a {
    /* renamed from: a */
    public static String m17187a(String str) {
        byte[] bArr;
        try {
            bArr = m17183a(m17188a(), str.getBytes());
        } catch (Exception unused) {
            bArr = null;
        }
        if (bArr != null) {
            return m17184a(bArr);
        }
        return null;
    }

    /* renamed from: b */
    public static String m17181b(String str) {
        try {
            return new String(m17180b(m17188a(), m17186a(str)));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m17188a() throws Exception {
        return C2518f.m17169a(new byte[]{33, TarConstants.LF_GNUTYPE_SPARSE, -50, -89, -84, -114, 80, 99, 10, 63, 22, -65, -11, 30, 101, -118});
    }

    /* renamed from: a */
    private static byte[] m17183a(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(m17182b()));
        return instance.doFinal(bArr2);
    }

    /* renamed from: b */
    private static byte[] m17180b(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(m17182b()));
        return instance.doFinal(bArr2);
    }

    /* renamed from: a */
    private static byte[] m17186a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    /* renamed from: a */
    private static String m17184a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            m17185a(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private static void m17185a(StringBuffer stringBuffer, byte b) {
        stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15));
        stringBuffer.append("0123456789ABCDEF".charAt(b & 15));
    }

    /* renamed from: b */
    private static byte[] m17182b() {
        try {
            byte[] decode = C2511b.decode("IUQSvE6r1TfFPdPEjfklLw==".getBytes("UTF-8"), 2);
            if (decode != null) {
                return C2518f.m17169a(decode);
            }
        } catch (Exception unused) {
        }
        return new byte[16];
    }
}
