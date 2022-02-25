package p110z1;

/* renamed from: z1.brj */
/* loaded from: classes3.dex */
public final class BlockingLastSubscriber<T> extends BlockingBaseSubscriber<T> {
    @Override // p110z1.Subscriber
    public void onNext(T t) {
        this.f19996a = t;
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        this.f19996a = null;
        this.f19997b = th;
        countDown();
    }
}
