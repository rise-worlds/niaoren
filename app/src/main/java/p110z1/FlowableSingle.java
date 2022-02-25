package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bdf */
/* loaded from: classes3.dex */
public final class FlowableSingle<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final T f18333c;

    /* renamed from: d */
    final boolean f18334d;

    public FlowableSingle(Flowable<T> arvVar, T t, boolean z) {
        super(arvVar);
        this.f18333c = t;
        this.f18334d = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4194a(dbxVar, this.f18333c, this.f18334d));
    }

    /* compiled from: FlowableSingle.java */
    /* renamed from: z1.bdf$a */
    /* loaded from: classes3.dex */
    static final class C4194a<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -5526049321428043809L;
        final T defaultValue;
        boolean done;
        final boolean failOnEmpty;
        dby upstream;

        C4194a(Subscriber<? super T> dbxVar, T t, boolean z) {
            super(dbxVar);
            this.defaultValue = t;
            this.failOnEmpty = z;
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
                if (this.value != null) {
                    this.done = true;
                    this.upstream.cancel();
                    this.downstream.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.value = t;
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
                T t = this.value;
                this.value = null;
                if (t == null) {
                    t = this.defaultValue;
                }
                if (t != null) {
                    complete(t);
                } else if (this.failOnEmpty) {
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
