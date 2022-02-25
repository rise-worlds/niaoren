package p110z1;

/* renamed from: z1.awc */
/* loaded from: classes3.dex */
public final class BlockingFirstObserver<T> extends BlockingBaseObserver<T> {
    @Override // p110z1.Observer
    public void onNext(T t) {
        if (this.f17614a == null) {
            this.f17614a = t;
            this.f17616c.dispose();
            countDown();
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        if (this.f17614a == null) {
            this.f17615b = th;
        }
        countDown();
    }
}
