package p110z1;

/* renamed from: z1.blj */
/* loaded from: classes3.dex */
public final class ObservableOnErrorNext<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super Throwable, ? extends ObservableSource<? extends T>> f19243b;

    /* renamed from: c */
    final boolean f19244c;

    public ObservableOnErrorNext(ObservableSource<T> asqVar, Function<? super Throwable, ? extends ObservableSource<? extends T>> aunVar, boolean z) {
        super(asqVar);
        this.f19243b = aunVar;
        this.f19244c = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        C4501a aVar = new C4501a(assVar, this.f19243b, this.f19244c);
        assVar.onSubscribe(aVar.f19248d);
        this.f18807a.subscribe(aVar);
    }

    /* compiled from: ObservableOnErrorNext.java */
    /* renamed from: z1.blj$a */
    /* loaded from: classes3.dex */
    static final class C4501a<T> implements Observer<T> {

        /* renamed from: a */
        final Observer<? super T> f19245a;

        /* renamed from: b */
        final Function<? super Throwable, ? extends ObservableSource<? extends T>> f19246b;

        /* renamed from: c */
        final boolean f19247c;

        /* renamed from: d */
        final SequentialDisposable f19248d = new SequentialDisposable();

        /* renamed from: e */
        boolean f19249e;

        /* renamed from: f */
        boolean f19250f;

        C4501a(Observer<? super T> assVar, Function<? super Throwable, ? extends ObservableSource<? extends T>> aunVar, boolean z) {
            this.f19245a = assVar;
            this.f19246b = aunVar;
            this.f19247c = z;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19248d.replace(atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19250f) {
                this.f19245a.onNext(t);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (!this.f19249e) {
                this.f19249e = true;
                if (!this.f19247c || (th instanceof Exception)) {
                    try {
                        ObservableSource asqVar = (ObservableSource) this.f19246b.apply(th);
                        if (asqVar == null) {
                            NullPointerException nullPointerException = new NullPointerException("Observable is null");
                            nullPointerException.initCause(th);
                            this.f19245a.onError(nullPointerException);
                            return;
                        }
                        asqVar.subscribe(this);
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        this.f19245a.onError(new CompositeException(th, th2));
                    }
                } else {
                    this.f19245a.onError(th);
                }
            } else if (this.f19250f) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.f19245a.onError(th);
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19250f) {
                this.f19250f = true;
                this.f19249e = true;
                this.f19245a.onComplete();
            }
        }
    }
}
