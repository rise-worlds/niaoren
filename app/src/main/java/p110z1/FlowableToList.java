package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;

/* renamed from: z1.bee */
/* loaded from: classes3.dex */
public final class FlowableToList<T, U extends Collection<? super T>> extends AbstractFlowableWithUpstream<T, U> {

    /* renamed from: c */
    final Callable<U> f18425c;

    public FlowableToList(Flowable<T> arvVar, Callable<U> callable) {
        super(arvVar);
        this.f18425c = callable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super U> dbxVar) {
        try {
            this.f17812b.m11187a((FlowableSubscriber) new C4231a(dbxVar, (Collection) ObjectHelper.m9873a(this.f18425c.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: FlowableToList.java */
    /* renamed from: z1.bee$a */
    /* loaded from: classes3.dex */
    static final class C4231a<T, U extends Collection<? super T>> extends DeferredScalarSubscription<U> implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -8134157938864266736L;
        dby upstream;

        /* JADX WARN: Multi-variable type inference failed */
        C4231a(Subscriber<? super U> dbxVar, U u) {
            super(dbxVar);
            this.value = u;
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
            Collection collection = (Collection) this.value;
            if (collection != null) {
                collection.add(t);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.value = null;
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            complete(this.value);
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
