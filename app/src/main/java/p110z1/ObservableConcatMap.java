package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bja */
/* loaded from: classes3.dex */
public final class ObservableConcatMap<T, U> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final Function<? super T, ? extends ObservableSource<? extends U>> f18960b;

    /* renamed from: c */
    final int f18961c;

    /* renamed from: d */
    final ErrorMode f18962d;

    public ObservableConcatMap(ObservableSource<T> asqVar, Function<? super T, ? extends ObservableSource<? extends U>> aunVar, int i, ErrorMode bsuVar) {
        super(asqVar);
        this.f18960b = aunVar;
        this.f18962d = bsuVar;
        this.f18961c = Math.max(8, i);
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super U> assVar) {
        if (!ObservableScalarXMap.m9575a(this.f18807a, assVar, this.f18960b)) {
            if (this.f18962d == ErrorMode.IMMEDIATE) {
                this.f18807a.subscribe(new C4412b(new SerializedObserver(assVar), this.f18960b, this.f18961c));
            } else {
                this.f18807a.subscribe(new C4410a(assVar, this.f18960b, this.f18961c, this.f18962d == ErrorMode.END));
            }
        }
    }

    /* compiled from: ObservableConcatMap.java */
    /* renamed from: z1.bja$b */
    /* loaded from: classes3.dex */
    static final class C4412b<T, U> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8828587559905699186L;
        volatile boolean active;
        final int bufferSize;
        volatile boolean disposed;
        volatile boolean done;
        final Observer<? super U> downstream;
        int fusionMode;
        final C4413a<U> inner;
        final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
        SimpleQueue<T> queue;
        Disposable upstream;

        C4412b(Observer<? super U> assVar, Function<? super T, ? extends ObservableSource<? extends U>> aunVar, int i) {
            this.downstream = assVar;
            this.mapper = aunVar;
            this.bufferSize = i;
            this.inner = new C4413a<>(assVar, this);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                if (atrVar instanceof QueueDisposable) {
                    QueueDisposable avrVar = (QueueDisposable) atrVar;
                    int requestFusion = avrVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = avrVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = avrVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new SpscLinkedArrayQueue(this.bufferSize);
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode == 0) {
                    this.queue.offer(t);
                }
                drain();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            dispose();
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.disposed = true;
            this.inner.dispose();
            this.upstream.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                while (!this.disposed) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            T poll = this.queue.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                this.disposed = true;
                                this.downstream.onComplete();
                                return;
                            } else if (!z2) {
                                try {
                                    ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                    this.active = true;
                                    asqVar.subscribe(this.inner);
                                } catch (Throwable th) {
                                    Exceptions.m9983b(th);
                                    dispose();
                                    this.queue.clear();
                                    this.downstream.onError(th);
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            Exceptions.m9983b(th2);
                            dispose();
                            this.queue.clear();
                            this.downstream.onError(th2);
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                this.queue.clear();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableConcatMap.java */
        /* renamed from: z1.bja$b$a */
        /* loaded from: classes3.dex */
        public static final class C4413a<U> extends AtomicReference<Disposable> implements Observer<U> {
            private static final long serialVersionUID = -7449079488798789337L;
            final Observer<? super U> downstream;
            final C4412b<?, ?> parent;

            C4413a(Observer<? super U> assVar, C4412b<?, ?> bVar) {
                this.downstream = assVar;
                this.parent = bVar;
            }

            @Override // p110z1.Observer
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.replace(this, atrVar);
            }

            @Override // p110z1.Observer
            public void onNext(U u) {
                this.downstream.onNext(u);
            }

            @Override // p110z1.Observer
            public void onError(Throwable th) {
                this.parent.dispose();
                this.downstream.onError(th);
            }

            @Override // p110z1.Observer
            public void onComplete() {
                this.parent.innerComplete();
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }

    /* compiled from: ObservableConcatMap.java */
    /* renamed from: z1.bja$a */
    /* loaded from: classes3.dex */
    static final class C4410a<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -6951100001833242599L;
        volatile boolean active;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        final Observer<? super R> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        final C4411a<R> observer;
        SimpleQueue<T> queue;
        int sourceMode;
        final boolean tillTheEnd;
        Disposable upstream;

        C4410a(Observer<? super R> assVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i, boolean z) {
            this.downstream = assVar;
            this.mapper = aunVar;
            this.bufferSize = i;
            this.tillTheEnd = z;
            this.observer = new C4411a<>(assVar, this);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                if (atrVar instanceof QueueDisposable) {
                    QueueDisposable avrVar = (QueueDisposable) atrVar;
                    int requestFusion = avrVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = avrVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = avrVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new SpscLinkedArrayQueue(this.bufferSize);
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (this.sourceMode == 0) {
                this.queue.offer(t);
            }
            drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.observer.dispose();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super R> assVar = this.downstream;
                SimpleQueue<T> avwVar = this.queue;
                AtomicThrowable bsnVar = this.error;
                while (true) {
                    if (!this.active) {
                        if (this.cancelled) {
                            avwVar.clear();
                            return;
                        } else if (this.tillTheEnd || bsnVar.get() == null) {
                            boolean z = this.done;
                            try {
                                T poll = avwVar.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    this.cancelled = true;
                                    Throwable terminate = bsnVar.terminate();
                                    if (terminate != null) {
                                        assVar.onError(terminate);
                                        return;
                                    } else {
                                        assVar.onComplete();
                                        return;
                                    }
                                } else if (!z2) {
                                    try {
                                        ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                        if (asqVar instanceof Callable) {
                                            try {
                                                Object obj = (Object) ((Callable) asqVar).call();
                                                if (obj != 0 && !this.cancelled) {
                                                    assVar.onNext(obj);
                                                }
                                            } catch (Throwable th) {
                                                Exceptions.m9983b(th);
                                                bsnVar.addThrowable(th);
                                            }
                                        } else {
                                            this.active = true;
                                            asqVar.subscribe(this.observer);
                                        }
                                    } catch (Throwable th2) {
                                        Exceptions.m9983b(th2);
                                        this.cancelled = true;
                                        this.upstream.dispose();
                                        avwVar.clear();
                                        bsnVar.addThrowable(th2);
                                        assVar.onError(bsnVar.terminate());
                                        return;
                                    }
                                }
                            } catch (Throwable th3) {
                                Exceptions.m9983b(th3);
                                this.cancelled = true;
                                this.upstream.dispose();
                                bsnVar.addThrowable(th3);
                                assVar.onError(bsnVar.terminate());
                                return;
                            }
                        } else {
                            avwVar.clear();
                            this.cancelled = true;
                            assVar.onError(bsnVar.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableConcatMap.java */
        /* renamed from: z1.bja$a$a */
        /* loaded from: classes3.dex */
        public static final class C4411a<R> extends AtomicReference<Disposable> implements Observer<R> {
            private static final long serialVersionUID = 2620149119579502636L;
            final Observer<? super R> downstream;
            final C4410a<?, R> parent;

            C4411a(Observer<? super R> assVar, C4410a<?, R> aVar) {
                this.downstream = assVar;
                this.parent = aVar;
            }

            @Override // p110z1.Observer
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.replace(this, atrVar);
            }

            @Override // p110z1.Observer
            public void onNext(R r) {
                this.downstream.onNext(r);
            }

            @Override // p110z1.Observer
            public void onError(Throwable th) {
                C4410a<?, R> aVar = this.parent;
                if (aVar.error.addThrowable(th)) {
                    if (!aVar.tillTheEnd) {
                        aVar.upstream.dispose();
                    }
                    aVar.active = false;
                    aVar.drain();
                    return;
                }
                RxJavaPlugins.m9212a(th);
            }

            @Override // p110z1.Observer
            public void onComplete() {
                C4410a<?, R> aVar = this.parent;
                aVar.active = false;
                aVar.drain();
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
