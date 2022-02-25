package p110z1;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bjb */
/* loaded from: classes3.dex */
public final class ObservableConcatMapEager<T, R> extends AbstractObservableWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super T, ? extends ObservableSource<? extends R>> f18963b;

    /* renamed from: c */
    final ErrorMode f18964c;

    /* renamed from: d */
    final int f18965d;

    /* renamed from: e */
    final int f18966e;

    public ObservableConcatMapEager(ObservableSource<T> asqVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar, ErrorMode bsuVar, int i, int i2) {
        super(asqVar);
        this.f18963b = aunVar;
        this.f18964c = bsuVar;
        this.f18965d = i;
        this.f18966e = i2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        this.f18807a.subscribe(new C4414a(assVar, this.f18963b, this.f18965d, this.f18966e, this.f18964c));
    }

    /* compiled from: ObservableConcatMapEager.java */
    /* renamed from: z1.bjb$a */
    /* loaded from: classes3.dex */
    static final class C4414a<T, R> extends AtomicInteger implements Observer<T>, Disposable, InnerQueuedObserverSupport<R> {
        private static final long serialVersionUID = 8080567949447303262L;
        int activeCount;
        volatile boolean cancelled;
        InnerQueuedObserver<R> current;
        volatile boolean done;
        final Observer<? super R> downstream;
        final ErrorMode errorMode;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        final int maxConcurrency;
        final int prefetch;
        SimpleQueue<T> queue;
        int sourceMode;
        Disposable upstream;
        final AtomicThrowable error = new AtomicThrowable();
        final ArrayDeque<InnerQueuedObserver<R>> observers = new ArrayDeque<>();

        C4414a(Observer<? super R> assVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i, int i2, ErrorMode bsuVar) {
            this.downstream = assVar;
            this.mapper = aunVar;
            this.maxConcurrency = i;
            this.prefetch = i2;
            this.errorMode = bsuVar;
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
                this.queue = new SpscLinkedArrayQueue(this.prefetch);
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
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                drainAndDispose();
            }
        }

        void drainAndDispose() {
            if (getAndIncrement() == 0) {
                do {
                    this.queue.clear();
                    disposeAll();
                } while (decrementAndGet() != 0);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void disposeAll() {
            InnerQueuedObserver<R> awpVar = this.current;
            if (awpVar != null) {
                awpVar.dispose();
            }
            while (true) {
                InnerQueuedObserver<R> poll = this.observers.poll();
                if (poll != null) {
                    poll.dispose();
                } else {
                    return;
                }
            }
        }

        @Override // p110z1.InnerQueuedObserverSupport
        public void innerNext(InnerQueuedObserver<R> awpVar, R r) {
            awpVar.queue().offer(r);
            drain();
        }

        @Override // p110z1.InnerQueuedObserverSupport
        public void innerError(InnerQueuedObserver<R> awpVar, Throwable th) {
            if (this.error.addThrowable(th)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.upstream.dispose();
                }
                awpVar.setDone();
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.InnerQueuedObserverSupport
        public void innerComplete(InnerQueuedObserver<R> awpVar) {
            awpVar.setDone();
            drain();
        }

        @Override // p110z1.InnerQueuedObserverSupport
        public void drain() {
            R poll;
            boolean z;
            if (getAndIncrement() == 0) {
                SimpleQueue<T> avwVar = this.queue;
                ArrayDeque<InnerQueuedObserver<R>> arrayDeque = this.observers;
                Observer<? super R> assVar = this.downstream;
                ErrorMode bsuVar = this.errorMode;
                int i = 1;
                while (true) {
                    int i2 = this.activeCount;
                    while (i2 != this.maxConcurrency) {
                        if (this.cancelled) {
                            avwVar.clear();
                            disposeAll();
                            return;
                        } else if (bsuVar != ErrorMode.IMMEDIATE || this.error.get() == null) {
                            try {
                                T poll2 = avwVar.poll();
                                if (poll2 == null) {
                                    break;
                                }
                                ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.mapper.apply(poll2), "The mapper returned a null ObservableSource");
                                InnerQueuedObserver<R> awpVar = new InnerQueuedObserver<>(this, this.prefetch);
                                arrayDeque.offer(awpVar);
                                asqVar.subscribe(awpVar);
                                i2++;
                            } catch (Throwable th) {
                                Exceptions.m9983b(th);
                                this.upstream.dispose();
                                avwVar.clear();
                                disposeAll();
                                this.error.addThrowable(th);
                                assVar.onError(this.error.terminate());
                                return;
                            }
                        } else {
                            avwVar.clear();
                            disposeAll();
                            assVar.onError(this.error.terminate());
                            return;
                        }
                    }
                    this.activeCount = i2;
                    if (this.cancelled) {
                        avwVar.clear();
                        disposeAll();
                        return;
                    } else if (bsuVar != ErrorMode.IMMEDIATE || this.error.get() == null) {
                        InnerQueuedObserver<R> awpVar2 = this.current;
                        if (awpVar2 == null) {
                            if (bsuVar != ErrorMode.BOUNDARY || this.error.get() == null) {
                                boolean z2 = this.done;
                                InnerQueuedObserver<R> poll3 = arrayDeque.poll();
                                boolean z3 = poll3 == null;
                                if (!z2 || !z3) {
                                    if (!z3) {
                                        this.current = poll3;
                                    }
                                    awpVar2 = poll3;
                                } else if (this.error.get() != null) {
                                    avwVar.clear();
                                    disposeAll();
                                    assVar.onError(this.error.terminate());
                                    return;
                                } else {
                                    assVar.onComplete();
                                    return;
                                }
                            } else {
                                avwVar.clear();
                                disposeAll();
                                assVar.onError(this.error.terminate());
                                return;
                            }
                        }
                        if (awpVar2 != null) {
                            SimpleQueue<R> queue = awpVar2.queue();
                            while (!this.cancelled) {
                                boolean isDone = awpVar2.isDone();
                                if (bsuVar != ErrorMode.IMMEDIATE || this.error.get() == null) {
                                    try {
                                        poll = queue.poll();
                                        z = poll == null;
                                    } catch (Throwable th2) {
                                        Exceptions.m9983b(th2);
                                        this.error.addThrowable(th2);
                                        this.current = null;
                                        this.activeCount--;
                                    }
                                    if (isDone && z) {
                                        this.current = null;
                                        this.activeCount--;
                                    } else if (!z) {
                                        assVar.onNext(poll);
                                    }
                                } else {
                                    avwVar.clear();
                                    disposeAll();
                                    assVar.onError(this.error.terminate());
                                    return;
                                }
                            }
                            avwVar.clear();
                            disposeAll();
                            return;
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        avwVar.clear();
                        disposeAll();
                        assVar.onError(this.error.terminate());
                        return;
                    }
                }
            }
        }
    }
}
