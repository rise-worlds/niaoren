package p110z1;

/* renamed from: z1.bjw */
/* loaded from: classes3.dex */
public final class ObservableElementAtMaybe<T> extends Maybe<T> implements FuseToObservable<T> {

    /* renamed from: a */
    final ObservableSource<T> f19068a;

    /* renamed from: b */
    final long f19069b;

    public ObservableElementAtMaybe(ObservableSource<T> asqVar, long j) {
        this.f19068a = asqVar;
        this.f19069b = j;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    public void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f19068a.subscribe(new C4440a(asfVar, this.f19069b));
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<T> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableElementAt(this.f19068a, this.f19069b, null, false));
    }

    /* compiled from: ObservableElementAtMaybe.java */
    /* renamed from: z1.bjw$a */
    /* loaded from: classes3.dex */
    static final class C4440a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f19070a;

        /* renamed from: b */
        final long f19071b;

        /* renamed from: c */
        Disposable f19072c;

        /* renamed from: d */
        long f19073d;

        /* renamed from: e */
        boolean f19074e;

        C4440a(MaybeObserver<? super T> asfVar, long j) {
            this.f19070a = asfVar;
            this.f19071b = j;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19072c, atrVar)) {
                this.f19072c = atrVar;
                this.f19070a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19072c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19072c.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19074e) {
                long j = this.f19073d;
                if (j == this.f19071b) {
                    this.f19074e = true;
                    this.f19072c.dispose();
                    this.f19070a.onSuccess(t);
                    return;
                }
                this.f19073d = j + 1;
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19074e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19074e = true;
            this.f19070a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19074e) {
                this.f19074e = true;
                this.f19070a.onComplete();
            }
        }
    }
}
