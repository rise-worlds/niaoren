package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bjh */
/* loaded from: classes3.dex */
public final class ObservableCreate<T> extends Observable<T> {

    /* renamed from: a */
    final ObservableOnSubscribe<T> f18977a;

    public ObservableCreate(ObservableOnSubscribe<T> asoVar) {
        this.f18977a = asoVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        C4420a aVar = new C4420a(assVar);
        assVar.onSubscribe(aVar);
        try {
            this.f18977a.mo10164a(aVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            aVar.onError(th);
        }
    }

    /* compiled from: ObservableCreate.java */
    /* renamed from: z1.bjh$a */
    /* loaded from: classes3.dex */
    static final class C4420a<T> extends AtomicReference<Disposable> implements ObservableEmitter<T>, Disposable {
        private static final long serialVersionUID = -3434801548987643227L;
        final Observer<? super T> observer;

        C4420a(Observer<? super T> assVar) {
            this.observer = assVar;
        }

        @Override // p110z1.Emitter
        public void onNext(T t) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else if (!isDisposed()) {
                this.observer.onNext(t);
            }
        }

        @Override // p110z1.Emitter
        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                RxJavaPlugins.m9212a(th);
            }
        }

        /* JADX WARN: Finally extract failed */
        @Override // p110z1.ObservableEmitter
        public boolean tryOnError(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (isDisposed()) {
                return false;
            }
            try {
                this.observer.onError(th);
                dispose();
                return true;
            } catch (Throwable th2) {
                dispose();
                throw th2;
            }
        }

        @Override // p110z1.Emitter
        public void onComplete() {
            if (!isDisposed()) {
                try {
                    this.observer.onComplete();
                } finally {
                    dispose();
                }
            }
        }

        @Override // p110z1.ObservableEmitter
        public void setDisposable(Disposable atrVar) {
            DisposableHelper.set(this, atrVar);
        }

        @Override // p110z1.ObservableEmitter
        public void setCancellable(Cancellable aulVar) {
            setDisposable(new CancellableDisposable(aulVar));
        }

        @Override // p110z1.ObservableEmitter
        public ObservableEmitter<T> serialize() {
            return new C4421b(this);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.ObservableEmitter, p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
        }
    }

    /* compiled from: ObservableCreate.java */
    /* renamed from: z1.bjh$b */
    /* loaded from: classes3.dex */
    static final class C4421b<T> extends AtomicInteger implements ObservableEmitter<T> {
        private static final long serialVersionUID = 4883307006032401862L;
        volatile boolean done;
        final ObservableEmitter<T> emitter;
        final AtomicThrowable error = new AtomicThrowable();
        final SpscLinkedArrayQueue<T> queue = new SpscLinkedArrayQueue<>(16);

        @Override // p110z1.ObservableEmitter
        public ObservableEmitter<T> serialize() {
            return this;
        }

        C4421b(ObservableEmitter<T> asnVar) {
            this.emitter = asnVar;
        }

        @Override // p110z1.Emitter
        public void onNext(T t) {
            if (!this.emitter.isDisposed() && !this.done) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                if (get() != 0 || !compareAndSet(0, 1)) {
                    SpscLinkedArrayQueue<T> bqlVar = this.queue;
                    synchronized (bqlVar) {
                        bqlVar.offer(t);
                    }
                    if (getAndIncrement() != 0) {
                        return;
                    }
                } else {
                    this.emitter.onNext(t);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                drainLoop();
            }
        }

        @Override // p110z1.Emitter
        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.ObservableEmitter
        public boolean tryOnError(Throwable th) {
            if (this.emitter.isDisposed() || this.done) {
                return false;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (!this.error.addThrowable(th)) {
                return false;
            }
            this.done = true;
            drain();
            return true;
        }

        @Override // p110z1.Emitter
        public void onComplete() {
            if (!this.emitter.isDisposed() && !this.done) {
                this.done = true;
                drain();
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            ObservableEmitter<T> asnVar = this.emitter;
            SpscLinkedArrayQueue<T> bqlVar = this.queue;
            AtomicThrowable bsnVar = this.error;
            int i = 1;
            while (!asnVar.isDisposed()) {
                if (bsnVar.get() != null) {
                    bqlVar.clear();
                    asnVar.onError(bsnVar.terminate());
                    return;
                }
                boolean z = this.done;
                T poll = bqlVar.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    asnVar.onComplete();
                    return;
                } else if (z2) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    asnVar.onNext(poll);
                }
            }
            bqlVar.clear();
        }

        @Override // p110z1.ObservableEmitter
        public void setDisposable(Disposable atrVar) {
            this.emitter.setDisposable(atrVar);
        }

        @Override // p110z1.ObservableEmitter
        public void setCancellable(Cancellable aulVar) {
            this.emitter.setCancellable(aulVar);
        }

        @Override // p110z1.ObservableEmitter, p110z1.Disposable
        public boolean isDisposed() {
            return this.emitter.isDisposed();
        }

        @Override // java.util.concurrent.atomic.AtomicInteger
        public String toString() {
            return this.emitter.toString();
        }
    }
}
