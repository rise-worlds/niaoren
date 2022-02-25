package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;

/* renamed from: z1.azf */
/* loaded from: classes3.dex */
public final class FlowableBufferExactBoundary<T, U extends Collection<? super T>, B> extends AbstractFlowableWithUpstream<T, U> {

    /* renamed from: c */
    final Publisher<B> f17879c;

    /* renamed from: d */
    final Callable<U> f17880d;

    public FlowableBufferExactBoundary(Flowable<T> arvVar, Publisher<B> dbwVar, Callable<U> callable) {
        super(arvVar);
        this.f17879c = dbwVar;
        this.f17880d = callable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super U> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4000b(new SerializedSubscriber(dbxVar), this.f17880d, this.f17879c));
    }

    /* compiled from: FlowableBufferExactBoundary.java */
    /* renamed from: z1.azf$b */
    /* loaded from: classes3.dex */
    static final class C4000b<T, U extends Collection<? super T>, B> extends QueueDrainSubscriber<T, U, U> implements FlowableSubscriber<T>, Disposable, dby {

        /* renamed from: a */
        final Callable<U> f17882a;

        /* renamed from: b */
        final Publisher<B> f17883b;

        /* renamed from: c */
        dby f17884c;

        /* renamed from: d */
        Disposable f17885d;

        /* renamed from: e */
        U f17886e;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.QueueDrainSubscriber, p110z1.QueueDrain
        /* renamed from: a */
        public /* bridge */ /* synthetic */ boolean mo9383a(Subscriber dbxVar, Object obj) {
            return m9809a((Subscriber<? super Subscriber>) dbxVar, (Subscriber) ((Collection) obj));
        }

        C4000b(Subscriber<? super U> dbxVar, Callable<U> callable, Publisher<B> dbwVar) {
            super(dbxVar, new MpscLinkedQueue());
            this.f17882a = callable;
            this.f17883b = dbwVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17884c, dbyVar)) {
                this.f17884c = dbyVar;
                try {
                    this.f17886e = (U) ((Collection) ObjectHelper.m9873a(this.f17882a.call(), "The buffer supplied is null"));
                    C3999a aVar = new C3999a(this);
                    this.f17885d = aVar;
                    this.f20003n.onSubscribe(this);
                    if (!this.f20005p) {
                        dbyVar.request(cjm.f20759b);
                        this.f17883b.subscribe(aVar);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f20005p = true;
                    dbyVar.cancel();
                    EmptySubscription.error(th, this.f20003n);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            synchronized (this) {
                U u = this.f17886e;
                if (u != null) {
                    u.add(t);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            cancel();
            this.f20003n.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            synchronized (this) {
                U u = this.f17886e;
                if (u != null) {
                    this.f17886e = null;
                    this.f20004o.offer(u);
                    this.f20006q = true;
                    if (mo9380e()) {
                        QueueDrainHelper.m9373a((SimplePlainQueue) this.f20004o, (Subscriber) this.f20003n, false, (Disposable) this, (QueueDrain) this);
                    }
                }
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            m9466b(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.f20005p) {
                this.f20005p = true;
                this.f17885d.dispose();
                this.f17884c.cancel();
                if (mo9380e()) {
                    this.f20004o.clear();
                }
            }
        }

        /* renamed from: a */
        void m9810a() {
            try {
                U u = (U) ((Collection) ObjectHelper.m9873a(this.f17882a.call(), "The buffer supplied is null"));
                synchronized (this) {
                    U u2 = this.f17886e;
                    if (u2 != null) {
                        this.f17886e = u;
                        m9467a(u2, false, this);
                    }
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                cancel();
                this.f20003n.onError(th);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            cancel();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f20005p;
        }

        /* renamed from: a */
        public boolean m9809a(Subscriber<? super U> dbxVar, U u) {
            this.f20003n.onNext(u);
            return true;
        }
    }

    /* compiled from: FlowableBufferExactBoundary.java */
    /* renamed from: z1.azf$a */
    /* loaded from: classes3.dex */
    static final class C3999a<T, U extends Collection<? super T>, B> extends DisposableSubscriber<B> {

        /* renamed from: a */
        final C4000b<T, U, B> f17881a;

        C3999a(C4000b<T, U, B> bVar) {
            this.f17881a = bVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(B b) {
            this.f17881a.m9810a();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f17881a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f17881a.onComplete();
        }
    }
}
