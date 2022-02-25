package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bgy */
/* loaded from: classes3.dex */
public final class MaybeTakeUntilMaybe<T, U> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final MaybeSource<U> f18705b;

    public MaybeTakeUntilMaybe(MaybeSource<T> asiVar, MaybeSource<U> asiVar2) {
        super(asiVar);
        this.f18705b = asiVar2;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        C4326a aVar = new C4326a(asfVar);
        asfVar.onSubscribe(aVar);
        this.f18705b.mo10646a(aVar.other);
        this.f18529a.mo10646a(aVar);
    }

    /* compiled from: MaybeTakeUntilMaybe.java */
    /* renamed from: z1.bgy$a */
    /* loaded from: classes3.dex */
    static final class C4326a<T, U> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -2187421758664251153L;
        final MaybeObserver<? super T> downstream;
        final C4327a<U> other = new C4327a<>(this);

        C4326a(MaybeObserver<? super T> asfVar) {
            this.downstream = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.other);
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
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onSuccess(t);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            DisposableHelper.dispose(this.other);
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

        /* compiled from: MaybeTakeUntilMaybe.java */
        /* renamed from: z1.bgy$a$a */
        /* loaded from: classes3.dex */
        static final class C4327a<U> extends AtomicReference<Disposable> implements MaybeObserver<U> {
            private static final long serialVersionUID = -1266041316834525931L;
            final C4326a<?, U> parent;

            C4327a(C4326a<?, U> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(Object obj) {
                this.parent.otherComplete();
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                this.parent.otherComplete();
            }
        }
    }
}
