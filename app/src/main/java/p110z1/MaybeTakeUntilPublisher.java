package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bgz */
/* loaded from: classes3.dex */
public final class MaybeTakeUntilPublisher<T, U> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Publisher<U> f18706b;

    public MaybeTakeUntilPublisher(MaybeSource<T> asiVar, Publisher<U> dbwVar) {
        super(asiVar);
        this.f18706b = dbwVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        C4328a aVar = new C4328a(asfVar);
        asfVar.onSubscribe(aVar);
        this.f18706b.subscribe(aVar.other);
        this.f18529a.mo10646a(aVar);
    }

    /* compiled from: MaybeTakeUntilPublisher.java */
    /* renamed from: z1.bgz$a */
    /* loaded from: classes3.dex */
    static final class C4328a<T, U> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -2187421758664251153L;
        final MaybeObserver<? super T> downstream;
        final C4329a<U> other = new C4329a<>(this);

        C4328a(MaybeObserver<? super T> asfVar) {
            this.downstream = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            SubscriptionHelper.cancel(this.other);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            SubscriptionHelper.cancel(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onSuccess(t);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onComplete();
            }
        }

        void otherError(Throwable th) {
            if (DisposableHelper.dispose(this)) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        void otherComplete() {
            if (DisposableHelper.dispose(this)) {
                this.downstream.onComplete();
            }
        }

        /* compiled from: MaybeTakeUntilPublisher.java */
        /* renamed from: z1.bgz$a$a */
        /* loaded from: classes3.dex */
        static final class C4329a<U> extends AtomicReference<dby> implements FlowableSubscriber<U> {
            private static final long serialVersionUID = -1266041316834525931L;
            final C4328a<?, U> parent;

            C4329a(C4328a<?, U> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
            public void onSubscribe(dby dbyVar) {
                SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
            }

            @Override // p110z1.Subscriber
            public void onNext(Object obj) {
                SubscriptionHelper.cancel(this);
                this.parent.otherComplete();
            }

            @Override // p110z1.Subscriber
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            @Override // p110z1.Subscriber
            public void onComplete() {
                this.parent.otherComplete();
            }
        }
    }
}
