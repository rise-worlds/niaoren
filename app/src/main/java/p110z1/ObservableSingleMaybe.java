package p110z1;

/* renamed from: z1.bmi */
/* loaded from: classes3.dex */
public final class ObservableSingleMaybe<T> extends Maybe<T> {

    /* renamed from: a */
    final ObservableSource<T> f19364a;

    public ObservableSingleMaybe(ObservableSource<T> asqVar) {
        this.f19364a = asqVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    public void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f19364a.subscribe(new C4554a(asfVar));
    }

    /* compiled from: ObservableSingleMaybe.java */
    /* renamed from: z1.bmi$a */
    /* loaded from: classes3.dex */
    static final class C4554a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f19365a;

        /* renamed from: b */
        Disposable f19366b;

        /* renamed from: c */
        T f19367c;

        /* renamed from: d */
        boolean f19368d;

        C4554a(MaybeObserver<? super T> asfVar) {
            this.f19365a = asfVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19366b, atrVar)) {
                this.f19366b = atrVar;
                this.f19365a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19366b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19366b.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19368d) {
                if (this.f19367c != null) {
                    this.f19368d = true;
                    this.f19366b.dispose();
                    this.f19365a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.f19367c = t;
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19368d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19368d = true;
            this.f19365a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19368d) {
                this.f19368d = true;
                T t = this.f19367c;
                this.f19367c = null;
                if (t == null) {
                    this.f19365a.onComplete();
                } else {
                    this.f19365a.onSuccess(t);
                }
            }
        }
    }
}
