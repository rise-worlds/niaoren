package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bom */
/* loaded from: classes3.dex */
public final class SingleCreate<T> extends Single<T> {

    /* renamed from: a */
    final SingleOnSubscribe<T> f19702a;

    public SingleCreate(SingleOnSubscribe<T> asyVar) {
        this.f19702a = asyVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        C4655a aVar = new C4655a(asxVar);
        asxVar.onSubscribe(aVar);
        try {
            this.f19702a.m10020a(aVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            aVar.onError(th);
        }
    }

    /* compiled from: SingleCreate.java */
    /* renamed from: z1.bom$a */
    /* loaded from: classes3.dex */
    static final class C4655a<T> extends AtomicReference<Disposable> implements SingleEmitter<T>, Disposable {
        private static final long serialVersionUID = -2467358622224974244L;
        final SingleObserver<? super T> downstream;

        C4655a(SingleObserver<? super T> asxVar) {
            this.downstream = asxVar;
        }

        @Override // p110z1.SingleEmitter
        public void onSuccess(T t) {
            Disposable andSet;
            if (get() != DisposableHelper.DISPOSED && (andSet = getAndSet(DisposableHelper.DISPOSED)) != DisposableHelper.DISPOSED) {
                try {
                    if (t == null) {
                        this.downstream.onError(new NullPointerException("onSuccess called with null. Null values are generally not allowed in 2.x operators and sources."));
                    } else {
                        this.downstream.onSuccess(t);
                    }
                    if (andSet != null) {
                        andSet.dispose();
                    }
                } catch (Throwable th) {
                    if (andSet != null) {
                        andSet.dispose();
                    }
                    throw th;
                }
            }
        }

        @Override // p110z1.SingleEmitter
        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.SingleEmitter
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

        @Override // p110z1.SingleEmitter
        public void setDisposable(Disposable atrVar) {
            DisposableHelper.set(this, atrVar);
        }

        @Override // p110z1.SingleEmitter
        public void setCancellable(Cancellable aulVar) {
            setDisposable(new CancellableDisposable(aulVar));
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.SingleEmitter, p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
        }
    }
}
