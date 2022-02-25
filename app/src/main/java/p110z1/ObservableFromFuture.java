package p110z1;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.bkj */
/* loaded from: classes3.dex */
public final class ObservableFromFuture<T> extends Observable<T> {

    /* renamed from: a */
    final Future<? extends T> f19132a;

    /* renamed from: b */
    final long f19133b;

    /* renamed from: c */
    final TimeUnit f19134c;

    public ObservableFromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.f19132a = future;
        this.f19133b = j;
        this.f19134c = timeUnit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        DeferredScalarDisposable awiVar = new DeferredScalarDisposable(assVar);
        assVar.onSubscribe(awiVar);
        if (!awiVar.isDisposed()) {
            try {
                awiVar.complete(ObjectHelper.m9873a(this.f19134c != null ? this.f19132a.get(this.f19133b, this.f19134c) : this.f19132a.get(), "Future returned null"));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                if (!awiVar.isDisposed()) {
                    assVar.onError(th);
                }
            }
        }
    }
}
