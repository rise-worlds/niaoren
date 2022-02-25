package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p110z1.Scheduler;
import p110z1.SchedulerMultiWorkerSupport;

/* renamed from: z1.boh */
/* loaded from: classes3.dex */
public final class ParallelRunOn<T> extends ParallelFlowable<T> {

    /* renamed from: a */
    final ParallelFlowable<? extends T> f19676a;

    /* renamed from: b */
    final Scheduler f19677b;

    /* renamed from: c */
    final int f19678c;

    public ParallelRunOn(ParallelFlowable<? extends T> bubVar, Scheduler astVar, int i) {
        this.f19676a = bubVar;
        this.f19677b = astVar;
        this.f19678c = i;
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public void mo9236a(Subscriber<? super T>[] dbxVarArr) {
        if (m9227b(dbxVarArr)) {
            int length = dbxVarArr.length;
            Subscriber<T>[] dbxVarArr2 = new Subscriber[length];
            Scheduler astVar = this.f19677b;
            if (astVar instanceof SchedulerMultiWorkerSupport) {
                ((SchedulerMultiWorkerSupport) astVar).mo9494a(length, new C4647b(dbxVarArr, dbxVarArr2));
            } else {
                for (int i = 0; i < length; i++) {
                    m9552a(i, dbxVarArr, dbxVarArr2, this.f19677b.mo9034b());
                }
            }
            this.f19676a.mo9236a((Subscriber<? super Object>[]) dbxVarArr2);
        }
    }

    /* renamed from: a */
    void m9552a(int i, Subscriber<? super T>[] dbxVarArr, Subscriber<T>[] dbxVarArr2, Scheduler.AbstractC3881c cVar) {
        Subscriber<? super T> dbxVar = dbxVarArr[i];
        SpscArrayQueue bqkVar = new SpscArrayQueue(this.f19678c);
        if (dbxVar instanceof ConditionalSubscriber) {
            dbxVarArr2[i] = new C4648c((ConditionalSubscriber) dbxVar, this.f19678c, bqkVar, cVar);
        } else {
            dbxVarArr2[i] = new C4649d(dbxVar, this.f19678c, bqkVar, cVar);
        }
    }

    /* compiled from: ParallelRunOn.java */
    /* renamed from: z1.boh$b */
    /* loaded from: classes3.dex */
    final class C4647b implements SchedulerMultiWorkerSupport.AbstractC4726a {

        /* renamed from: a */
        final Subscriber<? super T>[] f19679a;

        /* renamed from: b */
        final Subscriber<T>[] f19680b;

        C4647b(Subscriber<? super T>[] dbxVarArr, Subscriber<T>[] dbxVarArr2) {
            this.f19679a = dbxVarArr;
            this.f19680b = dbxVarArr2;
        }

        @Override // p110z1.SchedulerMultiWorkerSupport.AbstractC4726a
        /* renamed from: a */
        public void mo9493a(int i, Scheduler.AbstractC3881c cVar) {
            ParallelRunOn.this.m9552a(i, this.f19679a, this.f19680b, cVar);
        }
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19676a.mo9267a();
    }

    /* compiled from: ParallelRunOn.java */
    /* renamed from: z1.boh$a */
    /* loaded from: classes3.dex */
    static abstract class AbstractRunnableC4646a<T> extends AtomicInteger implements Runnable, FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 9222303586456402150L;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        final SpscArrayQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        dby upstream;
        final Scheduler.AbstractC3881c worker;

        AbstractRunnableC4646a(int i, SpscArrayQueue<T> bqkVar, Scheduler.AbstractC3881c cVar) {
            this.prefetch = i;
            this.queue = bqkVar;
            this.limit = i - (i >> 2);
            this.worker = cVar;
        }

        @Override // p110z1.Subscriber
        public final void onNext(T t) {
            if (!this.done) {
                if (!this.queue.offer(t)) {
                    this.upstream.cancel();
                    onError(new MissingBackpressureException("Queue is full?!"));
                    return;
                }
                schedule();
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
            schedule();
        }

        @Override // p110z1.Subscriber
        public final void onComplete() {
            if (!this.done) {
                this.done = true;
                schedule();
            }
        }

        @Override // p110z1.dby
        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                schedule();
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

        final void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.mo9031a(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelRunOn.java */
    /* renamed from: z1.boh$d */
    /* loaded from: classes3.dex */
    public static final class C4649d<T> extends AbstractRunnableC4646a<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final Subscriber<? super T> downstream;

        C4649d(Subscriber<? super T> dbxVar, int i, SpscArrayQueue<T> bqkVar, Scheduler.AbstractC3881c cVar) {
            super(i, bqkVar, cVar);
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(this.prefetch);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
            if (r13 != 0) goto L_0x0091;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
            if (r18.cancelled == false) goto L_0x006e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x006a, code lost:
            r2.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x006d, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0070, code lost:
            if (r18.done == false) goto L_0x0091;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0072, code lost:
            r13 = r18.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0074, code lost:
            if (r13 == null) goto L_0x0082;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0076, code lost:
            r2.clear();
            r3.onError(r13);
            r18.worker.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0081, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0086, code lost:
            if (r2.isEmpty() == false) goto L_0x0091;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0088, code lost:
            r3.onComplete();
            r18.worker.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0090, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0093, code lost:
            if (r11 == 0) goto L_0x00a4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x009c, code lost:
            if (r7 == p110z1.cjm.f20759b) goto L_0x00a4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x009e, code lost:
            r18.requested.addAndGet(-r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00a4, code lost:
            r7 = get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00a8, code lost:
            if (r7 != r6) goto L_0x00b4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00aa, code lost:
            r18.consumed = r1;
            r6 = addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00b1, code lost:
            if (r6 != 0) goto L_0x000c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00b3, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00b4, code lost:
            r6 = r7;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r18 = this;
                r0 = r18
                int r1 = r0.consumed
                z1.bqk<T> r2 = r0.queue
                z1.dbx<? super T> r3 = r0.downstream
                int r4 = r0.limit
                r5 = 1
                r6 = 1
            L_0x000c:
                java.util.concurrent.atomic.AtomicLong r7 = r0.requested
                long r7 = r7.get()
                r9 = 0
                r11 = r9
            L_0x0015:
                int r13 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
                if (r13 == 0) goto L_0x0064
                boolean r14 = r0.cancelled
                if (r14 == 0) goto L_0x0021
                r2.clear()
                return
            L_0x0021:
                boolean r14 = r0.done
                if (r14 == 0) goto L_0x0035
                java.lang.Throwable r15 = r0.error
                if (r15 == 0) goto L_0x0035
                r2.clear()
                r3.onError(r15)
                z1.ast$c r1 = r0.worker
                r1.dispose()
                return
            L_0x0035:
                java.lang.Object r15 = r2.poll()
                r16 = 0
                if (r15 != 0) goto L_0x0040
                r17 = 1
                goto L_0x0042
            L_0x0040:
                r17 = 0
            L_0x0042:
                if (r14 == 0) goto L_0x004f
                if (r17 == 0) goto L_0x004f
                r3.onComplete()
                z1.ast$c r1 = r0.worker
                r1.dispose()
                return
            L_0x004f:
                if (r17 == 0) goto L_0x0052
                goto L_0x0064
            L_0x0052:
                r3.onNext(r15)
                r13 = 1
                long r11 = r11 + r13
                int r1 = r1 + 1
                if (r1 != r4) goto L_0x0015
                z1.dby r13 = r0.upstream
                long r14 = (long) r1
                r13.request(r14)
                r1 = 0
                goto L_0x0015
            L_0x0064:
                if (r13 != 0) goto L_0x0091
                boolean r13 = r0.cancelled
                if (r13 == 0) goto L_0x006e
                r2.clear()
                return
            L_0x006e:
                boolean r13 = r0.done
                if (r13 == 0) goto L_0x0091
                java.lang.Throwable r13 = r0.error
                if (r13 == 0) goto L_0x0082
                r2.clear()
                r3.onError(r13)
                z1.ast$c r1 = r0.worker
                r1.dispose()
                return
            L_0x0082:
                boolean r13 = r2.isEmpty()
                if (r13 == 0) goto L_0x0091
                r3.onComplete()
                z1.ast$c r1 = r0.worker
                r1.dispose()
                return
            L_0x0091:
                int r9 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
                if (r9 == 0) goto L_0x00a4
                r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r7 == 0) goto L_0x00a4
                java.util.concurrent.atomic.AtomicLong r7 = r0.requested
                long r8 = -r11
                r7.addAndGet(r8)
            L_0x00a4:
                int r7 = r18.get()
                if (r7 != r6) goto L_0x00b4
                r0.consumed = r1
                int r6 = -r6
                int r6 = r0.addAndGet(r6)
                if (r6 != 0) goto L_0x000c
                return
            L_0x00b4:
                r6 = r7
                goto L_0x000c
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ParallelRunOn.C4649d.run():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelRunOn.java */
    /* renamed from: z1.boh$c */
    /* loaded from: classes3.dex */
    public static final class C4648c<T> extends AbstractRunnableC4646a<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final ConditionalSubscriber<? super T> downstream;

        C4648c(ConditionalSubscriber<? super T> aviVar, int i, SpscArrayQueue<T> bqkVar, Scheduler.AbstractC3881c cVar) {
            super(i, bqkVar, cVar);
            this.downstream = aviVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(this.prefetch);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:31:0x0067, code lost:
            if (r13 != 0) goto L_0x0094;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x006b, code lost:
            if (r18.cancelled == false) goto L_0x0071;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x006d, code lost:
            r2.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0070, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0073, code lost:
            if (r18.done == false) goto L_0x0094;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0075, code lost:
            r13 = r18.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0077, code lost:
            if (r13 == null) goto L_0x0085;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0079, code lost:
            r2.clear();
            r3.onError(r13);
            r18.worker.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0084, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0089, code lost:
            if (r2.isEmpty() == false) goto L_0x0094;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x008b, code lost:
            r3.onComplete();
            r18.worker.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0093, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0096, code lost:
            if (r11 == 0) goto L_0x00a7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x009f, code lost:
            if (r7 == p110z1.cjm.f20759b) goto L_0x00a7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00a1, code lost:
            r18.requested.addAndGet(-r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00a7, code lost:
            r7 = get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00ab, code lost:
            if (r7 != r6) goto L_0x00b7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00ad, code lost:
            r18.consumed = r1;
            r6 = addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00b4, code lost:
            if (r6 != 0) goto L_0x000c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00b6, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00b7, code lost:
            r6 = r7;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r18 = this;
                r0 = r18
                int r1 = r0.consumed
                z1.bqk<T> r2 = r0.queue
                z1.avi<? super T> r3 = r0.downstream
                int r4 = r0.limit
                r5 = 1
                r6 = 1
            L_0x000c:
                java.util.concurrent.atomic.AtomicLong r7 = r0.requested
                long r7 = r7.get()
                r9 = 0
                r11 = r9
            L_0x0015:
                int r13 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
                if (r13 == 0) goto L_0x0067
                boolean r14 = r0.cancelled
                if (r14 == 0) goto L_0x0021
                r2.clear()
                return
            L_0x0021:
                boolean r14 = r0.done
                if (r14 == 0) goto L_0x0035
                java.lang.Throwable r15 = r0.error
                if (r15 == 0) goto L_0x0035
                r2.clear()
                r3.onError(r15)
                z1.ast$c r1 = r0.worker
                r1.dispose()
                return
            L_0x0035:
                java.lang.Object r15 = r2.poll()
                r16 = 0
                if (r15 != 0) goto L_0x0040
                r17 = 1
                goto L_0x0042
            L_0x0040:
                r17 = 0
            L_0x0042:
                if (r14 == 0) goto L_0x004f
                if (r17 == 0) goto L_0x004f
                r3.onComplete()
                z1.ast$c r1 = r0.worker
                r1.dispose()
                return
            L_0x004f:
                if (r17 == 0) goto L_0x0052
                goto L_0x0067
            L_0x0052:
                boolean r13 = r3.tryOnNext(r15)
                if (r13 == 0) goto L_0x005b
                r13 = 1
                long r11 = r11 + r13
            L_0x005b:
                int r1 = r1 + 1
                if (r1 != r4) goto L_0x0015
                z1.dby r13 = r0.upstream
                long r14 = (long) r1
                r13.request(r14)
                r1 = 0
                goto L_0x0015
            L_0x0067:
                if (r13 != 0) goto L_0x0094
                boolean r13 = r0.cancelled
                if (r13 == 0) goto L_0x0071
                r2.clear()
                return
            L_0x0071:
                boolean r13 = r0.done
                if (r13 == 0) goto L_0x0094
                java.lang.Throwable r13 = r0.error
                if (r13 == 0) goto L_0x0085
                r2.clear()
                r3.onError(r13)
                z1.ast$c r1 = r0.worker
                r1.dispose()
                return
            L_0x0085:
                boolean r13 = r2.isEmpty()
                if (r13 == 0) goto L_0x0094
                r3.onComplete()
                z1.ast$c r1 = r0.worker
                r1.dispose()
                return
            L_0x0094:
                int r9 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
                if (r9 == 0) goto L_0x00a7
                r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r7 == 0) goto L_0x00a7
                java.util.concurrent.atomic.AtomicLong r7 = r0.requested
                long r8 = -r11
                r7.addAndGet(r8)
            L_0x00a7:
                int r7 = r18.get()
                if (r7 != r6) goto L_0x00b7
                r0.consumed = r1
                int r6 = -r6
                int r6 = r0.addAndGet(r6)
                if (r6 != 0) goto L_0x000c
                return
            L_0x00b7:
                r6 = r7
                goto L_0x000c
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ParallelRunOn.C4648c.run():void");
        }
    }
}
