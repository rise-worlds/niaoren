package p110z1;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bei */
/* loaded from: classes3.dex */
public final class FlowableWindow<T> extends AbstractFlowableWithUpstream<T, Flowable<T>> {

    /* renamed from: c */
    final long f18437c;

    /* renamed from: d */
    final long f18438d;

    /* renamed from: e */
    final int f18439e;

    public FlowableWindow(Flowable<T> arvVar, long j, long j2, int i) {
        super(arvVar);
        this.f18437c = j;
        this.f18438d = j2;
        this.f18439e = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super Flowable<T>> dbxVar) {
        long j = this.f18438d;
        long j2 = this.f18437c;
        if (j == j2) {
            this.f17812b.m11187a((FlowableSubscriber) new RunnableC4236a(dbxVar, this.f18437c, this.f18439e));
        } else if (j > j2) {
            this.f17812b.m11187a((FlowableSubscriber) new RunnableC4238c(dbxVar, this.f18437c, this.f18438d, this.f18439e));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new RunnableC4237b(dbxVar, this.f18437c, this.f18438d, this.f18439e));
        }
    }

    /* compiled from: FlowableWindow.java */
    /* renamed from: z1.bei$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4236a<T> extends AtomicInteger implements Runnable, FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -2365647875069161133L;
        final int bufferSize;
        final Subscriber<? super Flowable<T>> downstream;
        long index;
        final AtomicBoolean once = new AtomicBoolean();
        final long size;
        dby upstream;
        UnicastProcessor<T> window;

        RunnableC4236a(Subscriber<? super Flowable<T>> dbxVar, long j, int i) {
            super(1);
            this.downstream = dbxVar;
            this.size = j;
            this.bufferSize = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            long j = this.index;
            UnicastProcessor<T> bumVar = this.window;
            if (j == 0) {
                getAndIncrement();
                bumVar = UnicastProcessor.m9058a(this.bufferSize, (Runnable) this);
                this.window = bumVar;
                this.downstream.onNext(bumVar);
            }
            long j2 = j + 1;
            bumVar.onNext(t);
            if (j2 == this.size) {
                this.index = 0L;
                this.window = null;
                bumVar.onComplete();
                return;
            }
            this.index = j2;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            UnicastProcessor<T> bumVar = this.window;
            if (bumVar != null) {
                this.window = null;
                bumVar.onError(th);
            }
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            UnicastProcessor<T> bumVar = this.window;
            if (bumVar != null) {
                this.window = null;
                bumVar.onComplete();
            }
            this.downstream.onComplete();
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.upstream.request(BackpressureHelper.m9448b(this.size, j));
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }
    }

    /* compiled from: FlowableWindow.java */
    /* renamed from: z1.bei$c */
    /* loaded from: classes3.dex */
    static final class RunnableC4238c<T> extends AtomicInteger implements Runnable, FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -8792836352386833856L;
        final int bufferSize;
        final Subscriber<? super Flowable<T>> downstream;
        long index;
        final long size;
        final long skip;
        dby upstream;
        UnicastProcessor<T> window;
        final AtomicBoolean once = new AtomicBoolean();
        final AtomicBoolean firstRequest = new AtomicBoolean();

        RunnableC4238c(Subscriber<? super Flowable<T>> dbxVar, long j, long j2, int i) {
            super(1);
            this.downstream = dbxVar;
            this.size = j;
            this.skip = j2;
            this.bufferSize = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            long j = this.index;
            UnicastProcessor<T> bumVar = this.window;
            if (j == 0) {
                getAndIncrement();
                bumVar = UnicastProcessor.m9058a(this.bufferSize, (Runnable) this);
                this.window = bumVar;
                this.downstream.onNext(bumVar);
            }
            long j2 = j + 1;
            if (bumVar != null) {
                bumVar.onNext(t);
            }
            if (j2 == this.size) {
                this.window = null;
                bumVar.onComplete();
            }
            if (j2 == this.skip) {
                this.index = 0L;
            } else {
                this.index = j2;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            UnicastProcessor<T> bumVar = this.window;
            if (bumVar != null) {
                this.window = null;
                bumVar.onError(th);
            }
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            UnicastProcessor<T> bumVar = this.window;
            if (bumVar != null) {
                this.window = null;
                bumVar.onComplete();
            }
            this.downstream.onComplete();
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (!SubscriptionHelper.validate(j)) {
                return;
            }
            if (this.firstRequest.get() || !this.firstRequest.compareAndSet(false, true)) {
                this.upstream.request(BackpressureHelper.m9448b(this.skip, j));
                return;
            }
            this.upstream.request(BackpressureHelper.m9450a(BackpressureHelper.m9448b(this.size, j), BackpressureHelper.m9448b(this.skip - this.size, j - 1)));
        }

        @Override // p110z1.dby
        public void cancel() {
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }
    }

    /* compiled from: FlowableWindow.java */
    /* renamed from: z1.bei$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4237b<T> extends AtomicInteger implements Runnable, FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 2428527070996323976L;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        final Subscriber<? super Flowable<T>> downstream;
        Throwable error;
        long index;
        long produced;
        final SpscLinkedArrayQueue<UnicastProcessor<T>> queue;
        final long size;
        final long skip;
        dby upstream;
        final ArrayDeque<UnicastProcessor<T>> windows = new ArrayDeque<>();
        final AtomicBoolean once = new AtomicBoolean();
        final AtomicBoolean firstRequest = new AtomicBoolean();
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger wip = new AtomicInteger();

        RunnableC4237b(Subscriber<? super Flowable<T>> dbxVar, long j, long j2, int i) {
            super(1);
            this.downstream = dbxVar;
            this.size = j;
            this.skip = j2;
            this.queue = new SpscLinkedArrayQueue<>(i);
            this.bufferSize = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                long j = this.index;
                if (j == 0 && !this.cancelled) {
                    getAndIncrement();
                    UnicastProcessor<T> a = UnicastProcessor.m9058a(this.bufferSize, (Runnable) this);
                    this.windows.offer(a);
                    this.queue.offer(a);
                    drain();
                }
                long j2 = j + 1;
                Iterator<UnicastProcessor<T>> it = this.windows.iterator();
                while (it.hasNext()) {
                    it.next().onNext(t);
                }
                long j3 = this.produced + 1;
                if (j3 == this.size) {
                    this.produced = j3 - this.skip;
                    UnicastProcessor<T> poll = this.windows.poll();
                    if (poll != null) {
                        poll.onComplete();
                    }
                } else {
                    this.produced = j3;
                }
                if (j2 == this.skip) {
                    this.index = 0L;
                } else {
                    this.index = j2;
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            Iterator<UnicastProcessor<T>> it = this.windows.iterator();
            while (it.hasNext()) {
                it.next().onError(th);
            }
            this.windows.clear();
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                Iterator<UnicastProcessor<T>> it = this.windows.iterator();
                while (it.hasNext()) {
                    it.next().onComplete();
                }
                this.windows.clear();
                this.done = true;
                drain();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x003a, code lost:
            if (r10 != 0) goto L_0x0049;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
            if (checkTerminated(r14.done, r1.isEmpty(), r0, r1) == false) goto L_0x0049;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x004b, code lost:
            if (r8 == 0) goto L_0x005c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0054, code lost:
            if (r4 == p110z1.cjm.f20759b) goto L_0x005c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0056, code lost:
            r14.requested.addAndGet(-r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x005c, code lost:
            r3 = r14.wip.addAndGet(-r3);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                r14 = this;
                java.util.concurrent.atomic.AtomicInteger r0 = r14.wip
                int r0 = r0.getAndIncrement()
                if (r0 == 0) goto L_0x0009
                return
            L_0x0009:
                z1.dbx<? super z1.arv<T>> r0 = r14.downstream
                z1.bql<z1.bum<T>> r1 = r14.queue
                r2 = 1
                r3 = 1
            L_0x000f:
                java.util.concurrent.atomic.AtomicLong r4 = r14.requested
                long r4 = r4.get()
                r6 = 0
                r8 = r6
            L_0x0018:
                int r10 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
                if (r10 == 0) goto L_0x003a
                boolean r11 = r14.done
                java.lang.Object r12 = r1.poll()
                z1.bum r12 = (p110z1.UnicastProcessor) r12
                if (r12 != 0) goto L_0x0028
                r13 = 1
                goto L_0x0029
            L_0x0028:
                r13 = 0
            L_0x0029:
                boolean r11 = r14.checkTerminated(r11, r13, r0, r1)
                if (r11 == 0) goto L_0x0030
                return
            L_0x0030:
                if (r13 == 0) goto L_0x0033
                goto L_0x003a
            L_0x0033:
                r0.onNext(r12)
                r10 = 1
                long r8 = r8 + r10
                goto L_0x0018
            L_0x003a:
                if (r10 != 0) goto L_0x0049
                boolean r10 = r14.done
                boolean r11 = r1.isEmpty()
                boolean r10 = r14.checkTerminated(r10, r11, r0, r1)
                if (r10 == 0) goto L_0x0049
                return
            L_0x0049:
                int r6 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r6 == 0) goto L_0x005c
                r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r4 == 0) goto L_0x005c
                java.util.concurrent.atomic.AtomicLong r4 = r14.requested
                long r5 = -r8
                r4.addAndGet(r5)
            L_0x005c:
                java.util.concurrent.atomic.AtomicInteger r4 = r14.wip
                int r3 = -r3
                int r3 = r4.addAndGet(r3)
                if (r3 != 0) goto L_0x000f
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableWindow.RunnableC4237b.drain():void");
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> dbxVar, SpscLinkedArrayQueue<?> bqlVar) {
            if (this.cancelled) {
                bqlVar.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                Throwable th = this.error;
                if (th != null) {
                    bqlVar.clear();
                    dbxVar.onError(th);
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    dbxVar.onComplete();
                    return true;
                }
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                if (this.firstRequest.get() || !this.firstRequest.compareAndSet(false, true)) {
                    this.upstream.request(BackpressureHelper.m9448b(this.skip, j));
                } else {
                    this.upstream.request(BackpressureHelper.m9450a(this.size, BackpressureHelper.m9448b(this.skip, j - 1)));
                }
                drain();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.cancelled = true;
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }
    }
}
