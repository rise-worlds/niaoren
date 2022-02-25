package p110z1;

/* renamed from: z1.bkg */
/* loaded from: classes3.dex */
public final class ObservableFlattenIterable<T, R> extends AbstractObservableWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super T, ? extends Iterable<? extends R>> f19121b;

    public ObservableFlattenIterable(ObservableSource<T> asqVar, Function<? super T, ? extends Iterable<? extends R>> aunVar) {
        super(asqVar);
        this.f19121b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        this.f18807a.subscribe(new C4454a(assVar, this.f19121b));
    }

    /* compiled from: ObservableFlattenIterable.java */
    /* renamed from: z1.bkg$a */
    /* loaded from: classes3.dex */
    static final class C4454a<T, R> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super R> f19122a;

        /* renamed from: b */
        final Function<? super T, ? extends Iterable<? extends R>> f19123b;

        /* renamed from: c */
        Disposable f19124c;

        C4454a(Observer<? super R> assVar, Function<? super T, ? extends Iterable<? extends R>> aunVar) {
            this.f19122a = assVar;
            this.f19123b = aunVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19124c, atrVar)) {
                this.f19124c = atrVar;
                this.f19122a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (this.f19124c != DisposableHelper.DISPOSED) {
                try {
                    Observer<? super R> assVar = this.f19122a;
                    for (T t2 : (Iterable) this.f19123b.apply(t)) {
                        try {
                            try {
                                assVar.onNext((Object) ObjectHelper.m9873a((Object) t2, "The iterator returned a null value"));
                            } catch (Throwable th) {
                                Exceptions.m9983b(th);
                                this.f19124c.dispose();
                                onError(th);
                                return;
                            }
                        } catch (Throwable th2) {
                            Exceptions.m9983b(th2);
                            this.f19124c.dispose();
                            onError(th2);
                            return;
                        }
                    }
                } catch (Throwable th3) {
                    Exceptions.m9983b(th3);
                    this.f19124c.dispose();
                    onError(th3);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19124c == DisposableHelper.DISPOSED) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19124c = DisposableHelper.DISPOSED;
            this.f19122a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (this.f19124c != DisposableHelper.DISPOSED) {
                this.f19124c = DisposableHelper.DISPOSED;
                this.f19122a.onComplete();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19124c.isDisposed();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19124c.dispose();
            this.f19124c = DisposableHelper.DISPOSED;
        }
    }
}
