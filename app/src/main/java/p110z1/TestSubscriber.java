package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bvg */
/* loaded from: classes3.dex */
public class TestSubscriber<T> extends BaseTestConsumer<T, TestSubscriber<T>> implements FlowableSubscriber<T>, Disposable, dby {

    /* renamed from: k */
    private final Subscriber<? super T> f20349k;

    /* renamed from: l */
    private volatile boolean f20350l;

    /* renamed from: m */
    private final AtomicReference<dby> f20351m;

    /* renamed from: n */
    private final AtomicLong f20352n;

    /* renamed from: o */
    private QueueSubscription<T> f20353o;

    /* compiled from: TestSubscriber.java */
    /* renamed from: z1.bvg$a */
    /* loaded from: classes3.dex */
    enum EnumC4798a implements FlowableSubscriber<Object> {
        INSTANCE;

        @Override // p110z1.Subscriber
        public void onComplete() {
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
        }
    }

    /* renamed from: y */
    protected void m8905y() {
    }

    /* renamed from: x */
    public static <T> TestSubscriber<T> m8906x() {
        return new TestSubscriber<>();
    }

    /* renamed from: a */
    public static <T> TestSubscriber<T> m8915a(long j) {
        return new TestSubscriber<>(j);
    }

    /* renamed from: a */
    public static <T> TestSubscriber<T> m8913a(Subscriber<? super T> dbxVar) {
        return new TestSubscriber<>(dbxVar);
    }

    public TestSubscriber() {
        this(EnumC4798a.INSTANCE, cjm.f20759b);
    }

    public TestSubscriber(long j) {
        this(EnumC4798a.INSTANCE, j);
    }

    public TestSubscriber(Subscriber<? super T> dbxVar) {
        this(dbxVar, cjm.f20759b);
    }

    public TestSubscriber(Subscriber<? super T> dbxVar, long j) {
        if (j >= 0) {
            this.f20349k = dbxVar;
            this.f20351m = new AtomicReference<>();
            this.f20352n = new AtomicLong(j);
            return;
        }
        throw new IllegalArgumentException("Negative initial request not allowed");
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        this.f20111e = Thread.currentThread();
        if (dbyVar == null) {
            this.f20109c.add(new NullPointerException("onSubscribe received a null Subscription"));
        } else if (!this.f20351m.compareAndSet(null, dbyVar)) {
            dbyVar.cancel();
            if (this.f20351m.get() != SubscriptionHelper.CANCELLED) {
                this.f20109c.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + dbyVar));
            }
        } else {
            if (this.f20113g != 0 && (dbyVar instanceof QueueSubscription)) {
                this.f20353o = (QueueSubscription) dbyVar;
                int requestFusion = this.f20353o.requestFusion(this.f20113g);
                this.f20114h = requestFusion;
                if (requestFusion == 1) {
                    this.f20112f = true;
                    this.f20111e = Thread.currentThread();
                    while (true) {
                        try {
                            T poll = this.f20353o.poll();
                            if (poll != null) {
                                this.f20108b.add(poll);
                            } else {
                                this.f20110d++;
                                return;
                            }
                        } catch (Throwable th) {
                            this.f20109c.add(th);
                            return;
                        }
                    }
                }
            }
            this.f20349k.onSubscribe(dbyVar);
            long andSet = this.f20352n.getAndSet(0L);
            if (andSet != 0) {
                dbyVar.request(andSet);
            }
            m8905y();
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        if (!this.f20112f) {
            this.f20112f = true;
            if (this.f20351m.get() == null) {
                this.f20109c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        this.f20111e = Thread.currentThread();
        if (this.f20114h == 2) {
            while (true) {
                try {
                    T poll = this.f20353o.poll();
                    if (poll != null) {
                        this.f20108b.add(poll);
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    this.f20109c.add(th);
                    this.f20353o.cancel();
                    return;
                }
            }
        } else {
            this.f20108b.add(t);
            if (t == null) {
                this.f20109c.add(new NullPointerException("onNext received a null value"));
            }
            this.f20349k.onNext(t);
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        if (!this.f20112f) {
            this.f20112f = true;
            if (this.f20351m.get() == null) {
                this.f20109c.add(new NullPointerException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.f20111e = Thread.currentThread();
            this.f20109c.add(th);
            if (th == null) {
                this.f20109c.add(new IllegalStateException("onError received a null Throwable"));
            }
            this.f20349k.onError(th);
        } finally {
            this.f20107a.countDown();
        }
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (!this.f20112f) {
            this.f20112f = true;
            if (this.f20351m.get() == null) {
                this.f20109c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.f20111e = Thread.currentThread();
            this.f20110d++;
            this.f20349k.onComplete();
        } finally {
            this.f20107a.countDown();
        }
    }

    @Override // p110z1.dby
    public final void request(long j) {
        SubscriptionHelper.deferredRequest(this.f20351m, this.f20352n, j);
    }

    @Override // p110z1.dby
    public final void cancel() {
        if (!this.f20350l) {
            this.f20350l = true;
            SubscriptionHelper.cancel(this.f20351m);
        }
    }

    /* renamed from: z */
    public final boolean m8904z() {
        return this.f20350l;
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        cancel();
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return this.f20350l;
    }

    /* renamed from: A */
    public final boolean m8920A() {
        return this.f20351m.get() != null;
    }

    /* renamed from: B */
    public final TestSubscriber<T> mo8908q() {
        if (this.f20351m.get() != null) {
            return this;
        }
        throw m9341a("Not subscribed!");
    }

    /* renamed from: C */
    public final TestSubscriber<T> mo8907r() {
        if (this.f20351m.get() != null) {
            throw m9341a("Subscribed!");
        } else if (this.f20109c.isEmpty()) {
            return this;
        } else {
            throw m9341a("Not subscribed but errors found");
        }
    }

    /* renamed from: c */
    final TestSubscriber<T> m8911c(int i) {
        this.f20113g = i;
        return this;
    }

    /* renamed from: d */
    final TestSubscriber<T> m8910d(int i) {
        int i2 = this.f20114h;
        if (i2 == i) {
            return this;
        }
        if (this.f20353o != null) {
            throw new AssertionError("Fusion mode different. Expected: " + m8909e(i) + ", actual: " + m8909e(i2));
        }
        throw m9341a("Upstream is not fuseable");
    }

    /* renamed from: e */
    static String m8909e(int i) {
        switch (i) {
            case 0:
                return "NONE";
            case 1:
                return "SYNC";
            case 2:
                return "ASYNC";
            default:
                return "Unknown(" + i + ")";
        }
    }

    /* renamed from: D */
    final TestSubscriber<T> m8917D() {
        if (this.f20353o != null) {
            return this;
        }
        throw new AssertionError("Upstream is not fuseable.");
    }

    /* renamed from: E */
    final TestSubscriber<T> m8916E() {
        if (this.f20353o == null) {
            return this;
        }
        throw new AssertionError("Upstream is fuseable.");
    }

    /* renamed from: a */
    public final TestSubscriber<T> m8914a(Consumer<? super TestSubscriber<T>> aumVar) {
        try {
            aumVar.accept(this);
            return this;
        } catch (Throwable th) {
            throw ExceptionHelper.m9432a(th);
        }
    }

    /* renamed from: b */
    public final TestSubscriber<T> m8912b(long j) {
        request(j);
        return this;
    }
}
