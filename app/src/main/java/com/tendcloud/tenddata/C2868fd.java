package com.tendcloud.tenddata;

import com.tendcloud.tenddata.C2832ea;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fd */
/* loaded from: classes2.dex */
final class C2868fd {

    /* renamed from: a */
    private static IvParameterSpec f13938a = null;

    /* renamed from: b */
    private static byte[] f13939b = null;

    /* renamed from: c */
    private static SecretKey f13940c = null;

    /* renamed from: d */
    private static final int f13941d = 128;

    /* renamed from: e */
    private static final int f13942e = 10000;

    /* renamed from: f */
    private static final String f13943f = "iv";

    /* renamed from: g */
    private static final String f13944g = "salt";

    /* renamed from: h */
    private static final int f13945h = 16;

    /* renamed from: i */
    private static final int f13946i = 32;

    /* renamed from: j */
    private static final String f13947j = "AES/CBC/PKCS5Padding";

    /* renamed from: k */
    private static final String f13948k = "PBKDF2WithHmacSHA1";

    /* renamed from: l */
    private static final String f13949l = C2870ff.m15743a() + "";

    /* renamed from: m */
    private static final String f13950m = "AES";

    C2868fd() {
    }

    static {
        try {
            f13940c = m15757a(f13949l.toCharArray(), m15756b());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private static IvParameterSpec m15760a() {
        IvParameterSpec ivParameterSpec = f13938a;
        if (ivParameterSpec != null) {
            return ivParameterSpec;
        }
        try {
            C2832ea.getFileLock(C2832ea.EnumC2834b.AES_IV_LOCK.toString());
            byte[] a = C2869fe.m15752a(f13943f, 16);
            if (a == null) {
                a = m15759a(16);
                C2869fe.m15749a(f13943f, a);
            }
            f13938a = new IvParameterSpec(a);
        } catch (Throwable unused) {
        }
        C2832ea.releaseFileLock(C2832ea.EnumC2834b.AES_IV_LOCK.toString());
        return f13938a;
    }

    /* renamed from: b */
    private static byte[] m15756b() {
        byte[] bArr = f13939b;
        if (bArr != null) {
            return bArr;
        }
        try {
            C2832ea.getFileLock(C2832ea.EnumC2834b.AES_SALT_LOCK.toString());
            f13939b = C2869fe.m15752a(f13944g, 32);
            if (f13939b == null || f13939b.length == 0) {
                f13939b = m15759a(32);
                C2869fe.m15749a(f13944g, f13939b);
            }
        } catch (Throwable unused) {
        }
        C2832ea.releaseFileLock(C2832ea.EnumC2834b.AES_SALT_LOCK.toString());
        return f13939b;
    }

    /* renamed from: a */
    private static byte[] m15759a(int i) {
        byte[] bArr = new byte[i];
        C2855es.m15794b().nextBytes(bArr);
        return bArr;
    }

    /* renamed from: a */
    private static SecretKey m15757a(char[] cArr, byte[] bArr) {
        return SecretKeyFactory.getInstance(f13948k).generateSecret(new PBEKeySpec(cArr, bArr, f13942e, 128));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m15758a(byte[] bArr) {
        if (f13940c == null) {
            f13940c = m15757a(String.valueOf(C2870ff.m15743a()).toCharArray(), m15756b());
        }
        String str = f13947j;
        if (C2855es.m15793b(19)) {
            str = f13950m;
        }
        Cipher instance = Cipher.getInstance(str);
        instance.init(1, f13940c, m15760a());
        return instance.doFinal(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static byte[] m15755b(byte[] bArr) {
        if (f13940c == null) {
            f13940c = m15757a(String.valueOf(C2870ff.m15743a()).toCharArray(), m15756b());
        }
        String str = f13947j;
        if (C2855es.m15793b(19)) {
            str = f13950m;
        }
        Cipher instance = Cipher.getInstance(str);
        instance.init(2, f13940c, m15760a());
        return instance.doFinal(bArr);
    }
}
