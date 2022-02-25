package p110z1;

/* renamed from: z1.blp */
/* loaded from: classes3.dex */
public final class ObservableReduceMaybe<T> extends Maybe<T> {

    /* renamed from: a */
    final ObservableSource<T> f19272a;

    /* renamed from: b */
    final BiFunction<T, T, T> f19273b;

    public ObservableReduceMaybe(ObservableSource<T> asqVar, BiFunction<T, T, T> auiVar) {
        this.f19272a = asqVar;
        this.f19273b = auiVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f19272a.subscribe(new C4510a(asfVar, this.f19273b));
    }

    /* compiled from: ObservableReduceMaybe.java */
    /* renamed from: z1.blp$a */
    /* loaded from: classes3.dex */
    static final class C4510a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f19274a;

        /* renamed from: b */
        final BiFunction<T, T, T> f19275b;

        /* renamed from: c */
        boolean f19276c;

        /* renamed from: d */
        T f19277d;

        /* renamed from: e */
        Disposable f19278e;

        C4510a(MaybeObserver<? super T> asfVar, BiFunction<T, T, T> auiVar) {
            this.f19274a = asfVar;
            this.f19275b = auiVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19278e, atrVar)) {
                this.f19278e = atrVar;
                this.f19274a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19276c) {
                T t2 = this.f19277d;
                if (t2 == null) {
                    this.f19277d = t;
                    return;
                }
                try {
                    this.f19277d = (T) ObjectHelper.m9873a((Object) this.f19275b.apply(t2, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f19278e.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19276c) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19276c = true;
            this.f19277d = null;
            this.f19274a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19276c) {
                this.f19276c = true;
                T t = this.f19277d;
                this.f19277d = null;
                if (t != null) {
                    this.f19274a.onSuccess(t);
                } else {
                    this.f19274a.onComplete();
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19278e.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19278e.isDisposed();
        }
    }
}
