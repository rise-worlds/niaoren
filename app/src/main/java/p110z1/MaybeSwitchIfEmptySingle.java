package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bgx */
/* loaded from: classes3.dex */
public final class MaybeSwitchIfEmptySingle<T> extends Single<T> implements HasUpstreamMaybeSource<T> {

    /* renamed from: a */
    final MaybeSource<T> f18701a;

    /* renamed from: b */
    final SingleSource<? extends T> f18702b;

    public MaybeSwitchIfEmptySingle(MaybeSource<T> asiVar, SingleSource<? extends T> ataVar) {
        this.f18701a = asiVar;
        this.f18702b = ataVar;
    }

    @Override // p110z1.HasUpstreamMaybeSource
    /* renamed from: s_ */
    public MaybeSource<T> mo9687s_() {
        return this.f18701a;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f18701a.mo10646a(new C4324a(asxVar, this.f18702b));
    }

    /* compiled from: MaybeSwitchIfEmptySingle.java */
    /* renamed from: z1.bgx$a */
    /* loaded from: classes3.dex */
    static final class C4324a<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4603919676453758899L;
        final SingleObserver<? super T> downstream;
        final SingleSource<? extends T> other;

        C4324a(SingleObserver<? super T> asxVar, SingleSource<? extends T> ataVar) {
            this.downstream = asxVar;
            this.other = ataVar;
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
                this.other.mo10018a(new C4325a(this.downstream, this));
            }
        }

        /* compiled from: MaybeSwitchIfEmptySingle.java */
        /* renamed from: z1.bgx$a$a */
        /* loaded from: classes3.dex */
        static final class C4325a<T> implements SingleObserver<T> {

            /* renamed from: a */
            final SingleObserver<? super T> f18703a;

            /* renamed from: b */
            final AtomicReference<Disposable> f18704b;

            C4325a(SingleObserver<? super T> asxVar, AtomicReference<Disposable> atomicReference) {
                this.f18703a = asxVar;
                this.f18704b = atomicReference;
            }

            @Override // p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this.f18704b, atrVar);
            }

            @Override // p110z1.SingleObserver
            public void onSuccess(T t) {
                this.f18703a.onSuccess(t);
            }

            @Override // p110z1.SingleObserver
            public void onError(Throwable th) {
                this.f18703a.onError(th);
            }
        }
    }
}
