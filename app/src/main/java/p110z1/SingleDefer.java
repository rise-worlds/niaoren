package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bon */
/* loaded from: classes3.dex */
public final class SingleDefer<T> extends Single<T> {

    /* renamed from: a */
    final Callable<? extends SingleSource<? extends T>> f19703a;

    public SingleDefer(Callable<? extends SingleSource<? extends T>> callable) {
        this.f19703a = callable;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        try {
            ((SingleSource) ObjectHelper.m9873a(this.f19703a.call(), "The singleSupplier returned a null SingleSource")).mo10018a(asxVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asxVar);
        }
    }
}
