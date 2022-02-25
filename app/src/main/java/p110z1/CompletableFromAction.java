package p110z1;

/* renamed from: z1.axo */
/* loaded from: classes3.dex */
public final class CompletableFromAction extends Completable {

    /* renamed from: a */
    final Action f17722a;

    public CompletableFromAction(Action augVar) {
        this.f17722a = augVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        Disposable a = Disposables.m9995a();
        arpVar.onSubscribe(a);
        try {
            this.f17722a.mo9442a();
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
