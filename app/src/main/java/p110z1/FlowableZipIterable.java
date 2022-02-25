package p110z1;

import java.util.Iterator;

/* renamed from: z1.beq */
/* loaded from: classes3.dex */
public final class FlowableZipIterable<T, U, V> extends AbstractFlowableWithUpstream<T, V> {

    /* renamed from: c */
    final Iterable<U> f18522c;

    /* renamed from: d */
    final BiFunction<? super T, ? super U, ? extends V> f18523d;

    public FlowableZipIterable(Flowable<T> arvVar, Iterable<U> iterable, BiFunction<? super T, ? super U, ? extends V> auiVar) {
        super(arvVar);
        this.f18522c = iterable;
        this.f18523d = auiVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super V> dbxVar) {
        try {
            Iterator it = (Iterator) ObjectHelper.m9873a(this.f18522c.iterator(), "The iterator returned by other is null");
            try {
                if (!it.hasNext()) {
                    EmptySubscription.complete(dbxVar);
                } else {
                    this.f17812b.m11187a((FlowableSubscriber) new C4260a(dbxVar, it, this.f18523d));
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                EmptySubscription.error(th, dbxVar);
            }
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            EmptySubscription.error(th2, dbxVar);
        }
    }

    /* compiled from: FlowableZipIterable.java */
    /* renamed from: z1.beq$a */
    /* loaded from: classes3.dex */
    static final class C4260a<T, U, V> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super V> f18524a;

        /* renamed from: b */
        final Iterator<U> f18525b;

        /* renamed from: c */
        final BiFunction<? super T, ? super U, ? extends V> f18526c;

        /* renamed from: d */
        dby f18527d;

        /* renamed from: e */
        boolean f18528e;

        C4260a(Subscriber<? super V> dbxVar, Iterator<U> it, BiFunction<? super T, ? super U, ? extends V> auiVar) {
            this.f18524a = dbxVar;
            this.f18525b = it;
            this.f18526c = auiVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18527d, dbyVar)) {
                this.f18527d = dbyVar;
                this.f18524a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18528e) {
                try {
                    try {
                        this.f18524a.onNext(ObjectHelper.m9873a(this.f18526c.apply(t, ObjectHelper.m9873a(this.f18525b.next(), "The iterator returned a null value")), "The zipper function returned a null value"));
                        try {
                            if (!this.f18525b.hasNext()) {
                                this.f18528e = true;
                                this.f18527d.cancel();
                                this.f18524a.onComplete();
                            }
                        } catch (Throwable th) {
                            m9712a(th);
                        }
                    } catch (Throwable th2) {
                        m9712a(th2);
                    }
                } catch (Throwable th3) {
                    m9712a(th3);
                }
            }
        }

        /* renamed from: a */
        void m9712a(Throwable th) {
            Exceptions.m9983b(th);
            this.f18528e = true;
            this.f18527d.cancel();
            this.f18524a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18528e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18528e = true;
            this.f18524a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18528e) {
                this.f18528e = true;
                this.f18524a.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18527d.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18527d.cancel();
        }
    }
}
