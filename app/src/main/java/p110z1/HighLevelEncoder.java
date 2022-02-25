package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: z1.gd */
/* loaded from: classes3.dex */
public final class HighLevelEncoder {

    /* renamed from: b */
    static final int f21761b = 0;

    /* renamed from: c */
    static final int f21762c = 1;

    /* renamed from: d */
    static final int f21763d = 2;

    /* renamed from: e */
    static final int f21764e = 3;

    /* renamed from: f */
    static final int f21765f = 4;

    /* renamed from: h */
    static final int[][] f21767h;

    /* renamed from: j */
    private static final int[][] f21768j;

    /* renamed from: i */
    final byte[] f21769i;

    /* renamed from: a */
    static final String[] f21760a = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};

    /* renamed from: g */
    static final int[][] f21766g = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};

    static {
        int[][] iArr = (int[][]) Array.newInstance(int.class, 5, 256);
        f21768j = iArr;
        iArr[0][32] = 1;
        for (int i = 65; i <= 90; i++) {
            f21768j[0][i] = (i - 65) + 2;
        }
        f21768j[1][32] = 1;
        for (int i2 = 97; i2 <= 122; i2++) {
            f21768j[1][i2] = (i2 - 97) + 2;
        }
        f21768j[2][32] = 1;
        for (int i3 = 48; i3 <= 57; i3++) {
            f21768j[2][i3] = (i3 - 48) + 2;
        }
        int[][] iArr2 = f21768j;
        iArr2[2][44] = 12;
        iArr2[2][46] = 13;
        int[] iArr3 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, TbsListener.ErrorCode.DOWNLOAD_REDIRECT_EMPTY, TbsListener.ErrorCode.PV_UPLOAD_ERROR, 127};
        for (int i4 = 0; i4 < 28; i4++) {
            f21768j[3][iArr3[i4]] = i4;
        }
        int[] iArr4 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED, TbsListener.ErrorCode.DOWNLOAD_THROWABLE};
        for (int i5 = 0; i5 < 31; i5++) {
            if (iArr4[i5] > 0) {
                f21768j[4][iArr4[i5]] = i5;
            }
        }
        int[][] iArr5 = (int[][]) Array.newInstance(int.class, 6, 6);
        f21767h = iArr5;
        for (int[] iArr6 : iArr5) {
            Arrays.fill(iArr6, -1);
        }
        int[][] iArr7 = f21767h;
        iArr7[0][4] = 0;
        iArr7[1][4] = 0;
        iArr7[1][0] = 28;
        iArr7[3][4] = 0;
        iArr7[2][4] = 0;
        iArr7[2][0] = 15;
    }

    public HighLevelEncoder(byte[] bArr) {
        this.f21769i = bArr;
    }

    /* renamed from: a */
    private BitArray m2753a() {
        Collection<State> singletonList = Collections.singletonList(State.f21773a);
        int i = 0;
        while (true) {
            byte[] bArr = this.f21769i;
            if (i >= bArr.length) {
                return ((State) Collections.min(singletonList, new C53621())).m2742a(this.f21769i);
            }
            int i2 = i + 1;
            byte b = i2 < bArr.length ? bArr[i2] : (byte) 0;
            byte b2 = this.f21769i[i];
            int i3 = b2 != 13 ? b2 != 44 ? b2 != 46 ? b2 != 58 ? 0 : b == 32 ? 5 : 0 : b == 32 ? 3 : 0 : b == 32 ? 4 : 0 : b == 10 ? 2 : 0;
            if (i3 > 0) {
                LinkedList linkedList = new LinkedList();
                for (State gfVar : singletonList) {
                    State b3 = gfVar.m2740b(i);
                    linkedList.add(b3.m2744a(4, i3));
                    if (gfVar.f21774b != 4) {
                        linkedList.add(b3.m2739b(4, i3));
                    }
                    if (i3 == 3 || i3 == 4) {
                        linkedList.add(b3.m2744a(2, 16 - i3).m2744a(2, 1));
                    }
                    if (gfVar.f21775c > 0) {
                        linkedList.add(gfVar.m2745a(i).m2745a(i2));
                    }
                }
                singletonList = m2752a(linkedList);
                i = i2;
            } else {
                singletonList = m2751a(singletonList, i);
            }
            i++;
        }
    }

    /* compiled from: HighLevelEncoder.java */
    /* renamed from: z1.gd$1 */
    /* loaded from: classes3.dex */
    final class C53621 implements Comparator<State> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C53621() {
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(State gfVar, State gfVar2) {
            return gfVar.f21776d - gfVar2.f21776d;
        }

        /* renamed from: a */
        private static int m2747a(State gfVar, State gfVar2) {
            return gfVar.f21776d - gfVar2.f21776d;
        }
    }

    /* renamed from: a */
    private Collection<State> m2751a(Iterable<State> iterable, int i) {
        LinkedList linkedList = new LinkedList();
        for (State gfVar : iterable) {
            m2748a(gfVar, i, linkedList);
        }
        return m2752a(linkedList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m2748a(State gfVar, int i, Collection<State> collection) {
        char c = (char) (this.f21769i[i] & 255);
        boolean z = f21768j[gfVar.f21774b][c] > 0;
        State gfVar2 = null;
        for (int i2 = 0; i2 <= 4; i2++) {
            int i3 = f21768j[i2][c];
            if (i3 > 0) {
                if (gfVar2 == null) {
                    gfVar2 = gfVar.m2740b(i);
                }
                if (!z || i2 == gfVar.f21774b || i2 == 2) {
                    collection.add(gfVar2.m2744a(i2, i3));
                }
                if (!z && f21767h[gfVar.f21774b][i2] >= 0) {
                    collection.add(gfVar2.m2739b(i2, i3));
                }
            }
        }
        if (gfVar.f21775c > 0 || f21768j[gfVar.f21774b][c] == 0) {
            collection.add(gfVar.m2745a(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Collection<State> m2750a(Iterable<State> iterable, int i, int i2) {
        LinkedList linkedList = new LinkedList();
        for (State gfVar : iterable) {
            State b = gfVar.m2740b(i);
            linkedList.add(b.m2744a(4, i2));
            if (gfVar.f21774b != 4) {
                linkedList.add(b.m2739b(4, i2));
            }
            if (i2 == 3 || i2 == 4) {
                linkedList.add(b.m2744a(2, 16 - i2).m2744a(2, 1));
            }
            if (gfVar.f21775c > 0) {
                linkedList.add(gfVar.m2745a(i).m2745a(i + 1));
            }
        }
        return m2752a(linkedList);
    }

    /* renamed from: a */
    private static void m2749a(State gfVar, int i, int i2, Collection<State> collection) {
        State b = gfVar.m2740b(i);
        collection.add(b.m2744a(4, i2));
        if (gfVar.f21774b != 4) {
            collection.add(b.m2739b(4, i2));
        }
        if (i2 == 3 || i2 == 4) {
            collection.add(b.m2744a(2, 16 - i2).m2744a(2, 1));
        }
        if (gfVar.f21775c > 0) {
            collection.add(gfVar.m2745a(i).m2745a(i + 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Collection<State> m2752a(Iterable<State> iterable) {
        LinkedList linkedList = new LinkedList();
        for (State gfVar : iterable) {
            boolean z = true;
            Iterator it = linkedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                State gfVar2 = (State) it.next();
                if (gfVar2.m2743a(gfVar)) {
                    z = false;
                    break;
                } else if (gfVar.m2743a(gfVar2)) {
                    it.remove();
                }
            }
            if (z) {
                linkedList.add(gfVar);
            }
        }
        return linkedList;
    }
}
