package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.baj */
/* loaded from: classes3.dex */
public final class FlowableElementAt<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18057c;

    /* renamed from: d */
    final T f18058d;

    /* renamed from: e */
    final boolean f18059e;

    public FlowableElementAt(Flowable<T> arvVar, long j, T t, boolean z) {
        super(arvVar);
        this.f18057c = j;
        this.f18058d = t;
        this.f18059e = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4059a(dbxVar, this.f18057c, this.f18058d, this.f18059e));
    }

    /* compiled from: FlowableElementAt.java */
    /* renamed from: z1.baj$a */
    /* loaded from: classes3.dex */
    static final class C4059a<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 4066607327284737757L;
        long count;
        final T defaultValue;
        boolean done;
        final boolean errorOnFewer;
        final long index;
        dby upstream;

        C4059a(Subscriber<? super T> dbxVar, long j, T t, boolean z) {
            super(dbxVar);
            this.index = j;
            this.defaultValue = t;
            this.errorOnFewer = z;
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
            if (!this.done) {
                long j = this.count;
                if (j == this.index) {
                    this.done = true;
                    this.upstream.cancel();
                    complete(t);
                    return;
                }
                this.count = j + 1;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                T t = this.defaultValue;
                if (t != null) {
                    complete(t);
                } else if (this.errorOnFewer) {
                    this.downstream.onError(new NoSuchElementException());
                } else {
                    this.downstream.onComplete();
                }
            }
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
