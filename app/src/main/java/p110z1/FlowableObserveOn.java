package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import p110z1.Scheduler;

/* renamed from: z1.bbz */
/* loaded from: classes3.dex */
public final class FlowableObserveOn<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Scheduler f18203c;

    /* renamed from: d */
    final boolean f18204d;

    /* renamed from: e */
    final int f18205e;

    public FlowableObserveOn(Flowable<T> arvVar, Scheduler astVar, boolean z, int i) {
        super(arvVar);
        this.f18203c = astVar;
        this.f18204d = z;
        this.f18205e = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        Scheduler.AbstractC3881c b = this.f18203c.mo9034b();
        if (dbxVar instanceof ConditionalSubscriber) {
            this.f17812b.m11187a((FlowableSubscriber) new C4128b((ConditionalSubscriber) dbxVar, b, this.f18204d, this.f18205e));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4129c(dbxVar, b, this.f18204d, this.f18205e));
        }
    }

    /* compiled from: FlowableObserveOn.java */
    /* renamed from: z1.bbz$a */
    /* loaded from: classes3.dex */
    static abstract class AbstractRunnableC4127a<T> extends BasicIntQueueSubscription<T> implements Runnable, FlowableSubscriber<T> {
        private static final long serialVersionUID = -8241002408341274697L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final int limit;
        boolean outputFused;
        final int prefetch;
        long produced;
        SimpleQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        int sourceMode;
        dby upstream;
        final Scheduler.AbstractC3881c worker;

        abstract void runAsync();

        abstract void runBackfused();

        abstract void runSync();

        AbstractRunnableC4127a(Scheduler.AbstractC3881c cVar, boolean z, int i) {
            this.worker = cVar;
            this.delayError = z;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // p110z1.Subscriber
        public final void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode == 2) {
                    trySchedule();
                    return;
                }
                if (!this.queue.offer(t)) {
                    this.upstream.cancel();
                    this.error = new MissingBackpressureException("Queue is full?!");
                    this.done = true;
                }
                trySchedule();
            }
        }

        @Override // p110z1.Subscriber
        public final void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.error = th;
            this.done = true;
            trySchedule();
        }

        @Override // p110z1.Subscriber
        public final void onComplete() {
            if (!this.done) {
                this.done = true;
                trySchedule();
            }
        }

        @Override // p110z1.dby
        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                trySchedule();
            }
        }

        @Override // p110z1.dby
        public final void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        final void trySchedule() {
            if (getAndIncrement() == 0) {
                this.worker.mo9031a(this);
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.outputFused) {
                runBackfused();
            } else if (this.sourceMode == 1) {
                runSync();
            } else {
                runAsync();
            }
        }

        final boolean checkTerminated(boolean z, boolean z2, Subscriber<?> dbxVar) {
            if (this.cancelled) {
                clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!this.delayError) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.cancelled = true;
                        clear();
                        dbxVar.onError(th);
                        this.worker.dispose();
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        this.cancelled = true;
                        dbxVar.onComplete();
                        this.worker.dispose();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    this.cancelled = true;
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        dbxVar.onError(th2);
                    } else {
                        dbxVar.onComplete();
                    }
                    this.worker.dispose();
                    return true;
                }
            }
        }

        @Override // p110z1.QueueFuseable
        public final int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Override // p110z1.SimpleQueue
        public final void clear() {
            this.queue.clear();
        }

        @Override // p110z1.SimpleQueue
        public final boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    /* compiled from: FlowableObserveOn.java */
    /* renamed from: z1.bbz$c */
    /* loaded from: classes3.dex */
    static final class C4129c<T> extends AbstractRunnableC4127a<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -4547113800637756442L;
        final Subscriber<? super T> downstream;

        C4129c(Subscriber<? super T> dbxVar, Scheduler.AbstractC3881c cVar, boolean z, int i) {
            super(cVar, z, i);
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = 1;
                        this.queue = avtVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = 2;
                        this.queue = avtVar;
                        this.downstream.onSubscribe(this);
                        dbyVar.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
                dbyVar.request(this.prefetch);
            }
        }

        @Override // p110z1.FlowableObserveOn.AbstractRunnableC4127a
        void runSync() {
            Subscriber<? super T> dbxVar = this.downstream;
            SimpleQueue<T> avwVar = this.queue;
            long j = this.produced;
            int i = 1;
            while (true) {
                long j2 = this.requested.get();
                while (j != j2) {
                    try {
                        Object obj = (T) avwVar.poll();
                        if (!this.cancelled) {
                            if (obj == null) {
                                this.cancelled = true;
                                dbxVar.onComplete();
                                this.worker.dispose();
                                return;
                            }
                            dbxVar.onNext(obj);
                            j++;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.cancelled = true;
                        this.upstream.cancel();
                        dbxVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (!this.cancelled) {
                    if (avwVar.isEmpty()) {
                        this.cancelled = true;
                        dbxVar.onComplete();
                        this.worker.dispose();
                        return;
                    }
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        @Override // p110z1.FlowableObserveOn.AbstractRunnableC4127a
        void runAsync() {
            int i;
            Subscriber<? super T> dbxVar = this.downstream;
            SimpleQueue<T> avwVar = this.queue;
            long j = this.produced;
            int i2 = 1;
            while (true) {
                long j2 = this.requested.get();
                while (true) {
                    i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
                    if (i == 0) {
                        break;
                    }
                    boolean z = this.done;
                    try {
                        Object obj = (T) avwVar.poll();
                        boolean z2 = obj == null;
                        if (!checkTerminated(z, z2, dbxVar)) {
                            if (z2) {
                                break;
                            }
                            dbxVar.onNext(obj);
                            j++;
                            if (j == this.limit) {
                                if (j2 != cjm.f20759b) {
                                    j2 = this.requested.addAndGet(-j);
                                }
                                this.upstream.request(j);
                                j = 0;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.cancelled = true;
                        this.upstream.cancel();
                        avwVar.clear();
                        dbxVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (i != 0 || !checkTerminated(this.done, avwVar.isEmpty(), dbxVar)) {
                    int i3 = get();
                    if (i2 == i3) {
                        this.produced = j;
                        i2 = addAndGet(-i2);
                        if (i2 == 0) {
                            return;
                        }
                    } else {
                        i2 = i3;
                    }
                } else {
                    return;
                }
            }
        }

        @Override // p110z1.FlowableObserveOn.AbstractRunnableC4127a
        void runBackfused() {
            int i = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                this.downstream.onNext(null);
                if (z) {
                    this.cancelled = true;
                    Throwable th = this.error;
                    if (th != null) {
                        this.downstream.onError(th);
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
            }
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll = this.queue.poll();
            if (!(poll == null || this.sourceMode == 1)) {
                long j = this.produced + 1;
                if (j == this.limit) {
                    this.produced = 0L;
                    this.upstream.request(j);
                } else {
                    this.produced = j;
                }
            }
            return poll;
        }
    }

    /* compiled from: FlowableObserveOn.java */
    /* renamed from: z1.bbz$b */
    /* loaded from: classes3.dex */
    static final class C4128b<T> extends AbstractRunnableC4127a<T> {
        private static final long serialVersionUID = 644624475404284533L;
        long consumed;
        final ConditionalSubscriber<? super T> downstream;

        C4128b(ConditionalSubscriber<? super T> aviVar, Scheduler.AbstractC3881c cVar, boolean z, int i) {
            super(cVar, z, i);
            this.downstream = aviVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = 1;
                        this.queue = avtVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = 2;
                        this.queue = avtVar;
                        this.downstream.onSubscribe(this);
                        dbyVar.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
                dbyVar.request(this.prefetch);
            }
        }

        @Override // p110z1.FlowableObserveOn.AbstractRunnableC4127a
        void runSync() {
            ConditionalSubscriber<? super T> aviVar = this.downstream;
            SimpleQueue<T> avwVar = this.queue;
            long j = this.produced;
            int i = 1;
            while (true) {
                long j2 = this.requested.get();
                while (j != j2) {
                    try {
                        Object obj = (T) avwVar.poll();
                        if (!this.cancelled) {
                            if (obj == null) {
                                this.cancelled = true;
                                aviVar.onComplete();
                                this.worker.dispose();
                                return;
                            } else if (aviVar.tryOnNext(obj)) {
                                j++;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.cancelled = true;
                        this.upstream.cancel();
                        aviVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (!this.cancelled) {
                    if (avwVar.isEmpty()) {
                        this.cancelled = true;
                        aviVar.onComplete();
                        this.worker.dispose();
                        return;
                    }
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        @Override // p110z1.FlowableObserveOn.AbstractRunnableC4127a
        void runAsync() {
            int i;
            ConditionalSubscriber<? super T> aviVar = this.downstream;
            SimpleQueue<T> avwVar = this.queue;
            long j = this.produced;
            long j2 = this.consumed;
            int i2 = 1;
            while (true) {
                long j3 = this.requested.get();
                while (true) {
                    i = (j > j3 ? 1 : (j == j3 ? 0 : -1));
                    if (i == 0) {
                        break;
                    }
                    boolean z = this.done;
                    try {
                        Object obj = (T) avwVar.poll();
                        boolean z2 = obj == null;
                        if (!checkTerminated(z, z2, aviVar)) {
                            if (z2) {
                                break;
                            }
                            if (aviVar.tryOnNext(obj)) {
                                j++;
                            }
                            j2++;
                            if (j2 == this.limit) {
                                this.upstream.request(j2);
                                j2 = 0;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.cancelled = true;
                        this.upstream.cancel();
                        avwVar.clear();
                        aviVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (i != 0 || !checkTerminated(this.done, avwVar.isEmpty(), aviVar)) {
                    int i3 = get();
                    if (i2 == i3) {
                        this.produced = j;
                        this.consumed = j2;
                        i2 = addAndGet(-i2);
                        if (i2 == 0) {
                            return;
                        }
                    } else {
                        i2 = i3;
                    }
                } else {
                    return;
                }
            }
        }

        @Override // p110z1.FlowableObserveOn.AbstractRunnableC4127a
        void runBackfused() {
            int i = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                this.downstream.onNext(null);
                if (z) {
                    this.cancelled = true;
                    Throwable th = this.error;
                    if (th != null) {
                        this.downstream.onError(th);
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
            }
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll = this.queue.poll();
            if (!(poll == null || this.sourceMode == 1)) {
                long j = this.consumed + 1;
                if (j == this.limit) {
                    this.consumed = 0L;
                    this.upstream.request(j);
                } else {
                    this.consumed = j;
                }
            }
            return poll;
        }
    }
}
