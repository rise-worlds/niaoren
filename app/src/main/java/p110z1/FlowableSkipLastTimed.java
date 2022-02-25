package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bdk */
/* loaded from: classes3.dex */
public final class FlowableSkipLastTimed<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18352c;

    /* renamed from: d */
    final TimeUnit f18353d;

    /* renamed from: e */
    final Scheduler f18354e;

    /* renamed from: f */
    final int f18355f;

    /* renamed from: g */
    final boolean f18356g;

    public FlowableSkipLastTimed(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar, int i, boolean z) {
        super(arvVar);
        this.f18352c = j;
        this.f18353d = timeUnit;
        this.f18354e = astVar;
        this.f18355f = i;
        this.f18356g = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4199a(dbxVar, this.f18352c, this.f18353d, this.f18354e, this.f18355f, this.f18356g));
    }

    /* compiled from: FlowableSkipLastTimed.java */
    /* renamed from: z1.bdk$a */
    /* loaded from: classes3.dex */
    static final class C4199a<T> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
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

        C4199a(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler astVar, int i, boolean z) {
            this.downstream = dbxVar;
            this.time = j;
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
            this.queue.offer(Long.valueOf(this.scheduler.mo9035a(this.unit)), t);
            drain();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
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
            long j;
            if (getAndIncrement() == 0) {
                Subscriber<? super T> dbxVar = this.downstream;
                SpscLinkedArrayQueue<Object> bqlVar = this.queue;
                boolean z = this.delayError;
                TimeUnit timeUnit = this.unit;
                Scheduler astVar = this.scheduler;
                long j2 = this.time;
                int i = 1;
                do {
                    long j3 = this.requested.get();
                    long j4 = 0;
                    while (true) {
                        if (j4 == j3) {
                            j = 0;
                            break;
                        }
                        boolean z2 = this.done;
                        Long l = (Long) bqlVar.m9533a();
                        boolean z3 = l == null;
                        boolean z4 = (z3 || l.longValue() <= astVar.mo9035a(timeUnit) - j2) ? z3 : true;
                        if (!checkTerminated(z2, z4, dbxVar, z)) {
                            if (z4) {
                                j = 0;
                                break;
                            }
                            bqlVar.poll();
                            dbxVar.onNext(bqlVar.poll());
                            j4++;
                        } else {
                            return;
                        }
                    }
                    if (j4 != j) {
                        BackpressureHelper.m9446c(this.requested, j4);
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> dbxVar, boolean z3) {
            if (this.cancelled) {
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
    }
}
