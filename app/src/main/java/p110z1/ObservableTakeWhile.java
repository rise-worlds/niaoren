package p110z1;

/* renamed from: z1.bmy */
/* loaded from: classes3.dex */
public final class ObservableTakeWhile<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Predicate<? super T> f19434b;

    public ObservableTakeWhile(ObservableSource<T> asqVar, Predicate<? super T> auxVar) {
        super(asqVar);
        this.f19434b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4574a(assVar, this.f19434b));
    }

    /* compiled from: ObservableTakeWhile.java */
    /* renamed from: z1.bmy$a */
    /* loaded from: classes3.dex */
    static final class C4574a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19435a;

        /* renamed from: b */
        final Predicate<? super T> f19436b;

        /* renamed from: c */
        Disposable f19437c;

        /* renamed from: d */
        boolean f19438d;

        C4574a(Observer<? super T> assVar, Predicate<? super T> auxVar) {
            this.f19435a = assVar;
            this.f19436b = auxVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19437c, atrVar)) {
                this.f19437c = atrVar;
                this.f19435a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19437c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19437c.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19438d) {
                try {
                    if (!this.f19436b.test(t)) {
                        this.f19438d = true;
                        this.f19437c.dispose();
                        this.f19435a.onComplete();
                        return;
                    }
                    this.f19435a.onNext(t);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f19437c.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19438d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19438d = true;
            this.f19435a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19438d) {
                this.f19438d = true;
                this.f19435a.onComplete();
            }
        }
    }
}
