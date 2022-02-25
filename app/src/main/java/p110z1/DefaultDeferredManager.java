package p110z1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.dbc */
/* loaded from: classes3.dex */
public class DefaultDeferredManager extends AbstractDeferredManager {

    /* renamed from: b */
    public static final boolean f21241b = true;

    /* renamed from: c */
    private final ExecutorService f21242c;

    /* renamed from: d */
    private boolean f21243d;

    public DefaultDeferredManager() {
        this.f21243d = true;
        this.f21242c = Executors.newCachedThreadPool();
    }

    public DefaultDeferredManager(ExecutorService executorService) {
        this.f21243d = true;
        this.f21242c = executorService;
    }

    /* renamed from: b */
    public ExecutorService m3307b() {
        return this.f21242c;
    }

    /* renamed from: a */
    public boolean m3309a(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.f21242c.awaitTermination(j, timeUnit);
    }

    /* renamed from: c */
    public boolean m3304c() {
        return this.f21242c.isShutdown();
    }

    /* renamed from: d */
    public boolean m3303d() {
        return this.f21242c.isTerminated();
    }

    /* renamed from: e */
    public void m3302e() {
        this.f21242c.shutdown();
    }

    /* renamed from: f */
    public List<Runnable> m3301f() {
        return this.f21242c.shutdownNow();
    }

    @Override // p110z1.AbstractDeferredManager
    /* renamed from: b */
    protected void mo3306b(Runnable runnable) {
        this.f21242c.submit(runnable);
    }

    @Override // p110z1.AbstractDeferredManager
    /* renamed from: b */
    protected void mo3305b(Callable callable) {
        this.f21242c.submit(callable);
    }

    @Override // p110z1.AbstractDeferredManager
    /* renamed from: a */
    public boolean mo3310a() {
        return this.f21243d;
    }

    /* renamed from: a */
    public void m3308a(boolean z) {
        this.f21243d = z;
    }
}
