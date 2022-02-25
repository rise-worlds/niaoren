package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bto */
/* loaded from: classes3.dex */
public abstract class DisposableCompletableObserver implements CompletableObserver, Disposable {

    /* renamed from: a */
    final AtomicReference<Disposable> f20118a = new AtomicReference<>();

    /* renamed from: a */
    protected void m9301a() {
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public final void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (EndConsumerHelper.m9437a(this.f20118a, atrVar, getClass())) {
            m9301a();
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return this.f20118a.get() == DisposableHelper.DISPOSED;
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        DisposableHelper.dispose(this.f20118a);
    }
}
