package p110z1;

import p110z1.FlowableRepeatWhen;

/* renamed from: z1.bcw */
/* loaded from: classes3.dex */
public final class FlowableRetryWhen<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Function<? super Flowable<Throwable>, ? extends Publisher<?>> f18301c;

    public FlowableRetryWhen(Flowable<T> arvVar, Function<? super Flowable<Throwable>, ? extends Publisher<?>> aunVar) {
        super(arvVar);
        this.f18301c = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        SerializedSubscriber bvfVar = new SerializedSubscriber(dbxVar);
        FlowableProcessor<T> ac = UnicastProcessor.m9051m(8).m9111ac();
        try {
            Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.f18301c.apply(ac), "handler returned a null Publisher");
            FlowableRepeatWhen.C4159b bVar = new FlowableRepeatWhen.C4159b(this.f17812b);
            C4178a aVar = new C4178a(bvfVar, ac, bVar);
            bVar.subscriber = aVar;
            dbxVar.onSubscribe(aVar);
            dbwVar.subscribe(bVar);
            bVar.onNext(0);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: FlowableRetryWhen.java */
    /* renamed from: z1.bcw$a */
    /* loaded from: classes3.dex */
    static final class C4178a<T> extends FlowableRepeatWhen.AbstractC4160c<T, Throwable> {
        private static final long serialVersionUID = -2680129890138081029L;

        C4178a(Subscriber<? super T> dbxVar, FlowableProcessor<Throwable> buhVar, dby dbyVar) {
            super(dbxVar, buhVar, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            again(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.receiver.cancel();
            this.downstream.onComplete();
        }
    }
}
