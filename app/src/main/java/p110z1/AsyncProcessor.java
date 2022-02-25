package p110z1;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.buf */
/* loaded from: classes3.dex */
public final class AsyncProcessor<T> extends FlowableProcessor<T> {

    /* renamed from: b */
    static final C4760a[] f20171b = new C4760a[0];

    /* renamed from: c */
    static final C4760a[] f20172c = new C4760a[0];

    /* renamed from: d */
    final AtomicReference<C4760a<T>[]> f20173d = new AtomicReference<>(f20171b);

    /* renamed from: e */
    Throwable f20174e;

    /* renamed from: f */
    T f20175f;

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: T */
    public static <T> AsyncProcessor<T> m9130T() {
        return new AsyncProcessor<>();
    }

    AsyncProcessor() {
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (this.f20173d.get() == f20172c) {
            dbyVar.cancel();
        } else {
            dbyVar.request(cjm.f20759b);
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20173d.get() != f20172c) {
            this.f20175f = t;
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        C4760a<T>[] aVarArr = this.f20173d.get();
        C4760a<T>[] aVarArr2 = f20172c;
        if (aVarArr == aVarArr2) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20175f = null;
        this.f20174e = th;
        for (C4760a<T> aVar : this.f20173d.getAndSet(aVarArr2)) {
            aVar.onError(th);
        }
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        C4760a<T>[] aVarArr = this.f20173d.get();
        C4760a<T>[] aVarArr2 = f20172c;
        if (aVarArr != aVarArr2) {
            T t = this.f20175f;
            C4760a<T>[] andSet = this.f20173d.getAndSet(aVarArr2);
            int i = 0;
            if (t == null) {
                int length = andSet.length;
                while (i < length) {
                    andSet[i].onComplete();
                    i++;
                }
                return;
            }
            int length2 = andSet.length;
            while (i < length2) {
                andSet[i].complete(t);
                i++;
            }
        }
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: U */
    public boolean mo9064U() {
        return this.f20173d.get().length != 0;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: V */
    public boolean mo9063V() {
        return this.f20173d.get() == f20172c && this.f20174e != null;
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: W */
    public boolean mo9062W() {
        return this.f20173d.get() == f20172c && this.f20174e == null;
    }

    @Override // p110z1.FlowableProcessor
    @atn
    /* renamed from: X */
    public Throwable mo9061X() {
        if (this.f20173d.get() == f20172c) {
            return this.f20174e;
        }
        return null;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4760a<T> aVar = new C4760a<>(dbxVar, this);
        dbxVar.onSubscribe(aVar);
        if (!m9127a((C4760a) aVar)) {
            Throwable th = this.f20174e;
            if (th != null) {
                dbxVar.onError(th);
                return;
            }
            T t = this.f20175f;
            if (t != null) {
                aVar.complete(t);
            } else {
                aVar.onComplete();
            }
        } else if (aVar.isCancelled()) {
            m9125b((C4760a) aVar);
        }
    }

    /* renamed from: a */
    boolean m9127a(C4760a<T> aVar) {
        C4760a<T>[] aVarArr;
        C4760a<T>[] aVarArr2;
        do {
            aVarArr = this.f20173d.get();
            if (aVarArr == f20172c) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4760a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20173d.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9125b(C4760a<T> aVar) {
        C4760a<T>[] aVarArr;
        C4760a<T>[] aVarArr2;
        do {
            aVarArr = this.f20173d.get();
            int length = aVarArr.length;
            if (length != 0) {
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
                        aVarArr2 = f20171b;
                    } else {
                        C4760a<T>[] aVarArr3 = new C4760a[length - 1];
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
        } while (!this.f20173d.compareAndSet(aVarArr, aVarArr2));
    }

    /* renamed from: Y */
    public boolean m9129Y() {
        return this.f20173d.get() == f20172c && this.f20175f != null;
    }

    @atn
    /* renamed from: Z */
    public T m9128Z() {
        if (this.f20173d.get() == f20172c) {
            return this.f20175f;
        }
        return null;
    }

    @Deprecated
    /* renamed from: aa */
    public Object[] m9126aa() {
        T Z = m9128Z();
        return Z != null ? new Object[]{Z} : new Object[0];
    }

    @Deprecated
    /* renamed from: c */
    public T[] m9124c(T[] tArr) {
        T Z = m9128Z();
        if (Z == null) {
            if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        if (tArr.length == 0) {
            tArr = (T[]) Arrays.copyOf(tArr, 1);
        }
        tArr[0] = Z;
        if (tArr.length != 1) {
            tArr[1] = null;
        }
        return tArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AsyncProcessor.java */
    /* renamed from: z1.buf$a */
    /* loaded from: classes3.dex */
    public static final class C4760a<T> extends DeferredScalarSubscription<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        final AsyncProcessor<T> parent;

        C4760a(Subscriber<? super T> dbxVar, AsyncProcessor<T> bufVar) {
            super(dbxVar);
            this.parent = bufVar;
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            if (super.tryCancel()) {
                this.parent.m9125b((C4760a) this);
            }
        }

        void onComplete() {
            if (!isCancelled()) {
                this.downstream.onComplete();
            }
        }

        void onError(Throwable th) {
            if (isCancelled()) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.downstream.onError(th);
            }
        }
    }
}
