package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.btu */
/* loaded from: classes3.dex */
public abstract class ResourceMaybeObserver<T> implements MaybeObserver<T>, Disposable {

    /* renamed from: a */
    private final AtomicReference<Disposable> f20124a = new AtomicReference<>();

    /* renamed from: b */
    private final ListCompositeDisposable f20125b = new ListCompositeDisposable();

    /* renamed from: a */
    protected void m9295a() {
    }

    /* renamed from: a */
    public final void m9294a(@AbstractC3889atm Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "resource is null");
        this.f20125b.mo9939a(atrVar);
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public final void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (EndConsumerHelper.m9437a(this.f20124a, atrVar, getClass())) {
            m9295a();
        }
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        if (DisposableHelper.dispose(this.f20124a)) {
            this.f20125b.dispose();
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.f20124a.get());
    }
}
