package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bpm */
/* loaded from: classes3.dex */
public final class SingleFromCallable<T> extends Single<T> {

    /* renamed from: a */
    final Callable<? extends T> f19793a;

    public SingleFromCallable(Callable<? extends T> callable) {
        this.f19793a = callable;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        Disposable a = Disposables.m9995a();
        asxVar.onSubscribe(a);
        if (!a.isDisposed()) {
            try {
                Object obj = (Object) ObjectHelper.m9873a(this.f19793a.call(), "The callable returned a null value");
                if (!a.isDisposed()) {
                    asxVar.onSuccess(obj);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                if (!a.isDisposed()) {
                    asxVar.onError(th);
                } else {
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }
}
