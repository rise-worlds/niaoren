package p110z1;

import p110z1.DeferredManager;

/* renamed from: z1.dap */
/* loaded from: classes3.dex */
public abstract class DeferredRunnable<P> implements Runnable {

    /* renamed from: a */
    private final Deferred<Void, Throwable, P> f21228a;

    /* renamed from: b */
    private final DeferredManager.EnumC5238a f21229b;

    public DeferredRunnable() {
        this.f21228a = new DeferredObject();
        this.f21229b = DeferredManager.EnumC5238a.DEFAULT;
    }

    public DeferredRunnable(DeferredManager.EnumC5238a aVar) {
        this.f21228a = new DeferredObject();
        this.f21229b = aVar;
    }

    /* renamed from: a */
    protected void m3340a(P p) {
        this.f21228a.mo3297c(p);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Deferred<Void, Throwable, P> m3341a() {
        return this.f21228a;
    }

    /* renamed from: b */
    public DeferredManager.EnumC5238a m3339b() {
        return this.f21229b;
    }
}
