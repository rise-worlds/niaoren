package p110z1;

import java.util.concurrent.TimeUnit;

/* renamed from: z1.bea */
/* loaded from: classes3.dex */
public final class FlowableTimeInterval<T> extends AbstractFlowableWithUpstream<T, Timed<T>> {

    /* renamed from: c */
    final Scheduler f18404c;

    /* renamed from: d */
    final TimeUnit f18405d;

    public FlowableTimeInterval(Flowable<T> arvVar, TimeUnit timeUnit, Scheduler astVar) {
        super(arvVar);
        this.f18404c = astVar;
        this.f18405d = timeUnit;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super Timed<T>> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4220a(dbxVar, this.f18405d, this.f18404c));
    }

    /* compiled from: FlowableTimeInterval.java */
    /* renamed from: z1.bea$a */
    /* loaded from: classes3.dex */
    static final class C4220a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super Timed<T>> f18406a;

        /* renamed from: b */
        final TimeUnit f18407b;

        /* renamed from: c */
        final Scheduler f18408c;

        /* renamed from: d */
        dby f18409d;

        /* renamed from: e */
        long f18410e;

        C4220a(Subscriber<? super Timed<T>> dbxVar, TimeUnit timeUnit, Scheduler astVar) {
            this.f18406a = dbxVar;
            this.f18408c = astVar;
            this.f18407b = timeUnit;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18409d, dbyVar)) {
                this.f18410e = this.f18408c.mo9035a(this.f18407b);
                this.f18409d = dbyVar;
                this.f18406a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            long a = this.f18408c.mo9035a(this.f18407b);
            long j = this.f18410e;
            this.f18410e = a;
            this.f18406a.onNext(new Timed(t, a - j, this.f18407b));
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18406a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18406a.onComplete();
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18409d.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18409d.cancel();
        }
    }
}
