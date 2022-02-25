package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bpf */
/* loaded from: classes3.dex */
public final class SingleError<T> extends Single<T> {

    /* renamed from: a */
    final Callable<? extends Throwable> f19774a;

    public SingleError(Callable<? extends Throwable> callable) {
        this.f19774a = callable;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        Throwable th;
        try {
            th = (Throwable) ObjectHelper.m9873a(this.f19774a.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th2) {
            th = th2;
            Exceptions.m9983b(th);
        }
        EmptyDisposable.error(th, asxVar);
    }
}
