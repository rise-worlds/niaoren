package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bok */
/* loaded from: classes3.dex */
public final class SingleCache<T> extends Single<T> implements SingleObserver<T> {

    /* renamed from: a */
    static final C4653a[] f19690a = new C4653a[0];

    /* renamed from: b */
    static final C4653a[] f19691b = new C4653a[0];

    /* renamed from: c */
    final SingleSource<? extends T> f19692c;

    /* renamed from: d */
    final AtomicInteger f19693d = new AtomicInteger();

    /* renamed from: e */
    final AtomicReference<C4653a<T>[]> f19694e = new AtomicReference<>(f19690a);

    /* renamed from: f */
    T f19695f;

    /* renamed from: g */
    Throwable f19696g;

    @Override // p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
    }

    public SingleCache(SingleSource<? extends T> ataVar) {
        this.f19692c = ataVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        C4653a<T> aVar = new C4653a<>(asxVar, this);
        asxVar.onSubscribe(aVar);
        if (m9551a((C4653a) aVar)) {
            if (aVar.isDisposed()) {
                m9550b((C4653a) aVar);
            }
            if (this.f19693d.getAndIncrement() == 0) {
                this.f19692c.mo10018a(this);
                return;
            }
            return;
        }
        Throwable th = this.f19696g;
        if (th != null) {
            asxVar.onError(th);
        } else {
            asxVar.onSuccess((T) this.f19695f);
        }
    }

    /* renamed from: a */
    boolean m9551a(C4653a<T> aVar) {
        C4653a<T>[] aVarArr;
        C4653a<T>[] aVarArr2;
        do {
            aVarArr = this.f19694e.get();
            if (aVarArr == f19691b) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4653a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f19694e.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9550b(C4653a<T> aVar) {
        C4653a<T>[] aVarArr;
        C4653a<T>[] aVarArr2;
        do {
            aVarArr = this.f19694e.get();
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
                        aVarArr2 = f19690a;
                    } else {
                        C4653a<T>[] aVarArr3 = new C4653a[length - 1];
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
        } while (!this.f19694e.compareAndSet(aVarArr, aVarArr2));
    }

    @Override // p110z1.SingleObserver
    public void onSuccess(T t) {
        C4653a<T>[] andSet;
        this.f19695f = t;
        for (C4653a<T> aVar : this.f19694e.getAndSet(f19691b)) {
            if (!aVar.isDisposed()) {
                aVar.downstream.onSuccess(t);
            }
        }
    }

    @Override // p110z1.SingleObserver
    public void onError(Throwable th) {
        C4653a<T>[] andSet;
        this.f19696g = th;
        for (C4653a<T> aVar : this.f19694e.getAndSet(f19691b)) {
            if (!aVar.isDisposed()) {
                aVar.downstream.onError(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SingleCache.java */
    /* renamed from: z1.bok$a */
    /* loaded from: classes3.dex */
    public static final class C4653a<T> extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 7514387411091976596L;
        final SingleObserver<? super T> downstream;
        final SingleCache<T> parent;

        C4653a(SingleObserver<? super T> asxVar, SingleCache<T> bokVar) {
            this.downstream = asxVar;
            this.parent = bokVar;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.m9550b((C4653a) this);
            }
        }
    }
}
