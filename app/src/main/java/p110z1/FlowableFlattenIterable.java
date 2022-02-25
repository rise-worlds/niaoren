package p110z1;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bav */
/* loaded from: classes3.dex */
public final class FlowableFlattenIterable<T, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final Function<? super T, ? extends Iterable<? extends R>> f18104c;

    /* renamed from: d */
    final int f18105d;

    public FlowableFlattenIterable(Flowable<T> arvVar, Function<? super T, ? extends Iterable<? extends R>> aunVar, int i) {
        super(arvVar);
        this.f18104c = aunVar;
        this.f18105d = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super R> dbxVar) {
        if (this.f17812b instanceof Callable) {
            try {
                Object call = ((Callable) this.f17812b).call();
                if (call == null) {
                    EmptySubscription.complete(dbxVar);
                    return;
                }
                try {
                    FlowableFromIterable.m9792a((Subscriber) dbxVar, (Iterator) ((Iterable) this.f18104c.apply(call)).iterator());
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    EmptySubscription.error(th, dbxVar);
                }
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                EmptySubscription.error(th2, dbxVar);
            }
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4074a(dbxVar, this.f18104c, this.f18105d));
        }
    }

    /* compiled from: FlowableFlattenIterable.java */
    /* renamed from: z1.bav$a */
    /* loaded from: classes3.dex */
    static final class C4074a<T, R> extends BasicIntQueueSubscription<R> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -3096000382929934955L;
        volatile boolean cancelled;
        int consumed;
        Iterator<? extends R> current;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        int fusionMode;
        final int limit;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        final int prefetch;
        SimpleQueue<T> queue;
        dby upstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        C4074a(Subscriber<? super R> dbxVar, Function<? super T, ? extends Iterable<? extends R>> aunVar, int i) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = avtVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
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

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode != 0 || this.queue.offer(t)) {
                    drain();
                } else {
                    onError(new MissingBackpressureException("Queue is full?!"));
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done || !ExceptionHelper.m9430a(this.error, th)) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            drain();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                drain();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<?> dbxVar = this.downstream;
                SimpleQueue<T> avwVar = this.queue;
                boolean z = this.fusionMode != 1;
                Iterator<? extends R> it = this.current;
                int i = 1;
                while (true) {
                    if (it == null) {
                        boolean z2 = this.done;
                        try {
                            T poll = avwVar.poll();
                            if (!checkTerminated(z2, poll == null, dbxVar, avwVar)) {
                                if (poll != null) {
                                    try {
                                        it = ((Iterable) this.mapper.apply(poll)).iterator();
                                        if (!it.hasNext()) {
                                            consumedOne(z);
                                            it = null;
                                        } else {
                                            this.current = it;
                                        }
                                    } catch (Throwable th) {
                                        Exceptions.m9983b(th);
                                        this.upstream.cancel();
                                        ExceptionHelper.m9430a(this.error, th);
                                        dbxVar.onError(ExceptionHelper.m9431a(this.error));
                                        return;
                                    }
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            Exceptions.m9983b(th2);
                            this.upstream.cancel();
                            ExceptionHelper.m9430a(this.error, th2);
                            Throwable a = ExceptionHelper.m9431a(this.error);
                            this.current = null;
                            avwVar.clear();
                            dbxVar.onError(a);
                            return;
                        }
                    }
                    if (it != null) {
                        long j = this.requested.get();
                        long j2 = 0;
                        while (true) {
                            if (j2 == j) {
                                break;
                            } else if (!checkTerminated(this.done, false, dbxVar, avwVar)) {
                                try {
                                    dbxVar.onNext((Object) ObjectHelper.m9873a(it.next(), "The iterator returned a null value"));
                                    if (!checkTerminated(this.done, false, dbxVar, avwVar)) {
                                        j2++;
                                        try {
                                            if (!it.hasNext()) {
                                                consumedOne(z);
                                                this.current = null;
                                                it = null;
                                                break;
                                            }
                                        } catch (Throwable th3) {
                                            Exceptions.m9983b(th3);
                                            this.current = null;
                                            this.upstream.cancel();
                                            ExceptionHelper.m9430a(this.error, th3);
                                            dbxVar.onError(ExceptionHelper.m9431a(this.error));
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th4) {
                                    Exceptions.m9983b(th4);
                                    this.current = null;
                                    this.upstream.cancel();
                                    ExceptionHelper.m9430a(this.error, th4);
                                    dbxVar.onError(ExceptionHelper.m9431a(this.error));
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                        if (j2 == j) {
                            if (checkTerminated(this.done, avwVar.isEmpty() && it == null, dbxVar, avwVar)) {
                                return;
                            }
                        }
                        if (!(j2 == 0 || j == cjm.f20759b)) {
                            this.requested.addAndGet(-j2);
                        }
                        if (it == null) {
                        }
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }

        void consumedOne(boolean z) {
            if (z) {
                int i = this.consumed + 1;
                if (i == this.limit) {
                    this.consumed = 0;
                    this.upstream.request(i);
                    return;
                }
                this.consumed = i;
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> dbxVar, SimpleQueue<?> avwVar) {
            if (this.cancelled) {
                this.current = null;
                avwVar.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (this.error.get() != null) {
                    Throwable a = ExceptionHelper.m9431a(this.error);
                    this.current = null;
                    avwVar.clear();
                    dbxVar.onError(a);
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    dbxVar.onComplete();
                    return true;
                }
            }
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.current = null;
            this.queue.clear();
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.current == null && this.queue.isEmpty();
        }

        @Override // p110z1.SimpleQueue
        @atn
        public R poll() throws Exception {
            Iterator<? extends R> it = this.current;
            while (true) {
                if (it == null) {
                    T poll = this.queue.poll();
                    if (poll != null) {
                        it = ((Iterable) this.mapper.apply(poll)).iterator();
                        if (it.hasNext()) {
                            this.current = it;
                            break;
                        }
                        it = null;
                    } else {
                        return null;
                    }
                } else {
                    break;
                }
            }
            R r = (R) ObjectHelper.m9873a(it.next(), "The iterator returned a null value");
            if (!it.hasNext()) {
                this.current = null;
            }
            return r;
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return ((i & 1) == 0 || this.fusionMode != 1) ? 0 : 1;
        }
    }
}
