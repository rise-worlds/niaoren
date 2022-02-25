package p110z1;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import p110z1.HighLevelEncoder;

/* renamed from: z1.gc */
/* loaded from: classes3.dex */
public final class Encoder {

    /* renamed from: a */
    public static final int f21755a = 33;

    /* renamed from: b */
    public static final int f21756b = 0;

    /* renamed from: c */
    private static final int f21757c = 32;

    /* renamed from: d */
    private static final int f21758d = 4;

    /* renamed from: e */
    private static final int[] f21759e = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    /* renamed from: a */
    private static int m2762a(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    private Encoder() {
    }

    /* renamed from: a */
    private static AztecCode m2756a(byte[] bArr) {
        return m2755a(bArr, 33, 0);
    }

    /* renamed from: a */
    public static AztecCode m2755a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        boolean z;
        int i6;
        int i7;
        BitArray huVar;
        BitArray huVar2;
        int i8;
        HighLevelEncoder gdVar = new HighLevelEncoder(bArr);
        Collection<State> singletonList = Collections.singletonList(State.f21773a);
        int i9 = 0;
        while (true) {
            i3 = 5;
            i3 = 3;
            i3 = 4;
            i4 = 32;
            i3 = 2;
            if (i9 >= gdVar.f21769i.length) {
                break;
            }
            int i10 = i9 + 1;
            byte b = i10 < gdVar.f21769i.length ? gdVar.f21769i[i10] : (byte) 0;
            byte b2 = gdVar.f21769i[i9];
            if (b2 != 13) {
                if (b2 != 44) {
                    if (b2 != 46) {
                        if (b2 != 58) {
                            i3 = 0;
                        } else if (b != 32) {
                            i3 = 0;
                        }
                    } else if (b != 32) {
                        i3 = 0;
                    }
                } else if (b != 32) {
                    i3 = 0;
                }
            } else if (b != 10) {
                i3 = 0;
            }
            if (i3 > 0) {
                singletonList = HighLevelEncoder.m2750a(singletonList, i9, i3);
                i9 = i10;
            } else {
                LinkedList linkedList = new LinkedList();
                for (State gfVar : singletonList) {
                    gdVar.m2748a(gfVar, i9, linkedList);
                }
                singletonList = HighLevelEncoder.m2752a(linkedList);
            }
            i9++;
        }
        BitArray a = ((State) Collections.min(singletonList, new HighLevelEncoder.C53621())).m2742a(gdVar.f21769i);
        int i11 = 11;
        int i12 = ((a.f21908b * i) / 100) + 11;
        int i13 = a.f21908b + i12;
        if (i2 != 0) {
            boolean z2 = i2 < 0;
            int abs = Math.abs(i2);
            if (z2) {
                i4 = 4;
            }
            if (abs <= i4) {
                int a2 = m2762a(abs, z2);
                int i14 = f21759e[abs];
                int i15 = a2 - (a2 % i14);
                huVar = m2761a(a, i14);
                if (huVar.f21908b + i12 > i15) {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                } else if (!z2 || huVar.f21908b <= (i14 << 6)) {
                    z = z2;
                    i5 = abs;
                    i7 = a2;
                    i6 = i14;
                } else {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            } else {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
        } else {
            BitArray huVar3 = null;
            int i16 = 0;
            i6 = 0;
            while (i16 <= i4) {
                z = i16 <= i3;
                i5 = z ? i16 + 1 : i16;
                i7 = m2762a(i5, z);
                if (i13 <= i7) {
                    if (huVar3 == null || i6 != f21759e[i5]) {
                        int i17 = f21759e[i5];
                        huVar3 = m2761a(a, i17);
                        i6 = i17;
                    }
                    int i18 = i7 - (i7 % i6);
                    if ((!z || huVar3.f21908b <= (i6 << 6)) && huVar3.f21908b + i12 <= i18) {
                        huVar = huVar3;
                    }
                }
                i16++;
                i3 = 5;
                i3 = 3;
                i4 = 32;
                i3 = 2;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray a3 = m2760a(huVar, i7, i6);
        int i19 = huVar.f21908b / i6;
        BitArray huVar4 = new BitArray();
        if (z) {
            huVar4.m2544b(i5 - 1, i3);
            huVar4.m2544b(i19 - 1, 6);
            huVar2 = m2760a(huVar4, 28, 4);
        } else {
            huVar4.m2544b(i5 - 1, i3);
            huVar4.m2544b(i19 - 1, 11);
            huVar2 = m2760a(huVar4, 40, 4);
        }
        if (!z) {
            i11 = 14;
        }
        int i20 = i11 + (i5 << 2);
        int[] iArr = new int[i20];
        if (z) {
            for (int i21 = 0; i21 < iArr.length; i21++) {
                iArr[i21] = i21;
            }
            i8 = i20;
        } else {
            int i22 = i20 / 2;
            i8 = i20 + 1 + (((i22 - 1) / 15) * 2);
            int i23 = i8 / 2;
            for (int i24 = 0; i24 < i22; i24++) {
                int i25 = (i24 / 15) + i24;
                iArr[(i22 - i24) - 1] = (i23 - i25) - 1;
                iArr[i22 + i24] = i25 + i23 + 1;
            }
        }
        BitMatrix hyVar = new BitMatrix(i8);
        int i26 = 0;
        int i27 = 0;
        while (i26 < i5) {
            int i28 = ((i5 - i26) << i3) + (z ? 9 : 12);
            int i29 = 0;
            while (i29 < i28) {
                int i30 = i29 << 1;
                int i31 = 0;
                while (i31 < i3) {
                    if (a3.m2551a(i27 + i30 + i31)) {
                        int i32 = i26 << 1;
                        i19 = i19;
                        hyVar.m2511b(iArr[i32 + i31], iArr[i32 + i29]);
                    } else {
                        i19 = i19;
                    }
                    if (a3.m2551a((i28 << 1) + i27 + i30 + i31)) {
                        int i33 = i26 << 1;
                        hyVar.m2511b(iArr[i33 + i29], iArr[((i20 - 1) - i33) - i31]);
                    }
                    if (a3.m2551a((i28 << 2) + i27 + i30 + i31)) {
                        int i34 = (i20 - 1) - (i26 << 1);
                        hyVar.m2511b(iArr[i34 - i31], iArr[i34 - i29]);
                    }
                    if (a3.m2551a((i28 * 6) + i27 + i30 + i31)) {
                        int i35 = i26 << 1;
                        hyVar.m2511b(iArr[((i20 - 1) - i35) - i29], iArr[i35 + i31]);
                    }
                    i31++;
                    i3 = 2;
                }
                i29++;
                i3 = 2;
            }
            i27 += i28 << 3;
            i26++;
            i19 = i19;
            i3 = 2;
        }
        m2758a(hyVar, z, i8, huVar2);
        if (z) {
            m2759a(hyVar, i8 / 2, 5);
        } else {
            int i36 = i8 / 2;
            m2759a(hyVar, i36, 7);
            int i37 = 0;
            int i38 = 0;
            while (i37 < (i20 / 2) - 1) {
                for (int i39 = i36 & 1; i39 < i8; i39 += 2) {
                    int i40 = i36 - i38;
                    hyVar.m2511b(i40, i39);
                    int i41 = i36 + i38;
                    hyVar.m2511b(i41, i39);
                    hyVar.m2511b(i39, i40);
                    hyVar.m2511b(i39, i41);
                }
                i37 += 15;
                i38 += 16;
            }
        }
        AztecCode gaVar = new AztecCode();
        gaVar.f21748a = z;
        gaVar.f21749b = i8;
        gaVar.f21750c = i5;
        gaVar.f21751d = i19;
        gaVar.f21752e = hyVar;
        return gaVar;
    }

    /* renamed from: a */
    private static void m2759a(BitMatrix hyVar, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 <= i6) {
                    hyVar.m2511b(i5, i4);
                    hyVar.m2511b(i5, i6);
                    hyVar.m2511b(i4, i5);
                    hyVar.m2511b(i6, i5);
                    i5++;
                }
            }
        }
        int i7 = i - i2;
        hyVar.m2511b(i7, i7);
        int i8 = i7 + 1;
        hyVar.m2511b(i8, i7);
        hyVar.m2511b(i7, i8);
        int i9 = i + i2;
        hyVar.m2511b(i9, i7);
        hyVar.m2511b(i9, i8);
        hyVar.m2511b(i9, i9 - 1);
    }

    /* renamed from: a */
    private static BitArray m2757a(boolean z, int i, int i2) {
        BitArray huVar = new BitArray();
        if (z) {
            huVar.m2544b(i - 1, 2);
            huVar.m2544b(i2 - 1, 6);
            return m2760a(huVar, 28, 4);
        }
        huVar.m2544b(i - 1, 5);
        huVar.m2544b(i2 - 1, 11);
        return m2760a(huVar, 40, 4);
    }

    /* renamed from: a */
    private static void m2758a(BitMatrix hyVar, boolean z, int i, BitArray huVar) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (huVar.m2551a(i3)) {
                    hyVar.m2511b(i4, i2 - 5);
                }
                if (huVar.m2551a(i3 + 7)) {
                    hyVar.m2511b(i2 + 5, i4);
                }
                if (huVar.m2551a(20 - i3)) {
                    hyVar.m2511b(i4, i2 + 5);
                }
                if (huVar.m2551a(27 - i3)) {
                    hyVar.m2511b(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (huVar.m2551a(i3)) {
                hyVar.m2511b(i5, i2 - 7);
            }
            if (huVar.m2551a(i3 + 10)) {
                hyVar.m2511b(i2 + 7, i5);
            }
            if (huVar.m2551a(29 - i3)) {
                hyVar.m2511b(i5, i2 + 7);
            }
            if (huVar.m2551a(39 - i3)) {
                hyVar.m2511b(i2 - 7, i5);
            }
            i3++;
        }
    }

    /* renamed from: b */
    private static int[] m2754b(BitArray huVar, int i, int i2) {
        int[] iArr = new int[i2];
        int i3 = huVar.f21908b / i;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                i5 |= huVar.m2551a((i4 * i) + i6) ? 1 << ((i - i6) - 1) : 0;
            }
            iArr[i4] = i5;
        }
        return iArr;
    }

    /* renamed from: a */
    private static GenericGF m2763a(int i) {
        if (i == 4) {
            return GenericGF.f21927d;
        }
        if (i == 6) {
            return GenericGF.f21926c;
        }
        if (i == 8) {
            return GenericGF.f21930g;
        }
        if (i == 10) {
            return GenericGF.f21925b;
        }
        if (i == 12) {
            return GenericGF.f21924a;
        }
        throw new IllegalArgumentException("Unsupported word size ".concat(String.valueOf(i)));
    }

    /* renamed from: a */
    private static BitArray m2761a(BitArray huVar, int i) {
        BitArray huVar2 = new BitArray();
        int i2 = huVar.f21908b;
        int i3 = (1 << i) - 2;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                int i7 = i4 + i6;
                if (i7 >= i2 || huVar.m2551a(i7)) {
                    i5 |= 1 << ((i - 1) - i6);
                }
            }
            int i8 = i5 & i3;
            if (i8 == i3) {
                huVar2.m2544b(i8, i);
                i4--;
            } else if (i8 == 0) {
                huVar2.m2544b(i5 | 1, i);
                i4--;
            } else {
                huVar2.m2544b(i5, i);
            }
            i4 += i;
        }
        return huVar2;
    }

    /* renamed from: a */
    private static BitArray m2760a(BitArray huVar, int i, int i2) {
        GenericGF hzVar;
        int i3 = huVar.f21908b / i2;
        if (i2 == 4) {
            hzVar = GenericGF.f21927d;
        } else if (i2 == 6) {
            hzVar = GenericGF.f21926c;
        } else if (i2 == 8) {
            hzVar = GenericGF.f21930g;
        } else if (i2 == 10) {
            hzVar = GenericGF.f21925b;
        } else if (i2 == 12) {
            hzVar = GenericGF.f21924a;
        } else {
            throw new IllegalArgumentException("Unsupported word size ".concat(String.valueOf(i2)));
        }
        ReedSolomonEncoder icVar = new ReedSolomonEncoder(hzVar);
        int i4 = i / i2;
        int[] b = m2754b(huVar, i2, i4);
        icVar.m2468a(b, i4 - i3);
        BitArray huVar2 = new BitArray();
        huVar2.m2544b(0, i % i2);
        for (int i5 : b) {
            huVar2.m2544b(i5, i2);
        }
        return huVar2;
    }
}
