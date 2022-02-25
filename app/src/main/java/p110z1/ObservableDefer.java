package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bjk */
/* loaded from: classes3.dex */
public final class ObservableDefer<T> extends Observable<T> {

    /* renamed from: a */
    final Callable<? extends ObservableSource<? extends T>> f19001a;

    public ObservableDefer(Callable<? extends ObservableSource<? extends T>> callable) {
        this.f19001a = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        try {
            ((ObservableSource) ObjectHelper.m9873a(this.f19001a.call(), "null ObservableSource supplied")).subscribe(assVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
        }
    }
}
