package p110z1;

/* renamed from: z1.bkm */
/* loaded from: classes3.dex */
public final class ObservableFromUnsafeSource<T> extends Observable<T> {

    /* renamed from: a */
    final ObservableSource<T> f19145a;

    public ObservableFromUnsafeSource(ObservableSource<T> asqVar) {
        this.f19145a = asqVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f19145a.subscribe(assVar);
    }
}
