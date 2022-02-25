package p110z1;

import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.cze */
/* loaded from: classes3.dex */
public final class BackgroundPoster implements Runnable, Poster {

    /* renamed from: a */
    private final PendingPostQueue f21110a = new PendingPostQueue();

    /* renamed from: b */
    private final EventBus f21111b;

    /* renamed from: c */
    private volatile boolean f21112c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackgroundPoster(EventBus czfVar) {
        this.f21111b = czfVar;
    }

    @Override // p110z1.Poster
    /* renamed from: a */
    public void mo3390a(Subscription cztVar, Object obj) {
        PendingPost a = PendingPost.m3394a(cztVar, obj);
        synchronized (this) {
            this.f21110a.m3391a(a);
            if (!this.f21112c) {
                this.f21112c = true;
                this.f21111b.m3426e().execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            try {
                PendingPost a = this.f21110a.m3392a(1000);
                if (a == null) {
                    synchronized (this) {
                        a = this.f21110a.m3393a();
                        if (a == null) {
                            return;
                        }
                    }
                }
                this.f21111b.m3440a(a);
            } catch (InterruptedException e) {
                Logger f = this.f21111b.m3424f();
                Level level = Level.WARNING;
                f.mo3398a(level, Thread.currentThread().getName() + " was interruppted", e);
                return;
            } finally {
                this.f21112c = false;
            }
        }
    }
}
