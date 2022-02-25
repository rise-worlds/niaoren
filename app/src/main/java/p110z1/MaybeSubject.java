package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.buu */
/* loaded from: classes3.dex */
public final class MaybeSubject<T> extends Maybe<T> implements MaybeObserver<T> {

    /* renamed from: b */
    static final C4786a[] f20295b = new C4786a[0];

    /* renamed from: c */
    static final C4786a[] f20296c = new C4786a[0];

    /* renamed from: e */
    T f20299e;

    /* renamed from: f */
    Throwable f20300f;

    /* renamed from: d */
    final AtomicBoolean f20298d = new AtomicBoolean();

    /* renamed from: a */
    final AtomicReference<C4786a<T>[]> f20297a = new AtomicReference<>(f20295b);

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: s */
    public static <T> MaybeSubject<T> m8990s() {
        return new MaybeSubject<>();
    }

    MaybeSubject() {
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        if (this.f20297a.get() == f20296c) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSuccess(T t) {
        ObjectHelper.m9873a((Object) t, "onSuccess called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20298d.compareAndSet(false, true)) {
            this.f20299e = t;
            for (C4786a<T> aVar : this.f20297a.getAndSet(f20296c)) {
                aVar.downstream.onSuccess(t);
            }
        }
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20298d.compareAndSet(false, true)) {
            this.f20300f = th;
            for (C4786a<T> aVar : this.f20297a.getAndSet(f20296c)) {
                aVar.downstream.onError(th);
            }
            return;
        }
        RxJavaPlugins.m9212a(th);
    }

    @Override // p110z1.MaybeObserver
    public void onComplete() {
        if (this.f20298d.compareAndSet(false, true)) {
            for (C4786a<T> aVar : this.f20297a.getAndSet(f20296c)) {
                aVar.downstream.onComplete();
            }
        }
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        C4786a<T> aVar = new C4786a<>(asfVar, this);
        asfVar.onSubscribe(aVar);
        if (!m8993a((C4786a) aVar)) {
            Throwable th = this.f20300f;
            if (th != null) {
                asfVar.onError(th);
                return;
            }
            Object obj = (T) this.f20299e;
            if (obj == null) {
                asfVar.onComplete();
            } else {
                asfVar.onSuccess(obj);
            }
        } else if (aVar.isDisposed()) {
            m8991b((C4786a) aVar);
        }
    }

    /* renamed from: a */
    boolean m8993a(C4786a<T> aVar) {
        C4786a<T>[] aVarArr;
        C4786a<T>[] aVarArr2;
        do {
            aVarArr = this.f20297a.get();
            if (aVarArr == f20296c) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4786a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20297a.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m8991b(C4786a<T> aVar) {
        C4786a<T>[] aVarArr;
        C4786a<T>[] aVarArr2;
        do {
            aVarArr = this.f20297a.get();
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
                        aVarArr2 = f20295b;
                    } else {
                        C4786a<T>[] aVarArr3 = new C4786a[length - 1];
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
        } while (!this.f20297a.compareAndSet(aVarArr, aVarArr2));
    }

    @atn
    /* renamed from: t */
    public T m8989t() {
        if (this.f20297a.get() == f20296c) {
            return this.f20299e;
        }
        return null;
    }

    /* renamed from: u */
    public boolean m8988u() {
        return this.f20297a.get() == f20296c && this.f20299e != null;
    }

    @atn
    /* renamed from: v */
    public Throwable m8987v() {
        if (this.f20297a.get() == f20296c) {
            return this.f20300f;
        }
        return null;
    }

    /* renamed from: w */
    public boolean m8986w() {
        return this.f20297a.get() == f20296c && this.f20300f != null;
    }

    /* renamed from: x */
    public boolean m8985x() {
        return this.f20297a.get() == f20296c && this.f20299e == null && this.f20300f == null;
    }

    /* renamed from: y */
    public boolean m8984y() {
        return this.f20297a.get().length != 0;
    }

    /* renamed from: z */
    int m8983z() {
        return this.f20297a.get().length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeSubject.java */
    /* renamed from: z1.buu$a */
    /* loaded from: classes3.dex */
    public static final class C4786a<T> extends AtomicReference<MaybeSubject<T>> implements Disposable {
        private static final long serialVersionUID = -7650903191002190468L;
        final MaybeObserver<? super T> downstream;

        C4786a(MaybeObserver<? super T> asfVar, MaybeSubject<T> buuVar) {
            this.downstream = asfVar;
            lazySet(buuVar);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            MaybeSubject<T> andSet = getAndSet(null);
            if (andSet != null) {
                andSet.m8991b((C4786a) this);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
