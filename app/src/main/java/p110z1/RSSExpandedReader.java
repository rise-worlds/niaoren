package p110z1;

import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.tencent.smtt.sdk.TbsListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.tools.tar.TarConstants;

/* renamed from: z1.kw */
/* loaded from: classes3.dex */
public final class RSSExpandedReader extends AbstractRSSReader {

    /* renamed from: l */
    private static final int f22273l = 0;

    /* renamed from: m */
    private static final int f22274m = 1;

    /* renamed from: n */
    private static final int f22275n = 2;

    /* renamed from: o */
    private static final int f22276o = 3;

    /* renamed from: p */
    private static final int f22277p = 4;

    /* renamed from: q */
    private static final int f22278q = 5;

    /* renamed from: s */
    private static final int f22280s = 11;

    /* renamed from: t */
    private final List<ExpandedPair> f22281t = new ArrayList(11);

    /* renamed from: u */
    private final List<ExpandedRow> f22282u = new ArrayList();

    /* renamed from: v */
    private final int[] f22283v = new int[2];

    /* renamed from: w */
    private boolean f22284w;

    /* renamed from: g */
    private static final int[] f22268g = {7, 5, 4, 3, 1};

    /* renamed from: h */
    private static final int[] f22269h = {4, 20, 52, 104, 204};

    /* renamed from: i */
    private static final int[] f22270i = {0, 348, 1388, 2948, 3988};

    /* renamed from: j */
    private static final int[][] f22271j = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};

    /* renamed from: k */
    private static final int[][] f22272k = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, TbsListener.ErrorCode.NEEDDOWNLOAD_4, 7, 21, 63}, new int[]{189, TbsListener.ErrorCode.NEEDDOWNLOAD_6, 13, 39, 117, TbsListener.ErrorCode.NEEDDOWNLOAD_1, 209, 205}, new int[]{193, 157, 49, TbsListener.ErrorCode.NEEDDOWNLOAD_8, 19, 57, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_1, 91}, new int[]{62, 186, 136, 197, TbsListener.ErrorCode.STARTDOWNLOAD_10, 85, 44, 132}, new int[]{185, 133, 188, TbsListener.ErrorCode.NEEDDOWNLOAD_3, 4, 12, 36, 108}, new int[]{113, 128, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_3, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED, 158, 52, 156}, new int[]{46, 138, 203, 187, 139, 206, 196, TbsListener.ErrorCode.STARTDOWNLOAD_7}, new int[]{76, 17, 51, 153, 37, 111, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR, TarConstants.PREFIXLEN}, new int[]{43, IpcCommand.f8356aC, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_6, 106, 107, 110, 119, TbsListener.ErrorCode.NEEDDOWNLOAD_7}, new int[]{16, 48, TbsListener.ErrorCode.NEEDDOWNLOAD_5, 10, 30, 90, 59, TbsListener.ErrorCode.NONEEDDOWNLOAD_OTHER_PROCESS_DOWNLOADING}, new int[]{109, 116, 137, 200, 178, 112, TbsListener.ErrorCode.DOWNLOAD_THROWABLE, TbsListener.ErrorCode.STARTDOWNLOAD_5}, new int[]{70, 210, 208, 202, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, 204, 190}, new int[]{TbsListener.ErrorCode.NEEDDOWNLOAD_9, 22, 66, 198, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_2, 94, 71, 2}, new int[]{6, 18, 54, TbsListener.ErrorCode.STARTDOWNLOAD_3, 64, 192, 154, 40}, new int[]{120, TbsListener.ErrorCode.NEEDDOWNLOAD_10, 25, 75, 14, 42, TbsListener.ErrorCode.PV_UPLOAD_ERROR, TbsListener.ErrorCode.STARTDOWNLOAD_8}, new int[]{79, 26, 78, 23, 69, 207, 199, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5}, new int[]{103, 98, 83, 38, 114, 131, 182, TbsListener.ErrorCode.DOWNLOAD_REDIRECT_EMPTY}, new int[]{TbsListener.ErrorCode.STARTDOWNLOAD_2, 61, 183, 127, TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE, 88, 53, 159}, new int[]{55, TbsListener.ErrorCode.STARTDOWNLOAD_6, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, TbsListener.ErrorCode.STARTDOWNLOAD_1, 58, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4, 100, 89}};

    /* renamed from: r */
    private static final int[][] f22279r = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};

    @Override // p110z1.OneDReader
    /* renamed from: a */
    public final C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.f22281t.clear();
        this.f22284w = false;
        try {
            return m2147b(m2159a(i, huVar));
        } catch (NotFoundException unused) {
            this.f22281t.clear();
            this.f22284w = true;
            return m2147b(m2159a(i, huVar));
        }
    }

    @Override // p110z1.OneDReader, p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
        this.f22281t.clear();
        this.f22282u.clear();
    }

    /* renamed from: a */
    private List<ExpandedPair> m2159a(int i, BitArray huVar) throws NotFoundException {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7 = false;
        while (!z7) {
            try {
                this.f22281t.add(m2152a(huVar, this.f22281t, i));
            } catch (NotFoundException e) {
                if (!this.f22281t.isEmpty()) {
                    z7 = true;
                } else {
                    throw e;
                }
            }
        }
        if (m2144c()) {
            return this.f22281t;
        }
        boolean z8 = !this.f22282u.isEmpty();
        int i2 = 0;
        boolean z9 = false;
        while (true) {
            if (i2 >= this.f22282u.size()) {
                z = false;
                break;
            }
            ExpandedRow kvVar = this.f22282u.get(i2);
            if (kvVar.f22266b > i) {
                z = kvVar.m2163a(this.f22281t);
                break;
            }
            z9 = kvVar.m2163a(this.f22281t);
            i2++;
        }
        if (!z && !z9) {
            List<ExpandedPair> list = this.f22281t;
            Iterator<T> it = this.f22282u.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                ExpandedRow kvVar2 = (ExpandedRow) it.next();
                Iterator<T> it2 = list.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z5 = true;
                        continue;
                        break;
                    }
                    ExpandedPair kuVar = (ExpandedPair) it2.next();
                    Iterator<ExpandedPair> it3 = kvVar2.f22265a.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (kuVar.equals(it3.next())) {
                                z6 = true;
                                continue;
                                break;
                            }
                        } else {
                            z6 = false;
                            continue;
                            break;
                        }
                    }
                    if (!z6) {
                        z5 = false;
                        continue;
                        break;
                    }
                }
                if (z5) {
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                this.f22282u.add(i2, new ExpandedRow(this.f22281t, i));
                List<ExpandedPair> list2 = this.f22281t;
                Iterator<ExpandedRow> it4 = this.f22282u.iterator();
                while (it4.hasNext()) {
                    ExpandedRow next = it4.next();
                    if (next.f22265a.size() != list2.size()) {
                        Iterator<ExpandedPair> it5 = next.f22265a.iterator();
                        while (true) {
                            if (!it5.hasNext()) {
                                z3 = true;
                                break;
                            }
                            ExpandedPair next2 = it5.next();
                            Iterator<ExpandedPair> it6 = list2.iterator();
                            while (true) {
                                if (it6.hasNext()) {
                                    if (next2.equals(it6.next())) {
                                        z4 = true;
                                        continue;
                                        break;
                                    }
                                } else {
                                    z4 = false;
                                    continue;
                                    break;
                                }
                            }
                            if (!z4) {
                                z3 = false;
                                break;
                            }
                        }
                        if (z3) {
                            it4.remove();
                        }
                    }
                }
            }
        }
        if (z8) {
            List<ExpandedPair> a = m2149a(false);
            if (a != null) {
                return a;
            }
            List<ExpandedPair> a2 = m2149a(true);
            if (a2 != null) {
                return a2;
            }
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private List<ExpandedPair> m2149a(boolean z) {
        List<ExpandedPair> list = null;
        if (this.f22282u.size() > 25) {
            this.f22282u.clear();
            return null;
        }
        this.f22281t.clear();
        if (z) {
            Collections.reverse(this.f22282u);
        }
        try {
            list = m2156a(new ArrayList(), 0);
        } catch (NotFoundException unused) {
        }
        if (z) {
            Collections.reverse(this.f22282u);
        }
        return list;
    }

    /* renamed from: a */
    private List<ExpandedPair> m2156a(List<ExpandedRow> list, int i) throws NotFoundException {
        boolean z;
        while (i < this.f22282u.size()) {
            ExpandedRow kvVar = this.f22282u.get(i);
            this.f22281t.clear();
            for (ExpandedRow kvVar2 : list) {
                this.f22281t.addAll(kvVar2.f22265a);
            }
            this.f22281t.addAll(kvVar.f22265a);
            List<ExpandedPair> list2 = this.f22281t;
            int[][] iArr = f22279r;
            int length = iArr.length;
            boolean z2 = false;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                int[] iArr2 = iArr[i2];
                if (list2.size() <= iArr2.length) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= list2.size()) {
                            z = true;
                            break;
                        } else if (list2.get(i3).f22263c.f22287a != iArr2[i3]) {
                            z = false;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (z) {
                        z2 = true;
                        break;
                    }
                }
                i2++;
            }
            if (z2) {
                if (m2144c()) {
                    return this.f22281t;
                }
                ArrayList arrayList = new ArrayList(list);
                arrayList.add(kvVar);
                try {
                    return m2156a(arrayList, i + 1);
                } catch (NotFoundException unused) {
                    continue;
                }
            }
            i++;
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static boolean m2157a(List<ExpandedPair> list) {
        int[][] iArr;
        boolean z;
        for (int[] iArr2 : f22279r) {
            if (list.size() <= iArr2.length) {
                int i = 0;
                while (true) {
                    if (i >= list.size()) {
                        z = true;
                        break;
                    } else if (list.get(i).f22263c.f22287a != iArr2[i]) {
                        z = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    private void m2160a(int i) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        int i2 = 0;
        boolean z7 = false;
        while (true) {
            if (i2 >= this.f22282u.size()) {
                z = false;
                break;
            }
            ExpandedRow kvVar = this.f22282u.get(i2);
            if (kvVar.f22266b > i) {
                z = kvVar.m2163a(this.f22281t);
                break;
            } else {
                z7 = kvVar.m2163a(this.f22281t);
                i2++;
            }
        }
        if (!z && !z7) {
            List<ExpandedPair> list = this.f22281t;
            Iterator<T> it = this.f22282u.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                ExpandedRow kvVar2 = (ExpandedRow) it.next();
                Iterator<T> it2 = list.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z5 = true;
                        continue;
                        break;
                    }
                    ExpandedPair kuVar = (ExpandedPair) it2.next();
                    Iterator<ExpandedPair> it3 = kvVar2.f22265a.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (kuVar.equals(it3.next())) {
                                z6 = true;
                                continue;
                                break;
                            }
                        } else {
                            z6 = false;
                            continue;
                            break;
                        }
                    }
                    if (!z6) {
                        z5 = false;
                        continue;
                        break;
                    }
                }
                if (z5) {
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                this.f22282u.add(i2, new ExpandedRow(this.f22281t, i));
                List<ExpandedPair> list2 = this.f22281t;
                Iterator<ExpandedRow> it4 = this.f22282u.iterator();
                while (it4.hasNext()) {
                    ExpandedRow next = it4.next();
                    if (next.f22265a.size() != list2.size()) {
                        Iterator<ExpandedPair> it5 = next.f22265a.iterator();
                        while (true) {
                            if (!it5.hasNext()) {
                                z3 = true;
                                break;
                            }
                            ExpandedPair next2 = it5.next();
                            Iterator<ExpandedPair> it6 = list2.iterator();
                            while (true) {
                                if (it6.hasNext()) {
                                    if (next2.equals(it6.next())) {
                                        z4 = true;
                                        continue;
                                        break;
                                    }
                                } else {
                                    z4 = false;
                                    continue;
                                    break;
                                }
                            }
                            if (!z4) {
                                z3 = false;
                                break;
                            }
                        }
                        if (z3) {
                            it4.remove();
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0048, code lost:
        continue;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m2155a(java.util.List<p110z1.ExpandedPair> r6, java.util.List<p110z1.ExpandedRow> r7) {
        /*
            java.util.Iterator r7 = r7.iterator()
        L_0x0004:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x0052
            java.lang.Object r0 = r7.next()
            z1.kv r0 = (p110z1.ExpandedRow) r0
            java.util.List<z1.ku> r1 = r0.f22265a
            int r1 = r1.size()
            int r2 = r6.size()
            if (r1 == r2) goto L_0x0004
            java.util.List<z1.ku> r0 = r0.f22265a
            java.util.Iterator r0 = r0.iterator()
        L_0x0022:
            boolean r1 = r0.hasNext()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x004b
            java.lang.Object r1 = r0.next()
            z1.ku r1 = (p110z1.ExpandedPair) r1
            java.util.Iterator r4 = r6.iterator()
        L_0x0034:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0047
            java.lang.Object r5 = r4.next()
            z1.ku r5 = (p110z1.ExpandedPair) r5
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x0034
            goto L_0x0048
        L_0x0047:
            r3 = 0
        L_0x0048:
            if (r3 != 0) goto L_0x0022
            goto L_0x004c
        L_0x004b:
            r2 = 1
        L_0x004c:
            if (r2 == 0) goto L_0x0004
            r7.remove()
            goto L_0x0004
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.RSSExpandedReader.m2155a(java.util.List, java.util.List):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0041, code lost:
        continue;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m2158a(java.lang.Iterable<p110z1.ExpandedPair> r7, java.lang.Iterable<p110z1.ExpandedRow> r8) {
        /*
            java.util.Iterator r8 = r8.iterator()
        L_0x0004:
            boolean r0 = r8.hasNext()
            r1 = 0
            if (r0 == 0) goto L_0x0044
            java.lang.Object r0 = r8.next()
            z1.kv r0 = (p110z1.ExpandedRow) r0
            java.util.Iterator r2 = r7.iterator()
        L_0x0015:
            boolean r3 = r2.hasNext()
            r4 = 1
            if (r3 == 0) goto L_0x0040
            java.lang.Object r3 = r2.next()
            z1.ku r3 = (p110z1.ExpandedPair) r3
            java.util.List<z1.ku> r5 = r0.f22265a
            java.util.Iterator r5 = r5.iterator()
        L_0x0028:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x003c
            java.lang.Object r6 = r5.next()
            z1.ku r6 = (p110z1.ExpandedPair) r6
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x0028
            r3 = 1
            goto L_0x003d
        L_0x003c:
            r3 = 0
        L_0x003d:
            if (r3 != 0) goto L_0x0015
            goto L_0x0041
        L_0x0040:
            r1 = 1
        L_0x0041:
            if (r1 == 0) goto L_0x0004
            return r4
        L_0x0044:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.RSSExpandedReader.m2158a(java.lang.Iterable, java.lang.Iterable):boolean");
    }

    /* renamed from: b */
    private List<ExpandedRow> m2148b() {
        return this.f22282u;
    }

    /* renamed from: c */
    private boolean m2144c() {
        ExpandedPair kuVar = this.f22281t.get(0);
        DataCharacter kxVar = kuVar.f22261a;
        DataCharacter kxVar2 = kuVar.f22262b;
        if (kxVar2 == null) {
            return false;
        }
        int i = kxVar2.f22286b;
        int i2 = 2;
        for (int i3 = 1; i3 < this.f22281t.size(); i3++) {
            ExpandedPair kuVar2 = this.f22281t.get(i3);
            i += kuVar2.f22261a.f22286b;
            i2++;
            DataCharacter kxVar3 = kuVar2.f22262b;
            if (kxVar3 != null) {
                i += kxVar3.f22286b;
                i2++;
            }
        }
        return ((i2 + (-4)) * 211) + (i % 211) == kxVar.f22285a;
    }

    /* renamed from: a */
    private static int m2154a(BitArray huVar, int i) {
        if (huVar.m2551a(i)) {
            return huVar.m2541c(huVar.m2538d(i));
        }
        return huVar.m2538d(huVar.m2541c(i));
    }

    /* renamed from: a */
    private ExpandedPair m2152a(BitArray huVar, List<ExpandedPair> list, int i) throws NotFoundException {
        int i2;
        DataCharacter kxVar;
        int i3 = 2;
        boolean z = true;
        boolean z2 = list.size() % 2 == 0;
        if (this.f22284w) {
            z2 = !z2;
        }
        int i4 = -1;
        boolean z3 = true;
        while (true) {
            int[] iArr = this.f22207a;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[i3] = 0;
            iArr[3] = 0;
            int i5 = huVar.f21908b;
            if (i4 < 0) {
                i2 = list.isEmpty() ? 0 : list.get(list.size() - 1).f22263c.f22288b[1];
            } else {
                i2 = i4;
            }
            boolean z4 = list.size() % i3 != 0;
            if (this.f22284w) {
                z4 = !z4;
            }
            boolean z5 = false;
            while (i2 < i5) {
                z5 = !huVar.m2551a(i2);
                if (!z5) {
                    break;
                }
                i2++;
            }
            int i6 = i2;
            int i7 = 0;
            while (i2 < i5) {
                if (huVar.m2551a(i2) != z5) {
                    iArr[i7] = iArr[i7] + 1;
                } else {
                    if (i7 == 3) {
                        if (z4) {
                            m2145b(iArr);
                        }
                        if (m2239a(iArr)) {
                            int[] iArr2 = this.f22283v;
                            iArr2[0] = i6;
                            iArr2[1] = i2;
                            FinderPattern a = m2153a(huVar, i, z2);
                            if (a == null) {
                                int i8 = this.f22283v[0];
                                if (huVar.m2551a(i8)) {
                                    i4 = huVar.m2541c(huVar.m2538d(i8));
                                } else {
                                    i4 = huVar.m2538d(huVar.m2541c(i8));
                                }
                            } else {
                                z3 = false;
                            }
                            if (!z3) {
                                DataCharacter a2 = m2151a(huVar, a, z2, true);
                                if (!list.isEmpty()) {
                                    if (list.get(list.size() - 1).f22262b != null) {
                                        z = false;
                                    }
                                    if (z) {
                                        throw NotFoundException.m1647a();
                                    }
                                }
                                try {
                                    kxVar = m2151a(huVar, a, z2, false);
                                } catch (NotFoundException unused) {
                                    kxVar = null;
                                }
                                return new ExpandedPair(a2, kxVar, a);
                            }
                            i3 = 2;
                        } else {
                            if (z4) {
                                m2145b(iArr);
                            }
                            i6 += iArr[0] + iArr[1];
                            iArr[0] = iArr[2];
                            iArr[1] = iArr[3];
                            iArr[2] = 0;
                            iArr[3] = 0;
                            i7--;
                        }
                    } else {
                        i7++;
                    }
                    iArr[i7] = 1;
                    z5 = !z5;
                }
                i2++;
            }
            throw NotFoundException.m1647a();
        }
    }

    /* renamed from: b */
    private static void m2145b(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            int i3 = (length - i) - 1;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }

    /* renamed from: a */
    private FinderPattern m2153a(BitArray huVar, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (z) {
            int i5 = this.f22283v[0] - 1;
            while (i5 >= 0 && !huVar.m2551a(i5)) {
                i5--;
            }
            int i6 = i5 + 1;
            int[] iArr = this.f22283v;
            i2 = iArr[0] - i6;
            i3 = iArr[1];
            i4 = i6;
        } else {
            int[] iArr2 = this.f22283v;
            i4 = iArr2[0];
            int d = huVar.m2538d(iArr2[1] + 1);
            i2 = d - this.f22283v[1];
            i3 = d;
        }
        int[] iArr3 = this.f22207a;
        System.arraycopy(iArr3, 0, iArr3, 1, iArr3.length - 1);
        iArr3[0] = i2;
        try {
            return new FinderPattern(m2237a(iArr3, f22271j), new int[]{i4, i3}, i4, i3, i);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    /* renamed from: b */
    private static C5422ol m2147b(List<ExpandedPair> list) throws NotFoundException, FormatException {
        AbstractExpandedDecoder kkVar;
        int size = (list.size() << 1) - 1;
        if (list.get(list.size() - 1).f22262b == null) {
            size--;
        }
        BitArray huVar = new BitArray(size * 12);
        int i = list.get(0).f22262b.f22285a;
        int i2 = 0;
        for (int i3 = 11; i3 >= 0; i3--) {
            if (((1 << i3) & i) != 0) {
                huVar.m2545b(i2);
            }
            i2++;
        }
        for (int i4 = 1; i4 < list.size(); i4++) {
            ExpandedPair kuVar = list.get(i4);
            int i5 = kuVar.f22261a.f22285a;
            for (int i6 = 11; i6 >= 0; i6--) {
                if (((1 << i6) & i5) != 0) {
                    huVar.m2545b(i2);
                }
                i2++;
            }
            if (kuVar.f22262b != null) {
                int i7 = kuVar.f22262b.f22285a;
                for (int i8 = 11; i8 >= 0; i8--) {
                    if (((1 << i8) & i7) != 0) {
                        huVar.m2545b(i2);
                    }
                    i2++;
                }
            }
        }
        if (huVar.m2551a(1)) {
            kkVar = new AI01AndOtherAIs(huVar);
        } else if (!huVar.m2551a(2)) {
            kkVar = new AnyAIDecoder(huVar);
        } else {
            switch (GeneralAppIdDecoder.m2183a(huVar, 1, 4)) {
                case 4:
                    kkVar = new AI013103decoder(huVar);
                    break;
                case 5:
                    kkVar = new AI01320xDecoder(huVar);
                    break;
                default:
                    switch (GeneralAppIdDecoder.m2183a(huVar, 1, 5)) {
                        case 12:
                            kkVar = new AI01392xDecoder(huVar);
                            break;
                        case 13:
                            kkVar = new AI01393xDecoder(huVar);
                            break;
                        default:
                            switch (GeneralAppIdDecoder.m2183a(huVar, 1, 7)) {
                                case 56:
                                    kkVar = new AI013x0x1xDecoder(huVar, "310", "11");
                                    break;
                                case 57:
                                    kkVar = new AI013x0x1xDecoder(huVar, "320", "11");
                                    break;
                                case 58:
                                    kkVar = new AI013x0x1xDecoder(huVar, "310", "13");
                                    break;
                                case 59:
                                    kkVar = new AI013x0x1xDecoder(huVar, "320", "13");
                                    break;
                                case 60:
                                    kkVar = new AI013x0x1xDecoder(huVar, "310", "15");
                                    break;
                                case 61:
                                    kkVar = new AI013x0x1xDecoder(huVar, "320", "15");
                                    break;
                                case 62:
                                    kkVar = new AI013x0x1xDecoder(huVar, "310", "17");
                                    break;
                                case 63:
                                    kkVar = new AI013x0x1xDecoder(huVar, "320", "17");
                                    break;
                                default:
                                    throw new IllegalStateException("unknown decoder: ".concat(String.valueOf(huVar)));
                            }
                    }
            }
        }
        String a = kkVar.mo2216a();
        ResultPoint[] onVarArr = list.get(0).f22263c.f22289c;
        ResultPoint[] onVarArr2 = list.get(list.size() - 1).f22263c.f22289c;
        return new C5422ol(a, null, new ResultPoint[]{onVarArr[0], onVarArr[1], onVarArr2[0], onVarArr2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    /* renamed from: b */
    private void m2146b(BitArray huVar, List<ExpandedPair> list, int i) throws NotFoundException {
        int[] iArr = this.f22207a;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        int i2 = huVar.f21908b;
        if (i < 0) {
            i = list.isEmpty() ? 0 : list.get(list.size() - 1).f22263c.f22288b[1];
        }
        boolean z = list.size() % 2 != 0;
        if (this.f22284w) {
            z = !z;
        }
        boolean z2 = false;
        while (i < i2) {
            z2 = !huVar.m2551a(i);
            if (!z2) {
                break;
            }
            i++;
        }
        int i3 = i;
        int i4 = 0;
        while (i < i2) {
            if (huVar.m2551a(i) != z2) {
                iArr[i4] = iArr[i4] + 1;
            } else {
                if (i4 == 3) {
                    if (z) {
                        m2145b(iArr);
                    }
                    if (m2239a(iArr)) {
                        int[] iArr2 = this.f22283v;
                        iArr2[0] = i3;
                        iArr2[1] = i;
                        return;
                    }
                    if (z) {
                        m2145b(iArr);
                    }
                    i3 += iArr[0] + iArr[1];
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = 0;
                    iArr[3] = 0;
                    i4--;
                } else {
                    i4++;
                }
                iArr[i4] = 1;
                z2 = !z2;
            }
            i++;
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private DataCharacter m2151a(BitArray huVar, FinderPattern kyVar, boolean z, boolean z2) throws NotFoundException {
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        int[] iArr = this.f22208b;
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 0;
        }
        if (z2) {
            m2087b(huVar, kyVar.f22288b[0], iArr);
        } else {
            m2090a(huVar, kyVar.f22288b[1], iArr);
            int i2 = 0;
            for (int length = iArr.length - 1; i2 < length; length--) {
                int i3 = iArr[i2];
                iArr[i2] = iArr[length];
                iArr[length] = i3;
                i2++;
            }
        }
        float a = MathUtils.m2528a(iArr) / 17.0f;
        float f = (kyVar.f22288b[1] - kyVar.f22288b[0]) / 15.0f;
        if (Math.abs(a - f) / f <= 0.3f) {
            int[] iArr2 = this.f22211e;
            int[] iArr3 = this.f22212f;
            float[] fArr = this.f22209c;
            float[] fArr2 = this.f22210d;
            for (int i4 = 0; i4 < iArr.length; i4++) {
                float f2 = (iArr[i4] * 1.0f) / a;
                int i5 = (int) (0.5f + f2);
                if (i5 <= 0) {
                    if (f2 >= 0.3f) {
                        i5 = 1;
                    } else {
                        throw NotFoundException.m1647a();
                    }
                } else if (i5 > 8) {
                    if (f2 <= 8.7f) {
                        i5 = 8;
                    } else {
                        throw NotFoundException.m1647a();
                    }
                }
                int i6 = i4 / 2;
                if ((i4 & 1) == 0) {
                    iArr2[i6] = i5;
                    fArr[i6] = f2 - i5;
                } else {
                    iArr3[i6] = i5;
                    fArr2[i6] = f2 - i5;
                }
            }
            int a2 = MathUtils.m2528a(this.f22211e);
            int a3 = MathUtils.m2528a(this.f22212f);
            if (a2 > 13) {
                z4 = false;
                z3 = true;
            } else if (a2 < 4) {
                z4 = true;
                z3 = false;
            } else {
                z4 = false;
                z3 = false;
            }
            if (a3 > 13) {
                z6 = false;
                z5 = true;
            } else if (a3 < 4) {
                z6 = true;
                z5 = false;
            } else {
                z6 = false;
                z5 = false;
            }
            int i7 = (a2 + a3) - 17;
            boolean z7 = (a2 & 1) == 1;
            boolean z8 = (a3 & 1) == 0;
            if (i7 == 1) {
                if (z7) {
                    if (!z8) {
                        z3 = true;
                    } else {
                        throw NotFoundException.m1647a();
                    }
                } else if (z8) {
                    z5 = true;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else if (i7 == -1) {
                if (z7) {
                    if (!z8) {
                        z4 = true;
                    } else {
                        throw NotFoundException.m1647a();
                    }
                } else if (z8) {
                    z6 = true;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else if (i7 != 0) {
                throw NotFoundException.m1647a();
            } else if (z7) {
                if (!z8) {
                    throw NotFoundException.m1647a();
                } else if (a2 < a3) {
                    z4 = true;
                    z5 = true;
                } else {
                    z3 = true;
                    z6 = true;
                }
            } else if (z8) {
                throw NotFoundException.m1647a();
            }
            if (z4) {
                if (!z3) {
                    m2238a(this.f22211e, this.f22209c);
                } else {
                    throw NotFoundException.m1647a();
                }
            }
            if (z3) {
                m2234b(this.f22211e, this.f22209c);
            }
            if (z6) {
                if (!z5) {
                    m2238a(this.f22212f, this.f22209c);
                } else {
                    throw NotFoundException.m1647a();
                }
            }
            if (z5) {
                m2234b(this.f22212f, this.f22210d);
            }
            int i8 = (((kyVar.f22287a * 4) + (z ? 0 : 2)) + (!z2 ? 1 : 0)) - 1;
            int i9 = 0;
            int i10 = 0;
            for (int length2 = iArr2.length - 1; length2 >= 0; length2--) {
                if (m2150a(kyVar, z, z2)) {
                    i9 += iArr2[length2] * f22272k[i8][length2 * 2];
                }
                i10 += iArr2[length2];
            }
            int i11 = 0;
            for (int length3 = iArr3.length - 1; length3 >= 0; length3--) {
                if (m2150a(kyVar, z, z2)) {
                    i11 += iArr3[length3] * f22272k[i8][(length3 * 2) + 1];
                }
            }
            int i12 = i9 + i11;
            if ((i10 & 1) != 0 || i10 > 13 || i10 < 4) {
                throw NotFoundException.m1647a();
            }
            int i13 = (13 - i10) / 2;
            int i14 = f22268g[i13];
            return new DataCharacter((RSSUtils.m2125a(iArr2, i14, true) * f22269h[i13]) + RSSUtils.m2125a(iArr3, 9 - i14, false) + f22270i[i13], i12);
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static boolean m2150a(FinderPattern kyVar, boolean z, boolean z2) {
        return kyVar.f22287a != 0 || !z || !z2;
    }

    /* renamed from: d */
    private void m2143d() throws NotFoundException {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int a = MathUtils.m2528a(this.f22211e);
        int a2 = MathUtils.m2528a(this.f22212f);
        boolean z5 = false;
        if (a > 13) {
            z2 = false;
            z = true;
        } else if (a < 4) {
            z2 = true;
            z = false;
        } else {
            z2 = false;
            z = false;
        }
        if (a2 > 13) {
            z4 = false;
            z3 = true;
        } else if (a2 < 4) {
            z4 = true;
            z3 = false;
        } else {
            z4 = false;
            z3 = false;
        }
        int i = (a + a2) - 17;
        boolean z6 = (a & 1) == 1;
        if ((a2 & 1) == 0) {
            z5 = true;
        }
        if (i == 1) {
            if (z6) {
                if (!z5) {
                    z = true;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else if (z5) {
                z3 = true;
            } else {
                throw NotFoundException.m1647a();
            }
        } else if (i == -1) {
            if (z6) {
                if (!z5) {
                    z2 = true;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else if (z5) {
                z4 = true;
            } else {
                throw NotFoundException.m1647a();
            }
        } else if (i != 0) {
            throw NotFoundException.m1647a();
        } else if (z6) {
            if (!z5) {
                throw NotFoundException.m1647a();
            } else if (a < a2) {
                z3 = true;
                z2 = true;
            } else {
                z4 = true;
                z = true;
            }
        } else if (z5) {
            throw NotFoundException.m1647a();
        }
        if (z2) {
            if (!z) {
                m2238a(this.f22211e, this.f22209c);
            } else {
                throw NotFoundException.m1647a();
            }
        }
        if (z) {
            m2234b(this.f22211e, this.f22209c);
        }
        if (z4) {
            if (!z3) {
                m2238a(this.f22212f, this.f22209c);
            } else {
                throw NotFoundException.m1647a();
            }
        }
        if (z3) {
            m2234b(this.f22212f, this.f22210d);
        }
    }
}
