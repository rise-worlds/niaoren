package p110z1;

/* renamed from: z1.bri */
/* loaded from: classes3.dex */
public final class BlockingFirstSubscriber<T> extends BlockingBaseSubscriber<T> {
    @Override // p110z1.Subscriber
    public void onNext(T t) {
        if (this.f19996a == null) {
            this.f19996a = t;
            this.f19998c.cancel();
            countDown();
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        if (this.f19996a == null) {
            this.f19997b = th;
        } else {
            RxJavaPlugins.m9212a(th);
        }
        countDown();
    }
}
