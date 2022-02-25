package p110z1;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.FlowableMap;

/* renamed from: z1.azk */
/* loaded from: classes3.dex */
public final class FlowableCombineLatest<T, R> extends Flowable<R> {
    @atn

    /* renamed from: b */
    final Publisher<? extends T>[] f17945b;
    @atn

    /* renamed from: c */
    final Iterable<? extends Publisher<? extends T>> f17946c;

    /* renamed from: d */
    final Function<? super Object[], ? extends R> f17947d;

    /* renamed from: e */
    final int f17948e;

    /* renamed from: f */
    final boolean f17949f;

    public FlowableCombineLatest(@AbstractC3889atm Publisher<? extends T>[] dbwVarArr, @AbstractC3889atm Function<? super Object[], ? extends R> aunVar, int i, boolean z) {
        this.f17945b = dbwVarArr;
        this.f17946c = null;
        this.f17947d = aunVar;
        this.f17948e = i;
        this.f17949f = z;
    }

    public FlowableCombineLatest(@AbstractC3889atm Iterable<? extends Publisher<? extends T>> iterable, @AbstractC3889atm Function<? super Object[], ? extends R> aunVar, int i, boolean z) {
        this.f17945b = null;
        this.f17946c = iterable;
        this.f17947d = aunVar;
        this.f17948e = i;
        this.f17949f = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super R> dbxVar) {
        int i;
        Publisher<? extends T>[] dbwVarArr = this.f17945b;
        if (dbwVarArr == null) {
            dbwVarArr = new Publisher[8];
            try {
                Iterator it = (Iterator) ObjectHelper.m9873a(this.f17946c.iterator(), "The iterator returned is null");
                i = 0;
                while (it.hasNext()) {
                    try {
                        try {
                            Publisher<? extends T> dbwVar = (Publisher) ObjectHelper.m9873a(it.next(), "The publisher returned by the iterator is null");
                            if (i == dbwVarArr.length) {
                                Publisher<? extends T>[] dbwVarArr2 = new Publisher[(i >> 2) + i];
                                System.arraycopy(dbwVarArr, 0, dbwVarArr2, 0, i);
                                dbwVarArr = dbwVarArr2;
                            }
                            i++;
                            dbwVarArr[i] = dbwVar;
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            EmptySubscription.error(th, dbxVar);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        EmptySubscription.error(th2, dbxVar);
                        return;
                    }
                }
            } catch (Throwable th3) {
                Exceptions.m9983b(th3);
                EmptySubscription.error(th3, dbxVar);
                return;
            }
        } else {
            i = dbwVarArr.length;
        }
        if (i == 0) {
            EmptySubscription.complete(dbxVar);
        } else if (i == 1) {
            dbwVarArr[0].subscribe(new FlowableMap.C4118b(dbxVar, new C4011c()));
        } else {
            C4009a aVar = new C4009a(dbxVar, this.f17947d, i, this.f17948e, this.f17949f);
            dbxVar.onSubscribe(aVar);
            aVar.subscribe(dbwVarArr, i);
        }
    }

    /* compiled from: FlowableCombineLatest.java */
    /* renamed from: z1.azk$a */
    /* loaded from: classes3.dex */
    static final class C4009a<T, R> extends BasicIntQueueSubscription<R> {
        private static final long serialVersionUID = -5082275438355852221L;
        volatile boolean cancelled;
        final Function<? super Object[], ? extends R> combiner;
        int completedSources;
        final boolean delayErrors;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        int nonEmptySources;
        boolean outputFused;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested;
        final C4010b<T>[] subscribers;

        C4009a(Subscriber<? super R> dbxVar, Function<? super Object[], ? extends R> aunVar, int i, int i2, boolean z) {
            this.downstream = dbxVar;
            this.combiner = aunVar;
            C4010b<T>[] bVarArr = new C4010b[i];
            for (int i3 = 0; i3 < i; i3++) {
                bVarArr[i3] = new C4010b<>(this, i3, i2);
            }
            this.subscribers = bVarArr;
            this.latest = new Object[i];
            this.queue = new SpscLinkedArrayQueue<>(i2);
            this.requested = new AtomicLong();
            this.error = new AtomicReference<>();
            this.delayErrors = z;
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
            this.cancelled = true;
            cancelAll();
        }

        void subscribe(Publisher<? extends T>[] dbwVarArr, int i) {
            C4010b<T>[] bVarArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.done && !this.cancelled; i2++) {
                dbwVarArr[i2].subscribe(bVarArr[i2]);
            }
        }

        void innerValue(int i, T t) {
            boolean z;
            synchronized (this) {
                Object[] objArr = this.latest;
                int i2 = this.nonEmptySources;
                if (objArr[i] == null) {
                    i2++;
                    this.nonEmptySources = i2;
                }
                objArr[i] = t;
                if (objArr.length == i2) {
                    this.queue.offer(this.subscribers[i], objArr.clone());
                    z = false;
                } else {
                    z = true;
                }
            }
            if (z) {
                this.subscribers[i].requestOne();
            } else {
                drain();
            }
        }

        void innerComplete(int i) {
            synchronized (this) {
                Object[] objArr = this.latest;
                if (objArr[i] != null) {
                    int i2 = this.completedSources + 1;
                    if (i2 == objArr.length) {
                        this.done = true;
                    } else {
                        this.completedSources = i2;
                        return;
                    }
                } else {
                    this.done = true;
                }
                drain();
            }
        }

        void innerError(int i, Throwable th) {
            if (!ExceptionHelper.m9430a(this.error, th)) {
                RxJavaPlugins.m9212a(th);
            } else if (!this.delayErrors) {
                cancelAll();
                this.done = true;
                drain();
            } else {
                innerComplete(i);
            }
        }

        void drainOutput() {
            Subscriber<? super R> dbxVar = this.downstream;
            SpscLinkedArrayQueue<Object> bqlVar = this.queue;
            int i = 1;
            while (!this.cancelled) {
                Throwable th = this.error.get();
                if (th != null) {
                    bqlVar.clear();
                    dbxVar.onError(th);
                    return;
                }
                boolean z = this.done;
                boolean isEmpty = bqlVar.isEmpty();
                if (!isEmpty) {
                    dbxVar.onNext(null);
                }
                if (!z || !isEmpty) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    dbxVar.onComplete();
                    return;
                }
            }
            bqlVar.clear();
        }

        void drainAsync() {
            int i;
            Subscriber<? super R> dbxVar = this.downstream;
            SpscLinkedArrayQueue<?> bqlVar = this.queue;
            int i2 = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    }
                    boolean z = this.done;
                    Object poll = bqlVar.poll();
                    boolean z2 = poll == null;
                    if (!checkTerminated(z, z2, dbxVar, bqlVar)) {
                        if (z2) {
                            break;
                        }
                        try {
                            dbxVar.onNext((Object) ObjectHelper.m9873a(this.combiner.apply((Object[]) bqlVar.poll()), "The combiner returned a null value"));
                            ((C4010b) poll).requestOne();
                            j2++;
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            cancelAll();
                            ExceptionHelper.m9430a(this.error, th);
                            dbxVar.onError(ExceptionHelper.m9431a(this.error));
                            return;
                        }
                    } else {
                        return;
                    }
                }
                if (i != 0 || !checkTerminated(this.done, bqlVar.isEmpty(), dbxVar, bqlVar)) {
                    if (!(j2 == 0 || j == cjm.f20759b)) {
                        this.requested.addAndGet(-j2);
                    }
                    i2 = addAndGet(-i2);
                } else {
                    return;
                }
            } while (i2 != 0);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainOutput();
                } else {
                    drainAsync();
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> dbxVar, SpscLinkedArrayQueue<?> bqlVar) {
            if (this.cancelled) {
                cancelAll();
                bqlVar.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!this.delayErrors) {
                    Throwable a = ExceptionHelper.m9431a(this.error);
                    if (a != null && a != ExceptionHelper.f20064a) {
                        cancelAll();
                        bqlVar.clear();
                        dbxVar.onError(a);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        cancelAll();
                        dbxVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    cancelAll();
                    Throwable a2 = ExceptionHelper.m9431a(this.error);
                    if (a2 == null || a2 == ExceptionHelper.f20064a) {
                        dbxVar.onComplete();
                    } else {
                        dbxVar.onError(a2);
                    }
                    return true;
                }
            }
        }

        void cancelAll() {
            for (C4010b<T> bVar : this.subscribers) {
                bVar.cancel();
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            boolean z = false;
            if ((i & 4) != 0) {
                return 0;
            }
            int i2 = i & 2;
            if (i2 != 0) {
                z = true;
            }
            this.outputFused = z;
            return i2;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public R poll() throws Exception {
            Object poll = this.queue.poll();
            if (poll == null) {
                return null;
            }
            R r = (R) ObjectHelper.m9873a(this.combiner.apply((Object[]) this.queue.poll()), "The combiner returned a null value");
            ((C4010b) poll).requestOne();
            return r;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableCombineLatest.java */
    /* renamed from: z1.azk$b */
    /* loaded from: classes3.dex */
    public static final class C4010b<T> extends AtomicReference<dby> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -8730235182291002949L;
        final int index;
        final int limit;
        final C4009a<T, ?> parent;
        final int prefetch;
        int produced;

        C4010b(C4009a<T, ?> aVar, int i, int i2) {
            this.parent = aVar;
            this.index = i;
            this.prefetch = i2;
            this.limit = i2 - (i2 >> 2);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, this.prefetch);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.parent.innerValue(this.index, t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        public void requestOne() {
            int i = this.produced + 1;
            if (i == this.limit) {
                this.produced = 0;
                get().request(i);
                return;
            }
            this.produced = i;
        }
    }

    /* compiled from: FlowableCombineLatest.java */
    /* renamed from: z1.azk$c */
    /* loaded from: classes3.dex */
    final class C4011c implements Function<T, R> {
        C4011c() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.lang.Object] */
        @Override // p110z1.Function
        public R apply(T t) throws Exception {
            return (R) FlowableCombineLatest.this.f17947d.apply(new Object[]{t});
        }
    }
}
