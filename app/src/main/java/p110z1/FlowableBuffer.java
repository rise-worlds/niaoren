package p110z1;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.azc */
/* loaded from: classes3.dex */
public final class FlowableBuffer<T, C extends Collection<? super T>> extends AbstractFlowableWithUpstream<T, C> {

    /* renamed from: c */
    final int f17857c;

    /* renamed from: d */
    final int f17858d;

    /* renamed from: e */
    final Callable<C> f17859e;

    public FlowableBuffer(Flowable<T> arvVar, int i, int i2, Callable<C> callable) {
        super(arvVar);
        this.f17857c = i;
        this.f17858d = i2;
        this.f17859e = callable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super C> dbxVar) {
        int i = this.f17857c;
        int i2 = this.f17858d;
        if (i == i2) {
            this.f17812b.m11187a((FlowableSubscriber) new C3991a(dbxVar, this.f17857c, this.f17859e));
        } else if (i2 > i) {
            this.f17812b.m11187a((FlowableSubscriber) new C3993c(dbxVar, this.f17857c, this.f17858d, this.f17859e));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C3992b(dbxVar, this.f17857c, this.f17858d, this.f17859e));
        }
    }

    /* compiled from: FlowableBuffer.java */
    /* renamed from: z1.azc$a */
    /* loaded from: classes3.dex */
    static final class C3991a<T, C extends Collection<? super T>> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super C> f17860a;

        /* renamed from: b */
        final Callable<C> f17861b;

        /* renamed from: c */
        final int f17862c;

        /* renamed from: d */
        C f17863d;

        /* renamed from: e */
        dby f17864e;

        /* renamed from: f */
        boolean f17865f;

        /* renamed from: g */
        int f17866g;

        C3991a(Subscriber<? super C> dbxVar, int i, Callable<C> callable) {
            this.f17860a = dbxVar;
            this.f17862c = i;
            this.f17861b = callable;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.f17864e.request(BackpressureHelper.m9448b(j, this.f17862c));
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f17864e.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17864e, dbyVar)) {
                this.f17864e = dbyVar;
                this.f17860a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f17865f) {
                C c = this.f17863d;
                if (c == null) {
                    try {
                        c = (C) ((Collection) ObjectHelper.m9873a(this.f17861b.call(), "The bufferSupplier returned a null buffer"));
                        this.f17863d = c;
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        cancel();
                        onError(th);
                        return;
                    }
                }
                c.add(t);
                int i = this.f17866g + 1;
                if (i == this.f17862c) {
                    this.f17866g = 0;
                    this.f17863d = null;
                    this.f17860a.onNext(c);
                    return;
                }
                this.f17866g = i;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f17865f) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f17865f = true;
            this.f17860a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f17865f) {
                this.f17865f = true;
                C c = this.f17863d;
                if (c != null && !c.isEmpty()) {
                    this.f17860a.onNext(c);
                }
                this.f17860a.onComplete();
            }
        }
    }

    /* compiled from: FlowableBuffer.java */
    /* renamed from: z1.azc$c */
    /* loaded from: classes3.dex */
    static final class C3993c<T, C extends Collection<? super T>> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -5616169793639412593L;
        C buffer;
        final Callable<C> bufferSupplier;
        boolean done;
        final Subscriber<? super C> downstream;
        int index;
        final int size;
        final int skip;
        dby upstream;

        C3993c(Subscriber<? super C> dbxVar, int i, int i2, Callable<C> callable) {
            this.downstream = dbxVar;
            this.size = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (!SubscriptionHelper.validate(j)) {
                return;
            }
            if (get() != 0 || !compareAndSet(0, 1)) {
                this.upstream.request(BackpressureHelper.m9448b(this.skip, j));
                return;
            }
            this.upstream.request(BackpressureHelper.m9450a(BackpressureHelper.m9448b(j, this.size), BackpressureHelper.m9448b(this.skip - this.size, j - 1)));
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
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
                C c = this.buffer;
                int i = this.index;
                int i2 = i + 1;
                if (i == 0) {
                    try {
                        c = (C) ((Collection) ObjectHelper.m9873a(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer"));
                        this.buffer = c;
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        cancel();
                        onError(th);
                        return;
                    }
                }
                if (c != null) {
                    c.add(t);
                    if (c.size() == this.size) {
                        this.buffer = null;
                        this.downstream.onNext(c);
                    }
                }
                if (i2 == this.skip) {
                    i2 = 0;
                }
                this.index = i2;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.buffer = null;
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                C c = this.buffer;
                this.buffer = null;
                if (c != null) {
                    this.downstream.onNext(c);
                }
                this.downstream.onComplete();
            }
        }
    }

    /* compiled from: FlowableBuffer.java */
    /* renamed from: z1.azc$b */
    /* loaded from: classes3.dex */
    static final class C3992b<T, C extends Collection<? super T>> extends AtomicLong implements FlowableSubscriber<T>, BooleanSupplier, dby {
        private static final long serialVersionUID = -7370244972039324525L;
        final Callable<C> bufferSupplier;
        volatile boolean cancelled;
        boolean done;
        final Subscriber<? super C> downstream;
        int index;
        long produced;
        final int size;
        final int skip;
        dby upstream;
        final AtomicBoolean once = new AtomicBoolean();
        final ArrayDeque<C> buffers = new ArrayDeque<>();

        C3992b(Subscriber<? super C> dbxVar, int i, int i2, Callable<C> callable) {
            this.downstream = dbxVar;
            this.size = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        @Override // p110z1.BooleanSupplier
        public boolean getAsBoolean() {
            return this.cancelled;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j) && !QueueDrainHelper.m9376a(j, this.downstream, this.buffers, this, this)) {
                if (this.once.get() || !this.once.compareAndSet(false, true)) {
                    this.upstream.request(BackpressureHelper.m9448b(this.skip, j));
                    return;
                }
                this.upstream.request(BackpressureHelper.m9450a(this.size, BackpressureHelper.m9448b(this.skip, j - 1)));
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                ArrayDeque<C> arrayDeque = this.buffers;
                int i = this.index;
                int i2 = i + 1;
                if (i == 0) {
                    try {
                        arrayDeque.offer((Collection) ObjectHelper.m9873a(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer"));
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        cancel();
                        onError(th);
                        return;
                    }
                }
                Collection collection = (Collection) arrayDeque.peek();
                if (collection != null && collection.size() + 1 == this.size) {
                    arrayDeque.poll();
                    collection.add(t);
                    this.produced++;
                    this.downstream.onNext(collection);
                }
                Iterator it = arrayDeque.iterator();
                while (it.hasNext()) {
                    ((Collection) it.next()).add(t);
                }
                if (i2 == this.skip) {
                    i2 = 0;
                }
                this.index = i2;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.buffers.clear();
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                long j = this.produced;
                if (j != 0) {
                    BackpressureHelper.m9446c(this, j);
                }
                QueueDrainHelper.m9372a(this.downstream, this.buffers, this, this);
            }
        }
    }
}
