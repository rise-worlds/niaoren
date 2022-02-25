package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.but */
/* loaded from: classes3.dex */
public final class CompletableSubject extends Completable implements CompletableObserver {

    /* renamed from: b */
    static final C4785a[] f20290b = new C4785a[0];

    /* renamed from: c */
    static final C4785a[] f20291c = new C4785a[0];

    /* renamed from: e */
    Throwable f20294e;

    /* renamed from: d */
    final AtomicBoolean f20293d = new AtomicBoolean();

    /* renamed from: a */
    final AtomicReference<C4785a[]> f20292a = new AtomicReference<>(f20290b);

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: q */
    public static CompletableSubject m8999q() {
        return new CompletableSubject();
    }

    CompletableSubject() {
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        if (this.f20292a.get() == f20291c) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        ObjectHelper.m9873a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f20293d.compareAndSet(false, true)) {
            this.f20294e = th;
            for (C4785a aVar : this.f20292a.getAndSet(f20291c)) {
                aVar.downstream.onError(th);
            }
            return;
        }
        RxJavaPlugins.m9212a(th);
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
    public void onComplete() {
        if (this.f20293d.compareAndSet(false, true)) {
            for (C4785a aVar : this.f20292a.getAndSet(f20291c)) {
                aVar.downstream.onComplete();
            }
        }
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        C4785a aVar = new C4785a(arpVar, this);
        arpVar.onSubscribe(aVar);
        if (!m9002a(aVar)) {
            Throwable th = this.f20294e;
            if (th != null) {
                arpVar.onError(th);
            } else {
                arpVar.onComplete();
            }
        } else if (aVar.isDisposed()) {
            m9000b(aVar);
        }
    }

    /* renamed from: a */
    boolean m9002a(C4785a aVar) {
        C4785a[] aVarArr;
        C4785a[] aVarArr2;
        do {
            aVarArr = this.f20292a.get();
            if (aVarArr == f20291c) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C4785a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.f20292a.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* renamed from: b */
    void m9000b(C4785a aVar) {
        C4785a[] aVarArr;
        C4785a[] aVarArr2;
        do {
            aVarArr = this.f20292a.get();
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
                        aVarArr2 = f20290b;
                    } else {
                        C4785a[] aVarArr3 = new C4785a[length - 1];
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
        } while (!this.f20292a.compareAndSet(aVarArr, aVarArr2));
    }

    @atn
    /* renamed from: r */
    public Throwable m8998r() {
        if (this.f20292a.get() == f20291c) {
            return this.f20294e;
        }
        return null;
    }

    /* renamed from: s */
    public boolean m8997s() {
        return this.f20292a.get() == f20291c && this.f20294e != null;
    }

    /* renamed from: t */
    public boolean m8996t() {
        return this.f20292a.get() == f20291c && this.f20294e == null;
    }

    /* renamed from: u */
    public boolean m8995u() {
        return this.f20292a.get().length != 0;
    }

    /* renamed from: v */
    int m8994v() {
        return this.f20292a.get().length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CompletableSubject.java */
    /* renamed from: z1.but$a */
    /* loaded from: classes3.dex */
    public static final class C4785a extends AtomicReference<CompletableSubject> implements Disposable {
        private static final long serialVersionUID = -7650903191002190468L;
        final CompletableObserver downstream;

        C4785a(CompletableObserver arpVar, CompletableSubject butVar) {
            this.downstream = arpVar;
            lazySet(butVar);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            CompletableSubject andSet = getAndSet(null);
            if (andSet != null) {
                andSet.m9000b(this);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
