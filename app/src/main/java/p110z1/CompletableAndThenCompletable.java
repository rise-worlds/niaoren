package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awz */
/* loaded from: classes3.dex */
public final class CompletableAndThenCompletable extends Completable {

    /* renamed from: a */
    final CompletableSource f17678a;

    /* renamed from: b */
    final CompletableSource f17679b;

    public CompletableAndThenCompletable(CompletableSource arsVar, CompletableSource arsVar2) {
        this.f17678a = arsVar;
        this.f17679b = arsVar2;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17678a.mo11309a(new C3941b(arpVar, this.f17679b));
    }

    /* compiled from: CompletableAndThenCompletable.java */
    /* renamed from: z1.awz$b */
    /* loaded from: classes3.dex */
    static final class C3941b extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = -4101678820158072998L;
        final CompletableObserver actualObserver;
        final CompletableSource next;

        C3941b(CompletableObserver arpVar, CompletableSource arsVar) {
            this.actualObserver = arpVar;
            this.next = arsVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.actualObserver.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.actualObserver.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.next.mo11309a(new C3940a(this, this.actualObserver));
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

    /* compiled from: CompletableAndThenCompletable.java */
    /* renamed from: z1.awz$a */
    /* loaded from: classes3.dex */
    static final class C3940a implements CompletableObserver {

        /* renamed from: a */
        final AtomicReference<Disposable> f17680a;

        /* renamed from: b */
        final CompletableObserver f17681b;

        public C3940a(AtomicReference<Disposable> atomicReference, CompletableObserver arpVar) {
            this.f17680a = atomicReference;
            this.f17681b = arpVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this.f17680a, atrVar);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.f17681b.onComplete();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f17681b.onError(th);
        }
    }
}
