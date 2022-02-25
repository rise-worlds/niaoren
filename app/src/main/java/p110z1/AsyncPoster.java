package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.czd */
/* loaded from: classes3.dex */
public class AsyncPoster implements Runnable, Poster {

    /* renamed from: a */
    private final PendingPostQueue f21108a = new PendingPostQueue();

    /* renamed from: b */
    private final EventBus f21109b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsyncPoster(EventBus czfVar) {
        this.f21109b = czfVar;
    }

    @Override // p110z1.Poster
    /* renamed from: a */
    public void mo3390a(Subscription cztVar, Object obj) {
        this.f21108a.m3391a(PendingPost.m3394a(cztVar, obj));
        this.f21109b.m3426e().execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        PendingPost a = this.f21108a.m3393a();
        if (a != null) {
            this.f21109b.m3440a(a);
            return;
        }
        throw new IllegalStateException("No pending post available");
    }
}
