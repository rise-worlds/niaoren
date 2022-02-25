package p110z1;

/* renamed from: z1.bmo */
/* loaded from: classes3.dex */
public final class ObservableSkipWhile<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Predicate<? super T> f19397b;

    public ObservableSkipWhile(ObservableSource<T> asqVar, Predicate<? super T> auxVar) {
        super(asqVar);
        this.f19397b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4561a(assVar, this.f19397b));
    }

    /* compiled from: ObservableSkipWhile.java */
    /* renamed from: z1.bmo$a */
    /* loaded from: classes3.dex */
    static final class C4561a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19398a;

        /* renamed from: b */
        final Predicate<? super T> f19399b;

        /* renamed from: c */
        Disposable f19400c;

        /* renamed from: d */
        boolean f19401d;

        C4561a(Observer<? super T> assVar, Predicate<? super T> auxVar) {
            this.f19398a = assVar;
            this.f19399b = auxVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19400c, atrVar)) {
                this.f19400c = atrVar;
                this.f19398a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19400c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19400c.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (this.f19401d) {
                this.f19398a.onNext(t);
                return;
            }
            try {
                if (!this.f19399b.test(t)) {
                    this.f19401d = true;
                    this.f19398a.onNext(t);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f19400c.dispose();
                this.f19398a.onError(th);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19398a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19398a.onComplete();
        }
    }
}
