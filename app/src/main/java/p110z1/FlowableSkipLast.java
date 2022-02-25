package p110z1;

import java.util.ArrayDeque;

/* renamed from: z1.bdj */
/* loaded from: classes3.dex */
public final class FlowableSkipLast<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final int f18351c;

    public FlowableSkipLast(Flowable<T> arvVar, int i) {
        super(arvVar);
        this.f18351c = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4198a(dbxVar, this.f18351c));
    }

    /* compiled from: FlowableSkipLast.java */
    /* renamed from: z1.bdj$a */
    /* loaded from: classes3.dex */
    static final class C4198a<T> extends ArrayDeque<T> implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -3807491841935125653L;
        final Subscriber<? super T> downstream;
        final int skip;
        dby upstream;

        C4198a(Subscriber<? super T> dbxVar, int i) {
            super(i);
            this.downstream = dbxVar;
            this.skip = i;
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
            if (this.skip == size()) {
                this.downstream.onNext((T) poll());
            } else {
                this.upstream.request(1L);
            }
            offer(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
        }
    }
}
