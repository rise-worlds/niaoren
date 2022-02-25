package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bax */
/* loaded from: classes3.dex */
public final class FlowableFromCallable<T> extends Flowable<T> implements Callable<T> {

    /* renamed from: b */
    final Callable<? extends T> f18107b;

    public FlowableFromCallable(Callable<? extends T> callable) {
        this.f18107b = callable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        DeferredScalarSubscription bsgVar = new DeferredScalarSubscription(dbxVar);
        dbxVar.onSubscribe(bsgVar);
        try {
            bsgVar.complete(ObjectHelper.m9873a(this.f18107b.call(), "The callable returned a null value"));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            if (bsgVar.isCancelled()) {
                RxJavaPlugins.m9212a(th);
            } else {
                dbxVar.onError(th);
            }
        }
    }

    @Override // java.util.concurrent.Callable
    public T call() throws Exception {
        return (T) ObjectHelper.m9873a(this.f18107b.call(), "The callable returned a null value");
    }
}
