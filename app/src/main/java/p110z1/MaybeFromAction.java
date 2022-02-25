package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bfz */
/* loaded from: classes3.dex */
public final class MaybeFromAction<T> extends Maybe<T> implements Callable<T> {

    /* renamed from: a */
    final Action f18640a;

    public MaybeFromAction(Action augVar) {
        this.f18640a = augVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        Disposable a = Disposables.m9995a();
        asfVar.onSubscribe(a);
        if (!a.isDisposed()) {
            try {
                this.f18640a.mo9442a();
                if (!a.isDisposed()) {
                    asfVar.onComplete();
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                if (!a.isDisposed()) {
                    asfVar.onError(th);
                } else {
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }

    @Override // java.util.concurrent.Callable
    public T call() throws Exception {
        this.f18640a.mo9442a();
        return null;
    }
}
