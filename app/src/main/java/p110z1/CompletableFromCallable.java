package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.axp */
/* loaded from: classes3.dex */
public final class CompletableFromCallable extends Completable {

    /* renamed from: a */
    final Callable<?> f17723a;

    public CompletableFromCallable(Callable<?> callable) {
        this.f17723a = callable;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        Disposable a = Disposables.m9995a();
        arpVar.onSubscribe(a);
        try {
            this.f17723a.call();
            if (!a.isDisposed()) {
                arpVar.onComplete();
            }
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            if (!a.isDisposed()) {
                arpVar.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }
    }
}
