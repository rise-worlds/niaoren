package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.btq */
/* loaded from: classes3.dex */
public abstract class DisposableObserver<T> implements Observer<T>, Disposable {

    /* renamed from: f */
    final AtomicReference<Disposable> f20120f = new AtomicReference<>();

    /* renamed from: c */
    protected void m9299c() {
    }

    @Override // p110z1.Observer
    public final void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (EndConsumerHelper.m9437a(this.f20120f, atrVar, getClass())) {
            m9299c();
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return this.f20120f.get() == DisposableHelper.DISPOSED;
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        DisposableHelper.dispose(this.f20120f);
    }
}
