package p110z1;

/* renamed from: z1.bny */
/* loaded from: classes3.dex */
public final class ParallelFlatMap<T, R> extends ParallelFlowable<R> {

    /* renamed from: a */
    final ParallelFlowable<T> f19618a;

    /* renamed from: b */
    final Function<? super T, ? extends Publisher<? extends R>> f19619b;

    /* renamed from: c */
    final boolean f19620c;

    /* renamed from: d */
    final int f19621d;

    /* renamed from: e */
    final int f19622e;

    public ParallelFlatMap(ParallelFlowable<T> bubVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, boolean z, int i, int i2) {
        this.f19618a = bubVar;
        this.f19619b = aunVar;
        this.f19620c = z;
        this.f19621d = i;
        this.f19622e = i2;
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19618a.mo9267a();
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public void mo9236a(Subscriber<? super R>[] dbxVarArr) {
        if (m9227b(dbxVarArr)) {
            int length = dbxVarArr.length;
            Subscriber<? super T>[] dbxVarArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                dbxVarArr2[i] = FlowableFlatMap.m9793a(dbxVarArr[i], this.f19619b, this.f19620c, this.f19621d, this.f19622e);
            }
            this.f19618a.mo9236a(dbxVarArr2);
        }
    }
}
