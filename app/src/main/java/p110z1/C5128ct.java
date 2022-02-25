package p110z1;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: z1.ct */
/* loaded from: classes3.dex */
public final class C5128ct {

    /* renamed from: a */
    private static String f21101a = "idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#";

    /* renamed from: a */
    public static String m3521a() {
        String str = new String();
        for (int i = 0; i < f21101a.length() - 1; i += 4) {
            str = str + f21101a.charAt(i);
        }
        return str;
    }

    /* renamed from: a */
    public static String m3519a(String str, String str2) {
        try {
            PBEKeySpec a = m3520a(str);
            byte[] bytes = str2.getBytes();
            byte[] b = m3517b();
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(a).getEncoded(), "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(b));
            byte[] salt = a.getSalt();
            ByteBuffer allocate = ByteBuffer.allocate(salt.length + instance.getOutputSize(bytes.length));
            allocate.put(salt);
            instance.doFinal(ByteBuffer.wrap(bytes), allocate);
            return m3518a(allocate.array());
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m3518a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15));
            stringBuffer.append("0123456789ABCDEF".charAt(b & 15));
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private static PBEKeySpec m3520a(String str) {
        Class<?> cls = Class.forName(new String(C5103cr.m3523a("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        Object newInstance = cls.newInstance();
        byte[] bArr = new byte[16];
        Method method = cls.getMethod("nextBytes", bArr.getClass());
        method.setAccessible(true);
        method.invoke(newInstance, bArr);
        return new PBEKeySpec(str.toCharArray(), bArr, 10, 128);
    }

    /* renamed from: b */
    public static String m3516b(String str, String str2) {
        byte[] bArr;
        try {
            PBEKeySpec a = m3520a(str);
            int length = str2.length() / 2;
            byte[] bArr2 = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr2[i] = Integer.valueOf(str2.substring(i2, i2 + 2), 16).byteValue();
            }
            byte[] b = m3517b();
            if (bArr2.length <= 16) {
                bArr = null;
            } else {
                SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(a.getPassword(), Arrays.copyOf(bArr2, 16), 10, 128)).getEncoded(), "AES");
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, secretKeySpec, new IvParameterSpec(b));
                bArr = instance.doFinal(bArr2, 16, bArr2.length - 16);
            }
        } catch (Exception unused) {
        }
        if (bArr != null) {
            String str3 = new String(bArr);
            if (C5097cq.m3693c(str3)) {
                return str3;
            }
            return null;
        }
        throw new Exception();
    }

    /* renamed from: b */
    private static byte[] m3517b() {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 48; i += 2) {
                sb.append("AsAgAtA5A6AdAgABABACADAfAsAdAfAsAgAaAgA3A5A6=8=0".charAt(i));
            }
            return C5103cr.m3523a(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
