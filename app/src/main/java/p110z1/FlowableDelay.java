package p110z1;

import java.util.concurrent.TimeUnit;
import p110z1.Scheduler;

/* renamed from: z1.azz */
/* loaded from: classes3.dex */
public final class FlowableDelay<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f17993c;

    /* renamed from: d */
    final TimeUnit f17994d;

    /* renamed from: e */
    final Scheduler f17995e;

    /* renamed from: f */
    final boolean f17996f;

    public FlowableDelay(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        super(arvVar);
        this.f17993c = j;
        this.f17994d = timeUnit;
        this.f17995e = astVar;
        this.f17996f = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4039a(this.f17996f ? dbxVar : new SerializedSubscriber(dbxVar), this.f17993c, this.f17994d, this.f17995e.mo9034b(), this.f17996f));
    }

    /* compiled from: FlowableDelay.java */
    /* renamed from: z1.azz$a */
    /* loaded from: classes3.dex */
    static final class C4039a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f17997a;

        /* renamed from: b */
        final long f17998b;

        /* renamed from: c */
        final TimeUnit f17999c;

        /* renamed from: d */
        final Scheduler.AbstractC3881c f18000d;

        /* renamed from: e */
        final boolean f18001e;

        /* renamed from: f */
        dby f18002f;

        C4039a(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar, boolean z) {
            this.f17997a = dbxVar;
            this.f17998b = j;
            this.f17999c = timeUnit;
            this.f18000d = cVar;
            this.f18001e = z;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18002f, dbyVar)) {
                this.f18002f = dbyVar;
                this.f17997a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f18000d.mo9030a(new RunnableC4042c(t), this.f17998b, this.f17999c);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18000d.mo9030a(new RunnableC4041b(th), this.f18001e ? this.f17998b : 0L, this.f17999c);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18000d.mo9030a(new RunnableC4040a(), this.f17998b, this.f17999c);
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18002f.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18002f.cancel();
            this.f18000d.dispose();
        }

        /* compiled from: FlowableDelay.java */
        /* renamed from: z1.azz$a$c */
        /* loaded from: classes3.dex */
        final class RunnableC4042c implements Runnable {

            /* renamed from: b */
            private final T f18007b;

            RunnableC4042c(T t) {
                this.f18007b = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                C4039a.this.f17997a.onNext((T) this.f18007b);
            }
        }

        /* compiled from: FlowableDelay.java */
        /* renamed from: z1.azz$a$b */
        /* loaded from: classes3.dex */
        final class RunnableC4041b implements Runnable {

            /* renamed from: b */
            private final Throwable f18005b;

            RunnableC4041b(Throwable th) {
                this.f18005b = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    C4039a.this.f17997a.onError(this.f18005b);
                } finally {
                    C4039a.this.f18000d.dispose();
                }
            }
        }

        /* compiled from: FlowableDelay.java */
        /* renamed from: z1.azz$a$a */
        /* loaded from: classes3.dex */
        final class RunnableC4040a implements Runnable {
            RunnableC4040a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    C4039a.this.f17997a.onComplete();
                } finally {
                    C4039a.this.f18000d.dispose();
                }
            }
        }
    }
}
