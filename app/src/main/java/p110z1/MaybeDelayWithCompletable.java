package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bff */
/* loaded from: classes3.dex */
public final class MaybeDelayWithCompletable<T> extends Maybe<T> {

    /* renamed from: a */
    final MaybeSource<T> f18575a;

    /* renamed from: b */
    final CompletableSource f18576b;

    public MaybeDelayWithCompletable(MaybeSource<T> asiVar, CompletableSource arsVar) {
        this.f18575a = asiVar;
        this.f18576b = arsVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18576b.mo11309a(new C4278b(asfVar, this.f18575a));
    }

    /* compiled from: MaybeDelayWithCompletable.java */
    /* renamed from: z1.bff$b */
    /* loaded from: classes3.dex */
    static final class C4278b<T> extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = 703409937383992161L;
        final MaybeObserver<? super T> downstream;
        final MaybeSource<T> source;

        C4278b(MaybeObserver<? super T> asfVar, MaybeSource<T> asiVar) {
            this.downstream = asfVar;
            this.source = asiVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.source.mo10646a(new C4277a(this, this.downstream));
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }

    /* compiled from: MaybeDelayWithCompletable.java */
    /* renamed from: z1.bff$a */
    /* loaded from: classes3.dex */
    static final class C4277a<T> implements MaybeObserver<T> {

        /* renamed from: a */
        final AtomicReference<Disposable> f18577a;

        /* renamed from: b */
        final MaybeObserver<? super T> f18578b;

        C4277a(AtomicReference<Disposable> atomicReference, MaybeObserver<? super T> asfVar) {
            this.f18577a = atomicReference;
            this.f18578b = asfVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this.f18577a, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18578b.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18578b.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18578b.onComplete();
        }
    }
}
