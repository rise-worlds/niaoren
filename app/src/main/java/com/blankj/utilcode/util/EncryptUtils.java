package com.blankj.utilcode.util;

import android.util.Base64;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.blankj.utilcode.util.u */
/* loaded from: classes.dex */
public final class EncryptUtils {

    /* renamed from: a */
    private static final char[] f6909a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static String m22378a(String str) {
        return (str == null || str.length() == 0) ? "" : m22374a(str.getBytes());
    }

    /* renamed from: a */
    public static String m22374a(byte[] bArr) {
        return m22304o(m22362b(bArr));
    }

    /* renamed from: b */
    public static byte[] m22362b(byte[] bArr) {
        return m22373a(bArr, "MD2");
    }

    /* renamed from: b */
    public static String m22365b(String str) {
        return (str == null || str.length() == 0) ? "" : m22355c(str.getBytes());
    }

    /* renamed from: a */
    public static String m22377a(String str, String str2) {
        if (str == null && str2 == null) {
            return "";
        }
        if (str2 == null) {
            return m22304o(m22349d(str.getBytes()));
        }
        if (str == null) {
            return m22304o(m22349d(str2.getBytes()));
        }
        return m22304o(m22349d((str + str2).getBytes()));
    }

    /* renamed from: c */
    public static String m22355c(byte[] bArr) {
        return m22304o(m22349d(bArr));
    }

    /* renamed from: a */
    public static String m22372a(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 == null) {
            return "";
        }
        if (bArr2 == null) {
            return m22304o(m22349d(bArr));
        }
        if (bArr == null) {
            return m22304o(m22349d(bArr2));
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return m22304o(m22349d(bArr3));
    }

    /* renamed from: d */
    public static byte[] m22349d(byte[] bArr) {
        return m22373a(bArr, "MD5");
    }

    /* renamed from: c */
    public static String m22358c(String str) {
        return m22379a(m22317k(str) ? null : new File(str));
    }

    /* renamed from: d */
    public static byte[] m22351d(String str) {
        return m22366b(m22317k(str) ? null : new File(str));
    }

    /* renamed from: a */
    public static String m22379a(File file) {
        return m22304o(m22366b(file));
    }

    /* renamed from: b */
    public static byte[] m22366b(File file) {
        Throwable th;
        Exception e;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            if (file == null) {
                return null;
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance("MD5"));
                    do {
                    } while (digestInputStream.read(new byte[262144]) > 0);
                    byte[] digest = digestInputStream.getMessageDigest().digest();
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return digest;
                } catch (IOException | NoSuchAlgorithmException e3) {
                    e = e3;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (IOException | NoSuchAlgorithmException e5) {
                e = e5;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: e */
    public static String m22345e(String str) {
        return (str == null || str.length() == 0) ? "" : m22343e(str.getBytes());
    }

    /* renamed from: e */
    public static String m22343e(byte[] bArr) {
        return m22304o(m22337f(bArr));
    }

    /* renamed from: f */
    public static byte[] m22337f(byte[] bArr) {
        return m22373a(bArr, "SHA-1");
    }

    /* renamed from: f */
    public static String m22339f(String str) {
        return (str == null || str.length() == 0) ? "" : m22332g(str.getBytes());
    }

    /* renamed from: g */
    public static String m22332g(byte[] bArr) {
        return m22304o(m22328h(bArr));
    }

    /* renamed from: h */
    public static byte[] m22328h(byte[] bArr) {
        return m22373a(bArr, "SHA224");
    }

    /* renamed from: g */
    public static String m22334g(String str) {
        return (str == null || str.length() == 0) ? "" : m22324i(str.getBytes());
    }

    /* renamed from: i */
    public static String m22324i(byte[] bArr) {
        return m22304o(m22320j(bArr));
    }

    /* renamed from: j */
    public static byte[] m22320j(byte[] bArr) {
        return m22373a(bArr, "SHA-256");
    }

    /* renamed from: h */
    public static String m22329h(String str) {
        return (str == null || str.length() == 0) ? "" : m22316k(str.getBytes());
    }

    /* renamed from: k */
    public static String m22316k(byte[] bArr) {
        return m22304o(m22313l(bArr));
    }

    /* renamed from: l */
    public static byte[] m22313l(byte[] bArr) {
        return m22373a(bArr, "SHA-384");
    }

    /* renamed from: i */
    public static String m22325i(String str) {
        return (str == null || str.length() == 0) ? "" : m22310m(str.getBytes());
    }

    /* renamed from: m */
    public static String m22310m(byte[] bArr) {
        return m22304o(m22307n(bArr));
    }

    /* renamed from: n */
    public static byte[] m22307n(byte[] bArr) {
        return m22373a(bArr, "SHA-512");
    }

    /* renamed from: a */
    private static byte[] m22373a(byte[] bArr, String str) {
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

    /* renamed from: b */
    public static String m22364b(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : m22361b(str.getBytes(), str2.getBytes());
    }

    /* renamed from: b */
    public static String m22361b(byte[] bArr, byte[] bArr2) {
        return m22304o(m22354c(bArr, bArr2));
    }

    /* renamed from: c */
    public static byte[] m22354c(byte[] bArr, byte[] bArr2) {
        return m22371a(bArr, bArr2, "HmacMD5");
    }

    /* renamed from: c */
    public static String m22357c(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : m22348d(str.getBytes(), str2.getBytes());
    }

    /* renamed from: d */
    public static String m22348d(byte[] bArr, byte[] bArr2) {
        return m22304o(m22342e(bArr, bArr2));
    }

    /* renamed from: e */
    public static byte[] m22342e(byte[] bArr, byte[] bArr2) {
        return m22371a(bArr, bArr2, "HmacSHA1");
    }

    /* renamed from: d */
    public static String m22350d(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : m22336f(str.getBytes(), str2.getBytes());
    }

    /* renamed from: f */
    public static String m22336f(byte[] bArr, byte[] bArr2) {
        return m22304o(m22331g(bArr, bArr2));
    }

    /* renamed from: g */
    public static byte[] m22331g(byte[] bArr, byte[] bArr2) {
        return m22371a(bArr, bArr2, "HmacSHA224");
    }

    /* renamed from: e */
    public static String m22344e(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : m22327h(str.getBytes(), str2.getBytes());
    }

    /* renamed from: h */
    public static String m22327h(byte[] bArr, byte[] bArr2) {
        return m22304o(m22323i(bArr, bArr2));
    }

    /* renamed from: i */
    public static byte[] m22323i(byte[] bArr, byte[] bArr2) {
        return m22371a(bArr, bArr2, "HmacSHA256");
    }

    /* renamed from: f */
    public static String m22338f(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : m22319j(str.getBytes(), str2.getBytes());
    }

    /* renamed from: j */
    public static String m22319j(byte[] bArr, byte[] bArr2) {
        return m22304o(m22315k(bArr, bArr2));
    }

    /* renamed from: k */
    public static byte[] m22315k(byte[] bArr, byte[] bArr2) {
        return m22371a(bArr, bArr2, "HmacSHA384");
    }

    /* renamed from: g */
    public static String m22333g(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : m22312l(str.getBytes(), str2.getBytes());
    }

    /* renamed from: l */
    public static String m22312l(byte[] bArr, byte[] bArr2) {
        return m22304o(m22309m(bArr, bArr2));
    }

    /* renamed from: m */
    public static byte[] m22309m(byte[] bArr, byte[] bArr2) {
        return m22371a(bArr, bArr2, "HmacSHA512");
    }

    /* renamed from: a */
    private static byte[] m22371a(byte[] bArr, byte[] bArr2, String str) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, str);
            Mac instance = Mac.getInstance(str);
            instance.init(secretKeySpec);
            return instance.doFinal(bArr);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m22369a(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22302p(m22353c(bArr, bArr2, str, bArr3));
    }

    /* renamed from: b */
    public static String m22360b(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22304o(m22353c(bArr, bArr2, str, bArr3));
    }

    /* renamed from: c */
    public static byte[] m22353c(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22370a(bArr, bArr2, "DES", str, bArr3, true);
    }

    /* renamed from: d */
    public static byte[] m22347d(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22341e(m22301q(bArr), bArr2, str, bArr3);
    }

    /* renamed from: a */
    public static byte[] m22376a(String str, byte[] bArr, String str2, byte[] bArr2) {
        return m22341e(m22321j(str), bArr, str2, bArr2);
    }

    /* renamed from: e */
    public static byte[] m22341e(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22370a(bArr, bArr2, "DES", str, bArr3, false);
    }

    /* renamed from: f */
    public static byte[] m22335f(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22302p(m22326h(bArr, bArr2, str, bArr3));
    }

    /* renamed from: g */
    public static String m22330g(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22304o(m22326h(bArr, bArr2, str, bArr3));
    }

    /* renamed from: h */
    public static byte[] m22326h(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22370a(bArr, bArr2, "DESede", str, bArr3, true);
    }

    /* renamed from: i */
    public static byte[] m22322i(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22318j(m22301q(bArr), bArr2, str, bArr3);
    }

    /* renamed from: b */
    public static byte[] m22363b(String str, byte[] bArr, String str2, byte[] bArr2) {
        return m22318j(m22321j(str), bArr, str2, bArr2);
    }

    /* renamed from: j */
    public static byte[] m22318j(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22370a(bArr, bArr2, "DESede", str, bArr3, false);
    }

    /* renamed from: k */
    public static byte[] m22314k(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22302p(m22308m(bArr, bArr2, str, bArr3));
    }

    /* renamed from: l */
    public static String m22311l(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22304o(m22308m(bArr, bArr2, str, bArr3));
    }

    /* renamed from: m */
    public static byte[] m22308m(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22370a(bArr, bArr2, "AES", str, bArr3, true);
    }

    /* renamed from: n */
    public static byte[] m22305n(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22303o(m22301q(bArr), bArr2, str, bArr3);
    }

    /* renamed from: c */
    public static byte[] m22356c(String str, byte[] bArr, String str2, byte[] bArr2) {
        return m22303o(m22321j(str), bArr, str2, bArr2);
    }

    /* renamed from: o */
    public static byte[] m22303o(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return m22370a(bArr, bArr2, "AES", str, bArr3, false);
    }

    /* renamed from: a */
    private static byte[] m22370a(byte[] bArr, byte[] bArr2, String str, String str2, byte[] bArr3, boolean z) {
        SecretKey secretKey;
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        try {
            if ("DES".equals(str)) {
                secretKey = SecretKeyFactory.getInstance(str).generateSecret(new DESKeySpec(bArr2));
            } else {
                secretKey = new SecretKeySpec(bArr2, str);
            }
            Cipher instance = Cipher.getInstance(str2);
            int i = 1;
            if (!(bArr3 == null || bArr3.length == 0)) {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
                if (!z) {
                    i = 2;
                }
                instance.init(i, secretKey, ivParameterSpec);
                return instance.doFinal(bArr);
            }
            i = 2;
            instance.init(i, secretKey);
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m22368a(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return m22302p(m22352c(bArr, bArr2, z, str));
    }

    /* renamed from: b */
    public static String m22359b(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return m22304o(m22352c(bArr, bArr2, z, str));
    }

    /* renamed from: c */
    public static byte[] m22352c(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return m22367a(bArr, bArr2, z, str, true);
    }

    /* renamed from: d */
    public static byte[] m22346d(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return m22340e(m22301q(bArr), bArr2, z, str);
    }

    /* renamed from: a */
    public static byte[] m22375a(String str, byte[] bArr, boolean z, String str2) {
        return m22340e(m22321j(str), bArr, z, str2);
    }

    /* renamed from: e */
    public static byte[] m22340e(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return m22367a(bArr, bArr2, z, str, false);
    }

    /* renamed from: a */
    private static byte[] m22367a(byte[] bArr, byte[] bArr2, boolean z, String str, boolean z2) {
        Key key;
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        try {
            if (z) {
                key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
            } else {
                key = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bArr2));
            }
            if (key == null) {
                return null;
            }
            Cipher instance = Cipher.getInstance(str);
            instance.init(z2 ? 1 : 2, key);
            int length = bArr.length;
            int i = z2 ? 117 : 128;
            int i2 = length / i;
            if (i2 <= 0) {
                return instance.doFinal(bArr);
            }
            byte[] bArr3 = new byte[i];
            byte[] bArr4 = new byte[0];
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                System.arraycopy(bArr, i3, bArr3, 0, i);
                bArr4 = m22306n(bArr4, instance.doFinal(bArr3));
                i3 += i;
            }
            if (i3 == length) {
                return bArr4;
            }
            int i5 = length - i3;
            byte[] bArr5 = new byte[i5];
            System.arraycopy(bArr, i3, bArr5, 0, i5);
            return m22306n(bArr4, instance.doFinal(bArr5));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            return null;
        } catch (BadPaddingException e4) {
            e4.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    /* renamed from: n */
    private static byte[] m22306n(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* renamed from: o */
    private static String m22304o(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = f6909a;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    /* renamed from: j */
    private static byte[] m22321j(String str) {
        if (m22317k(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = ResultTypeConstant.f7213z + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[length >> 1];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((m22380a(charArray[i]) << 4) | m22380a(charArray[i + 1]));
        }
        return bArr;
    }

    /* renamed from: a */
    private static int m22380a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: p */
    private static byte[] m22302p(byte[] bArr) {
        return Base64.encode(bArr, 2);
    }

    /* renamed from: q */
    private static byte[] m22301q(byte[] bArr) {
        return Base64.decode(bArr, 2);
    }

    /* renamed from: k */
    private static boolean m22317k(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
