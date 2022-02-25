package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.atw */
/* loaded from: classes3.dex */
public final class SerialDisposable implements Disposable {

    /* renamed from: a */
    final AtomicReference<Disposable> f17514a;

    public SerialDisposable() {
        this.f17514a = new AtomicReference<>();
    }

    public SerialDisposable(@atn Disposable atrVar) {
        this.f17514a = new AtomicReference<>(atrVar);
    }

    /* renamed from: a */
    public boolean m9987a(@atn Disposable atrVar) {
        return DisposableHelper.set(this.f17514a, atrVar);
    }

    /* renamed from: b */
    public boolean m9986b(@atn Disposable atrVar) {
        return DisposableHelper.replace(this.f17514a, atrVar);
    }

    @atn
    /* renamed from: a */
    public Disposable m9988a() {
        Disposable atrVar = this.f17514a.get();
        return atrVar == DisposableHelper.DISPOSED ? Disposables.m9989b() : atrVar;
    }

    @Override // p110z1.Disposable
    public void dispose() {
        DisposableHelper.dispose(this.f17514a);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.f17514a.get());
    }
}
