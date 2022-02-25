package p110z1;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: z1.az */
/* loaded from: classes3.dex */
public class C3990az {
    /* renamed from: a */
    public static String m9819a(String str, String str2) {
        return m9820a(1, str, str2);
    }

    /* renamed from: b */
    public static String m9818b(String str, String str2) {
        return m9820a(2, str, str2);
    }

    /* renamed from: a */
    public static String m9820a(int i, String str, String str2) {
        byte[] bArr;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "DES");
            Cipher instance = Cipher.getInstance("DES");
            instance.init(i, secretKeySpec);
            if (i == 2) {
                bArr = C3961ay.m9836a(str);
            } else {
                bArr = str.getBytes("UTF-8");
            }
            byte[] doFinal = instance.doFinal(bArr);
            if (i == 2) {
                return new String(doFinal);
            }
            return C3961ay.m9835a(doFinal);
        } catch (Exception e) {
            C4921cd.m5618a(e);
            return null;
        }
    }
}
