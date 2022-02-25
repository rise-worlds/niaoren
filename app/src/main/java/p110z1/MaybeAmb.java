package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.bes */
/* loaded from: classes3.dex */
public final class MaybeAmb<T> extends Maybe<T> {

    /* renamed from: a */
    private final MaybeSource<? extends T>[] f18530a;

    /* renamed from: b */
    private final Iterable<? extends MaybeSource<? extends T>> f18531b;

    public MaybeAmb(MaybeSource<? extends T>[] asiVarArr, Iterable<? extends MaybeSource<? extends T>> iterable) {
        this.f18530a = asiVarArr;
        this.f18531b = iterable;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        int i;
        MaybeSource<? extends T>[] asiVarArr = this.f18530a;
        if (asiVarArr == null) {
            asiVarArr = new MaybeSource[8];
            try {
                i = 0;
                for (MaybeSource<? extends T> asiVar : this.f18531b) {
                    if (asiVar == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), asfVar);
                        return;
                    }
                    if (i == asiVarArr.length) {
                        MaybeSource<? extends T>[] asiVarArr2 = new MaybeSource[(i >> 2) + i];
                        System.arraycopy(asiVarArr, 0, asiVarArr2, 0, i);
                        asiVarArr = asiVarArr2;
                    }
                    i++;
                    asiVarArr[i] = asiVar;
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                EmptyDisposable.error(th, asfVar);
                return;
            }
        } else {
            i = asiVarArr.length;
        }
        CompositeDisposable atqVar = new CompositeDisposable();
        asfVar.onSubscribe(atqVar);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        for (int i2 = 0; i2 < i; i2++) {
            MaybeSource<? extends T> asiVar2 = asiVarArr[i2];
            if (atqVar.isDisposed()) {
                return;
            }
            if (asiVar2 == null) {
                atqVar.dispose();
                NullPointerException nullPointerException = new NullPointerException("One of the MaybeSources is null");
                if (atomicBoolean.compareAndSet(false, true)) {
                    asfVar.onError(nullPointerException);
                    return;
                } else {
                    RxJavaPlugins.m9212a(nullPointerException);
                    return;
                }
            } else {
                asiVar2.mo10646a(new C4261a(asfVar, atqVar, atomicBoolean));
            }
        }
        if (i == 0) {
            asfVar.onComplete();
        }
    }

    /* compiled from: MaybeAmb.java */
    /* renamed from: z1.bes$a */
    /* loaded from: classes3.dex */
    static final class C4261a<T> implements MaybeObserver<T> {

        /* renamed from: a */
        final MaybeObserver<? super T> f18532a;

        /* renamed from: b */
        final AtomicBoolean f18533b;

        /* renamed from: c */
        final CompositeDisposable f18534c;

        /* renamed from: d */
        Disposable f18535d;

        C4261a(MaybeObserver<? super T> asfVar, CompositeDisposable atqVar, AtomicBoolean atomicBoolean) {
            this.f18532a = asfVar;
            this.f18534c = atqVar;
            this.f18533b = atomicBoolean;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f18535d = atrVar;
            this.f18534c.mo9939a(atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            if (this.f18533b.compareAndSet(false, true)) {
                this.f18534c.mo9936c(this.f18535d);
                this.f18534c.dispose();
                this.f18532a.onSuccess(t);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.f18533b.compareAndSet(false, true)) {
                this.f18534c.mo9936c(this.f18535d);
                this.f18534c.dispose();
                this.f18532a.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            if (this.f18533b.compareAndSet(false, true)) {
                this.f18534c.mo9936c(this.f18535d);
                this.f18534c.dispose();
                this.f18532a.onComplete();
            }
        }
    }
}
