package p110z1;

import java.util.LinkedList;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.gf */
/* loaded from: classes3.dex */
public final class State {

    /* renamed from: a */
    static final State f21773a = new State(AbstractC5363gg.f21778a, 0, 0, 0);

    /* renamed from: b */
    final int f21774b;

    /* renamed from: c */
    final int f21775c;

    /* renamed from: d */
    final int f21776d;

    /* renamed from: e */
    private final AbstractC5363gg f21777e;

    private State(AbstractC5363gg ggVar, int i, int i2, int i3) {
        this.f21777e = ggVar;
        this.f21774b = i;
        this.f21775c = i2;
        this.f21776d = i3;
    }

    /* renamed from: a */
    private int m2746a() {
        return this.f21774b;
    }

    /* renamed from: b */
    private AbstractC5363gg m2741b() {
        return this.f21777e;
    }

    /* renamed from: c */
    private int m2738c() {
        return this.f21775c;
    }

    /* renamed from: d */
    private int m2737d() {
        return this.f21776d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final State m2744a(int i, int i2) {
        int i3 = this.f21776d;
        AbstractC5363gg ggVar = this.f21777e;
        if (i != this.f21774b) {
            int i4 = HighLevelEncoder.f21766g[this.f21774b][i];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            ggVar = ggVar.m2735a(i5, i6);
            i3 += i6;
        }
        int i7 = i == 2 ? 4 : 5;
        return new State(ggVar.m2735a(i2, i7), i, 0, i3 + i7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final State m2739b(int i, int i2) {
        AbstractC5363gg ggVar = this.f21777e;
        int i3 = this.f21774b == 2 ? 4 : 5;
        return new State(ggVar.m2735a(HighLevelEncoder.f21767h[this.f21774b][i], i3).m2735a(i2, 5), this.f21774b, 0, this.f21776d + i3 + 5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final State m2745a(int i) {
        AbstractC5363gg ggVar = this.f21777e;
        int i2 = this.f21774b;
        int i3 = this.f21776d;
        if (i2 == 4 || i2 == 2) {
            int i4 = HighLevelEncoder.f21766g[i2][0];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            ggVar = ggVar.m2735a(i5, i6);
            i3 += i6;
            i2 = 0;
        }
        int i7 = this.f21775c;
        State gfVar = new State(ggVar, i2, this.f21775c + 1, i3 + ((i7 == 0 || i7 == 31) ? 18 : i7 == 62 ? 9 : 8));
        return gfVar.f21775c == 2078 ? gfVar.m2740b(i + 1) : gfVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final State m2740b(int i) {
        int i2 = this.f21775c;
        return i2 == 0 ? this : new State(new BinaryShiftToken(this.f21777e, i - i2, i2), this.f21774b, 0, this.f21776d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m2743a(State gfVar) {
        int i;
        int i2 = this.f21776d + (HighLevelEncoder.f21766g[this.f21774b][gfVar.f21774b] >> 16);
        int i3 = gfVar.f21775c;
        if (i3 > 0 && ((i = this.f21775c) == 0 || i > i3)) {
            i2 += 10;
        }
        return i2 <= gfVar.f21776d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final BitArray m2742a(byte[] bArr) {
        LinkedList<AbstractC5363gg> linkedList = new LinkedList();
        for (AbstractC5363gg ggVar = m2740b(bArr.length).f21777e; ggVar != null; ggVar = ggVar.f21779b) {
            linkedList.addFirst(ggVar);
        }
        BitArray huVar = new BitArray();
        for (AbstractC5363gg ggVar2 : linkedList) {
            ggVar2.mo2734a(huVar, bArr);
        }
        return huVar;
    }

    public final String toString() {
        return String.format("%s bits=%d bytes=%d", HighLevelEncoder.f21760a[this.f21774b], Integer.valueOf(this.f21776d), Integer.valueOf(this.f21775c));
    }
}
