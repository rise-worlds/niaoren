package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfa */
/* loaded from: classes3.dex */
public final class MaybeCreate<T> extends Maybe<T> {

    /* renamed from: a */
    final MaybeOnSubscribe<T> f18562a;

    public MaybeCreate(MaybeOnSubscribe<T> asgVar) {
        this.f18562a = asgVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        C4271a aVar = new C4271a(asfVar);
        asfVar.onSubscribe(aVar);
        try {
            this.f18562a.m10648a(aVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            aVar.onError(th);
        }
    }

    /* compiled from: MaybeCreate.java */
    /* renamed from: z1.bfa$a */
    /* loaded from: classes3.dex */
    static final class C4271a<T> extends AtomicReference<Disposable> implements MaybeEmitter<T>, Disposable {
        private static final long serialVersionUID = -2467358622224974244L;
        final MaybeObserver<? super T> downstream;

        C4271a(MaybeObserver<? super T> asfVar) {
            this.downstream = asfVar;
        }

        @Override // p110z1.MaybeEmitter
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

        @Override // p110z1.MaybeEmitter
        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.MaybeEmitter
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

        @Override // p110z1.MaybeEmitter
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

        @Override // p110z1.MaybeEmitter
        public void setDisposable(Disposable atrVar) {
            DisposableHelper.set(this, atrVar);
        }

        @Override // p110z1.MaybeEmitter
        public void setCancellable(Cancellable aulVar) {
            setDisposable(new CancellableDisposable(aulVar));
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.MaybeEmitter, p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
        }
    }
}
