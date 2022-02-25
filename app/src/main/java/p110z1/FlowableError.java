package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.ban */
/* loaded from: classes3.dex */
public final class FlowableError<T> extends Flowable<T> {

    /* renamed from: b */
    final Callable<? extends Throwable> f18077b;

    public FlowableError(Callable<? extends Throwable> callable) {
        this.f18077b = callable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        Throwable th;
        try {
            th = (Throwable) ObjectHelper.m9873a(this.f18077b.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th2) {
            th = th2;
            Exceptions.m9983b(th);
        }
        EmptySubscription.error(th, dbxVar);
    }
}
