package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;

/* renamed from: z1.bad */
/* loaded from: classes3.dex */
public final class FlowableDistinct<T, K> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Function<? super T, K> f18017c;

    /* renamed from: d */
    final Callable<? extends Collection<? super K>> f18018d;

    public FlowableDistinct(Flowable<T> arvVar, Function<? super T, K> aunVar, Callable<? extends Collection<? super K>> callable) {
        super(arvVar);
        this.f18017c = aunVar;
        this.f18018d = callable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        try {
            this.f17812b.m11187a((FlowableSubscriber) new C4049a(dbxVar, this.f18017c, (Collection) ObjectHelper.m9873a(this.f18018d.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: FlowableDistinct.java */
    /* renamed from: z1.bad$a */
    /* loaded from: classes3.dex */
    static final class C4049a<T, K> extends BasicFuseableSubscriber<T, T> {

        /* renamed from: f */
        final Collection<? super K> f18019f;

        /* renamed from: g */
        final Function<? super T, K> f18020g;

        C4049a(Subscriber<? super T> dbxVar, Function<? super T, K> aunVar, Collection<? super K> collection) {
            super(dbxVar);
            this.f18020g = aunVar;
            this.f18019f = collection;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19994m) {
                if (this.f19995n == 0) {
                    try {
                        if (this.f18019f.add(ObjectHelper.m9873a(this.f18020g.apply(t), "The keySelector returned a null key"))) {
                            this.f19991j.onNext(t);
                        } else {
                            this.f19992k.request(1L);
                        }
                    } catch (Throwable th) {
                        m9470a(th);
                    }
                } else {
                    this.f19991j.onNext(null);
                }
            }
        }

        @Override // p110z1.BasicFuseableSubscriber, p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19994m) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19994m = true;
            this.f18019f.clear();
            this.f19991j.onError(th);
        }

        @Override // p110z1.BasicFuseableSubscriber, p110z1.Subscriber
        public void onComplete() {
            if (!this.f19994m) {
                this.f19994m = true;
                this.f18019f.clear();
                this.f19991j.onComplete();
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9471a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll;
            while (true) {
                poll = this.f19993l.poll();
                if (poll == null || this.f18019f.add((Object) ObjectHelper.m9873a(this.f18020g.apply(poll), "The keySelector returned a null key"))) {
                    break;
                } else if (this.f19995n == 2) {
                    this.f19992k.request(1L);
                }
            }
            return poll;
        }

        @Override // p110z1.BasicFuseableSubscriber, p110z1.SimpleQueue
        public void clear() {
            this.f18019f.clear();
            super.clear();
        }
    }
}
