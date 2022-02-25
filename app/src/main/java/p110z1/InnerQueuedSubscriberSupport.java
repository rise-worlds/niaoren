package p110z1;

/* renamed from: z1.brq */
/* loaded from: classes3.dex */
public interface InnerQueuedSubscriberSupport<T> {
    void drain();

    void innerComplete(InnerQueuedSubscriber<T> brpVar);

    void innerError(InnerQueuedSubscriber<T> brpVar, Throwable th);

    void innerNext(InnerQueuedSubscriber<T> brpVar, T t);
}
