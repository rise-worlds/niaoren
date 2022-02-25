package p110z1;

import p110z1.Scheduler;

/* renamed from: z1.bli */
/* loaded from: classes3.dex */
public final class ObservableObserveOn<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Scheduler f19240b;

    /* renamed from: c */
    final boolean f19241c;

    /* renamed from: d */
    final int f19242d;

    public ObservableObserveOn(ObservableSource<T> asqVar, Scheduler astVar, boolean z, int i) {
        super(asqVar);
        this.f19240b = astVar;
        this.f19241c = z;
        this.f19242d = i;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        Scheduler astVar = this.f19240b;
        if (astVar instanceof TrampolineScheduler) {
            this.f18807a.subscribe(assVar);
            return;
        }
        this.f18807a.subscribe(new RunnableC4500a(assVar, astVar.mo9034b(), this.f19241c, this.f19242d));
    }

    /* compiled from: ObservableObserveOn.java */
    /* renamed from: z1.bli$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4500a<T> extends BasicIntQueueDisposable<T> implements Runnable, Observer<T> {
        private static final long serialVersionUID = 6576896619930983584L;
        final int bufferSize;
        final boolean delayError;
        volatile boolean disposed;
        volatile boolean done;
        final Observer<? super T> downstream;
        Throwable error;
        boolean outputFused;
        SimpleQueue<T> queue;
        int sourceMode;
        Disposable upstream;
        final Scheduler.AbstractC3881c worker;

        RunnableC4500a(Observer<? super T> assVar, Scheduler.AbstractC3881c cVar, boolean z, int i) {
            this.downstream = assVar;
            this.worker = cVar;
            this.delayError = z;
            this.bufferSize = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                if (atrVar instanceof QueueDisposable) {
                    QueueDisposable avrVar = (QueueDisposable) atrVar;
                    int requestFusion = avrVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = avrVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        schedule();
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
            if (!this.done) {
                if (this.sourceMode != 2) {
                    this.queue.offer(t);
                }
                schedule();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.error = th;
            this.done = true;
            schedule();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                schedule();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.disposed) {
                this.disposed = true;
                this.upstream.dispose();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.mo9031a(this);
            }
        }

        void drainNormal() {
            SimpleQueue<T> avwVar = this.queue;
            Observer<? super T> assVar = this.downstream;
            int i = 1;
            while (!checkTerminated(this.done, avwVar.isEmpty(), assVar)) {
                while (true) {
                    boolean z = this.done;
                    try {
                        Object obj = (T) avwVar.poll();
                        boolean z2 = obj == null;
                        if (!checkTerminated(z, z2, assVar)) {
                            if (z2) {
                                i = addAndGet(-i);
                                if (i == 0) {
                                    return;
                                }
                            } else {
                                assVar.onNext(obj);
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.disposed = true;
                        this.upstream.dispose();
                        avwVar.clear();
                        assVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
            }
        }

        void drainFused() {
            int i = 1;
            while (!this.disposed) {
                boolean z = this.done;
                Throwable th = this.error;
                if (this.delayError || !z || th == null) {
                    this.downstream.onNext(null);
                    if (z) {
                        this.disposed = true;
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            this.downstream.onError(th2);
                        } else {
                            this.downstream.onComplete();
                        }
                        this.worker.dispose();
                        return;
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    this.disposed = true;
                    this.downstream.onError(th);
                    this.worker.dispose();
                    return;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.outputFused) {
                drainFused();
            } else {
                drainNormal();
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Observer<? super T> assVar) {
            if (this.disposed) {
                this.queue.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                Throwable th = this.error;
                if (this.delayError) {
                    if (!z2) {
                        return false;
                    }
                    this.disposed = true;
                    if (th != null) {
                        assVar.onError(th);
                    } else {
                        assVar.onComplete();
                    }
                    this.worker.dispose();
                    return true;
                } else if (th != null) {
                    this.disposed = true;
                    this.queue.clear();
                    assVar.onError(th);
                    this.worker.dispose();
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    this.disposed = true;
                    assVar.onComplete();
                    this.worker.dispose();
                    return true;
                }
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            return this.queue.poll();
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.queue.clear();
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }
}
