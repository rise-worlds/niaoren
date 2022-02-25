package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.awy */
/* loaded from: classes3.dex */
public final class CompletableAmb extends Completable {

    /* renamed from: a */
    private final CompletableSource[] f17672a;

    /* renamed from: b */
    private final Iterable<? extends CompletableSource> f17673b;

    public CompletableAmb(CompletableSource[] arsVarArr, Iterable<? extends CompletableSource> iterable) {
        this.f17672a = arsVarArr;
        this.f17673b = iterable;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        int i;
        CompletableSource[] arsVarArr = this.f17672a;
        if (arsVarArr == null) {
            arsVarArr = new CompletableSource[8];
            try {
                i = 0;
                for (CompletableSource arsVar : this.f17673b) {
                    if (arsVar == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), arpVar);
                        return;
                    }
                    if (i == arsVarArr.length) {
                        CompletableSource[] arsVarArr2 = new CompletableSource[(i >> 2) + i];
                        System.arraycopy(arsVarArr, 0, arsVarArr2, 0, i);
                        arsVarArr = arsVarArr2;
                    }
                    i++;
                    arsVarArr[i] = arsVar;
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                EmptyDisposable.error(th, arpVar);
                return;
            }
        } else {
            i = arsVarArr.length;
        }
        CompositeDisposable atqVar = new CompositeDisposable();
        arpVar.onSubscribe(atqVar);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        for (int i2 = 0; i2 < i; i2++) {
            CompletableSource arsVar2 = arsVarArr[i2];
            if (!atqVar.isDisposed()) {
                if (arsVar2 == null) {
                    Throwable nullPointerException = new NullPointerException("One of the sources is null");
                    if (atomicBoolean.compareAndSet(false, true)) {
                        atqVar.dispose();
                        arpVar.onError(nullPointerException);
                        return;
                    }
                    RxJavaPlugins.m9212a(nullPointerException);
                    return;
                }
                arsVar2.mo11309a(new C3939a(atomicBoolean, atqVar, arpVar));
            } else {
                return;
            }
        }
        if (i == 0) {
            arpVar.onComplete();
        }
    }

    /* compiled from: CompletableAmb.java */
    /* renamed from: z1.awy$a */
    /* loaded from: classes3.dex */
    static final class C3939a implements CompletableObserver {

        /* renamed from: a */
        final AtomicBoolean f17674a;

        /* renamed from: b */
        final CompositeDisposable f17675b;

        /* renamed from: c */
        final CompletableObserver f17676c;

        /* renamed from: d */
        Disposable f17677d;

        C3939a(AtomicBoolean atomicBoolean, CompositeDisposable atqVar, CompletableObserver arpVar) {
            this.f17674a = atomicBoolean;
            this.f17675b = atqVar;
            this.f17676c = arpVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            if (this.f17674a.compareAndSet(false, true)) {
                this.f17675b.mo9936c(this.f17677d);
                this.f17675b.dispose();
                this.f17676c.onComplete();
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.f17674a.compareAndSet(false, true)) {
                this.f17675b.mo9936c(this.f17677d);
                this.f17675b.dispose();
                this.f17676c.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f17677d = atrVar;
            this.f17675b.mo9939a(atrVar);
        }
    }
}
