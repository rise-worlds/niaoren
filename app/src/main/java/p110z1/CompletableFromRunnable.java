package p110z1;

/* renamed from: z1.axs */
/* loaded from: classes3.dex */
public final class CompletableFromRunnable extends Completable {

    /* renamed from: a */
    final Runnable f17729a;

    public CompletableFromRunnable(Runnable runnable) {
        this.f17729a = runnable;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        Disposable a = Disposables.m9995a();
        arpVar.onSubscribe(a);
        try {
            this.f17729a.run();
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
