package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bmr */
/* loaded from: classes3.dex */
public final class ObservableSwitchMap<T, R> extends AbstractObservableWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super T, ? extends ObservableSource<? extends R>> f19410b;

    /* renamed from: c */
    final int f19411c;

    /* renamed from: d */
    final boolean f19412d;

    public ObservableSwitchMap(ObservableSource<T> asqVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i, boolean z) {
        super(asqVar);
        this.f19410b = aunVar;
        this.f19411c = i;
        this.f19412d = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super R> assVar) {
        if (!ObservableScalarXMap.m9575a(this.f18807a, assVar, this.f19410b)) {
            this.f18807a.subscribe(new C4566b(assVar, this.f19410b, this.f19411c, this.f19412d));
        }
    }

    /* compiled from: ObservableSwitchMap.java */
    /* renamed from: z1.bmr$b */
    /* loaded from: classes3.dex */
    static final class C4566b<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        static final C4565a<Object, Object> CANCELLED = new C4565a<>(null, -1, 1);
        private static final long serialVersionUID = -3491074160481096299L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Observer<? super R> downstream;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        volatile long unique;
        Disposable upstream;
        final AtomicReference<C4565a<T, R>> active = new AtomicReference<>();
        final AtomicThrowable errors = new AtomicThrowable();

        static {
            CANCELLED.cancel();
        }

        C4566b(Observer<? super R> assVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i, boolean z) {
            this.downstream = assVar;
            this.mapper = aunVar;
            this.bufferSize = i;
            this.delayErrors = z;
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
            C4565a<T, R> aVar;
            long j = this.unique + 1;
            this.unique = j;
            C4565a<T, R> aVar2 = this.active.get();
            if (aVar2 != null) {
                aVar2.cancel();
            }
            try {
                ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.mapper.apply(t), "The ObservableSource returned is null");
                C4565a<T, R> aVar3 = new C4565a<>(this, j, this.bufferSize);
                do {
                    aVar = this.active.get();
                    if (aVar == CANCELLED) {
                        return;
                    }
                } while (!this.active.compareAndSet(aVar, aVar3));
                asqVar.subscribe(aVar3);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.done || !this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            if (!this.delayErrors) {
                disposeInner();
            }
            this.done = true;
            drain();
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
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                disposeInner();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        /* JADX WARN: Multi-variable type inference failed */
        void disposeInner() {
            C4565a<Object, Object> aVar;
            C4565a<T, R> aVar2 = this.active.get();
            C4565a<Object, Object> aVar3 = CANCELLED;
            if (aVar2 != aVar3 && (aVar = (C4565a) this.active.getAndSet(aVar3)) != CANCELLED && aVar != null) {
                aVar.cancel();
            }
        }

        void drain() {
            SimpleQueue<R> avwVar;
            R r;
            if (getAndIncrement() == 0) {
                Observer<? super R> assVar = this.downstream;
                AtomicReference<C4565a<T, R>> atomicReference = this.active;
                boolean z = this.delayErrors;
                int i = 1;
                while (!this.cancelled) {
                    if (this.done) {
                        boolean z2 = atomicReference.get() == null;
                        if (z) {
                            if (z2) {
                                Throwable th = this.errors.get();
                                if (th != null) {
                                    assVar.onError(th);
                                    return;
                                } else {
                                    assVar.onComplete();
                                    return;
                                }
                            }
                        } else if (this.errors.get() != null) {
                            assVar.onError(this.errors.terminate());
                            return;
                        } else if (z2) {
                            assVar.onComplete();
                            return;
                        }
                    }
                    C4565a<T, R> aVar = atomicReference.get();
                    if (!(aVar == null || (avwVar = aVar.queue) == null)) {
                        if (aVar.done) {
                            boolean isEmpty = avwVar.isEmpty();
                            if (z) {
                                if (isEmpty) {
                                    atomicReference.compareAndSet(aVar, null);
                                }
                            } else if (this.errors.get() != null) {
                                assVar.onError(this.errors.terminate());
                                return;
                            } else if (isEmpty) {
                                atomicReference.compareAndSet(aVar, null);
                            }
                        }
                        boolean z3 = false;
                        while (!this.cancelled) {
                            if (aVar != atomicReference.get()) {
                                z3 = true;
                            } else if (z || this.errors.get() == null) {
                                boolean z4 = aVar.done;
                                try {
                                    r = avwVar.poll();
                                } catch (Throwable th2) {
                                    Exceptions.m9983b(th2);
                                    this.errors.addThrowable(th2);
                                    atomicReference.compareAndSet(aVar, null);
                                    if (!z) {
                                        disposeInner();
                                        this.upstream.dispose();
                                        this.done = true;
                                    } else {
                                        aVar.cancel();
                                    }
                                    r = (Object) null;
                                    z3 = true;
                                }
                                boolean z5 = r == null;
                                if (z4 && z5) {
                                    atomicReference.compareAndSet(aVar, null);
                                    z3 = true;
                                } else if (!z5) {
                                    assVar.onNext(r);
                                }
                            } else {
                                assVar.onError(this.errors.terminate());
                                return;
                            }
                            if (z3) {
                                continue;
                            }
                        }
                        return;
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }

        void innerError(C4565a<T, R> aVar, Throwable th) {
            if (aVar.index != this.unique || !this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            if (!this.delayErrors) {
                this.upstream.dispose();
            }
            aVar.done = true;
            drain();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableSwitchMap.java */
    /* renamed from: z1.bmr$a */
    /* loaded from: classes3.dex */
    public static final class C4565a<T, R> extends AtomicReference<Disposable> implements Observer<R> {
        private static final long serialVersionUID = 3837284832786408377L;
        final int bufferSize;
        volatile boolean done;
        final long index;
        final C4566b<T, R> parent;
        volatile SimpleQueue<R> queue;

        C4565a(C4566b<T, R> bVar, long j, int i) {
            this.parent = bVar;
            this.index = j;
            this.bufferSize = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                if (atrVar instanceof QueueDisposable) {
                    QueueDisposable avrVar = (QueueDisposable) atrVar;
                    int requestFusion = avrVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.queue = avrVar;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.queue = avrVar;
                        return;
                    }
                }
                this.queue = new SpscLinkedArrayQueue(this.bufferSize);
            }
        }

        @Override // p110z1.Observer
        public void onNext(R r) {
            if (this.index == this.parent.unique) {
                if (r != null) {
                    this.queue.offer(r);
                }
                this.parent.drain();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.parent.innerError(this, th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (this.index == this.parent.unique) {
                this.done = true;
                this.parent.drain();
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this);
        }
    }
}
