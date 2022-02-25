package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bgw */
/* loaded from: classes3.dex */
public final class MaybeSwitchIfEmpty<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final MaybeSource<? extends T> f18698b;

    public MaybeSwitchIfEmpty(MaybeSource<T> asiVar, MaybeSource<? extends T> asiVar2) {
        super(asiVar);
        this.f18698b = asiVar2;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4322a(asfVar, this.f18698b));
    }

    /* compiled from: MaybeSwitchIfEmpty.java */
    /* renamed from: z1.bgw$a */
    /* loaded from: classes3.dex */
    static final class C4322a<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -2223459372976438024L;
        final MaybeObserver<? super T> downstream;
        final MaybeSource<? extends T> other;

        C4322a(MaybeObserver<? super T> asfVar, MaybeSource<? extends T> asiVar) {
            this.downstream = asfVar;
            this.other = asiVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            Disposable atrVar = get();
            if (atrVar != DisposableHelper.DISPOSED && compareAndSet(atrVar, null)) {
                this.other.mo10646a(new C4323a(this.downstream, this));
            }
        }

        /* compiled from: MaybeSwitchIfEmpty.java */
        /* renamed from: z1.bgw$a$a */
        /* loaded from: classes3.dex */
        static final class C4323a<T> implements MaybeObserver<T> {

            /* renamed from: a */
            final MaybeObserver<? super T> f18699a;

            /* renamed from: b */
            final AtomicReference<Disposable> f18700b;

            C4323a(MaybeObserver<? super T> asfVar, AtomicReference<Disposable> atomicReference) {
                this.f18699a = asfVar;
                this.f18700b = atomicReference;
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this.f18700b, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(T t) {
                this.f18699a.onSuccess(t);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.f18699a.onError(th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                this.f18699a.onComplete();
            }
        }
    }
}
