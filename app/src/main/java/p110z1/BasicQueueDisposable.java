package p110z1;

/* renamed from: z1.avz */
/* loaded from: classes3.dex */
public abstract class BasicQueueDisposable<T> implements QueueDisposable<T> {
    @Override // p110z1.SimpleQueue
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }

    @Override // p110z1.SimpleQueue
    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
