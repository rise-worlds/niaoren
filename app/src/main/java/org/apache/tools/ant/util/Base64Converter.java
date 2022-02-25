package org.apache.tools.ant.util;

import org.apache.commons.p105io.IOUtils;

/* loaded from: classes2.dex */
public class Base64Converter {
    private static final int BYTE = 8;
    private static final int BYTE_MASK = 255;
    private static final int POS_0_MASK = 63;
    private static final int POS_1_MASK = 4032;
    private static final int POS_1_SHIFT = 6;
    private static final int POS_2_MASK = 258048;
    private static final int POS_2_SHIFT = 12;
    private static final int POS_3_MASK = 16515072;
    private static final int POS_3_SHIFT = 18;
    private static final int WORD = 16;
    private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', IOUtils.DIR_SEPARATOR_UNIX};
    public static final char[] alphabet = ALPHABET;

    public String encode(String str) {
        return encode(str.getBytes());
    }

    public String encode(byte[] bArr) {
        char[] cArr = new char[(((bArr.length - 1) / 3) + 1) * 4];
        int i = 0;
        int i2 = 0;
        while (i + 3 <= bArr.length) {
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 16) | ((bArr[i3] & 255) << 8);
            i = i4 + 1;
            int i6 = i5 | (bArr[i4] & 255);
            int i7 = i2 + 1;
            char[] cArr2 = ALPHABET;
            cArr[i2] = cArr2[(i6 & POS_3_MASK) >> 18];
            int i8 = i7 + 1;
            cArr[i7] = cArr2[(i6 & POS_2_MASK) >> 12];
            int i9 = i8 + 1;
            cArr[i8] = cArr2[(i6 & POS_1_MASK) >> 6];
            i2 = i9 + 1;
            cArr[i9] = cArr2[i6 & 63];
        }
        if (bArr.length - i == 2) {
            int i10 = ((bArr[i + 1] & 255) << 8) | ((bArr[i] & 255) << 16);
            int i11 = i2 + 1;
            char[] cArr3 = ALPHABET;
            cArr[i2] = cArr3[(i10 & POS_3_MASK) >> 18];
            int i12 = i11 + 1;
            cArr[i11] = cArr3[(i10 & POS_2_MASK) >> 12];
            cArr[i12] = cArr3[(i10 & POS_1_MASK) >> 6];
            cArr[i12 + 1] = '=';
        } else if (bArr.length - i == 1) {
            int i13 = (bArr[i] & 255) << 16;
            int i14 = i2 + 1;
            char[] cArr4 = ALPHABET;
            cArr[i2] = cArr4[(i13 & POS_3_MASK) >> 18];
            int i15 = i14 + 1;
            cArr[i14] = cArr4[(i13 & POS_2_MASK) >> 12];
            cArr[i15] = '=';
            cArr[i15 + 1] = '=';
        }
        return new String(cArr);
    }
}
