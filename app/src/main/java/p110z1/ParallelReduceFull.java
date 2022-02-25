package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bog */
/* loaded from: classes3.dex */
public final class ParallelReduceFull<T> extends Flowable<T> {

    /* renamed from: b */
    final ParallelFlowable<? extends T> f19674b;

    /* renamed from: c */
    final BiFunction<T, T, T> f19675c;

    public ParallelReduceFull(ParallelFlowable<? extends T> bubVar, BiFunction<T, T, T> auiVar) {
        this.f19674b = bubVar;
        this.f19675c = auiVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4644b bVar = new C4644b(dbxVar, this.f19674b.mo9267a(), this.f19675c);
        dbxVar.onSubscribe(bVar);
        this.f19674b.mo9236a(bVar.subscribers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelReduceFull.java */
    /* renamed from: z1.bog$b */
    /* loaded from: classes3.dex */
    public static final class C4644b<T> extends DeferredScalarSubscription<T> {
        private static final long serialVersionUID = -5370107872170712765L;
        final BiFunction<T, T, T> reducer;
        final C4643a<T>[] subscribers;
        final AtomicReference<C4645c<T>> current = new AtomicReference<>();
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        C4644b(Subscriber<? super T> dbxVar, int i, BiFunction<T, T, T> auiVar) {
            super(dbxVar);
            C4643a<T>[] aVarArr = new C4643a[i];
            for (int i2 = 0; i2 < i; i2++) {
                aVarArr[i2] = new C4643a<>(this, auiVar);
            }
            this.subscribers = aVarArr;
            this.reducer = auiVar;
            this.remaining.lazySet(i);
        }

        C4645c<T> addValue(T t) {
            C4645c<T> cVar;
            int tryAcquireSlot;
            while (true) {
                cVar = this.current.get();
                if (cVar == null) {
                    cVar = new C4645c<>();
                    if (!this.current.compareAndSet(null, cVar)) {
                        continue;
                    }
                }
                tryAcquireSlot = cVar.tryAcquireSlot();
                if (tryAcquireSlot >= 0) {
                    break;
                }
                this.current.compareAndSet(cVar, null);
            }
            if (tryAcquireSlot == 0) {
                cVar.first = t;
            } else {
                cVar.second = t;
            }
            if (!cVar.releaseSlot()) {
                return null;
            }
            this.current.compareAndSet(cVar, null);
            return cVar;
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            for (C4643a<T> aVar : this.subscribers) {
                aVar.cancel();
            }
        }

        void innerError(Throwable th) {
            if (this.error.compareAndSet(null, th)) {
                cancel();
                this.downstream.onError(th);
            } else if (th != this.error.get()) {
                RxJavaPlugins.m9212a(th);
            }
        }

        void innerComplete(T t) {
            if (t != null) {
                while (true) {
                    C4645c<T> addValue = addValue(t);
                    if (addValue == null) {
                        break;
                    }
                    try {
                        t = (T) ObjectHelper.m9873a((Object) this.reducer.apply(addValue.first, addValue.second), "The reducer returned a null value");
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        innerError(th);
                        return;
                    }
                }
            }
            if (this.remaining.decrementAndGet() == 0) {
                C4645c<T> cVar = this.current.get();
                this.current.lazySet(null);
                if (cVar != null) {
                    complete(cVar.first);
                } else {
                    this.downstream.onComplete();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelReduceFull.java */
    /* renamed from: z1.bog$a */
    /* loaded from: classes3.dex */
    public static final class C4643a<T> extends AtomicReference<dby> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -7954444275102466525L;
        boolean done;
        final C4644b<T> parent;
        final BiFunction<T, T, T> reducer;
        T value;

        C4643a(C4644b<T> bVar, BiFunction<T, T, T> auiVar) {
            this.parent = bVar;
            this.reducer = auiVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                T t2 = this.value;
                if (t2 == null) {
                    this.value = t;
                    return;
                }
                try {
                    this.value = (T) ObjectHelper.m9873a((Object) this.reducer.apply(t2, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    get().cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.parent.innerError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.parent.innerComplete(this.value);
            }
        }

        void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelReduceFull.java */
    /* renamed from: z1.bog$c */
    /* loaded from: classes3.dex */
    public static final class C4645c<T> extends AtomicInteger {
        private static final long serialVersionUID = 473971317683868662L;
        T first;
        final AtomicInteger releaseIndex = new AtomicInteger();
        T second;

        C4645c() {
        }

        int tryAcquireSlot() {
            int i;
            do {
                i = get();
                if (i >= 2) {
                    return -1;
                }
            } while (!compareAndSet(i, i + 1));
            return i;
        }

        boolean releaseSlot() {
            return this.releaseIndex.incrementAndGet() == 2;
        }
    }
}
