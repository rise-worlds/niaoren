package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.azm */
/* loaded from: classes3.dex */
public final class FlowableConcatMap<T, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<? extends R>> f17953c;

    /* renamed from: d */
    final int f17954d;

    /* renamed from: e */
    final ErrorMode f17955e;

    /* compiled from: FlowableConcatMap.java */
    /* renamed from: z1.azm$e */
    /* loaded from: classes3.dex */
    interface AbstractC4018e<T> {
        void innerComplete();

        void innerError(Throwable th);

        void innerNext(T t);
    }

    public FlowableConcatMap(Flowable<T> arvVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, ErrorMode bsuVar) {
        super(arvVar);
        this.f17953c = aunVar;
        this.f17954d = i;
        this.f17955e = bsuVar;
    }

    /* renamed from: a */
    public static <T, R> Subscriber<T> m9797a(Subscriber<? super R> dbxVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, ErrorMode bsuVar) {
        switch (bsuVar) {
            case BOUNDARY:
                return new C4015b(dbxVar, aunVar, i, false);
            case END:
                return new C4015b(dbxVar, aunVar, i, true);
            default:
                return new C4016c(dbxVar, aunVar, i);
        }
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        if (!FlowableScalarXMap.m9735a(this.f17812b, dbxVar, this.f17953c)) {
            this.f17812b.subscribe(m9797a(dbxVar, this.f17953c, this.f17954d, this.f17955e));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableConcatMap.java */
    /* renamed from: z1.azm$a */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC4014a<T, R> extends AtomicInteger implements FlowableSubscriber<T>, AbstractC4018e<R>, dby {
        private static final long serialVersionUID = -3511336836796789179L;
        volatile boolean active;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        final int limit;
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        final int prefetch;
        SimpleQueue<T> queue;
        int sourceMode;
        dby upstream;
        final C4017d<R> inner = new C4017d<>(this);
        final AtomicThrowable errors = new AtomicThrowable();

        abstract void drain();

        abstract void subscribeActual();

        AbstractC4014a(Function<? super T, ? extends Publisher<? extends R>> aunVar, int i) {
            this.mapper = aunVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public final void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = avtVar;
                        this.done = true;
                        subscribeActual();
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = avtVar;
                        subscribeActual();
                        dbyVar.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                subscribeActual();
                dbyVar.request(this.prefetch);
            }
        }

        @Override // p110z1.Subscriber
        public final void onNext(T t) {
            if (this.sourceMode == 2 || this.queue.offer(t)) {
                drain();
                return;
            }
            this.upstream.cancel();
            onError(new IllegalStateException("Queue full?!"));
        }

        @Override // p110z1.Subscriber
        public final void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.FlowableConcatMap.AbstractC4018e
        public final void innerComplete() {
            this.active = false;
            drain();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableConcatMap.java */
    /* renamed from: z1.azm$c */
    /* loaded from: classes3.dex */
    public static final class C4016c<T, R> extends AbstractC4014a<T, R> {
        private static final long serialVersionUID = 7898995095634264146L;
        final Subscriber<? super R> downstream;
        final AtomicInteger wip = new AtomicInteger();

        C4016c(Subscriber<? super R> dbxVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i) {
            super(aunVar, i);
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableConcatMap.AbstractC4014a
        void subscribeActual() {
            this.downstream.onSubscribe(this);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.inner.cancel();
                if (getAndIncrement() == 0) {
                    this.downstream.onError(this.errors.terminate());
                    return;
                }
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.FlowableConcatMap.AbstractC4018e
        public void innerNext(R r) {
            if (get() == 0 && compareAndSet(0, 1)) {
                this.downstream.onNext(r);
                if (!compareAndSet(1, 0)) {
                    this.downstream.onError(this.errors.terminate());
                }
            }
        }

        @Override // p110z1.FlowableConcatMap.AbstractC4018e
        public void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.downstream.onError(this.errors.terminate());
                    return;
                }
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.inner.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.inner.cancel();
                this.upstream.cancel();
            }
        }

        @Override // p110z1.FlowableConcatMap.AbstractC4014a
        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                while (!this.cancelled) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            T poll = this.queue.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                this.downstream.onComplete();
                                return;
                            } else if (!z2) {
                                try {
                                    Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.mapper.apply(poll), "The mapper returned a null Publisher");
                                    if (this.sourceMode != 1) {
                                        int i = this.consumed + 1;
                                        if (i == this.limit) {
                                            this.consumed = 0;
                                            this.upstream.request(i);
                                        } else {
                                            this.consumed = i;
                                        }
                                    }
                                    if (dbwVar instanceof Callable) {
                                        try {
                                            Object call = ((Callable) dbwVar).call();
                                            if (call == null) {
                                                continue;
                                            } else if (!this.inner.isUnbounded()) {
                                                this.active = true;
                                                this.inner.setSubscription(new C4019f(call, this.inner));
                                            } else if (get() == 0 && compareAndSet(0, 1)) {
                                                this.downstream.onNext(call);
                                                if (!compareAndSet(1, 0)) {
                                                    this.downstream.onError(this.errors.terminate());
                                                    return;
                                                }
                                            }
                                        } catch (Throwable th) {
                                            Exceptions.m9983b(th);
                                            this.upstream.cancel();
                                            this.errors.addThrowable(th);
                                            this.downstream.onError(this.errors.terminate());
                                            return;
                                        }
                                    } else {
                                        this.active = true;
                                        dbwVar.subscribe(this.inner);
                                    }
                                } catch (Throwable th2) {
                                    Exceptions.m9983b(th2);
                                    this.upstream.cancel();
                                    this.errors.addThrowable(th2);
                                    this.downstream.onError(this.errors.terminate());
                                    return;
                                }
                            }
                        } catch (Throwable th3) {
                            Exceptions.m9983b(th3);
                            this.upstream.cancel();
                            this.errors.addThrowable(th3);
                            this.downstream.onError(this.errors.terminate());
                            return;
                        }
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableConcatMap.java */
    /* renamed from: z1.azm$f */
    /* loaded from: classes3.dex */
    public static final class C4019f<T> implements dby {

        /* renamed from: a */
        final Subscriber<? super T> f17957a;

        /* renamed from: b */
        final T f17958b;

        /* renamed from: c */
        boolean f17959c;

        @Override // p110z1.dby
        public void cancel() {
        }

        C4019f(T t, Subscriber<? super T> dbxVar) {
            this.f17958b = t;
            this.f17957a = dbxVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (j > 0 && !this.f17959c) {
                this.f17959c = true;
                Subscriber<? super T> dbxVar = this.f17957a;
                dbxVar.onNext((T) this.f17958b);
                dbxVar.onComplete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableConcatMap.java */
    /* renamed from: z1.azm$b */
    /* loaded from: classes3.dex */
    public static final class C4015b<T, R> extends AbstractC4014a<T, R> {
        private static final long serialVersionUID = -2945777694260521066L;
        final Subscriber<? super R> downstream;
        final boolean veryEnd;

        C4015b(Subscriber<? super R> dbxVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, boolean z) {
            super(aunVar, i);
            this.downstream = dbxVar;
            this.veryEnd = z;
        }

        @Override // p110z1.FlowableConcatMap.AbstractC4014a
        void subscribeActual() {
            this.downstream.onSubscribe(this);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.FlowableConcatMap.AbstractC4018e
        public void innerNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // p110z1.FlowableConcatMap.AbstractC4018e
        public void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (!this.veryEnd) {
                    this.upstream.cancel();
                    this.done = true;
                }
                this.active = false;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.inner.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.inner.cancel();
                this.upstream.cancel();
            }
        }

        @Override // p110z1.FlowableConcatMap.AbstractC4014a
        void drain() {
            if (getAndIncrement() == 0) {
                while (!this.cancelled) {
                    if (!this.active) {
                        boolean z = this.done;
                        if (!z || this.veryEnd || this.errors.get() == null) {
                            try {
                                T poll = this.queue.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    Throwable terminate = this.errors.terminate();
                                    if (terminate != null) {
                                        this.downstream.onError(terminate);
                                        return;
                                    } else {
                                        this.downstream.onComplete();
                                        return;
                                    }
                                } else if (!z2) {
                                    try {
                                        Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.mapper.apply(poll), "The mapper returned a null Publisher");
                                        if (this.sourceMode != 1) {
                                            int i = this.consumed + 1;
                                            if (i == this.limit) {
                                                this.consumed = 0;
                                                this.upstream.request(i);
                                            } else {
                                                this.consumed = i;
                                            }
                                        }
                                        if (dbwVar instanceof Callable) {
                                            try {
                                                Object call = ((Callable) dbwVar).call();
                                                if (call == null) {
                                                    continue;
                                                } else if (this.inner.isUnbounded()) {
                                                    this.downstream.onNext(call);
                                                } else {
                                                    this.active = true;
                                                    this.inner.setSubscription(new C4019f(call, this.inner));
                                                }
                                            } catch (Throwable th) {
                                                Exceptions.m9983b(th);
                                                this.upstream.cancel();
                                                this.errors.addThrowable(th);
                                                this.downstream.onError(this.errors.terminate());
                                                return;
                                            }
                                        } else {
                                            this.active = true;
                                            dbwVar.subscribe(this.inner);
                                        }
                                    } catch (Throwable th2) {
                                        Exceptions.m9983b(th2);
                                        this.upstream.cancel();
                                        this.errors.addThrowable(th2);
                                        this.downstream.onError(this.errors.terminate());
                                        return;
                                    }
                                }
                            } catch (Throwable th3) {
                                Exceptions.m9983b(th3);
                                this.upstream.cancel();
                                this.errors.addThrowable(th3);
                                this.downstream.onError(this.errors.terminate());
                                return;
                            }
                        } else {
                            this.downstream.onError(this.errors.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableConcatMap.java */
    /* renamed from: z1.azm$d */
    /* loaded from: classes3.dex */
    public static final class C4017d<R> extends SubscriptionArbiter implements FlowableSubscriber<R> {
        private static final long serialVersionUID = 897683679971470653L;
        final AbstractC4018e<R> parent;
        long produced;

        C4017d(AbstractC4018e<R> eVar) {
            super(false);
            this.parent = eVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            setSubscription(dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(R r) {
            this.produced++;
            this.parent.innerNext(r);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            long j = this.produced;
            if (j != 0) {
                this.produced = 0L;
                produced(j);
            }
            this.parent.innerError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            long j = this.produced;
            if (j != 0) {
                this.produced = 0L;
                produced(j);
            }
            this.parent.innerComplete();
        }
    }
}
