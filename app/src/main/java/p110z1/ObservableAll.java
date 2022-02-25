package p110z1;

/* renamed from: z1.bik */
/* loaded from: classes3.dex */
public final class ObservableAll<T> extends AbstractObservableWithUpstream<T, Boolean> {

    /* renamed from: b */
    final Predicate<? super T> f18829b;

    public ObservableAll(ObservableSource<T> asqVar, Predicate<? super T> auxVar) {
        super(asqVar);
        this.f18829b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Boolean> assVar) {
        this.f18807a.subscribe(new C4383a(assVar, this.f18829b));
    }

    /* compiled from: ObservableAll.java */
    /* renamed from: z1.bik$a */
    /* loaded from: classes3.dex */
    static final class C4383a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super Boolean> f18830a;

        /* renamed from: b */
        final Predicate<? super T> f18831b;

        /* renamed from: c */
        Disposable f18832c;

        /* renamed from: d */
        boolean f18833d;

        C4383a(Observer<? super Boolean> assVar, Predicate<? super T> auxVar) {
            this.f18830a = assVar;
            this.f18831b = auxVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18832c, atrVar)) {
                this.f18832c = atrVar;
                this.f18830a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f18833d) {
                try {
                    if (!this.f18831b.test(t)) {
                        this.f18833d = true;
                        this.f18832c.dispose();
                        this.f18830a.onNext(false);
                        this.f18830a.onComplete();
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f18832c.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f18833d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18833d = true;
            this.f18830a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f18833d) {
                this.f18833d = true;
                this.f18830a.onNext(true);
                this.f18830a.onComplete();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18832c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18832c.isDisposed();
        }
    }
}
