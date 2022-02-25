package p110z1;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: z1.bd */
/* loaded from: classes3.dex */
public class C4187bd {

    /* renamed from: a */
    private static String f18312a = "DESede/CBC/PKCS5Padding";

    /* renamed from: a */
    public static byte[] m9733a(String str, byte[] bArr, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher instance = Cipher.getInstance(f18312a);
            instance.init(1, secretKeySpec, new IvParameterSpec(C4081bb.m9791a(instance, str2)));
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m9731b(String str, byte[] bArr, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher instance = Cipher.getInstance(f18312a);
            instance.init(2, secretKeySpec, new IvParameterSpec(C4081bb.m9791a(instance, str2)));
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m9734a(String str, String str2, String str3) {
        try {
            return C3961ay.m9835a(m9733a(str, str2.getBytes(), str3));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static String m9732b(String str, String str2, String str3) {
        try {
            return new String(m9731b(str, C3961ay.m9836a(str2), str3));
        } catch (Exception unused) {
            return null;
        }
    }
}
