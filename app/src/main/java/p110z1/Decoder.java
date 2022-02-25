package p110z1;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.cyjh.mobileanjian.ipc.utils.RpcError;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Arrays;
import org.slf4j.Marker;

/* renamed from: z1.fw */
/* loaded from: classes3.dex */
public final class Decoder {

    /* renamed from: a */
    private static final String[] f21723a = {"CTRL_PS", ExpandableTextView.f6958c, "A", "B", "C", "D", "E", TessBaseAPI.f9205f, "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", TessBaseAPI.f9204e, "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* renamed from: b */
    private static final String[] f21724b = {"CTRL_PS", ExpandableTextView.f6958c, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", RpcError.f8691a, "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* renamed from: c */
    private static final String[] f21725c = {"CTRL_PS", ExpandableTextView.f6958c, "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};

    /* renamed from: d */
    private static final String[] f21726d = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", C4745bt.f20071b, "'", "(", ")", Marker.ANY_MARKER, Marker.ANY_NON_NULL_MARKER, ",", "-", Consts.f23430h, "/", ":", C4963cj.f20745b, SimpleComparison.f23612f, SimpleComparison.f23609c, SimpleComparison.f23610d, "?", "[", "]", "{", C4963cj.f20747d, "CTRL_UL"};

    /* renamed from: e */
    private static final String[] f21727e = {"CTRL_PS", ExpandableTextView.f6958c, ResultTypeConstant.f7213z, "1", "2", DdyConstants.APP_INSTALL_ERROR, DdyConstants.APP_INSTALL_DOWNLOADING, DdyConstants.APP_INSTALL_INSTALLING, DdyConstants.APP_INSTALL_UNINSTALL, DdyConstants.APP_INSTALL_INSTALLED, "8", "9", ",", Consts.f23430h, "CTRL_UL", "CTRL_US"};

    /* renamed from: f */
    private AztecDetectorResult f21728f;

    /* renamed from: a */
    private static int m2807a(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: Decoder.java */
    /* renamed from: z1.fw$a */
    /* loaded from: classes3.dex */
    public static final class EnumC5360a extends Enum<EnumC5360a> {

        /* renamed from: a */
        public static final int f21730a = 1;

        /* renamed from: b */
        public static final int f21731b = 2;

        /* renamed from: c */
        public static final int f21732c = 3;

        /* renamed from: d */
        public static final int f21733d = 4;

        /* renamed from: e */
        public static final int f21734e = 5;

        /* renamed from: f */
        public static final int f21735f = 6;

        /* renamed from: g */
        private static final /* synthetic */ int[] f21736g = {f21730a, f21731b, f21732c, f21733d, f21734e, f21735f};

        private EnumC5360a(String str, int i) {
        }

        /* renamed from: a */
        public static int[] m2798a() {
            return (int[]) f21736g.clone();
        }
    }

    /* renamed from: a */
    public final DecoderResult m2806a(AztecDetectorResult fvVar) throws FormatException {
        int i;
        this.f21728f = fvVar;
        BitMatrix hyVar = fvVar.f21999d;
        boolean z = this.f21728f.f21720a;
        int i2 = this.f21728f.f21722c;
        int i3 = (z ? 11 : 14) + (i2 << 2);
        int[] iArr = new int[i3];
        boolean[] zArr = new boolean[((z ? 88 : 112) + (i2 << 4)) * i2];
        int i4 = 2;
        if (z) {
            for (int i5 = 0; i5 < iArr.length; i5++) {
                iArr[i5] = i5;
            }
        } else {
            int i6 = i3 / 2;
            int i7 = ((i3 + 1) + (((i6 - 1) / 15) * 2)) / 2;
            for (int i8 = 0; i8 < i6; i8++) {
                int i9 = (i8 / 15) + i8;
                iArr[(i6 - i8) - 1] = (i7 - i9) - 1;
                iArr[i6 + i8] = i9 + i7 + 1;
            }
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < i2) {
            int i12 = ((i2 - i10) << i4) + (z ? 9 : 12);
            int i13 = i10 << 1;
            int i14 = (i3 - 1) - i13;
            int i15 = 0;
            while (i15 < i12) {
                int i16 = i15 << 1;
                int i17 = 0;
                while (i17 < i4) {
                    int i18 = i13 + i17;
                    int i19 = i13 + i15;
                    zArr[i11 + i16 + i17] = hyVar.m2519a(iArr[i18], iArr[i19]);
                    int i20 = i14 - i17;
                    zArr[(i12 * 2) + i11 + i16 + i17] = hyVar.m2519a(iArr[i19], iArr[i20]);
                    int i21 = i14 - i15;
                    zArr[(i12 * 4) + i11 + i16 + i17] = hyVar.m2519a(iArr[i20], iArr[i21]);
                    zArr[(i12 * 6) + i11 + i16 + i17] = hyVar.m2519a(iArr[i21], iArr[i18]);
                    i17++;
                    i3 = i3;
                    i2 = i2;
                    z = z;
                    i4 = 2;
                }
                i15++;
                i4 = 2;
            }
            i11 += i12 << 3;
            i10++;
            z = z;
            i4 = 2;
        }
        boolean[] c = m2800c(zArr);
        byte[] bArr = new byte[(c.length + 7) / 8];
        for (int i22 = 0; i22 < bArr.length; i22++) {
            int i23 = i22 << 3;
            int length = c.length - i23;
            if (length >= 8) {
                i = m2802a(c, i23, 8);
            } else {
                i = m2802a(c, i23, length) << (8 - length);
            }
            bArr[i22] = (byte) i;
        }
        DecoderResult igVar = new DecoderResult(bArr, m2801b(c), null, null);
        igVar.f21990b = c.length;
        return igVar;
    }

    /* renamed from: a */
    private static String m2804a(boolean[] zArr) {
        return m2801b(zArr);
    }

    /* renamed from: b */
    private static String m2801b(boolean[] zArr) {
        String str;
        int length = zArr.length;
        int i = EnumC5360a.f21730a;
        int i2 = EnumC5360a.f21730a;
        StringBuilder sb = new StringBuilder(20);
        int i3 = i;
        int i4 = 0;
        while (i4 < length) {
            if (i2 == EnumC5360a.f21735f) {
                if (length - i4 >= 5) {
                    int a = m2802a(zArr, i4, 5);
                    int i5 = i4 + 5;
                    if (a == 0) {
                        if (length - i5 >= 11) {
                            a = m2802a(zArr, i5, 11) + 31;
                            i5 += 11;
                        }
                    }
                    int i6 = 0;
                    while (true) {
                        if (i6 >= a) {
                            i4 = i5;
                        } else if (length - i5 < 8) {
                            i4 = length;
                        } else {
                            sb.append((char) m2802a(zArr, i5, 8));
                            i5 += 8;
                            i6++;
                        }
                    }
                    i2 = i3;
                }
                return sb.toString();
            }
            int i7 = i2 == EnumC5360a.f21733d ? 4 : 5;
            if (length - i4 < i7) {
                return sb.toString();
            }
            int a2 = m2802a(zArr, i4, i7);
            i4 += i7;
            switch (C53591.f21729a[i2 - 1]) {
                case 1:
                    str = f21723a[a2];
                    break;
                case 2:
                    str = f21724b[a2];
                    break;
                case 3:
                    str = f21725c[a2];
                    break;
                case 4:
                    str = f21726d[a2];
                    break;
                case 5:
                    str = f21727e[a2];
                    break;
                default:
                    throw new IllegalStateException("Bad table");
            }
            if (str.startsWith("CTRL_")) {
                char charAt = str.charAt(5);
                if (charAt == 'B') {
                    i3 = EnumC5360a.f21735f;
                } else if (charAt == 'D') {
                    i3 = EnumC5360a.f21733d;
                } else if (charAt != 'P') {
                    switch (charAt) {
                        case 'L':
                            i3 = EnumC5360a.f21731b;
                            break;
                        case 'M':
                            i3 = EnumC5360a.f21732c;
                            break;
                        default:
                            i3 = EnumC5360a.f21730a;
                            break;
                    }
                } else {
                    i3 = EnumC5360a.f21734e;
                }
                if (str.charAt(6) == 'L') {
                    i2 = i3;
                } else {
                    i3 = i2;
                    i2 = i3;
                }
            } else {
                sb.append(str);
                i2 = i3;
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static int m2809a(char c) {
        if (c == 'B') {
            return EnumC5360a.f21735f;
        }
        if (c == 'D') {
            return EnumC5360a.f21733d;
        }
        if (c == 'P') {
            return EnumC5360a.f21734e;
        }
        switch (c) {
            case 'L':
                return EnumC5360a.f21731b;
            case 'M':
                return EnumC5360a.f21732c;
            default:
                return EnumC5360a.f21730a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Decoder.java */
    /* renamed from: z1.fw$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C53591 {

        /* renamed from: a */
        static final /* synthetic */ int[] f21729a = new int[EnumC5360a.m2798a().length];

        static {
            try {
                f21729a[EnumC5360a.f21730a - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f21729a[EnumC5360a.f21731b - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f21729a[EnumC5360a.f21732c - 1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f21729a[EnumC5360a.f21734e - 1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f21729a[EnumC5360a.f21733d - 1] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* renamed from: a */
    private static String m2808a(int i, int i2) {
        switch (C53591.f21729a[i - 1]) {
            case 1:
                return f21723a[i2];
            case 2:
                return f21724b[i2];
            case 3:
                return f21725c[i2];
            case 4:
                return f21726d[i2];
            case 5:
                return f21727e[i2];
            default:
                throw new IllegalStateException("Bad table");
        }
    }

    /* renamed from: c */
    private boolean[] m2800c(boolean[] zArr) throws FormatException {
        GenericGF hzVar;
        int i = 8;
        if (this.f21728f.f21722c <= 2) {
            i = 6;
            hzVar = GenericGF.f21926c;
        } else if (this.f21728f.f21722c <= 8) {
            hzVar = GenericGF.f21930g;
        } else if (this.f21728f.f21722c <= 22) {
            i = 10;
            hzVar = GenericGF.f21925b;
        } else {
            i = 12;
            hzVar = GenericGF.f21924a;
        }
        int i2 = this.f21728f.f21721b;
        int length = zArr.length / i;
        if (length >= i2) {
            int length2 = zArr.length % i;
            int[] iArr = new int[length];
            int i3 = 0;
            while (i3 < length) {
                iArr[i3] = m2802a(zArr, length2, i);
                i3++;
                length2 += i;
            }
            try {
                new ReedSolomonDecoder(hzVar).m2470a(iArr, length - i2);
                int i4 = (1 << i) - 1;
                int i5 = 0;
                for (int i6 = 0; i6 < i2; i6++) {
                    int i7 = iArr[i6];
                    if (i7 == 0 || i7 == i4) {
                        throw FormatException.m2059a();
                    }
                    if (i7 == 1 || i7 == i4 - 1) {
                        i5++;
                    }
                }
                boolean[] zArr2 = new boolean[(i2 * i) - i5];
                int i8 = 0;
                for (int i9 = 0; i9 < i2; i9++) {
                    int i10 = iArr[i9];
                    if (i10 == 1 || i10 == i4 - 1) {
                        Arrays.fill(zArr2, i8, (i8 + i) - 1, i10 > 1);
                        i8 += i - 1;
                    } else {
                        for (int i11 = i - 1; i11 >= 0; i11--) {
                            i8++;
                            zArr2[i8] = ((1 << i11) & i10) != 0;
                        }
                    }
                }
                return zArr2;
            } catch (ReedSolomonException e) {
                throw FormatException.m2058a(e);
            }
        } else {
            throw FormatException.m2059a();
        }
    }

    /* renamed from: a */
    private boolean[] m2805a(BitMatrix hyVar) {
        boolean z = this.f21728f.f21720a;
        int i = this.f21728f.f21722c;
        int i2 = (z ? 11 : 14) + (i << 2);
        int[] iArr = new int[i2];
        boolean[] zArr = new boolean[((z ? 88 : 112) + (i << 4)) * i];
        int i3 = 2;
        if (z) {
            for (int i4 = 0; i4 < iArr.length; i4++) {
                iArr[i4] = i4;
            }
        } else {
            int i5 = i2 / 2;
            int i6 = ((i2 + 1) + (((i5 - 1) / 15) * 2)) / 2;
            for (int i7 = 0; i7 < i5; i7++) {
                int i8 = (i7 / 15) + i7;
                iArr[(i5 - i7) - 1] = (i6 - i8) - 1;
                iArr[i5 + i7] = i8 + i6 + 1;
            }
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < i) {
            int i11 = ((i - i9) << i3) + (z ? 9 : 12);
            int i12 = i9 << 1;
            int i13 = (i2 - 1) - i12;
            int i14 = 0;
            while (i14 < i11) {
                int i15 = i14 << 1;
                int i16 = 0;
                while (i16 < i3) {
                    int i17 = i12 + i16;
                    int i18 = i12 + i14;
                    zArr[i10 + i15 + i16] = hyVar.m2519a(iArr[i17], iArr[i18]);
                    int i19 = i13 - i16;
                    zArr[(i11 * 2) + i10 + i15 + i16] = hyVar.m2519a(iArr[i18], iArr[i19]);
                    int i20 = i13 - i14;
                    zArr[(i11 * 4) + i10 + i15 + i16] = hyVar.m2519a(iArr[i19], iArr[i20]);
                    zArr[(i11 * 6) + i10 + i15 + i16] = hyVar.m2519a(iArr[i20], iArr[i17]);
                    i16++;
                    i = i;
                    z = z;
                    i3 = 2;
                }
                i14++;
                i3 = 2;
            }
            i10 += i11 << 3;
            i9++;
            i3 = 2;
        }
        return zArr;
    }

    /* renamed from: a */
    private static int m2802a(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }

    /* renamed from: a */
    private static byte m2803a(boolean[] zArr, int i) {
        int length = zArr.length - i;
        if (length >= 8) {
            return (byte) m2802a(zArr, i, 8);
        }
        return (byte) (m2802a(zArr, i, length) << (8 - length));
    }

    /* renamed from: d */
    private static byte[] m2799d(boolean[] zArr) {
        int i;
        byte[] bArr = new byte[(zArr.length + 7) / 8];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i2 << 3;
            int length = zArr.length - i3;
            if (length >= 8) {
                i = m2802a(zArr, i3, 8);
            } else {
                i = m2802a(zArr, i3, length) << (8 - length);
            }
            bArr[i2] = (byte) i;
        }
        return bArr;
    }
}
