package p110z1;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bbd */
/* loaded from: classes3.dex */
public final class FlowableGroupBy<T, K, V> extends AbstractFlowableWithUpstream<T, GroupedFlowable<K, V>> {

    /* renamed from: c */
    final Function<? super T, ? extends K> f18120c;

    /* renamed from: d */
    final Function<? super T, ? extends V> f18121d;

    /* renamed from: e */
    final int f18122e;

    /* renamed from: f */
    final boolean f18123f;

    /* renamed from: g */
    final Function<? super Consumer<Object>, ? extends Map<K, Object>> f18124g;

    public FlowableGroupBy(Flowable<T> arvVar, Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, int i, boolean z, Function<? super Consumer<Object>, ? extends Map<K, Object>> aunVar3) {
        super(arvVar);
        this.f18120c = aunVar;
        this.f18121d = aunVar2;
        this.f18122e = i;
        this.f18123f = z;
        this.f18124g = aunVar3;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super GroupedFlowable<K, V>> dbxVar) {
        ConcurrentLinkedQueue concurrentLinkedQueue;
        Map map;
        try {
            if (this.f18124g == null) {
                concurrentLinkedQueue = null;
                map = new ConcurrentHashMap();
            } else {
                ConcurrentLinkedQueue concurrentLinkedQueue2 = new ConcurrentLinkedQueue();
                map = (Map) this.f18124g.apply(new C4084a(concurrentLinkedQueue2));
                concurrentLinkedQueue = concurrentLinkedQueue2;
            }
            this.f17812b.m11187a((FlowableSubscriber) new C4085b(dbxVar, this.f18120c, this.f18121d, this.f18122e, this.f18123f, map, concurrentLinkedQueue));
        } catch (Exception e) {
            Exceptions.m9983b(e);
            dbxVar.onSubscribe(EmptyComponent.INSTANCE);
            dbxVar.onError(e);
        }
    }

    /* compiled from: FlowableGroupBy.java */
    /* renamed from: z1.bbd$b */
    /* loaded from: classes3.dex */
    public static final class C4085b<T, K, V> extends BasicIntQueueSubscription<GroupedFlowable<K, V>> implements FlowableSubscriber<T> {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final int bufferSize;
        final boolean delayError;
        boolean done;
        final Subscriber<? super GroupedFlowable<K, V>> downstream;
        Throwable error;
        final Queue<C4086c<K, V>> evictedGroups;
        volatile boolean finished;
        final Map<Object, C4086c<K, V>> groups;
        final Function<? super T, ? extends K> keySelector;
        boolean outputFused;
        final SpscLinkedArrayQueue<GroupedFlowable<K, V>> queue;
        dby upstream;
        final Function<? super T, ? extends V> valueSelector;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger groupCount = new AtomicInteger(1);

        public C4085b(Subscriber<? super GroupedFlowable<K, V>> dbxVar, Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, int i, boolean z, Map<Object, C4086c<K, V>> map, Queue<C4086c<K, V>> queue) {
            this.downstream = dbxVar;
            this.keySelector = aunVar;
            this.valueSelector = aunVar2;
            this.bufferSize = i;
            this.delayError = z;
            this.groups = map;
            this.evictedGroups = queue;
            this.queue = new SpscLinkedArrayQueue<>(i);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(this.bufferSize);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                SpscLinkedArrayQueue<GroupedFlowable<K, V>> bqlVar = this.queue;
                try {
                    Object apply = this.keySelector.apply(t);
                    boolean z = false;
                    Object obj = apply != null ? apply : NULL_KEY;
                    C4086c<K, V> cVar = this.groups.get(obj);
                    if (cVar == null) {
                        if (!this.cancelled.get()) {
                            cVar = C4086c.m9788a(apply, this.bufferSize, (C4085b<?, Object, T>) this, this.delayError);
                            this.groups.put(obj, cVar);
                            this.groupCount.getAndIncrement();
                            z = true;
                        } else {
                            return;
                        }
                    }
                    try {
                        cVar.m9786m((C4086c) ObjectHelper.m9873a(this.valueSelector.apply(t), "The valueSelector returned null"));
                        completeEvictions();
                        if (z) {
                            bqlVar.offer(cVar);
                            drain();
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.upstream.cancel();
                        onError(th);
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    this.upstream.cancel();
                    onError(th2);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            for (C4086c<K, V> cVar : this.groups.values()) {
                cVar.m9787b(th);
            }
            this.groups.clear();
            Queue<C4086c<K, V>> queue = this.evictedGroups;
            if (queue != null) {
                queue.clear();
            }
            this.error = th;
            this.finished = true;
            drain();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                for (C4086c<K, V> cVar : this.groups.values()) {
                    cVar.m9789U();
                }
                this.groups.clear();
                Queue<C4086c<K, V>> queue = this.evictedGroups;
                if (queue != null) {
                    queue.clear();
                }
                this.done = true;
                this.finished = true;
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
            if (this.cancelled.compareAndSet(false, true)) {
                completeEvictions();
                if (this.groupCount.decrementAndGet() == 0) {
                    this.upstream.cancel();
                }
            }
        }

        private void completeEvictions() {
            if (this.evictedGroups != null) {
                int i = 0;
                while (true) {
                    C4086c<K, V> poll = this.evictedGroups.poll();
                    if (poll == null) {
                        break;
                    }
                    poll.m9789U();
                    i++;
                }
                if (i != 0) {
                    this.groupCount.addAndGet(-i);
                }
            }
        }

        public void cancel(K k) {
            if (k == null) {
                k = (K) NULL_KEY;
            }
            this.groups.remove(k);
            if (this.groupCount.decrementAndGet() == 0) {
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainFused();
                } else {
                    drainNormal();
                }
            }
        }

        void drainFused() {
            Throwable th;
            SpscLinkedArrayQueue<GroupedFlowable<K, V>> bqlVar = this.queue;
            Subscriber<? super GroupedFlowable<K, V>> dbxVar = this.downstream;
            int i = 1;
            while (!this.cancelled.get()) {
                boolean z = this.finished;
                if (!z || this.delayError || (th = this.error) == null) {
                    dbxVar.onNext(null);
                    if (z) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            dbxVar.onError(th2);
                            return;
                        } else {
                            dbxVar.onComplete();
                            return;
                        }
                    } else {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                } else {
                    bqlVar.clear();
                    dbxVar.onError(th);
                    return;
                }
            }
            bqlVar.clear();
        }

        void drainNormal() {
            int i;
            SpscLinkedArrayQueue<GroupedFlowable<K, V>> bqlVar = this.queue;
            Subscriber<? super GroupedFlowable<K, V>> dbxVar = this.downstream;
            int i2 = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    }
                    boolean z = this.finished;
                    GroupedFlowable<K, V> poll = bqlVar.poll();
                    boolean z2 = poll == null;
                    if (!checkTerminated(z, z2, dbxVar, bqlVar)) {
                        if (z2) {
                            break;
                        }
                        dbxVar.onNext(poll);
                        j2++;
                    } else {
                        return;
                    }
                }
                if (i != 0 || !checkTerminated(this.finished, bqlVar.isEmpty(), dbxVar, bqlVar)) {
                    if (j2 != 0) {
                        if (j != cjm.f20759b) {
                            this.requested.addAndGet(-j2);
                        }
                        this.upstream.request(j2);
                    }
                    i2 = addAndGet(-i2);
                } else {
                    return;
                }
            } while (i2 != 0);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> dbxVar, SpscLinkedArrayQueue<?> bqlVar) {
            if (this.cancelled.get()) {
                bqlVar.clear();
                return true;
            } else if (this.delayError) {
                if (!z || !z2) {
                    return false;
                }
                Throwable th = this.error;
                if (th != null) {
                    dbxVar.onError(th);
                } else {
                    dbxVar.onComplete();
                }
                return true;
            } else if (!z) {
                return false;
            } else {
                Throwable th2 = this.error;
                if (th2 != null) {
                    bqlVar.clear();
                    dbxVar.onError(th2);
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    dbxVar.onComplete();
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
        public GroupedFlowable<K, V> poll() {
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

    /* compiled from: FlowableGroupBy.java */
    /* renamed from: z1.bbd$a */
    /* loaded from: classes3.dex */
    static final class C4084a<K, V> implements Consumer<C4086c<K, V>> {

        /* renamed from: a */
        final Queue<C4086c<K, V>> f18125a;

        C4084a(Queue<C4086c<K, V>> queue) {
            this.f18125a = queue;
        }

        /* renamed from: a */
        public void accept(C4086c<K, V> cVar) {
            this.f18125a.offer(cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableGroupBy.java */
    /* renamed from: z1.bbd$c */
    /* loaded from: classes3.dex */
    public static final class C4086c<K, T> extends GroupedFlowable<K, T> {

        /* renamed from: c */
        final C4087d<T, K> f18126c;

        /* renamed from: a */
        public static <T, K> C4086c<K, T> m9788a(K k, int i, C4085b<?, K, T> bVar, boolean z) {
            return new C4086c<>(k, new C4087d(i, bVar, k, z));
        }

        protected C4086c(K k, C4087d<T, K> dVar) {
            super(k);
            this.f18126c = dVar;
        }

        @Override // p110z1.Flowable
        /* renamed from: d */
        protected void mo9054d(Subscriber<? super T> dbxVar) {
            this.f18126c.subscribe(dbxVar);
        }

        /* renamed from: m */
        public void m9786m(T t) {
            this.f18126c.onNext(t);
        }

        /* renamed from: b */
        public void m9787b(Throwable th) {
            this.f18126c.onError(th);
        }

        /* renamed from: U */
        public void m9789U() {
            this.f18126c.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableGroupBy.java */
    /* renamed from: z1.bbd$d */
    /* loaded from: classes3.dex */
    public static final class C4087d<T, K> extends BasicIntQueueSubscription<T> implements Publisher<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        boolean outputFused;
        final C4085b<?, K, T> parent;
        int produced;
        final SpscLinkedArrayQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        final AtomicBoolean cancelled = new AtomicBoolean();
        final AtomicReference<Subscriber<? super T>> actual = new AtomicReference<>();
        final AtomicBoolean once = new AtomicBoolean();

        C4087d(int i, C4085b<?, K, T> bVar, K k, boolean z) {
            this.queue = new SpscLinkedArrayQueue<>(i);
            this.parent = bVar;
            this.key = k;
            this.delayError = z;
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
            if (this.cancelled.compareAndSet(false, true)) {
                this.parent.cancel(this.key);
            }
        }

        @Override // p110z1.Publisher
        public void subscribe(Subscriber<? super T> dbxVar) {
            if (this.once.compareAndSet(false, true)) {
                dbxVar.onSubscribe(this);
                this.actual.lazySet(dbxVar);
                drain();
                return;
            }
            EmptySubscription.error(new IllegalStateException("Only one Subscriber allowed!"), dbxVar);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainFused();
                } else {
                    drainNormal();
                }
            }
        }

        void drainFused() {
            Throwable th;
            SpscLinkedArrayQueue<T> bqlVar = this.queue;
            Subscriber<? super T> dbxVar = this.actual.get();
            int i = 1;
            while (true) {
                if (dbxVar != null) {
                    if (this.cancelled.get()) {
                        bqlVar.clear();
                        return;
                    }
                    boolean z = this.done;
                    if (!z || this.delayError || (th = this.error) == null) {
                        dbxVar.onNext(null);
                        if (z) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                dbxVar.onError(th2);
                                return;
                            } else {
                                dbxVar.onComplete();
                                return;
                            }
                        }
                    } else {
                        bqlVar.clear();
                        dbxVar.onError(th);
                        return;
                    }
                }
                i = addAndGet(-i);
                if (i != 0) {
                    if (dbxVar == null) {
                        dbxVar = this.actual.get();
                    }
                } else {
                    return;
                }
            }
        }

        void drainNormal() {
            int i;
            SpscLinkedArrayQueue<T> bqlVar = this.queue;
            boolean z = this.delayError;
            Subscriber<? super T> dbxVar = this.actual.get();
            int i2 = 1;
            while (true) {
                if (dbxVar != null) {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (true) {
                        i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                        if (i == 0) {
                            break;
                        }
                        boolean z2 = this.done;
                        Object obj = (T) bqlVar.poll();
                        boolean z3 = obj == null;
                        if (!checkTerminated(z2, z3, dbxVar, z)) {
                            if (z3) {
                                break;
                            }
                            dbxVar.onNext(obj);
                            j2++;
                        } else {
                            return;
                        }
                    }
                    if (i == 0 && checkTerminated(this.done, bqlVar.isEmpty(), dbxVar, z)) {
                        return;
                    }
                    if (j2 != 0) {
                        if (j != cjm.f20759b) {
                            this.requested.addAndGet(-j2);
                        }
                        this.parent.upstream.request(j2);
                    }
                }
                i2 = addAndGet(-i2);
                if (i2 != 0) {
                    if (dbxVar == null) {
                        dbxVar = this.actual.get();
                    }
                } else {
                    return;
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> dbxVar, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!z3) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        dbxVar.onError(th);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        dbxVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        dbxVar.onError(th2);
                    } else {
                        dbxVar.onComplete();
                    }
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
        public T poll() {
            T poll = this.queue.poll();
            if (poll != null) {
                this.produced++;
                return poll;
            }
            int i = this.produced;
            if (i == 0) {
                return null;
            }
            this.produced = 0;
            this.parent.upstream.request(i);
            return null;
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.queue.clear();
        }
    }
}
