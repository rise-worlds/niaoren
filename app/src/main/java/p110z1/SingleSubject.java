package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.buy */
/* loaded from: classes3.dex */
public final class SingleSubject<T> extends Single<T> implements SingleObserver<T> {

    /* renamed from: b */
    static final C4795a[] f20315b = new C4795a[0];

    /* renamed from: c */
    static final C4795a[] f20316c = new C4795a[0];

    /* renamed from: e */
    T f20319e;

    /* renamed from: f */
    Throwable f20320f;

    /* renamed from: d */
    final AtomicBoolean f20318d = new AtomicBoolean();

    /* renamed from: a */
    final AtomicReference<C4795a<T>[]> f20317a = new AtomicReference<>(f20315b);

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: q */
    public static <T> SingleSubject<T> m8959q() {
        return new SingleSubject<>();
    }

    SingleSubject() {
    }

    @Override // p110z1.SingleObserver
    public void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (this.f20317a.get() == f20316c) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.SingleObserver
    public void onSuccess(@AbstractC3889atm T t) {
        ObjectHelper.m9873a((Object) t, "onSuccess called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20318d.compareAndSet(false, true)) {
            this.f20319e = t;
            for (C4795a<T> aVar : this.f20317a.getAndSet(f20316c)) {
                aVar.downstream.onSuccess(t);
            }
        }
    }

    @Override // p110z1.SingleObserver
    public void onError(@AbstractC3889atm Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20318d.compareAndSet(false, true)) {
            this.f20320f = th;
            for (C4795a<T> aVar : this.f20317a.getAndSet(f20316c)) {
                aVar.downstream.onError(th);
            }
            return;
        }
        RxJavaPlugins.m9212a(th);
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(@AbstractC3889atm SingleObserver<? super T> asxVar) {
        C4795a<T> aVar = new C4795a<>(asxVar, this);
        asxVar.onSubscribe(aVar);
        if (!m8962a((C4795a) aVar)) {
            Throwable th = this.f20320f;
            if (th != null) {
                asxVar.onError(th);
            } else {
                asxVar.onSuccess((T) this.f20319e);
            }
        } else if (aVar.isDisposed()) {
            m8960b((C4795a) aVar);
        }
    }

    /* renamed from: a */
    boolean m8962a(@AbstractC3889atm C4795a<T> aVar) {
        C4795a<T>[] aVarArr;
        C4795a<T>[] aVarArr2;
        do {
            aVarArr = this.f20317a.get();
            if (aVarArr == f20316c) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4795a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20317a.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m8960b(@AbstractC3889atm C4795a<T> aVar) {
        C4795a<T>[] aVarArr;
        C4795a<T>[] aVarArr2;
        do {
            aVarArr = this.f20317a.get();
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
                        aVarArr2 = f20315b;
                    } else {
                        C4795a<T>[] aVarArr3 = new C4795a[length - 1];
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
        } while (!this.f20317a.compareAndSet(aVarArr, aVarArr2));
    }

    @atn
    /* renamed from: r */
    public T m8958r() {
        if (this.f20317a.get() == f20316c) {
            return this.f20319e;
        }
        return null;
    }

    /* renamed from: s */
    public boolean m8957s() {
        return this.f20317a.get() == f20316c && this.f20319e != null;
    }

    @atn
    /* renamed from: t */
    public Throwable m8956t() {
        if (this.f20317a.get() == f20316c) {
            return this.f20320f;
        }
        return null;
    }

    /* renamed from: u */
    public boolean m8955u() {
        return this.f20317a.get() == f20316c && this.f20320f != null;
    }

    /* renamed from: v */
    public boolean m8954v() {
        return this.f20317a.get().length != 0;
    }

    /* renamed from: w */
    int m8953w() {
        return this.f20317a.get().length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SingleSubject.java */
    /* renamed from: z1.buy$a */
    /* loaded from: classes3.dex */
    public static final class C4795a<T> extends AtomicReference<SingleSubject<T>> implements Disposable {
        private static final long serialVersionUID = -7650903191002190468L;
        final SingleObserver<? super T> downstream;

        C4795a(SingleObserver<? super T> asxVar, SingleSubject<T> buyVar) {
            this.downstream = asxVar;
            lazySet(buyVar);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            SingleSubject<T> andSet = getAndSet(null);
            if (andSet != null) {
                andSet.m8960b((C4795a) this);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
