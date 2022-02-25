package p110z1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.bgc */
/* loaded from: classes3.dex */
public final class MaybeFromFuture<T> extends Maybe<T> {

    /* renamed from: a */
    final Future<? extends T> f18645a;

    /* renamed from: b */
    final long f18646b;

    /* renamed from: c */
    final TimeUnit f18647c;

    public MaybeFromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.f18645a = future;
        this.f18646b = j;
        this.f18647c = timeUnit;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        Object obj;
        Disposable a = Disposables.m9995a();
        asfVar.onSubscribe(a);
        if (!a.isDisposed()) {
            try {
                if (this.f18646b <= 0) {
                    obj = (Object) this.f18645a.get();
                } else {
                    obj = (Object) this.f18645a.get(this.f18646b, this.f18647c);
                }
                if (a.isDisposed()) {
                    return;
                }
                if (obj == null) {
                    asfVar.onComplete();
                } else {
                    asfVar.onSuccess(obj);
                }
            } catch (Throwable th) {
                th = th;
                if (th instanceof ExecutionException) {
                    th = th.getCause();
                }
                Exceptions.m9983b(th);
                if (!a.isDisposed()) {
                    asfVar.onError(th);
                }
            }
        }
    }
}
