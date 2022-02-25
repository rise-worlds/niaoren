package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.btv */
/* loaded from: classes3.dex */
public abstract class ResourceObserver<T> implements Observer<T>, Disposable {

    /* renamed from: a */
    private final AtomicReference<Disposable> f20126a = new AtomicReference<>();

    /* renamed from: b */
    private final ListCompositeDisposable f20127b = new ListCompositeDisposable();

    /* renamed from: a */
    protected void m9293a() {
    }

    /* renamed from: a */
    public final void m9292a(@AbstractC3889atm Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "resource is null");
        this.f20127b.mo9939a(atrVar);
    }

    @Override // p110z1.Observer
    public final void onSubscribe(Disposable atrVar) {
        if (EndConsumerHelper.m9437a(this.f20126a, atrVar, getClass())) {
            m9293a();
        }
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        if (DisposableHelper.dispose(this.f20126a)) {
            this.f20127b.dispose();
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.f20126a.get());
    }
}
