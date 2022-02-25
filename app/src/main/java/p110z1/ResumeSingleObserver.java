package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.aww */
/* loaded from: classes3.dex */
public final class ResumeSingleObserver<T> implements SingleObserver<T> {

    /* renamed from: a */
    final AtomicReference<Disposable> f17668a;

    /* renamed from: b */
    final SingleObserver<? super T> f17669b;

    public ResumeSingleObserver(AtomicReference<Disposable> atomicReference, SingleObserver<? super T> asxVar) {
        this.f17668a = atomicReference;
        this.f17669b = asxVar;
    }

    @Override // p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        DisposableHelper.replace(this.f17668a, atrVar);
    }

    @Override // p110z1.SingleObserver
    public void onSuccess(T t) {
        this.f17669b.onSuccess(t);
    }

    @Override // p110z1.SingleObserver
    public void onError(Throwable th) {
        this.f17669b.onError(th);
    }
}
