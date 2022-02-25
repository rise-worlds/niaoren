package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bix */
/* loaded from: classes3.dex */
public final class ObservableCollect<T, U> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final Callable<? extends U> f18938b;

    /* renamed from: c */
    final BiConsumer<? super U, ? super T> f18939c;

    public ObservableCollect(ObservableSource<T> asqVar, Callable<? extends U> callable, BiConsumer<? super U, ? super T> auhVar) {
        super(asqVar);
        this.f18938b = callable;
        this.f18939c = auhVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super U> assVar) {
        try {
            this.f18807a.subscribe(new C4405a(assVar, ObjectHelper.m9873a(this.f18938b.call(), "The initialSupplier returned a null value"), this.f18939c));
        } catch (Throwable th) {
            EmptyDisposable.error(th, assVar);
        }
    }

    /* compiled from: ObservableCollect.java */
    /* renamed from: z1.bix$a */
    /* loaded from: classes3.dex */
    static final class C4405a<T, U> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super U> f18940a;

        /* renamed from: b */
        final BiConsumer<? super U, ? super T> f18941b;

        /* renamed from: c */
        final U f18942c;

        /* renamed from: d */
        Disposable f18943d;

        /* renamed from: e */
        boolean f18944e;

        C4405a(Observer<? super U> assVar, U u, BiConsumer<? super U, ? super T> auhVar) {
            this.f18940a = assVar;
            this.f18941b = auhVar;
            this.f18942c = u;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18943d, atrVar)) {
                this.f18943d = atrVar;
                this.f18940a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18943d.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18943d.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f18944e) {
                try {
                    this.f18941b.mo9895a((U) this.f18942c, t);
                } catch (Throwable th) {
                    this.f18943d.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f18944e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18944e = true;
            this.f18940a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f18944e) {
                this.f18944e = true;
                this.f18940a.onNext((U) this.f18942c);
                this.f18940a.onComplete();
            }
        }
    }
}
