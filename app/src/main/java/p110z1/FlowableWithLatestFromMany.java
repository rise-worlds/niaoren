package p110z1;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: z1.beo */
/* loaded from: classes3.dex */
public final class FlowableWithLatestFromMany<T, R> extends AbstractFlowableWithUpstream<T, R> {
    @atn

    /* renamed from: c */
    final Publisher<?>[] f18513c;
    @atn

    /* renamed from: d */
    final Iterable<? extends Publisher<?>> f18514d;

    /* renamed from: e */
    final Function<? super Object[], R> f18515e;

    public FlowableWithLatestFromMany(@AbstractC3889atm Flowable<T> arvVar, @AbstractC3889atm Publisher<?>[] dbwVarArr, Function<? super Object[], R> aunVar) {
        super(arvVar);
        this.f18513c = dbwVarArr;
        this.f18514d = null;
        this.f18515e = aunVar;
    }

    public FlowableWithLatestFromMany(@AbstractC3889atm Flowable<T> arvVar, @AbstractC3889atm Iterable<? extends Publisher<?>> iterable, @AbstractC3889atm Function<? super Object[], R> aunVar) {
        super(arvVar);
        this.f18513c = null;
        this.f18514d = iterable;
        this.f18515e = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        int i;
        Publisher<?>[] dbwVarArr = this.f18513c;
        if (dbwVarArr == null) {
            dbwVarArr = new Publisher[8];
            try {
                i = 0;
                for (Publisher<?> dbwVar : this.f18514d) {
                    if (i == dbwVarArr.length) {
                        dbwVarArr = (Publisher[]) Arrays.copyOf(dbwVarArr, (i >> 1) + i);
                    }
                    i++;
                    dbwVarArr[i] = dbwVar;
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                EmptySubscription.error(th, dbxVar);
                return;
            }
        } else {
            i = dbwVarArr.length;
        }
        if (i == 0) {
            new FlowableMap(this.f17812b, new C4255a()).mo9054d((Subscriber) dbxVar);
            return;
        }
        C4256b bVar = new C4256b(dbxVar, this.f18515e, i);
        dbxVar.onSubscribe(bVar);
        bVar.subscribe(dbwVarArr, i);
        this.f17812b.m11187a((FlowableSubscriber) bVar);
    }

    /* compiled from: FlowableWithLatestFromMany.java */
    /* renamed from: z1.beo$b */
    /* loaded from: classes3.dex */
    static final class C4256b<T, R> extends AtomicInteger implements ConditionalSubscriber<T>, dby {
        private static final long serialVersionUID = 1577321883966341961L;
        final Function<? super Object[], R> combiner;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        final AtomicThrowable error;
        final AtomicLong requested;
        final C4257c[] subscribers;
        final AtomicReference<dby> upstream;
        final AtomicReferenceArray<Object> values;

        C4256b(Subscriber<? super R> dbxVar, Function<? super Object[], R> aunVar, int i) {
            this.downstream = dbxVar;
            this.combiner = aunVar;
            C4257c[] cVarArr = new C4257c[i];
            for (int i2 = 0; i2 < i; i2++) {
                cVarArr[i2] = new C4257c(this, i2);
            }
            this.subscribers = cVarArr;
            this.values = new AtomicReferenceArray<>(i);
            this.upstream = new AtomicReference<>();
            this.requested = new AtomicLong();
            this.error = new AtomicThrowable();
        }

        void subscribe(Publisher<?>[] dbwVarArr, int i) {
            C4257c[] cVarArr = this.subscribers;
            AtomicReference<dby> atomicReference = this.upstream;
            for (int i2 = 0; i2 < i && atomicReference.get() != SubscriptionHelper.CANCELLED; i2++) {
                dbwVarArr[i2].subscribe(cVarArr[i2]);
            }
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t) && !this.done) {
                this.upstream.get().request(1L);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            AtomicReferenceArray<Object> atomicReferenceArray = this.values;
            int length = atomicReferenceArray.length();
            Object[] objArr = new Object[length + 1];
            objArr[0] = t;
            int i = 0;
            while (i < length) {
                Object obj = atomicReferenceArray.get(i);
                if (obj == null) {
                    return false;
                }
                i++;
                objArr[i] = obj;
            }
            try {
                HalfSerializer.m9424a(this.downstream, ObjectHelper.m9873a(this.combiner.apply(objArr), "The combiner returned a null value"), this, this.error);
                return true;
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                cancel();
                onError(th);
                return false;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            cancelAllBut(-1);
            HalfSerializer.m9423a((Subscriber<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                cancelAllBut(-1);
                HalfSerializer.m9422a(this.downstream, this, this.error);
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            for (C4257c cVar : this.subscribers) {
                cVar.dispose();
            }
        }

        void innerNext(int i, Object obj) {
            this.values.set(i, obj);
        }

        void innerError(int i, Throwable th) {
            this.done = true;
            SubscriptionHelper.cancel(this.upstream);
            cancelAllBut(i);
            HalfSerializer.m9423a((Subscriber<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        void innerComplete(int i, boolean z) {
            if (!z) {
                this.done = true;
                SubscriptionHelper.cancel(this.upstream);
                cancelAllBut(i);
                HalfSerializer.m9422a(this.downstream, this, this.error);
            }
        }

        void cancelAllBut(int i) {
            C4257c[] cVarArr = this.subscribers;
            for (int i2 = 0; i2 < cVarArr.length; i2++) {
                if (i2 != i) {
                    cVarArr[i2].dispose();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableWithLatestFromMany.java */
    /* renamed from: z1.beo$c */
    /* loaded from: classes3.dex */
    public static final class C4257c extends AtomicReference<dby> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = 3256684027868224024L;
        boolean hasValue;
        final int index;
        final C4256b<?, ?> parent;

        C4257c(C4256b<?, ?> bVar, int i) {
            this.parent = bVar;
            this.index = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            if (!this.hasValue) {
                this.hasValue = true;
            }
            this.parent.innerNext(this.index, obj);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.parent.innerComplete(this.index, this.hasValue);
        }

        void dispose() {
            SubscriptionHelper.cancel(this);
        }
    }

    /* compiled from: FlowableWithLatestFromMany.java */
    /* renamed from: z1.beo$a */
    /* loaded from: classes3.dex */
    final class C4255a implements Function<T, R> {
        C4255a() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.lang.Object] */
        @Override // p110z1.Function
        public R apply(T t) throws Exception {
            return (R) ObjectHelper.m9873a(FlowableWithLatestFromMany.this.f18515e.apply(new Object[]{t}), "The combiner returned a null value");
        }
    }
}
