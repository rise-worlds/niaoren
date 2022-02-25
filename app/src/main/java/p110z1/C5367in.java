package p110z1;

import java.nio.charset.Charset;
import java.util.Map;

/* compiled from: StringUtils.java */
/* renamed from: z1.in */
/* loaded from: classes3.dex */
public final class C5367in {

    /* renamed from: a */
    public static final String f22023a = "SJIS";

    /* renamed from: b */
    public static final String f22024b = "GB2312";

    /* renamed from: c */
    private static final String f22025c = Charset.defaultCharset().name();

    /* renamed from: d */
    private static final String f22026d = "EUC_JP";

    /* renamed from: e */
    private static final String f22027e = "UTF8";

    /* renamed from: f */
    private static final String f22028f = "ISO8859_1";

    /* renamed from: g */
    private static final boolean f22029g;

    static {
        f22029g = f22023a.equalsIgnoreCase(f22025c) || f22026d.equalsIgnoreCase(f22025c);
    }

    private C5367in() {
    }

    /* renamed from: a */
    public static String m2422a(byte[] bArr, Map<DecodeHintType, ?> map) {
        byte[] bArr2 = bArr;
        if (map != null && map.containsKey(DecodeHintType.CHARACTER_SET)) {
            return map.get(DecodeHintType.CHARACTER_SET).toString();
        }
        int length = bArr2.length;
        int i = 0;
        boolean z = bArr2.length > 3 && bArr2[0] == -17 && bArr2[1] == -69 && bArr2[2] == -65;
        int i2 = 0;
        int i3 = 0;
        boolean z2 = true;
        boolean z3 = true;
        boolean z4 = true;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i3 < length && (z2 || z3 || z4)) {
            int i13 = bArr2[i3] & 255;
            if (z4) {
                if (i4 > 0) {
                    if ((i13 & 128) != 0) {
                        i4--;
                    }
                    z4 = false;
                } else if ((i13 & 128) != 0) {
                    if ((i13 & 64) != 0) {
                        i4++;
                        if ((i13 & 32) == 0) {
                            i6++;
                        } else {
                            i4++;
                            if ((i13 & 16) == 0) {
                                i7++;
                            } else {
                                i4++;
                                if ((i13 & 8) == 0) {
                                    i8++;
                                }
                            }
                        }
                    }
                    z4 = false;
                }
            }
            if (z2) {
                if (i13 > 127 && i13 < 160) {
                    z2 = false;
                } else if (i13 > 159 && (i13 < 192 || i13 == 215 || i13 == 247)) {
                    i10++;
                }
            }
            if (z3) {
                if (i5 > 0) {
                    if (i13 < 64 || i13 == 127 || i13 > 252) {
                        z3 = false;
                    } else {
                        i5--;
                    }
                } else if (i13 == 128 || i13 == 160 || i13 > 239) {
                    z3 = false;
                } else if (i13 > 160 && i13 < 224) {
                    i2++;
                    int i14 = i12 + 1;
                    if (i14 > i9) {
                        i9 = i14;
                        i12 = i9;
                        i11 = 0;
                    } else {
                        i12 = i14;
                        i11 = 0;
                    }
                } else if (i13 > 127) {
                    i5++;
                    int i15 = i11 + 1;
                    if (i15 > i) {
                        i = i15;
                        i11 = i;
                        i12 = 0;
                    } else {
                        i11 = i15;
                        i12 = 0;
                    }
                } else {
                    i11 = 0;
                    i12 = 0;
                }
            }
            i3++;
            bArr2 = bArr;
        }
        if (z4 && i4 > 0) {
            z4 = false;
        }
        if (z3 && i5 > 0) {
            z3 = false;
        }
        return (!z4 || (!z && (i6 + i7) + i8 <= 0)) ? (!z3 || (!f22029g && i9 < 3 && i < 3)) ? (!z2 || !z3) ? z2 ? f22028f : z3 ? f22023a : z4 ? f22027e : f22025c : (!(i9 == 2 && i2 == 2) && i10 * 10 < length) ? f22028f : f22023a : f22023a : f22027e;
    }
}
