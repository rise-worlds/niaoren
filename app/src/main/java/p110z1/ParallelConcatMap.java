package p110z1;

/* renamed from: z1.bnu */
/* loaded from: classes3.dex */
public final class ParallelConcatMap<T, R> extends ParallelFlowable<R> {

    /* renamed from: a */
    final ParallelFlowable<T> f19583a;

    /* renamed from: b */
    final Function<? super T, ? extends Publisher<? extends R>> f19584b;

    /* renamed from: c */
    final int f19585c;

    /* renamed from: d */
    final ErrorMode f19586d;

    public ParallelConcatMap(ParallelFlowable<T> bubVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, ErrorMode bsuVar) {
        this.f19583a = bubVar;
        this.f19584b = (Function) ObjectHelper.m9873a(aunVar, "mapper");
        this.f19585c = i;
        this.f19586d = (ErrorMode) ObjectHelper.m9873a(bsuVar, "errorMode");
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19583a.mo9267a();
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public void mo9236a(Subscriber<? super R>[] dbxVarArr) {
        if (m9227b(dbxVarArr)) {
            int length = dbxVarArr.length;
            Subscriber<? super T>[] dbxVarArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                dbxVarArr2[i] = FlowableConcatMap.m9797a(dbxVarArr[i], this.f19584b, this.f19585c, this.f19586d);
            }
            this.f19583a.mo9236a(dbxVarArr2);
        }
    }
}
