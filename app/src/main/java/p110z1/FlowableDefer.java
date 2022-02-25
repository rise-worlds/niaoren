package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.azy */
/* loaded from: classes3.dex */
public final class FlowableDefer<T> extends Flowable<T> {

    /* renamed from: b */
    final Callable<? extends Publisher<? extends T>> f17992b;

    public FlowableDefer(Callable<? extends Publisher<? extends T>> callable) {
        this.f17992b = callable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        try {
            ((Publisher) ObjectHelper.m9873a(this.f17992b.call(), "The publisher supplied is null")).subscribe(dbxVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }
}
