package p110z1;

/* renamed from: z1.axm */
/* loaded from: classes3.dex */
public final class CompletableError extends Completable {

    /* renamed from: a */
    final Throwable f17720a;

    public CompletableError(Throwable th) {
        this.f17720a = th;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        EmptyDisposable.error(this.f17720a, arpVar);
    }
}
