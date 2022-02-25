package p110z1;

/* renamed from: z1.avc */
/* loaded from: classes3.dex */
public enum EmptyDisposable implements QueueDisposable<Object> {
    INSTANCE,
    NEVER;

    @Override // p110z1.SimpleQueue
    public void clear() {
    }

    @Override // p110z1.Disposable
    public void dispose() {
    }

    @Override // p110z1.SimpleQueue
    public boolean isEmpty() {
        return true;
    }

    @Override // p110z1.SimpleQueue
    @atn
    public Object poll() throws Exception {
        return null;
    }

    @Override // p110z1.QueueFuseable
    public int requestFusion(int i) {
        return i & 2;
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this == INSTANCE;
    }

    public static void complete(Observer<?> assVar) {
        assVar.onSubscribe(INSTANCE);
        assVar.onComplete();
    }

    public static void complete(MaybeObserver<?> asfVar) {
        asfVar.onSubscribe(INSTANCE);
        asfVar.onComplete();
    }

    public static void error(Throwable th, Observer<?> assVar) {
        assVar.onSubscribe(INSTANCE);
        assVar.onError(th);
    }

    public static void complete(CompletableObserver arpVar) {
        arpVar.onSubscribe(INSTANCE);
        arpVar.onComplete();
    }

    public static void error(Throwable th, CompletableObserver arpVar) {
        arpVar.onSubscribe(INSTANCE);
        arpVar.onError(th);
    }

    public static void error(Throwable th, SingleObserver<?> asxVar) {
        asxVar.onSubscribe(INSTANCE);
        asxVar.onError(th);
    }

    public static void error(Throwable th, MaybeObserver<?> asfVar) {
        asfVar.onSubscribe(INSTANCE);
        asfVar.onError(th);
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
