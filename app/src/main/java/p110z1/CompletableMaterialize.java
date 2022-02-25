package p110z1;

@Experimental
/* renamed from: z1.axx */
/* loaded from: classes3.dex */
public final class CompletableMaterialize<T> extends Single<Notification<T>> {

    /* renamed from: a */
    final Completable f17738a;

    public CompletableMaterialize(Completable armVar) {
        this.f17738a = armVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Notification<T>> asxVar) {
        this.f17738a.mo11309a((CompletableObserver) new MaterializeSingleObserver(asxVar));
    }
}
