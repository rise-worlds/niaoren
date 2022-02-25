package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.axn */
/* loaded from: classes3.dex */
public final class CompletableErrorSupplier extends Completable {

    /* renamed from: a */
    final Callable<? extends Throwable> f17721a;

    public CompletableErrorSupplier(Callable<? extends Throwable> callable) {
        this.f17721a = callable;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        Throwable th;
        try {
            th = (Throwable) ObjectHelper.m9873a(this.f17721a.call(), "The error returned is null");
        } catch (Throwable th2) {
            th = th2;
            Exceptions.m9983b(th);
        }
        EmptyDisposable.error(th, arpVar);
    }
}
