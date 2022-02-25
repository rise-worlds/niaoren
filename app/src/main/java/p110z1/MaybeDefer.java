package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bfb */
/* loaded from: classes3.dex */
public final class MaybeDefer<T> extends Maybe<T> {

    /* renamed from: a */
    final Callable<? extends MaybeSource<? extends T>> f18563a;

    public MaybeDefer(Callable<? extends MaybeSource<? extends T>> callable) {
        this.f18563a = callable;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        try {
            ((MaybeSource) ObjectHelper.m9873a(this.f18563a.call(), "The maybeSupplier returned a null MaybeSource")).mo10646a(asfVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asfVar);
        }
    }
}
