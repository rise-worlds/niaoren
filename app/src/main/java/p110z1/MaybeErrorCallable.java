package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bfo */
/* loaded from: classes3.dex */
public final class MaybeErrorCallable<T> extends Maybe<T> {

    /* renamed from: a */
    final Callable<? extends Throwable> f18599a;

    public MaybeErrorCallable(Callable<? extends Throwable> callable) {
        this.f18599a = callable;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        Throwable th;
        asfVar.onSubscribe(Disposables.m9989b());
        try {
            th = (Throwable) ObjectHelper.m9873a(this.f18599a.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th2) {
            th = th2;
            Exceptions.m9983b(th);
        }
        asfVar.onError(th);
    }
}
