package p110z1;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: z1.bgo */
/* loaded from: classes3.dex */
public final class MaybeMergeArray<T> extends Flowable<T> {

    /* renamed from: b */
    final MaybeSource<? extends T>[] f18671b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeMergeArray.java */
    /* renamed from: z1.bgo$d */
    /* loaded from: classes3.dex */
    public interface AbstractC4313d<T> extends SimpleQueue<T> {
        int consumerIndex();

        void drop();

        T peek();

        @Override // java.util.Queue, p110z1.MaybeMergeArray.AbstractC4313d, p110z1.SimpleQueue
        @atn
        T poll();

        int producerIndex();
    }

    public MaybeMergeArray(MaybeSource<? extends T>[] asiVarArr) {
        this.f18671b = asiVarArr;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        AbstractC4313d dVar;
        MaybeSource[] asiVarArr = this.f18671b;
        int length = asiVarArr.length;
        if (length <= m11274a()) {
            dVar = new C4312c(length);
        } else {
            dVar = new C4310a();
        }
        C4311b bVar = new C4311b(dbxVar, length, dVar);
        dbxVar.onSubscribe(bVar);
        AtomicThrowable bsnVar = bVar.error;
        for (MaybeSource asiVar : asiVarArr) {
            if (!bVar.isCancelled() && bsnVar.get() == null) {
                asiVar.mo10646a(bVar);
            } else {
                return;
            }
        }
    }

    /* compiled from: MaybeMergeArray.java */
    /* renamed from: z1.bgo$b */
    /* loaded from: classes3.dex */
    static final class C4311b<T> extends BasicIntQueueSubscription<T> implements MaybeObserver<T> {
        private static final long serialVersionUID = -660395290758764731L;
        volatile boolean cancelled;
        long consumed;
        final Subscriber<? super T> downstream;
        boolean outputFused;
        final AbstractC4313d<Object> queue;
        final int sourceCount;
        final CompositeDisposable set = new CompositeDisposable();
        final AtomicLong requested = new AtomicLong();
        final AtomicThrowable error = new AtomicThrowable();

        C4311b(Subscriber<? super T> dbxVar, int i, AbstractC4313d<Object> dVar) {
            this.downstream = dbxVar;
            this.sourceCount = i;
            this.queue = dVar;
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
            T t;
            do {
                t = (T) this.queue.poll();
            } while (t == NotificationLite.COMPLETE);
            return t;
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.queue.clear();
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
                this.set.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.set.mo9939a(atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                this.set.dispose();
                this.queue.offer(NotificationLite.COMPLETE);
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.queue.offer(NotificationLite.COMPLETE);
            drain();
        }

        boolean isCancelled() {
            return this.cancelled;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
            if (r7 != 0) goto L_0x007f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
            if (r10.error.get() == null) goto L_0x0067;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x005a, code lost:
            r1.clear();
            r0.onError(r10.error.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0066, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x006d, code lost:
            if (r1.peek() != p110z1.NotificationLite.COMPLETE) goto L_0x0073;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x006f, code lost:
            r1.drop();
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0079, code lost:
            if (r1.consumerIndex() != r10.sourceCount) goto L_0x007f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x007b, code lost:
            r0.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x007e, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x007f, code lost:
            r10.consumed = r2;
            r4 = addAndGet(-r4);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drainNormal() {
            /*
                r10 = this;
                z1.dbx<? super T> r0 = r10.downstream
                z1.bgo$d<java.lang.Object> r1 = r10.queue
                long r2 = r10.consumed
                r4 = 1
            L_0x0007:
                java.util.concurrent.atomic.AtomicLong r5 = r10.requested
                long r5 = r5.get()
            L_0x000d:
                int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
                if (r7 == 0) goto L_0x004e
                boolean r8 = r10.cancelled
                if (r8 == 0) goto L_0x0019
                r1.clear()
                return
            L_0x0019:
                z1.bsn r8 = r10.error
                java.lang.Object r8 = r8.get()
                java.lang.Throwable r8 = (java.lang.Throwable) r8
                if (r8 == 0) goto L_0x0030
                r1.clear()
                z1.bsn r1 = r10.error
                java.lang.Throwable r1 = r1.terminate()
                r0.onError(r1)
                return
            L_0x0030:
                int r8 = r1.consumerIndex()
                int r9 = r10.sourceCount
                if (r8 != r9) goto L_0x003c
                r0.onComplete()
                return
            L_0x003c:
                java.lang.Object r8 = r1.poll()
                if (r8 != 0) goto L_0x0043
                goto L_0x004e
            L_0x0043:
                z1.btb r7 = p110z1.NotificationLite.COMPLETE
                if (r8 == r7) goto L_0x000d
                r0.onNext(r8)
                r7 = 1
                long r2 = r2 + r7
                goto L_0x000d
            L_0x004e:
                if (r7 != 0) goto L_0x007f
                z1.bsn r5 = r10.error
                java.lang.Object r5 = r5.get()
                java.lang.Throwable r5 = (java.lang.Throwable) r5
                if (r5 == 0) goto L_0x0067
                r1.clear()
                z1.bsn r1 = r10.error
                java.lang.Throwable r1 = r1.terminate()
                r0.onError(r1)
                return
            L_0x0067:
                java.lang.Object r5 = r1.peek()
                z1.btb r6 = p110z1.NotificationLite.COMPLETE
                if (r5 != r6) goto L_0x0073
                r1.drop()
                goto L_0x0067
            L_0x0073:
                int r5 = r1.consumerIndex()
                int r6 = r10.sourceCount
                if (r5 != r6) goto L_0x007f
                r0.onComplete()
                return
            L_0x007f:
                r10.consumed = r2
                int r4 = -r4
                int r4 = r10.addAndGet(r4)
                if (r4 != 0) goto L_0x0007
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.MaybeMergeArray.C4311b.drainNormal():void");
        }

        void drainFused() {
            Subscriber<? super T> dbxVar = this.downstream;
            AbstractC4313d<Object> dVar = this.queue;
            int i = 1;
            while (!this.cancelled) {
                Throwable th = this.error.get();
                if (th != null) {
                    dVar.clear();
                    dbxVar.onError(th);
                    return;
                }
                boolean z = dVar.producerIndex() == this.sourceCount;
                if (!dVar.isEmpty()) {
                    dbxVar.onNext(null);
                }
                if (z) {
                    dbxVar.onComplete();
                    return;
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
            dVar.clear();
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
    }

    /* compiled from: MaybeMergeArray.java */
    /* renamed from: z1.bgo$c */
    /* loaded from: classes3.dex */
    static final class C4312c<T> extends AtomicReferenceArray<T> implements AbstractC4313d<T> {
        private static final long serialVersionUID = -7969063454040569579L;
        int consumerIndex;
        final AtomicInteger producerIndex = new AtomicInteger();

        C4312c(int i) {
            super(i);
        }

        @Override // p110z1.SimpleQueue
        public boolean offer(T t) {
            ObjectHelper.m9873a((Object) t, "value is null");
            int andIncrement = this.producerIndex.getAndIncrement();
            if (andIncrement >= length()) {
                return false;
            }
            lazySet(andIncrement, t);
            return true;
        }

        @Override // p110z1.SimpleQueue
        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException();
        }

        @Override // p110z1.MaybeMergeArray.AbstractC4313d, java.util.Queue, p110z1.SimpleQueue
        @atn
        public T poll() {
            int i = this.consumerIndex;
            if (i == length()) {
                return null;
            }
            AtomicInteger atomicInteger = this.producerIndex;
            do {
                T t = get(i);
                if (t != null) {
                    this.consumerIndex = i + 1;
                    lazySet(i, null);
                    return t;
                }
            } while (atomicInteger.get() != i);
            return null;
        }

        @Override // p110z1.MaybeMergeArray.AbstractC4313d
        public T peek() {
            int i = this.consumerIndex;
            if (i == length()) {
                return null;
            }
            return get(i);
        }

        @Override // p110z1.MaybeMergeArray.AbstractC4313d
        public void drop() {
            int i = this.consumerIndex;
            lazySet(i, null);
            this.consumerIndex = i + 1;
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.consumerIndex == producerIndex();
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            while (poll() != null && !isEmpty()) {
            }
        }

        @Override // p110z1.MaybeMergeArray.AbstractC4313d
        public int consumerIndex() {
            return this.consumerIndex;
        }

        @Override // p110z1.MaybeMergeArray.AbstractC4313d
        public int producerIndex() {
            return this.producerIndex.get();
        }
    }

    /* compiled from: MaybeMergeArray.java */
    /* renamed from: z1.bgo$a */
    /* loaded from: classes3.dex */
    static final class C4310a<T> extends ConcurrentLinkedQueue<T> implements AbstractC4313d<T> {
        private static final long serialVersionUID = -4025173261791142821L;
        int consumerIndex;
        final AtomicInteger producerIndex = new AtomicInteger();

        C4310a() {
        }

        @Override // p110z1.SimpleQueue
        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.ConcurrentLinkedQueue, java.util.Queue, p110z1.SimpleQueue
        public boolean offer(T t) {
            this.producerIndex.getAndIncrement();
            return super.offer(t);
        }

        @Override // java.util.concurrent.ConcurrentLinkedQueue, java.util.Queue, p110z1.MaybeMergeArray.AbstractC4313d, p110z1.SimpleQueue
        @atn
        public T poll() {
            T t = (T) super.poll();
            if (t != null) {
                this.consumerIndex++;
            }
            return t;
        }

        @Override // p110z1.MaybeMergeArray.AbstractC4313d
        public int consumerIndex() {
            return this.consumerIndex;
        }

        @Override // p110z1.MaybeMergeArray.AbstractC4313d
        public int producerIndex() {
            return this.producerIndex.get();
        }

        @Override // p110z1.MaybeMergeArray.AbstractC4313d
        public void drop() {
            poll();
        }
    }
}
