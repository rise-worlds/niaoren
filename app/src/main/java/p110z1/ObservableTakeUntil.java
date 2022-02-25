package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bmw */
/* loaded from: classes3.dex */
public final class ObservableTakeUntil<T, U> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final ObservableSource<? extends U> f19428b;

    public ObservableTakeUntil(ObservableSource<T> asqVar, ObservableSource<? extends U> asqVar2) {
        super(asqVar);
        this.f19428b = asqVar2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        C4571a aVar = new C4571a(assVar);
        assVar.onSubscribe(aVar);
        this.f19428b.subscribe(aVar.otherObserver);
        this.f18807a.subscribe(aVar);
    }

    /* compiled from: ObservableTakeUntil.java */
    /* renamed from: z1.bmw$a */
    /* loaded from: classes3.dex */
    static final class C4571a<T, U> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 1418547743690811973L;
        final Observer<? super T> downstream;
        final AtomicReference<Disposable> upstream = new AtomicReference<>();
        final C4571a<T, U>.C4572a otherObserver = new C4572a();
        final AtomicThrowable error = new AtomicThrowable();

        C4571a(Observer<? super T> assVar) {
            this.downstream = assVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.otherObserver);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
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
            DisposableHelper.dispose(this.otherObserver);
            HalfSerializer.m9426a((Observer<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            DisposableHelper.dispose(this.otherObserver);
            HalfSerializer.m9425a(this.downstream, this, this.error);
        }

        void otherError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            HalfSerializer.m9426a((Observer<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        void otherComplete() {
            DisposableHelper.dispose(this.upstream);
            HalfSerializer.m9425a(this.downstream, this, this.error);
        }

        /* compiled from: ObservableTakeUntil.java */
        /* renamed from: z1.bmw$a$a */
        /* loaded from: classes3.dex */
        final class C4572a extends AtomicReference<Disposable> implements Observer<U> {
            private static final long serialVersionUID = -8693423678067375039L;

            C4572a() {
            }

            @Override // p110z1.Observer
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.Observer
            public void onNext(U u) {
                DisposableHelper.dispose(this);
                C4571a.this.otherComplete();
            }

            @Override // p110z1.Observer
            public void onError(Throwable th) {
                C4571a.this.otherError(th);
            }

            @Override // p110z1.Observer
            public void onComplete() {
                C4571a.this.otherComplete();
            }
        }
    }
}
