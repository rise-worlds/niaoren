package p110z1;

/* renamed from: z1.bmx */
/* loaded from: classes3.dex */
public final class ObservableTakeUntilPredicate<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Predicate<? super T> f19429b;

    public ObservableTakeUntilPredicate(ObservableSource<T> asqVar, Predicate<? super T> auxVar) {
        super(asqVar);
        this.f19429b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4573a(assVar, this.f19429b));
    }

    /* compiled from: ObservableTakeUntilPredicate.java */
    /* renamed from: z1.bmx$a */
    /* loaded from: classes3.dex */
    static final class C4573a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19430a;

        /* renamed from: b */
        final Predicate<? super T> f19431b;

        /* renamed from: c */
        Disposable f19432c;

        /* renamed from: d */
        boolean f19433d;

        C4573a(Observer<? super T> assVar, Predicate<? super T> auxVar) {
            this.f19430a = assVar;
            this.f19431b = auxVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19432c, atrVar)) {
                this.f19432c = atrVar;
                this.f19430a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19432c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19432c.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19433d) {
                this.f19430a.onNext(t);
                try {
                    if (this.f19431b.test(t)) {
                        this.f19433d = true;
                        this.f19432c.dispose();
                        this.f19430a.onComplete();
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f19432c.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (!this.f19433d) {
                this.f19433d = true;
                this.f19430a.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19433d) {
                this.f19433d = true;
                this.f19430a.onComplete();
            }
        }
    }
}
