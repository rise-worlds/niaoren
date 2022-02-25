package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bki */
/* loaded from: classes3.dex */
public final class ObservableFromCallable<T> extends Observable<T> implements Callable<T> {

    /* renamed from: a */
    final Callable<? extends T> f19131a;

    public ObservableFromCallable(Callable<? extends T> callable) {
        this.f19131a = callable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        DeferredScalarDisposable awiVar = new DeferredScalarDisposable(assVar);
        assVar.onSubscribe(awiVar);
        if (!awiVar.isDisposed()) {
            try {
                awiVar.complete(ObjectHelper.m9873a(this.f19131a.call(), "Callable returned null"));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                if (!awiVar.isDisposed()) {
                    assVar.onError(th);
                } else {
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }

    @Override // java.util.concurrent.Callable
    public T call() throws Exception {
        return (T) ObjectHelper.m9873a(this.f19131a.call(), "The callable returned a null value");
    }
}
