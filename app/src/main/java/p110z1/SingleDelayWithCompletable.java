package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bop */
/* loaded from: classes3.dex */
public final class SingleDelayWithCompletable<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19716a;

    /* renamed from: b */
    final CompletableSource f19717b;

    public SingleDelayWithCompletable(SingleSource<T> ataVar, CompletableSource arsVar) {
        this.f19716a = ataVar;
        this.f19717b = arsVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19717b.mo11309a(new C4659a(asxVar, this.f19716a));
    }

    /* compiled from: SingleDelayWithCompletable.java */
    /* renamed from: z1.bop$a */
    /* loaded from: classes3.dex */
    static final class C4659a<T> extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = -8565274649390031272L;
        final SingleObserver<? super T> downstream;
        final SingleSource<T> source;

        C4659a(SingleObserver<? super T> asxVar, SingleSource<T> ataVar) {
            this.downstream = asxVar;
            this.source = ataVar;
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
            this.source.mo10018a(new ResumeSingleObserver(this, this.downstream));
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
}
