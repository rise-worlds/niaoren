package p110z1;

/* renamed from: z1.awq */
/* loaded from: classes3.dex */
public interface InnerQueuedObserverSupport<T> {
    void drain();

    void innerComplete(InnerQueuedObserver<T> awpVar);

    void innerError(InnerQueuedObserver<T> awpVar, Throwable th);

    void innerNext(InnerQueuedObserver<T> awpVar, T t);
}
