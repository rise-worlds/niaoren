package com.cyjh.ddy.p032a;

import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.mail.EmailConstants;
import org.apache.tools.tar.TarConstants;

/* compiled from: EncryptUtils.java */
/* renamed from: com.cyjh.ddy.a.a */
/* loaded from: classes.dex */
public class C1118a {

    /* renamed from: a */
    static AlgorithmParameterSpec f7039a;

    /* renamed from: b */
    private static Key f7040b;

    /* renamed from: c */
    private static final byte[] f7041c = {66, 28, 59, 34, TarConstants.LF_GNUTYPE_SPARSE, 16, 116, 12};

    /* renamed from: d */
    private static final byte[] f7042d = {85, 60, 5, 26, 121, 68, 19, 73};

    /* renamed from: e */
    private static final String[] f7043e = {"3949729b-9a58-4ffc-b720-770f67212d1c", "2d6aa33c-9638-42ab-87fc-78ae6d8f923c", "9628a2e0-9bda-4c0b-b2fd-902063900474", "0b2ce052-5930-42bc-a2d2-7e0e20aeeb35", "26c0da7d-9fe5-4236-8936-f0229dc63dc4", "db36880a-a810-46eb-9c73-603f1e6ce686", "626dfc8e-7d53-4532-a85d-6ef09882a49d", "c4de589e-a409-46fe-bbee-60e0d3e5d7dd"};

    /* renamed from: f */
    private static final char[] f7044f = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: a */
    public static String m21981a(String str) throws Exception {
        return m21979a(str, f7041c, f7042d);
    }

    /* renamed from: a */
    public static String m21979a(String str, byte[] bArr, byte[] bArr2) throws Exception {
        m21976a(bArr, bArr2);
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(1, f7040b, f7039a);
        return Base64.encodeToString(instance.doFinal(str.getBytes(EmailConstants.UTF_8)), 2);
    }

    /* renamed from: a */
    private static void m21976a(byte[] bArr, byte[] bArr2) {
        try {
            DESKeySpec dESKeySpec = new DESKeySpec(bArr);
            f7039a = new IvParameterSpec(bArr2);
            f7040b = SecretKeyFactory.getInstance("DES").generateSecret(dESKeySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
        }
    }

    /* renamed from: b */
    public static String m21975b(String str) throws Exception {
        return m21974b(str, f7041c, f7042d);
    }

    /* renamed from: b */
    public static String m21974b(String str, byte[] bArr, byte[] bArr2) throws Exception {
        m21976a(bArr, bArr2);
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(2, f7040b, f7039a);
        return new String(instance.doFinal(Base64.decode(str, 0)), "UTF-8");
    }

    /* renamed from: a */
    public static String m21980a(String str, int i) {
        if (i < 0 || i >= f7043e.length) {
            return "";
        }
        return m21972c(str + f7043e[i]);
    }

    /* renamed from: c */
    public static String m21972c(String str) {
        return (str == null || str.length() == 0) ? "" : m21978a(str.getBytes());
    }

    /* renamed from: a */
    public static String m21978a(byte[] bArr) {
        return m21971c(m21973b(bArr));
    }

    /* renamed from: b */
    public static byte[] m21973b(byte[] bArr) {
        return m21977a(bArr, "MD5");
    }

    /* renamed from: a */
    private static byte[] m21977a(byte[] bArr, String str) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    private static String m21971c(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = f7044f;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }
}
