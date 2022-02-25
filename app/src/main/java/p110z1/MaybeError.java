package p110z1;

/* renamed from: z1.bfn */
/* loaded from: classes3.dex */
public final class MaybeError<T> extends Maybe<T> {

    /* renamed from: a */
    final Throwable f18598a;

    public MaybeError(Throwable th) {
        this.f18598a = th;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        asfVar.onSubscribe(Disposables.m9989b());
        asfVar.onError(this.f18598a);
    }
}
