package p110z1;

/* renamed from: z1.btn */
/* loaded from: classes3.dex */
public abstract class DefaultObserver<T> implements Observer<T> {

    /* renamed from: a */
    private Disposable f20117a;

    /* renamed from: c */
    protected void m9302c() {
    }

    @Override // p110z1.Observer
    public final void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (EndConsumerHelper.m9435a(this.f20117a, atrVar, getClass())) {
            this.f20117a = atrVar;
            m9302c();
        }
    }

    /* renamed from: b */
    protected final void m9303b() {
        Disposable atrVar = this.f20117a;
        this.f20117a = DisposableHelper.DISPOSED;
        atrVar.dispose();
    }
}
