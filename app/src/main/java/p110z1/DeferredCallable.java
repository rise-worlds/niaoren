package p110z1;

import java.util.concurrent.Callable;
import p110z1.DeferredManager;

/* renamed from: z1.dam */
/* loaded from: classes3.dex */
public abstract class DeferredCallable<D, P> implements Callable<D> {

    /* renamed from: a */
    private final Deferred<D, Throwable, P> f21224a;

    /* renamed from: b */
    private final DeferredManager.EnumC5238a f21225b;

    public DeferredCallable() {
        this.f21224a = new DeferredObject();
        this.f21225b = DeferredManager.EnumC5238a.DEFAULT;
    }

    public DeferredCallable(DeferredManager.EnumC5238a aVar) {
        this.f21224a = new DeferredObject();
        this.f21225b = aVar;
    }

    /* renamed from: a */
    protected void m3345a(P p) {
        this.f21224a.mo3297c(p);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Deferred<D, Throwable, P> m3346a() {
        return this.f21224a;
    }

    /* renamed from: b */
    public DeferredManager.EnumC5238a m3344b() {
        return this.f21225b;
    }
}
