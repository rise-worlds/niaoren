package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import java.util.Arrays;
import org.apache.commons.p105io.IOUtils;

/* renamed from: z1.ob */
/* loaded from: classes3.dex */
final class MatrixUtil {

    /* renamed from: a */
    private static final int[][] f22675a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};

    /* renamed from: b */
    private static final int[][] f22676b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};

    /* renamed from: c */
    private static final int[][] f22677c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR, -1}, new int[]{6, 30, 54, 78, 102, TbsListener.ErrorCode.PV_UPLOAD_ERROR, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, TbsListener.ErrorCode.NEEDDOWNLOAD_3, -1}, new int[]{6, 34, 62, 90, 118, TbsListener.ErrorCode.NEEDDOWNLOAD_7, -1}, new int[]{6, 30, 54, 78, 102, TbsListener.ErrorCode.PV_UPLOAD_ERROR, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, TbsListener.ErrorCode.STARTDOWNLOAD_3}, new int[]{6, 26, 54, 82, 110, 138, TbsListener.ErrorCode.STARTDOWNLOAD_7}, new int[]{6, 30, 58, 86, 114, TbsListener.ErrorCode.NEEDDOWNLOAD_3, TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE}};

    /* renamed from: d */
    private static final int[][] f22678d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    /* renamed from: e */
    private static final int f22679e = 7973;

    /* renamed from: f */
    private static final int f22680f = 1335;

    /* renamed from: g */
    private static final int f22681g = 21522;

    /* renamed from: b */
    private static boolean m1676b(int i) {
        return i == -1;
    }

    private MatrixUtil() {
    }

    /* renamed from: a */
    private static void m1680a(ErrorCorrectionLevel nlVar, int i, ByteMatrix nyVar) throws WriterException {
        BitArray huVar = new BitArray();
        if (i >= 0 && i < 8) {
            int i2 = (nlVar.f22601e << 3) | i;
            huVar.m2544b(i2, 5);
            huVar.m2544b(m1685a(i2, (int) f22680f), 10);
            BitArray huVar2 = new BitArray();
            huVar2.m2544b(f22681g, 15);
            if (huVar.f21908b == huVar2.f21908b) {
                for (int i3 = 0; i3 < huVar.f21907a.length; i3++) {
                    int[] iArr = huVar.f21907a;
                    iArr[i3] = iArr[i3] ^ huVar2.f21907a[i3];
                }
                if (huVar.f21908b == 15) {
                    for (int i4 = 0; i4 < huVar.f21908b; i4++) {
                        boolean a = huVar.m2551a((huVar.f21908b - 1) - i4);
                        int[] iArr2 = f22678d[i4];
                        nyVar.m1742a(iArr2[0], iArr2[1], a);
                        if (i4 < 8) {
                            nyVar.m1742a((nyVar.f22661b - i4) - 1, 8, a);
                        } else {
                            nyVar.m1742a(8, (nyVar.f22662c - 7) + (i4 - 8), a);
                        }
                    }
                    return;
                }
                throw new WriterException("should not happen but we got: " + huVar.f21908b);
            }
            throw new IllegalArgumentException("Sizes don't match");
        }
        throw new WriterException("Invalid mask pattern");
    }

    /* renamed from: a */
    private static int m1686a(int i) {
        return 32 - Integer.numberOfLeadingZeros(i);
    }

    /* renamed from: a */
    private static void m1684a(int i, int i2, ByteMatrix nyVar) throws WriterException {
        for (int i3 = 0; i3 < 8; i3++) {
            int i4 = i + i3;
            if (m1676b(nyVar.m1745a(i4, i2))) {
                nyVar.m1743a(i4, i2, 0);
            } else {
                throw new WriterException();
            }
        }
    }

    /* renamed from: b */
    private static void m1675b(int i, int i2, ByteMatrix nyVar) throws WriterException {
        for (int i3 = 0; i3 < 7; i3++) {
            int i4 = i2 + i3;
            if (m1676b(nyVar.m1745a(i, i4))) {
                nyVar.m1743a(i, i4, 0);
            } else {
                throw new WriterException();
            }
        }
    }

    /* renamed from: c */
    private static void m1672c(int i, int i2, ByteMatrix nyVar) {
        for (int i3 = 0; i3 < 5; i3++) {
            int[] iArr = f22676b[i3];
            for (int i4 = 0; i4 < 5; i4++) {
                nyVar.m1743a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    /* renamed from: d */
    private static void m1669d(int i, int i2, ByteMatrix nyVar) {
        for (int i3 = 0; i3 < 7; i3++) {
            int[] iArr = f22675a[i3];
            for (int i4 = 0; i4 < 7; i4++) {
                nyVar.m1743a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    /* renamed from: d */
    private static void m1668d(ByteMatrix nyVar) throws WriterException {
        int length = f22675a[0].length;
        m1669d(0, 0, nyVar);
        m1669d(nyVar.f22661b - length, 0, nyVar);
        m1669d(0, nyVar.f22661b - length, nyVar);
        m1684a(0, 7, nyVar);
        m1684a(nyVar.f22661b - 8, 7, nyVar);
        m1684a(0, nyVar.f22661b - 8, nyVar);
        m1675b(7, 0, nyVar);
        m1675b((nyVar.f22662c - 7) - 1, 0, nyVar);
        m1675b(7, nyVar.f22662c - 7, nyVar);
    }

    /* renamed from: a */
    private static void m1677a(ByteMatrix nyVar) {
        for (byte[] bArr : nyVar.f22660a) {
            Arrays.fill(bArr, (byte) -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m1682a(BitArray huVar, ErrorCorrectionLevel nlVar, C5411np npVar, int i, ByteMatrix nyVar) throws WriterException {
        int i2 = 0;
        for (byte[] bArr : nyVar.f22660a) {
            Arrays.fill(bArr, (byte) -1);
        }
        int length = f22675a[0].length;
        m1669d(0, 0, nyVar);
        m1669d(nyVar.f22661b - length, 0, nyVar);
        m1669d(0, nyVar.f22661b - length, nyVar);
        m1684a(0, 7, nyVar);
        m1684a(nyVar.f22661b - 8, 7, nyVar);
        m1684a(0, nyVar.f22661b - 8, nyVar);
        m1675b(7, 0, nyVar);
        m1675b((nyVar.f22662c - 7) - 1, 0, nyVar);
        m1675b(7, nyVar.f22662c - 7, nyVar);
        if (nyVar.m1745a(8, nyVar.f22662c - 8) != 0) {
            nyVar.m1743a(8, nyVar.f22662c - 8, 1);
            int i3 = 5;
            if (npVar.f22622a >= 2) {
                int[] iArr = f22677c[npVar.f22622a - 1];
                int length2 = iArr.length;
                int i4 = 0;
                while (i4 < length2) {
                    int i5 = iArr[i4];
                    if (i5 >= 0) {
                        int length3 = iArr.length;
                        int i6 = 0;
                        while (i6 < length3) {
                            int i7 = iArr[i6];
                            if (i7 >= 0 && m1676b(nyVar.m1745a(i7, i5))) {
                                int i8 = i7 - 2;
                                int i9 = i5 - 2;
                                int i10 = 0;
                                while (i10 < i3) {
                                    int[] iArr2 = f22676b[i10];
                                    while (i2 < i3) {
                                        nyVar.m1743a(i8 + i2, i9 + i10, iArr2[i2]);
                                        i2++;
                                        i3 = 5;
                                    }
                                    i10++;
                                    i2 = 0;
                                    i3 = 5;
                                }
                            }
                            i6++;
                            i2 = 0;
                            i3 = 5;
                        }
                    }
                    i4++;
                    i2 = 0;
                    i3 = 5;
                }
            }
            int i11 = 8;
            while (i11 < nyVar.f22661b - 8) {
                int i12 = i11 + 1;
                int i13 = i12 % 2;
                if (m1676b(nyVar.m1745a(i11, 6))) {
                    nyVar.m1743a(i11, 6, i13);
                }
                if (m1676b(nyVar.m1745a(6, i11))) {
                    nyVar.m1743a(6, i11, i13);
                }
                i11 = i12;
            }
            BitArray huVar2 = new BitArray();
            if (i >= 0 && i < 8) {
                int i14 = (nlVar.f22601e << 3) | i;
                huVar2.m2544b(i14, 5);
                huVar2.m2544b(m1685a(i14, (int) f22680f), 10);
                BitArray huVar3 = new BitArray();
                huVar3.m2544b(f22681g, 15);
                if (huVar2.f21908b == huVar3.f21908b) {
                    for (int i15 = 0; i15 < huVar2.f21907a.length; i15++) {
                        int[] iArr3 = huVar2.f21907a;
                        iArr3[i15] = iArr3[i15] ^ huVar3.f21907a[i15];
                    }
                    if (huVar2.f21908b == 15) {
                        for (int i16 = 0; i16 < huVar2.f21908b; i16++) {
                            boolean a = huVar2.m2551a((huVar2.f21908b - 1) - i16);
                            int[] iArr4 = f22678d[i16];
                            nyVar.m1742a(iArr4[0], iArr4[1], a);
                            if (i16 < 8) {
                                nyVar.m1742a((nyVar.f22661b - i16) - 1, 8, a);
                            } else {
                                nyVar.m1742a(8, (nyVar.f22662c - 7) + (i16 - 8), a);
                            }
                        }
                        m1674b(npVar, nyVar);
                        m1683a(huVar, i, nyVar);
                        return;
                    }
                    throw new WriterException("should not happen but we got: " + huVar2.f21908b);
                }
                throw new IllegalArgumentException("Sizes don't match");
            }
            throw new WriterException("Invalid mask pattern");
        }
        throw new WriterException();
    }

    /* renamed from: a */
    private static void m1678a(C5411np npVar, ByteMatrix nyVar) throws WriterException {
        int length = f22675a[0].length;
        m1669d(0, 0, nyVar);
        m1669d(nyVar.f22661b - length, 0, nyVar);
        m1669d(0, nyVar.f22661b - length, nyVar);
        m1684a(0, 7, nyVar);
        m1684a(nyVar.f22661b - 8, 7, nyVar);
        m1684a(0, nyVar.f22661b - 8, nyVar);
        m1675b(7, 0, nyVar);
        m1675b((nyVar.f22662c - 7) - 1, 0, nyVar);
        m1675b(7, nyVar.f22662c - 7, nyVar);
        if (nyVar.m1745a(8, nyVar.f22662c - 8) != 0) {
            nyVar.m1743a(8, nyVar.f22662c - 8, 1);
            if (npVar.f22622a >= 2) {
                int[] iArr = f22677c[npVar.f22622a - 1];
                for (int i : iArr) {
                    if (i >= 0) {
                        for (int i2 : iArr) {
                            if (i2 >= 0 && m1676b(nyVar.m1745a(i2, i))) {
                                int i3 = i2 - 2;
                                int i4 = i - 2;
                                int i5 = 0;
                                while (true) {
                                    if (i5 < 5) {
                                        int[] iArr2 = f22676b[i5];
                                        int i6 = 0;
                                        for (int i7 = 5; i6 < i7; i7 = 5) {
                                            nyVar.m1743a(i3 + i6, i4 + i5, iArr2[i6]);
                                            i6++;
                                        }
                                        i5++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int i8 = 8;
            while (i8 < nyVar.f22661b - 8) {
                int i9 = i8 + 1;
                int i10 = i9 % 2;
                if (m1676b(nyVar.m1745a(i8, 6))) {
                    nyVar.m1743a(i8, 6, i10);
                }
                if (m1676b(nyVar.m1745a(6, i8))) {
                    nyVar.m1743a(6, i8, i10);
                }
                i8 = i9;
            }
            return;
        }
        throw new WriterException();
    }

    /* renamed from: b */
    private static void m1674b(C5411np npVar, ByteMatrix nyVar) throws WriterException {
        if (npVar.f22622a >= 7) {
            BitArray huVar = new BitArray();
            huVar.m2544b(npVar.f22622a, 6);
            huVar.m2544b(m1685a(npVar.f22622a, (int) f22679e), 12);
            if (huVar.f21908b == 18) {
                int i = 0;
                int i2 = 17;
                while (i < 6) {
                    for (int i3 = 0; i3 < 3; i3++) {
                        boolean a = huVar.m2551a(i2);
                        i2--;
                        nyVar.m1742a(i, (nyVar.f22662c - 11) + i3, a);
                        nyVar.m1742a((nyVar.f22662c - 11) + i3, i, a);
                    }
                    i++;
                    i2 = i2;
                }
                return;
            }
            throw new WriterException("should not happen but we got: " + huVar.f21908b);
        }
    }

    /* renamed from: a */
    private static void m1683a(BitArray huVar, int i, ByteMatrix nyVar) throws WriterException {
        boolean z;
        int i2;
        int i3 = nyVar.f22661b - 1;
        int i4 = nyVar.f22662c - 1;
        int i5 = 0;
        int i6 = -1;
        while (i3 > 0) {
            if (i3 == 6) {
                i3--;
            }
            while (i4 >= 0 && i4 < nyVar.f22662c) {
                int i7 = i5;
                for (int i8 = 0; i8 < 2; i8++) {
                    int i9 = i3 - i8;
                    if (m1676b(nyVar.m1745a(i9, i4))) {
                        if (i7 < huVar.f21908b) {
                            z = huVar.m2551a(i7);
                            i7++;
                        } else {
                            z = false;
                        }
                        if (i != -1) {
                            switch (i) {
                                case 0:
                                    i2 = (i4 + i9) & 1;
                                    break;
                                case 1:
                                    i2 = i4 & 1;
                                    break;
                                case 2:
                                    i2 = i9 % 3;
                                    break;
                                case 3:
                                    i2 = (i4 + i9) % 3;
                                    break;
                                case 4:
                                    i2 = ((i4 / 2) + (i9 / 3)) & 1;
                                    break;
                                case 5:
                                    int i10 = i4 * i9;
                                    i2 = (i10 % 3) + (i10 & 1);
                                    break;
                                case 6:
                                    int i11 = i4 * i9;
                                    i2 = ((i11 & 1) + (i11 % 3)) & 1;
                                    break;
                                case 7:
                                    i2 = (((i4 * i9) % 3) + ((i4 + i9) & 1)) & 1;
                                    break;
                                default:
                                    throw new IllegalArgumentException("Invalid mask pattern: ".concat(String.valueOf(i)));
                            }
                            if (i2 == 0) {
                                z = !z;
                            }
                        }
                        nyVar.m1742a(i9, i4, z);
                    }
                }
                i4 += i6;
                i5 = i7;
            }
            i6 = -i6;
            i4 += i6;
            i3 -= 2;
        }
        if (i5 != huVar.f21908b) {
            throw new WriterException("Not all bits consumed: " + i5 + IOUtils.DIR_SEPARATOR_UNIX + huVar.f21908b);
        }
    }

    /* renamed from: a */
    private static int m1685a(int i, int i2) {
        if (i2 != 0) {
            int numberOfLeadingZeros = 32 - Integer.numberOfLeadingZeros(i2);
            int i3 = i << (numberOfLeadingZeros - 1);
            while (32 - Integer.numberOfLeadingZeros(i3) >= numberOfLeadingZeros) {
                i3 ^= i2 << ((32 - Integer.numberOfLeadingZeros(i3)) - numberOfLeadingZeros);
            }
            return i3;
        }
        throw new IllegalArgumentException("0 polynomial");
    }

    /* renamed from: a */
    private static void m1681a(ErrorCorrectionLevel nlVar, int i, BitArray huVar) throws WriterException {
        if (i >= 0 && i < 8) {
            int i2 = (nlVar.f22601e << 3) | i;
            huVar.m2544b(i2, 5);
            huVar.m2544b(m1685a(i2, (int) f22680f), 10);
            BitArray huVar2 = new BitArray();
            huVar2.m2544b(f22681g, 15);
            if (huVar.f21908b == huVar2.f21908b) {
                for (int i3 = 0; i3 < huVar.f21907a.length; i3++) {
                    int[] iArr = huVar.f21907a;
                    iArr[i3] = iArr[i3] ^ huVar2.f21907a[i3];
                }
                if (huVar.f21908b != 15) {
                    throw new WriterException("should not happen but we got: " + huVar.f21908b);
                }
                return;
            }
            throw new IllegalArgumentException("Sizes don't match");
        }
        throw new WriterException("Invalid mask pattern");
    }

    /* renamed from: a */
    private static void m1679a(C5411np npVar, BitArray huVar) throws WriterException {
        huVar.m2544b(npVar.f22622a, 6);
        huVar.m2544b(m1685a(npVar.f22622a, (int) f22679e), 12);
        if (huVar.f21908b != 18) {
            throw new WriterException("should not happen but we got: " + huVar.f21908b);
        }
    }

    /* renamed from: b */
    private static void m1673b(ByteMatrix nyVar) {
        int i = 8;
        while (i < nyVar.f22661b - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (m1676b(nyVar.m1745a(i, 6))) {
                nyVar.m1743a(i, 6, i3);
            }
            if (m1676b(nyVar.m1745a(6, i))) {
                nyVar.m1743a(6, i, i3);
            }
            i = i2;
        }
    }

    /* renamed from: c */
    private static void m1670c(ByteMatrix nyVar) throws WriterException {
        if (nyVar.m1745a(8, nyVar.f22662c - 8) != 0) {
            nyVar.m1743a(8, nyVar.f22662c - 8, 1);
            return;
        }
        throw new WriterException();
    }

    /* renamed from: c */
    private static void m1671c(C5411np npVar, ByteMatrix nyVar) {
        if (npVar.f22622a >= 2) {
            int[] iArr = f22677c[npVar.f22622a - 1];
            for (int i : iArr) {
                if (i >= 0) {
                    for (int i2 : iArr) {
                        if (i2 >= 0 && m1676b(nyVar.m1745a(i2, i))) {
                            int i3 = i2 - 2;
                            int i4 = i - 2;
                            for (int i5 = 0; i5 < 5; i5++) {
                                int[] iArr2 = f22676b[i5];
                                for (int i6 = 0; i6 < 5; i6++) {
                                    nyVar.m1743a(i3 + i6, i4 + i5, iArr2[i6]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
