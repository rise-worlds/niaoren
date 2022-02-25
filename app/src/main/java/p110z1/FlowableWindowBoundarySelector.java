package p110z1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bek */
/* loaded from: classes3.dex */
public final class FlowableWindowBoundarySelector<T, B, V> extends AbstractFlowableWithUpstream<T, Flowable<T>> {

    /* renamed from: c */
    final Publisher<B> f18444c;

    /* renamed from: d */
    final Function<? super B, ? extends Publisher<V>> f18445d;

    /* renamed from: e */
    final int f18446e;

    public FlowableWindowBoundarySelector(Flowable<T> arvVar, Publisher<B> dbwVar, Function<? super B, ? extends Publisher<V>> aunVar, int i) {
        super(arvVar);
        this.f18444c = dbwVar;
        this.f18445d = aunVar;
        this.f18446e = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super Flowable<T>> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4243c(new SerializedSubscriber(dbxVar), this.f18444c, this.f18445d, this.f18446e));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableWindowBoundarySelector.java */
    /* renamed from: z1.bek$c */
    /* loaded from: classes3.dex */
    public static final class C4243c<T, B, V> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements dby {

        /* renamed from: a */
        final Publisher<B> f18451a;

        /* renamed from: b */
        final Function<? super B, ? extends Publisher<V>> f18452b;

        /* renamed from: c */
        final int f18453c;

        /* renamed from: e */
        dby f18455e;

        /* renamed from: f */
        final AtomicReference<Disposable> f18456f = new AtomicReference<>();

        /* renamed from: h */
        final AtomicLong f18458h = new AtomicLong();

        /* renamed from: i */
        final AtomicBoolean f18459i = new AtomicBoolean();

        /* renamed from: d */
        final CompositeDisposable f18454d = new CompositeDisposable();

        /* renamed from: g */
        final List<UnicastProcessor<T>> f18457g = new ArrayList();

        @Override // p110z1.QueueDrainSubscriber, p110z1.QueueDrain
        /* renamed from: a */
        public boolean mo9383a(Subscriber<? super Flowable<T>> dbxVar, Object obj) {
            return false;
        }

        C4243c(Subscriber<? super Flowable<T>> dbxVar, Publisher<B> dbwVar, Function<? super B, ? extends Publisher<V>> aunVar, int i) {
            super(dbxVar, new MpscLinkedQueue());
            this.f18451a = dbwVar;
            this.f18452b = aunVar;
            this.f18453c = i;
            this.f18458h.lazySet(1L);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18455e, dbyVar)) {
                this.f18455e = dbyVar;
                this.f20003n.onSubscribe(this);
                if (!this.f18459i.get()) {
                    C4242b bVar = new C4242b(this);
                    if (this.f18456f.compareAndSet(null, bVar)) {
                        dbyVar.request(cjm.f20759b);
                        this.f18451a.subscribe(bVar);
                    }
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f20006q) {
                if (m9464f()) {
                    for (UnicastProcessor<T> bumVar : this.f18457g) {
                        bumVar.onNext(t);
                    }
                    if (mo9385a(-1) == 0) {
                        return;
                    }
                } else {
                    this.f20004o.offer(NotificationLite.next(t));
                    if (!mo9380e()) {
                        return;
                    }
                }
                m9722b();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f20006q) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f20007r = th;
            this.f20006q = true;
            if (mo9380e()) {
                m9722b();
            }
            if (this.f18458h.decrementAndGet() == 0) {
                this.f18454d.dispose();
            }
            this.f20003n.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f20006q) {
                this.f20006q = true;
                if (mo9380e()) {
                    m9722b();
                }
                if (this.f18458h.decrementAndGet() == 0) {
                    this.f18454d.dispose();
                }
                this.f20003n.onComplete();
            }
        }

        /* renamed from: a */
        void m9724a(Throwable th) {
            this.f18455e.cancel();
            this.f18454d.dispose();
            DisposableHelper.dispose(this.f18456f);
            this.f20003n.onError(th);
        }

        @Override // p110z1.dby
        public void request(long j) {
            m9466b(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            if (this.f18459i.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.f18456f);
                if (this.f18458h.decrementAndGet() == 0) {
                    this.f18455e.cancel();
                }
            }
        }

        /* renamed from: a */
        void m9726a() {
            this.f18454d.dispose();
            DisposableHelper.dispose(this.f18456f);
        }

        /* renamed from: b */
        void m9722b() {
            SimpleQueue avwVar = this.f20004o;
            Subscriber<? super V> dbxVar = this.f20003n;
            List<UnicastProcessor<T>> list = this.f18457g;
            int i = 1;
            while (true) {
                boolean z = this.f20006q;
                Object poll = avwVar.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    m9726a();
                    Throwable th = this.f20007r;
                    if (th != null) {
                        for (UnicastProcessor<T> bumVar : list) {
                            bumVar.onError(th);
                        }
                    } else {
                        for (UnicastProcessor<T> bumVar2 : list) {
                            bumVar2.onComplete();
                        }
                    }
                    list.clear();
                    return;
                } else if (z2) {
                    i = mo9385a(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (poll instanceof C4244d) {
                    C4244d dVar = (C4244d) poll;
                    if (dVar.f18460a != null) {
                        if (list.remove(dVar.f18460a)) {
                            dVar.f18460a.onComplete();
                            if (this.f18458h.decrementAndGet() == 0) {
                                m9726a();
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.f18459i.get()) {
                        UnicastProcessor<T> m = UnicastProcessor.m9051m(this.f18453c);
                        long h = mo9378h();
                        if (h != 0) {
                            list.add(m);
                            dbxVar.onNext(m);
                            if (h != cjm.f20759b) {
                                mo9384a(1L);
                            }
                            try {
                                Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.f18452b.apply((B) dVar.f18461b), "The publisher supplied is null");
                                C4241a aVar = new C4241a(this, m);
                                if (this.f18454d.mo9939a(aVar)) {
                                    this.f18458h.getAndIncrement();
                                    dbwVar.subscribe(aVar);
                                }
                            } catch (Throwable th2) {
                                cancel();
                                dbxVar.onError(th2);
                            }
                        } else {
                            cancel();
                            dbxVar.onError(new MissingBackpressureException("Could not deliver new window due to lack of requests"));
                        }
                    }
                } else {
                    for (UnicastProcessor<T> bumVar3 : list) {
                        bumVar3.onNext((T) NotificationLite.getValue(poll));
                    }
                }
            }
        }

        /* renamed from: a */
        void m9725a(B b) {
            this.f20004o.offer(new C4244d(null, b));
            if (mo9380e()) {
                m9722b();
            }
        }

        /* renamed from: a */
        void m9723a(C4241a<T, V> aVar) {
            this.f18454d.mo9936c(aVar);
            this.f20004o.offer(new C4244d(aVar.f18448b, null));
            if (mo9380e()) {
                m9722b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableWindowBoundarySelector.java */
    /* renamed from: z1.bek$d */
    /* loaded from: classes3.dex */
    public static final class C4244d<T, B> {

        /* renamed from: a */
        final UnicastProcessor<T> f18460a;

        /* renamed from: b */
        final B f18461b;

        C4244d(UnicastProcessor<T> bumVar, B b) {
            this.f18460a = bumVar;
            this.f18461b = b;
        }
    }

    /* compiled from: FlowableWindowBoundarySelector.java */
    /* renamed from: z1.bek$b */
    /* loaded from: classes3.dex */
    static final class C4242b<T, B> extends DisposableSubscriber<B> {

        /* renamed from: a */
        final C4243c<T, B, ?> f18450a;

        C4242b(C4243c<T, B, ?> cVar) {
            this.f18450a = cVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(B b) {
            this.f18450a.m9725a((C4243c<T, B, ?>) b);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18450a.m9724a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18450a.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableWindowBoundarySelector.java */
    /* renamed from: z1.bek$a */
    /* loaded from: classes3.dex */
    public static final class C4241a<T, V> extends DisposableSubscriber<V> {

        /* renamed from: a */
        final C4243c<T, ?, V> f18447a;

        /* renamed from: b */
        final UnicastProcessor<T> f18448b;

        /* renamed from: c */
        boolean f18449c;

        C4241a(C4243c<T, ?, V> cVar, UnicastProcessor<T> bumVar) {
            this.f18447a = cVar;
            this.f18448b = bumVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(V v) {
            m8927d();
            onComplete();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18449c) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18449c = true;
            this.f18447a.m9724a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18449c) {
                this.f18449c = true;
                this.f18447a.m9723a((C4241a) this);
            }
        }
    }
}
