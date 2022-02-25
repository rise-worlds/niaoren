package p110z1;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bur */
/* loaded from: classes3.dex */
public final class AsyncSubject<T> extends Subject<T> {

    /* renamed from: a */
    static final C4783a[] f20267a = new C4783a[0];

    /* renamed from: b */
    static final C4783a[] f20268b = new C4783a[0];

    /* renamed from: c */
    final AtomicReference<C4783a<T>[]> f20269c = new AtomicReference<>(f20267a);

    /* renamed from: d */
    Throwable f20270d;

    /* renamed from: e */
    T f20271e;

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> AsyncSubject<T> m9020a() {
        return new AsyncSubject<>();
    }

    AsyncSubject() {
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        if (this.f20269c.get() == f20268b) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20269c.get() != f20268b) {
            this.f20271e = t;
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        C4783a<T>[] aVarArr = this.f20269c.get();
        C4783a<T>[] aVarArr2 = f20268b;
        if (aVarArr == aVarArr2) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20271e = null;
        this.f20270d = th;
        for (C4783a<T> aVar : this.f20269c.getAndSet(aVarArr2)) {
            aVar.onError(th);
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        C4783a<T>[] aVarArr = this.f20269c.get();
        C4783a<T>[] aVarArr2 = f20268b;
        if (aVarArr != aVarArr2) {
            T t = this.f20271e;
            C4783a<T>[] andSet = this.f20269c.getAndSet(aVarArr2);
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

    @Override // p110z1.Subject
    /* renamed from: b */
    public boolean mo8939b() {
        return this.f20269c.get().length != 0;
    }

    @Override // p110z1.Subject
    /* renamed from: c */
    public boolean mo8936c() {
        return this.f20269c.get() == f20268b && this.f20270d != null;
    }

    @Override // p110z1.Subject
    /* renamed from: R */
    public boolean mo8947R() {
        return this.f20269c.get() == f20268b && this.f20270d == null;
    }

    @Override // p110z1.Subject
    /* renamed from: S */
    public Throwable mo8946S() {
        if (this.f20269c.get() == f20268b) {
            return this.f20270d;
        }
        return null;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        C4783a<T> aVar = new C4783a<>(assVar, this);
        assVar.onSubscribe(aVar);
        if (!m9019a((C4783a) aVar)) {
            Throwable th = this.f20270d;
            if (th != null) {
                assVar.onError(th);
                return;
            }
            T t = this.f20271e;
            if (t != null) {
                aVar.complete(t);
            } else {
                aVar.onComplete();
            }
        } else if (aVar.isDisposed()) {
            m9018b((C4783a) aVar);
        }
    }

    /* renamed from: a */
    boolean m9019a(C4783a<T> aVar) {
        C4783a<T>[] aVarArr;
        C4783a<T>[] aVarArr2;
        do {
            aVarArr = this.f20269c.get();
            if (aVarArr == f20268b) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4783a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20269c.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9018b(C4783a<T> aVar) {
        C4783a<T>[] aVarArr;
        C4783a<T>[] aVarArr2;
        do {
            aVarArr = this.f20269c.get();
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
                        aVarArr2 = f20267a;
                    } else {
                        C4783a<T>[] aVarArr3 = new C4783a[length - 1];
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
        } while (!this.f20269c.compareAndSet(aVarArr, aVarArr2));
    }

    /* renamed from: T */
    public boolean m9023T() {
        return this.f20269c.get() == f20268b && this.f20271e != null;
    }

    @atn
    /* renamed from: U */
    public T m9022U() {
        if (this.f20269c.get() == f20268b) {
            return this.f20271e;
        }
        return null;
    }

    @Deprecated
    /* renamed from: V */
    public Object[] m9021V() {
        T U = m9022U();
        return U != null ? new Object[]{U} : new Object[0];
    }

    @Deprecated
    /* renamed from: c */
    public T[] m9017c(T[] tArr) {
        T U = m9022U();
        if (U == null) {
            if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        if (tArr.length == 0) {
            tArr = (T[]) Arrays.copyOf(tArr, 1);
        }
        tArr[0] = U;
        if (tArr.length != 1) {
            tArr[1] = null;
        }
        return tArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AsyncSubject.java */
    /* renamed from: z1.bur$a */
    /* loaded from: classes3.dex */
    public static final class C4783a<T> extends DeferredScalarDisposable<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        final AsyncSubject<T> parent;

        C4783a(Observer<? super T> assVar, AsyncSubject<T> burVar) {
            super(assVar);
            this.parent = burVar;
        }

        @Override // p110z1.DeferredScalarDisposable, p110z1.Disposable
        public void dispose() {
            if (super.tryDispose()) {
                this.parent.m9018b((C4783a) this);
            }
        }

        void onComplete() {
            if (!isDisposed()) {
                this.downstream.onComplete();
            }
        }

        void onError(Throwable th) {
            if (isDisposed()) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.downstream.onError(th);
            }
        }
    }
}
