package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bet */
/* loaded from: classes3.dex */
public final class MaybeCache<T> extends Maybe<T> implements MaybeObserver<T> {

    /* renamed from: a */
    static final C4262a[] f18536a = new C4262a[0];

    /* renamed from: b */
    static final C4262a[] f18537b = new C4262a[0];

    /* renamed from: c */
    final AtomicReference<MaybeSource<T>> f18538c;

    /* renamed from: d */
    final AtomicReference<C4262a<T>[]> f18539d = new AtomicReference<>(f18536a);

    /* renamed from: e */
    T f18540e;

    /* renamed from: f */
    Throwable f18541f;

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
    }

    public MaybeCache(MaybeSource<T> asiVar) {
        this.f18538c = new AtomicReference<>(asiVar);
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        C4262a<T> aVar = new C4262a<>(asfVar, this);
        asfVar.onSubscribe(aVar);
        if (m9711a((C4262a) aVar)) {
            if (aVar.isDisposed()) {
                m9710b((C4262a) aVar);
                return;
            }
            MaybeSource<T> andSet = this.f18538c.getAndSet(null);
            if (andSet != null) {
                andSet.mo10646a(this);
            }
        } else if (!aVar.isDisposed()) {
            Throwable th = this.f18541f;
            if (th != null) {
                asfVar.onError(th);
                return;
            }
            Object obj = (T) this.f18540e;
            if (obj != null) {
                asfVar.onSuccess(obj);
            } else {
                asfVar.onComplete();
            }
        }
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSuccess(T t) {
        C4262a<T>[] andSet;
        this.f18540e = t;
        for (C4262a<T> aVar : this.f18539d.getAndSet(f18537b)) {
            if (!aVar.isDisposed()) {
                aVar.downstream.onSuccess(t);
            }
        }
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        C4262a<T>[] andSet;
        this.f18541f = th;
        for (C4262a<T> aVar : this.f18539d.getAndSet(f18537b)) {
            if (!aVar.isDisposed()) {
                aVar.downstream.onError(th);
            }
        }
    }

    @Override // p110z1.MaybeObserver
    public void onComplete() {
        C4262a<T>[] andSet;
        for (C4262a<T> aVar : this.f18539d.getAndSet(f18537b)) {
            if (!aVar.isDisposed()) {
                aVar.downstream.onComplete();
            }
        }
    }

    /* renamed from: a */
    boolean m9711a(C4262a<T> aVar) {
        C4262a<T>[] aVarArr;
        C4262a<T>[] aVarArr2;
        do {
            aVarArr = this.f18539d.get();
            if (aVarArr == f18537b) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4262a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f18539d.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9710b(C4262a<T> aVar) {
        C4262a<T>[] aVarArr;
        C4262a<T>[] aVarArr2;
        do {
            aVarArr = this.f18539d.get();
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
                        aVarArr2 = f18536a;
                    } else {
                        C4262a<T>[] aVarArr3 = new C4262a[length - 1];
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
        } while (!this.f18539d.compareAndSet(aVarArr, aVarArr2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeCache.java */
    /* renamed from: z1.bet$a */
    /* loaded from: classes3.dex */
    public static final class C4262a<T> extends AtomicReference<MaybeCache<T>> implements Disposable {
        private static final long serialVersionUID = -5791853038359966195L;
        final MaybeObserver<? super T> downstream;

        C4262a(MaybeObserver<? super T> asfVar, MaybeCache<T> betVar) {
            super(betVar);
            this.downstream = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            MaybeCache<T> andSet = getAndSet(null);
            if (andSet != null) {
                andSet.m9710b((C4262a) this);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
