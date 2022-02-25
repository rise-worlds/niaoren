package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.btr */
/* loaded from: classes3.dex */
public abstract class DisposableSingleObserver<T> implements SingleObserver<T>, Disposable {

    /* renamed from: a */
    final AtomicReference<Disposable> f20121a = new AtomicReference<>();

    /* renamed from: a */
    protected void m9298a() {
    }

    @Override // p110z1.SingleObserver
    public final void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (EndConsumerHelper.m9437a(this.f20121a, atrVar, getClass())) {
            m9298a();
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return this.f20121a.get() == DisposableHelper.DISPOSED;
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        DisposableHelper.dispose(this.f20121a);
    }
}
