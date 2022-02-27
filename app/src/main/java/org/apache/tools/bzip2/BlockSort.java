package org.apache.tools.bzip2;

import android.support.v4.media.session.PlaybackStateCompat;
import com.tencent.smtt.sdk.TbsListener;
import java.util.BitSet;

/* loaded from: classes2.dex */
class BlockSort {
    private static final int CLEARMASK = -2097153;
    private static final int DEPTH_THRESH = 10;
    private static final int FALLBACK_QSORT_SMALL_THRESH = 10;
    private static final int FALLBACK_QSORT_STACK_SIZE = 100;
    private static final int[] INCS = {1, 4, 13, 40, TbsListener.ErrorCode.THREAD_INIT_ERROR, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    private static final int QSORT_STACK_SIZE = 1000;
    private static final int SETMASK = 2097152;
    private static final int SMALL_THRESH = 20;
    private static final int STACK_SIZE = 1000;
    private static final int WORK_FACTOR = 30;
    private int[] eclass;
    private boolean firstAttempt;
    private final char[] quadrant;
    private int workDone;
    private int workLimit;
    private final int[] stack_ll = new int[1000];
    private final int[] stack_hh = new int[1000];
    private final int[] stack_dd = new int[1000];
    private final int[] mainSort_runningOrder = new int[256];
    private final int[] mainSort_copy = new int[256];
    private final boolean[] mainSort_bigDone = new boolean[256];
    private final int[] ftab = new int[65537];

    private int fmin(int i, int i2) {
        return i < i2 ? i : i2;
    }

    private static byte med3(byte b, byte b2, byte b3) {
        if (b < b2) {
            if (b2 >= b3) {
                if (b >= b3) {
                    return b;
                }
                return b3;
            }
            return b2;
        }
        if (b2 <= b3) {
            if (b <= b3) {
                return b;
            }
            return b3;
        }
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BlockSort(CBZip2OutputStream.Data data) {
        this.quadrant = data.sfmap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void blockSort(CBZip2OutputStream.Data data, int i) {
        this.workLimit = i * 30;
        this.workDone = 0;
        this.firstAttempt = true;
        if (i + 1 < 10000) {
            fallbackSort(data, i);
        } else {
            mainSort(data, i);
            if (this.firstAttempt && this.workDone > this.workLimit) {
                fallbackSort(data, i);
            }
        }
        int[] iArr = data.fmap;
        data.origPtr = -1;
        for (int i2 = 0; i2 <= i; i2++) {
            if (iArr[i2] == 0) {
                data.origPtr = i2;
                return;
            }
        }
    }

    final void fallbackSort(CBZip2OutputStream.Data data, int i) {
        int i2 = i + 1;
        data.block[0] = data.block[i2];
        fallbackSort(data.fmap, data.block, i2);
        for (int i3 = 0; i3 < i2; i3++) {
            int[] iArr = data.fmap;
            iArr[i3] = iArr[i3] - 1;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (data.fmap[i4] == -1) {
                data.fmap[i4] = i;
                return;
            }
        }
    }

    private void fallbackSimpleSort(int[] iArr, int[] iArr2, int i, int i2) {
        if (i != i2) {
            if (i2 - i > 3) {
                for (int i3 = i2 - 4; i3 >= i; i3--) {
                    int i4 = iArr[i3];
                    int i5 = iArr2[i4];
                    int i6 = i3 + 4;
                    while (i6 <= i2 && i5 > iArr2[iArr[i6]]) {
                        iArr[i6 - 4] = iArr[i6];
                        i6 += 4;
                    }
                    iArr[i6 - 4] = i4;
                }
            }
            for (int i7 = i2 - 1; i7 >= i; i7--) {
                int i8 = iArr[i7];
                int i9 = iArr2[i8];
                int i10 = i7 + 1;
                while (i10 <= i2 && i9 > iArr2[iArr[i10]]) {
                    iArr[i10 - 1] = iArr[i10];
                    i10++;
                }
                iArr[i10 - 1] = i8;
            }
        }
    }

    private void fswap(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    private void fvswap(int[] iArr, int i, int i2, int i3) {
        while (i3 > 0) {
            fswap(iArr, i, i2);
            i++;
            i2++;
            i3--;
        }
    }

    private void fpush(int i, int i2, int i3) {
        this.stack_ll[i] = i2;
        this.stack_hh[i] = i3;
    }

    private int[] fpop(int i) {
        return new int[]{this.stack_ll[i], this.stack_hh[i]};
    }

    private void fallbackQSort3(int[] iArr, int[] iArr2, int i, int i2) {
        long j;
        int i3;
        char c = 0;
        fpush(0, i, i2);
        long j2 = 0;
        int i4 = 1;
        long j3 = 0;
        int i5 = 1;
        while (i5 > 0) {
            i5--;
            int[] fpop = fpop(i5);
            int i6 = fpop[c];
            int i7 = fpop[i4];
            if (i7 - i6 < 10) {
                fallbackSimpleSort(iArr, iArr2, i6, i7);
            } else {
                j3 = ((j3 * 7621) + 1) % PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                long j4 = j3 % 3;
                if (j4 == j2) {
                    j = iArr2[iArr[i6]];
                } else if (j4 == 1) {
                    j = iArr2[iArr[(i6 + i7) >>> i4]];
                } else {
                    j = iArr2[iArr[i7]];
                }
                int i8 = i7;
                int i9 = i8;
                int i10 = i6;
                int i11 = i10;
                while (true) {
                    if (i11 <= i8) {
                        int i12 = iArr2[iArr[i11]] - ((int) j);
                        if (i12 == 0) {
                            fswap(iArr, i11, i10);
                            i10++;
                            i11++;
                        } else if (i12 <= 0) {
                            i11++;
                        }
                    }
                    i3 = i9;
                    while (i11 <= i8) {
                        int i13 = iArr2[iArr[i8]] - ((int) j);
                        if (i13 == 0) {
                            fswap(iArr, i8, i3);
                            i3--;
                            i8--;
                        } else if (i13 < 0) {
                            break;
                        } else {
                            i8--;
                        }
                    }
                    if (i11 > i8) {
                        break;
                    }
                    fswap(iArr, i11, i8);
                    i11++;
                    i8--;
                    i9 = i3;
                }
                if (i3 < i10) {
                    c = 0;
                    j2 = 0;
                    i4 = 1;
                } else {
                    int fmin = fmin(i10 - i6, i11 - i10);
                    fvswap(iArr, i6, i11 - fmin, fmin);
                    int i14 = i7 - i3;
                    int i15 = i3 - i8;
                    int fmin2 = fmin(i14, i15);
                    fvswap(iArr, i8 + 1, (i7 - fmin2) + 1, fmin2);
                    int i16 = ((i11 + i6) - i10) - 1;
                    int i17 = (i7 - i15) + 1;
                    if (i16 - i6 > i7 - i17) {
                        int i18 = i5 + 1;
                        fpush(i5, i6, i16);
                        i5 = i18 + 1;
                        fpush(i18, i17, i7);
                    } else {
                        int i19 = i5 + 1;
                        fpush(i5, i17, i7);
                        i5 = i19 + 1;
                        fpush(i19, i6, i16);
                    }
                    c = 0;
                    j2 = 0;
                    i4 = 1;
                }
            }
        }
    }

    private int[] getEclass() {
        int[] iArr = this.eclass;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[this.quadrant.length / 2];
        this.eclass = iArr2;
        return iArr2;
    }

    final void fallbackSort(int[] iArr, byte[] bArr, int i) {
        int i2;
        int[] iArr2 = new int[257];
        int[] eclass = getEclass();
        for (int i3 = 0; i3 < i; i3++) {
            eclass[i3] = 0;
        }
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = bArr[i4] & 255;
            iArr2[i5] = iArr2[i5] + 1;
        }
        for (int i6 = 1; i6 < 257; i6++) {
            iArr2[i6] = iArr2[i6] + iArr2[i6 - 1];
        }
        for (int i7 = 0; i7 < i; i7++) {
            int i8 = bArr[i7] & 255;
            int i9 = iArr2[i8] - 1;
            iArr2[i8] = i9;
            iArr[i9] = i7;
        }
        BitSet bitSet = new BitSet(i + 64);
        for (int i10 = 0; i10 < 256; i10++) {
            bitSet.set(iArr2[i10]);
        }
        for (int i11 = 0; i11 < 32; i11++) {
            int i12 = (i11 * 2) + i;
            bitSet.set(i12);
            bitSet.clear(i12 + 1);
        }
        int i13 = 1;
        do {
            int i14 = 0;
            for (int i15 = 0; i15 < i; i15++) {
                if (bitSet.get(i15)) {
                    i14 = i15;
                }
                int i16 = iArr[i15] - i13;
                if (i16 < 0) {
                    i16 += i;
                }
                eclass[i16] = i14;
            }
            int i17 = -1;
            i2 = 0;
            while (true) {
                int nextClearBit = bitSet.nextClearBit(i17 + 1);
                int i18 = nextClearBit - 1;
                if (i18 < i && (i17 = bitSet.nextSetBit(nextClearBit + 1) - 1) < i) {
                    if (i17 > i18) {
                        i2 += (i17 - i18) + 1;
                        fallbackQSort3(iArr, eclass, i18, i17);
                        int i19 = -1;
                        while (i18 <= i17) {
                            int i20 = eclass[iArr[i18]];
                            if (i19 != i20) {
                                bitSet.set(i18);
                                i19 = i20;
                            }
                            i18++;
                        }
                    }
                }
            }
            i13 *= 2;
            if (i13 > i) {
                return;
            }
        } while (i2 != 0);
    }

    private boolean mainSimpleSort(CBZip2OutputStream.Data data, int i, int i2, int i3, int i4) {
        int i5 = (i2 - i) + 1;
        if (i5 < 2) {
            return this.firstAttempt && this.workDone > this.workLimit;
        }
        int i6 = 0;
        while (INCS[i6] < i5) {
            i6++;
        }
        int[] iArr = data.fmap;
        char[] cArr = this.quadrant;
        byte[] bArr = data.block;
        int i7 = i4 + 1;
        boolean z = this.firstAttempt;
        int i8 = this.workLimit;
        int i9 = this.workDone;
        loop1: while (true) {
            i6--;
            if (i6 < 0) {
                break;
            }
            int i10 = INCS[i6];
            int i11 = i + i10;
            int i12 = i11 - 1;
            while (i11 <= i2) {
                int i13 = 3;
                while (i11 <= i2) {
                    i13--;
                    if (i13 < 0) {
                        break;
                    }
                    int i14 = iArr[i11];
                    int i15 = i14 + i3;
                    i9 = i9;
                    int i16 = i11;
                    boolean z2 = false;
                    int i17 = 0;
                    while (true) {
                        if (z2) {
                            iArr[i16] = i17;
                            int i18 = i16 - i10;
                            if (i18 <= i12) {
                                i16 = i18;
                                i6 = i6;
                                i10 = i10;
                                i12 = i12;
                                break;
                            }
                            i16 = i18;
                        } else {
                            z2 = true;
                        }
                        int i19 = iArr[i16 - i10];
                        int i20 = i19 + i3;
                        int i21 = i20 + 1;
                        int i22 = i15 + 1;
                        if (bArr[i21] == bArr[i22]) {
                            int i23 = i20 + 2;
                            int i24 = i15 + 2;
                            i6 = i6;
                            if (bArr[i23] == bArr[i24]) {
                                int i25 = i20 + 3;
                                int i26 = i15 + 3;
                                z2 = z2;
                                if (bArr[i25] == bArr[i26]) {
                                    int i27 = i20 + 4;
                                    int i28 = i15 + 4;
                                    if (bArr[i27] == bArr[i28]) {
                                        int i29 = i20 + 5;
                                        int i30 = i15 + 5;
                                        if (bArr[i29] == bArr[i30]) {
                                            int i31 = i20 + 6;
                                            int i32 = i15 + 6;
                                            if (bArr[i31] == bArr[i32]) {
                                                int i33 = i4;
                                                while (true) {
                                                    if (i33 <= 0) {
                                                        i10 = i10;
                                                        i12 = i12;
                                                        break;
                                                    }
                                                    i33 -= 4;
                                                    int i34 = i31 + 1;
                                                    int i35 = i32 + 1;
                                                    if (bArr[i34] != bArr[i35]) {
                                                        i10 = i10;
                                                        i12 = i12;
                                                        if ((bArr[i34] & 255) > (bArr[i35] & 255)) {
                                                        }
                                                    } else if (cArr[i31] == cArr[i32]) {
                                                        int i36 = i31 + 2;
                                                        int i37 = i32 + 2;
                                                        i10 = i10;
                                                        if (bArr[i36] != bArr[i37]) {
                                                            i12 = i12;
                                                            if ((bArr[i36] & 255) > (bArr[i37] & 255)) {
                                                            }
                                                        } else if (cArr[i34] == cArr[i35]) {
                                                            int i38 = i31 + 3;
                                                            int i39 = i32 + 3;
                                                            i12 = i12;
                                                            if (bArr[i38] == bArr[i39]) {
                                                                if (cArr[i36] == cArr[i37]) {
                                                                    int i40 = i31 + 4;
                                                                    i32 += 4;
                                                                    if (bArr[i40] == bArr[i32]) {
                                                                        if (cArr[i38] == cArr[i39]) {
                                                                            i31 = i40 >= i7 ? i40 - i7 : i40;
                                                                            if (i32 >= i7) {
                                                                                i32 -= i7;
                                                                            }
                                                                            i9++;
                                                                            i10 = i10;
                                                                            i12 = i12;
                                                                        } else if (cArr[i38] > cArr[i39]) {
                                                                        }
                                                                    } else if ((bArr[i40] & 255) > (bArr[i32] & 255)) {
                                                                    }
                                                                } else if (cArr[i36] > cArr[i37]) {
                                                                }
                                                            } else if ((bArr[i38] & 255) > (bArr[i39] & 255)) {
                                                            }
                                                        } else {
                                                            i12 = i12;
                                                            if (cArr[i34] > cArr[i35]) {
                                                            }
                                                        }
                                                    } else {
                                                        i10 = i10;
                                                        i12 = i12;
                                                        if (cArr[i31] > cArr[i32]) {
                                                        }
                                                    }
                                                }
                                            } else {
                                                i10 = i10;
                                                i12 = i12;
                                                if ((bArr[i31] & 255) > (bArr[i32] & 255)) {
                                                    i17 = i19;
                                                }
                                            }
                                        } else {
                                            i10 = i10;
                                            i12 = i12;
                                            if ((bArr[i29] & 255) > (bArr[i30] & 255)) {
                                                i17 = i19;
                                            }
                                        }
                                    } else {
                                        i10 = i10;
                                        i12 = i12;
                                        if ((bArr[i27] & 255) > (bArr[i28] & 255)) {
                                            i17 = i19;
                                        }
                                    }
                                } else {
                                    i10 = i10;
                                    i12 = i12;
                                    if ((bArr[i25] & 255) > (bArr[i26] & 255)) {
                                        i17 = i19;
                                    }
                                }
                            } else {
                                z2 = z2;
                                i10 = i10;
                                i12 = i12;
                                if ((bArr[i23] & 255) > (bArr[i24] & 255)) {
                                    i17 = i19;
                                }
                            }
                        } else {
                            i6 = i6;
                            z2 = z2;
                            i10 = i10;
                            i12 = i12;
                            if ((bArr[i21] & 255) > (bArr[i22] & 255)) {
                                i17 = i19;
                            }
                        }
                    }
                    iArr[i16] = i14;
                    i11++;
                }
                if (z && i11 <= i2 && i9 > i8) {
                    break loop1;
                }
                i6 = i6;
                i10 = i10;
                i12 = i12;
            }
        }
        this.workDone = i9;
        return z && i9 > i8;
    }

    private static void vswap(int[] iArr, int i, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            int i5 = iArr[i];
            i++;
            iArr[i] = iArr[i2];
            i2++;
            iArr[i2] = i5;
        }
    }

    private void mainQSort3(CBZip2OutputStream.Data data, int i, int i2, int i3, int i4) {
        int i5;
        int[] iArr = this.stack_ll;
        int[] iArr2 = this.stack_hh;
        int[] iArr3 = this.stack_dd;
        int[] iArr4 = data.fmap;
        byte[] bArr = data.block;
        iArr[0] = i;
        iArr2[0] = i2;
        iArr3[0] = i3;
        int i6 = 1;
        int i7 = 1;
        while (true) {
            int i8 = i7 - 1;
            if (i8 >= 0) {
                int i9 = iArr[i8];
                int i10 = iArr2[i8];
                int i11 = iArr3[i8];
                if (i10 - i9 >= 20 && i11 <= 10) {
                    int i12 = i11 + 1;
                    int med3 = med3(bArr[iArr4[i9] + i12], bArr[iArr4[i10] + i12], bArr[iArr4[(i9 + i10) >>> i6] + i12]) & 255;
                    int i13 = i9;
                    int i14 = i13;
                    int i15 = i10;
                    int i16 = i15;
                    while (true) {
                        if (i13 <= i15) {
                            int i17 = (bArr[iArr4[i13] + i12] & 255) - med3;
                            if (i17 == 0) {
                                int i18 = iArr4[i13];
                                i13++;
                                iArr4[i13] = iArr4[i14];
                                i14++;
                                iArr4[i14] = i18;
                            } else if (i17 < 0) {
                                i13++;
                            }
                        }
                        i5 = i16;
                        while (i13 <= i15) {
                            int i19 = (bArr[iArr4[i15] + i12] & 255) - med3;
                            if (i19 != 0) {
                                if (i19 <= 0) {
                                    break;
                                }
                                i15--;
                            } else {
                                int i20 = iArr4[i15];
                                i15--;
                                iArr4[i15] = iArr4[i5];
                                i5--;
                                iArr4[i5] = i20;
                            }
                        }
                        if (i13 > i15) {
                            break;
                        }
                        int i21 = iArr4[i13];
                        i13++;
                        iArr4[i13] = iArr4[i15];
                        i15--;
                        iArr4[i15] = i21;
                        i16 = i5;
                    }
                    if (i5 < i14) {
                        iArr[i8] = i9;
                        iArr2[i8] = i10;
                        iArr3[i8] = i12;
                        i7 = i8 + 1;
                    } else {
                        int i22 = i14 - i9;
                        int i23 = i13 - i14;
                        if (i22 >= i23) {
                            i22 = i23;
                        }
                        vswap(iArr4, i9, i13 - i22, i22);
                        int i24 = i10 - i5;
                        int i25 = i5 - i15;
                        if (i24 >= i25) {
                            i24 = i25;
                        }
                        vswap(iArr4, i13, (i10 - i24) + 1, i24);
                        int i26 = ((i13 + i9) - i14) - 1;
                        int i27 = (i10 - i25) + 1;
                        iArr[i8] = i9;
                        iArr2[i8] = i26;
                        iArr3[i8] = i11;
                        int i28 = i8 + 1;
                        iArr[i28] = i26 + 1;
                        iArr2[i28] = i27 - 1;
                        iArr3[i28] = i12;
                        int i29 = i28 + 1;
                        iArr[i29] = i27;
                        iArr2[i29] = i10;
                        iArr3[i29] = i11;
                        i7 = i29 + 1;
                    }
                } else if (!mainSimpleSort(data, i9, i10, i11, i4)) {
                    i7 = i8;
                } else {
                    return;
                }
                i6 = 1;
            } else {
                return;
            }
        }
    }

    final void mainSort(CBZip2OutputStream.Data data, int i) {
        int i2;
        int i3;
        int i4;
        int[] iArr = this.mainSort_runningOrder;
        int[] iArr2 = this.mainSort_copy;
        boolean[] zArr = this.mainSort_bigDone;
        int[] iArr3 = this.ftab;
        byte[] bArr = data.block;
        int[] iArr4 = data.fmap;
        char[] cArr = this.quadrant;
        int i5 = this.workLimit;
        boolean z = this.firstAttempt;
        int i6 = 65537;
        while (true) {
            i6--;
            if (i6 < 0) {
                break;
            }
            iArr3[i6] = 0;
        }
        for (int i7 = 0; i7 < 20; i7++) {
            bArr[i + i7 + 2] = bArr[(i7 % (i + 1)) + 1];
        }
        int i8 = i + 20 + 1;
        while (true) {
            i8--;
            if (i8 < 0) {
                break;
            }
            cArr[i8] = 0;
        }
        int i9 = i + 1;
        bArr[0] = bArr[i9];
        int i10 = 255;
        int i11 = bArr[0] & 255;
        int i12 = 0;
        while (i12 <= i) {
            i12++;
            int i13 = bArr[i12] & 255;
            int i14 = (i11 << 8) + i13;
            iArr3[i14] = iArr3[i14] + 1;
            i11 = i13;
        }
        for (int i15 = 1; i15 <= 65536; i15++) {
            iArr3[i15] = iArr3[i15] + iArr3[i15 - 1];
        }
        int i16 = bArr[1] & 255;
        int i17 = 0;
        while (i17 < i) {
            int i18 = bArr[i17 + 2] & 255;
            int i19 = (i16 << 8) + i18;
            int i20 = iArr3[i19] - 1;
            iArr3[i19] = i20;
            iArr4[i20] = i17;
            i17++;
            i16 = i18;
        }
        int i21 = ((bArr[i9] & 255) << 8) + (bArr[1] & 255);
        int i22 = iArr3[i21] - 1;
        iArr3[i21] = i22;
        iArr4[i22] = i;
        int i23 = 256;
        while (true) {
            i23--;
            if (i23 < 0) {
                break;
            }
            zArr[i23] = false;
            iArr[i23] = i23;
        }
        int i24 = 364;
        for (int i25 = 1; i24 != i25; i25 = 1) {
            i24 /= 3;
            int i26 = i24;
            while (i26 <= i10) {
                int i27 = iArr[i26];
                int i28 = iArr3[(i27 + 1) << 8] - iArr3[i27 << 8];
                int i29 = i24 - 1;
                int i30 = iArr[i26 - i24];
                int i31 = i26;
                while (true) {
                    if (iArr3[(i30 + 1) << 8] - iArr3[i30 << 8] <= i28) {
                        i4 = i31;
                        break;
                    }
                    iArr[i31] = i30;
                    i4 = i31 - i24;
                    if (i4 <= i29) {
                        break;
                    }
                    i30 = iArr[i4 - i24];
                    i31 = i4;
                    i5 = i5;
                }
                iArr[i4] = i27;
                i26++;
                i5 = i5;
                i10 = 255;
            }
        }
        int i32 = i5;
        int i33 = 0;
        while (i33 <= i10) {
            int i34 = iArr[i33];
            int i35 = 0;
            while (i35 <= i10) {
                int i36 = (i34 << 8) + i35;
                int i37 = iArr3[i36];
                if ((i37 & 2097152) != 2097152) {
                    int i38 = i37 & CLEARMASK;
                    int i39 = (iArr3[i36 + 1] & CLEARMASK) - 1;
                    if (i39 > i38) {
                        i3 = 2097152;
                        i2 = i35;
                        i32 = i32;
                        iArr = iArr;
                        i33 = i33;
                        mainQSort3(data, i38, i39, 2, i);
                        if (z && this.workDone > i32) {
                            return;
                        }
                    } else {
                        i2 = i35;
                        i32 = i32;
                        i3 = 2097152;
                        iArr = iArr;
                        i33 = i33;
                    }
                    iArr3[i36] = i37 | i3;
                } else {
                    i2 = i35;
                    i32 = i32;
                    iArr = iArr;
                    i33 = i33;
                }
                i35 = i2 + 1;
                i10 = 255;
            }
            for (int i40 = 0; i40 <= 255; i40++) {
                iArr2[i40] = iArr3[(i40 << 8) + i34] & CLEARMASK;
            }
            int i41 = i34 << 8;
            int i42 = iArr3[i41] & CLEARMASK;
            int i43 = (i34 + 1) << 8;
            for (int i44 = iArr3[i43] & CLEARMASK; i42 < i44; i44 = i44) {
                int i45 = iArr4[i42];
                int i46 = bArr[i45] & 255;
                if (!zArr[i46]) {
                    iArr4[iArr2[i46]] = i45 == 0 ? i : i45 - 1;
                    iArr2[i46] = iArr2[i46] + 1;
                }
                i42++;
            }
            int i47 = 256;
            while (true) {
                i47--;
                if (i47 < 0) {
                    break;
                }
                int i48 = (i47 << 8) + i34;
                iArr3[i48] = iArr3[i48] | 2097152;
            }
            zArr[i34] = true;
            if (i33 < 255) {
                int i49 = iArr3[i41] & CLEARMASK;
                int i50 = (CLEARMASK & iArr3[i43]) - i49;
                int i51 = 0;
                while ((i50 >> i51) > 65534) {
                    i51++;
                }
                int i52 = 0;
                while (i52 < i50) {
                    int i53 = iArr4[i49 + i52];
                    char c = (char) (i52 >> i51);
                    cArr[i53] = c;
                    if (i53 < 20) {
                        cArr[i53 + i + 1] = c;
                    }
                    i52++;
                    i49 = i49;
                }
            }
            i33++;
            iArr = iArr;
            i10 = 255;
            i32 = i32;
        }
    }
}
