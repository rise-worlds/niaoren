package p110z1;

import java.security.MessageDigest;

/* renamed from: z1.cs */
/* loaded from: classes3.dex */
public final class C5116cs {
    /* renamed from: a */
    public static String m3522a(String str) {
        try {
            if (C5097cq.m3699a(str)) {
                return null;
            }
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(String.format("%02x", Byte.valueOf(digest[i])));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
