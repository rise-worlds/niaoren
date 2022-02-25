package com.stripe.android;

import android.support.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.stripe.android.w */
/* loaded from: classes2.dex */
public class StripeTextUtils {

    /* renamed from: a */
    private static final char[] f12650a = "0123456789ABCDEF".toCharArray();

    @Nullable
    /* renamed from: a */
    public static String m17205a(@Nullable String str) {
        if (m17202b(str)) {
            return null;
        }
        return str;
    }

    /* renamed from: b */
    public static boolean m17202b(@Nullable String str) {
        return str == null || str.trim().length() == 0;
    }

    @Nullable
    /* renamed from: c */
    public static String m17201c(@Nullable String str) {
        if (m17202b(str)) {
            return null;
        }
        return str.replaceAll("\\s|-", "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17204a(String str, String... strArr) {
        if (str == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: d */
    public static String m17200d(@Nullable String str) {
        if (m17202b(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bytes = str.getBytes("UTF-8");
            instance.update(bytes, 0, bytes.length);
            return m17203a(instance.digest());
        } catch (UnsupportedEncodingException unused) {
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m17203a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f12650a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }
}
