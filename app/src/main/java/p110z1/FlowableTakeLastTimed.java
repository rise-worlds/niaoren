package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bdt */
/* loaded from: classes3.dex */
public final class FlowableTakeLastTimed<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18377c;

    /* renamed from: d */
    final long f18378d;

    /* renamed from: e */
    final TimeUnit f18379e;

    /* renamed from: f */
    final Scheduler f18380f;

    /* renamed from: g */
    final int f18381g;

    /* renamed from: h */
    final boolean f18382h;

    public FlowableTakeLastTimed(Flowable<T> arvVar, long j, long j2, TimeUnit timeUnit, Scheduler astVar, int i, boolean z) {
        super(arvVar);
        this.f18377c = j;
        this.f18378d = j2;
        this.f18379e = timeUnit;
        this.f18380f = astVar;
        this.f18381g = i;
        this.f18382h = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4211a(dbxVar, this.f18377c, this.f18378d, this.f18379e, this.f18380f, this.f18381g, this.f18382h));
    }

    /* compiled from: FlowableTakeLastTimed.java */
    /* renamed from: z1.bdt$a */
    /* loaded from: classes3.dex */
    static final class C4211a<T> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final long count;
        final boolean delayError;
        volatile boolean done;
        final Subscriber<? super T> downstream;
        Throwable error;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested = new AtomicLong();
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;
        dby upstream;

        C4211a(Subscriber<? super T> dbxVar, long j, long j2, TimeUnit timeUnit, Scheduler astVar, int i, boolean z) {
            this.downstream = dbxVar;
            this.count = j;
            this.time = j2;
            this.unit = timeUnit;
            this.scheduler = astVar;
            this.queue = new SpscLinkedArrayQueue<>(i);
            this.delayError = z;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            SpscLinkedArrayQueue<Object> bqlVar = this.queue;
            long a = this.scheduler.mo9035a(this.unit);
            bqlVar.offer(Long.valueOf(a), t);
            trim(a, bqlVar);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.delayError) {
                trim(this.scheduler.mo9035a(this.unit), this.queue);
            }
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            trim(this.scheduler.mo9035a(this.unit), this.queue);
            this.done = true;
            drain();
        }

        void trim(long j, SpscLinkedArrayQueue<Object> bqlVar) {
            long j2 = this.time;
            long j3 = this.count;
            boolean z = j3 == cjm.f20759b;
            while (!bqlVar.isEmpty()) {
                if (((Long) bqlVar.m9533a()).longValue() < j - j2 || (!z && (bqlVar.m9523b() >> 1) > j3)) {
                    bqlVar.poll();
                    bqlVar.poll();
                } else {
                    return;
                }
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
                Subscriber<? super T> dbxVar = this.downstream;
                SpscLinkedArrayQueue<Object> bqlVar = this.queue;
                boolean z = this.delayError;
                int i = 1;
                do {
                    if (this.done) {
                        if (!checkTerminated(bqlVar.isEmpty(), dbxVar, z)) {
                            long j = this.requested.get();
                            long j2 = 0;
                            while (true) {
                                if (!checkTerminated(bqlVar.m9533a() == null, dbxVar, z)) {
                                    if (j != j2) {
                                        bqlVar.poll();
                                        dbxVar.onNext(bqlVar.poll());
                                        j2++;
                                    } else if (j2 != 0) {
                                        BackpressureHelper.m9446c(this.requested, j2);
                                    }
                                } else {
                                    return;
                                }
                            }
                        } else {
                            return;
                        }
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        boolean checkTerminated(boolean z, Subscriber<? super T> dbxVar, boolean z2) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            } else if (!z2) {
                Throwable th = this.error;
                if (th != null) {
                    this.queue.clear();
                    dbxVar.onError(th);
                    return true;
                } else if (!z) {
                    return false;
                } else {
                    dbxVar.onComplete();
                    return true;
                }
            } else if (!z) {
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
}
