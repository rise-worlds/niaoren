package p110z1;

/* renamed from: z1.bnz */
/* loaded from: classes3.dex */
public final class ParallelFromArray<T> extends ParallelFlowable<T> {

    /* renamed from: a */
    final Publisher<T>[] f19623a;

    public ParallelFromArray(Publisher<T>[] dbwVarArr) {
        this.f19623a = dbwVarArr;
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19623a.length;
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public void mo9236a(Subscriber<? super T>[] dbxVarArr) {
        if (m9227b(dbxVarArr)) {
            int length = dbxVarArr.length;
            for (int i = 0; i < length; i++) {
                this.f19623a[i].subscribe(dbxVarArr[i]);
            }
        }
    }
}
