package org.apache.tools.tar;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.apache.tools.zip.ZipEncoding;
import org.apache.tools.zip.ZipEncodingHelper;
import p110z1.SimpleComparison;
import p110z1.cin;

/* loaded from: classes2.dex */
public class TarUtils {
    private static final int BYTE_MASK = 255;
    static final ZipEncoding DEFAULT_ENCODING = ZipEncodingHelper.getZipEncoding(null);
    static final ZipEncoding FALLBACK_ENCODING = new ZipEncoding() { // from class: org.apache.tools.tar.TarUtils.1
        @Override // org.apache.tools.zip.ZipEncoding
        public boolean canEncode(String str) {
            return true;
        }

        @Override // org.apache.tools.zip.ZipEncoding
        public ByteBuffer encode(String str) {
            int length = str.length();
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) str.charAt(i);
            }
            return ByteBuffer.wrap(bArr);
        }

        @Override // org.apache.tools.zip.ZipEncoding
        public String decode(byte[] bArr) {
            StringBuilder sb = new StringBuilder(bArr.length);
            for (byte b : bArr) {
                if (b == 0) {
                    break;
                }
                sb.append((char) (b & 255));
            }
            return sb.toString();
        }
    };

    private TarUtils() {
    }

    public static long parseOctal(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        if (i2 >= 2) {
            long j = 0;
            if (bArr[i] == 0) {
                return 0L;
            }
            int i4 = i;
            while (i4 < i3 && bArr[i4] == 32) {
                i4++;
            }
            byte b = bArr[i3 - 1];
            while (i4 < i3 && (b == 0 || b == 32)) {
                i3--;
                b = bArr[i3 - 1];
            }
            while (i4 < i3) {
                byte b2 = bArr[i4];
                if (b2 < 48 || b2 > 55) {
                    throw new IllegalArgumentException(exceptionMessage(bArr, i, i2, i4, b2));
                }
                j = (j << 3) + (b2 - 48);
                i4++;
            }
            return j;
        }
        throw new IllegalArgumentException("Length " + i2 + " must be at least 2");
    }

    public static long parseOctalOrBinary(byte[] bArr, int i, int i2) {
        if ((bArr[i] & cin.f20709a) == 0) {
            return parseOctal(bArr, i, i2);
        }
        boolean z = bArr[i] == -1;
        if (i2 < 9) {
            return parseBinaryLong(bArr, i, i2, z);
        }
        return parseBinaryBigInteger(bArr, i, i2, z);
    }

    private static long parseBinaryLong(byte[] bArr, int i, int i2, boolean z) {
        if (i2 < 9) {
            long j = 0;
            for (int i3 = 1; i3 < i2; i3++) {
                j = (j << 8) + (bArr[i + i3] & 255);
            }
            if (z) {
                j = (j - 1) ^ (((long) Math.pow(2.0d, (i2 - 1) * 8)) - 1);
            }
            return z ? -j : j;
        }
        throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number exceeds maximum signed long value");
    }

    private static long parseBinaryBigInteger(byte[] bArr, int i, int i2, boolean z) {
        int i3 = i2 - 1;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i + 1, bArr2, 0, i3);
        BigInteger bigInteger = new BigInteger(bArr2);
        if (z) {
            bigInteger = bigInteger.add(BigInteger.valueOf(-1L)).not();
        }
        if (bigInteger.bitLength() <= 63) {
            return z ? -bigInteger.longValue() : bigInteger.longValue();
        }
        throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number exceeds maximum signed long value");
    }

    public static boolean parseBoolean(byte[] bArr, int i) {
        return bArr[i] == 1;
    }

    private static String exceptionMessage(byte[] bArr, int i, int i2, int i3, byte b) {
        String replaceAll = new String(bArr, i, i2).replaceAll("\u0000", "{NUL}");
        return "Invalid byte " + ((int) b) + " at offset " + (i3 - i) + " in '" + replaceAll + "' len=" + i2;
    }

    public static String parseName(byte[] bArr, int i, int i2) {
        try {
            try {
                return parseName(bArr, i, i2, DEFAULT_ENCODING);
            } catch (IOException unused) {
                return parseName(bArr, i, i2, FALLBACK_ENCODING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String parseName(byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        while (i2 > 0 && bArr[(i + i2) - 1] == 0) {
            i2--;
        }
        if (i2 <= 0) {
            return "";
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return zipEncoding.decode(bArr2);
    }

    public static int formatNameBytes(String str, byte[] bArr, int i, int i2) {
        try {
            try {
                return formatNameBytes(str, bArr, i, i2, DEFAULT_ENCODING);
            } catch (IOException unused) {
                return formatNameBytes(str, bArr, i, i2, FALLBACK_ENCODING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int formatNameBytes(String str, byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        int length = str.length();
        ByteBuffer encode = zipEncoding.encode(str);
        while (encode.limit() > i2 && length > 0) {
            length--;
            encode = zipEncoding.encode(str.substring(0, length));
        }
        int limit = encode.limit() - encode.position();
        System.arraycopy(encode.array(), encode.arrayOffset(), bArr, i, limit);
        while (limit < i2) {
            bArr[i + limit] = 0;
            limit++;
        }
        return i + i2;
    }

    public static void formatUnsignedOctalString(long j, byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i2 - 1;
        if (j == 0) {
            i3 = i4 - 1;
            bArr[i4 + i] = TarConstants.LF_NORMAL;
        } else {
            long j2 = j;
            while (i4 >= 0 && j2 != 0) {
                bArr[i + i4] = (byte) (((byte) (7 & j2)) + TarConstants.LF_NORMAL);
                j2 >>>= 3;
                i4--;
            }
            if (j2 == 0) {
                i3 = i4;
            } else {
                throw new IllegalArgumentException(j + SimpleComparison.f23609c + Long.toOctalString(j) + " will not fit in octal number buffer of length " + i2);
            }
        }
        while (i3 >= 0) {
            bArr[i + i3] = TarConstants.LF_NORMAL;
            i3--;
        }
    }

    public static int formatOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        bArr[i3 + 1 + i] = 0;
        return i + i2;
    }

    public static int formatLongOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        return i + i2;
    }

    public static int formatLongOctalOrBinaryBytes(long j, byte[] bArr, int i, int i2) {
        long j2 = i2 == 8 ? TarConstants.MAXID : TarConstants.MAXSIZE;
        boolean z = j < 0;
        if (!z && j <= j2) {
            return formatLongOctalBytes(j, bArr, i, i2);
        }
        if (i2 < 9) {
            formatLongBinary(j, bArr, i, i2, z);
        }
        formatBigIntegerBinary(j, bArr, i, i2, z);
        bArr[i] = (byte) (z ? 255 : 128);
        return i + i2;
    }

    private static void formatLongBinary(long j, byte[] bArr, int i, int i2, boolean z) {
        int i3;
        long j2 = 1 << ((i2 - 1) * 8);
        long abs = Math.abs(j);
        if (abs < j2) {
            if (z) {
                abs = ((abs ^ (j2 - 1)) | (255 << i3)) + 1;
            }
            for (int i4 = (i2 + i) - 1; i4 >= i; i4--) {
                bArr[i4] = (byte) abs;
                abs >>= 8;
            }
            return;
        }
        throw new IllegalArgumentException("Value " + j + " is too large for " + i2 + " byte field.");
    }

    private static void formatBigIntegerBinary(long j, byte[] bArr, int i, int i2, boolean z) {
        byte[] byteArray = BigInteger.valueOf(j).toByteArray();
        int length = byteArray.length;
        int i3 = (i2 + i) - length;
        int i4 = 0;
        System.arraycopy(byteArray, 0, bArr, i3, length);
        if (z) {
            i4 = 255;
        }
        byte b = (byte) i4;
        while (true) {
            i++;
            if (i < i3) {
                bArr[i] = b;
            } else {
                return;
            }
        }
    }

    public static int formatCheckSumOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 0;
        bArr[i3 + 1 + i] = 32;
        return i + i2;
    }

    public static long computeCheckSum(byte[] bArr) {
        long j = 0;
        for (byte b : bArr) {
            j += b & 255;
        }
        return j;
    }
}
