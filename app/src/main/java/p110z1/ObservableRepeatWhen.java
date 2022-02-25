package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.blv */
/* loaded from: classes3.dex */
public final class ObservableRepeatWhen<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super Observable<Object>, ? extends ObservableSource<?>> f19298b;

    public ObservableRepeatWhen(ObservableSource<T> asqVar, Function<? super Observable<Object>, ? extends ObservableSource<?>> aunVar) {
        super(asqVar);
        this.f19298b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        Subject<T> aa = PublishSubject.m8982a().m8952aa();
        try {
            ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.f19298b.apply(aa), "The handler returned a null ObservableSource");
            C4516a aVar = new C4516a(assVar, aa, this.f18807a);
            assVar.onSubscribe(aVar);
            asqVar.subscribe(aVar.inner);
            aVar.subscribeNext();
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
        }
    }

    /* compiled from: ObservableRepeatWhen.java */
    /* renamed from: z1.blv$a */
    /* loaded from: classes3.dex */
    static final class C4516a<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 802743776666017014L;
        volatile boolean active;
        final Observer<? super T> downstream;
        final Subject<Object> signaller;
        final ObservableSource<T> source;
        final AtomicInteger wip = new AtomicInteger();
        final AtomicThrowable error = new AtomicThrowable();
        final C4516a<T>.C4517a inner = new C4517a();
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        C4516a(Observer<? super T> assVar, Subject<Object> buzVar, ObservableSource<T> asqVar) {
            this.downstream = assVar;
            this.signaller = buzVar;
            this.source = asqVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.upstream, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            HalfSerializer.m9427a(this.downstream, t, this, this.error);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.inner);
            HalfSerializer.m9426a((Observer<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            DisposableHelper.replace(this.upstream, null);
            this.active = false;
            this.signaller.onNext(0);
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

        /* compiled from: ObservableRepeatWhen.java */
        /* renamed from: z1.blv$a$a */
        /* loaded from: classes3.dex */
        final class C4517a extends AtomicReference<Disposable> implements Observer<Object> {
            private static final long serialVersionUID = 3254781284376480842L;

            C4517a() {
            }

            @Override // p110z1.Observer
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.Observer
            public void onNext(Object obj) {
                C4516a.this.innerNext();
            }

            @Override // p110z1.Observer
            public void onError(Throwable th) {
                C4516a.this.innerError(th);
            }

            @Override // p110z1.Observer
            public void onComplete() {
                C4516a.this.innerComplete();
            }
        }
    }
}
