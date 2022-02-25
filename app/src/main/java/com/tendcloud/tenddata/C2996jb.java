package com.tendcloud.tenddata;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jb */
/* loaded from: classes2.dex */
class C2996jb {
    C2996jb() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static String m15387a(Context context) {
        String packageName = context.getPackageName();
        String a = m15386a(context, packageName);
        return a + packageName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static String m15386a(Context context, String str) {
        String str2 = "";
        try {
            str2 = m15385a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray())));
        } catch (PackageManager.NameNotFoundException | CertificateException unused) {
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str2.length(); i++) {
            stringBuffer.append(str2.charAt(i));
            if (i > 0 && i % 2 == 1 && i < str2.length() - 1) {
                stringBuffer.append(":");
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    static String m15385a(X509Certificate x509Certificate) {
        try {
            return C2997a.encode(m15384a(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException unused) {
            return null;
        }
    }

    /* renamed from: a */
    static byte[] m15384a(byte[] bArr) {
        byte[] bArr2 = new byte[0];
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            return bArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.jb$a */
    /* loaded from: classes2.dex */
    public static class C2997a {
        C2997a() {
        }

        public static String encode(byte[] bArr) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuilder sb = new StringBuilder(bArr.length * 2);
            for (int i = 0; i < bArr.length; i++) {
                sb.append(cArr[(bArr[i] & 240) >> 4]);
                sb.append(cArr[bArr[i] & 15]);
            }
            return sb.toString();
        }
    }
}
