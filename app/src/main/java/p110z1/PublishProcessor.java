package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.buj */
/* loaded from: classes3.dex */
public final class PublishProcessor<T> extends FlowableProcessor<T> {

    /* renamed from: b */
    static final C4763a[] f20200b = new C4763a[0];

    /* renamed from: c */
    static final C4763a[] f20201c = new C4763a[0];

    /* renamed from: d */
    final AtomicReference<C4763a<T>[]> f20202d = new AtomicReference<>(f20201c);

    /* renamed from: e */
    Throwable f20203e;

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: T */
    public static <T> PublishProcessor<T> m9100T() {
        return new PublishProcessor<>();
    }

    PublishProcessor() {
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4763a<T> aVar = new C4763a<>(dbxVar, this);
        dbxVar.onSubscribe(aVar);
        if (!m9099a((C4763a) aVar)) {
            Throwable th = this.f20203e;
            if (th != null) {
                dbxVar.onError(th);
            } else {
                dbxVar.onComplete();
            }
        } else if (aVar.isCancelled()) {
            m9098b((C4763a) aVar);
        }
    }

    /* renamed from: a */
    boolean m9099a(C4763a<T> aVar) {
        C4763a<T>[] aVarArr;
        C4763a<T>[] aVarArr2;
        do {
            aVarArr = this.f20202d.get();
            if (aVarArr == f20200b) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4763a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20202d.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9098b(C4763a<T> aVar) {
        C4763a<T>[] aVarArr;
        C4763a<T>[] aVarArr2;
        do {
            aVarArr = this.f20202d.get();
            if (aVarArr != f20200b && aVarArr != f20201c) {
                int length = aVarArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (aVarArr[i2] == aVar) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        aVarArr2 = f20201c;
                    } else {
                        C4763a<T>[] aVarArr3 = new C4763a[length - 1];
                        System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                        System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                        aVarArr2 = aVarArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.f20202d.compareAndSet(aVarArr, aVarArr2));
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (this.f20202d.get() == f20200b) {
            dbyVar.cancel();
        } else {
            dbyVar.request(cjm.f20759b);
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        for (C4763a<T> aVar : this.f20202d.get()) {
            aVar.onNext(t);
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        C4763a<T>[] aVarArr = this.f20202d.get();
        C4763a<T>[] aVarArr2 = f20200b;
        if (aVarArr == aVarArr2) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20203e = th;
        for (C4763a<T> aVar : this.f20202d.getAndSet(aVarArr2)) {
            aVar.onError(th);
        }
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        C4763a<T>[] aVarArr = this.f20202d.get();
        C4763a<T>[] aVarArr2 = f20200b;
        if (aVarArr != aVarArr2) {
            for (C4763a<T> aVar : this.f20202d.getAndSet(aVarArr2)) {
                aVar.onComplete();
            }
        }
    }

    /* renamed from: m */
    public boolean m9097m(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return true;
        }
        C4763a<T>[] aVarArr = this.f20202d.get();
        for (C4763a<T> aVar : aVarArr) {
            if (aVar.isFull()) {
                return false;
            }
        }
        for (C4763a<T> aVar2 : aVarArr) {
            aVar2.onNext(t);
        }
        return true;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: U */
    public boolean mo9064U() {
        return this.f20202d.get().length != 0;
    }

    @Override // p110z1.FlowableProcessor
    @atn
    /* renamed from: X */
    public Throwable mo9061X() {
        if (this.f20202d.get() == f20200b) {
            return this.f20203e;
        }
        return null;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: V */
    public boolean mo9063V() {
        return this.f20202d.get() == f20200b && this.f20203e != null;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: W */
    public boolean mo9062W() {
        return this.f20202d.get() == f20200b && this.f20203e == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PublishProcessor.java */
    /* renamed from: z1.buj$a */
    /* loaded from: classes3.dex */
    public static final class C4763a<T> extends AtomicLong implements dby {
        private static final long serialVersionUID = 3562861878281475070L;
        final Subscriber<? super T> downstream;
        final PublishProcessor<T> parent;

        C4763a(Subscriber<? super T> dbxVar, PublishProcessor<T> bujVar) {
            this.downstream = dbxVar;
            this.parent = bujVar;
        }

        public void onNext(T t) {
            long j = get();
            if (j != Long.MIN_VALUE) {
                if (j != 0) {
                    this.downstream.onNext(t);
                    BackpressureHelper.m9445d(this, 1L);
                    return;
                }
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
            }
        }

        public void onError(Throwable th) {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        public void onComplete() {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9447b(this, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.m9098b((C4763a) this);
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }

        boolean isFull() {
            return get() == 0;
        }
    }
}
