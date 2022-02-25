package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.axe */
/* loaded from: classes3.dex */
public final class CompletableCreate extends Completable {

    /* renamed from: a */
    final CompletableOnSubscribe f17697a;

    public CompletableCreate(CompletableOnSubscribe arqVar) {
        this.f17697a = arqVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        C3948a aVar = new C3948a(arpVar);
        arpVar.onSubscribe(aVar);
        try {
            this.f17697a.m11311a(aVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            aVar.onError(th);
        }
    }

    /* compiled from: CompletableCreate.java */
    /* renamed from: z1.axe$a */
    /* loaded from: classes3.dex */
    static final class C3948a extends AtomicReference<Disposable> implements CompletableEmitter, Disposable {
        private static final long serialVersionUID = -2467358622224974244L;
        final CompletableObserver downstream;

        C3948a(CompletableObserver arpVar) {
            this.downstream = arpVar;
        }

        @Override // p110z1.CompletableEmitter
        public void onComplete() {
            Disposable andSet;
            if (get() != DisposableHelper.DISPOSED && (andSet = getAndSet(DisposableHelper.DISPOSED)) != DisposableHelper.DISPOSED) {
                try {
                    this.downstream.onComplete();
                } finally {
                    if (andSet != null) {
                        andSet.dispose();
                    }
                }
            }
        }

        @Override // p110z1.CompletableEmitter
        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.CompletableEmitter
        public boolean tryOnError(Throwable th) {
            Disposable andSet;
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (get() == DisposableHelper.DISPOSED || (andSet = getAndSet(DisposableHelper.DISPOSED)) == DisposableHelper.DISPOSED) {
                return false;
            }
            try {
                this.downstream.onError(th);
            } finally {
                if (andSet != null) {
                    andSet.dispose();
                }
            }
        }

        @Override // p110z1.CompletableEmitter
        public void setDisposable(Disposable atrVar) {
            DisposableHelper.set(this, atrVar);
        }

        @Override // p110z1.CompletableEmitter
        public void setCancellable(Cancellable aulVar) {
            setDisposable(new CancellableDisposable(aulVar));
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.CompletableEmitter, p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
        }
    }
}
