package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.blz */
/* loaded from: classes3.dex */
public final class ObservableRetryWhen<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super Observable<Throwable>, ? extends ObservableSource<?>> f19319b;

    public ObservableRetryWhen(ObservableSource<T> asqVar, Function<? super Observable<Throwable>, ? extends ObservableSource<?>> aunVar) {
        super(asqVar);
        this.f19319b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        Subject<T> aa = PublishSubject.m8982a().m8952aa();
        try {
            ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.f19319b.apply(aa), "The handler returned a null ObservableSource");
            C4536a aVar = new C4536a(assVar, aa, this.f18807a);
            assVar.onSubscribe(aVar);
            asqVar.subscribe(aVar.inner);
            aVar.subscribeNext();
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
        }
    }

    /* compiled from: ObservableRetryWhen.java */
    /* renamed from: z1.blz$a */
    /* loaded from: classes3.dex */
    static final class C4536a<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 802743776666017014L;
        volatile boolean active;
        final Observer<? super T> downstream;
        final Subject<Throwable> signaller;
        final ObservableSource<T> source;
        final AtomicInteger wip = new AtomicInteger();
        final AtomicThrowable error = new AtomicThrowable();
        final C4536a<T>.C4537a inner = new C4537a();
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        C4536a(Observer<? super T> assVar, Subject<Throwable> buzVar, ObservableSource<T> asqVar) {
            this.downstream = assVar;
            this.signaller = buzVar;
            this.source = asqVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this.upstream, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            HalfSerializer.m9427a(this.downstream, t, this, this.error);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            DisposableHelper.replace(this.upstream, null);
            this.active = false;
            this.signaller.onNext(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            DisposableHelper.dispose(this.inner);
            HalfSerializer.m9425a(this.downstream, this, this.error);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.inner);
        }

        void innerNext() {
            subscribeNext();
        }

        void innerError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            HalfSerializer.m9426a((Observer<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        void innerComplete() {
            DisposableHelper.dispose(this.upstream);
            HalfSerializer.m9425a(this.downstream, this, this.error);
        }

        void subscribeNext() {
            if (this.wip.getAndIncrement() == 0) {
                while (!isDisposed()) {
                    if (!this.active) {
                        this.active = true;
                        this.source.subscribe(this);
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        /* compiled from: ObservableRetryWhen.java */
        /* renamed from: z1.blz$a$a */
        /* loaded from: classes3.dex */
        final class C4537a extends AtomicReference<Disposable> implements Observer<Object> {
            private static final long serialVersionUID = 3254781284376480842L;

            C4537a() {
            }

            @Override // p110z1.Observer
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.Observer
            public void onNext(Object obj) {
                C4536a.this.innerNext();
            }

            @Override // p110z1.Observer
            public void onError(Throwable th) {
                C4536a.this.innerError(th);
            }

            @Override // p110z1.Observer
            public void onComplete() {
                C4536a.this.innerComplete();
            }
        }
    }
}
