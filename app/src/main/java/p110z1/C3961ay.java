package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import org.apache.commons.p105io.IOUtils;

/* renamed from: z1.ay */
/* loaded from: classes3.dex */
public final class C3961ay {

    /* renamed from: a */
    private static final int f17743a = 128;

    /* renamed from: b */
    private static final int f17744b = 64;

    /* renamed from: c */
    private static final int f17745c = 24;

    /* renamed from: d */
    private static final int f17746d = 8;

    /* renamed from: e */
    private static final int f17747e = 16;

    /* renamed from: f */
    private static final int f17748f = 4;

    /* renamed from: g */
    private static final int f17749g = -128;

    /* renamed from: h */
    private static final char f17750h = '=';

    /* renamed from: i */
    private static final byte[] f17751i = new byte[128];

    /* renamed from: j */
    private static final char[] f17752j = new char[64];

    /* renamed from: a */
    private static boolean m9837a(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    /* renamed from: b */
    private static boolean m9833b(char c) {
        return c == '=';
    }

    static {
        int i;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < 128; i4++) {
            f17751i[i4] = -1;
        }
        for (int i5 = 90; i5 >= 65; i5--) {
            f17751i[i5] = (byte) (i5 - 65);
        }
        int i6 = TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR;
        while (true) {
            i = 26;
            if (i6 < 97) {
                break;
            }
            f17751i[i6] = (byte) ((i6 - 97) + 26);
            i6--;
        }
        int i7 = 57;
        while (true) {
            i2 = 52;
            if (i7 < 48) {
                break;
            }
            f17751i[i7] = (byte) ((i7 - 48) + 52);
            i7--;
        }
        byte[] bArr = f17751i;
        bArr[43] = 62;
        bArr[47] = 63;
        for (int i8 = 0; i8 <= 25; i8++) {
            f17752j[i8] = (char) (i8 + 65);
        }
        int i9 = 0;
        while (i <= 51) {
            f17752j[i] = (char) (i9 + 97);
            i++;
            i9++;
        }
        while (i2 <= 61) {
            f17752j[i2] = (char) (i3 + 48);
            i2++;
            i3++;
        }
        char[] cArr = f17752j;
        cArr[62] = '+';
        cArr[63] = IOUtils.DIR_SEPARATOR_UNIX;
    }

    /* renamed from: c */
    private static boolean m9832c(char c) {
        return c < 128 && f17751i[c] != -1;
    }

    /* renamed from: a */
    public static String m9835a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i = length % 24;
        int i2 = length / 24;
        char[] cArr = new char[(i != 0 ? i2 + 1 : i2) * 4];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i3 + 1;
            byte b = bArr[i3];
            int i7 = i6 + 1;
            byte b2 = bArr[i6];
            i3 = i7 + 1;
            byte b3 = bArr[i7];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
            byte b6 = (byte) ((b & cin.f20709a) == 0 ? b >> 2 : (b >> 2) ^ 192);
            byte b7 = (byte) ((b2 & cin.f20709a) == 0 ? b2 >> 4 : (b2 >> 4) ^ TbsListener.ErrorCode.TPATCH_VERSION_FAILED);
            int i8 = (b3 & cin.f20709a) == 0 ? b3 >> 6 : (b3 >> 6) ^ 252;
            int i9 = i4 + 1;
            char[] cArr2 = f17752j;
            cArr[i4] = cArr2[b6];
            int i10 = i9 + 1;
            cArr[i9] = cArr2[(b5 << 4) | b7];
            int i11 = i10 + 1;
            cArr[i10] = cArr2[(b4 << 2) | ((byte) i8)];
            i4 = i11 + 1;
            cArr[i11] = cArr2[b3 & 63];
        }
        if (i == 8) {
            byte b8 = bArr[i3];
            byte b9 = (byte) (b8 & 3);
            int i12 = (b8 & cin.f20709a) == 0 ? b8 >> 2 : (b8 >> 2) ^ 192;
            int i13 = i4 + 1;
            char[] cArr3 = f17752j;
            cArr[i4] = cArr3[(byte) i12];
            int i14 = i13 + 1;
            cArr[i13] = cArr3[b9 << 4];
            cArr[i14] = f17750h;
            cArr[i14 + 1] = f17750h;
        } else if (i == 16) {
            byte b10 = bArr[i3];
            byte b11 = bArr[i3 + 1];
            byte b12 = (byte) (b11 & 15);
            byte b13 = (byte) (b10 & 3);
            byte b14 = (byte) ((b10 & cin.f20709a) == 0 ? b10 >> 2 : (b10 >> 2) ^ 192);
            int i15 = (b11 & cin.f20709a) == 0 ? b11 >> 4 : (b11 >> 4) ^ TbsListener.ErrorCode.TPATCH_VERSION_FAILED;
            int i16 = i4 + 1;
            char[] cArr4 = f17752j;
            cArr[i4] = cArr4[b14];
            int i17 = i16 + 1;
            cArr[i16] = cArr4[((byte) i15) | (b13 << 4)];
            cArr[i17] = cArr4[b12 << 2];
            cArr[i17 + 1] = f17750h;
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static byte[] m9836a(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int a = m9834a(charArray);
        if (a % 4 != 0) {
            return null;
        }
        int i = a / 4;
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i * 3];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < i - 1) {
            int i5 = i3 + 1;
            char c = charArray[i3];
            if (m9832c(c)) {
                int i6 = i5 + 1;
                char c2 = charArray[i5];
                if (m9832c(c2)) {
                    int i7 = i6 + 1;
                    char c3 = charArray[i6];
                    if (m9832c(c3)) {
                        i3 = i7 + 1;
                        char c4 = charArray[i7];
                        if (m9832c(c4)) {
                            byte[] bArr2 = f17751i;
                            byte b = bArr2[c];
                            byte b2 = bArr2[c2];
                            byte b3 = bArr2[c3];
                            byte b4 = bArr2[c4];
                            int i8 = i4 + 1;
                            bArr[i4] = (byte) ((b << 2) | (b2 >> 4));
                            int i9 = i8 + 1;
                            bArr[i8] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
                            i4 = i9 + 1;
                            bArr[i9] = (byte) ((b3 << 6) | b4);
                            i2++;
                        }
                    }
                }
            }
            return null;
        }
        int i10 = i3 + 1;
        char c5 = charArray[i3];
        if (m9832c(c5)) {
            int i11 = i10 + 1;
            char c6 = charArray[i10];
            if (m9832c(c6)) {
                byte[] bArr3 = f17751i;
                byte b5 = bArr3[c5];
                byte b6 = bArr3[c6];
                int i12 = i11 + 1;
                char c7 = charArray[i11];
                char c8 = charArray[i12];
                if (m9832c(c7) && m9832c(c8)) {
                    byte[] bArr4 = f17751i;
                    byte b7 = bArr4[c7];
                    byte b8 = bArr4[c8];
                    int i13 = i4 + 1;
                    bArr[i4] = (byte) ((b5 << 2) | (b6 >> 4));
                    bArr[i13] = (byte) (((b6 & 15) << 4) | ((b7 >> 2) & 15));
                    bArr[i13 + 1] = (byte) (b8 | (b7 << 6));
                    return bArr;
                } else if (!m9833b(c7) || !m9833b(c8)) {
                    if (m9833b(c7) || !m9833b(c8)) {
                        return null;
                    }
                    byte b9 = f17751i[c7];
                    if ((b9 & 3) != 0) {
                        return null;
                    }
                    int i14 = i2 * 3;
                    byte[] bArr5 = new byte[i14 + 2];
                    System.arraycopy(bArr, 0, bArr5, 0, i14);
                    bArr5[i4] = (byte) ((b5 << 2) | (b6 >> 4));
                    bArr5[i4 + 1] = (byte) (((b9 >> 2) & 15) | ((b6 & 15) << 4));
                    return bArr5;
                } else if ((b6 & 15) != 0) {
                    return null;
                } else {
                    int i15 = i2 * 3;
                    byte[] bArr6 = new byte[i15 + 1];
                    System.arraycopy(bArr, 0, bArr6, 0, i15);
                    bArr6[i4] = (byte) ((b5 << 2) | (b6 >> 4));
                    return bArr6;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private static int m9834a(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!m9837a(cArr[i2])) {
                i++;
                cArr[i] = cArr[i2];
            }
        }
        return i;
    }
}
