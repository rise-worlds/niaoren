package p110z1;

@Experimental
/* renamed from: z1.bgn */
/* loaded from: classes3.dex */
public final class MaybeMaterialize<T> extends Single<Notification<T>> {

    /* renamed from: a */
    final Maybe<T> f18670a;

    public MaybeMaterialize(Maybe<T> ascVar) {
        this.f18670a = ascVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Notification<T>> asxVar) {
        this.f18670a.mo10646a((MaybeObserver) new MaterializeSingleObserver(asxVar));
    }
}
