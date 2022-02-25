package p110z1;

import java.util.Map;
import p110z1.C5411np;

/* compiled from: Decoder.java */
/* renamed from: z1.nk */
/* loaded from: classes3.dex */
public final class C5410nk {

    /* renamed from: a */
    private final ReedSolomonDecoder f22594a = new ReedSolomonDecoder(GenericGF.f21928e);

    /* renamed from: b */
    private DecoderResult m1824b(boolean[][] zArr) throws ChecksumException, FormatException {
        return m1828a(BitMatrix.m2513a(zArr), (Map<DecodeHintType, ?>) null);
    }

    /* renamed from: a */
    private DecoderResult m1829a(BitMatrix hyVar) throws ChecksumException, FormatException {
        return m1828a(hyVar, (Map<DecodeHintType, ?>) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0015 A[Catch: io | md -> 0x006e, TryCatch #0 {io | md -> 0x006e, blocks: (B:9:0x0011, B:11:0x0015, B:12:0x0028, B:13:0x0036, B:15:0x003c, B:16:0x003f, B:18:0x0045, B:20:0x0053, B:21:0x005d, B:23:0x0062), top: B:29:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003c A[Catch: io | md -> 0x006e, TryCatch #0 {io | md -> 0x006e, blocks: (B:9:0x0011, B:11:0x0015, B:12:0x0028, B:13:0x0036, B:15:0x003c, B:16:0x003f, B:18:0x0045, B:20:0x0053, B:21:0x005d, B:23:0x0062), top: B:29:0x0011 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p110z1.DecoderResult m1828a(p110z1.BitMatrix r8, java.util.Map<p110z1.DecodeHintType, ?> r9) throws p110z1.FormatException, p110z1.ChecksumException {
        /*
            r7 = this;
            z1.ng r0 = new z1.ng
            r0.<init>(r8)
            r8 = 0
            z1.ig r8 = r7.m1827a(r0, r9)     // Catch: io -> 0x000b, md -> 0x000f
            return r8
        L_0x000b:
            r1 = move-exception
            r2 = r1
            r1 = r8
            goto L_0x0011
        L_0x000f:
            r1 = move-exception
            r2 = r8
        L_0x0011:
            z1.nm r3 = r0.f22578c     // Catch: md | io -> 0x006e
            if (r3 == 0) goto L_0x0028
            z1.ni[] r3 = p110z1.DataMask.values()     // Catch: md | io -> 0x006e
            z1.nm r4 = r0.f22578c     // Catch: md | io -> 0x006e
            byte r4 = r4.f22605b     // Catch: md | io -> 0x006e
            r3 = r3[r4]     // Catch: md | io -> 0x006e
            z1.hy r4 = r0.f22576a     // Catch: md | io -> 0x006e
            int r4 = r4.f21921b     // Catch: md | io -> 0x006e
            z1.hy r5 = r0.f22576a     // Catch: md | io -> 0x006e
            r3.m1839a(r5, r4)     // Catch: md | io -> 0x006e
        L_0x0028:
            r0.f22577b = r8     // Catch: md | io -> 0x006e
            r0.f22578c = r8     // Catch: md | io -> 0x006e
            r8 = 1
            r0.f22579d = r8     // Catch: md | io -> 0x006e
            r0.m1847b()     // Catch: md | io -> 0x006e
            r0.m1849a()     // Catch: md | io -> 0x006e
            r8 = 0
        L_0x0036:
            z1.hy r3 = r0.f22576a     // Catch: md | io -> 0x006e
            int r3 = r3.f21920a     // Catch: md | io -> 0x006e
            if (r8 >= r3) goto L_0x0062
            int r3 = r8 + 1
            r4 = r3
        L_0x003f:
            z1.hy r5 = r0.f22576a     // Catch: md | io -> 0x006e
            int r5 = r5.f21921b     // Catch: md | io -> 0x006e
            if (r4 >= r5) goto L_0x0060
            z1.hy r5 = r0.f22576a     // Catch: md | io -> 0x006e
            boolean r5 = r5.m2519a(r8, r4)     // Catch: md | io -> 0x006e
            z1.hy r6 = r0.f22576a     // Catch: md | io -> 0x006e
            boolean r6 = r6.m2519a(r4, r8)     // Catch: md | io -> 0x006e
            if (r5 == r6) goto L_0x005d
            z1.hy r5 = r0.f22576a     // Catch: md | io -> 0x006e
            r5.m2507c(r4, r8)     // Catch: md | io -> 0x006e
            z1.hy r5 = r0.f22576a     // Catch: md | io -> 0x006e
            r5.m2507c(r8, r4)     // Catch: md | io -> 0x006e
        L_0x005d:
            int r4 = r4 + 1
            goto L_0x003f
        L_0x0060:
            r8 = r3
            goto L_0x0036
        L_0x0062:
            z1.ig r8 = r7.m1827a(r0, r9)     // Catch: md | io -> 0x006e
            z1.no r9 = new z1.no     // Catch: md | io -> 0x006e
            r9.<init>()     // Catch: md | io -> 0x006e
            r8.f21996h = r9     // Catch: md | io -> 0x006e
            return r8
        L_0x006e:
            if (r1 == 0) goto L_0x0072
            throw r1
        L_0x0072:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5410nk.m1828a(z1.hy, java.util.Map):z1.ig");
    }

    /* renamed from: a */
    private DecoderResult m1827a(C5398ng ngVar, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        int i;
        int i2;
        int i3;
        int i4;
        C5411np b = ngVar.m1847b();
        ErrorCorrectionLevel nlVar = ngVar.m1849a().f22604a;
        FormatInformation a = ngVar.m1849a();
        C5411np b2 = ngVar.m1847b();
        DataMask niVar = DataMask.values()[a.f22605b];
        int i5 = ngVar.f22576a.f21921b;
        niVar.m1839a(ngVar.f22576a, i5);
        int a2 = b2.m1811a();
        BitMatrix hyVar = new BitMatrix(a2);
        hyVar.m2518a(0, 0, 9, 9);
        int i6 = a2 - 8;
        hyVar.m2518a(i6, 0, 8, 9);
        hyVar.m2518a(0, i6, 9, 8);
        int length = b2.f22623b.length;
        int i7 = 0;
        while (true) {
            i = 2;
            if (i7 >= length) {
                break;
            }
            int i8 = b2.f22623b[i7] - 2;
            for (int i9 = 0; i9 < length; i9++) {
                if (!((i7 == 0 && (i9 == 0 || i9 == length - 1)) || (i7 == length - 1 && i9 == 0))) {
                    hyVar.m2518a(b2.f22623b[i9] - 2, i8, 5, 5);
                }
            }
            i7++;
        }
        int i10 = a2 - 17;
        int i11 = 6;
        hyVar.m2518a(6, 9, 1, i10);
        hyVar.m2518a(9, 6, i10, 1);
        if (b2.f22622a > 6) {
            int i12 = a2 - 11;
            hyVar.m2518a(i12, 0, 3, 6);
            hyVar.m2518a(0, i12, 6, 3);
        }
        byte[] bArr = new byte[b2.f22624c];
        int i13 = i5 - 1;
        int i14 = i13;
        int i15 = 0;
        boolean z = true;
        int i16 = 0;
        int i17 = 0;
        while (i14 > 0) {
            if (i14 == i11) {
                i14--;
            }
            int i18 = i15;
            int i19 = 0;
            while (i19 < i5) {
                int i20 = z ? i13 - i19 : i19;
                int i21 = i17;
                int i22 = i16;
                int i23 = i18;
                int i24 = 0;
                while (i24 < i) {
                    int i25 = i14 - i24;
                    if (!hyVar.m2519a(i25, i20)) {
                        int i26 = i22 + 1;
                        int i27 = i21 << 1;
                        if (ngVar.f22576a.m2519a(i25, i20)) {
                            i4 = i27 | 1;
                            i3 = 8;
                        } else {
                            i4 = i27;
                            i3 = 8;
                        }
                        if (i26 == i3) {
                            i23++;
                            bArr[i23] = (byte) i4;
                            i22 = 0;
                            i21 = 0;
                        } else {
                            i21 = i4;
                            i22 = i26;
                        }
                    }
                    i24++;
                    i = 2;
                }
                i19++;
                i18 = i23;
                i16 = i22;
                i17 = i21;
                i = 2;
            }
            z = !z;
            i14 -= 2;
            i15 = i18;
            i16 = i16;
            i17 = i17;
            i11 = 6;
            i = 2;
        }
        if (i15 != b2.f22624c) {
            throw FormatException.m2059a();
        } else if (bArr.length == b.f22624c) {
            C5411np.C5413b a3 = b.m1809a(nlVar);
            C5411np.C5412a[] aVarArr = a3.f22629b;
            int i28 = 0;
            for (C5411np.C5412a aVar : aVarArr) {
                i28 += aVar.f22626a;
            }
            C5399nh[] nhVarArr = new C5399nh[i28];
            int length2 = aVarArr.length;
            int i29 = 0;
            int i30 = 0;
            while (i29 < length2) {
                C5411np.C5412a aVar2 = aVarArr[i29];
                int i31 = i30;
                for (int i32 = 0; i32 < aVar2.f22626a; i32++) {
                    int i33 = aVar2.f22627b;
                    i31++;
                    nhVarArr[i31] = new C5399nh(i33, new byte[a3.f22628a + i33]);
                }
                i29++;
                i30 = i31;
            }
            int length3 = nhVarArr[0].f22581b.length;
            int length4 = nhVarArr.length - 1;
            while (true) {
                if (length4 < 0) {
                    i2 = 1;
                    break;
                }
                if (nhVarArr[length4].f22581b.length == length3) {
                    i2 = 1;
                    break;
                }
                length4--;
            }
            int i34 = length4 + i2;
            int i35 = length3 - a3.f22628a;
            int i36 = 0;
            int i37 = 0;
            while (i36 < i35) {
                int i38 = i37;
                for (int i39 = 0; i39 < i30; i39++) {
                    i38++;
                    nhVarArr[i39].f22581b[i36] = bArr[i38];
                }
                i36++;
                i37 = i38;
            }
            for (int i40 = i34; i40 < i30; i40++) {
                i37++;
                nhVarArr[i40].f22581b[i35] = bArr[i37];
            }
            int length5 = nhVarArr[0].f22581b.length;
            while (i35 < length5) {
                int i41 = 0;
                while (i41 < i30) {
                    i37++;
                    nhVarArr[i41].f22581b[i41 < i34 ? i35 : i35 + 1] = bArr[i37];
                    i41++;
                }
                i35++;
                i37 = i37;
            }
            int i42 = 0;
            for (C5399nh nhVar : nhVarArr) {
                i42 += nhVar.f22580a;
            }
            byte[] bArr2 = new byte[i42];
            int length6 = nhVarArr.length;
            int i43 = 0;
            int i44 = 0;
            while (i43 < length6) {
                C5399nh nhVar2 = nhVarArr[i43];
                byte[] bArr3 = nhVar2.f22581b;
                int i45 = nhVar2.f22580a;
                m1826a(bArr3, i45);
                for (int i46 = 0; i46 < i45; i46++) {
                    i44++;
                    bArr2[i44] = bArr3[i46];
                }
                i43++;
                i44 = i44;
            }
            return C5408nj.m1832a(bArr2, b, nlVar, map);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: a */
    private void m1826a(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f22594a.m2470a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.m2421a();
        }
    }

    /* renamed from: a */
    private DecoderResult m1825a(boolean[][] zArr) throws ChecksumException, FormatException {
        return m1828a(BitMatrix.m2513a(zArr), (Map<DecodeHintType, ?>) null);
    }
}
