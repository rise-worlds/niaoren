package p110z1;

/* renamed from: z1.bld */
/* loaded from: classes3.dex */
public final class ObservableMaterialize<T> extends AbstractObservableWithUpstream<T, Notification<T>> {
    public ObservableMaterialize(ObservableSource<T> asqVar) {
        super(asqVar);
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Notification<T>> assVar) {
        this.f18807a.subscribe(new C4493a(assVar));
    }

    /* compiled from: ObservableMaterialize.java */
    /* renamed from: z1.bld$a */
    /* loaded from: classes3.dex */
    static final class C4493a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super Notification<T>> f19234a;

        /* renamed from: b */
        Disposable f19235b;

        C4493a(Observer<? super Notification<T>> assVar) {
            this.f19234a = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19235b, atrVar)) {
                this.f19235b = atrVar;
                this.f19234a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19235b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19235b.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19234a.onNext(Notification.m10643a(t));
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19234a.onNext(Notification.m10642a(th));
            this.f19234a.onComplete();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19234a.onNext(Notification.m10637f());
            this.f19234a.onComplete();
        }
    }
}
