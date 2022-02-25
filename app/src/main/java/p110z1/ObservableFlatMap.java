package p110z1;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bkb */
/* loaded from: classes3.dex */
public final class ObservableFlatMap<T, U> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final Function<? super T, ? extends ObservableSource<? extends U>> f19107b;

    /* renamed from: c */
    final boolean f19108c;

    /* renamed from: d */
    final int f19109d;

    /* renamed from: e */
    final int f19110e;

    public ObservableFlatMap(ObservableSource<T> asqVar, Function<? super T, ? extends ObservableSource<? extends U>> aunVar, boolean z, int i, int i2) {
        super(asqVar);
        this.f19107b = aunVar;
        this.f19108c = z;
        this.f19109d = i;
        this.f19110e = i2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super U> assVar) {
        if (!ObservableScalarXMap.m9575a(this.f18807a, assVar, this.f19107b)) {
            this.f18807a.subscribe(new C4445b(assVar, this.f19107b, this.f19108c, this.f19109d, this.f19110e));
        }
    }

    /* compiled from: ObservableFlatMap.java */
    /* renamed from: z1.bkb$b */
    /* loaded from: classes3.dex */
    static final class C4445b<T, U> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -2117620485640801370L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Observer<? super U> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
        final int maxConcurrency;
        final AtomicReference<C4444a<?, ?>[]> observers;
        volatile SimplePlainQueue<U> queue;
        Queue<ObservableSource<? extends U>> sources;
        long uniqueId;
        Disposable upstream;
        int wip;
        static final C4444a<?, ?>[] EMPTY = new C4444a[0];
        static final C4444a<?, ?>[] CANCELLED = new C4444a[0];

        C4445b(Observer<? super U> assVar, Function<? super T, ? extends ObservableSource<? extends U>> aunVar, boolean z, int i, int i2) {
            this.downstream = assVar;
            this.mapper = aunVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
            this.bufferSize = i2;
            if (i != Integer.MAX_VALUE) {
                this.sources = new ArrayDeque(i);
            }
            this.observers = new AtomicReference<>(EMPTY);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.done) {
                try {
                    ObservableSource<? extends U> asqVar = (ObservableSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null ObservableSource");
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        synchronized (this) {
                            if (this.wip == this.maxConcurrency) {
                                this.sources.offer(asqVar);
                                return;
                            }
                            this.wip++;
                        }
                    }
                    subscribeInner(asqVar);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.upstream.dispose();
                    onError(th);
                }
            }
        }

        void subscribeInner(ObservableSource<? extends U> asqVar) {
            ObservableSource<? extends U> poll;
            while (asqVar instanceof Callable) {
                if (tryEmitScalar((Callable) asqVar) && this.maxConcurrency != Integer.MAX_VALUE) {
                    boolean z = false;
                    synchronized (this) {
                        poll = this.sources.poll();
                        if (poll == null) {
                            this.wip--;
                            z = true;
                        }
                    }
                    if (z) {
                        drain();
                        return;
                    }
                    asqVar = poll;
                } else {
                    return;
                }
            }
            long j = this.uniqueId;
            this.uniqueId = 1 + j;
            C4444a<T, U> aVar = new C4444a<>(this, j);
            if (addInner(aVar)) {
                asqVar.subscribe(aVar);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean addInner(C4444a<T, U> aVar) {
            C4444a<?, ?>[] aVarArr;
            C4444a[] aVarArr2;
            do {
                aVarArr = this.observers.get();
                if (aVarArr == CANCELLED) {
                    aVar.dispose();
                    return false;
                }
                int length = aVarArr.length;
                aVarArr2 = new C4444a[length + 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
                aVarArr2[length] = aVar;
            } while (!this.observers.compareAndSet(aVarArr, aVarArr2));
            return true;
        }

        void removeInner(C4444a<T, U> aVar) {
            C4444a<?, ?>[] aVarArr;
            C4444a<?, ?>[] aVarArr2;
            do {
                aVarArr = this.observers.get();
                int length = aVarArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (aVarArr[i2] == aVar) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            aVarArr2 = EMPTY;
                        } else {
                            C4444a<?, ?>[] aVarArr3 = new C4444a[length - 1];
                            System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                            System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                            aVarArr2 = aVarArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.observers.compareAndSet(aVarArr, aVarArr2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v3, types: [z1.avv] */
        boolean tryEmitScalar(Callable<? extends U> callable) {
            SpscArrayQueue bqkVar;
            try {
                Object call = callable.call();
                if (call == null) {
                    return true;
                }
                if (get() != 0 || !compareAndSet(0, 1)) {
                    SimplePlainQueue<U> avvVar = this.queue;
                    SimplePlainQueue<U> avvVar2 = avvVar;
                    if (avvVar == null) {
                        int i = this.maxConcurrency;
                        if (i == Integer.MAX_VALUE) {
                            bqkVar = new SpscLinkedArrayQueue(this.bufferSize);
                        } else {
                            bqkVar = new SpscArrayQueue(i);
                        }
                        this.queue = bqkVar;
                        avvVar2 = bqkVar;
                    }
                    if (!avvVar2.offer(call)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return true;
                    } else if (getAndIncrement() != 0) {
                        return false;
                    }
                } else {
                    this.downstream.onNext(call);
                    if (decrementAndGet() == 0) {
                        return true;
                    }
                }
                drainLoop();
                return true;
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.errors.addThrowable(th);
                drain();
                return true;
            }
        }

        void tryEmit(U u, C4444a<T, U> aVar) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                SimpleQueue avwVar = aVar.queue;
                if (avwVar == null) {
                    avwVar = new SpscLinkedArrayQueue(this.bufferSize);
                    aVar.queue = avwVar;
                }
                avwVar.offer(u);
                if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                this.downstream.onNext(u);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
            } else if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Throwable terminate;
            if (!this.cancelled) {
                this.cancelled = true;
                if (disposeAll() && (terminate = this.errors.terminate()) != null && terminate != ExceptionHelper.f20064a) {
                    RxJavaPlugins.m9212a(terminate);
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:125:0x0004, code lost:
            continue;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drainLoop() {
            /*
                Method dump skipped, instructions count: 303
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ObservableFlatMap.C4445b.drainLoop():void");
        }

        boolean checkTerminate() {
            if (this.cancelled) {
                return true;
            }
            Throwable th = this.errors.get();
            if (this.delayErrors || th == null) {
                return false;
            }
            disposeAll();
            Throwable terminate = this.errors.terminate();
            if (terminate != ExceptionHelper.f20064a) {
                this.downstream.onError(terminate);
            }
            return true;
        }

        boolean disposeAll() {
            C4444a<?, ?>[] andSet;
            this.upstream.dispose();
            C4444a<?, ?>[] aVarArr = this.observers.get();
            C4444a<?, ?>[] aVarArr2 = CANCELLED;
            if (aVarArr == aVarArr2 || (andSet = this.observers.getAndSet(aVarArr2)) == CANCELLED) {
                return false;
            }
            for (C4444a<?, ?> aVar : andSet) {
                aVar.dispose();
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableFlatMap.java */
    /* renamed from: z1.bkb$a */
    /* loaded from: classes3.dex */
    public static final class C4444a<T, U> extends AtomicReference<Disposable> implements Observer<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        volatile boolean done;
        int fusionMode;

        /* renamed from: id */
        final long f19111id;
        final C4445b<T, U> parent;
        volatile SimpleQueue<U> queue;

        C4444a(C4445b<T, U> bVar, long j) {
            this.f19111id = j;
            this.parent = bVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar) && (atrVar instanceof QueueDisposable)) {
                QueueDisposable avrVar = (QueueDisposable) atrVar;
                int requestFusion = avrVar.requestFusion(7);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = avrVar;
                    this.done = true;
                    this.parent.drain();
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = avrVar;
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(U u) {
            if (this.fusionMode == 0) {
                this.parent.tryEmit(u, this);
            } else {
                this.parent.drain();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.parent.errors.addThrowable(th)) {
                if (!this.parent.delayErrors) {
                    this.parent.disposeAll();
                }
                this.done = true;
                this.parent.drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
