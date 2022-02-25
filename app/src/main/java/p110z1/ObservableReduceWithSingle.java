package p110z1;

import java.util.concurrent.Callable;
import p110z1.ObservableReduceSeedSingle;

/* renamed from: z1.blr */
/* loaded from: classes3.dex */
public final class ObservableReduceWithSingle<T, R> extends Single<R> {

    /* renamed from: a */
    final ObservableSource<T> f19286a;

    /* renamed from: b */
    final Callable<R> f19287b;

    /* renamed from: c */
    final BiFunction<R, ? super T, R> f19288c;

    public ObservableReduceWithSingle(ObservableSource<T> asqVar, Callable<R> callable, BiFunction<R, ? super T, R> auiVar) {
        this.f19286a = asqVar;
        this.f19287b = callable;
        this.f19288c = auiVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        try {
            this.f19286a.subscribe(new ObservableReduceSeedSingle.C4511a(asxVar, this.f19288c, ObjectHelper.m9873a(this.f19287b.call(), "The seedSupplier returned a null value")));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asxVar);
        }
    }
}
