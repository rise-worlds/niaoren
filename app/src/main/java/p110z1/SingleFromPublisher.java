package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bpn */
/* loaded from: classes3.dex */
public final class SingleFromPublisher<T> extends Single<T> {

    /* renamed from: a */
    final Publisher<? extends T> f19794a;

    public SingleFromPublisher(Publisher<? extends T> dbwVar) {
        this.f19794a = dbwVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19794a.subscribe(new C4684a(asxVar));
    }

    /* compiled from: SingleFromPublisher.java */
    /* renamed from: z1.bpn$a */
    /* loaded from: classes3.dex */
    static final class C4684a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f19795a;

        /* renamed from: b */
        dby f19796b;

        /* renamed from: c */
        T f19797c;

        /* renamed from: d */
        boolean f19798d;

        /* renamed from: e */
        volatile boolean f19799e;

        C4684a(SingleObserver<? super T> asxVar) {
            this.f19795a = asxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19796b, dbyVar)) {
                this.f19796b = dbyVar;
                this.f19795a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19798d) {
                if (this.f19797c != null) {
                    this.f19796b.cancel();
                    this.f19798d = true;
                    this.f19797c = null;
                    this.f19795a.onError(new IndexOutOfBoundsException("Too many elements in the Publisher"));
                    return;
                }
                this.f19797c = t;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19798d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19798d = true;
            this.f19797c = null;
            this.f19795a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19798d) {
                this.f19798d = true;
                T t = this.f19797c;
                this.f19797c = null;
                if (t == null) {
                    this.f19795a.onError(new NoSuchElementException("The source Publisher is empty"));
                } else {
                    this.f19795a.onSuccess(t);
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19799e;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19799e = true;
            this.f19796b.cancel();
        }
    }
}
