package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.ayx */
/* loaded from: classes3.dex */
public final class FlowableAmb<T> extends Flowable<T> {

    /* renamed from: b */
    final Publisher<? extends T>[] f17841b;

    /* renamed from: c */
    final Iterable<? extends Publisher<? extends T>> f17842c;

    public FlowableAmb(Publisher<? extends T>[] dbwVarArr, Iterable<? extends Publisher<? extends T>> iterable) {
        this.f17841b = dbwVarArr;
        this.f17842c = iterable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        int i;
        Publisher<? extends T>[] dbwVarArr = this.f17841b;
        if (dbwVarArr == null) {
            dbwVarArr = new Publisher[8];
            try {
                i = 0;
                for (Publisher<? extends T> dbwVar : this.f17842c) {
                    if (dbwVar == null) {
                        EmptySubscription.error(new NullPointerException("One of the sources is null"), dbxVar);
                        return;
                    }
                    if (i == dbwVarArr.length) {
                        Publisher<? extends T>[] dbwVarArr2 = new Publisher[(i >> 2) + i];
                        System.arraycopy(dbwVarArr, 0, dbwVarArr2, 0, i);
                        dbwVarArr = dbwVarArr2;
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
            EmptySubscription.complete(dbxVar);
        } else if (i == 1) {
            dbwVarArr[0].subscribe(dbxVar);
        } else {
            new C3986a(dbxVar, i).m9821a(dbwVarArr);
        }
    }

    /* compiled from: FlowableAmb.java */
    /* renamed from: z1.ayx$a */
    /* loaded from: classes3.dex */
    static final class C3986a<T> implements dby {

        /* renamed from: a */
        final Subscriber<? super T> f17843a;

        /* renamed from: b */
        final C3987b<T>[] f17844b;

        /* renamed from: c */
        final AtomicInteger f17845c = new AtomicInteger();

        C3986a(Subscriber<? super T> dbxVar, int i) {
            this.f17843a = dbxVar;
            this.f17844b = new C3987b[i];
        }

        /* renamed from: a */
        public void m9821a(Publisher<? extends T>[] dbwVarArr) {
            C3987b<T>[] bVarArr = this.f17844b;
            int length = bVarArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                bVarArr[i] = new C3987b<>(this, i2, this.f17843a);
                i = i2;
            }
            this.f17845c.lazySet(0);
            this.f17843a.onSubscribe(this);
            for (int i3 = 0; i3 < length && this.f17845c.get() == 0; i3++) {
                dbwVarArr[i3].subscribe(bVarArr[i3]);
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                int i = this.f17845c.get();
                if (i > 0) {
                    this.f17844b[i - 1].request(j);
                } else if (i == 0) {
                    for (C3987b<T> bVar : this.f17844b) {
                        bVar.request(j);
                    }
                }
            }
        }

        /* renamed from: a */
        public boolean m9822a(int i) {
            int i2 = 0;
            if (this.f17845c.get() != 0 || !this.f17845c.compareAndSet(0, i)) {
                return false;
            }
            C3987b<T>[] bVarArr = this.f17844b;
            int length = bVarArr.length;
            while (i2 < length) {
                int i3 = i2 + 1;
                if (i3 != i) {
                    bVarArr[i2].cancel();
                }
                i2 = i3;
            }
            return true;
        }

        @Override // p110z1.dby
        public void cancel() {
            if (this.f17845c.get() != -1) {
                this.f17845c.lazySet(-1);
                for (C3987b<T> bVar : this.f17844b) {
                    bVar.cancel();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableAmb.java */
    /* renamed from: z1.ayx$b */
    /* loaded from: classes3.dex */
    public static final class C3987b<T> extends AtomicReference<dby> implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -1185974347409665484L;
        final Subscriber<? super T> downstream;
        final int index;
        final AtomicLong missedRequested = new AtomicLong();
        final C3986a<T> parent;
        boolean won;

        C3987b(C3986a<T> aVar, int i, Subscriber<? super T> dbxVar) {
            this.parent = aVar;
            this.index = i;
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this, this.missedRequested, dbyVar);
        }

        @Override // p110z1.dby
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this, this.missedRequested, j);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (this.won) {
                this.downstream.onNext(t);
            } else if (this.parent.m9822a(this.index)) {
                this.won = true;
                this.downstream.onNext(t);
            } else {
                get().cancel();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.won) {
                this.downstream.onError(th);
            } else if (this.parent.m9822a(this.index)) {
                this.won = true;
                this.downstream.onError(th);
            } else {
                get().cancel();
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.won) {
                this.downstream.onComplete();
            } else if (this.parent.m9822a(this.index)) {
                this.won = true;
                this.downstream.onComplete();
            } else {
                get().cancel();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }
}
