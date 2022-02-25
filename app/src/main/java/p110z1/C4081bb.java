package p110z1;

import android.text.TextUtils;
import java.security.SecureRandom;
import javax.crypto.Cipher;

/* renamed from: z1.bb */
/* loaded from: classes3.dex */
class C4081bb {
    C4081bb() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m9791a(Cipher cipher, String str) {
        SecureRandom secureRandom = new SecureRandom();
        int blockSize = cipher.getBlockSize();
        if (TextUtils.isEmpty(str)) {
            str = String.valueOf(secureRandom.nextDouble());
        }
        byte[] bArr = new byte[blockSize * 2];
        byte[] bArr2 = new byte[blockSize];
        secureRandom.nextBytes(bArr2);
        int length = bArr.length;
        for (int i = 1; i < length; i++) {
            bArr[i] = (byte) (str.codePointAt(i % str.length()) & 127);
            if (i >= blockSize) {
                bArr[i] = (byte) (bArr[0] & bArr[i]);
            }
        }
        System.arraycopy(bArr, blockSize, bArr2, 0, blockSize);
        return bArr2;
    }
}
