package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.axf */
/* loaded from: classes3.dex */
public final class CompletableDefer extends Completable {

    /* renamed from: a */
    final Callable<? extends CompletableSource> f17698a;

    public CompletableDefer(Callable<? extends CompletableSource> callable) {
        this.f17698a = callable;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        try {
            ((CompletableSource) ObjectHelper.m9873a(this.f17698a.call(), "The completableSupplier returned a null CompletableSource")).mo11309a(arpVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, arpVar);
        }
    }
}
