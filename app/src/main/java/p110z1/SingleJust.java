package p110z1;

/* renamed from: z1.bpr */
/* loaded from: classes3.dex */
public final class SingleJust<T> extends Single<T> {

    /* renamed from: a */
    final T f19806a;

    public SingleJust(T t) {
        this.f19806a = t;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        asxVar.onSubscribe(Disposables.m9989b());
        asxVar.onSuccess((T) this.f19806a);
    }
}
