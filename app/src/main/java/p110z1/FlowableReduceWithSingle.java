package p110z1;

import java.util.concurrent.Callable;
import p110z1.FlowableReduceSeedSingle;

/* renamed from: z1.bco */
/* loaded from: classes3.dex */
public final class FlowableReduceWithSingle<T, R> extends Single<R> {

    /* renamed from: a */
    final Publisher<T> f18264a;

    /* renamed from: b */
    final Callable<R> f18265b;

    /* renamed from: c */
    final BiFunction<R, ? super T, R> f18266c;

    public FlowableReduceWithSingle(Publisher<T> dbwVar, Callable<R> callable, BiFunction<R, ? super T, R> auiVar) {
        this.f18264a = dbwVar;
        this.f18265b = callable;
        this.f18266c = auiVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        try {
            this.f18264a.subscribe(new FlowableReduceSeedSingle.C4153a(asxVar, this.f18266c, ObjectHelper.m9873a(this.f18265b.call(), "The seedSupplier returned a null value")));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asxVar);
        }
    }
}
