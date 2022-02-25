package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bgd */
/* loaded from: classes3.dex */
public final class MaybeFromRunnable<T> extends Maybe<T> implements Callable<T> {

    /* renamed from: a */
    final Runnable f18648a;

    public MaybeFromRunnable(Runnable runnable) {
        this.f18648a = runnable;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        Disposable a = Disposables.m9995a();
        asfVar.onSubscribe(a);
        if (!a.isDisposed()) {
            try {
                this.f18648a.run();
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
        this.f18648a.run();
        return null;
    }
}
