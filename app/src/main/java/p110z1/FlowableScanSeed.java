package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bdb */
/* loaded from: classes3.dex */
public final class FlowableScanSeed<T, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final BiFunction<R, ? super T, R> f18319c;

    /* renamed from: d */
    final Callable<R> f18320d;

    public FlowableScanSeed(Flowable<T> arvVar, Callable<R> callable, BiFunction<R, ? super T, R> auiVar) {
        super(arvVar);
        this.f18319c = auiVar;
        this.f18320d = callable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        try {
            this.f17812b.m11187a((FlowableSubscriber) new C4189a(dbxVar, this.f18319c, ObjectHelper.m9873a(this.f18320d.call(), "The seed supplied is null"), m11274a()));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: FlowableScanSeed.java */
    /* renamed from: z1.bdb$a */
    /* loaded from: classes3.dex */
    static final class C4189a<T, R> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -1776795561228106469L;
        final BiFunction<R, ? super T, R> accumulator;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        Throwable error;
        final int limit;
        final int prefetch;
        final SimplePlainQueue<R> queue;
        final AtomicLong requested = new AtomicLong();
        dby upstream;
        R value;

        C4189a(Subscriber<? super R> dbxVar, BiFunction<R, ? super T, R> auiVar, R r, int i) {
            this.downstream = dbxVar;
            this.accumulator = auiVar;
            this.value = r;
            this.prefetch = i;
            this.limit = i - (i >> 2);
            this.queue = new SpscArrayQueue(i);
            this.queue.offer(r);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(this.prefetch - 1);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                try {
                    R r = (R) ObjectHelper.m9873a(this.accumulator.apply(this.value, t), "The accumulator returned a null value");
                    this.value = r;
                    this.queue.offer(r);
                    drain();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.upstream.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.error = th;
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
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                drain();
            }
        }

        void drain() {
            int i;
            Throwable th;
            if (getAndIncrement() == 0) {
                Subscriber<? super R> dbxVar = this.downstream;
                SimplePlainQueue<R> avvVar = this.queue;
                int i2 = this.limit;
                int i3 = this.consumed;
                int i4 = 1;
                do {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (true) {
                        i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                        if (i == 0) {
                            break;
                        } else if (this.cancelled) {
                            avvVar.clear();
                            return;
                        } else {
                            boolean z = this.done;
                            if (!z || (th = this.error) == null) {
                                Object obj = (R) avvVar.poll();
                                boolean z2 = obj == null;
                                if (z && z2) {
                                    dbxVar.onComplete();
                                    return;
                                } else if (z2) {
                                    break;
                                } else {
                                    dbxVar.onNext(obj);
                                    j2++;
                                    i3++;
                                    if (i3 == i2) {
                                        this.upstream.request(i2);
                                        i3 = 0;
                                    }
                                }
                            } else {
                                avvVar.clear();
                                dbxVar.onError(th);
                                return;
                            }
                        }
                    }
                    if (i == 0 && this.done) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            avvVar.clear();
                            dbxVar.onError(th2);
                            return;
                        } else if (avvVar.isEmpty()) {
                            dbxVar.onComplete();
                            return;
                        }
                    }
                    if (j2 != 0) {
                        BackpressureHelper.m9446c(this.requested, j2);
                    }
                    this.consumed = i3;
                    i4 = addAndGet(-i4);
                } while (i4 != 0);
            }
        }
    }
}
