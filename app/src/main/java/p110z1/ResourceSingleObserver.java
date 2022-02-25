package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.btw */
/* loaded from: classes3.dex */
public abstract class ResourceSingleObserver<T> implements SingleObserver<T>, Disposable {

    /* renamed from: a */
    private final AtomicReference<Disposable> f20128a = new AtomicReference<>();

    /* renamed from: b */
    private final ListCompositeDisposable f20129b = new ListCompositeDisposable();

    /* renamed from: a */
    protected void m9291a() {
    }

    /* renamed from: a */
    public final void m9290a(@AbstractC3889atm Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "resource is null");
        this.f20129b.mo9939a(atrVar);
    }

    @Override // p110z1.SingleObserver
    public final void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (EndConsumerHelper.m9437a(this.f20128a, atrVar, getClass())) {
            m9291a();
        }
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        if (DisposableHelper.dispose(this.f20128a)) {
            this.f20129b.dispose();
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.f20128a.get());
    }
}
