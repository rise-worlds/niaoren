package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import p110z1.C5411np;

/* compiled from: Encoder.java */
/* renamed from: z1.nz */
/* loaded from: classes3.dex */
public final class C5419nz {

    /* renamed from: a */
    static final String f22663a = "ISO-8859-1";

    /* renamed from: b */
    private static final int[] f22664b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    private C5419nz() {
    }

    /* renamed from: a */
    private static QRCode m1727a(String str, ErrorCorrectionLevel nlVar) throws WriterException {
        return m1726a(str, nlVar, (Map<EncodeHintType, ?>) null);
    }

    /* renamed from: a */
    public static QRCode m1726a(String str, ErrorCorrectionLevel nlVar, Map<EncodeHintType, ?> map) throws WriterException {
        C5411np npVar;
        int i;
        CharacterSetECI a;
        String str2 = "ISO-8859-1";
        int i2 = 0;
        boolean z = map != null && map.containsKey(EncodeHintType.CHARACTER_SET);
        if (z) {
            str2 = map.get(EncodeHintType.CHARACTER_SET).toString();
        }
        Mode a2 = m1730a(str, str2);
        BitArray huVar = new BitArray();
        if (a2 == Mode.BYTE && z && (a = CharacterSetECI.m2461a(str2)) != null) {
            huVar.m2544b(Mode.ECI.f22617k, 4);
            huVar.m2544b(a.f21987B[0], 8);
        }
        if ((map != null && map.containsKey(EncodeHintType.GS1_FORMAT)) && Boolean.valueOf(map.get(EncodeHintType.GS1_FORMAT).toString()).booleanValue()) {
            m1720a(Mode.FNC1_FIRST_POSITION, huVar);
        }
        m1720a(a2, huVar);
        BitArray huVar2 = new BitArray();
        switch (a2) {
            case NUMERIC:
                int length = str.length();
                while (i2 < length) {
                    int charAt = str.charAt(i2) - '0';
                    int i3 = i2 + 2;
                    if (i3 < length) {
                        huVar2.m2544b((charAt * 100) + ((str.charAt(i2 + 1) - '0') * 10) + (str.charAt(i3) - '0'), 10);
                        i2 += 3;
                    } else {
                        i2++;
                        if (i2 < length) {
                            huVar2.m2544b((charAt * 10) + (str.charAt(i2) - '0'), 7);
                            i2 = i3;
                        } else {
                            huVar2.m2544b(charAt, 4);
                        }
                    }
                }
                break;
            case ALPHANUMERIC:
                int length2 = str.length();
                while (i2 < length2) {
                    int a3 = m1738a(str.charAt(i2));
                    if (a3 != -1) {
                        int i4 = i2 + 1;
                        if (i4 < length2) {
                            int a4 = m1738a(str.charAt(i4));
                            if (a4 != -1) {
                                huVar2.m2544b((a3 * 45) + a4, 11);
                                i2 += 2;
                            } else {
                                throw new WriterException();
                            }
                        } else {
                            huVar2.m2544b(a3, 6);
                            i2 = i4;
                        }
                    } else {
                        throw new WriterException();
                    }
                }
                break;
            case BYTE:
                try {
                    byte[] bytes = str.getBytes(str2);
                    int length3 = bytes.length;
                    while (i2 < length3) {
                        huVar2.m2544b(bytes[i2], 8);
                        i2++;
                    }
                    break;
                } catch (UnsupportedEncodingException e) {
                    throw new WriterException(e);
                }
            case KANJI:
                try {
                    byte[] bytes2 = str.getBytes("Shift_JIS");
                    int length4 = bytes2.length;
                    while (i2 < length4) {
                        int i5 = ((bytes2[i2] & 255) << 8) | (bytes2[i2 + 1] & 255);
                        int i6 = (i5 < 33088 || i5 > 40956) ? (i5 < 57408 || i5 > 60351) ? -1 : i5 - 49472 : i5 - 33088;
                        if (i6 != -1) {
                            huVar2.m2544b(((i6 >> 8) * 192) + (i6 & 255), 13);
                            i2 += 2;
                        } else {
                            throw new WriterException("Invalid byte sequence");
                        }
                    }
                    break;
                } catch (UnsupportedEncodingException e2) {
                    throw new WriterException(e2);
                }
                break;
            default:
                throw new WriterException("Invalid mode: ".concat(String.valueOf(a2)));
        }
        if (map == null || !map.containsKey(EncodeHintType.QR_VERSION)) {
            npVar = m1735a(m1719a(a2, huVar, huVar2, m1735a(m1719a(a2, huVar, huVar2, C5411np.m1807b(1)), nlVar)), nlVar);
        } else {
            npVar = C5411np.m1807b(Integer.parseInt(map.get(EncodeHintType.QR_VERSION).toString()));
            if (!m1734a(m1719a(a2, huVar, huVar2, npVar), npVar, nlVar)) {
                throw new WriterException("Data too big for requested version");
            }
        }
        BitArray huVar3 = new BitArray();
        huVar3.m2548a(huVar);
        int a5 = a2 == Mode.BYTE ? huVar2.m2552a() : str.length();
        int a6 = a2.m1814a(npVar);
        if (a5 < (1 << a6)) {
            huVar3.m2544b(a5, a6);
            huVar3.m2548a(huVar2);
            C5411np.C5413b a7 = npVar.m1809a(nlVar);
            int b = npVar.f22624c - a7.m1798b();
            m1736a(b, huVar3);
            BitArray a8 = m1724a(huVar3, npVar.f22624c, b, a7.m1799a());
            QRCode ocVar = new QRCode();
            ocVar.f22684c = nlVar;
            ocVar.f22683b = a2;
            ocVar.f22685d = npVar;
            int a9 = npVar.m1811a();
            ByteMatrix nyVar = new ByteMatrix(a9, a9);
            int a10 = m1723a(a8, nlVar, npVar, nyVar);
            ocVar.f22686e = a10;
            MatrixUtil.m1682a(a8, nlVar, npVar, a10, nyVar);
            ocVar.f22687f = nyVar;
            return ocVar;
        }
        throw new WriterException(a5 + " is bigger than " + (i - 1));
    }

    /* renamed from: a */
    private static C5411np m1721a(ErrorCorrectionLevel nlVar, Mode nnVar, BitArray huVar, BitArray huVar2) throws WriterException {
        return m1735a(m1719a(nnVar, huVar, huVar2, m1735a(m1719a(nnVar, huVar, huVar2, C5411np.m1807b(1)), nlVar)), nlVar);
    }

    /* renamed from: a */
    private static int m1738a(int i) {
        int[] iArr = f22664b;
        if (i < iArr.length) {
            return iArr[i];
        }
        return -1;
    }

    /* renamed from: a */
    private static Mode m1731a(String str) {
        return m1730a(str, (String) null);
    }

    /* renamed from: a */
    private static Mode m1730a(String str, String str2) {
        if ("Shift_JIS".equals(str2) && m1715b(str)) {
            return Mode.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else if (m1738a(charAt) == -1) {
                return Mode.BYTE;
            } else {
                z = true;
            }
        }
        if (z) {
            return Mode.ALPHANUMERIC;
        }
        if (z2) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    /* renamed from: b */
    private static boolean m1715b(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                if ((i2 < 129 || i2 > 159) && (i2 < 224 || i2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static int m1723a(BitArray huVar, ErrorCorrectionLevel nlVar, C5411np npVar, ByteMatrix nyVar) throws WriterException {
        boolean z = false;
        int i = -1;
        int i2 = 0;
        int i3 = Integer.MAX_VALUE;
        while (i2 < 8) {
            MatrixUtil.m1682a(huVar, nlVar, npVar, i2, nyVar);
            int a = MaskUtil.m1692a(nyVar, true) + MaskUtil.m1692a(nyVar, z);
            byte[][] bArr = nyVar.f22660a;
            int i4 = nyVar.f22661b;
            int i5 = nyVar.f22662c;
            int i6 = 0;
            int i7 = 0;
            while (i6 < i5 - 1) {
                byte[] bArr2 = bArr[i6];
                int i8 = i7;
                int i9 = 0;
                while (i9 < i4 - 1) {
                    byte b = bArr2[i9];
                    int i10 = i9 + 1;
                    if (b == bArr2[i10]) {
                        int i11 = i6 + 1;
                        if (b == bArr[i11][i9] && b == bArr[i11][i10]) {
                            i8++;
                        }
                    }
                    i9 = i10;
                }
                i6++;
                i7 = i8;
            }
            int i12 = a + (i7 * 3);
            byte[][] bArr3 = nyVar.f22660a;
            int i13 = nyVar.f22661b;
            int i14 = nyVar.f22662c;
            int i15 = 0;
            int i16 = 0;
            while (i15 < i14) {
                int i17 = i16;
                for (int i18 = 0; i18 < i13; i18++) {
                    byte[] bArr4 = bArr3[i15];
                    int i19 = i18 + 6;
                    if (i19 < i13 && bArr4[i18] == 1 && bArr4[i18 + 1] == 0 && bArr4[i18 + 2] == 1 && bArr4[i18 + 3] == 1 && bArr4[i18 + 4] == 1 && bArr4[i18 + 5] == 0 && bArr4[i19] == 1 && (MaskUtil.m1691a(bArr4, i18 - 4, i18) || MaskUtil.m1691a(bArr4, i18 + 7, i18 + 11))) {
                        i17++;
                    }
                    int i20 = i15 + 6;
                    if (i20 < i14 && bArr3[i15][i18] == 1 && bArr3[i15 + 1][i18] == 0 && bArr3[i15 + 2][i18] == 1 && bArr3[i15 + 3][i18] == 1 && bArr3[i15 + 4][i18] == 1 && bArr3[i15 + 5][i18] == 0 && bArr3[i20][i18] == 1 && (MaskUtil.m1690a(bArr3, i18, i15 - 4, i15) || MaskUtil.m1690a(bArr3, i18, i15 + 7, i15 + 11))) {
                        i17++;
                    }
                }
                i15++;
                i16 = i17;
            }
            int i21 = i12 + (i16 * 40);
            byte[][] bArr5 = nyVar.f22660a;
            int i22 = nyVar.f22661b;
            int i23 = nyVar.f22662c;
            int i24 = 0;
            int i25 = 0;
            while (i24 < i23) {
                byte[] bArr6 = bArr5[i24];
                int i26 = i25;
                for (int i27 = 0; i27 < i22; i27++) {
                    if (bArr6[i27] == 1) {
                        i26++;
                    }
                }
                i24++;
                i25 = i26;
            }
            int i28 = nyVar.f22662c * nyVar.f22661b;
            int abs = i21 + (((Math.abs((i25 << 1) - i28) * 10) / i28) * 10);
            if (abs < i3) {
                i = i2;
                i3 = abs;
            }
            i2++;
            z = false;
        }
        return i;
    }

    /* renamed from: a */
    private static C5411np m1735a(int i, ErrorCorrectionLevel nlVar) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            C5411np b = C5411np.m1807b(i2);
            if (m1734a(i, b, nlVar)) {
                return b;
            }
        }
        throw new WriterException("Data too big");
    }

    /* renamed from: a */
    private static void m1737a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 < i3) {
            int i5 = i % i3;
            int i6 = i3 - i5;
            int i7 = i / i3;
            int i8 = i7 + 1;
            int i9 = i2 / i3;
            int i10 = i9 + 1;
            int i11 = i7 - i9;
            int i12 = i8 - i10;
            if (i11 != i12) {
                throw new WriterException("EC bytes mismatch");
            } else if (i3 != i6 + i5) {
                throw new WriterException("RS blocks mismatch");
            } else if (i != ((i9 + i11) * i6) + ((i10 + i12) * i5)) {
                throw new WriterException("Total bytes mismatch");
            } else if (i4 < i6) {
                iArr[0] = i9;
                iArr2[0] = i11;
            } else {
                iArr[0] = i10;
                iArr2[0] = i12;
            }
        } else {
            throw new WriterException("Block ID too large");
        }
    }

    /* renamed from: a */
    private static BitArray m1724a(BitArray huVar, int i, int i2, int i3) throws WriterException {
        char c;
        if (huVar.m2552a() == i2) {
            ArrayList<BlockPair> arrayList = new ArrayList(i3);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                if (i7 < i3) {
                    int i8 = i % i3;
                    int i9 = i3 - i8;
                    int i10 = i / i3;
                    int i11 = i10 + 1;
                    int i12 = i2 / i3;
                    int i13 = i12 + 1;
                    int i14 = i10 - i12;
                    int i15 = i11 - i13;
                    if (i14 != i15) {
                        throw new WriterException("EC bytes mismatch");
                    } else if (i3 != i9 + i8) {
                        throw new WriterException("RS blocks mismatch");
                    } else if (i == ((i12 + i14) * i9) + ((i13 + i15) * i8)) {
                        if (i7 < i9) {
                            c = 0;
                            iArr[0] = i12;
                            iArr2[0] = i14;
                        } else {
                            c = 0;
                            iArr[0] = i13;
                            iArr2[0] = i15;
                        }
                        int i16 = iArr[c];
                        byte[] bArr = new byte[i16];
                        huVar.m2549a(i4 << 3, bArr, i16);
                        byte[] a = m1717a(bArr, iArr2[c]);
                        arrayList.add(new BlockPair(bArr, a));
                        i5 = Math.max(i5, i16);
                        i6 = Math.max(i6, a.length);
                        i4 += iArr[c];
                    } else {
                        throw new WriterException("Total bytes mismatch");
                    }
                } else {
                    throw new WriterException("Block ID too large");
                }
            }
            if (i2 == i4) {
                BitArray huVar2 = new BitArray();
                for (int i17 = 0; i17 < i5; i17++) {
                    for (BlockPair nxVar : arrayList) {
                        byte[] bArr2 = nxVar.f22658a;
                        if (i17 < bArr2.length) {
                            huVar2.m2544b(bArr2[i17], 8);
                        }
                    }
                }
                for (int i18 = 0; i18 < i6; i18++) {
                    for (BlockPair nxVar2 : arrayList) {
                        byte[] bArr3 = nxVar2.f22659b;
                        if (i18 < bArr3.length) {
                            huVar2.m2544b(bArr3[i18], 8);
                        }
                    }
                }
                if (i == huVar2.m2552a()) {
                    return huVar2;
                }
                throw new WriterException("Interleaving error: " + i + " and " + huVar2.m2552a() + " differ.");
            }
            throw new WriterException("Data bytes does not match offset");
        }
        throw new WriterException("Number of bits and data bytes does not match");
    }

    /* renamed from: a */
    private static byte[] m1717a(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[length + i];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new ReedSolomonEncoder(GenericGF.f21928e).m2468a(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    /* renamed from: a */
    private static void m1733a(int i, C5411np npVar, Mode nnVar, BitArray huVar) throws WriterException {
        int a = nnVar.m1814a(npVar);
        int i2 = 1 << a;
        if (i < i2) {
            huVar.m2544b(i, a);
            return;
        }
        throw new WriterException(i + " is bigger than " + (i2 - 1));
    }

    /* renamed from: a */
    private static void m1725a(String str, Mode nnVar, BitArray huVar, String str2) throws WriterException {
        int i = 0;
        switch (nnVar) {
            case NUMERIC:
                int length = str.length();
                while (i < length) {
                    int charAt = str.charAt(i) - '0';
                    int i2 = i + 2;
                    if (i2 < length) {
                        huVar.m2544b((charAt * 100) + ((str.charAt(i + 1) - '0') * 10) + (str.charAt(i2) - '0'), 10);
                        i += 3;
                    } else {
                        i++;
                        if (i < length) {
                            huVar.m2544b((charAt * 10) + (str.charAt(i) - '0'), 7);
                            i = i2;
                        } else {
                            huVar.m2544b(charAt, 4);
                        }
                    }
                }
                return;
            case ALPHANUMERIC:
                int length2 = str.length();
                while (i < length2) {
                    int a = m1738a(str.charAt(i));
                    if (a != -1) {
                        int i3 = i + 1;
                        if (i3 < length2) {
                            int a2 = m1738a(str.charAt(i3));
                            if (a2 != -1) {
                                huVar.m2544b((a * 45) + a2, 11);
                                i += 2;
                            } else {
                                throw new WriterException();
                            }
                        } else {
                            huVar.m2544b(a, 6);
                            i = i3;
                        }
                    } else {
                        throw new WriterException();
                    }
                }
                return;
            case BYTE:
                try {
                    byte[] bytes = str.getBytes(str2);
                    int length3 = bytes.length;
                    while (i < length3) {
                        huVar.m2544b(bytes[i], 8);
                        i++;
                    }
                    return;
                } catch (UnsupportedEncodingException e) {
                    throw new WriterException(e);
                }
            case KANJI:
                try {
                    byte[] bytes2 = str.getBytes("Shift_JIS");
                    int length4 = bytes2.length;
                    while (i < length4) {
                        int i4 = ((bytes2[i] & 255) << 8) | (bytes2[i + 1] & 255);
                        int i5 = (i4 < 33088 || i4 > 40956) ? (i4 < 57408 || i4 > 60351) ? -1 : i4 - 49472 : i4 - 33088;
                        if (i5 != -1) {
                            huVar.m2544b(((i5 >> 8) * 192) + (i5 & 255), 13);
                            i += 2;
                        } else {
                            throw new WriterException("Invalid byte sequence");
                        }
                    }
                    return;
                } catch (UnsupportedEncodingException e2) {
                    throw new WriterException(e2);
                }
            default:
                throw new WriterException("Invalid mode: ".concat(String.valueOf(nnVar)));
        }
    }

    /* renamed from: a */
    private static void m1732a(CharSequence charSequence, BitArray huVar) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - '0';
            int i2 = i + 2;
            if (i2 < length) {
                huVar.m2544b((charAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i2) - '0'), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    huVar.m2544b((charAt * 10) + (charSequence.charAt(i) - '0'), 7);
                    i = i2;
                } else {
                    huVar.m2544b(charAt, 4);
                }
            }
        }
    }

    /* renamed from: b */
    private static void m1716b(CharSequence charSequence, BitArray huVar) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int a = m1738a(charSequence.charAt(i));
            if (a != -1) {
                int i2 = i + 1;
                if (i2 < length) {
                    int a2 = m1738a(charSequence.charAt(i2));
                    if (a2 != -1) {
                        huVar.m2544b((a * 45) + a2, 11);
                        i += 2;
                    } else {
                        throw new WriterException();
                    }
                } else {
                    huVar.m2544b(a, 6);
                    i = i2;
                }
            } else {
                throw new WriterException();
            }
        }
    }

    /* renamed from: a */
    private static void m1728a(String str, BitArray huVar, String str2) throws WriterException {
        try {
            for (byte b : str.getBytes(str2)) {
                huVar.m2544b(b, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e);
        }
    }

    /* renamed from: a */
    private static void m1729a(String str, BitArray huVar) throws WriterException {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i = 0; i < length; i += 2) {
                int i2 = ((bytes[i] & 255) << 8) | (bytes[i + 1] & 255);
                int i3 = (i2 < 33088 || i2 > 40956) ? (i2 < 57408 || i2 > 60351) ? -1 : i2 - 49472 : i2 - 33088;
                if (i3 != -1) {
                    huVar.m2544b(((i3 >> 8) * 192) + (i3 & 255), 13);
                } else {
                    throw new WriterException("Invalid byte sequence");
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e);
        }
    }

    /* renamed from: a */
    private static void m1722a(CharacterSetECI ifVar, BitArray huVar) {
        huVar.m2544b(Mode.ECI.f22617k, 4);
        huVar.m2544b(ifVar.f21987B[0], 8);
    }

    /* renamed from: a */
    private static int m1718a(ByteMatrix nyVar) {
        int a = MaskUtil.m1692a(nyVar, true) + MaskUtil.m1692a(nyVar, false);
        byte[][] bArr = nyVar.f22660a;
        int i = nyVar.f22661b;
        int i2 = nyVar.f22662c;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2 - 1) {
            byte[] bArr2 = bArr[i3];
            int i5 = i4;
            int i6 = 0;
            while (i6 < i - 1) {
                byte b = bArr2[i6];
                int i7 = i6 + 1;
                if (b == bArr2[i7]) {
                    int i8 = i3 + 1;
                    if (b == bArr[i8][i6] && b == bArr[i8][i7]) {
                        i5++;
                    }
                }
                i6 = i7;
            }
            i3++;
            i4 = i5;
        }
        int i9 = a + (i4 * 3);
        byte[][] bArr3 = nyVar.f22660a;
        int i10 = nyVar.f22661b;
        int i11 = nyVar.f22662c;
        int i12 = 0;
        int i13 = 0;
        while (i12 < i11) {
            int i14 = i13;
            for (int i15 = 0; i15 < i10; i15++) {
                byte[] bArr4 = bArr3[i12];
                int i16 = i15 + 6;
                if (i16 < i10 && bArr4[i15] == 1 && bArr4[i15 + 1] == 0 && bArr4[i15 + 2] == 1 && bArr4[i15 + 3] == 1 && bArr4[i15 + 4] == 1 && bArr4[i15 + 5] == 0 && bArr4[i16] == 1 && (MaskUtil.m1691a(bArr4, i15 - 4, i15) || MaskUtil.m1691a(bArr4, i15 + 7, i15 + 11))) {
                    i14++;
                }
                int i17 = i12 + 6;
                if (i17 < i11 && bArr3[i12][i15] == 1 && bArr3[i12 + 1][i15] == 0 && bArr3[i12 + 2][i15] == 1 && bArr3[i12 + 3][i15] == 1 && bArr3[i12 + 4][i15] == 1 && bArr3[i12 + 5][i15] == 0 && bArr3[i17][i15] == 1 && (MaskUtil.m1690a(bArr3, i15, i12 - 4, i12) || MaskUtil.m1690a(bArr3, i15, i12 + 7, i12 + 11))) {
                    i14++;
                }
            }
            i12++;
            i13 = i14;
        }
        int i18 = i9 + (i13 * 40);
        byte[][] bArr5 = nyVar.f22660a;
        int i19 = nyVar.f22661b;
        int i20 = nyVar.f22662c;
        int i21 = 0;
        int i22 = 0;
        while (i21 < i20) {
            byte[] bArr6 = bArr5[i21];
            int i23 = i22;
            for (int i24 = 0; i24 < i19; i24++) {
                if (bArr6[i24] == 1) {
                    i23++;
                }
            }
            i21++;
            i22 = i23;
        }
        int i25 = nyVar.f22662c * nyVar.f22661b;
        return i18 + (((Math.abs((i22 << 1) - i25) * 10) / i25) * 10);
    }

    /* renamed from: a */
    private static int m1719a(Mode nnVar, BitArray huVar, BitArray huVar2, C5411np npVar) {
        return huVar.f21908b + nnVar.m1814a(npVar) + huVar2.f21908b;
    }

    /* renamed from: a */
    private static boolean m1734a(int i, C5411np npVar, ErrorCorrectionLevel nlVar) {
        return npVar.f22624c - npVar.m1809a(nlVar).m1798b() >= (i + 7) / 8;
    }

    /* renamed from: a */
    private static void m1736a(int i, BitArray huVar) throws WriterException {
        int i2 = i << 3;
        if (huVar.f21908b <= i2) {
            for (int i3 = 0; i3 < 4 && huVar.f21908b < i2; i3++) {
                huVar.m2547a(false);
            }
            int i4 = huVar.f21908b & 7;
            if (i4 > 0) {
                while (i4 < 8) {
                    huVar.m2547a(false);
                    i4++;
                }
            }
            int a = i - huVar.m2552a();
            for (int i5 = 0; i5 < a; i5++) {
                huVar.m2544b((i5 & 1) == 0 ? TbsListener.ErrorCode.TPATCH_INSTALL_SUCCESS : 17, 8);
            }
            if (huVar.f21908b != i2) {
                throw new WriterException("Bits size does not equal capacity");
            }
            return;
        }
        throw new WriterException("data bits cannot fit in the QR Code" + huVar.f21908b + " > " + i2);
    }

    /* renamed from: a */
    private static void m1720a(Mode nnVar, BitArray huVar) {
        huVar.m2544b(nnVar.f22617k, 4);
    }
}
