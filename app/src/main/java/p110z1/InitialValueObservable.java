package p110z1;

/* renamed from: z1.xd */
/* loaded from: classes3.dex */
public abstract class InitialValueObservable<T> extends Observable<T> {
    /* renamed from: a */
    protected abstract T mo37a();

    /* renamed from: b */
    protected abstract void mo36b(Observer<? super T> assVar);

    @Override // p110z1.Observable
    /* renamed from: a */
    protected final void mo34a(Observer<? super T> assVar) {
        mo36b((Observer) assVar);
        assVar.onNext(mo37a());
    }

    /* renamed from: b */
    public final Observable<T> m128b() {
        return new C5591a();
    }

    /* compiled from: InitialValueObservable.java */
    /* renamed from: z1.xd$a */
    /* loaded from: classes3.dex */
    private final class C5591a extends Observable<T> {
        C5591a() {
        }

        @Override // p110z1.Observable
        /* renamed from: a */
        protected void mo34a(Observer<? super T> assVar) {
            InitialValueObservable.this.mo36b((Observer) assVar);
        }
    }
}
