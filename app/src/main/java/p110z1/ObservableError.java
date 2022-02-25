package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bjz */
/* loaded from: classes3.dex */
public final class ObservableError<T> extends Observable<T> {

    /* renamed from: a */
    final Callable<? extends Throwable> f19085a;

    public ObservableError(Callable<? extends Throwable> callable) {
        this.f19085a = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        Throwable th;
        try {
            th = (Throwable) ObjectHelper.m9873a(this.f19085a.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th2) {
            th = th2;
            Exceptions.m9983b(th);
        }
        EmptyDisposable.error(th, assVar);
    }
}
