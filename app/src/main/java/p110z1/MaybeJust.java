package p110z1;

/* renamed from: z1.bgk */
/* loaded from: classes3.dex */
public final class MaybeJust<T> extends Maybe<T> implements ScalarCallable<T> {

    /* renamed from: a */
    final T f18664a;

    public MaybeJust(T t) {
        this.f18664a = t;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        asfVar.onSubscribe(Disposables.m9989b());
        asfVar.onSuccess((T) this.f18664a);
    }

    @Override // p110z1.ScalarCallable, java.util.concurrent.Callable
    public T call() {
        return this.f18664a;
    }
}
