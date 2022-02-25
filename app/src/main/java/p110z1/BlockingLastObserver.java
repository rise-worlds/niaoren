package p110z1;

/* renamed from: z1.awd */
/* loaded from: classes3.dex */
public final class BlockingLastObserver<T> extends BlockingBaseObserver<T> {
    @Override // p110z1.Observer
    public void onNext(T t) {
        this.f17614a = t;
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        this.f17614a = null;
        this.f17615b = th;
        countDown();
    }
}
