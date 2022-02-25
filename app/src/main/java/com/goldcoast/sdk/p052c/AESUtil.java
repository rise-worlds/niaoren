package com.goldcoast.sdk.p052c;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.goldcoast.sdk.c.a */
/* loaded from: classes.dex */
public final class AESUtil {

    /* renamed from: a */
    private static String f9003a = "";

    /* renamed from: a */
    public static String m20345a(String str) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            int blockSize = instance.getBlockSize();
            byte[] bytes = str.getBytes();
            int length = bytes.length;
            if (length % blockSize != 0) {
                length += blockSize - (length % blockSize);
            }
            byte[] bArr = new byte[length];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            if (f9003a.equals("")) {
                f9003a = new String(Base64.decode("Y2NiOWRmN2IyYjA5M2E2Yw==\n".getBytes(), 0));
            }
            instance.init(1, new SecretKeySpec(f9003a.getBytes("UTF-8"), "AES"), new IvParameterSpec(f9003a.getBytes()));
            return Base64.encodeToString(instance.doFinal(bArr), 4).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static String m20344b(String str) {
        if (f9003a.equals("")) {
            f9003a = new String(Base64.decode("Y2NiOWRmN2IyYjA5M2E2Yw==\n".getBytes(), 0));
        }
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, new SecretKeySpec(f9003a.getBytes("UTF-8"), "AES"), new IvParameterSpec(f9003a.getBytes()));
            return new String(instance.doFinal(Base64.decode(str, 4)), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
