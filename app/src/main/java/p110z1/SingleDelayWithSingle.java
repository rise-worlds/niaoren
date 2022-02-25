package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bos */
/* loaded from: classes3.dex */
public final class SingleDelayWithSingle<T, U> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19722a;

    /* renamed from: b */
    final SingleSource<U> f19723b;

    public SingleDelayWithSingle(SingleSource<T> ataVar, SingleSource<U> ataVar2) {
        this.f19722a = ataVar;
        this.f19723b = ataVar2;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19723b.mo10018a(new C4662a(asxVar, this.f19722a));
    }

    /* compiled from: SingleDelayWithSingle.java */
    /* renamed from: z1.bos$a */
    /* loaded from: classes3.dex */
    static final class C4662a<T, U> extends AtomicReference<Disposable> implements SingleObserver<U>, Disposable {
        private static final long serialVersionUID = -8565274649390031272L;
        final SingleObserver<? super T> downstream;
        final SingleSource<T> source;

        C4662a(SingleObserver<? super T> asxVar, SingleSource<T> ataVar) {
            this.downstream = asxVar;
            this.source = ataVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(U u) {
            this.source.mo10018a(new ResumeSingleObserver(this, this.downstream));
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
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
