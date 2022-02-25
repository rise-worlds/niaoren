package p110z1;

/* renamed from: z1.blk */
/* loaded from: classes3.dex */
public final class ObservableOnErrorReturn<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super Throwable, ? extends T> f19251b;

    public ObservableOnErrorReturn(ObservableSource<T> asqVar, Function<? super Throwable, ? extends T> aunVar) {
        super(asqVar);
        this.f19251b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4502a(assVar, this.f19251b));
    }

    /* compiled from: ObservableOnErrorReturn.java */
    /* renamed from: z1.blk$a */
    /* loaded from: classes3.dex */
    static final class C4502a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19252a;

        /* renamed from: b */
        final Function<? super Throwable, ? extends T> f19253b;

        /* renamed from: c */
        Disposable f19254c;

        C4502a(Observer<? super T> assVar, Function<? super Throwable, ? extends T> aunVar) {
            this.f19252a = assVar;
            this.f19253b = aunVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19254c, atrVar)) {
                this.f19254c = atrVar;
                this.f19252a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19254c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19254c.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19252a.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            try {
                Object apply = this.f19253b.apply(th);
                if (apply == null) {
                    NullPointerException nullPointerException = new NullPointerException("The supplied value is null");
                    nullPointerException.initCause(th);
                    this.f19252a.onError(nullPointerException);
                    return;
                }
                this.f19252a.onNext(apply);
                this.f19252a.onComplete();
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.f19252a.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19252a.onComplete();
        }
    }
}
