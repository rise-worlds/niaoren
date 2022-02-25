package p110z1;

/* renamed from: z1.blq */
/* loaded from: classes3.dex */
public final class ObservableReduceSeedSingle<T, R> extends Single<R> {

    /* renamed from: a */
    final ObservableSource<T> f19279a;

    /* renamed from: b */
    final R f19280b;

    /* renamed from: c */
    final BiFunction<R, ? super T, R> f19281c;

    public ObservableReduceSeedSingle(ObservableSource<T> asqVar, R r, BiFunction<R, ? super T, R> auiVar) {
        this.f19279a = asqVar;
        this.f19280b = r;
        this.f19281c = auiVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        this.f19279a.subscribe(new C4511a(asxVar, this.f19281c, this.f19280b));
    }

    /* compiled from: ObservableReduceSeedSingle.java */
    /* renamed from: z1.blq$a */
    /* loaded from: classes3.dex */
    static final class C4511a<T, R> implements Observer<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super R> f19282a;

        /* renamed from: b */
        final BiFunction<R, ? super T, R> f19283b;

        /* renamed from: c */
        R f19284c;

        /* renamed from: d */
        Disposable f19285d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4511a(SingleObserver<? super R> asxVar, BiFunction<R, ? super T, R> auiVar, R r) {
            this.f19282a = asxVar;
            this.f19284c = r;
            this.f19283b = auiVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19285d, atrVar)) {
                this.f19285d = atrVar;
                this.f19282a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            R r = this.f19284c;
            if (r != null) {
                try {
                    this.f19284c = (R) ObjectHelper.m9873a(this.f19283b.apply(r, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f19285d.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19284c != null) {
                this.f19284c = null;
                this.f19282a.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            R r = this.f19284c;
            if (r != null) {
                this.f19284c = null;
                this.f19282a.onSuccess(r);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19285d.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19285d.isDisposed();
        }
    }
}
