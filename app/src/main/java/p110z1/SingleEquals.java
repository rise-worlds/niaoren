package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bpe */
/* loaded from: classes3.dex */
public final class SingleEquals<T> extends Single<Boolean> {

    /* renamed from: a */
    final SingleSource<? extends T> f19767a;

    /* renamed from: b */
    final SingleSource<? extends T> f19768b;

    public SingleEquals(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2) {
        this.f19767a = ataVar;
        this.f19768b = ataVar2;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Boolean> asxVar) {
        AtomicInteger atomicInteger = new AtomicInteger();
        Object[] objArr = {null, null};
        CompositeDisposable atqVar = new CompositeDisposable();
        asxVar.onSubscribe(atqVar);
        this.f19767a.mo10018a(new C4675a(0, atqVar, objArr, asxVar, atomicInteger));
        this.f19768b.mo10018a(new C4675a(1, atqVar, objArr, asxVar, atomicInteger));
    }

    /* compiled from: SingleEquals.java */
    /* renamed from: z1.bpe$a */
    /* loaded from: classes3.dex */
    static class C4675a<T> implements SingleObserver<T> {

        /* renamed from: a */
        final int f19769a;

        /* renamed from: b */
        final CompositeDisposable f19770b;

        /* renamed from: c */
        final Object[] f19771c;

        /* renamed from: d */
        final SingleObserver<? super Boolean> f19772d;

        /* renamed from: e */
        final AtomicInteger f19773e;

        C4675a(int i, CompositeDisposable atqVar, Object[] objArr, SingleObserver<? super Boolean> asxVar, AtomicInteger atomicInteger) {
            this.f19769a = i;
            this.f19770b = atqVar;
            this.f19771c = objArr;
            this.f19772d = asxVar;
            this.f19773e = atomicInteger;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19770b.mo9939a(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f19771c[this.f19769a] = t;
            if (this.f19773e.incrementAndGet() == 2) {
                SingleObserver<? super Boolean> asxVar = this.f19772d;
                Object[] objArr = this.f19771c;
                asxVar.onSuccess(Boolean.valueOf(ObjectHelper.m9874a(objArr[0], objArr[1])));
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            int i;
            do {
                i = this.f19773e.get();
                if (i >= 2) {
                    RxJavaPlugins.m9212a(th);
                    return;
                }
            } while (!this.f19773e.compareAndSet(i, 2));
            this.f19770b.dispose();
            this.f19772d.onError(th);
        }
    }
}
