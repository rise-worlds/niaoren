package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.axa */
/* loaded from: classes3.dex */
public final class CompletableCache extends Completable implements CompletableObserver {

    /* renamed from: a */
    static final C3943a[] f17685a = new C3943a[0];

    /* renamed from: b */
    static final C3943a[] f17686b = new C3943a[0];

    /* renamed from: c */
    final CompletableSource f17687c;

    /* renamed from: d */
    final AtomicReference<C3943a[]> f17688d = new AtomicReference<>(f17685a);

    /* renamed from: e */
    final AtomicBoolean f17689e = new AtomicBoolean();

    /* renamed from: f */
    Throwable f17690f;

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
    }

    public CompletableCache(CompletableSource arsVar) {
        this.f17687c = arsVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        C3943a aVar = new C3943a(arpVar);
        arpVar.onSubscribe(aVar);
        if (m9839a(aVar)) {
            if (aVar.isDisposed()) {
                m9838b(aVar);
            }
            if (this.f17689e.compareAndSet(false, true)) {
                this.f17687c.mo11309a(this);
                return;
            }
            return;
        }
        Throwable th = this.f17690f;
        if (th != null) {
            arpVar.onError(th);
        } else {
            arpVar.onComplete();
        }
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        C3943a[] andSet;
        this.f17690f = th;
        for (C3943a aVar : this.f17688d.getAndSet(f17686b)) {
            if (!aVar.get()) {
                aVar.downstream.onError(th);
            }
        }
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
    public void onComplete() {
        C3943a[] andSet;
        for (C3943a aVar : this.f17688d.getAndSet(f17686b)) {
            if (!aVar.get()) {
                aVar.downstream.onComplete();
            }
        }
    }

    /* renamed from: a */
    boolean m9839a(C3943a aVar) {
        C3943a[] aVarArr;
        C3943a[] aVarArr2;
        do {
            aVarArr = this.f17688d.get();
            if (aVarArr == f17686b) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C3943a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f17688d.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9838b(C3943a aVar) {
        C3943a[] aVarArr;
        C3943a[] aVarArr2;
        do {
            aVarArr = this.f17688d.get();
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
                        aVarArr2 = f17685a;
                    } else {
                        C3943a[] aVarArr3 = new C3943a[length - 1];
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
        } while (!this.f17688d.compareAndSet(aVarArr, aVarArr2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CompletableCache.java */
    /* renamed from: z1.axa$a */
    /* loaded from: classes3.dex */
    public final class C3943a extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 8943152917179642732L;
        final CompletableObserver downstream;

        C3943a(CompletableObserver arpVar) {
            this.downstream = arpVar;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (compareAndSet(false, true)) {
                CompletableCache.this.m9838b(this);
            }
        }
    }
}
