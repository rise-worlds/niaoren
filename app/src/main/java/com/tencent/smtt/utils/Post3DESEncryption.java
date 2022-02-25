package com.tencent.smtt.utils;

import android.util.Base64;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/* renamed from: com.tencent.smtt.utils.h */
/* loaded from: classes2.dex */
public class Post3DESEncryption {

    /* renamed from: b */
    private static String f13371b = "";

    /* renamed from: c */
    private static byte[] f13372c;

    /* renamed from: g */
    private static String f13374g;

    /* renamed from: d */
    private Cipher f13375d;

    /* renamed from: e */
    private Cipher f13376e;

    /* renamed from: a */
    protected static final char[] f13370a = "0123456789abcdef".toCharArray();

    /* renamed from: f */
    private static Post3DESEncryption f13373f = null;

    private Post3DESEncryption() throws Exception {
        this.f13375d = null;
        this.f13376e = null;
        f13374g = String.valueOf(new Random().nextInt(89999999) + 10000000) + String.valueOf(new Random().nextInt(89999999) + 10000000) + String.valueOf(new Random().nextInt(89999999) + 10000000);
        String str = "00000000";
        for (int i = 0; i < 12; i++) {
            str = str + String.valueOf(new Random().nextInt(89999999) + 10000000);
        }
        f13372c = (str + f13374g).getBytes();
        this.f13375d = Cipher.getInstance("RSA/ECB/NoPadding");
        this.f13375d.init(1, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcEQ3TCNWPBqgIiY7WQ/IqTOTTV2w8aZ/GPm68FK0fAJBemZKtYR3Li46VJ+Hwnor7ZpQnblGWPFaLv5JoPqvavgB0GInuhm+T+syPs1mw0uPLWaqwvZsCfoaIvUuxy5xHJgmWARrK4/9pHyDxRlZte0PCIoR1ko5B8lVVH1X1dQIDAQAB".getBytes(), 0))));
        f13371b = m16419b(this.f13375d.doFinal(f13372c));
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(f13374g.getBytes()));
        this.f13376e = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        this.f13376e.init(1, generateSecret);
    }

    /* renamed from: a */
    public static Post3DESEncryption m16423a() {
        try {
            if (f13373f == null) {
                f13373f = new Post3DESEncryption();
            }
            return f13373f;
        } catch (Exception e) {
            f13373f = null;
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public byte[] m16422a(byte[] bArr) throws Exception {
        return this.f13376e.doFinal(bArr);
    }

    /* renamed from: b */
    public static String m16419b(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f13370a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    /* renamed from: c */
    public byte[] m16416c(byte[] bArr) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(f13374g.getBytes()));
            Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            instance.init(2, generateSecret);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public String m16420b() {
        return f13371b;
    }

    /* renamed from: a */
    public static byte[] m16421a(byte[] bArr, String str) throws Exception {
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str.getBytes()));
        Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        instance.init(1, generateSecret);
        return instance.doFinal(bArr);
    }

    /* renamed from: b */
    public static byte[] m16418b(byte[] bArr, String str) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            instance.init(2, generateSecret);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static String m16417c() {
        return f13374g;
    }
}
