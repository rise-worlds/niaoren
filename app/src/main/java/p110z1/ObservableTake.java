package p110z1;

/* renamed from: z1.bms */
/* loaded from: classes3.dex */
public final class ObservableTake<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19413b;

    public ObservableTake(ObservableSource<T> asqVar, long j) {
        super(asqVar);
        this.f19413b = j;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4567a(assVar, this.f19413b));
    }

    /* compiled from: ObservableTake.java */
    /* renamed from: z1.bms$a */
    /* loaded from: classes3.dex */
    static final class C4567a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19414a;

        /* renamed from: b */
        boolean f19415b;

        /* renamed from: c */
        Disposable f19416c;

        /* renamed from: d */
        long f19417d;

        C4567a(Observer<? super T> assVar, long j) {
            this.f19414a = assVar;
            this.f19417d = j;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19416c, atrVar)) {
                this.f19416c = atrVar;
                if (this.f19417d == 0) {
                    this.f19415b = true;
                    atrVar.dispose();
                    EmptyDisposable.complete(this.f19414a);
                    return;
                }
                this.f19414a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19415b) {
                long j = this.f19417d;
                this.f19417d = j - 1;
                if (j > 0) {
                    boolean z = this.f19417d == 0;
                    this.f19414a.onNext(t);
                    if (z) {
                        onComplete();
                    }
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19415b) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19415b = true;
            this.f19416c.dispose();
            this.f19414a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19415b) {
                this.f19415b = true;
                this.f19416c.dispose();
                this.f19414a.onComplete();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19416c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19416c.isDisposed();
        }
    }
}
