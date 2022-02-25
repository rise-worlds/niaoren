package p110z1;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bis */
/* loaded from: classes3.dex */
public final class ObservableBufferBoundary<T, U extends Collection<? super T>, Open, Close> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final Callable<U> f18869b;

    /* renamed from: c */
    final ObservableSource<? extends Open> f18870c;

    /* renamed from: d */
    final Function<? super Open, ? extends ObservableSource<? extends Close>> f18871d;

    public ObservableBufferBoundary(ObservableSource<T> asqVar, ObservableSource<? extends Open> asqVar2, Function<? super Open, ? extends ObservableSource<? extends Close>> aunVar, Callable<U> callable) {
        super(asqVar);
        this.f18870c = asqVar2;
        this.f18871d = aunVar;
        this.f18869b = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super U> assVar) {
        C4391a aVar = new C4391a(assVar, this.f18870c, this.f18871d, this.f18869b);
        assVar.onSubscribe(aVar);
        this.f18807a.subscribe(aVar);
    }

    /* compiled from: ObservableBufferBoundary.java */
    /* renamed from: z1.bis$a */
    /* loaded from: classes3.dex */
    static final class C4391a<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -8466418554264089604L;
        final Function<? super Open, ? extends ObservableSource<? extends Close>> bufferClose;
        final ObservableSource<? extends Open> bufferOpen;
        final Callable<C> bufferSupplier;
        volatile boolean cancelled;
        volatile boolean done;
        final Observer<? super C> downstream;
        long index;
        final SpscLinkedArrayQueue<C> queue = new SpscLinkedArrayQueue<>(Observable.m10338d());
        final CompositeDisposable observers = new CompositeDisposable();
        final AtomicReference<Disposable> upstream = new AtomicReference<>();
        Map<Long, C> buffers = new LinkedHashMap();
        final AtomicThrowable errors = new AtomicThrowable();

        C4391a(Observer<? super C> assVar, ObservableSource<? extends Open> asqVar, Function<? super Open, ? extends ObservableSource<? extends Close>> aunVar, Callable<C> callable) {
            this.downstream = assVar;
            this.bufferSupplier = callable;
            this.bufferOpen = asqVar;
            this.bufferClose = aunVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this.upstream, atrVar)) {
                C4392a aVar = new C4392a(this);
                this.observers.mo9939a(aVar);
                this.bufferOpen.subscribe(aVar);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            synchronized (this) {
                Map<Long, C> map = this.buffers;
                if (map != null) {
                    for (C c : map.values()) {
                        c.add(t);
                    }
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.observers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.observers.dispose();
            synchronized (this) {
                Map<Long, C> map = this.buffers;
                if (map != null) {
                    for (C c : map.values()) {
                        this.queue.offer(c);
                    }
                    this.buffers = null;
                    this.done = true;
                    drain();
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (DisposableHelper.dispose(this.upstream)) {
                this.cancelled = true;
                this.observers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                if (getAndIncrement() != 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        /* JADX WARN: Multi-variable type inference failed */
        void open(Open open) {
            try {
                Collection collection = (Collection) ObjectHelper.m9873a(this.bufferSupplier.call(), "The bufferSupplier returned a null Collection");
                ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.bufferClose.apply(open), "The bufferClose returned a null ObservableSource");
                long j = this.index;
                this.index = 1 + j;
                synchronized (this) {
                    Map<Long, C> map = this.buffers;
                    if (map != 0) {
                        map.put(Long.valueOf(j), collection);
                        C4393b bVar = new C4393b(this, j);
                        this.observers.mo9939a(bVar);
                        asqVar.subscribe(bVar);
                    }
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                DisposableHelper.dispose(this.upstream);
                onError(th);
            }
        }

        void openComplete(C4392a<Open> aVar) {
            this.observers.mo9936c(aVar);
            if (this.observers.m9996b() == 0) {
                DisposableHelper.dispose(this.upstream);
                this.done = true;
                drain();
            }
        }

        void close(C4393b<T, C> bVar, long j) {
            boolean z;
            this.observers.mo9936c(bVar);
            if (this.observers.m9996b() == 0) {
                DisposableHelper.dispose(this.upstream);
                z = true;
            } else {
                z = false;
            }
            synchronized (this) {
                if (this.buffers != null) {
                    this.queue.offer(this.buffers.remove(Long.valueOf(j)));
                    if (z) {
                        this.done = true;
                    }
                    drain();
                }
            }
        }

        void boundaryError(Disposable atrVar, Throwable th) {
            DisposableHelper.dispose(this.upstream);
            this.observers.mo9936c(atrVar);
            onError(th);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super C> assVar = this.downstream;
                SpscLinkedArrayQueue<C> bqlVar = this.queue;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = this.done;
                    if (!z || this.errors.get() == null) {
                        C poll = bqlVar.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            assVar.onComplete();
                            return;
                        } else if (z2) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            assVar.onNext(poll);
                        }
                    } else {
                        bqlVar.clear();
                        assVar.onError(this.errors.terminate());
                        return;
                    }
                }
                bqlVar.clear();
            }
        }

        /* compiled from: ObservableBufferBoundary.java */
        /* renamed from: z1.bis$a$a */
        /* loaded from: classes3.dex */
        static final class C4392a<Open> extends AtomicReference<Disposable> implements Observer<Open>, Disposable {
            private static final long serialVersionUID = -8498650778633225126L;
            final C4391a<?, ?, Open, ?> parent;

            C4392a(C4391a<?, ?, Open, ?> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.Observer
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.Observer
            public void onNext(Open open) {
                this.parent.open(open);
            }

            @Override // p110z1.Observer
            public void onError(Throwable th) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.boundaryError(this, th);
            }

            @Override // p110z1.Observer
            public void onComplete() {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.openComplete(this);
            }

            @Override // p110z1.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // p110z1.Disposable
            public boolean isDisposed() {
                return get() == DisposableHelper.DISPOSED;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableBufferBoundary.java */
    /* renamed from: z1.bis$b */
    /* loaded from: classes3.dex */
    public static final class C4393b<T, C extends Collection<? super T>> extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
        private static final long serialVersionUID = -8498650778633225126L;
        final long index;
        final C4391a<T, C, ?, ?> parent;

        C4393b(C4391a<T, C, ?, ?> aVar, long j) {
            this.parent = aVar;
            this.index = j;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(Object obj) {
            Disposable atrVar = get();
            if (atrVar != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                atrVar.dispose();
                this.parent.close(this, this.index);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.boundaryError(this, th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.close(this, this.index);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }
    }
}
