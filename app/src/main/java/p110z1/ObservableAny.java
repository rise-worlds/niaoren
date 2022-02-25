package p110z1;

/* renamed from: z1.bin */
/* loaded from: classes3.dex */
public final class ObservableAny<T> extends AbstractObservableWithUpstream<T, Boolean> {

    /* renamed from: b */
    final Predicate<? super T> f18845b;

    public ObservableAny(ObservableSource<T> asqVar, Predicate<? super T> auxVar) {
        super(asqVar);
        this.f18845b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Boolean> assVar) {
        this.f18807a.subscribe(new C4387a(assVar, this.f18845b));
    }

    /* compiled from: ObservableAny.java */
    /* renamed from: z1.bin$a */
    /* loaded from: classes3.dex */
    static final class C4387a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super Boolean> f18846a;

        /* renamed from: b */
        final Predicate<? super T> f18847b;

        /* renamed from: c */
        Disposable f18848c;

        /* renamed from: d */
        boolean f18849d;

        C4387a(Observer<? super Boolean> assVar, Predicate<? super T> auxVar) {
            this.f18846a = assVar;
            this.f18847b = auxVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18848c, atrVar)) {
                this.f18848c = atrVar;
                this.f18846a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f18849d) {
                try {
                    if (this.f18847b.test(t)) {
                        this.f18849d = true;
                        this.f18848c.dispose();
                        this.f18846a.onNext(true);
                        this.f18846a.onComplete();
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f18848c.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f18849d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18849d = true;
            this.f18846a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f18849d) {
                this.f18849d = true;
                this.f18846a.onNext(false);
                this.f18846a.onComplete();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18848c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18848c.isDisposed();
        }
    }
}
