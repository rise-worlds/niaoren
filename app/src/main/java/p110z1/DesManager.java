package p110z1;

import android.os.Build;
import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.tools.tar.TarConstants;

/* renamed from: z1.alb */
/* loaded from: classes3.dex */
public class DesManager {

    /* renamed from: b */
    private static Key f16309b;

    /* renamed from: a */
    private AlgorithmParameterSpec f16310a;

    /* renamed from: c */
    private byte[] f16311c;

    /* renamed from: d */
    private byte[] f16312d;

    /* renamed from: a */
    public void m12648a() {
    }

    /* renamed from: c */
    private void m12644c() {
        try {
            DESKeySpec dESKeySpec = new DESKeySpec(this.f16311c);
            this.f16310a = new IvParameterSpec(this.f16312d);
            f16309b = SecretKeyFactory.getInstance("DES").generateSecret(dESKeySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
        }
    }

    /* renamed from: a */
    public String m12647a(String str) throws Exception {
        byte[] bArr;
        if (f16309b == null || this.f16310a == null) {
            m12644c();
        }
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(1, f16309b, this.f16310a);
        byte[] bArr2 = new byte[0];
        if (Build.VERSION.SDK_INT >= 19) {
            bArr = instance.doFinal(str.getBytes(StandardCharsets.UTF_8));
        } else {
            bArr = instance.doFinal(str.getBytes("UTF-8"));
        }
        return Base64.encodeToString(bArr, 2);
    }

    /* renamed from: b */
    public String m12645b(String str) throws Exception {
        if (f16309b == null || this.f16310a == null) {
            m12644c();
        }
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(2, f16309b, this.f16310a);
        return new String(instance.doFinal(Base64.decode(str, 0)), "UTF-8");
    }

    /* compiled from: DesManager.java */
    /* renamed from: z1.alb$a */
    /* loaded from: classes3.dex */
    public static class C3642a {

        /* renamed from: a */
        private static final DesManager f16313a = new DesManager();
    }

    private DesManager() {
        this.f16310a = null;
        this.f16311c = new byte[]{TarConstants.LF_CONTIG, 33, 16, 18, 28, 105, 73, 91};
        this.f16312d = new byte[]{1, 82, 64, 18, 19, 110, TarConstants.LF_BLK, 37};
    }

    /* renamed from: b */
    public static DesManager m12646b() {
        return C3642a.f16313a;
    }
}
