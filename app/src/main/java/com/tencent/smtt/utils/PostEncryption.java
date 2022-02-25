package com.tencent.smtt.utils;

import android.util.Base64;
import com.tencent.smtt.sdk.p078a.DesUtils;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;

/* renamed from: com.tencent.smtt.utils.i */
/* loaded from: classes2.dex */
public class PostEncryption {

    /* renamed from: a */
    private static final char[] f13377a = "0123456789abcdef".toCharArray();

    /* renamed from: b */
    private static PostEncryption f13378b;

    /* renamed from: d */
    private String f13380d;

    /* renamed from: e */
    private String f13381e = String.valueOf(new Random().nextInt(89999999) + 10000000);

    /* renamed from: c */
    private String f13379c = this.f13381e + String.valueOf(new Random().nextInt(89999999) + 10000000);

    private PostEncryption() {
    }

    /* renamed from: a */
    public static synchronized PostEncryption m16415a() {
        PostEncryption iVar;
        synchronized (PostEncryption.class) {
            if (f13378b == null) {
                f13378b = new PostEncryption();
            }
            iVar = f13378b;
        }
        return iVar;
    }

    /* renamed from: b */
    public void m16412b() throws Exception {
        Security.addProvider((Provider) Class.forName("com.android.org.bouncycastle.jce.provider.BouncyCastleProvider", true, ClassLoader.getSystemClassLoader()).newInstance());
    }

    /* renamed from: c */
    public String m16410c() throws Exception {
        if (this.f13380d == null) {
            byte[] bytes = this.f13379c.getBytes();
            Cipher cipher = null;
            try {
                try {
                    cipher = Cipher.getInstance("RSA/ECB/NoPadding");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception unused) {
                m16412b();
                cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            }
            cipher.init(1, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MCwwDQYJKoZIhvcNAQEBBQADGwAwGAIRAMRB/Q0hTCD+XtnQhpQJefUCAwEAAQ==".getBytes(), 0))));
            this.f13380d = m16411b(cipher.doFinal(bytes));
        }
        return this.f13380d;
    }

    /* renamed from: a */
    public String m16414a(String str) throws Exception {
        Cipher cipher;
        byte[] bytes = str.getBytes();
        try {
            try {
                cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            } catch (Exception unused) {
                m16412b();
                cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            }
        } catch (Exception e) {
            e.printStackTrace();
            cipher = null;
        }
        cipher.init(1, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MCwwDQYJKoZIhvcNAQEBBQADGwAwGAIRAMRB/Q0hTCD+XtnQhpQJefUCAwEAAQ==".getBytes(), 0))));
        return m16411b(cipher.doFinal(bytes));
    }

    /* renamed from: a */
    public byte[] m16413a(byte[] bArr) throws Exception {
        return DesUtils.m16902a(this.f13381e.getBytes(), bArr, 1);
    }

    /* renamed from: b */
    private String m16411b(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f13377a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }
}
