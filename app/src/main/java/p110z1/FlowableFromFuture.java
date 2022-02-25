package p110z1;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.bay */
/* loaded from: classes3.dex */
public final class FlowableFromFuture<T> extends Flowable<T> {

    /* renamed from: b */
    final Future<? extends T> f18108b;

    /* renamed from: c */
    final long f18109c;

    /* renamed from: d */
    final TimeUnit f18110d;

    public FlowableFromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.f18108b = future;
        this.f18109c = j;
        this.f18110d = timeUnit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        DeferredScalarSubscription bsgVar = new DeferredScalarSubscription(dbxVar);
        dbxVar.onSubscribe(bsgVar);
        try {
            Object obj = this.f18110d != null ? this.f18108b.get(this.f18109c, this.f18110d) : this.f18108b.get();
            if (obj == null) {
                dbxVar.onError(new NullPointerException("The future returned null"));
            } else {
                bsgVar.complete(obj);
            }
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            if (!bsgVar.isCancelled()) {
                dbxVar.onError(th);
            }
        }
    }
}
