package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.buv */
/* loaded from: classes3.dex */
public final class PublishSubject<T> extends Subject<T> {

    /* renamed from: a */
    static final C4787a[] f20301a = new C4787a[0];

    /* renamed from: b */
    static final C4787a[] f20302b = new C4787a[0];

    /* renamed from: c */
    final AtomicReference<C4787a<T>[]> f20303c = new AtomicReference<>(f20302b);

    /* renamed from: d */
    Throwable f20304d;

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> PublishSubject<T> m8982a() {
        return new PublishSubject<>();
    }

    PublishSubject() {
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        C4787a<T> aVar = new C4787a<>(assVar, this);
        assVar.onSubscribe(aVar);
        if (!m8981a((C4787a) aVar)) {
            Throwable th = this.f20304d;
            if (th != null) {
                assVar.onError(th);
            } else {
                assVar.onComplete();
            }
        } else if (aVar.isDisposed()) {
            m8980b((C4787a) aVar);
        }
    }

    /* renamed from: a */
    boolean m8981a(C4787a<T> aVar) {
        C4787a<T>[] aVarArr;
        C4787a<T>[] aVarArr2;
        do {
            aVarArr = this.f20303c.get();
            if (aVarArr == f20301a) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4787a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20303c.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m8980b(C4787a<T> aVar) {
        C4787a<T>[] aVarArr;
        C4787a<T>[] aVarArr2;
        do {
            aVarArr = this.f20303c.get();
            if (aVarArr != f20301a && aVarArr != f20302b) {
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
                        aVarArr2 = f20302b;
                    } else {
                        C4787a<T>[] aVarArr3 = new C4787a[length - 1];
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
        } while (!this.f20303c.compareAndSet(aVarArr, aVarArr2));
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        if (this.f20303c.get() == f20301a) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        ObjectHelper.m9873a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        for (C4787a<T> aVar : this.f20303c.get()) {
            aVar.onNext(t);
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        C4787a<T>[] aVarArr = this.f20303c.get();
        C4787a<T>[] aVarArr2 = f20301a;
        if (aVarArr == aVarArr2) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20304d = th;
        for (C4787a<T> aVar : this.f20303c.getAndSet(aVarArr2)) {
            aVar.onError(th);
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        C4787a<T>[] aVarArr = this.f20303c.get();
        C4787a<T>[] aVarArr2 = f20301a;
        if (aVarArr != aVarArr2) {
            for (C4787a<T> aVar : this.f20303c.getAndSet(aVarArr2)) {
                aVar.onComplete();
            }
        }
    }

    @Override // p110z1.Subject
    /* renamed from: b */
    public boolean mo8939b() {
        return this.f20303c.get().length != 0;
    }

    @Override // p110z1.Subject
    @atn
    /* renamed from: S */
    public Throwable mo8946S() {
        if (this.f20303c.get() == f20301a) {
            return this.f20304d;
        }
        return null;
    }

    @Override // p110z1.Subject
    /* renamed from: c */
    public boolean mo8936c() {
        return this.f20303c.get() == f20301a && this.f20304d != null;
    }

    @Override // p110z1.Subject
    /* renamed from: R */
    public boolean mo8947R() {
        return this.f20303c.get() == f20301a && this.f20304d == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PublishSubject.java */
    /* renamed from: z1.buv$a */
    /* loaded from: classes3.dex */
    public static final class C4787a<T> extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 3562861878281475070L;
        final Observer<? super T> downstream;
        final PublishSubject<T> parent;

        C4787a(Observer<? super T> assVar, PublishSubject<T> buvVar) {
            this.downstream = assVar;
            this.parent = buvVar;
        }

        public void onNext(T t) {
            if (!get()) {
                this.downstream.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (get()) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        public void onComplete() {
            if (!get()) {
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.m8980b((C4787a) this);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get();
        }
    }
}
