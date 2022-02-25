package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.boj */
/* loaded from: classes3.dex */
public final class SingleAmb<T> extends Single<T> {

    /* renamed from: a */
    private final SingleSource<? extends T>[] f19684a;

    /* renamed from: b */
    private final Iterable<? extends SingleSource<? extends T>> f19685b;

    public SingleAmb(SingleSource<? extends T>[] ataVarArr, Iterable<? extends SingleSource<? extends T>> iterable) {
        this.f19684a = ataVarArr;
        this.f19685b = iterable;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        int i;
        SingleSource<? extends T>[] ataVarArr = this.f19684a;
        if (ataVarArr == null) {
            ataVarArr = new SingleSource[8];
            try {
                i = 0;
                for (SingleSource<? extends T> ataVar : this.f19685b) {
                    if (ataVar == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), asxVar);
                        return;
                    }
                    if (i == ataVarArr.length) {
                        SingleSource<? extends T>[] ataVarArr2 = new SingleSource[(i >> 2) + i];
                        System.arraycopy(ataVarArr, 0, ataVarArr2, 0, i);
                        ataVarArr = ataVarArr2;
                    }
                    i++;
                    ataVarArr[i] = ataVar;
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                EmptyDisposable.error(th, asxVar);
                return;
            }
        } else {
            i = ataVarArr.length;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        CompositeDisposable atqVar = new CompositeDisposable();
        asxVar.onSubscribe(atqVar);
        for (int i2 = 0; i2 < i; i2++) {
            SingleSource<? extends T> ataVar2 = ataVarArr[i2];
            if (atqVar.isDisposed()) {
                return;
            }
            if (ataVar2 == null) {
                atqVar.dispose();
                NullPointerException nullPointerException = new NullPointerException("One of the sources is null");
                if (atomicBoolean.compareAndSet(false, true)) {
                    asxVar.onError(nullPointerException);
                    return;
                } else {
                    RxJavaPlugins.m9212a(nullPointerException);
                    return;
                }
            } else {
                ataVar2.mo10018a(new C4652a(asxVar, atqVar, atomicBoolean));
            }
        }
    }

    /* compiled from: SingleAmb.java */
    /* renamed from: z1.boj$a */
    /* loaded from: classes3.dex */
    static final class C4652a<T> implements SingleObserver<T> {

        /* renamed from: a */
        final CompositeDisposable f19686a;

        /* renamed from: b */
        final SingleObserver<? super T> f19687b;

        /* renamed from: c */
        final AtomicBoolean f19688c;

        /* renamed from: d */
        Disposable f19689d;

        C4652a(SingleObserver<? super T> asxVar, CompositeDisposable atqVar, AtomicBoolean atomicBoolean) {
            this.f19687b = asxVar;
            this.f19686a = atqVar;
            this.f19688c = atomicBoolean;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19689d = atrVar;
            this.f19686a.mo9939a(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            if (this.f19688c.compareAndSet(false, true)) {
                this.f19686a.mo9936c(this.f19689d);
                this.f19686a.dispose();
                this.f19687b.onSuccess(t);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.f19688c.compareAndSet(false, true)) {
                this.f19686a.mo9936c(this.f19689d);
                this.f19686a.dispose();
                this.f19687b.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }
    }
}
