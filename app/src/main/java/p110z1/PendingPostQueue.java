package p110z1;

/* renamed from: z1.czn */
/* loaded from: classes3.dex */
final class PendingPostQueue {

    /* renamed from: a */
    private PendingPost f21171a;

    /* renamed from: b */
    private PendingPost f21172b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m3391a(PendingPost czmVar) {
        try {
            if (czmVar != null) {
                if (this.f21172b != null) {
                    this.f21172b.f21170c = czmVar;
                    this.f21172b = czmVar;
                } else if (this.f21171a == null) {
                    this.f21172b = czmVar;
                    this.f21171a = czmVar;
                } else {
                    throw new IllegalStateException("Head present, but no tail");
                }
                notifyAll();
            } else {
                throw new NullPointerException("null cannot be enqueued");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized PendingPost m3393a() {
        PendingPost czmVar;
        czmVar = this.f21171a;
        if (this.f21171a != null) {
            this.f21171a = this.f21171a.f21170c;
            if (this.f21171a == null) {
                this.f21172b = null;
            }
        }
        return czmVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized PendingPost m3392a(int i) throws InterruptedException {
        if (this.f21171a == null) {
            wait(i);
        }
        return m3393a();
    }
}
