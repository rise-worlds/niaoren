package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bhg */
/* loaded from: classes3.dex */
public final class MaybeToSingle<T> extends Single<T> implements HasUpstreamMaybeSource<T> {

    /* renamed from: a */
    final MaybeSource<T> f18718a;

    /* renamed from: b */
    final T f18719b;

    public MaybeToSingle(MaybeSource<T> asiVar, T t) {
        this.f18718a = asiVar;
        this.f18719b = t;
    }

    @Override // p110z1.HasUpstreamMaybeSource
    /* renamed from: s_ */
    public MaybeSource<T> mo9687s_() {
        return this.f18718a;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f18718a.mo10646a(new C4340a(asxVar, this.f18719b));
    }

    /* compiled from: MaybeToSingle.java */
    /* renamed from: z1.bhg$a */
    /* loaded from: classes3.dex */
    static final class C4340a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f18720a;

        /* renamed from: b */
        final T f18721b;

        /* renamed from: c */
        Disposable f18722c;

        C4340a(SingleObserver<? super T> asxVar, T t) {
            this.f18720a = asxVar;
            this.f18721b = t;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18722c.dispose();
            this.f18722c = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18722c.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18722c, atrVar)) {
                this.f18722c = atrVar;
                this.f18720a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18722c = DisposableHelper.DISPOSED;
            this.f18720a.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18722c = DisposableHelper.DISPOSED;
            this.f18720a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18722c = DisposableHelper.DISPOSED;
            T t = this.f18721b;
            if (t != null) {
                this.f18720a.onSuccess(t);
            } else {
                this.f18720a.onError(new NoSuchElementException("The MaybeSource is empty"));
            }
        }
    }
}
