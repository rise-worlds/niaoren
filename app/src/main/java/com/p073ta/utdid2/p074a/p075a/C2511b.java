package com.p073ta.utdid2.p074a.p075a;

import android.annotation.SuppressLint;
import com.tendcloud.tenddata.C2771ci;
import java.io.UnsupportedEncodingException;
import org.apache.tools.tar.TarConstants;

/* renamed from: com.ta.utdid2.a.a.b */
/* loaded from: classes2.dex */
public class C2511b {

    /* renamed from: a */
    static final /* synthetic */ boolean f12667a = !C2511b.class.desiredAssertionStatus();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ta.utdid2.a.a.b$a */
    /* loaded from: classes2.dex */
    public static abstract class AbstractC2512a {

        /* renamed from: a */
        public int f12668a;

        /* renamed from: a */
        public byte[] f12669a;

        AbstractC2512a() {
        }
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        C2513b bVar = new C2513b(i3, new byte[(i2 * 3) / 4]);
        if (!bVar.m17179a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (bVar.f12668a == bVar.f12669a.length) {
            return bVar.f12669a;
        } else {
            byte[] bArr2 = new byte[bVar.f12668a];
            System.arraycopy(bVar.f12669a, 0, bArr2, 0, bVar.f12668a);
            return bArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ta.utdid2.a.a.b$b */
    /* loaded from: classes2.dex */
    public static class C2513b extends AbstractC2512a {

        /* renamed from: a */
        private static final int[] f12670a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* renamed from: b */
        private static final int[] f12671b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* renamed from: c */
        private final int[] f12672c;
        private int state;
        private int value;

        public C2513b(int i, byte[] bArr) {
            this.f12669a = bArr;
            this.f12672c = (i & 8) == 0 ? f12670a : f12671b;
            this.state = 0;
            this.value = 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:52:0x00e4  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00eb  */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean m17179a(byte[] r12, int r13, int r14, boolean r15) {
            /*
                Method dump skipped, instructions count: 304
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p073ta.utdid2.p074a.p075a.C2511b.C2513b.m17179a(byte[], int, int, boolean):boolean");
        }
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    @SuppressLint({"Assert"})
    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        C2514c cVar = new C2514c(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!cVar.f12677b) {
            switch (i2 % 3) {
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (cVar.f12678c && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (cVar.f12679d ? 2 : 1);
        }
        cVar.f12669a = new byte[i4];
        cVar.m17178a(bArr, i, i2, true);
        if (f12667a || cVar.f12668a == i4) {
            return cVar.f12669a;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ta.utdid2.a.a.b$c */
    /* loaded from: classes2.dex */
    public static class C2514c extends AbstractC2512a {

        /* renamed from: a */
        static final /* synthetic */ boolean f12673a = !C2511b.class.desiredAssertionStatus();

        /* renamed from: b */
        private static final byte[] f12674b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 43, 47};

        /* renamed from: c */
        private static final byte[] f12675c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 45, 95};

        /* renamed from: b */
        int f12676b;

        /* renamed from: b */
        public final boolean f12677b;

        /* renamed from: c */
        public final boolean f12678c;
        private int count;

        /* renamed from: d */
        public final boolean f12679d;

        /* renamed from: d */
        private final byte[] f12680d;

        /* renamed from: e */
        private final byte[] f12681e;

        public C2514c(int i, byte[] bArr) {
            this.f12669a = bArr;
            boolean z = true;
            this.f12677b = (i & 1) == 0;
            this.f12678c = (i & 2) == 0;
            this.f12679d = (i & 4) == 0 ? false : z;
            this.f12681e = (i & 8) == 0 ? f12674b : f12675c;
            this.f12680d = new byte[2];
            this.f12676b = 0;
            this.count = this.f12678c ? 19 : -1;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* renamed from: a */
        public boolean m17178a(byte[] bArr, int i, int i2, boolean z) {
            int i3;
            int i4;
            int i5;
            int i6;
            byte b;
            int i7;
            byte b2;
            byte b3;
            int i8;
            int i9;
            int i10;
            byte[] bArr2 = this.f12681e;
            byte[] bArr3 = this.f12669a;
            int i11 = this.count;
            int i12 = i2 + i;
            int i13 = 0;
            switch (this.f12676b) {
                case 0:
                default:
                    i3 = i;
                    i4 = -1;
                    break;
                case 1:
                    if (i + 2 <= i12) {
                        int i14 = i + 1;
                        i3 = i14 + 1;
                        i4 = ((this.f12680d[0] & 255) << 16) | ((bArr[i] & 255) << 8) | (bArr[i14] & 255);
                        this.f12676b = 0;
                        break;
                    }
                    i3 = i;
                    i4 = -1;
                    break;
                case 2:
                    int i15 = i + 1;
                    if (i15 <= i12) {
                        byte[] bArr4 = this.f12680d;
                        i4 = ((bArr4[1] & 255) << 8) | ((bArr4[0] & 255) << 16) | (bArr[i] & 255);
                        this.f12676b = 0;
                        i3 = i15;
                        break;
                    }
                    i3 = i;
                    i4 = -1;
                    break;
            }
            if (i4 != -1) {
                bArr3[0] = bArr2[(i4 >> 18) & 63];
                bArr3[1] = bArr2[(i4 >> 12) & 63];
                bArr3[2] = bArr2[(i4 >> 6) & 63];
                bArr3[3] = bArr2[i4 & 63];
                i11--;
                if (i11 == 0) {
                    if (this.f12679d) {
                        i10 = 5;
                        bArr3[4] = C2771ci.f13672f;
                    } else {
                        i10 = 4;
                    }
                    i5 = i10 + 1;
                    bArr3[i10] = 10;
                    i11 = 19;
                } else {
                    i5 = 4;
                }
            } else {
                i5 = 0;
            }
            while (true) {
                int i16 = i3 + 3;
                if (i16 <= i12) {
                    int i17 = ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3] & 255) << 16) | (bArr[i3 + 2] & 255);
                    bArr3[i5] = bArr2[(i17 >> 18) & 63];
                    bArr3[i5 + 1] = bArr2[(i17 >> 12) & 63];
                    bArr3[i5 + 2] = bArr2[(i17 >> 6) & 63];
                    bArr3[i5 + 3] = bArr2[i17 & 63];
                    i5 += 4;
                    i11--;
                    if (i11 == 0) {
                        if (this.f12679d) {
                            i9 = i5 + 1;
                            bArr3[i5] = C2771ci.f13672f;
                        } else {
                            i9 = i5;
                        }
                        i5 = i9 + 1;
                        bArr3[i9] = 10;
                        i3 = i16;
                        i11 = 19;
                    } else {
                        i3 = i16;
                    }
                } else {
                    if (z) {
                        int i18 = this.f12676b;
                        if (i3 - i18 == i12 - 1) {
                            if (i18 > 0) {
                                b3 = this.f12680d[0];
                                i13 = 1;
                            } else {
                                i3++;
                                b3 = bArr[i3];
                            }
                            int i19 = (b3 & 255) << 4;
                            this.f12676b -= i13;
                            int i20 = i5 + 1;
                            bArr3[i5] = bArr2[(i19 >> 6) & 63];
                            i5 = i20 + 1;
                            bArr3[i20] = bArr2[i19 & 63];
                            if (this.f12677b) {
                                int i21 = i5 + 1;
                                bArr3[i5] = 61;
                                i5 = i21 + 1;
                                bArr3[i21] = 61;
                            }
                            if (this.f12678c) {
                                if (this.f12679d) {
                                    i8 = i5 + 1;
                                    bArr3[i5] = C2771ci.f13672f;
                                } else {
                                    i8 = i5;
                                }
                                i5 = i8 + 1;
                                bArr3[i8] = 10;
                            }
                        } else if (i3 - i18 == i12 - 2) {
                            if (i18 > 1) {
                                b = this.f12680d[0];
                                i13 = 1;
                            } else {
                                i3++;
                                b = bArr[i3];
                            }
                            int i22 = (b & 255) << 10;
                            if (this.f12676b > 0) {
                                i7 = i13 + 1;
                                b2 = this.f12680d[i13];
                            } else {
                                i3++;
                                b2 = bArr[i3];
                                i7 = i13;
                            }
                            int i23 = i22 | ((b2 & 255) << 2);
                            this.f12676b -= i7;
                            int i24 = i5 + 1;
                            bArr3[i5] = bArr2[(i23 >> 12) & 63];
                            int i25 = i24 + 1;
                            bArr3[i24] = bArr2[(i23 >> 6) & 63];
                            int i26 = i25 + 1;
                            bArr3[i25] = bArr2[i23 & 63];
                            if (this.f12677b) {
                                i5 = i26 + 1;
                                bArr3[i26] = 61;
                            } else {
                                i5 = i26;
                            }
                            if (this.f12678c) {
                                if (this.f12679d) {
                                    i5++;
                                    bArr3[i5] = C2771ci.f13672f;
                                }
                                i5++;
                                bArr3[i5] = 10;
                            }
                        } else if (this.f12678c && i5 > 0 && i11 != 19) {
                            if (this.f12679d) {
                                i6 = i5 + 1;
                                bArr3[i5] = C2771ci.f13672f;
                            } else {
                                i6 = i5;
                            }
                            i5 = i6 + 1;
                            bArr3[i6] = 10;
                        }
                        if (!f12673a && this.f12676b != 0) {
                            throw new AssertionError();
                        } else if (!f12673a && i3 != i12) {
                            throw new AssertionError();
                        }
                    } else if (i3 == i12 - 1) {
                        byte[] bArr5 = this.f12680d;
                        int i27 = this.f12676b;
                        this.f12676b = i27 + 1;
                        bArr5[i27] = bArr[i3];
                    } else if (i3 == i12 - 2) {
                        byte[] bArr6 = this.f12680d;
                        int i28 = this.f12676b;
                        this.f12676b = i28 + 1;
                        bArr6[i28] = bArr[i3];
                        int i29 = this.f12676b;
                        this.f12676b = i29 + 1;
                        bArr6[i29] = bArr[i3 + 1];
                    }
                    this.f12668a = i5;
                    this.count = i11;
                    return true;
                }
            }
        }
    }

    private C2511b() {
    }
}
