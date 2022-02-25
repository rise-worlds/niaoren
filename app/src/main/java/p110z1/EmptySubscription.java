package p110z1;

/* renamed from: z1.bsh */
/* loaded from: classes3.dex */
public enum EmptySubscription implements QueueSubscription<Object> {
    INSTANCE;

    @Override // p110z1.dby
    public void cancel() {
    }

    @Override // p110z1.SimpleQueue
    public void clear() {
    }

    @Override // p110z1.SimpleQueue
    public boolean isEmpty() {
        return true;
    }

    @Override // p110z1.SimpleQueue
    @atn
    public Object poll() {
        return null;
    }

    @Override // p110z1.QueueFuseable
    public int requestFusion(int i) {
        return i & 2;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "EmptySubscription";
    }

    @Override // p110z1.dby
    public void request(long j) {
        SubscriptionHelper.validate(j);
    }

    public static void error(Throwable th, Subscriber<?> dbxVar) {
        dbxVar.onSubscribe(INSTANCE);
        dbxVar.onError(th);
    }

    public static void complete(Subscriber<?> dbxVar) {
        dbxVar.onSubscribe(INSTANCE);
        dbxVar.onComplete();
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
