package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.blm */
/* loaded from: classes3.dex */
public final class ObservablePublishSelector<T, R> extends AbstractObservableWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super Observable<T>, ? extends ObservableSource<R>> f19265b;

    public ObservablePublishSelector(ObservableSource<T> asqVar, Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar) {
        super(asqVar);
        this.f19265b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        PublishSubject a = PublishSubject.m8982a();
        try {
            ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.f19265b.apply(a), "The selector returned a null ObservableSource");
            C4507b bVar = new C4507b(assVar);
            asqVar.subscribe(bVar);
            this.f18807a.subscribe(new C4506a(a, bVar));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
        }
    }

    /* compiled from: ObservablePublishSelector.java */
    /* renamed from: z1.blm$a */
    /* loaded from: classes3.dex */
    static final class C4506a<T, R> implements Observer<T> {

        /* renamed from: a */
        final PublishSubject<T> f19266a;

        /* renamed from: b */
        final AtomicReference<Disposable> f19267b;

        C4506a(PublishSubject<T> buvVar, AtomicReference<Disposable> atomicReference) {
            this.f19266a = buvVar;
            this.f19267b = atomicReference;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.f19267b, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19266a.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19266a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19266a.onComplete();
        }
    }

    /* compiled from: ObservablePublishSelector.java */
    /* renamed from: z1.blm$b */
    /* loaded from: classes3.dex */
    static final class C4507b<T, R> extends AtomicReference<Disposable> implements Observer<R>, Disposable {
        private static final long serialVersionUID = 854110278590336484L;
        final Observer<? super R> downstream;
        Disposable upstream;

        C4507b(Observer<? super R> assVar) {
            this.downstream = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this);
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            DisposableHelper.dispose(this);
            this.downstream.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.dispose();
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }
    }
}
