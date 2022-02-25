package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bga */
/* loaded from: classes3.dex */
public final class MaybeFromCallable<T> extends Maybe<T> implements Callable<T> {

    /* renamed from: a */
    final Callable<? extends T> f18641a;

    public MaybeFromCallable(Callable<? extends T> callable) {
        this.f18641a = callable;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        Disposable a = Disposables.m9995a();
        asfVar.onSubscribe(a);
        if (!a.isDisposed()) {
            try {
                Object obj = (Object) this.f18641a.call();
                if (a.isDisposed()) {
                    return;
                }
                if (obj == 0) {
                    asfVar.onComplete();
                } else {
                    asfVar.onSuccess(obj);
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
        return (T) this.f18641a.call();
    }
}
