package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.btt */
/* loaded from: classes3.dex */
public abstract class ResourceCompletableObserver implements CompletableObserver, Disposable {

    /* renamed from: a */
    private final AtomicReference<Disposable> f20122a = new AtomicReference<>();

    /* renamed from: b */
    private final ListCompositeDisposable f20123b = new ListCompositeDisposable();

    /* renamed from: a */
    protected void m9297a() {
    }

    /* renamed from: a */
    public final void m9296a(@AbstractC3889atm Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "resource is null");
        this.f20123b.mo9939a(atrVar);
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public final void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (EndConsumerHelper.m9437a(this.f20122a, atrVar, getClass())) {
            m9297a();
        }
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        if (DisposableHelper.dispose(this.f20122a)) {
            this.f20123b.dispose();
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.f20122a.get());
    }
}
