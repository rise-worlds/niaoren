package p110z1;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.FlowableTimeoutTimed;

/* renamed from: z1.beb */
/* loaded from: classes3.dex */
public final class FlowableTimeout<T, U, V> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Publisher<U> f18411c;

    /* renamed from: d */
    final Function<? super T, ? extends Publisher<V>> f18412d;

    /* renamed from: e */
    final Publisher<? extends T> f18413e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableTimeout.java */
    /* renamed from: z1.beb$c */
    /* loaded from: classes3.dex */
    public interface AbstractC4223c extends FlowableTimeoutTimed.AbstractC4228d {
        void onTimeoutError(long j, Throwable th);
    }

    public FlowableTimeout(Flowable<T> arvVar, Publisher<U> dbwVar, Function<? super T, ? extends Publisher<V>> aunVar, Publisher<? extends T> dbwVar2) {
        super(arvVar);
        this.f18411c = dbwVar;
        this.f18412d = aunVar;
        this.f18413e = dbwVar2;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        Publisher<? extends T> dbwVar = this.f18413e;
        if (dbwVar == null) {
            C4224d dVar = new C4224d(dbxVar, this.f18412d);
            dbxVar.onSubscribe(dVar);
            dVar.startFirstTimeout(this.f18411c);
            this.f17812b.m11187a((FlowableSubscriber) dVar);
            return;
        }
        C4222b bVar = new C4222b(dbxVar, this.f18412d, dbwVar);
        dbxVar.onSubscribe(bVar);
        bVar.startFirstTimeout(this.f18411c);
        this.f17812b.m11187a((FlowableSubscriber) bVar);
    }

    /* compiled from: FlowableTimeout.java */
    /* renamed from: z1.beb$d */
    /* loaded from: classes3.dex */
    static final class C4224d<T> extends AtomicLong implements FlowableSubscriber<T>, AbstractC4223c, dby {
        private static final long serialVersionUID = 3764492702657003550L;
        final Subscriber<? super T> downstream;
        final Function<? super T, ? extends Publisher<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        C4224d(Subscriber<? super T> dbxVar, Function<? super T, ? extends Publisher<?>> aunVar) {
            this.downstream = dbxVar;
            this.itemTimeoutIndicator = aunVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            long j = get();
            if (j != cjm.f20759b) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    Disposable atrVar = this.task.get();
                    if (atrVar != null) {
                        atrVar.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null Publisher.");
                        C4221a aVar = new C4221a(j2, this);
                        if (this.task.replace(aVar)) {
                            dbwVar.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.upstream.get().cancel();
                        getAndSet(cjm.f20759b);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        void startFirstTimeout(Publisher<?> dbwVar) {
            if (dbwVar != null) {
                C4221a aVar = new C4221a(0L, this);
                if (this.task.replace(aVar)) {
                    dbwVar.subscribe(aVar);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.FlowableTimeoutTimed.AbstractC4228d
        public void onTimeout(long j) {
            if (compareAndSet(j, cjm.f20759b)) {
                SubscriptionHelper.cancel(this.upstream);
                this.downstream.onError(new TimeoutException());
            }
        }

        @Override // p110z1.FlowableTimeout.AbstractC4223c
        public void onTimeoutError(long j, Throwable th) {
            if (compareAndSet(j, cjm.f20759b)) {
                SubscriptionHelper.cancel(this.upstream);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.dby
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            this.task.dispose();
        }
    }

    /* compiled from: FlowableTimeout.java */
    /* renamed from: z1.beb$b */
    /* loaded from: classes3.dex */
    static final class C4222b<T> extends SubscriptionArbiter implements FlowableSubscriber<T>, AbstractC4223c {
        private static final long serialVersionUID = 3764492702657003550L;
        long consumed;
        final Subscriber<? super T> downstream;
        Publisher<? extends T> fallback;
        final Function<? super T, ? extends Publisher<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<dby> upstream = new AtomicReference<>();
        final AtomicLong index = new AtomicLong();

        C4222b(Subscriber<? super T> dbxVar, Function<? super T, ? extends Publisher<?>> aunVar, Publisher<? extends T> dbwVar) {
            super(true);
            this.downstream = dbxVar;
            this.itemTimeoutIndicator = aunVar;
            this.fallback = dbwVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this.upstream, dbyVar)) {
                setSubscription(dbyVar);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            long j = this.index.get();
            if (j != cjm.f20759b) {
                long j2 = j + 1;
                if (this.index.compareAndSet(j, j2)) {
                    Disposable atrVar = this.task.get();
                    if (atrVar != null) {
                        atrVar.dispose();
                    }
                    this.consumed++;
                    this.downstream.onNext(t);
                    try {
                        Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null Publisher.");
                        C4221a aVar = new C4221a(j2, this);
                        if (this.task.replace(aVar)) {
                            dbwVar.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.upstream.get().cancel();
                        this.index.getAndSet(cjm.f20759b);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        void startFirstTimeout(Publisher<?> dbwVar) {
            if (dbwVar != null) {
                C4221a aVar = new C4221a(0L, this);
                if (this.task.replace(aVar)) {
                    dbwVar.subscribe(aVar);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.index.getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onError(th);
                this.task.dispose();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.index.getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onComplete();
                this.task.dispose();
            }
        }

        @Override // p110z1.FlowableTimeoutTimed.AbstractC4228d
        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, cjm.f20759b)) {
                SubscriptionHelper.cancel(this.upstream);
                Publisher<? extends T> dbwVar = this.fallback;
                this.fallback = null;
                long j2 = this.consumed;
                if (j2 != 0) {
                    produced(j2);
                }
                dbwVar.subscribe(new FlowableTimeoutTimed.C4225a(this.downstream, this));
            }
        }

        @Override // p110z1.FlowableTimeout.AbstractC4223c
        public void onTimeoutError(long j, Throwable th) {
            if (this.index.compareAndSet(j, cjm.f20759b)) {
                SubscriptionHelper.cancel(this.upstream);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.SubscriptionArbiter, p110z1.dby
        public void cancel() {
            super.cancel();
            this.task.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableTimeout.java */
    /* renamed from: z1.beb$a */
    /* loaded from: classes3.dex */
    public static final class C4221a extends AtomicReference<dby> implements FlowableSubscriber<Object>, Disposable {
        private static final long serialVersionUID = 8708641127342403073L;
        final long idx;
        final AbstractC4223c parent;

        C4221a(long j, AbstractC4223c cVar) {
            this.idx = j;
            this.parent = cVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            dby dbyVar = (dby) get();
            if (dbyVar != SubscriptionHelper.CANCELLED) {
                dbyVar.cancel();
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.onTimeout(this.idx);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.onTimeoutError(this.idx, th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.onTimeout(this.idx);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }
}
